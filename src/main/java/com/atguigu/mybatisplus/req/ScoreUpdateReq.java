package com.atguigu.mybatisplus.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ScoreUpdateReq {
    @NotNull(message = "更新学生成绩时，score的id必须指定")
    private  Long id;

    @NotNull
    private String studentId;

    //    private  String studentName;
    //    @TableField("exam_name")
    @NotBlank
    private String examName;

    //    @TableField("cn_score")
    @NotNull
    private BigDecimal cnScore;
    //    @TableField("en_score")
    @NotNull
    private BigDecimal enScore;

    //    @TableField("math_score")
    @NotNull
    private BigDecimal mathScore;
}
