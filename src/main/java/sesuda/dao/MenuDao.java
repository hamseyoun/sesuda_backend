package sesuda.dao;

import org.apache.ibatis.annotations.Mapper;
import sesuda.dto.MenuDTO;

import java.util.List;

@Mapper
public interface MenuDao {

    List<MenuDTO> menuList();

}
