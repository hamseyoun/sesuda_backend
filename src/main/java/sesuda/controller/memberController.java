package sesuda.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
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
import java.util.*;

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
                if(resultDTO.getAuth().equals("admin")) {
                    LOGGER.info("admin login");
                    // 로그인시 세션키 생성
                    String uuid = UUID.randomUUID().toString();
                    // DTO의 세션키 세팅
                    resultDTO.setSessionKey(uuid);
                    // 세션키 생성
                    String sessionResult=memberService.sessionKeySet(resultDTO);
                    LOGGER.info("세션키생성완료");
                    //세션키가 있는 DTO로 다시 조회해서 받음
                    MemberDTO sessionResultDTO = memberService.memberLogin(memberDTO);
                    // 비밀번호는 상시 초기화로
                    sessionResultDTO.setPw(null);
                    message.setData(sessionResultDTO);
                    message.setMessage("admin login");
                    return new ResponseEntity<Message>(message, headers, HttpStatus.OK);
                } else{

                LOGGER.info("login success");

                // 로그인시 세션키 생성
                String uuid = UUID.randomUUID().toString();
                // DTO의 세션키 세팅
                resultDTO.setSessionKey(uuid);
                // 세션키 생성
                String sessionResult=memberService.sessionKeySet(resultDTO);
                LOGGER.info("세션키생성완료");
                //세션키가 있는 DTO로 다시 조회해서 받음
                MemberDTO sessionResultDTO = memberService.memberLogin(memberDTO);
                // 비밀번호는 상시 초기화로
                sessionResultDTO.setPw(null);

                message.setData(sessionResultDTO);
                result = "login success";
                }
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

    // 세션을 이용한 조회
    @PostMapping(value = "/memberInformation")
    public ResponseEntity memberInformation(HttpServletResponse response,@RequestBody String sessionKey){
        Message message = new Message();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        JSONObject jsonObject = new JSONObject(sessionKey);
        Object obj = jsonObject.get("sessionKey");
        sessionKey = obj.toString();
        MemberDTO memberDTO = memberService.memberInformation(sessionKey);
        String result="";
        // 비밀번호는 널값 표시
        memberDTO.setPw(null);
        if(!memberDTO.getId().equals(null)){

            //JSONObject obj1 = new JSONObject();
            //obj1.put("asd","asd");
            //HashMap<String,String> map = new HashMap<>();
            //map.put("asd","asd");
            result = "회원조회 성공";
            message.setData(memberDTO);
        }else{
            result = "조회 실패";
        }
        message.setMessage(result);

        return new ResponseEntity<Message>(message, headers, HttpStatus.OK);

    }

    // 로그아웃시 세션키를 지워야함
    @PostMapping(value = "/memberLogout")
    public ResponseEntity memberLogout(HttpServletResponse response,@RequestBody String sessionKey){
        LOGGER.info("로그아웃");
        Message message = new Message();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        JSONObject jsonObject = new JSONObject(sessionKey);
        Object obj = jsonObject.get("sessionKey");
        sessionKey = obj.toString();
        String result="";
        //로그아웃시 세션삭제
        result = memberService.memberLogout(sessionKey);
        message.setMessage(result);

        return new ResponseEntity<Message>(message, headers, HttpStatus.OK);

    }


    @PostMapping(value = "/myOrderList")
    public ResponseEntity myOrderList(HttpServletResponse response,@RequestBody String sessionKey){
        Message message = new Message();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        JSONObject jsonObject = new JSONObject(sessionKey);
        Object obj = jsonObject.get("sessionKey");
        sessionKey = obj.toString();
        MemberDTO memberDTO = memberService.memberInformation(sessionKey);
        String result="";

        Set<Integer> orderUidsSet = new LinkedHashSet<>();
        List<Integer> orderUidsList = new ArrayList<>();
        List<Map<String, Object>> finalList = new ArrayList<>();

        String memberUid = String.valueOf(memberDTO.getMemberUid());
        List<AdminDTO> dtos = memberService.myOrderList(memberUid);

        for (int i = 0; i < dtos.size(); i++) {
            orderUidsSet.add(dtos.get(i).getOrderUid());
        }
        System.out.println("orderUidsSet = " + orderUidsSet);

        Iterator<Integer> orderUidsIter = orderUidsSet.iterator();
        while (orderUidsIter.hasNext()) {
            orderUidsList.add(orderUidsIter.next());
        }

        System.out.println("orderUidsList = " + orderUidsList);

        for (int i = 0; i < orderUidsList.size(); i++) {
            List<AdminDTO> dtosList = new ArrayList<>();
            for (int j = 0; j < dtos.size(); j++) {
                int orderUid_i = orderUidsList.get(i);
                int orderUid_j = dtos.get(j).getOrderUid();

                if( orderUid_i == orderUid_j ){
                    dtosList.add(dtos.get(j));
                }
            }
            Map<String, Object> dtosMap = new HashMap<>();
            dtosMap.put("orderUid", orderUidsList.get(i));
            dtosMap.put("orderList", dtosList);
            finalList.add(dtosMap);
        }

        System.out.println("finalList = " + finalList);

        // 비밀번호는 널값 표시
        memberDTO.setPw(null);
        if(!memberDTO.getId().equals(null)){

            result = "회원조회 성공";
            message.setData(finalList);
        }else{
            result = "조회 실패";
        }
        message.setMessage(result);

        return new ResponseEntity<Message>(message, headers, HttpStatus.OK);

    }


    //주문 수락 상태
    @PostMapping(value="/orderCancel")
    public ResponseEntity<Message> orderCancel(@RequestBody AdminDTO dto) {
        Message message = new Message();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        String result ="";
        System.out.println("dto = " + dto);
        result = memberService.orderCancel(dto);

        message.setMessage(result);
        return new ResponseEntity<>(message, headers, HttpStatusCode.valueOf(200));

    }






}
