package club.banyuan.zgMallMgt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;

import javax.servlet.*;
import java.io.IOException;
import java.util.logging.LogRecord;

/**
查询用户所有资源
查询当前请求路径资源
然后比较路径资源是否在用户资源列表中
 UsernamePasswordAuthenticationToken塞入权限信息后，来到这儿做权限校验
 */
public class DynamicResourceFilter extends AbstractSecurityInterceptor implements Filter {

   @Autowired
   private DynamicMetadataSource dynamicMetadataSource;

   @Override
   public void doFilter(ServletRequest servletRequest,
                        ServletResponse servletResponse,
                        FilterChain filterChain)
           throws IOException, ServletException {

        //new完FilterInvocation跳去DynamicMetadataSource获取信息
       FilterInvocation filterInvocation = new FilterInvocation(servletRequest,servletResponse,filterChain);

       // //权限查询，通过request，查询到token， token中的用户名查询权限。查询资源列表。
       // // 鉴权
       //让他执行doFilter，与下面getSecureObjectClass返回值相同，执行doFilter；
       //权限查询，通过request获取token，获取权限，资源列表
       //执行之前去校验权限
       InterceptorStatusToken interceptorStatusToken = super.beforeInvocation(filterInvocation);

       try {
       //DynamicAccessDecisionManager鉴别admin是否具有相关权限后跳转过来，携带了url
           //跳转去JwtAuthenticationFilter，继续执行 filterChain.doFilter(request, response);
           filterInvocation.getChain()
                   .doFilter(filterInvocation.getRequest(), filterInvocation.getResponse());
       } finally {
           super.afterInvocation(interceptorStatusToken,null);
       }

   }

   @Autowired
   //设置AccessDecisionManager
   public void setAccessDecisionManager(DynamicAccessDecisionManager decisionManager) {
       super.setAccessDecisionManager(decisionManager);
   }

   //返回值与上面dofilter中的FilterInvocation一致，一致时才会继续执行
   @Override
   public Class<?> getSecureObjectClass() {
       return FilterInvocation.class;
   }

   @Override
   public SecurityMetadataSource obtainSecurityMetadataSource() {

       return dynamicMetadataSource;
   }

}
