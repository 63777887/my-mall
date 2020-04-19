package club.banyuan.zgMallMgt.service.imp;

import club.banyuan.zgMallMgt.service.TokenService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;


@Service
public class TokenServiceImpl implements TokenService {


    @Value("${token.secretKey}")
    private String SECRET_KEY;

    @Value("${token.expireSec}")
    private long EXPIRE_SEC;

    @Value("${token.header}")
    private String JWT_HEADER_NAME;

    /**
     * 生成token令牌,加入过期时间
     *
     * @param subject 用户名
     * @return token令牌
     */
    @Override
    public String generateToken(String subject){
       return Jwts.builder()
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRE_SEC))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    @Override
    public String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRE_SEC))
                .signWith(SignatureAlgorithm.HS512,SECRET_KEY)
                .compact();
    }

    /**
     * 从令牌中获取数据声明,过期也强行获取
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims getClaimsFromToken(String token) {
        try {
            return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        }catch (ExpiredJwtException e){
            //过期也解析body
            return e.getClaims();
        }catch (UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e){
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    @Override
    public String parseSubject(String token){
        return getClaimsFromToken(token).getSubject();
    }

    /**
     * 从令牌中获取令牌剩余时间，小于等于0即是过期
     *
     * @param token 令牌
     * @return 过期剩余时间
     */
    @Override
    public long getExpireSec(String token){
        return getClaimsFromToken(token).getExpiration().getTime()-System.currentTimeMillis();
    }

    /**
     * 从令牌判断是否过期,返回true为过期
     *
     * @param token 令牌
     * @return 是否过期
     */
    @Override
    public boolean isExpire(String token){
        return getExpireSec(token)<=0;
    }

    /**
     * 验证令牌
     *
     * @param token,username       token和usename，判断是否是有效令牌
     * @return 是否有效
     */
    @Override
    public boolean validateToken(String token, String username) {
        Claims claims = getClaimsFromToken(token);
        if (claims.getExpiration()==null
                || claims.getExpiration().getTime()<=System.currentTimeMillis()){
            throw new IllegalArgumentException("token过期");
        }
//        else if (username.equals(claims.getSubject())){
//            throw new IllegalArgumentException("token用户不一致");
//        }
        return true;
    }

    /**
     * 刷新令牌
     *
     * @param token 原令牌
     * @return 新令牌
     */
    @Override
    public String refreshToken(String token){
        Claims claims = getClaimsFromToken(token);
        validateToken(token,"username");
        return generateToken(claims);
    }

    @Override
    public Map<String,Object> parseMap(String token){
        return getClaimsFromToken(token);
    }


}
