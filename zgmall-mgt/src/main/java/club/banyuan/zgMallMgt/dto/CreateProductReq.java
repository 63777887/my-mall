package club.banyuan.zgMallMgt.dto;

import club.banyuan.zgMallMgt.dao.entity.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CreateProductReq implements Serializable {
    /**
     * albumPics :
     * brandId : 51
     * brandName : 苹果
     * deleteStatus : 0
     * description : 全新的苹果手机测试款来了，赶紧买，小心耍猴
     * detailDesc : 买买买，买就完事儿了，钱不够就卖肾
     * detailHtml : <p>苹果手机买起来！苹果手机买起来！苹果手机买起来！苹果手机买起来！</p>
     * detailMobileHtml :
     * detailTitle : 赶快买啊
     * feightTemplateId : 0
     * flashPromotionCount : 0
     * flashPromotionId : 0
     * flashPromotionPrice : 0
     * flashPromotionSort : 0
     * giftPoint : 0
     * giftGrowth : 0
     * keywords : 苹果手机，卖肾
     * lowStock : 0
     * name : 苹果手机测试款
     * newStatus : 1
     * note : 不买多个肾，买了喝肾宝
     * originalPrice : 9999
     * pic : http://minio.banyuan.club/dev/20200425/icon1.jpg
     * memberPriceList : [{"memberLevelId":1,"memberLevelName":"黄金会员","memberPrice":"7777"},{"memberLevelId":2,"memberLevelName":"白金会员","memberPrice":"6666"},{"memberLevelId":3,"memberLevelName":"钻石会员","memberPrice":"5555"}]
     * productFullReductionList : [{"fullPrice":0,"reducePrice":0}]
     * productLadderList : [{"count":0,"discount":0,"price":0}]
     * previewStatus : 1
     * price : 8888
     * productAttributeCategoryId : 3
     * productAttributeValueList : [{"productAttributeId":43,"value":"黑,白"},{"productAttributeId":45,"value":"140*80"},{"productAttributeId":46,"value":"4G"},{"productAttributeId":47,"value":"IOS"},{"productAttributeId":48,"value":"3000mA"}]
     * skuStockList : [{"spData":"[{\"key\":\"颜色\",\"value\":\"黑\"},{\"key\":\"容量\",\"value\":\"128G\"}]","pic":"http://minio.banyuan.club/dev/20200425/haiyang1.jpg"},{"spData":"[{\"key\":\"颜色\",\"value\":\"白\"},{\"key\":\"容量\",\"value\":\"128G\"}]","pic":"http://minio.banyuan.club/dev/20200425/yinfu.jpg"}]
     * subjectProductRelationList : [{"subjectId":2},{"subjectId":6}]
     * prefrenceAreaProductRelationList : [{"prefrenceAreaId":1},{"prefrenceAreaId":2},{"prefrenceAreaId":4}]
     * productCategoryId : 19
     * productCategoryName : 手机通讯
     * productSn : 3838438
     * promotionEndTime :
     * promotionPerLimit : 0
     * promotionPrice : null
     * promotionStartTime :
     * promotionType : 2
     * publishStatus : 1
     * recommandStatus : 0
     * sale : 0
     * serviceIds : 1,3,2
     * sort : 0
     * stock : 99
     * subTitle : 苹果手机测试款来了
     * unit : 元
     * usePointLimit : 0
     * verifyStatus : 0
     * weight : 300
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
