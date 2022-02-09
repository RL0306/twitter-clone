package com.example.twitterclone.security;

import com.example.twitterclone.controller.UserController;
import com.example.twitterclone.filter.CustomFilter;
import com.example.twitterclone.service.EmailSendingService;
import com.example.twitterclone.service.IpService;
import com.example.twitterclone.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final ObjectMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final IpService ipService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .authorizeRequests()
                .antMatchers("/api/register", "/api/login", "/api/ip")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .disable();

        http.addFilterBefore(customFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public CustomFilter customFilter() throws Exception {
        CustomFilter customFilter = new CustomFilter(authenticationManagerBean(), mapper, ipService);
        customFilter.setFilterProcessesUrl("/api/login");
        customFilter.setAuthenticationManager(authenticationManagerBean());
        customFilter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler());
        customFilter.setAuthenticationFailureHandler(myAuthenticationFailHandler());
        return customFilter;
    }

    private AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return new CustomAuthSuccessHandler(mapper);
    }

    private AuthenticationFailureHandler myAuthenticationFailHandler(){
        return new CustomAuthFailHandler(mapper);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }


    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(List.of("http://localhost:3000"));
        config.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Origin","Origin", "Content-Type", "Accept","Authorization"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));

        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
