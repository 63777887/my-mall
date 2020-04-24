package club.banyuan.zgMallMgt.security;

import club.banyuan.zgMallMgt.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        //首先从这里开始，获取token
        String authHead = request.getHeader(AUTH_KEY);
        if (authHead!=null && authHead.startsWith(SCHEMA)){

            String token = authHead.substring(SCHEMA.length());
            try {
                //获取userDetails用户信息
                UserDetails userDetails = adminService.getUserDetailsByToken(token);

                if (userDetails!=null){

                    // token校验通过，设置身份认证信息
                    // 两个参数构造方法表示身份未认证，三个参数构造方法表示身份已认证
                    //usernamePasswordAuthenticationToken把getUserDetailsByToken获得的带有认证鉴权信息的userDetails交给security
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
