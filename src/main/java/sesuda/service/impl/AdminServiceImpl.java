package sesuda.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sesuda.dao.AdminDao;
import sesuda.dto.AdminDTO;
import sesuda.dto.MemberDTO;
import sesuda.service.AdminService;

import java.util.ArrayList;
import java.util.List;
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminDao adminDao;
    @Override
    public List<AdminDTO> orderList() {

        System.out.println("AdminDao.orderList() = " + adminDao.orderList());

        return new ArrayList<>(adminDao.orderList());

    }
    
    
    // 주문 수락 상태
    @Override
    public String orderAccept(AdminDTO adminDTO) {
            String result;
        try{
            int intResult = adminDao.orderAccept(adminDTO);
            if(intResult==0) {
                result = "주문수락 실패";
            }else {
                result="주문수락";
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            result = "DB오류";
        }
        return  result;
    }

    // 조리 완료 상태
    @Override
    public String orderFinish(AdminDTO adminDTO) {
        String result;
        try{
            int intResult = adminDao.orderFinish(adminDTO);
            if(intResult==0) {
                result = "조리완료 실패";
            }else {
                result="조리완료";
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            result = "DB오류";
        }
        return  result;
    }

    // 주문 완료 상태
    @Override
    public String orderCancel(AdminDTO adminDTO) {
        String result;
        try{
            int intResult = adminDao.orderCancel(adminDTO);
            if(intResult==0) {
                result = "주문취소 실패";
            }else {
                result= "주문취소";
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            result = "DB오류";
        }
        return  result;
    }


    //관리자 체크용도
    @Override
    public MemberDTO adminCheck(int memberUid) {
        MemberDTO memberDTO = adminDao.adminChecK(memberUid);
        return memberDTO;
    }
}
