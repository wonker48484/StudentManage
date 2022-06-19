package com.atguigu.mybatisplus.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class StudentAddReq {
    @NotBlank
    private String sno;

    @NotBlank
    private String realName;

    @NotNull
//    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd ")
    private LocalDateTime enrollTime;


}
