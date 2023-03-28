package sesuda.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class MemberDTO {
    private String id;
    private String pw;
    private String nickname;
    private Timestamp joinDate;
    private String auth;

}
