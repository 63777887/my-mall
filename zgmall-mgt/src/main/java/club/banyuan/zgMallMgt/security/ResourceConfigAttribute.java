package club.banyuan.zgMallMgt.security;

import club.banyuan.zgMallMgt.dao.entity.UmsResource;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.GrantedAuthority;


//转换资源格式，换成符合权限验证的格式
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
