package sesuda.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class AdminDTO {

    private int memberUid;

    @Min(value=2, message = "유저 권한입니다")
    @NotNull
    private Integer auth;
}
