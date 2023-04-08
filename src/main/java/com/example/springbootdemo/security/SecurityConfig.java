package com.example.springbootdemo.security;

import com.example.springbootdemo.security.filters.JWTFilter;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@AllArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private JWTFilter jwtFilter;

    @SneakyThrows
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {
        http
                .csrf()
                .disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/clients/save", "/clients/login").permitAll()
                .requestMatchers(HttpMethod.GET, "/clients/**").permitAll()
                .requestMatchers("/admin/**").hasAnyAuthority("ADMIN", "SUPERADMIN")
                .requestMatchers("/superadmin/**").hasAuthority("SUPERADMIN")
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", HttpMethod.POST.name()));
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000","http://localhost:4200"));
        corsConfiguration.addExposedHeader("Authorization");


        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("**/",corsConfiguration);

        return source;
    }

//    @Bean
//    public UserDetailsService users(){
//        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();
//        UserDetails superadmin = userBuilder
//                .username("superadmin")
//                .password("asdqwe")
//                .roles("SUPERADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(superadmin);
//    }
}
