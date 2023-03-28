package sesuda.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sesuda.dto.MemberDTO;
import sesuda.service.MemberService;
import sesuda.util.Message;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/member")
public class memberController {
    @Autowired
    MemberService memberService;

    public JSONObject json;

    @GetMapping("/memberList")
    public ResponseEntity memberlist(HttpServletResponse response) {

        List<MemberDTO> dto = new ArrayList<>();
        dto = memberService.memberList();

        System.out.println("dto.get(0).getId() = " + dto.get(0).getId());

        System.out.println("ResponseEntity.ok(dto).getClass() = " + ResponseEntity.ok(dto).getClass());
        return ResponseEntity.ok(dto);

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

        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }


}
