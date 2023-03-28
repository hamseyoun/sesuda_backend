package sesuda.service.impl;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sesuda.dao.MemberDao;
import sesuda.dto.MemberDTO;
import sesuda.service.MemberService;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao dao;
    @Override
    public List<MemberDTO> memberList() {

        return new ArrayList<>(dao.memberList());

    }
}
