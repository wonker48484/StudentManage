package com.atguigu.mybatisplus.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 学生表
 * </p>
 *
 * @author wonker47
 * @since 2022-06-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private long id;

    @TableField("sno")
    private String sno;

    @TableField("real_name")
    private String realName;

    /**
     * 入学日期
     */
    @TableField("enroll_time")
//    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd ")
    private LocalDateTime enrollTime;


}
