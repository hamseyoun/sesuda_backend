package sesuda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import sesuda.dto.MenuDTO;
import sesuda.service.MemberService;
import sesuda.service.MenuService;
import sesuda.util.Message;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    MenuService service;

    @GetMapping(value = "/menulist")
    public ResponseEntity<Message> menuList(MenuDTO dto) {
        Message message = new Message();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        List<MenuDTO> dtos = new ArrayList<>();
        dtos = (service.menuList());

        message.setMessage("success");
        message.setData(dtos);

        return new ResponseEntity<>(message, headers, HttpStatusCode.valueOf(200));
    }

    @PostMapping(value = "/order")
    public ResponseEntity<Message> order(@RequestBody MenuDTO dto) {
        Message message = new Message();
        message.setMessage(service.order(dto));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
}
