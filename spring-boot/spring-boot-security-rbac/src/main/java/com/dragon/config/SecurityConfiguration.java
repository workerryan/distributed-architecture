package com.dragon.config;

import com.dragon.entity.Permission;
import com.dragon.mapper.PermissionMapper;
import com.dragon.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import com.dragon.handler.MyAuthenticationFailureHandler;
import com.dragon.handler.MyAuthenticationSuccessHandler;
import com.dragon.service.MyUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

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

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 用户认证信息和权限
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService)
                //设置加密方式
                .passwordEncoder(new PasswordEncoder() {
                    @Override
                    public String encode(CharSequence charSequence) {
                        //加密
                        return MD5Util.encode((String)charSequence);
                    }

                    /**
                     * 加密的密码与数据库的密码进行比对
                     * @param rawPassword     表单提交的密码
                     * @param encodePassword  数据库查询得到的密码
                     * @return                进行比对的结果
                     */
                    @Override
                    public boolean matches(CharSequence rawPassword, String encodePassword) {
                        return MD5Util.encode((String)rawPassword).equalsIgnoreCase(encodePassword);
                    }
                });
    }

    /** 配置HttpSecurity 拦截资源
     *  它的原理是给每一个请求路径分配一个权限名称，然后账号只要关联该名称，就可以拥有访问权限
     * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       /* http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/showOrder").hasAnyAuthority("showOrder")
                .antMatchers("/addOrder").hasAnyAuthority("addOrder")
                .antMatchers("/updateOrder").hasAnyAuthority("updateOrder")
                .antMatchers("/deleteOrder").hasAnyAuthority("deleteOrder")
                //屏蔽掉SpringSecurity默认的登录页，调整到自定义的登录页面
                .antMatchers("/**").fullyAuthenticated()
                .and().formLogin().loginPage("/login").successHandler(successHandler).failureHandler(failureHandler)
                .and().csrf().disable();
        ; //选择认证模式，这里选择为表单认证 .httpBasic()*/

        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests = http
                .authorizeRequests();
        // 1.读取数据库权限列表
        List<Permission> listPermission = permissionMapper.findAllPermission();
        for (Permission permission : listPermission) {
            // 设置权限
            authorizeRequests.antMatchers(permission.getUrl()).hasAnyAuthority(permission.getPermTag());
        }
        authorizeRequests
                .antMatchers("/login").permitAll()
                .antMatchers("/**").fullyAuthenticated()
                .and().formLogin().loginPage("/login").successHandler(successHandler).failureHandler(failureHandler)
                .and().csrf().disable();
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
