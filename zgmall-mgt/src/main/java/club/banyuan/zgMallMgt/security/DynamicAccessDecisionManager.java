package club.banyuan.zgMallMgt.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * 用DynamicMetadataSource返回的权限
 * 动态的权限验证
 */
@Component
public class DynamicAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication,
                       Object object, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {

        //获取所需要的权限的集合
        Set<String> adminResources = authentication.getAuthorities().stream().
                map(GrantedAuthority::getAuthority).collect(Collectors.toSet());

        //进行验证，符合则返回ture
        boolean isAuthorized=false;
        for (ConfigAttribute c :
                configAttributes) {
            if (adminResources.contains(c.getAttribute())){
                isAuthorized=true;
                break;
            }
        }
        //判断，符合则允许继续，否则跳出
        if (!isAuthorized){
            throw new AccessDeniedException("没有访问权限");
        }
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
