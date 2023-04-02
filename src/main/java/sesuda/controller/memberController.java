package sesuda.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sesuda.dto.AdminDTO;
import sesuda.dto.MemberDTO;
import sesuda.service.MemberService;
import sesuda.util.Message;

import javax.validation.Valid;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/member")
public class memberController {


    private static final Logger LOGGER = LogManager.getLogger(memberController.class);


    @Autowired
    MemberService memberService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public JSONObject json;



    @GetMapping("/memberList")
    public ResponseEntity memberlist(HttpServletResponse response) {
        Message message = new Message();
        HttpHeaders headers= new HttpHeaders();
        List<MemberDTO> dto = new ArrayList<>();
        dto = memberService.memberList();

        System.out.println("dto.get(0).getId() = " + dto.get(0).getId());

        System.out.println("ResponseEntity.ok(dto).getClass() = " + ResponseEntity.ok(dto).getClass());
        return ResponseEntity.ok(dto);

    }

    @PostMapping(value = "/memberJoin")
    public ResponseEntity memberJoin(HttpServletResponse response,@RequestBody MemberDTO memberDTO){
        Message message = new Message();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        System.out.println("memberDTO.getId() = " + memberDTO.getId());
        System.out.println("memberDTO.getNickname() = " + memberDTO.getNickName());
        System.out.println("memberDTO.getPw() = " + memberDTO.getPw());
        //암호화
        memberDTO.setPw(passwordEncoder.encode(memberDTO.getPw()));
        System.out.println("memberDTO = " + memberDTO.getPw());
        String result =memberService.memberInsert(memberDTO);

        System.out.println("memberDTO = " + memberDTO.getPw());
        message.setMessage(result);
        return new ResponseEntity<Message>(message, headers, HttpStatus.OK);

    }
    // id 중복여부체크
    @PostMapping(value = "/memberSameCheck")
    public ResponseEntity memberSameCheck(HttpServletResponse response,@RequestBody String id){
        Message message = new Message();
        HttpHeaders headers= new HttpHeaders();

        JSONObject jsonObject = new JSONObject(id);
        Object obj = jsonObject.get("id");
        id = obj.toString();


        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        String result =memberService.memberSameCheck(id);

        message.setMessage(result);
        return new ResponseEntity<Message>(message, headers, HttpStatus.OK);

    }

    // 로그인
    @PostMapping(value = "/memberLogin")
    public ResponseEntity memberLogin(HttpServletResponse response,@RequestBody MemberDTO memberDTO){
        Message message = new Message();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        String idCheck = memberService.memberIdCheck(memberDTO.getId());
        String result="d";
        //아이디가 존재하는지 파악
        if(idCheck.equals("success")) {
            LOGGER.info("login_equal");

            // 암호화 비교용
            MemberDTO resultDTO = memberService.memberLogin(memberDTO);
            String encodePw = resultDTO.getPw();
            // 아이디,패스워드 일치시
            if(passwordEncoder.matches(memberDTO.getPw(),encodePw))  {
                LOGGER.info("login success");

                // 비밀번호 다시 null
                resultDTO.setPw("null");
                message.setData(resultDTO);
                result = "login success";
            }
            else{
                LOGGER.info("패스워드불일치");
                result = "password-fail";
            }

        }else if(idCheck.equals("fail")){
            result = "not-found-user";
        }
        message.setMessage(result);
        return new ResponseEntity<Message>(message, headers, HttpStatus.OK);

    }


    @GetMapping(value = "/membertest1")
    public ResponseEntity<Message> findById() {
        Message message = new Message();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        List<MemberDTO> dto = new ArrayList<>();
        dto = memberService.memberList();

        message.setMessage("굿나잇");
        message.setData(dto);

        return new ResponseEntity<Message>(message, headers, HttpStatus.OK);
    }


}
