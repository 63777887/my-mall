package club.banyuan.zgMallMgt.service.imp;

import club.banyuan.zgMallMgt.common.ReqFailException;
import club.banyuan.zgMallMgt.common.ResponsePage;
import club.banyuan.zgMallMgt.dao.PmsProductAttributeCategoryDao;
import club.banyuan.zgMallMgt.dao.PmsProductAttributeDao;
import club.banyuan.zgMallMgt.dao.entity.PmsProductAttribute;
import club.banyuan.zgMallMgt.dao.entity.PmsProductAttributeCategory;
import club.banyuan.zgMallMgt.dto.PmsProductAttributeCategoryResp;
import club.banyuan.zgMallMgt.dto.PmsProductAttributeCategoryTreeNode;
import club.banyuan.zgMallMgt.service.PmsProductAttributeCategoryService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static club.banyuan.zgMallMgt.common.FailReason.PMS_PRODUCT_ATTRIBUTE_CATEGORY_NAME_DUPLICATE;
import static club.banyuan.zgMallMgt.common.FailReason.PMS_PRODUCT_ATTRIBUTE_CATEGORY_NOT_EXIST;

@Service
public class PmsProductAttributeCategoryServiceImpl implements PmsProductAttributeCategoryService {

    @Autowired
    private PmsProductAttributeCategoryDao pmsProductAttributeCategoryDao;
    @Autowired
    private PmsProductAttributeDao pmsProductAttributeDao;

    @Override
    public ResponsePage list(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<PmsProductAttributeCategory> pmsProductAttributeCategories = pmsProductAttributeCategoryDao.selectAll();
        PageInfo<PmsProductAttributeCategory> pageInfo = new PageInfo<>(pmsProductAttributeCategories);
        List<PmsProductAttributeCategoryResp> collect = pmsProductAttributeCategories.stream().map(t -> {
            PmsProductAttributeCategoryResp pmsProductAttributeCategoryResp = new PmsProductAttributeCategoryResp();
            BeanUtil.copyProperties(t, pmsProductAttributeCategoryResp);
            return pmsProductAttributeCategoryResp;
        }).collect(Collectors.toList());
        return ResponsePage.setPages(pageInfo,collect);
    }

    @Override
    public List<PmsProductAttributeCategoryTreeNode> listWithAttr() {
        List<PmsProductAttributeCategory> pmsProductAttributeCategories = pmsProductAttributeCategoryDao.selectAll();
        List<PmsProductAttributeCategoryTreeNode> treeNodeList=new ArrayList<>();
        pmsProductAttributeCategories.forEach(t->{
            List<PmsProductAttribute> pmsProductAttributes =
                    pmsProductAttributeDao.selectByProductAttributeCategoryId(t.getId());
            PmsProductAttributeCategoryTreeNode pmsProductAttributeCategoryTreeNode = new PmsProductAttributeCategoryTreeNode();
            BeanUtil.copyProperties(t, pmsProductAttributeCategoryTreeNode);
            pmsProductAttributeCategoryTreeNode.setProductAttributeList(pmsProductAttributes);
            treeNodeList.add(pmsProductAttributeCategoryTreeNode);
        });
        return treeNodeList;
    }

    @Override
    public Integer create(String name) {
        List<PmsProductAttributeCategory> pmsProductAttributeCategories = pmsProductAttributeCategoryDao.selectAll();
        pmsProductAttributeCategories.forEach(t->{
            if (t.getName().equals(name)){
                throw new ReqFailException(PMS_PRODUCT_ATTRIBUTE_CATEGORY_NAME_DUPLICATE);
            }
        });
        PmsProductAttributeCategory pmsProductAttributeCategory = new PmsProductAttributeCategory();
        pmsProductAttributeCategory.setName(name);
        return pmsProductAttributeCategoryDao.insert(pmsProductAttributeCategory);
    }

    @Override
    public Integer update(String name, Long id) {
        PmsProductAttributeCategory pmsProductAttributeCategory = pmsProductAttributeCategoryDao.selectByPrimaryKey(id);
        if (BeanUtil.isEmpty(pmsProductAttributeCategory)){
            throw new ReqFailException(PMS_PRODUCT_ATTRIBUTE_CATEGORY_NOT_EXIST);
        }
        pmsProductAttributeCategory.setName(name);
        return pmsProductAttributeCategoryDao.updateByPrimaryKey(pmsProductAttributeCategory);
    }

    @Override
    public Integer delete(Long id) {
        PmsProductAttributeCategory pmsProductAttributeCategory = pmsProductAttributeCategoryDao.selectByPrimaryKey(id);
        if (BeanUtil.isEmpty(pmsProductAttributeCategory)){
            throw new ReqFailException(PMS_PRODUCT_ATTRIBUTE_CATEGORY_NOT_EXIST);
        }
        return pmsProductAttributeCategoryDao.deleteByPrimaryKey(id);
    }
}
