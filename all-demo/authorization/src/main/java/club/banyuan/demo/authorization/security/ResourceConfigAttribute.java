package club.banyuan.demo.authorization.security;

import club.banyuan.demo.authorization.dao.entity.UmsResource;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.GrantedAuthority;

public class ResourceConfigAttribute implements ConfigAttribute , GrantedAuthority {

    private UmsResource umsResource;

    public ResourceConfigAttribute(UmsResource umsResource) {
        this.umsResource = umsResource;
    }

    @Override
    public String getAttribute() {
        return umsResource.getId()+":"+umsResource.getName();
    }

    public UmsResource getUmsResource() {
        return umsResource;
    }

    public void setUmsResource(UmsResource umsResource) {
        this.umsResource = umsResource;
    }

    @Override
    public String getAuthority() {
        return getAttribute();
    }
}
