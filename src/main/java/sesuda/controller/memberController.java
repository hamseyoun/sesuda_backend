package sesuda.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sesuda.dto.MemberDTO;
import sesuda.service.MemberService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/member")
public class memberController {
    @Autowired
    MemberService memberService;

    public JSONObject json;

    @GetMapping("/memberList")
    public Object memberlist(HttpServletResponse response) {


        response.setHeader("Access-Control-Allow-OriginAccess-Control-Allow-Origin", "*");
        List<MemberDTO> dto = new ArrayList<>();
        dto = memberService.memberList();

        Object dtoo = new JSONObject(dto);

        System.out.println("dto.get(0).getId() = " + dto.get(0).getId());
        
        return dtoo;
    }


}
