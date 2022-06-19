package com.atguigu.mybatisplus.service;

import com.atguigu.mybatisplus.entity.Score;
import com.atguigu.mybatisplus.req.CommonSearchReq;
import com.atguigu.mybatisplus.req.ScoreAddReq;
import com.atguigu.mybatisplus.req.ScoreUpdateReq;
import com.atguigu.mybatisplus.results.Result;
import com.atguigu.mybatisplus.vo.ScoreVO;
import com.atguigu.mybatisplus.vo.StudentVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wonker47
 * @since 2022-06-13
 */
public interface IScoreService extends IService<Score> {
void addScore(ScoreAddReq addReq);

Result<List<ScoreVO>> selectPage(CommonSearchReq searchReq);

void deleteById(Long id);

    void updateScoreById(ScoreUpdateReq updateReq);

    Result<ScoreVO> selectById(Long id);
}
