package com.atguigu.mybatisplus.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class StudentUpdateReq {
    @NotNull(message = "更新学生时，学生id必须指定")
    private Long id;

    @NotBlank
    private String sno;

    @NotBlank
    private String realName;

    @NotNull
//    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd ")
    private LocalDateTime enrollTime;
}
