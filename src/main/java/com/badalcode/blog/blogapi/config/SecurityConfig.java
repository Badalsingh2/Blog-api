package com.badalcode.blog.blogapi.config;

import com.badalcode.blog.blogapi.Security.CustomUserDetailService;
import com.badalcode.blog.blogapi.Security.JwtAuthenticationEntryPoint;
import com.badalcode.blog.blogapi.Security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.stereotype.Component;

//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;



@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {



        @Autowired
        private CustomUserDetailService customUserDetailsService;

        @Autowired
        private UserDetailsService userDetailsService;


        @Autowired
        private JwtAuthenticationEntryPoint point;
        @Autowired
        private JwtAuthenticationFilter filter;
        private static final String[] PUBLIC_URLS = {
                "/api/v1/auth/**",
                "/api/posts/**",
                "/api/users/**",
                "/v3/api-docs"
                // Add other public URLs here
        };

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .csrf((csrf) -> csrf.disable())
                    .authorizeRequests((authz) -> authz
                            .requestMatchers(PUBLIC_URLS).permitAll()
                            .requestMatchers("/v3/api-docs").permitAll()
                            .requestMatchers(HttpMethod.POST,"/api/v1/auth/**").permitAll()
                            .requestMatchers(HttpMethod.GET).permitAll()
                            .anyRequest().authenticated()
                    )
                    .exceptionHandling((exceptionHandling) -> exceptionHandling
                            .authenticationEntryPoint(this.point)
                    )
                    .sessionManagement((sessionManagement) -> sessionManagement
                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    )
                    .addFilterBefore(this.filter, UsernamePasswordAuthenticationFilter.class);
            http.authenticationProvider(daoAuthenticationProvider());

            DefaultSecurityFilterChain defaultSecurityFilterChain = http.build();
            return defaultSecurityFilterChain;

        }


        @Bean
        public DaoAuthenticationProvider daoAuthenticationProvider() {
            DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
            authProvider.setUserDetailsService(this.customUserDetailsService);
            authProvider.setPasswordEncoder(passwordEncoder());
            return authProvider;
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
                throws Exception {
            return config.getAuthenticationManager();
        }



        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }



    }

