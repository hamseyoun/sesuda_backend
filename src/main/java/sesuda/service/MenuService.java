package sesuda.service;

import org.springframework.stereotype.Service;
import sesuda.dto.MenuDTO;

import java.util.List;

public interface MenuService {

    //메뉴리스트 가져오기
    public List<MenuDTO> menuList();
}
