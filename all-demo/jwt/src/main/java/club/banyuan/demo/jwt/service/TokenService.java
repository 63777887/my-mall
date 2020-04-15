package club.banyuan.demo.jwt.service;


import java.util.Map;

public interface TokenService {

    String generateToken(String subject);

    String generateToken(Map<String, Object> claims);

    String parseSubject(String token);

    long getExpireSec(String token);

    boolean isExpire(String token);

    String refreshToken(String token);

    Map<String,Object> parseMap(String token);
}
