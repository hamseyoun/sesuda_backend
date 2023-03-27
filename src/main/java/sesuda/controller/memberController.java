package sesuda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sesuda.dto.MemberDTO;
import sesuda.service.MemberService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/member")
public class memberController {
    @Autowired
    MemberService memberService;

    @GetMapping("/memberList")
    public List<MemberDTO> memberlist() {

        List<MemberDTO> dto = new ArrayList<>();
        dto = memberService.memberList();

        System.out.println("dto.get(0).getUserDisplayName() = " + dto.get(0).getUserDisplayName());
        
        return dto;
    }
}
