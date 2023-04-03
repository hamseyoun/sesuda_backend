package sesuda.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sesuda.dto.AdminDTO;
import sesuda.dto.MemberDTO;
import sesuda.dto.MenuDTO;
import sesuda.service.AdminService;
import sesuda.util.Message;
import javax.validation.Valid;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


@RequestMapping("/admin")
@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping(value = "/orderList")
    public ResponseEntity<Message> admin(@RequestBody String memberUid) {
        Message message = new Message();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        JSONObject jsonObject = new JSONObject(memberUid);
        Object obj = jsonObject.get("memberUid");
        memberUid = obj.toString();
        int intMemberUid = Integer.parseInt(memberUid);

        MemberDTO dto = adminService.adminCheck(intMemberUid);

        String result ="";
        List<AdminDTO> dtos = new ArrayList<>();
        if(dto.getAuth().equals("admin")) {
            dtos = adminService.orderList();
            result ="Welcome Admin";
        }
        else{
            result = "not admin";
        }

        message.setMessage(result);
        message.setData(dtos);

        return new ResponseEntity<>(message, headers, HttpStatusCode.valueOf(200));

    }
    //주문 수락 상태
    @PostMapping(value="/orderAccept")
    public ResponseEntity<Message> orderAccept(@RequestBody AdminDTO dto) {
        Message message = new Message();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        String result ="";

        result = adminService.orderAccept(dto);

        message.setMessage(result);
        return new ResponseEntity<>(message, headers, HttpStatusCode.valueOf(200));

    }

    //주문 수락 상태
    @PostMapping(value="/orderFinish")
    public ResponseEntity<Message> orderFinish(@RequestBody AdminDTO dto) {
        Message message = new Message();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        String result ="";
        result = adminService.orderFinish(dto);

        message.setMessage(result);
        return new ResponseEntity<>(message, headers, HttpStatusCode.valueOf(200));

    }

    //주문 수락 상태
    @PostMapping(value="/orderCancel")
    public ResponseEntity<Message> orderCancel(@RequestBody AdminDTO dto) {
        Message message = new Message();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        String result ="";
        result = adminService.orderCancel(dto);

        message.setMessage(result);
        return new ResponseEntity<>(message, headers, HttpStatusCode.valueOf(200));

    }
}
