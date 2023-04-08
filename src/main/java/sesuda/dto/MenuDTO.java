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

    private int cup;

    // 주문자
    private int memberUid;


    // 프론트단에서 쓰는 값 (서버에서는 안씀)
    private String frontOrderUid;

    // 옵션
    private int orderUid;
    private boolean addShot;
    private boolean addMilk;
    private boolean addSyrup;
    private Timestamp orderDate;

    // 주문 상태
    private int orderState;
}
