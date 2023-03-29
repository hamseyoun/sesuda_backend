package sesuda.dao;

import org.apache.ibatis.annotations.Mapper;
import sesuda.dto.MenuDTO;

import java.util.List;

@Mapper
public interface MenuDao {

    public List<MenuDTO> menuList();

    public void order(MenuDTO dto);

}
