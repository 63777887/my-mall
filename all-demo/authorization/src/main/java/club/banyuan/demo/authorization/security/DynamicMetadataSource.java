package club.banyuan.demo.authorization.security;

import club.banyuan.demo.authorization.dao.entity.UmsResource;
import club.banyuan.demo.authorization.service.UmsResourceService;
import cn.hutool.core.util.URLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.awt.*;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DynamicMetadataSource implements SecurityMetadataSource {

    @Autowired
    private UmsResourceService umsResourceService;



    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation invocation = (FilterInvocation) object;
        String url = invocation.getRequestUrl();
        String path = URLUtil.getPath(url);
        List<UmsResource> allResource = umsResourceService.getAllResource();
        AntPathMatcher antPathMatcher = new AntPathMatcher();

        //stream流，lambda表达式
        return allResource.stream()
                .filter(t->antPathMatcher.match(t.getUrl(),path) )
                .map(ResourceConfigAttribute::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
