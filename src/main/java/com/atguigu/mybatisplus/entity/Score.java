package com.atguigu.mybatisplus.entity;

import java.math.BigDecimal;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author wonker47
 * @since 2022-06-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Score implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private long id;

    @TableField("student_id")
    private String studentId;
    @TableField("exam_name")
    private String examName;

    @TableField("cn_score")
    private BigDecimal cnScore;
    @TableField("en_score")
    private BigDecimal enScore;

    @TableField("math_score")
    private BigDecimal mathScore;


}
