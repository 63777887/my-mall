package club.banyuan.zgMallMgt.service;

import club.banyuan.zgMallMgt.dao.entity.CmsSubject;
import club.banyuan.zgMallMgt.dto.CmsSubjectResp;

import java.util.List;

public interface CmsSubjectService {
    List<CmsSubjectResp> listAll();
}
