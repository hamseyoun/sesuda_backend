package sesuda.dao;


import org.apache.ibatis.annotations.Mapper;
import sesuda.dto.MemberDTO;

import java.util.List;


@Mapper
public interface MemberDao {
    List<MemberDTO> memberList();
    int memberInsert(MemberDTO memberDTO);

}
