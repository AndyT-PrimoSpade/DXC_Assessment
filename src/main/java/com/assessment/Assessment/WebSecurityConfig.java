package com.assessment.Assessment;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.web.bind.annotation.*;

import javax.sql.*;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery("select email, password, enabled from user where email=?")
                .authoritiesByUsernameQuery("select email, role from user where email=?")
        ;
    }
    @CrossOrigin
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http.formLogin().loginPage("/index");

        http.formLogin()
                .defaultSuccessUrl("/welcome");

        http.logout()
                .logoutSuccessUrl("/index");

        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/welcome").authenticated()
                .antMatchers("/data").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/index").permitAll()
                .and()
                .logout().permitAll();
    }
}

