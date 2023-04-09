package sesuda.dao;


import org.apache.ibatis.annotations.Mapper;
import sesuda.dto.AdminDTO;
import sesuda.dto.MemberDTO;

import java.util.List;


@Mapper
public interface MemberDao {
    public List<MemberDTO> memberList();

    // 회원가입
    public int memberInsert(MemberDTO memberDTO);

    // 아이디 중복 체크 및 로그인시 아이디 확인
    public int memberIdCheck(String id);

    //아이디 로그인
    public MemberDTO memberLogin(MemberDTO memberDTO);
    // 세션키 생성
    public int sessionKeySet(MemberDTO resultDTO);
    // 세션키를 이용 한 멤버조회
    public MemberDTO memberInformation(String sessionKey);
    // 로그아웃
    public int memberLogout(String sessionKey);

    //내 주문 리스트 가져오기
    public List<AdminDTO> myOrderList(String memberUid);
}
