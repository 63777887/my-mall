package club.banyuan.zgMallMgt.dto;

import club.banyuan.zgMallMgt.dao.entity.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class PmsProductInfoResp {

    /**
     * id : 26
     * brandId : 3
     * productCategoryId : 19
     * feightTemplateId : 0
     * productAttributeCategoryId : 3
     * name : 华为 HUAWEI P20
     * pic : http://minio.banyuan.club/dev/preset/5ac1bf58Ndefaac16.jpg
     * productSn : 6946605
     * deleteStatus : 0
     * publishStatus : 1
     * newStatus : 1
     * recommandStatus : 1
     * verifyStatus : 0
     * sort : 100
     * sale : 0
     * price : 3788
     * promotionPrice : null
     * giftGrowth : 3788
     * giftPoint : 3788
     * usePointLimit : 0
     * subTitle : AI智慧全面屏 6GB +64GB 亮黑色 全网通版 移动联通电信4G手机 双卡双待手机 双卡双待
     * originalPrice : 4288
     * stock : 1000
     * lowStock : 0
     * unit : 件
     * weight : 0
     * previewStatus : 1
     * serviceIds : 2,3,1
     * keywords :
     * note :
     * albumPics : http://minio.banyuan.club/dev/preset/5ab46a3cN616bdc41.jpg,http://minio.banyuan.club/dev/preset/5ac1bf5fN2522b9dc.jpg
     * detailTitle :
     * promotionStartTime : null
     * promotionEndTime : null
     * promotionPerLimit : 0
     * promotionType : 1
     * brandName : 华为
     * productCategoryName : 手机通讯
     * description :
     * detailDesc :
     * detailHtml : <p><img class="wscnph" src="http://minio.banyuan.club/dev/preset/5ad44f1cNf51f3bb0.jpg" /><img class="wscnph" src="http://minio.banyuan.club/dev/preset/5ad44fa8Nfcf71c10.jpg" /><img class="wscnph" src="http://minio.banyuan.club/dev/preset/5ad44fa9N40e78ee0.jpg" /><img class="wscnph" src="http://minio.banyuan.club/dev/preset/5ad457f4N1c94bdda.jpg" /><img class="wscnph" src="http://minio.banyuan.club/dev/preset/5ad457f5Nd30de41d.jpg" /><img class="wscnph" src="http://minio.banyuan.club/dev/preset/5b10fb0eN0eb053fb.jpg" /></p>
     * detailMobileHtml :
     * productLadderList : [{"id":68,"productId":26,"count":0,"discount":0,"price":0}]
     * productFullReductionList : [{"id":63,"productId":26,"fullPrice":0,"reducePrice":0}]
     * memberPriceList : [{"id":201,"productId":26,"memberLevelId":1,"memberPrice":null,"memberLevelName":"黄金会员"}]
     * skuStockList : [{"id":110,"productId":26,"skuCode":"201806070026001","price":3788,"stock":499,"lowStock":null,"pic":null,"sale":null,"promotionPrice":null,"lockStock":null,"spData":"[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"16G\"}]"}]
     * productAttributeValueList : [{"id":228,"productId":26,"productAttributeId":43,"value":"金色,银色"}]
     * subjectProductRelationList : [{"id":41,"subjectId":2,"productId":26}]
     * prefrenceAreaProductRelationList : []
     * cateParentId : 2
     */

    private String albumPics;
    private Long brandId;
    private String brandName;
    private int deleteStatus;
    private String description;
    private String detailDesc;
    private String detailHtml;
    private String detailMobileHtml;
    private String detailTitle;
    private Long feightTemplateId;
    private int flashPromotionCount;
    private Long flashPromotionId;
    private BigDecimal flashPromotionPrice;
    private Integer flashPromotionSort;
    private int giftPoint;
    private int giftGrowth;
    private String keywords;
    private int lowStock;
    private String name;
    private int newStatus;
    private String note;
    private Long originalPrice;
    private String pic;
    private int previewStatus;
    private BigDecimal price;
    private Long productAttributeCategoryId;
    private Long productCategoryId;
    private String productCategoryName;
    private String productSn;
    private Date promotionEndTime;
    private int promotionPerLimit;
    private BigDecimal promotionPrice;
    private Date promotionStartTime;
    private int promotionType;
    private int publishStatus;
    private int recommandStatus;
    private int sale;
    private String serviceIds;
    private int sort;
    private Integer stock;
    private String subTitle;
    private String unit;
    private int usePointLimit;
    private int verifyStatus;
    private BigDecimal weight;
    private List<PmsMemberPrice> memberPriceList;
    private List<PmsProductFullReduction> productFullReductionList;
    private List<PmsProductLadder> productLadderList;
    private List<PmsProductAttributeValue> productAttributeValueList;
    private List<PmsSkuStock> skuStockList;
    private List<CmsSubjectProductRelation> subjectProductRelationList;
    private List<CmsPrefrenceAreaProductRelation> prefrenceAreaProductRelationList;

    public String getAlbumPics() {
        return albumPics;
    }

    public void setAlbumPics(String albumPics) {
        this.albumPics = albumPics;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public int getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(int deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetailDesc() {
        return detailDesc;
    }

    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc;
    }

    public String getDetailHtml() {
        return detailHtml;
    }

    public void setDetailHtml(String detailHtml) {
        this.detailHtml = detailHtml;
    }

    public String getDetailMobileHtml() {
        return detailMobileHtml;
    }

    public void setDetailMobileHtml(String detailMobileHtml) {
        this.detailMobileHtml = detailMobileHtml;
    }

    public String getDetailTitle() {
        return detailTitle;
    }

    public void setDetailTitle(String detailTitle) {
        this.detailTitle = detailTitle;
    }

    public Long getFeightTemplateId() {
        return feightTemplateId;
    }

    public void setFeightTemplateId(Long feightTemplateId) {
        this.feightTemplateId = feightTemplateId;
    }

    public int getFlashPromotionCount() {
        return flashPromotionCount;
    }

    public void setFlashPromotionCount(int flashPromotionCount) {
        this.flashPromotionCount = flashPromotionCount;
    }

    public Long getFlashPromotionId() {
        return flashPromotionId;
    }

    public void setFlashPromotionId(Long flashPromotionId) {
        this.flashPromotionId = flashPromotionId;
    }

    public BigDecimal getFlashPromotionPrice() {
        return flashPromotionPrice;
    }

    public void setFlashPromotionPrice(BigDecimal flashPromotionPrice) {
        this.flashPromotionPrice = flashPromotionPrice;
    }

    public Integer getFlashPromotionSort() {
        return flashPromotionSort;
    }

    public void setFlashPromotionSort(Integer flashPromotionSort) {
        this.flashPromotionSort = flashPromotionSort;
    }

    public int getGiftPoint() {
        return giftPoint;
    }

    public void setGiftPoint(int giftPoint) {
        this.giftPoint = giftPoint;
    }

    public int getGiftGrowth() {
        return giftGrowth;
    }

    public void setGiftGrowth(int giftGrowth) {
        this.giftGrowth = giftGrowth;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public int getLowStock() {
        return lowStock;
    }

    public void setLowStock(int lowStock) {
        this.lowStock = lowStock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(int newStatus) {
        this.newStatus = newStatus;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Long originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getPreviewStatus() {
        return previewStatus;
    }

    public void setPreviewStatus(int previewStatus) {
        this.previewStatus = previewStatus;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getProductAttributeCategoryId() {
        return productAttributeCategoryId;
    }

    public void setProductAttributeCategoryId(Long productAttributeCategoryId) {
        this.productAttributeCategoryId = productAttributeCategoryId;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public String getProductSn() {
        return productSn;
    }

    public void setProductSn(String productSn) {
        this.productSn = productSn;
    }

    public Date getPromotionEndTime() {
        return promotionEndTime;
    }

    public void setPromotionEndTime(Date promotionEndTime) {
        this.promotionEndTime = promotionEndTime;
    }

    public int getPromotionPerLimit() {
        return promotionPerLimit;
    }

    public void setPromotionPerLimit(int promotionPerLimit) {
        this.promotionPerLimit = promotionPerLimit;
    }

    public Object getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(BigDecimal promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public Date getPromotionStartTime() {
        return promotionStartTime;
    }

    public void setPromotionStartTime(Date promotionStartTime) {
        this.promotionStartTime = promotionStartTime;
    }

    public int getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(int promotionType) {
        this.promotionType = promotionType;
    }

    public int getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(int publishStatus) {
        this.publishStatus = publishStatus;
    }

    public int getRecommandStatus() {
        return recommandStatus;
    }

    public void setRecommandStatus(int recommandStatus) {
        this.recommandStatus = recommandStatus;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public String getServiceIds() {
        return serviceIds;
    }

    public void setServiceIds(String serviceIds) {
        this.serviceIds = serviceIds;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getUsePointLimit() {
        return usePointLimit;
    }

    public void setUsePointLimit(int usePointLimit) {
        this.usePointLimit = usePointLimit;
    }

    public int getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(int verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }


    public PmsProduct findPmsProduct() {
        PmsProduct pmsProduct = new PmsProduct();
        pmsProduct.setAlbumPics(albumPics);
        pmsProduct.setBrandId(brandId);
        pmsProduct.setBrandName(brandName);
        pmsProduct.setDeleteStatus(deleteStatus);
        pmsProduct.setDescription(description);
        pmsProduct.setDetailDesc(detailDesc);
        pmsProduct.setDetailHtml(detailHtml);
        pmsProduct.setDetailMobileHtml(detailMobileHtml);
        pmsProduct.setDetailTitle(detailTitle);
        pmsProduct.setFeightTemplateId(feightTemplateId);
        pmsProduct.setGiftGrowth(giftGrowth);
        pmsProduct.setGiftPoint(giftPoint);
        pmsProduct.setKeywords(keywords);
        pmsProduct.setLowStock(lowStock);
        pmsProduct.setName(name);
        pmsProduct.setNewStatus(newStatus);
        pmsProduct.setNote(note);
        pmsProduct.setOriginalPrice(originalPrice);
        pmsProduct.setPic(pic);
        pmsProduct.setPreviewStatus(previewStatus);
        pmsProduct.setPrice(price);
        pmsProduct.setProductAttributeCategoryId(productAttributeCategoryId);
        pmsProduct.setProductCategoryId(productCategoryId);
        pmsProduct.setProductCategoryName(productCategoryName);
        pmsProduct.setProductSn(productSn);
        pmsProduct.setPromotionEndTime(promotionEndTime);
        pmsProduct.setPromotionPerLimit(promotionPerLimit);
        pmsProduct.setPromotionPrice(promotionPrice);
        pmsProduct.setPromotionStartTime(promotionStartTime);
        pmsProduct.setPromotionType(promotionType);
        pmsProduct.setPublishStatus(publishStatus);
        pmsProduct.setRecommandStatus(recommandStatus);
        pmsProduct.setSale(sale);
        pmsProduct.setServiceIds(serviceIds);
        pmsProduct.setSort(sort);
        pmsProduct.setStock(stock);
        pmsProduct.setSubTitle(subTitle);
        pmsProduct.setUnit(unit);
        pmsProduct.setUsePointLimit(usePointLimit);
        pmsProduct.setVerifyStatus(verifyStatus);
        pmsProduct.setWeight(weight);
        return pmsProduct;
    }
    public void setPmsProduct(PmsProduct pmsProduct) {
        this.albumPics=pmsProduct.getAlbumPics();
        this.brandId=pmsProduct.getBrandId();
        this.brandName=pmsProduct.getBrandName();
        this.deleteStatus=pmsProduct.getDeleteStatus();
        this.description=pmsProduct.getDescription();
        this.detailDesc=pmsProduct.getDetailDesc();
        this.detailHtml=pmsProduct.getDetailHtml();
        this.detailMobileHtml=pmsProduct.getDetailMobileHtml();
        this.detailTitle=pmsProduct.getDetailTitle();
        this.feightTemplateId=pmsProduct.getFeightTemplateId();
        this.giftPoint=pmsProduct.getGiftPoint();
        this.giftGrowth=pmsProduct.getGiftGrowth();
        this.keywords=pmsProduct.getKeywords();
        this.lowStock=pmsProduct.getLowStock();
        this.name=pmsProduct.getName();
        this.newStatus=pmsProduct.getNewStatus();
        this.note=pmsProduct.getNote();
        this.originalPrice=pmsProduct.getOriginalPrice();
        this.pic=pmsProduct.getPic();
        this.previewStatus=pmsProduct.getPreviewStatus();
        this.price=pmsProduct.getPrice();
        this.productAttributeCategoryId=pmsProduct.getProductAttributeCategoryId();
        this.productCategoryId=pmsProduct.getProductCategoryId();
        this.productCategoryName=pmsProduct.getProductCategoryName();
        this.productSn=pmsProduct.getProductSn();
        this.promotionEndTime=pmsProduct.getPromotionEndTime();
        this.promotionPerLimit=pmsProduct.getPromotionPerLimit();
        this.promotionPrice=pmsProduct.getPromotionPrice();
        this.promotionStartTime=pmsProduct.getPromotionStartTime();
        this.promotionType=pmsProduct.getPromotionType();
        this.publishStatus=pmsProduct.getPublishStatus();
        this.recommandStatus=pmsProduct.getRecommandStatus();
        this.sale=pmsProduct.getSale();
        this.serviceIds=pmsProduct.getServiceIds();
        this.sort=pmsProduct.getSort();
        this.stock=pmsProduct.getStock();
        this.subTitle=pmsProduct.getSubTitle();
        this.unit=pmsProduct.getUnit();
        this.usePointLimit=pmsProduct.getUsePointLimit();
        this.verifyStatus=pmsProduct.getVerifyStatus();
        this.weight=pmsProduct.getWeight();
    }





    public SmsFlashPromotionProductRelation findSmsFlashPromotionProductRelation() {
        SmsFlashPromotionProductRelation smsFlashPromotionProductRelation = new SmsFlashPromotionProductRelation();
        smsFlashPromotionProductRelation.setFlashPromotionCount(flashPromotionCount);
        smsFlashPromotionProductRelation.setFlashPromotionId(feightTemplateId);
        smsFlashPromotionProductRelation.setFlashPromotionPrice(flashPromotionPrice);
        smsFlashPromotionProductRelation.setSort(flashPromotionSort);
        return smsFlashPromotionProductRelation;
    }
    public void setSmsFlashPromotionProductRelation(SmsFlashPromotionProductRelation smsFlashPromotionProductRelation) {
        this.flashPromotionCount=smsFlashPromotionProductRelation.getFlashPromotionCount();
        this.flashPromotionId=smsFlashPromotionProductRelation.getFlashPromotionId();
        this.flashPromotionPrice=smsFlashPromotionProductRelation.getFlashPromotionPrice();
        this.flashPromotionSort=smsFlashPromotionProductRelation.getSort();
    }


    public List<PmsMemberPrice> getMemberPriceList() {
        return memberPriceList;
    }
    public void setMemberPriceList(List<PmsMemberPrice> pmsMemberPrices) {
        this.memberPriceList=pmsMemberPrices;
    }



    public List<PmsProductFullReduction> getProductFullReductionList() {
        return productFullReductionList;
    }
    public void setProductFullReductionList(List<PmsProductFullReduction> pmsProductFullReductions) {
        this.productFullReductionList=pmsProductFullReductions;
    }



    public List<PmsProductLadder> getProductLadderList() {
        return productLadderList;
    }
    public void setProductLadderList(List<PmsProductLadder> pmsProductLadders) {
        this.productLadderList=pmsProductLadders;
    }



    public List<PmsProductAttributeValue> getProductAttributeValueList() {
        return productAttributeValueList;
    }
    public void setProductAttributeValueList(List<PmsProductAttributeValue> pmsProductAttributeValues) {
        this.productAttributeValueList=pmsProductAttributeValues;
    }



    public void setSkuStockList(List<PmsSkuStock> skuStockList) {
        this.skuStockList = skuStockList;
    }
    public List<PmsSkuStock> getSkuStockList() {
        return skuStockList;
    }


    public List<CmsSubjectProductRelation> getSubjectProductRelationList() {
        return subjectProductRelationList;
    }
    public void setSubjectProductRelationList(List<CmsSubjectProductRelation> cmsSubjectProductRelations) {
        this.subjectProductRelationList=cmsSubjectProductRelations;
    }


    public List<CmsPrefrenceAreaProductRelation> getPrefrenceAreaProductRelationList() {
        return prefrenceAreaProductRelationList;
    }
    public void setPrefrenceAreaProductRelationList(List<CmsPrefrenceAreaProductRelation> cmsPrefrenceAreaProductRelations) {
        this.prefrenceAreaProductRelationList=cmsPrefrenceAreaProductRelations;
    }


}
