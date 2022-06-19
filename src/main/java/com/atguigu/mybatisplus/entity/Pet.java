package com.atguigu.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author wonker47
 * @since 2022-05-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Pet implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("petName")
    private String petname;

    @TableId("petId")
    private String petid;


}
