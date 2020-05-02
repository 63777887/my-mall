package club.banyuan.zgMallMgt.service.imp;

import club.banyuan.zgMallMgt.common.ReqFailException;
import club.banyuan.zgMallMgt.common.ResponsePage;
import club.banyuan.zgMallMgt.dao.SmsHomeNewProductDao;
import club.banyuan.zgMallMgt.dao.entity.SmsHomeNewProduct;
import club.banyuan.zgMallMgt.dao.entity.SmsHomeNewProductExample;
import club.banyuan.zgMallMgt.dao.entity.SmsHomeRecommendProduct;
import club.banyuan.zgMallMgt.dao.entity.SmsHomeRecommendProductExample;
import club.banyuan.zgMallMgt.dto.SmsHomeNewProductResp;
import club.banyuan.zgMallMgt.dto.SmsHomeRecommendProductResp;
import club.banyuan.zgMallMgt.service.SmsHomeNewProductService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static club.banyuan.zgMallMgt.common.FailReason.*;

@Service
public class SmsHomeNewProductServiceImpl implements SmsHomeNewProductService {

    @Autowired
    private SmsHomeNewProductDao smsHomeNewProductDao;

    @Override
    public ResponsePage list(Integer pageNum, Integer pageSize, String productName, Integer recommendStatus) {
        PageHelper.startPage(pageNum, pageSize);
        SmsHomeNewProductExample smsHomeNewProductExample = new SmsHomeNewProductExample();
        SmsHomeNewProductExample.Criteria criteria = smsHomeNewProductExample.createCriteria();
        if (StrUtil.isNotBlank(productName)){
            criteria.andProductNameLike(StrUtil.concat(true, "%",productName,"%"));
        }
        if (recommendStatus!=null){
            criteria.andRecommendStatusEqualTo(recommendStatus);
        }
        smsHomeNewProductExample.setOrderByClause("`sort` DESC,'id' ASC");
        List<SmsHomeNewProduct> smsHomeNewProducts = smsHomeNewProductDao.selectByExample(smsHomeNewProductExample);
        PageInfo<SmsHomeNewProduct> pageInfo = new PageInfo<>(smsHomeNewProducts);
        List<SmsHomeNewProductResp> collect = smsHomeNewProducts.stream().map(t -> {
            SmsHomeNewProductResp smsHomeNewProductResp = new SmsHomeNewProductResp();
            BeanUtil.copyProperties(t, smsHomeNewProductResp);
            return smsHomeNewProductResp;
        }).collect(Collectors.toList());
        return ResponsePage.setPages(pageInfo,collect);
    }

    @Override
    public Integer updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        ids.forEach(t->{
            SmsHomeNewProduct smsHomeNewProduct = smsHomeNewProductDao.selectByPrimaryKey(t);
            if (BeanUtil.isEmpty(smsHomeNewProduct)){
                throw new ReqFailException(SMS_HOME_NEW_PRODUCT_NOT_EXIST);
            }
        });
        return smsHomeNewProductDao.updateRecommendStatusByIds(ids,recommendStatus);
    }

    @Override
    public Integer create(List<SmsHomeNewProduct> smsHomeNewProducts) {
        smsHomeNewProducts.forEach(t->{
            t.setSort(0);
            t.setRecommendStatus(1);
            smsHomeNewProductDao.insert(t);
        });
        return 1;
    }

    @Override
    public Integer updateSort(Integer sort, Long id) {
        SmsHomeNewProduct smsHomeNewProduct = smsHomeNewProductDao.selectByPrimaryKey(id);
        if (BeanUtil.isEmpty(smsHomeNewProduct)){
            throw new ReqFailException(SMS_HOME_NEW_PRODUCT_NOT_EXIST);
        }
        smsHomeNewProduct.setSort(sort);
        return smsHomeNewProductDao.updateByPrimaryKey(smsHomeNewProduct);
    }

    @Override
    public Integer delete(List<Long> ids) {
        ids.forEach(t->{
            SmsHomeNewProduct smsHomeNewProduct = smsHomeNewProductDao.selectByPrimaryKey(t);
            if (BeanUtil.isEmpty(smsHomeNewProduct)){
                throw new ReqFailException(SMS_HOME_RECOMMEND_SUBJECT_NOT_EXIST);
            }
        });
        return smsHomeNewProductDao.deleteByids(ids);
    }
}
