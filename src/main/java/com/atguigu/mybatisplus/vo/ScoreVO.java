package com.atguigu.mybatisplus.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ScoreVO {
    private String id;

//    @TableField("student_id")
    private String studentId;

    private  String studentName;
//    @TableField("exam_name")
    private String examName;

//    @TableField("cn_score")
    private BigDecimal cnScore;
//    @TableField("en_score")
    private BigDecimal enScore;

//    @TableField("math_score")
    private BigDecimal mathScore;
}
