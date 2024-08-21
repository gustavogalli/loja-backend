package com.galli.loja.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfigV2 {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        // configs
        http.headers().frameOptions().disable();

        http.httpBasic()
                .and().authorizeHttpRequests()
                // Liberamos as permissões agora dentro da Controller
//                .antMatchers(HttpMethod.GET, "/login").permitAll()
//                .antMatchers(HttpMethod.POST, "/login").hasRole("USER")
//                .antMatchers(HttpMethod.DELETE, "/login").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().csrf().disable();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
