package club.banyuan.zgMallMgt.service.imp;

import club.banyuan.zgMallMgt.common.ReqFailException;
import club.banyuan.zgMallMgt.common.ResponsePage;
import club.banyuan.zgMallMgt.dao.OmsOrderDao;
import club.banyuan.zgMallMgt.dao.OmsOrderItemDao;
import club.banyuan.zgMallMgt.dao.OmsOrderOperateHistoryDao;
import club.banyuan.zgMallMgt.dao.UmsAdminDao;
import club.banyuan.zgMallMgt.dao.entity.*;
import club.banyuan.zgMallMgt.dto.DeliveryResp;
import club.banyuan.zgMallMgt.dto.OmsOrderInfoResp;
import club.banyuan.zgMallMgt.dto.OmsOrderResp;
import club.banyuan.zgMallMgt.service.OmsOrderService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static club.banyuan.zgMallMgt.common.FailReason.OMS_ORDER_NOT_EXIST;

@Service
public class OmsOrderServiceImpl implements OmsOrderService {

    @Autowired
    private OmsOrderDao omsOrderDao;
    @Autowired
    private OmsOrderItemDao omsOrderItemDao;
    @Autowired
    private OmsOrderOperateHistoryDao omsOrderOperateHistoryDao;
    @Autowired
    private UmsAdminDao umsAdminDao;

    @Override
    public ResponsePage list(Integer pageNum, Integer pageSize, String orderSn, String receiverKeyword,
                             Integer status, Integer orderType, Integer sourceType, Date createTime) {
        PageHelper.startPage(pageNum, pageSize);
        OmsOrderExample omsOrderExample = new OmsOrderExample();
        OmsOrderExample.Criteria criteria = omsOrderExample.createCriteria();
        OmsOrderExample.Criteria criteria1 = omsOrderExample.createCriteria();
        if (StrUtil.isNotBlank(orderSn)){
            criteria.andOrderSnLike(StrUtil.concat(true,"%"+orderSn+"%"));
        }

        if (StrUtil.isNotBlank(receiverKeyword)){
            criteria.andReceiverNameLike(StrUtil.concat(true,"%",receiverKeyword,"%"));
            criteria1.andReceiverPhoneLike(StrUtil.concat(true,"%",receiverKeyword,"%"));
        }
        if (status!=null){
            criteria.andStatusEqualTo(status);
        }
        if (orderType!=null){
            criteria.andOrderTypeEqualTo(orderType);
        }
        if (sourceType!=null){
            criteria.andSourceTypeEqualTo(sourceType);
        }
        if (createTime!=null){
            criteria.andCommentTimeEqualTo(createTime);
        }
        omsOrderExample.or(criteria1);
        List<OmsOrder> omsOrders = omsOrderDao.selectByExample(omsOrderExample);
        PageInfo<OmsOrder> pageInfo = new PageInfo<>(omsOrders);
        List<OmsOrderResp> collect = omsOrders.stream().map(t -> {
            OmsOrderResp omsOrderResp = new OmsOrderResp();
            BeanUtil.copyProperties(t, omsOrderResp);
            return omsOrderResp;
        }).collect(Collectors.toList());
        return ResponsePage.setPages(pageInfo   ,collect);
    }

    @Override
    public OmsOrderInfoResp showOrderInfo(Long orderId) {
        OmsOrder omsOrder = omsOrderDao.selectByPrimaryKey(orderId);
        if (BeanUtil.isEmpty(omsOrder)){
            throw new ReqFailException(OMS_ORDER_NOT_EXIST);
        }
        List<OmsOrderItem> omsOrderItems = omsOrderItemDao.selectByOrderId(orderId);
        OmsOrderInfoResp omsOrderInfoResp = new OmsOrderInfoResp();
        List<OmsOrderOperateHistory> omsOrderOperateHistories = omsOrderOperateHistoryDao.selectByOrderId(orderId);
        BeanUtil.copyProperties(omsOrder, omsOrderInfoResp);
        omsOrderInfoResp.setOrderItemList(omsOrderItems);
        omsOrderInfoResp.setHistoryList(omsOrderOperateHistories);
        return omsOrderInfoResp;
    }

    @Override
    @Transactional
    public Integer delivery(List<DeliveryResp> deliveryResp, Principal principal) {
        deliveryResp.forEach(t->{
            Long orderId = t.getOrderId();
            OmsOrder omsOrder = omsOrderDao.selectByPrimaryKey(orderId);
            if (BeanUtil.isEmpty(omsOrder)){
                throw new ReqFailException(OMS_ORDER_NOT_EXIST);
            }
            omsOrderDao.uodateDeliveryById(t.getDeliveryCompany(),2,t.getDeliverySn(),new Date(),orderId);
            String adminId = principal.getName();
            UmsAdmin umsAdmin = umsAdminDao.selectByPrimaryKey(Long.parseLong(adminId));
            String nickName = umsAdmin.getNickName();
            omsOrderOperateHistoryDao.insertByOrderId(orderId,nickName,new Date(),2,"完成发货");
        });
        return 1;
    }

    @Override
    @Transactional
    public Integer delete(List<Long> ids) {
        ids.forEach(t->{
            OmsOrder omsOrder = omsOrderDao.selectByPrimaryKey(t);
            if (BeanUtil.isEmpty(omsOrder)){
                throw new ReqFailException(OMS_ORDER_NOT_EXIST);
            }
//            omsOrder.setDeleteStatus(1);
//            omsOrderDao.updateByPrimaryKey(omsOrder);
        });
        OmsOrderExample omsOrderExample = new OmsOrderExample();
        OmsOrderExample.Criteria criteria = omsOrderExample.createCriteria();
        criteria.andIdIn(ids);
        omsOrderDao.deleteByExample(omsOrderExample);
        return omsOrderOperateHistoryDao.deleteByOrderIds(ids);
    }

    @Override
    public Integer close(List<Long> ids, String note, Principal principal) {
        String adminId = principal.getName();
        UmsAdmin umsAdmin = umsAdminDao.selectByPrimaryKey(Long.parseLong(adminId));
        String nickName = umsAdmin.getNickName();
        ids.forEach(t->{
            OmsOrder omsOrder = omsOrderDao.selectByPrimaryKey(t);
            if (BeanUtil.isEmpty(omsOrder)){
                throw new ReqFailException(OMS_ORDER_NOT_EXIST);
            }
            omsOrder.setStatus(4);
            omsOrderDao.updateByPrimaryKey(omsOrder);
            omsOrderOperateHistoryDao.insertByOrderId(omsOrder.getId(),nickName,new Date(),4,note);
        });
        return 1;
    }

    @Override
    public Integer updateNote(Long id, String note, Integer status) {
        return omsOrderDao.updateNoteById(id,note,status);
    }
}
