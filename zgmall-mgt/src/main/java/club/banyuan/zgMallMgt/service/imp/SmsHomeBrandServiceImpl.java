package club.banyuan.zgMallMgt.service.imp;

import club.banyuan.zgMallMgt.common.ReqFailException;
import club.banyuan.zgMallMgt.common.ResponsePage;
import club.banyuan.zgMallMgt.dao.SmsHomeBrandDao;
import club.banyuan.zgMallMgt.dao.entity.SmsHomeBrand;
import club.banyuan.zgMallMgt.dao.entity.SmsHomeBrandExample;
import club.banyuan.zgMallMgt.dao.entity.SmsHomeNewProduct;
import club.banyuan.zgMallMgt.dto.SmsHomeBrandResp;
import club.banyuan.zgMallMgt.service.SmsHomeBrandService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.util.List;
import java.util.stream.Collectors;

import static club.banyuan.zgMallMgt.common.FailReason.*;

@Service
public class SmsHomeBrandServiceImpl implements SmsHomeBrandService {

    @Autowired
    private SmsHomeBrandDao smsHomeBrandDao;

    @Override
    public ResponsePage list(Integer pageNum, Integer pageSize, String brandName, Integer recommendStatus) {
        PageHelper.startPage(pageNum,pageSize);
        SmsHomeBrandExample smsHomeBrandExample = new SmsHomeBrandExample();
        SmsHomeBrandExample.Criteria criteria = smsHomeBrandExample.createCriteria();
        if (StrUtil.isNotBlank(brandName)){
            criteria.andBrandNameLike(StrUtil.concat(true, "%",brandName,"%"));
        }
        if (recommendStatus!=null){
            criteria.andRecommendStatusEqualTo(recommendStatus);
        }
        smsHomeBrandExample.setOrderByClause("`sort` DESC,'id' ASC");
        List<SmsHomeBrand> smsHomeBrands = smsHomeBrandDao.selectByExample(smsHomeBrandExample);
        PageInfo<SmsHomeBrand> pageInfo = new PageInfo<>(smsHomeBrands);
        List<SmsHomeBrandResp> collect = smsHomeBrands.stream().map(t -> {
            SmsHomeBrandResp smsHomeBrandResp = new SmsHomeBrandResp();
            BeanUtil.copyProperties(t, smsHomeBrandResp);
            return smsHomeBrandResp;
        }).collect(Collectors.toList());
        return ResponsePage.setPages(pageInfo,collect);
    }

    @Override
    public Integer create(List<SmsHomeBrand> smsHomeBrands) {
        smsHomeBrands.forEach(t->{
            t.setSort(0);
            t.setRecommendStatus(1);
            smsHomeBrandDao.insert(t);
        });
        return 1;
    }

    @Override
    public Integer updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        ids.forEach(t->{
            SmsHomeBrand smsHomeBrand = smsHomeBrandDao.selectByPrimaryKey(t);
            if (BeanUtil.isEmpty(smsHomeBrand)){
                throw new ReqFailException(SMS_HOME_BRAND_NOT_EXIST);
            }
        });
        return smsHomeBrandDao.updateRecommendStatusByIds(ids,recommendStatus);
    }

    @Override
    public Integer updateSort(Integer sort, Long id) {
        SmsHomeBrand smsHomeBrand = smsHomeBrandDao.selectByPrimaryKey(id);
        if (BeanUtil.isEmpty(smsHomeBrand)){
            throw new ReqFailException(SMS_HOME_BRAND_NOT_EXIST);
        }
        smsHomeBrand.setSort(sort);
        return smsHomeBrandDao.updateByPrimaryKey(smsHomeBrand);
    }

    @Override
    public Integer delete(List<Long> ids) {
        ids.forEach(t->{
            SmsHomeBrand smsHomeBrand = smsHomeBrandDao.selectByPrimaryKey(t);
            if (BeanUtil.isEmpty(smsHomeBrand)){
                throw new ReqFailException(SMS_HOME_BRAND_NOT_EXIST);
            }
        });
        return smsHomeBrandDao.deleteByids(ids);
    }
}
