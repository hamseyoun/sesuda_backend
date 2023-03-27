package sesuda.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sesuda")
public class JoinController {

    public JSONObject json;

    @GetMapping(value = "/join", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object join(HttpServletResponse response) {

        Map<String, String> status = new HashMap<>();
        status.put("status", "success");
        new JSONObject(status);

        response.setHeader("Access-Control-Allow-Origin", "*");

        return status;
    }
}
