package sesuda.dao;


import org.apache.ibatis.annotations.Mapper;
import sesuda.dto.MemberDTO;

import java.util.List;


@Mapper
public interface MemberDao {
    List<MemberDTO> memberList();

    // 회원가입
    int memberInsert(MemberDTO memberDTO);

    // 아이디 중복 체크 및 로그인시 아이디 확인
    int memberIdCheck(String id);

    //아이디 로그인
    MemberDTO memberLogin(MemberDTO memberDTO);
}
