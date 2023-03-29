package sesuda.service;

import sesuda.dto.MemberDTO;


import java.util.List;

public interface MemberService {

    // 멤버인원
    public List<MemberDTO> memberList();

    // 회원 가입
    public String memberInsert(MemberDTO memberDTO);
    // 아이디 중복 체크
    public String memberSameCheck(String id);
}
