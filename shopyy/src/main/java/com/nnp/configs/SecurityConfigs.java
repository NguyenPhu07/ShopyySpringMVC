/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnp.configs;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import javax.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 *
 * @author Admin
 */
@Configuration
@EnableWebMvcSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.nnp.controllers",
    "com.nnp.repository",
    "com.nnp.service",
    "com.nnp.pojo", 
    "com.nnp.configs"// để cho quét cái @Componet ở CustomAccessDeniedHandler
})
public class SecurityConfigs extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;
    
    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Bean// tạo bean làm hàm băng mật khẩu
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override // sau khi chứng thực và băm rồi sẽ đối chiếu lại lần nữa thông qua userDetailsService
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override // Phân Quyền
    protected void configure(HttpSecurity http) throws Exception {
        // tạo form login từ security đã làm sẵn
        http.formLogin().loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password");

        http.formLogin()
                 .successHandler(customAuthenticationSuccessHandler) // Sử dụng Custom Success Handler
                .failureUrl("/login?error=1");

        http.logout().logoutSuccessUrl("/login");

        http.exceptionHandling()
                .accessDeniedPage("/login?accessDenied=1");

        // Phân Quyền
        http.authorizeRequests().antMatchers("/").permitAll()// cho tất cả truy cập ở controller /
                .antMatchers("/approval").hasAnyRole("ADMIN", "STAFF")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/manager").hasAnyRole("ADMIN", "SELLER", "STAFF") // chỉ cho ADMIN truy cập ở controller /admin/gì đó...
                .antMatchers("/shop/**").hasRole("SELLER")
                .antMatchers("/stats/**").hasAnyRole("SELLER", "ADMIN");

        http.csrf().disable();

    }

    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary
                = new Cloudinary(ObjectUtils.asMap(
                        "cloud_name", "dxsxt6jm4",
                        "api_key", "278379285187921",
                        "api_secret", "lE0GDZYu6L9elAqfuWpOIXwbNpo",
                        "secure", true));
        return cloudinary;
    }

}
