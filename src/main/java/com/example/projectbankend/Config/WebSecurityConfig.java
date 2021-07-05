package com.example.projectbankend.Config;

import com.example.projectbankend.ExceptionHandler.JwtExceptionHandler;
import com.example.projectbankend.Filter.AuthorizationFilter;
import com.example.projectbankend.Filter.WebSecurityCorsFilter;
import com.example.projectbankend.Services.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthorizationFilter authorizationFilter;
    @Autowired
    private AuthenticateService authenticateService;
    @Autowired
    private JwtExceptionHandler jwtExceptionHandler;
    @Autowired
    private WebSecurityCorsFilter webSecurityCorsFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticateService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().exceptionHandling()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.httpBasic().authenticationEntryPoint(jwtExceptionHandler);

        http.authorizeRequests().antMatchers("/api/authenticate/**/**").permitAll();

        http.authorizeRequests().antMatchers("/api/admin/**").hasAuthority("admin");
        http.authorizeRequests().antMatchers("/api/user/**").hasAuthority("user");
        http.authorizeRequests().antMatchers("/api/provider/**").hasAuthority("provider");
        http.authorizeRequests().antMatchers("/api/products/**").permitAll();
        http.addFilterBefore(webSecurityCorsFilter, ChannelProcessingFilter.class);
        http.addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
