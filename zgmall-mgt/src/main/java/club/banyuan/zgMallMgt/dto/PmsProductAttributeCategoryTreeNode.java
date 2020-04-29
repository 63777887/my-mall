package club.banyuan.zgMallMgt.dto;

import club.banyuan.zgMallMgt.dao.entity.PmsProductAttribute;

import java.util.List;

public class PmsProductAttributeCategoryTreeNode {


    /**
     * id : 1
     * name : 服装-T恤
     * attributeCount : null
     * paramCount : null
     * productAttributeList : [{"id":24,"productAttributeCategoryId":null,"name":"商品编号","selectType":null,"inputType":null,"inputList":null,"sort":null,"filterType":null,"searchType":null,"relatedStatus":null,"handAddStatus":null,"type":null}]
     */


    private Long id;

    private String name;

    /**
     * 属性数量
     */
    private Integer attributeCount;

    /**
     * 参数数量
     */
    private Integer paramCount;
    private List<PmsProductAttribute> productAttributeList;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAttributeCount() {
        return attributeCount;
    }

    public void setAttributeCount(Integer attributeCount) {
        this.attributeCount = attributeCount;
    }

    public Integer getParamCount() {
        return paramCount;
    }

    public void setParamCount(Integer paramCount) {
        this.paramCount = paramCount;
    }

    public List<PmsProductAttribute> getProductAttributeList() {
        return productAttributeList;
    }

    public void setProductAttributeList(List<PmsProductAttribute> productAttributeList) {
        this.productAttributeList = productAttributeList;
    }
}
