package club.banyuan.demo.authorization.config;

import club.banyuan.demo.authorization.security.AuthenticationFailHandler;
import club.banyuan.demo.authorization.security.DynamicResourceFilter;
import club.banyuan.demo.authorization.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AuthConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AutowireCapableBeanFactory autowireCapableBeanFactory;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/admin/login");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.exceptionHandling().authenticationEntryPoint(new AuthenticationFailHandler());
        http.authorizeRequests().anyRequest().authenticated();

        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter();
        autowireCapableBeanFactory.autowireBean(jwtAuthenticationFilter);
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        DynamicResourceFilter dynamicResourceFilter = new DynamicResourceFilter();
        autowireCapableBeanFactory.autowireBean(dynamicResourceFilter);
        http.addFilterBefore(dynamicResourceFilter, FilterSecurityInterceptor.class);

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetails userDetails(){
//        return new U
//    }

}
