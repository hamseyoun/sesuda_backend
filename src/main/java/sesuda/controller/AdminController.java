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
        List<AdminDTO> dtos;

        //orderUid만 중복 없이 담을 list를 만들어야 하니까 set을 사용하자
        Set<Integer> orderUidsSet = new LinkedHashSet<>();

        //set은 .get(index)로 가져올 수 없으니, iterator로 set에 담겨있는것을 꺼내 list에 다시 담아줘야한다.
        List<Integer> orderUidsList = new ArrayList<>();

        //최종적으로 나온 값들을 담아줄 list
        List<Map<String, Object>> finalList = new ArrayList<>();

        if(dto.getAuth().equals("admin")) {
            dtos = adminService.orderList();

            //dtos에서 orderUid만 가져와 set에 넣어주는 과정
            for (int i = 0; i < dtos.size(); i++) {
                orderUidsSet.add(dtos.get(i).getOrderUid());
            }

            //set에서 hasNext(), next() 이용해 값을 꺼내서 다시 list에 넣어주는 과정
            Iterator<Integer> orderUidsIter = orderUidsSet.iterator();
            while (orderUidsIter.hasNext()) {
                orderUidsList.add(orderUidsIter.next());
            }

            System.out.println("orderUidsList = " + orderUidsList);


            //list의 길이만큼 for문을 돌림 (list의 상태는 [2,3] 이런 형태)
            for (int i = 0; i < orderUidsList.size(); i++) {
                List<AdminDTO> dtosList = new ArrayList<>();

                //service-dao를 통해 가져온 dtos 사이즈만큼 for문을 돌리는데,
                for (int j = 0; j < dtos.size(); j++) {
                    int orderUid_i = orderUidsList.get(i);
                    int orderUid_j = dtos.get(j).getOrderUid();

                    //dtos의 orderUid가 set->list의 orderUid 와 일치하는 것만 dtosList에 담아줌
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
