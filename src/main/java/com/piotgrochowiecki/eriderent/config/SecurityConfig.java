package com.piotgrochowiecki.eriderent.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//
//        List<UserDetails> userDetails = new ArrayList<>();
//        userDetails.add(User.withUsername("user").password(passwordEncoder().encode("1234")).roles("USER").build());
//        userDetails.add(User.withUsername("admin").password(passwordEncoder().encode("1234")).roles("USER", "ADMIN").build());
//
//        return new InMemoryUserDetailsManager(userDetails);
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .anyRequest().permitAll();
//                .antMatchers("/reservation/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
//                .antMatchers("/dashboard").hasAnyRole("USER", "ADMIN")
                .antMatchers("/dashboard").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
//                .antMatchers("/car/**").hasAuthority("ROLE_ADMIN")
//                .antMatchers("/user/**").hasAuthority("ROLE_ADMIN")
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
