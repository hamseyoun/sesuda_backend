package sesuda.service;

import sesuda.dto.AdminDTO;
import sesuda.dto.MenuDTO;

import java.util.List;

public interface AdminService {
    //주문 리스트
    public List<AdminDTO> orderList();
    //주문 수락
    public String orderAccept(AdminDTO adminDTO);
    //주문 완료
    public String orderFinish(AdminDTO adminDTO);
    //주문 취소
    public String orderCancel(AdminDTO adminDTO);
}
