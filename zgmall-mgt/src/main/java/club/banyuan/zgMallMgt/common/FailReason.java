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
    UMS_RESOURCE_CATEGORY_NAME_DUPLICATE("资源分类名冲突");

    private final String message;

    FailReason(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
