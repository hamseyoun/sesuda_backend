package sesuda.service.impl;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sesuda.dao.MemberDao;
import sesuda.dto.AdminDTO;
import sesuda.dto.MemberDTO;
import sesuda.service.MemberService;
import sesuda.util.Message;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao dao;



    // 멤버리스트
    @Override
    public List<MemberDTO> memberList() {

        return new ArrayList<>(dao.memberList());

    }
    // 회원가입
    @Override
    public String memberInsert(MemberDTO memberDTO) {

        int daoResult;
        String result;

        try{
            daoResult = dao.memberInsert(memberDTO);
            if(daoResult >= 1){
                result =  "회원가입 성공";
            }
            else {
                result = "회원가입 실패";
            }
        }
        catch(Exception e){
            e.getStackTrace();
            result = "DB오류";
        }

        return result;
    }

    // 아이디 중복 체크
    @Override
    public String memberSameCheck(String id) {
        String result;
        int daoResult;

        try{
            daoResult =dao.memberIdCheck(id);
            System.out.println("daoResult = " + daoResult);
            if(daoResult>=1) {
                result = "중복입니다";
            }
            else{
                result = "중복아닙니다";
            }
        }
        catch (Exception e) {
            e.getStackTrace();
            result = "DB오류";
        }
        return result;
    }


    // 로그인시 아이디 있는지 체크
    @Override
    public String memberIdCheck(String id) {
        String result;
        int daoResult;

        try{
            daoResult =dao.memberIdCheck(id);
            System.out.println("daoResult = " + daoResult);
            if(daoResult>=1) {
                result = "success";
            }
            else{
                result = "fail";
            }
        }
        catch (Exception e) {
            e.getStackTrace();
            result = "DB오류";
        }
        return result;
    }
    // 로그인
    @Override
    public MemberDTO memberLogin(MemberDTO memberDTO) {
        String result;

        try{
            memberDTO=dao.memberLogin(memberDTO);
            System.out.println("memberDTO.getId() = " + memberDTO.getId());
            System.out.println("memberDTO.getNickName() = " + memberDTO.getNickName());
            System.out.println("memberDTO = " + memberDTO.getPw());

        }
        catch (Exception e) {
            e.getStackTrace();
            result = "DB오류";
        }
        return memberDTO;
    }
    // 세션키 생성
    @Override
    public String sessionKeySet(MemberDTO resultDTO) {
        String result;
            try{
                int intResult = dao.sessionKeySet(resultDTO);
                if (intResult==0) {
                    result ="세션키 생성 실패";
                }else{
                    result ="세션키 생성";
                }
            }catch (Exception e) {
                e.getStackTrace();
                result ="DB오류";
            }

        return result;
    }
    // 세션키를 이용한 멤버 조회
    @Override
    public MemberDTO memberInformation(String sessionKey) {
        MemberDTO dto = new MemberDTO();

        try{
            dto =dao.memberInformation(sessionKey);
        }catch (Exception e){
            e.getStackTrace();
        }
        return dto;

    }

    @Override
    public String memberLogout(String sessionKey) {
        int intResult;
        String result;
        try{
            intResult= dao.memberLogout(sessionKey);
            if (intResult==0) {
                result ="로그아웃 실패";
            }else{
                result ="로그아웃 성공";
            }
        }catch (Exception e) {
            e.getStackTrace();
            result = "DB오류";
        }
        return result;
    }

    @Override
    public List<AdminDTO> myOrderList(String memberUid) {
        return new ArrayList<>(dao.myOrderList(memberUid));
    }

    @Override
    public String orderCancel(AdminDTO adminDTO) {
        String result;
        try{
            int intResult = dao.orderCancel(adminDTO);
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

}
