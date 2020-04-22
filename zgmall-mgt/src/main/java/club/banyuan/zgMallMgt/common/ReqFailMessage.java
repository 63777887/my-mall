package club.banyuan.zgMallMgt.common;

public interface   ReqFailMessage {
    String LOGIN_FAIL = "用户名或密码错误";
    String USER_NOT_EXIST = "用户不存在";
    String ROLE_IS_NULL = "角色列表为空";
    String BAD_TOKEN = "token不存在或已过期";
}
