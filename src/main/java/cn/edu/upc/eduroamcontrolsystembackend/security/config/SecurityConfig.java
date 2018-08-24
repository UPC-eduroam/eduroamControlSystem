package cn.edu.upc.eduroamcontrolsystembackend.security.config;

import cn.edu.upc.eduroamcontrolsystembackend.security.service.JwtAuthenticationEntryPoint;
import cn.edu.upc.eduroamcontrolsystembackend.security.service.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * WebSecurityConfig, used to config Spring Security
 *
 * @author jay
 * @date 2018/07/21
 */

@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //无需权限即可访问的白名单
    private static final String[] AUTH_WHITELIST = {
            "/",
            "/*.html",
            "/favicon.ico",
            "/**/*.html",
            "/**/*.css",
            "/**/*.js",

            // swagger ui
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**",

            //验证绑定邮箱的链接
            "/UserController/VerifyEmail"

    };

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    // Spring会自动寻找实现接口的类注入,会找到我们自己实现的UserDetailsService类
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                // 设置UserDetailsService
                .userDetailsService(this.userDetailsService)
                // 使用BCrypt对密码进行加密
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter();
    }

    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                //禁用csrf防护功能,因为使用token进行身份验证,所以较为安全,而且禁用后也方便开发
                .csrf().disable()
                //添加对于出现权限问题的异常处理
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                //禁用session,因为使用token,所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                //放开options请求
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers(HttpMethod.GET, AUTH_WHITELIST).permitAll()
                //允许匿名访问获取token的api
                .antMatchers("/user/login").permitAll()
                //除上面允许匿名的api外,其他的全部需要身份认证
                .anyRequest().authenticated();

        //根据token进行身份过滤
        httpSecurity
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        // 禁用缓存
        httpSecurity.headers().cacheControl();
    }
}
