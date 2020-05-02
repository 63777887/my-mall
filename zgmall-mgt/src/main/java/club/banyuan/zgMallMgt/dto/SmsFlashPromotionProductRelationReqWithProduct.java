package club.banyuan.zgMallMgt.dto;

import club.banyuan.zgMallMgt.dao.entity.PmsProduct;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class SmsFlashPromotionProductRelationReqWithProduct {

    /**
     * id : 21
     * flashPromotionId : 14
     * flashPromotionSessionId : 1
     * productId : 26
     * flashPromotionPrice : 1000
     * flashPromotionCount : 10
     * flashPromotionLimit : 1
     * sort : 0
     * product : {"id":26,"brandId":null,"productCategoryId":null,"feightTemplateId":null,"productAttributeCategoryId":null,"name":"华为 HUAWEI P20 ","pic":null,"productSn":"6946605","deleteStatus":null,"publishStatus":null,"newStatus":null,"recommandStatus":null,"verifyStatus":null,"sort":null,"sale":null,"price":3788,"promotionPrice":null,"giftGrowth":null,"giftPoint":null,"usePointLimit":null,"subTitle":null,"originalPrice":null,"stock":1000,"lowStock":null,"unit":null,"weight":null,"previewStatus":null,"serviceIds":null,"keywords":null,"note":null,"albumPics":null,"detailTitle":null,"promotionStartTime":null,"promotionEndTime":null,"promotionPerLimit":null,"promotionType":null,"brandName":null,"productCategoryName":null,"description":null,"detailDesc":null,"detailHtml":null,"detailMobileHtml":null}
     */

    /**
     * 编号
     */
    private Long id;

    @NotNull
    private Long flashPromotionId;

    /**
     * 编号
     */
    @NotNull
    private Long flashPromotionSessionId;
    @NotNull
    private Long productId;

    /**
     * 限时购价格
     */
    private BigDecimal flashPromotionPrice;

    /**
     * 限时购数量
     */
    private Integer flashPromotionCount;

    /**
     * 每人限购数量
     */
    private Integer flashPromotionLimit;

    /**
     * 排序
     */
    private Integer sort;
    private PmsProduct product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFlashPromotionId() {
        return flashPromotionId;
    }

    public void setFlashPromotionId(Long flashPromotionId) {
        this.flashPromotionId = flashPromotionId;
    }

    public Long getFlashPromotionSessionId() {
        return flashPromotionSessionId;
    }

    public void setFlashPromotionSessionId(Long flashPromotionSessionId) {
        this.flashPromotionSessionId = flashPromotionSessionId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getFlashPromotionPrice() {
        return flashPromotionPrice;
    }

    public void setFlashPromotionPrice(BigDecimal flashPromotionPrice) {
        this.flashPromotionPrice = flashPromotionPrice;
    }

    public Integer getFlashPromotionCount() {
        return flashPromotionCount;
    }

    public void setFlashPromotionCount(Integer flashPromotionCount) {
        this.flashPromotionCount = flashPromotionCount;
    }

    public Integer getFlashPromotionLimit() {
        return flashPromotionLimit;
    }

    public void setFlashPromotionLimit(Integer flashPromotionLimit) {
        this.flashPromotionLimit = flashPromotionLimit;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public PmsProduct getProduct() {
        return product;
    }

    public void setProduct(PmsProduct product) {
        this.product = product;
    }


}
