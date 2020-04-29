package club.banyuan.zgMallMgt.service.imp;

import club.banyuan.zgMallMgt.common.ReqFailException;
import club.banyuan.zgMallMgt.common.ResponsePage;
import club.banyuan.zgMallMgt.dao.PmsBrandDao;
import club.banyuan.zgMallMgt.dao.entity.PmsBrand;
import club.banyuan.zgMallMgt.dao.entity.PmsBrandExample;
import club.banyuan.zgMallMgt.dto.PmsBrandResp;
import club.banyuan.zgMallMgt.service.OssFileService;
import club.banyuan.zgMallMgt.service.PmsBrandService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static club.banyuan.zgMallMgt.common.FailReason.*;

@Service
public class PmsBrandServiceImpl implements PmsBrandService {

    @Autowired
    private PmsBrandDao pmsBrandDao;
    @Autowired
    private OssFileService ossFileService;

    @Override
    public ResponsePage list(Integer pageNum, Integer pageSize, String keyword) {
        PageHelper.startPage(pageNum, pageSize);
        PmsBrandExample pmsBrandExample = new PmsBrandExample();
        PmsBrandExample.Criteria criteria = pmsBrandExample.createCriteria();
        if (StrUtil.isNotBlank(keyword)) {
            criteria.andNameLike(StrUtil.concat(true, "%", keyword, "%"));
        }
//        List<PmsBrand> pmsBrands = pmsBrandDao.selectAll();
        List<PmsBrand> pmsBrands = pmsBrandDao.selectByExample(pmsBrandExample);
        PageInfo<PmsBrand> pageInfo = new PageInfo<>(pmsBrands);
        List<PmsBrandResp> collect = pmsBrands.stream().map(t -> {
            PmsBrandResp pmsBrandResp = new PmsBrandResp();
            BeanUtil.copyProperties(t, pmsBrandResp);
            return pmsBrandResp;
        }).collect(Collectors.toList());
        return ResponsePage.setPages(pageInfo, collect);

    }

    @Override
    public Integer create(PmsBrand pmsBrand) {
        if (BeanUtil.isEmpty(pmsBrand)) {
            throw new ReqFailException(PMS_BRAND_EMPTY);
        }
        List<PmsBrand> pmsBrands = pmsBrandDao.selectAll();
        pmsBrands.forEach(t -> {
            if (t.getName().equals(pmsBrand.getName())) {
                throw new ReqFailException(PMS_BRAND_DUPLICATE);
            }
        });
        return pmsBrandDao.insert(pmsBrand);
    }

    @Override
    public PmsBrandResp pmsBrandInfo(Long brandId) {
        PmsBrand pmsBrand = pmsBrandDao.selectByPrimaryKey(brandId);
        if (BeanUtil.isEmpty(pmsBrand)) {
            throw new ReqFailException(PMS_BRAND_NOT_EXIST);
        }
        PmsBrandResp pmsBrandResp = new PmsBrandResp();
        BeanUtil.copyProperties(pmsBrand, pmsBrandResp);
        return pmsBrandResp;
    }

    @Override
    public Integer update(Long brandId, PmsBrand pmsBrand) {
        if (BeanUtil.isEmpty(pmsBrand)) {
            throw new ReqFailException(PMS_BRAND_EMPTY);
        }
        PmsBrand pmsBrand1 = pmsBrandDao.selectByPrimaryKey(brandId);
        if (BeanUtil.isEmpty(pmsBrand1)) {
            throw new ReqFailException(PMS_BRAND_NOT_EXIST);
        }
        pmsBrand.setId(brandId);
        int i = pmsBrandDao.updateByPrimaryKey(pmsBrand);
        String oldLogo = pmsBrand1.getLogo();
        String newLogo = pmsBrand.getLogo();
        deleteByDifferentUrl(oldLogo, newLogo);
        String oldBigPic = pmsBrand1.getBigPic();
        String newBigPic = pmsBrand.getBigPic();
        deleteByDifferentUrl(oldBigPic, newBigPic);
        return i;
    }

    private void deleteByDifferentUrl(String oldLogo, String newLogo) {
        if (StrUtil.isNotBlank(oldLogo)) {
            if (!oldLogo.equals(newLogo)) {
                try {
                    ossFileService.delete(oldLogo);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public Integer showStatus(List<Long> brandIds, Integer showStatus) {
        return pmsBrandDao.updateShowStatusByBrandIds(brandIds, showStatus);
    }

    @Override
    public Integer factoryStatus(List<Long> brandIds, Integer factoryStatus) {
        return pmsBrandDao.updateFactoryStatusByBrandIds(brandIds, factoryStatus);
    }

    @Override
    public Integer delete(Long brandId) throws IOException {
        PmsBrand pmsBrand = pmsBrandDao.selectByPrimaryKey(brandId);
        if (BeanUtil.isEmpty(pmsBrand)) {
            throw new ReqFailException(PMS_BRAND_NOT_EXIST);
        }
        String bigPic = pmsBrand.getBigPic();
        String logo = pmsBrand.getLogo();
        int i = pmsBrandDao.deleteByPrimaryKey(brandId);
        if (StrUtil.isNotBlank(bigPic)) {
           ossFileService.delete(bigPic);
        }
        if (StrUtil.isNotBlank(logo)) {
            ossFileService.delete(logo);
        }
        return i;
    }
}
