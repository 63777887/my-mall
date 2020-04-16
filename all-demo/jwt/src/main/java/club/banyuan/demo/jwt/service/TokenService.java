package club.banyuan.demo.jwt.service;


import io.jsonwebtoken.Claims;

import java.util.Map;

public interface TokenService {

    String generateToken(String subject);

    String generateToken(Map<String, Object> claims);

    String parseSubject(String token);

    long getExpireSec(String token);

    boolean isExpire(String token);

    boolean validateToken(String token, String username);

    String refreshToken(String token);

    Map<String,Object> parseMap(String token);
}
