package sesuda.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class MenuDTO {
    private int menuUid;
    private String menuName;
    private String menuInfo;
    private int menuPrice;
    private String menuPicture;
    private Timestamp menuUploadDate;
}
