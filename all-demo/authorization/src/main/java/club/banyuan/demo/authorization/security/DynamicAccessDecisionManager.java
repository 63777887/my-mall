package club.banyuan.demo.authorization.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Component
public class DynamicAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication,
                       Object object, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {

        Set<String> adminResources = authentication.getAuthorities().stream().
                map(GrantedAuthority::getAuthority).collect(Collectors.toSet());


        boolean isAuthorized=false;
        for (ConfigAttribute c :
                configAttributes) {
            if (adminResources.contains(c.getAttribute())){
                isAuthorized=true;
                break;
            }
        }
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
