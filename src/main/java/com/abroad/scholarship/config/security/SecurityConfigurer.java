package com.abroad.scholarship.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(1)
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private PersonDetailService personDetailService;
    @Autowired
    private JwtAuthenticationEntryPoint authenticationEntryPoint;
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/person").permitAll()
                .antMatchers("/leave").hasAnyAuthority("ADMIN","STAFF")
                .antMatchers("/v2/api-docs",
                        "/swagger-resources",
                        "/swagger-resources/**",
                        "/configuration/ui",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/v3/api-docs/**",
                        "/swagger-ui/**").permitAll()

                .antMatchers("/home", "/company", "/faq",
                        "/contact", "/signup", "/confirmRegistration",
                        "/h2-console/**", "/login", "/logout", "/forgot_password",
                        "/api/v1/auth/login", "/verifyEmail", "/api/v1/auth/users/forgot-password",
                        "/api/v1/auth/users/enter-password", "/api/v1/auth/users/reset-password",
                        "/api/v1/auth/logout", "/cart/**")
                .permitAll()
                .antMatchers("/api/v1/auth/users/fetch-single-product/{id}").hasAnyAuthority("ROLE_ADMIN", "ROLE_PREMIUM", "ROLE_ANONYMOUS")
                .antMatchers("/admin").hasAuthority("ROLE_ADMIN")
                .antMatchers("api/v1/auth/wallet/fund-wallet").hasAuthority("ROLE_PREMIUM")
                .antMatchers("api/v1/auth/wallet/withdrawal").hasAuthority("ROLE_PREMIUM")
                .antMatchers("/orders").hasAuthority("ROLE_ADMIN")
                .antMatchers("/api/admin/delete-product/{productId}").hasAuthority("ROLE_ADMIN")
                .antMatchers( "/checkout",
                        "/wallet", "/order-history",
                        "/favorites", "/verifyEmail")

                .hasAnyAuthority("ROLE_ADMIN", "ROLE_PREMIUM")
                .antMatchers("/api/v1/auth/users/view-a-single-favorite-product").hasAnyAuthority("ROLE_ADMIN", "ROLE_PREMIUM")
                .antMatchers("/api/v1/auth/users/favorites").hasAnyAuthority("ROLE_ADMIN", "ROLE_PREMIUM")
                .antMatchers("/api/v1/auth/users/favorites/{productId}").hasAnyAuthority("ROLE_ADMIN", "ROLE_PREMIUM")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.headers().frameOptions().disable();

    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
        auth.userDetailsService(personDetailService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
