package club.banyuan.zgMallMgt.user;

import org.springframework.cache.annotation.Cacheable;

import java.util.Objects;

public class JacksonUser {
    private String username;
    private String password;
    private static int count;

    public JacksonUser() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JacksonUser that = (JacksonUser) o;
        return Objects.equals(username, that.username) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    public JacksonUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Cacheable(value = "user",key = "'key'")
    public JacksonUser jacksonUser(){
        count++;
        return new JacksonUser("user"+count,count+"");
    }

    @Override
    public String toString() {
        return "JacksonUser{" +
                "admin='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Cacheable(value = "user",key = "'key'")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
