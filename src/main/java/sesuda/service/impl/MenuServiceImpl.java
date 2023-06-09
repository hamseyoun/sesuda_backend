package sesuda.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sesuda.dao.MenuDao;
import sesuda.dto.MenuDTO;
import sesuda.service.MenuService;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuDao dao;

    @Override
    public List<MenuDTO> menuList() {
        return new ArrayList<>(dao.menuList());
    }

    @Override
    public String order(MenuDTO dto) {
        String result = "";

        try {
            dao.order(dto);
            result = "order success!";
        } catch (Exception e) {
            result = "order fail!";
        }

        return result;
    }

    @Override
    public int sequence() {
        int result = dao.sequence();
        return result;
    }
}
