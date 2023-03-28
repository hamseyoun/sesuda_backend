package sesuda.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sesuda.dto.MemberDTO;
import sesuda.service.MemberService;

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
    public JSONObject memberlist(HttpServletResponse response) {


//        Map<String, String> status = new HashMap<>();
//        status.put("status", "success");
//        new JSONObject(status);


        List<MemberDTO> dto = new ArrayList<>();
        dto = memberService.memberList();

        JSONObject dtoo = new JSONObject(dto);

        System.out.println("dto.get(0).getId() = " + dto.get(0).getId());

        //String jsonMap = dtoo.toString();

        return dtoo;
    }


}
