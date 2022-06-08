package com.piotgrochowiecki.eriderent.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .antMatcher("/**")
                .authorizeRequests(authorize -> authorize
                        .anyRequest().authenticated()
                )
                .build();
    }

//    @Bean
//    public AuthService authService() {
//        return new AuthService();
//    }


//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(authService()).passwordEncoder(passwordEncoder());
//        auth.inMemoryAuthentication()
//                .withUser("admin@admin.com").password(passwordEncoder().encode("1234")).roles("admin")
//                .and()
//                .withUser("user@user.com").password(passwordEncoder().encode("1234")).roles("user");
//    }
//
//
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf()
//                .disable()
//                .authorizeRequests()
//                .antMatchers("/reservation/**").hasRole("user")
//                .antMatchers("/car/**").hasRole("admin")
//                .antMatchers("/").anonymous()
//                .antMatchers("/login").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login.jsp")
//                .loginProcessingUrl("/login")
//                .usernameParameter("email")
//                .passwordParameter("password")
//                .defaultSuccessUrl("/afterLogin.jsp", true)
//                .failureUrl("/")
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .deleteCookies("JSESSIONID");
//
////                .anyRequest()
////                .permitAll();
//    }
//
//}
