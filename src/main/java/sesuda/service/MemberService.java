package sesuda.service;

import sesuda.dto.MemberDTO;


import java.util.List;

public interface MemberService {

    // 멤버리스트
    public List<MemberDTO> memberList();

    // 회원 가입
    public String memberInsert(MemberDTO memberDTO);
    // 아이디 중복 체크
    public String memberSameCheck(String id);

    // 로그인시 아이디 있는지 체크
    public String memberIdCheck(String id);

    // 로그인
    public MemberDTO memberLogin(MemberDTO memberDTO);
}
