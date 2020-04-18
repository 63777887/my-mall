package club.banyuan.demo.authorization.security;

import club.banyuan.demo.authorization.dao.entity.UmsAdmin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


//把数据库信息转成security需要的
public class AdminUserDetails implements UserDetails {

    private UmsAdmin umsAdmin;
    private List<ResourceConfigAttribute> resourceConfigAttributes;

    public AdminUserDetails() {
    }

    public AdminUserDetails(UmsAdmin umsAdmin,
                            List<ResourceConfigAttribute> resourceConfigAttributes) {
        this.umsAdmin = umsAdmin;
        this.resourceConfigAttributes = resourceConfigAttributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return resourceConfigAttributes;
    }



    @Override
    public String getPassword() {
        return umsAdmin.getPassword();
    }

    @Override
    public String getUsername() {
        return umsAdmin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return umsAdmin.getStatus()==1;
    }
}
