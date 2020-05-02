package club.banyuan.zgMallMgt.service;

import club.banyuan.zgMallMgt.common.ResponsePage;
import club.banyuan.zgMallMgt.dto.CmsSubjectResp;

import java.util.List;

public interface CmsSubjectService {
    List<CmsSubjectResp> listAll();

    ResponsePage list(Integer pageNum, Integer pageSize, String keyword);

}
