package club.banyuan.demo.authentication.config;

import club.banyuan.demo.jwt.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String AUTH_KEY = "Authorization";
    private static final String SCHEMA = "Bearer";

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String authHead = request.getHeader(AUTH_KEY);
        System.out.println(authHead);
        if (authHead!=null && authHead.startsWith(SCHEMA)){
            String token = authHead.substring(SCHEMA.length());
            try {
                String  username = tokenService.parseSubject(token);
                //            if (username!=null &&
//                    SecurityContextHolder.getContext().getAuthentication()==null){

//                if (tokenService.validateToken(token,username)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(username,null,null);
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//                }
//            }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        filterChain.doFilter(request, response);
    }
}
