package club.banyuan.zgMallMgt.service.imp;

import club.banyuan.zgMallMgt.common.ReqFailException;
import club.banyuan.zgMallMgt.common.ResponsePage;
import club.banyuan.zgMallMgt.dao.SmsHomeRecommendSubjectDao;
import club.banyuan.zgMallMgt.dao.entity.SmsHomeRecommendSubject;
import club.banyuan.zgMallMgt.dao.entity.SmsHomeRecommendSubjectExample;
import club.banyuan.zgMallMgt.dto.SmsHomeRecommendSubjectResp;
import club.banyuan.zgMallMgt.service.SmsHomeRecommendSubjectService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static club.banyuan.zgMallMgt.common.FailReason.SMS_HOME_RECOMMEND_SUBJECT_NOT_EXIST;

@Service
public class SmsHomeRecommendSubjectServiceImpl implements SmsHomeRecommendSubjectService {
    @Autowired
    private SmsHomeRecommendSubjectDao smsHomeRecommendSubjectDao;
    @Override
    public ResponsePage list(Integer pageNum, Integer pageSize, String subjectName, Integer recommendStatus) {
        PageHelper.startPage(pageNum, pageSize);
        SmsHomeRecommendSubjectExample smsHomeRecommendSubjectExample = new SmsHomeRecommendSubjectExample();
        SmsHomeRecommendSubjectExample.Criteria criteria = smsHomeRecommendSubjectExample.createCriteria();
        if (StrUtil.isNotBlank(subjectName)){
            criteria.andSubjectNameLike(StrUtil.concat(true, "%",subjectName,"%"));
        }
        if (recommendStatus!=null){
            criteria.andRecommendStatusEqualTo(recommendStatus);
        }
        smsHomeRecommendSubjectExample.setOrderByClause("`sort` DESC,'id' ASC");
        List<SmsHomeRecommendSubject> smsHomeRecommendSubjects = smsHomeRecommendSubjectDao.selectByExample(smsHomeRecommendSubjectExample);
        PageInfo<SmsHomeRecommendSubject> pageInfo = new PageInfo<>(smsHomeRecommendSubjects);
        List<SmsHomeRecommendSubjectResp> collect = smsHomeRecommendSubjects.stream().map(t -> {
            SmsHomeRecommendSubjectResp smsHomeRecommendSubjectResp = new SmsHomeRecommendSubjectResp();
            BeanUtil.copyProperties(t, smsHomeRecommendSubjectResp);
            return smsHomeRecommendSubjectResp;
        }).collect(Collectors.toList());
//        sorted(Comparator.comparingInt(SmsHomeRecommendSubjectResp::getSort).reversed())
        return ResponsePage.setPages(pageInfo,collect);
    }

    @Override
    public Integer updateSort(Integer sort, Long id) {
        SmsHomeRecommendSubject smsHomeRecommendSubject = smsHomeRecommendSubjectDao.selectByPrimaryKey(id);
        if (BeanUtil.isEmpty(smsHomeRecommendSubject)){
            throw new ReqFailException(SMS_HOME_RECOMMEND_SUBJECT_NOT_EXIST);
        }
        smsHomeRecommendSubject.setSort(sort);
        return smsHomeRecommendSubjectDao.updateByPrimaryKey(smsHomeRecommendSubject);
    }

    @Override
    public Integer updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        ids.forEach(t->{
            SmsHomeRecommendSubject smsHomeRecommendSubject = smsHomeRecommendSubjectDao.selectByPrimaryKey(t);
            if (BeanUtil.isEmpty(smsHomeRecommendSubject)){
                throw new ReqFailException(SMS_HOME_RECOMMEND_SUBJECT_NOT_EXIST);
            }
        });
        return smsHomeRecommendSubjectDao.updateRecommendStatusByIds(ids,recommendStatus);
    }

    @Override
    public Integer delete(List<Long> ids) {
        ids.forEach(t->{
            SmsHomeRecommendSubject smsHomeRecommendSubject = smsHomeRecommendSubjectDao.selectByPrimaryKey(t);
            if (BeanUtil.isEmpty(smsHomeRecommendSubject)){
                throw new ReqFailException(SMS_HOME_RECOMMEND_SUBJECT_NOT_EXIST);
            }
        });
        return smsHomeRecommendSubjectDao.deleteByids(ids);
    }

    @Override
    public Integer create(List<SmsHomeRecommendSubject> smsHomeRecommendSubjects) {
        smsHomeRecommendSubjects.forEach(t->{
            t.setSort(0);
            t.setRecommendStatus(1);
            smsHomeRecommendSubjectDao.insert(t);
        });
        return 1;
    }
}
