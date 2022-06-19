package com.atguigu.mybatisplus.service.impl;

import com.atguigu.mybatisplus.entity.Pet;
import com.atguigu.mybatisplus.mapper.PetMapper;
import com.atguigu.mybatisplus.service.IPetService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wonker47
 * @since 2022-05-08
 */
@Service
public class PetServiceImpl extends ServiceImpl<PetMapper, Pet> implements IPetService {

}
