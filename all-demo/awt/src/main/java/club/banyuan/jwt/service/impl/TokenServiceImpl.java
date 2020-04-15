package club.banyuan.jwt.service.impl;

import club.banyuan.jwt.service.TokenService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class TokenServiceImpl implements TokenService {

    @Value("${token.secretKey}")
    private String SECRET_KEY;

    @Value("${token.expireSec}")
    private Long EXPIRE_SEC;


    public String generateToken(String subject){
        Jwts.builder()
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRE_SEC))
                .setAudience()
    }

}
