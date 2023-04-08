package sesuda.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
public class AdminDTO {

    private int memberUid;

    private String auth;

    private String menuName;

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
