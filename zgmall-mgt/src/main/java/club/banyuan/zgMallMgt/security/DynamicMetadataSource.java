package club.banyuan.zgMallMgt.security;

import club.banyuan.zgMallMgt.dao.entity.UmsResource;
import club.banyuan.zgMallMgt.service.UmsResourceService;
import cn.hutool.core.util.URLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class DynamicMetadataSource implements SecurityMetadataSource {

    @Autowired
    private UmsResourceService umsResourceService;


    /**
     * 返回访问路径所需要的资源列表(字符串)
     *
     * @param object
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

        //获取当前的路径，filterInvocation与拦截器重的对应
        FilterInvocation invocation = (FilterInvocation) object;
        String url = invocation.getRequestUrl();
        String path = URLUtil.getPath(url);

        //获取所有的资源
        List<UmsResource> allResource = umsResourceService.getAllResource();

        //进行匹配
        AntPathMatcher antPathMatcher = new AntPathMatcher();

        //stream流，lambda表达式
        //返回一个ResourceConfigAttribute的资源集合
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
