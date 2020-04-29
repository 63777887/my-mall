package club.banyuan.zgMallMgt.service.imp;

import club.banyuan.zgMallMgt.common.ReqFailException;
import club.banyuan.zgMallMgt.common.ResponsePage;
import club.banyuan.zgMallMgt.dao.PmsProductAttributeDao;
import club.banyuan.zgMallMgt.dao.PmsProductCategoryAttributeRelationDao;
import club.banyuan.zgMallMgt.dao.PmsProductCategoryDao;
import club.banyuan.zgMallMgt.dao.entity.PmsProductCategory;
import club.banyuan.zgMallMgt.dao.entity.PmsProductCategoryExample;
import club.banyuan.zgMallMgt.dto.PmsProductCategoryResp;
import club.banyuan.zgMallMgt.dto.PmsProductCategoryWithListResp;
import club.banyuan.zgMallMgt.service.PmsProductCategoryService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static club.banyuan.zgMallMgt.common.FailReason.PMS_PRODUCT_CATEGORY_NOT_EXIST;

@Service
public class PmsProductCategoryServiceImpl implements PmsProductCategoryService {

    @Autowired
    private PmsProductCategoryDao pmsProductCategoryDao;
    @Autowired
    private PmsProductCategoryAttributeRelationDao productCategoryAttributeRelationDao;

    @Override
    public List<PmsProductCategoryResp> listWithChildren() {
        List<PmsProductCategory> pmsProductCategories = pmsProductCategoryDao.selectAll();
        List<PmsProductCategoryResp> list=new ArrayList<>();
        pmsProductCategories.forEach(t->{
            if (t.getParentId()==0){
                PmsProductCategoryResp pmsProductCategoryResp = new PmsProductCategoryResp();
                BeanUtil.copyProperties(t, pmsProductCategoryResp);
                pmsProductCategoryResp.setChildren(new ArrayList<>());
                list.add(pmsProductCategoryResp);
            }
        });

        list.forEach(parent->{
            pmsProductCategories.forEach(child->{
                if (parent.getId().equals(child.getParentId())){
                    PmsProductCategoryResp pmsProductCategoryResp = new PmsProductCategoryResp();
                    BeanUtil.copyProperties(child, pmsProductCategoryResp);
                    parent.getChildren().add(pmsProductCategoryResp);
                }
            });
        });
        return list;
    }

    @Override
    public ResponsePage list(Integer pageNum, Integer pageSize, Long parentId) {
        PageHelper.startPage(pageNum, pageSize);
        PmsProductCategoryExample pmsProductCategoryExample = new PmsProductCategoryExample();
        PmsProductCategoryExample.Criteria criteria = pmsProductCategoryExample.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<PmsProductCategory> pmsProductCategories = pmsProductCategoryDao.selectByExample(pmsProductCategoryExample);
        PageInfo<PmsProductCategory> pageInfo = new PageInfo<>(pmsProductCategories);
        List<PmsProductCategoryResp> collect = pmsProductCategories.stream().map(t -> {
            PmsProductCategoryResp pmsProductCategoryResp = new PmsProductCategoryResp();
            BeanUtil.copyProperties(t, pmsProductCategoryResp);
            return pmsProductCategoryResp;
        }).collect(Collectors.toList());
        return ResponsePage.setPages(pageInfo, collect);
    }

    @Override
    public Integer create(PmsProductCategory pmsProductCategory) {
        return pmsProductCategoryDao.insert(pmsProductCategory);
    }

    @Override
    @Transactional
    public Integer update(PmsProductCategoryWithListResp pmsProductCategoryWithListResp, Long productCategoryId) {
        pmsProductCategoryDao.selectByPrimaryKey(productCategoryId);
        if (ObjectUtil.isEmpty(productCategoryId)){
            throw new ReqFailException(PMS_PRODUCT_CATEGORY_NOT_EXIST);
        }
        pmsProductCategoryWithListResp.setId(productCategoryId);
        PmsProductCategory pmsProductCategory = pmsProductCategoryWithListResp.findPmsProductCategory();
        pmsProductCategoryDao.updateByPrimaryKey(pmsProductCategory);
        productCategoryAttributeRelationDao.deleteByProductCategoryId(productCategoryId);
        List productAttributeIdList = pmsProductCategoryWithListResp.getProductAttributeIdList();
        return productCategoryAttributeRelationDao.insertByProductAttributeIdList(productAttributeIdList,productCategoryId);
    }

    @Override
    public Integer delete(Long productCategoryId) {
        pmsProductCategoryDao.selectByPrimaryKey(productCategoryId);
        if (ObjectUtil.isEmpty(productCategoryId)){
            throw new ReqFailException(PMS_PRODUCT_CATEGORY_NOT_EXIST);
        }
        return pmsProductCategoryDao.deleteByPrimaryKey(productCategoryId);
    }

    @Override
    public Integer updateNavStatus(Long ids, Integer navStatus) {
        pmsProductCategoryDao.selectByPrimaryKey(ids);
        if (ObjectUtil.isEmpty(ids)){
            throw new ReqFailException(PMS_PRODUCT_CATEGORY_NOT_EXIST);
        }
     return pmsProductCategoryDao.updateNavStatusById(ids,navStatus);
    }

    @Override
    public Integer updateShowStatus(Long ids, Integer showStatus) {
        pmsProductCategoryDao.selectByPrimaryKey(ids);
        if (ObjectUtil.isEmpty(ids)){
            throw new ReqFailException(PMS_PRODUCT_CATEGORY_NOT_EXIST);
        }
        return pmsProductCategoryDao.updateShowStatusById(ids,showStatus);
    }

    @Override
    public PmsProductCategoryResp showInfo(Long productCategoryId) {
        PmsProductCategory pmsProductCategory = pmsProductCategoryDao.selectByPrimaryKey(productCategoryId);
        if (ObjectUtil.isEmpty(pmsProductCategory)){
            throw new ReqFailException(PMS_PRODUCT_CATEGORY_NOT_EXIST);
        }
        PmsProductCategoryResp pmsProductCategoryResp = new PmsProductCategoryResp();
        BeanUtil.copyProperties(pmsProductCategory, pmsProductCategoryResp);
        return pmsProductCategoryResp;
    }
}
