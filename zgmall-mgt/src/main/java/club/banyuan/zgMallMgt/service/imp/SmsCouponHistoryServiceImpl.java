package club.banyuan.zgMallMgt.service.imp;

import club.banyuan.zgMallMgt.common.ResponsePage;
import club.banyuan.zgMallMgt.dao.SmsCouponHistoryDao;
import club.banyuan.zgMallMgt.dao.entity.SmsCouponHistory;
import club.banyuan.zgMallMgt.dao.entity.SmsCouponHistoryExample;
import club.banyuan.zgMallMgt.dto.SmsCouponHistoryResp;
import club.banyuan.zgMallMgt.service.SmsCouponHistoryService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SmsCouponHistoryServiceImpl implements SmsCouponHistoryService {

    @Autowired
    private SmsCouponHistoryDao smsCouponHistoryDao;

    @Override
    public ResponsePage list(Integer pageNum, Integer pageSize, Long couponId, Integer useStatus, String orderSn) {
        PageHelper.startPage(pageNum,pageSize);
        SmsCouponHistoryExample smsCouponHistoryExample = new SmsCouponHistoryExample();
        SmsCouponHistoryExample.Criteria criteria = smsCouponHistoryExample.createCriteria();
        criteria.andCouponIdEqualTo(couponId);
        if (useStatus!=null){
            criteria.andUseStatusEqualTo(useStatus);
        }
        if (StrUtil.isNotBlank(orderSn)){
            criteria.andOrderSnLike(StrUtil.concat(true, "%",orderSn,"%"));
        }
        List<SmsCouponHistory> smsCouponHistories = smsCouponHistoryDao.selectByExample(smsCouponHistoryExample);
        PageInfo<SmsCouponHistory> pageInfo = new PageInfo<>(smsCouponHistories);
        List<SmsCouponHistoryResp> collect = smsCouponHistories.stream().map(t -> {
            SmsCouponHistoryResp smsCouponHistoryResp = new SmsCouponHistoryResp();
            BeanUtil.copyProperties(t, smsCouponHistoryResp);
            return smsCouponHistoryResp;
        }).collect(Collectors.toList());
        return ResponsePage.setPages(pageInfo,collect);
    }
}
