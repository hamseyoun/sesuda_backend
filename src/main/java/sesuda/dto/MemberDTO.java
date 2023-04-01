package sesuda.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class MemberDTO {
    //회원가입
    private String id;
    private String pw;
    private String nickName;
    private Timestamp joinDate;
    private String auth;

    // 로그인상태인지 1이면 비로그인,2이면 로그인
    private int state = 1;

}
