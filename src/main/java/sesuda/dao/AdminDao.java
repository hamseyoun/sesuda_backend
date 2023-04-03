package sesuda.dao;


import org.apache.ibatis.annotations.Mapper;
import sesuda.dto.AdminDTO;
import sesuda.dto.MemberDTO;

import java.util.List;

@Mapper
public interface AdminDao {
    //주문 리스트
    public List<AdminDTO> orderList();

    // 주문수락 상태
    public int orderAccept(AdminDTO adminDTO);

    // 주문완료 상태
    public int orderFinish(AdminDTO adminDTO);

    // 주문삭제
    public int orderDelete(AdminDTO adminDTO);
    // 관리자 체크용도
    public MemberDTO adminChecK(int memberUid);
}
