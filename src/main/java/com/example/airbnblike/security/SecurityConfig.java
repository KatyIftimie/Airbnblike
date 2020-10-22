package com.example.airbnblike.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtTokenServices jwtTokenServices;

    public SecurityConfig(JwtTokenServices jwtTokenServices) {
        this.jwtTokenServices = jwtTokenServices;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable().cors().and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/auth/login").permitAll()
                .antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**","/swagger-resources/configuration/ui","/swagger-ui.html").permitAll()
                .antMatchers("/api/v1/auth/register").permitAll()
                .antMatchers("/api/v1/images/**").permitAll()
                .antMatchers("/api/v1/rentals/count-rentals-and-rooms").permitAll()
                .antMatchers("/api/v1/auth/get-user/**").authenticated()
                .antMatchers(HttpMethod.GET, "/api/v1/rentals/**").authenticated()
                .antMatchers(HttpMethod.GET, "/api/v1/beds").hasRole("HOST")
                .antMatchers(HttpMethod.GET, "/api/v1/rooms/room-types").hasRole("HOST")
                .antMatchers(HttpMethod.GET, "/api/v1/amenities").hasRole("HOST")
                .antMatchers(HttpMethod.GET, "/api/v1/rentals/rental-types").hasRole("HOST")
                .antMatchers(HttpMethod.GET, "/api/v1/rentals/country/**").hasRole("HOST")
                .antMatchers(HttpMethod.POST, "/api/v1/rentals").hasRole("HOST")
                .antMatchers(HttpMethod.POST, "/api/v1/rentals/**/images").hasRole("HOST")
                .antMatchers(HttpMethod.POST, "/api/v1/reservations").hasRole("GUEST")
                .antMatchers(HttpMethod.GET, "/api/v1/reservations/user/**").hasRole("GUEST")
                .antMatchers(HttpMethod.POST, "/api/v1/reservations/bookedRooms").hasRole("GUEST")
                .anyRequest().denyAll()
                // NEW PART:
                .and()
                .addFilterBefore(new JwtTokenFilter(jwtTokenServices), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource()
    {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "DELETE", "PUT", "PATCH"));
        configuration.setAllowedHeaders(Arrays.asList("X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization", "Access-Control-Request-Method", "Access-Control-Request-Headers", "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Set-Cookie", "Cookie", "token"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
