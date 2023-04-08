package sesuda.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class MemberDTO {
    //회원가입

    private int memberUid;
    private String id;
    private String pw;
    private String nickName;
    private Timestamp joinDate;
    private String auth;

    // 세션키
    private String sessionKey;

}
