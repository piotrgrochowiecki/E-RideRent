package com.piotgrochowiecki.eriderent.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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

//        List<GrantedAuthority> userRoles = new ArrayList<>();
//        userRoles.add(new SimpleGrantedAuthority("USER"));
//        List<GrantedAuthority> adminRoles = new ArrayList<>();
//        adminRoles.add(new SimpleGrantedAuthority("ADMIN"));

        List<UserDetails> userDetails = new ArrayList<>();
        userDetails.add(User.withUsername("user").password(passwordEncoder().encode("1234")).roles("USER").build());
        userDetails.add(User.withUsername("admin").password(passwordEncoder().encode("1234")).roles("USER", "ADMIN").build());

        return new InMemoryUserDetailsManager(userDetails);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .anyRequest().permitAll();
                .antMatchers("/reservation/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/dashboard").hasAnyRole("USER", "ADMIN")
                .antMatchers("/car/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("ADMIN")
                .antMatchers("/registration").permitAll()
                .antMatchers("/").anonymous()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/dashboard", true)
                .failureUrl("/")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403");
        return http.build();
    }
}
