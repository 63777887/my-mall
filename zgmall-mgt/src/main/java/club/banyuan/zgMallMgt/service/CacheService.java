package club.banyuan.zgMallMgt.service;

public interface CacheService {

    void set(String key, Object value);

    <T> T get(String key);

    void setExpire(String key, Long sec);

    Long getExpireSec(String key);

}
