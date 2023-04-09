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
import java.util.*;


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
        Set<Integer> orderUidsSet = new LinkedHashSet<>();
        List<Integer> orderUidsList = new ArrayList<>();
        List<Map<String, Object>> finalList = new ArrayList<>();

        if(dto.getAuth().equals("admin")) {
            dtos = adminService.orderList();

            for (int i = 0; i < dtos.size(); i++) {
                orderUidsSet.add(dtos.get(i).getOrderUid());
            }

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

            result ="Welcome Admin";
        }
        else{
            result = "not admin";
        }

        message.setMessage(result);
        message.setData(finalList);

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
