package sesuda.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class MenuDTO {
    // 메뉴
    private int menuUid;
    private String menuName;
    private String menuInfo;
    private int menuPrice;
    private String menuPicture;
    private Timestamp menuUploadDate;

    // 주문자
    private String memberUid;

    // 옵션
    private int orderUid;
    private int addShot;
    private int addMilk;
    private int addSyrup;
    private int selectBean;
    private int count;
    private Timestamp orderDate;

    // 주문 상태
    private int orderState;
}
