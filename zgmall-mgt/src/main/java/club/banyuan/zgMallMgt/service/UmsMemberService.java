package club.banyuan.zgMallMgt.service;

import club.banyuan.zgMallMgt.dto.UmsMemberLevelResp;

import java.util.List;

public interface UmsMemberService {
    List<UmsMemberLevelResp> list(Integer defaultStatus);
}
