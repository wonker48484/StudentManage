package com.atguigu.mybatisplus.mapper;

import com.atguigu.mybatisplus.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wonker47
 * @since 2022-05-08
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
 public User selectByUserName(String username);
}
