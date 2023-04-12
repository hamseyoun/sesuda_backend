package sesuda.service;

import sesuda.dto.AdminDTO;
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
    // 세션키저장
    public String sessionKeySet(MemberDTO resultDTO);
    //세션키를 이용한 회원정보조회
    public MemberDTO memberInformation(String sessionKey);
    // 로그아웃시 세션키 삭제
    public String memberLogout(String sessionKey);

    //내 주문 리스트 가져오기
    public List<AdminDTO> myOrderList(String memberUid);

    // 내 주문 취소하기
    public String orderCancel(AdminDTO adminDTO);
}
