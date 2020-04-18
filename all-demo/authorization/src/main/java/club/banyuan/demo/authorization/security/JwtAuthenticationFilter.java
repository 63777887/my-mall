package club.banyuan.demo.authorization.security;

import club.banyuan.demo.authorization.service.AdminService;
import club.banyuan.demo.authorization.service.UmsResourceService;
import club.banyuan.demo.jwt.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * 进行权限认证
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String AUTH_KEY = "Authorization";
    private static final String SCHEMA = "Bearer";
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);


    @Autowired
    private AdminService adminService;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String authHead = request.getHeader(AUTH_KEY);
        if (authHead!=null && authHead.startsWith(SCHEMA)){

            String token = authHead.substring(SCHEMA.length());
            try {
                UserDetails userDetails = adminService.getUserDetailsByToken(token);

                if (userDetails!=null){

                    // token校验通过，设置身份认证信息
                    // 两个参数构造方法表示身份未认证，三个参数构造方法表示身份已认证
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                                new UsernamePasswordAuthenticationToken(userDetails.getUsername() ,userDetails.getPassword()  ,userDetails.getAuthorities());
                        usernamePasswordAuthenticationToken.setDetails(userDetails);
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }
            } catch (Exception e) {
                LOGGER.warn("认证异常",e);

            }
        }
        filterChain.doFilter(request, response);
    }
}
