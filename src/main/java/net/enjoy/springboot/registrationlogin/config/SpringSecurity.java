package net.enjoy.springboot.registrationlogin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SpringSecurity {
        @Autowired
        private UserDetailsService userDetailsService;

        @Bean
        public static PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http.csrf(csrf -> csrf.disable())
                                .authorizeHttpRequests((authorize) ->
                                // co phep tat ca

                                authorize.requestMatchers("/register/**").permitAll()
                                                .requestMatchers("/index/**").permitAll()
                                                .requestMatchers("/index#/**").permitAll()
                                                .requestMatchers("/index#/product").permitAll()
                                                .requestMatchers("/home").permitAll()
                                                .requestMatchers("/admin/**").permitAll()
                                                .requestMatchers("/contact").permitAll()
                                                .requestMatchers("/shop").permitAll()
                                                .requestMatchers("/blog").permitAll()
                                                .requestMatchers("/about").permitAll()
                                                .requestMatchers("/cart/**").permitAll()
                                                .requestMatchers("/profile/**").permitAll()
                                                .requestMatchers("/product_detail").permitAll()
                                                .requestMatchers("/order").permitAll()
                                                .requestMatchers("/order/**").permitAll()
                                                .requestMatchers("/order-detail").permitAll()
                                                .requestMatchers("/users").hasRole("ADMIN")
                                                .requestMatchers("/cart/**").permitAll()
                                                .requestMatchers("/test").permitAll()
                                                .requestMatchers("/admin").permitAll()
                                                .requestMatchers("/api/products").permitAll()
                                                .requestMatchers("/api/products/getall").permitAll()
                                                .requestMatchers("/api/products/add").permitAll()
                                                .requestMatchers("/api/products/add/**").permitAll()
                                                .requestMatchers("/api/products/getProductPage").permitAll()
                                                .requestMatchers("/api/employees/**").permitAll()
                                                .requestMatchers("/api/categories").permitAll()
                                                .requestMatchers("/api/categories/getall").permitAll()
                                                .requestMatchers("/api/employees").permitAll()
                                                .requestMatchers("/api/employees/getall").permitAll()
                                                .requestMatchers("/api/suppliers").permitAll()
                                                .requestMatchers("/api/suppliers/getall").permitAll()
                                                .requestMatchers("/api/importproduct").permitAll()
                                                .requestMatchers("/api/importproduct/getall").permitAll()
                                                .requestMatchers("/api/importproduct/*").permitAll()
                                                .requestMatchers("/manifest.json").permitAll()
                                                .requestMatchers("/css/**", "/js/**", "/img/**", "/assets/**",
                                                                "/static/**")
                                                .permitAll()

                                                .requestMatchers("/css/**", "/js/**", "/img/**", "/assets/**",
                                                                "/static/**")
                                                .permitAll()
                                                .anyRequest().authenticated())
                                .formLogin(
                                                form -> form
                                                                .loginPage("/login")
                                                                .loginProcessingUrl("/login")
                                                                .successHandler(new CustomAuthenticationSuccessHandler())
                                                                .permitAll())
                                .logout(
                                                logout -> logout
                                                                .logoutRequestMatcher(
                                                                                new AntPathRequestMatcher("/logout"))
                                                                .permitAll());
                return http.build();
        }

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
                auth
                                .userDetailsService(userDetailsService)
                                .passwordEncoder(passwordEncoder());
        }

}