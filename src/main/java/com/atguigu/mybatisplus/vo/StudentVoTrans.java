package com.atguigu.mybatisplus.vo;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class StudentVoTrans {
    //主键
    private long id;

    //    @TableField("sno")
    private String sno;

    //    @TableField("real_name")
    private String realName;

    /**
     * 入学日期
     */
//    @TableField("enroll_time")
//    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd ")
    private LocalDate enrollTime;
}
