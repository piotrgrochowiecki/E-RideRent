package com.piotgrochowiecki.eriderent.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {

        List<GrantedAuthority> userRoles = new ArrayList<>();
        userRoles.add(new SimpleGrantedAuthority("USER"));
        List<GrantedAuthority> adminRoles = new ArrayList<>();
        adminRoles.add(new SimpleGrantedAuthority("ADMIN"));

        List<UserDetails> userDetails = new ArrayList<>();
        userDetails.add(new User("user", passwordEncoder().encode("1234"), userRoles));
        userDetails.add(new User("admin", passwordEncoder().encode("1234"),adminRoles ));

        return new InMemoryUserDetailsManager(userDetails);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/reservation/**").hasRole("user")
                .antMatchers("/car/**").hasRole("admin")
                .antMatchers("/user/**").hasRole("admin")
                .antMatchers("/").anonymous()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/afterLogin", true)
                .failureUrl("/")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID");
        return http.build();
    }
}
