package club.banyuan.zgMallMgt.service;

import club.banyuan.zgMallMgt.common.ResponsePage;
import club.banyuan.zgMallMgt.dao.entity.SmsHomeRecommendSubject;

import java.util.List;

public interface SmsHomeRecommendSubjectService {
    ResponsePage list(Integer pageNum, Integer pageSize, String subjectName, Integer recommendStatus);

    Integer updateSort(Integer sort, Long id);

    Integer updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    Integer delete(List<Long> ids);

    Integer create(List<SmsHomeRecommendSubject> smsHomeRecommendSubjects);
}
