package com.dragon.security.config;

import com.dragon.security.handler.MyAuthenticationFailureHandler;
import com.dragon.security.handler.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 * Security的配置项 @EnableWebSecurity的作用是开启过滤器链条来进行验证
 * @author wanglei
 */
@SpringBootConfiguration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationSuccessHandler successHandler;

    @Autowired
    private MyAuthenticationFailureHandler failureHandler;

    /**
     * 用户认证信息和权限
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 设置用户账号信息和权限
        auth.inMemoryAuthentication()
                .withUser("admin").password("123456").authorities("showOrder","addOrder","updateOrder","deleteOrder")
                .and().withUser("userAdd").password("123456").authorities("showOrder", "addOrder");
    }

    /** 配置HttpSecurity 拦截资源
     *  它的原理是给每一个请求路径分配一个权限名称，然后账号只要关联该名称，就可以拥有访问权限
     * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/showOrder").hasAnyAuthority("showOrder")
                .antMatchers("/addOrder").hasAnyAuthority("addOrder")
                .antMatchers("/updateOrder").hasAnyAuthority("updateOrder")
                .antMatchers("/deleteOrder").hasAnyAuthority("deleteOrder")
                //屏蔽掉SpringSecurity默认的登录页，调整到自定义的登录页面
                .antMatchers("/**").fullyAuthenticated()
                .and().formLogin().loginPage("/login").successHandler(successHandler).failureHandler(failureHandler)
                .and().csrf().disable();
                /*.antMatchers("/**")
                .fullyAuthenticated()
                .and().formLogin()*/
        ; //选择认证模式，这里选择为表单认证 .httpBasic()
    }

    /**
     * Security5.0以上密码支持多种加密方式，不加密会报错：
     * There is no PasswordEncoder mapped for the id "null",所以，这里将其设置为不加密
     */
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}
