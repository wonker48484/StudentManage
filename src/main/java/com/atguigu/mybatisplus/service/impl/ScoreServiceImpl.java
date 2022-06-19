package com.atguigu.mybatisplus.service.impl;

import com.atguigu.mybatisplus.entity.Score;
import com.atguigu.mybatisplus.entity.Student;
import com.atguigu.mybatisplus.exception.BizEx;
import com.atguigu.mybatisplus.mapper.ScoreMapper;
import com.atguigu.mybatisplus.req.CommonSearchReq;
import com.atguigu.mybatisplus.req.ScoreAddReq;
import com.atguigu.mybatisplus.req.ScoreUpdateReq;
import com.atguigu.mybatisplus.results.Result;
import com.atguigu.mybatisplus.service.IScoreService;
import com.atguigu.mybatisplus.util.PageUtil;
import com.atguigu.mybatisplus.vo.ScoreVO;
import com.atguigu.mybatisplus.vo.StudentVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wonker47
 * @since 2022-06-13
 */
@Service
@Slf4j
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements IScoreService {
@Resource private ScoreMapper scoreMapper;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addScore(ScoreAddReq addReq) {

        Score score=new Score();
        if(addReq==null){
            log.info("addReq为空");
            return;
        }
        BeanUtils.copyProperties(addReq, score);
        //生成随机id
        long id=(int)(Math.random()*1000000);
        LambdaQueryWrapper<Score> qw1=new LambdaQueryWrapper<>();
        qw1.eq(Score::getId,id);
        long count=Long.valueOf(scoreMapper.selectCount(qw1));
        while(count>0){
            id=(int)(Math.random()*1000000);
            count=Long.valueOf(scoreMapper.selectCount(qw1));
        }
        score.setId(id);
        scoreMapper.insert(score);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<List<ScoreVO>> selectPage(CommonSearchReq searchReq) {
        log.info("searchReq:{}",searchReq);
        PageUtil.startPage(searchReq.getPageNow(),searchReq.getPageSize());
        List<ScoreVO> list =scoreMapper.queryList(searchReq);
        return PageUtil.wrapPageData(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        scoreMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateScoreById(ScoreUpdateReq updateReq) {
        Score scoreToUpdate=new Score();
        BeanUtils.copyProperties(updateReq,scoreToUpdate);
        scoreMapper.updateById(scoreToUpdate);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<ScoreVO> selectById(Long id) {
        ScoreVO vo=scoreMapper.queryList(new CommonSearchReq(String.valueOf(id))).get(0);
        if(vo==null){
            return Result.buildEmptySuccess();
        }
        log.info("查询结果:{}",vo);
        return Result.buildSuccess(vo);
    }

}
