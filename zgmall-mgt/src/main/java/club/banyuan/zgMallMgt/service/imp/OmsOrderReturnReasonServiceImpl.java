package club.banyuan.zgMallMgt.service.imp;

import club.banyuan.zgMallMgt.common.ReqFailException;
import club.banyuan.zgMallMgt.common.ResponsePage;
import club.banyuan.zgMallMgt.dao.OmsOrderReturnReasonDao;
import club.banyuan.zgMallMgt.dao.entity.OmsOrderReturnReason;
import club.banyuan.zgMallMgt.dto.OmsOrderReturnReasonResp;
import club.banyuan.zgMallMgt.service.OmsOrderReturnReasonService;
import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static club.banyuan.zgMallMgt.common.FailReason.OMS_ORDER_RETURN_REASON_NOT_EXIST;

@Service
public class OmsOrderReturnReasonServiceImpl implements OmsOrderReturnReasonService {

    @Autowired
    private OmsOrderReturnReasonDao omsOrderReturnReasonDao;

    @Override
    public ResponsePage list(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<OmsOrderReturnReason> omsOrderReturnReasons = omsOrderReturnReasonDao.selectAll();
        PageInfo<OmsOrderReturnReason> pageInfo = new PageInfo<>(omsOrderReturnReasons);
        List<OmsOrderReturnReasonResp> collect = omsOrderReturnReasons.stream().map(t -> {
            OmsOrderReturnReasonResp omsOrderReturnReasonResp = new OmsOrderReturnReasonResp();
            BeanUtil.copyProperties(t, omsOrderReturnReasonResp);
            return omsOrderReturnReasonResp;
        }).collect(Collectors.toList());
        return ResponsePage.setPages(pageInfo,collect);
    }

    @Override
    public Integer create(OmsOrderReturnReason omsOrderReturnReason) {
        if (omsOrderReturnReason.getCreateTime()==null) {
            omsOrderReturnReason.setCreateTime(new Date());
        }
        return omsOrderReturnReasonDao.insert(omsOrderReturnReason);
    }

    @Override
    public OmsOrderReturnReasonResp getInfo(Long id) {
        OmsOrderReturnReason omsOrderReturnReason = omsOrderReturnReasonDao.selectByPrimaryKey(id);
        if (BeanUtil.isEmpty(omsOrderReturnReason)){
            throw new ReqFailException(OMS_ORDER_RETURN_REASON_NOT_EXIST);
        }
        OmsOrderReturnReasonResp omsOrderReturnReasonResp = new OmsOrderReturnReasonResp();
        BeanUtil.copyProperties(omsOrderReturnReason, omsOrderReturnReasonResp);
        return omsOrderReturnReasonResp;
    }

    @Override
    public Integer update(OmsOrderReturnReason omsOrderReturnReason, Long id) {
        OmsOrderReturnReason omsOrderReturnReason1 = omsOrderReturnReasonDao.selectByPrimaryKey(id);
        if (BeanUtil.isEmpty(omsOrderReturnReason1)){
            throw new ReqFailException(OMS_ORDER_RETURN_REASON_NOT_EXIST);
        }
        omsOrderReturnReason.setId(id);
        return omsOrderReturnReasonDao.updateByPrimaryKey(omsOrderReturnReason);
    }

    @Override
    public Integer delete(List<Long> ids) {
        ids.forEach(t->{
            OmsOrderReturnReason omsOrderReturnReason = omsOrderReturnReasonDao.selectByPrimaryKey(t);
            if (BeanUtil.isEmpty(omsOrderReturnReason)){
                throw new ReqFailException(OMS_ORDER_RETURN_REASON_NOT_EXIST);
            }
        });
        return omsOrderReturnReasonDao.deleteByPrimaryKeys(ids);
    }

    @Override
    public Integer updateStatus(List<Long> ids, Integer status) {
        ids.forEach(t->{
            OmsOrderReturnReason omsOrderReturnReason = omsOrderReturnReasonDao.selectByPrimaryKey(t);
            if (BeanUtil.isEmpty(omsOrderReturnReason)){
                throw new ReqFailException(OMS_ORDER_RETURN_REASON_NOT_EXIST);
            }
        });
        return omsOrderReturnReasonDao.updateStatusByids(ids,status);
    }

}
