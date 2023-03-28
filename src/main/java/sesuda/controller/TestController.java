package sesuda.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sesuda.dto.MemberDTO;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sesuda")
public class TestController {

    public JSONObject json;

    @GetMapping(value = "/join", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object join(HttpServletResponse response) {

        Map<String, String> status = new HashMap<>();
        status.put("status", "success");
        new JSONObject(status);

        response.setHeader("Access-Control-Allow-OriginAccess-Control-Allow-Origin", "*");

        return status;
    }

    @PostMapping(value = "/test1")
    public String test1(@RequestBody MemberDTO dto, HttpServletResponse response) {

        System.out.println("dto = " + dto);

        return "success!";
    }

}
