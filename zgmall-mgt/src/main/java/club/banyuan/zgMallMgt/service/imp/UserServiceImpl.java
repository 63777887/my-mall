package club.banyuan.zgMallMgt.service.imp;

import club.banyuan.zgMallMgt.service.UserService;
import club.banyuan.zgMallMgt.user.JacksonUser;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {


    @Override
    @Cacheable(value = "user",key = "#name")
    public JacksonUser getUser(String name) {
        return new JacksonUser(name, ((int)(Math.random()*100000))+"");
    }

    @Override
    @CachePut(value = "user",key = "#name")
    public JacksonUser putUser(String name, String password) {
        JacksonUser user = getUser(name);
        user.setPassword(password);
        return user;
    }

    @Override
    @CacheEvict(value = "user",key = "#name")
    public boolean deleteUser(String name) {
        return getUser(name)!=null;
    }
}