package sesuda.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import sesuda.dto.MemberDTO;
import sesuda.dto.MenuDTO;
import sesuda.service.MemberService;
import sesuda.service.MenuService;
import sesuda.util.Message;

import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {

    private static final Logger LOGGER = LogManager.getLogger(MenuController.class);

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
    public ResponseEntity<Message> order(@RequestBody List<Map<String, String>> orderSource)  {
        LOGGER.info("주문진입");
        // 시퀀스용도
        int sequence = service.sequence();

        //현재시간
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        // 아이디값 가져오기
        String temp = orderSource.get(0).get("memberUid");
        int memberUid = Integer.parseInt(temp);

        List<Map<String,Object>> memuList = new ArrayList<>();
        Message message = new Message();
        for(int i=1; i<orderSource.toArray().length;i++) {
            ObjectMapper mapper = new ObjectMapper();
            LOGGER.info("mapper에용 ",mapper);
            Map<String, String> response = orderSource.get(i);
            LOGGER.info("response에용 ",response);
            MenuDTO dto=mapper.convertValue(response, MenuDTO.class);
            // 시퀀스 직접부여
            dto.setOrderUid(sequence+1);
            // 현재 시간 부여
            dto.setOrderDate(timestamp);
            // 아이디 값 넣기
            dto.setMemberUid(memberUid);
            message.setMessage(service.order(dto));
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
}
