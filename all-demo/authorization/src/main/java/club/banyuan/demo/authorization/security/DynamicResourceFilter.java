package club.banyuan.demo.authorization.security;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;

import javax.servlet.*;
import java.io.IOException;
import java.util.logging.LogRecord;

public class DynamicResourceFilter extends AbstractSecurityInterceptor implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        FilterInvocation filterInvocation =
                new FilterInvocation(servletRequest,servletResponse,filterChain);

        //让他执行doFilter，与下面getSecureObjectClass返回值相同，执行doFilter；
        //权限查询，通过request获取token，获取权限，资源列表
        InterceptorStatusToken interceptorStatusToken =
                super.beforeInvocation(filterInvocation);

        try {
            filterInvocation.getChain()
                    .doFilter(filterInvocation.getRequest(), filterInvocation.getResponse());
        } finally {
            super.afterInvocation(interceptorStatusToken,null);
        }

    }


    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {



        return null;
    }

}
