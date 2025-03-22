package lk.ijse.voaestheticlounge.config;

import lk.ijse.voaestheticlounge.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/v1/auth/authenticate",
                                "/api/v1/user/register",
                                "/api/v1/auth/refreshToken",
                                "/api/v1/user/delete/{id}",
                                "/api/v1/user/update/{id}",
                                "/api/v1/user/getAll",
                                "/api/v1/booking",
                                "/api/v1/booking/save",
                                "/api/v1/order/save",
                                "/api/v1/booking/update/{id}",
                                "/api/v1/booking/delete/{id}",
                                "/api/v1/booking/getAll",
                                "/api/v1/service/save",
                                "/api/v1/service/delete/{id}",
                                "/api/v1/service/update/{id}",
                                "/api/v1/service/getAll",
                                "/api/v1/product",
                                "/api/v1/product/save",
                                "/api/v1/product/delete/{id}",
                                "/api/v1/product/update/{id}",
                                "/api/v1/product/getAll",
                                "/api/v1/cart",
                                "/api/v1/cart/save",
                                "/api/v1/cart/getAll",
                                "/api/v1/cart/delete/{id}",
                                "/api/v1/cart/update/{id}",
                                "/v3/api-docs/",
                                "/swagger-ui/",
                                "/swagger-ui.html"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}