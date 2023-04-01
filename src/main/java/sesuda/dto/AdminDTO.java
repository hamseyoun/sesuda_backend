package sesuda.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class AdminDTO {

    @Min(value = 2 ,message = "떙")
    private int memberUid;

    @NotNull(message = "떙")
    private int auth;
}
