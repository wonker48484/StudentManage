package com.atguigu.mybatisplus.mapper;

import com.atguigu.mybatisplus.entity.Score;
import com.atguigu.mybatisplus.req.CommonSearchReq;
import com.atguigu.mybatisplus.vo.ScoreVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wonker47
 * @since 2022-06-13
 */
@Repository
public interface ScoreMapper extends BaseMapper<Score> {
List<ScoreVO> queryList(CommonSearchReq searchReq);
}
