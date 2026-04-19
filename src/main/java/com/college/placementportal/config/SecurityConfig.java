package com.college.placementportal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {
       @Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http
        .csrf(csrf -> csrf.disable())
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
        .authorizeHttpRequests(auth -> auth
            .anyRequest().permitAll()   // 🔥 allow everything
        )
        .httpBasic(httpBasic -> httpBasic.disable()); // 🔥 disable auth

    return http.build();
}
//     @Bean
// public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//     http
//         .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//         .csrf(csrf -> csrf.disable())
//         .authorizeHttpRequests(auth -> auth

//             // 🔥 ADD THIS LINE (CDC access)
//             .requestMatchers("/cdc/**").permitAll()

//             // ✅ Allow file access
//             .requestMatchers("/files/**").permitAll()

//             // 🔒 Only ADMIN can update status
//             .requestMatchers(HttpMethod.PUT, "/applications/*/status")
//             .hasRole("ADMIN")

//             // Applications
//             .requestMatchers("/applications/**")
//             .hasAnyRole("ADMIN", "STUDENT")

//             // Admin-only
//             .requestMatchers("/companies/**")
//             .hasRole("ADMIN")

//             .requestMatchers("/jobposts/**")
//             .hasRole("ADMIN")

//             // Student + Admin
//             .requestMatchers("/students/**")
//             .hasAnyRole("ADMIN", "STUDENT")

//             // Everything else requires login
//             // .anyRequest().authenticated()
//             .anyRequest().permitAll()
//         )
//         .httpBasic(Customizer.withDefaults());

//     return http.build();
// }

    // ✅ CORS CONFIGURATION
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(List.of("http://localhost:3000"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin123"))
                .roles("ADMIN")
                .build();

        UserDetails student = User.builder()
                .username("student")
                .password(passwordEncoder.encode("student123"))
                .roles("STUDENT")
                .build();

        return new InMemoryUserDetailsManager(admin, student);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}