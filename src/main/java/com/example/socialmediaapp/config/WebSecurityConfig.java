package com.example.socialmediaapp.config;

import com.example.socialmediaapp.util.JwtRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;
    private final UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(SWAGGER_WHITELIST).permitAll()
                        .requestMatchers("/email").permitAll()
                        .requestMatchers("roles/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/users").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/users").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/users").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET,"/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/posts/users").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/posts").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/posts").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/posts").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/posts").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/likes").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/likes").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/likes/post").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/likes").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/comments").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/comments/post/").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/comments").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/comments/{commentId}/user/").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/comments").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/home/").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/follows").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/follows").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/follows/followers/").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/follows/followings/").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/messages").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/messages/sender/").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/explore").hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated()
        )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
//                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                .logout(LogoutConfigurer::permitAll);
        http.userDetailsService(userDetailsService);

        return http.build();
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("password")
//                        .roles("USER")
//                        .build();
//
//        UserDetails admin =
//                User.withDefaultPasswordEncoder()
//                        .username("admin")
//                        .password("admin")
//                        .roles("ADMIN")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }

    public static final String[] SWAGGER_WHITELIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "api/authenticate"
    };

}
