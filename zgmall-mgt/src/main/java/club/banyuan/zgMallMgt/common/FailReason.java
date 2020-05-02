package club.banyuan.zgMallMgt.common;

public enum  FailReason {
    UMS_ADMIN_USER_NOT_VALID("用户名或密码错误"),
    UMS_ADMIN_USER_NOT_EXIST("用户不存在"),
    UMS_ADMIN_NAME_DUPLICATE("角色名冲突"),
    UMS_ADMIN_BAD_TOKEN("token不存在或已过期"),
    UMS_ADMIN_ROLE_EMPTY("角色列表为空"),
    UMS_ADMIN_ROLE_NOT_EXIST("角色不存在"),
    UMS_ROLE_NAME_DUPLICATE("角色名冲突"),
    UMS_ADMIN_MENU_NOT_EXIST("菜单不存在"),
    UMS_ROLE_MENU_DUPLICATE("菜单名或前端名称冲突"),
    UMS_ROLE_MENU_REL_ILLEGAL("角色菜单关联关系不合法"),
    UMS_ADMIN_RESOURCE_EMPTY("资源列表为空"),
    UMS_ADMIN_RESOURCE_NOT_EXIST("资源不存在"),
    UMS_RESOURCE_NAME_DUPLICATE("资源名或URL冲突"),
    UMS_RESOURCE_CATEGORY_NOT_EXIST("资源分类不存在"),
    UMS_RESOURCE_CATEGORY_NAME_DUPLICATE("资源分类名冲突"),
    OSS_UPLOAD_FAIL("上传失败"),
    PMS_PRODUCT_CATEGORY_NOT_EXIST("资源不存在"),
    PMS_PRODUCT_NOT_EXIST("商品不存在"),
    PMS_PRODUCT_ATTRIBUTE_CATEGORY_NAME_DUPLICATE("资源名或URL冲突"),
    PMS_PRODUCT_ATTRIBUTE_CATEGORY_NOT_EXIST("商品属性分类不存在"),
    PMS_PRODUCT_ATTRIBUTE_EMPTY("商品属性为空"),
    PMS_PRODUCT_ATTRIBUTE_NOT_EXIST("商品属性分类不存在"),
    PMS_BRAND_EMPTY("品牌为空"),
    PMS_BRAND_NOT_EXIST("品牌不存在"),
    PMS_BRAND_DUPLICATE("品牌名冲突"),
    OMS_ORDER_NOT_EXIST("订单不存在"),
    OMS_ORDER_SETTING_NOT_EXIST("订单设置不存在"),
    OMS_ORDER_RETURN_APPLY_NOT_EXIST("退货申请不存在"),
    OMS_ORDER_RETURN_REASON_NOT_EXIST("退货申请不存在"),
    SMS_HOME_ADVERTISE_NOT_EXIST("广告不存在"),
    SMS_HOME_RECOMMEND_SUBJECT_NOT_EXIST("专题不存在"),
    SMS_HOME_RECOMMEND_PRODUCTT_NOT_EXIST("推荐商品不存在"),
    SMS_HOME_NEW_PRODUCT_NOT_EXIST("新鲜好物不存在"),
    SMS_HOME_BRAND_NOT_EXIST("新鲜好物不存在"),
    SMS_COUPON_NOT_EXIST("优惠券不存在"),
    SMS_FLASH_PROMOTION_SESSION_NOT_EXIST("限时购场次不存在"),
    SMS_FLASH_PROMOTION_PRODUCT_RELATION_NOT_EXIST("商品限时购与商品关系表不存在"),
    SMS_FLASH_PROMOTION_NOT_EXIST("限时购不存在");

    private final String message;

    FailReason(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
