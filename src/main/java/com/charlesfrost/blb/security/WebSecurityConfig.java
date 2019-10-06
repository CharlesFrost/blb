package com.charlesfrost.blb.security;

import com.charlesfrost.blb.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@EnableWebSecurity
@CrossOrigin
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private MyUserDetailsService myUserDetailsService;
    private UserRepository userRepository;

    public WebSecurityConfig(MyUserDetailsService myUserDetailsService, UserRepository userRepository) {
        this.myUserDetailsService = myUserDetailsService;
        this.userRepository = userRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), this.userRepository))
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/api/post").hasAnyRole("ADMIN", "MANAGER")
                .antMatchers(HttpMethod.POST, "/api/user").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/api/match").hasAnyRole("MODERATOR", "ADMIN")
                .antMatchers(HttpMethod.POST, "/api/player").hasAnyRole("MODERATOR", "ADMIN")
                .antMatchers(HttpMethod.POST, "/api/team").hasAnyRole("MODERATOR", "ADMIN")
                .antMatchers(HttpMethod.POST, "/api/coach").hasAnyRole("MODERATOR", "ADMIN")

                .antMatchers(HttpMethod.PUT,"/api/post/**").hasAnyRole("ADMIN", "MANAGER")
                .antMatchers(HttpMethod.PUT, "/api/user/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/match/**").hasAnyRole("MODERATOR", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/player/**").hasAnyRole("MODERATOR", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/team/**").hasAnyRole("MODERATOR", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/coach/**").hasAnyRole("MODERATOR", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/statistic/**").hasAnyRole("MODERATOR", "ADMIN")

                .antMatchers(HttpMethod.DELETE,"/api/post/**").hasAnyRole("ADMIN", "MANAGER")
                .antMatchers(HttpMethod.DELETE, "/api/user/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/api/match/**").hasAnyRole("MODERATOR", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/player/**").hasAnyRole("MODERATOR", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/team/**").hasAnyRole("MODERATOR", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/coach/**").hasAnyRole("MODERATOR", "ADMIN")


                .antMatchers("/image").hasRole("ADMIN");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
