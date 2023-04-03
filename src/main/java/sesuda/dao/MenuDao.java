package sesuda.dao;

import org.apache.ibatis.annotations.Mapper;
import sesuda.dto.MenuDTO;

import java.util.List;

@Mapper
public interface MenuDao {
    // 메뉴리스트
    public List<MenuDTO> menuList();
    // 주문하기
    public void order(MenuDTO dto);
    // 직접 시퀀스 부여
    public int sequence();
}
