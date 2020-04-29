package club.banyuan.zgMallMgt.dto;

import club.banyuan.zgMallMgt.dao.entity.PmsProductCategory;

import java.util.List;

public class PmsProductCategoryWithListResp {

    /**
     * id : 1
     * parentId : null
     * name : 服装
     * level : null
     * productCount : null
     * productUnit : null
     * navStatus : null
     * showStatus : null
     * sort : null
     * icon : null
     * keywords : null
     * description : null
     * children : [{"id":7,"parentId":null,"name":"外套","level":null,"productCount":null,"productUnit":null,"navStatus":null,"showStatus":null,"sort":null,"icon":null,"keywords":null,"description":null}]
     */

    private Long id;

    /**
     * 上机分类的编号：0表示一级分类
     */
    private Long parentId;

    private String name;

    /**
     * 分类级别：0->1级；1->2级
     */
    private Integer level;

    private Integer productCount;

    private String productUnit;

    /**
     * 是否显示在导航栏：0->不显示；1->显示
     */
    private Integer navStatus;

    /**
     * 显示状态：0->不显示；1->显示
     */
    private Integer showStatus;

    private Integer sort;

    /**
     * 图标
     */
    private String icon;

    private String keywords;

    /**
     * 描述
     */
    private String description;

    private List<PmsProductCategoryWithListResp>  children;
    private List productAttributeIdList;

    public List getProductAttributeIdList() {
        return productAttributeIdList;
    }

    public void setProductAttributeIdList(List productAttributeIdList) {
        this.productAttributeIdList = productAttributeIdList;
    }
    public PmsProductCategory findPmsProductCategory(){
        PmsProductCategory pmsProductCategory = new PmsProductCategory();
        pmsProductCategory.setId(id);
        pmsProductCategory.setDescription(description);
        pmsProductCategory.setIcon(icon);
        pmsProductCategory.setKeywords(keywords);
        pmsProductCategory.setLevel(level);
        pmsProductCategory.setName(name);
        pmsProductCategory.setNavStatus(navStatus);
        pmsProductCategory.setParentId(parentId);
        pmsProductCategory.setProductCount(productCount);
        pmsProductCategory.setProductUnit(productUnit);
        pmsProductCategory.setShowStatus(showStatus);
        pmsProductCategory.setSort(sort);
        return pmsProductCategory;
    }

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public Integer getNavStatus() {
        return navStatus;
    }

    public void setNavStatus(Integer navStatus) {
        this.navStatus = navStatus;
    }

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public List<PmsProductCategoryWithListResp> getChildren() {
        return children;
    }

    public void setChildren(List<PmsProductCategoryWithListResp> children) {
        this.children = children;
    }
}
