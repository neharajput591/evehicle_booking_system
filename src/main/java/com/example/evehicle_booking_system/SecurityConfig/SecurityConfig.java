// package com.example.evehicle_booking_system.SecurityConfig;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

// // @Configuration
// // public class SecurityConfig {

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     @Autowired
//     private CustomUserDetailsService customUserDetailsService;

//     @Autowired
//     private CustomLoginSuccessHandler customLoginSuccessHandler;

//     // @Bean
//     // public PasswordEncoder passwordEncoder() {
//     //     return new BCryptPasswordEncoder();
//     // }

//     // @Bean
//     // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//     //     http
//     //         .csrf(csrf -> csrf.disable())           // VERY IMPORTANT for fetch POST
//     //         .cors(cors -> {})                      // enable CORS
//     //         .authorizeHttpRequests(auth -> auth
//     //             .requestMatchers("/User/**").authenticated()
//     //             .anyRequest().permitAll()
//     //         )
//     //         .formLogin(form -> form
//     //             .loginPage("/login")               // your login page
//     //             .permitAll()
//     //         )
//     //         .logout(logout -> logout.permitAll());

//     //     return http.build();
//     // }

//     // @Bean
//     // public CorsConfigurationSource corsConfigurationSource() {
//     //     CorsConfiguration config = new CorsConfiguration();
//     //     config.addAllowedOrigin("http://localhost:8080"); // OR your UI port
//     //     config.addAllowedOrigin("http://localhost:5500"); // if using live server
//     //     config.addAllowedHeader("*");
//     //     config.addAllowedMethod("*");
//     //     config.setAllowCredentials(true);                // CRITICAL !!

//     //     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//     //     source.registerCorsConfiguration("/**", config);
//     //     return source;
//     // }
// }

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//             .csrf(csrf -> csrf.disable())
//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers(
//                     "/", 
//                     "/home", 
//                     "/register", 
//                     "/saveUser", 
//                     "/login",
//                     "/images/**", 
//                     "/css/**", 
//                     "/js/**", 
//                     "/webjars/**"
//                 ).permitAll()
//                 .requestMatchers("/Admin/**").hasRole("ADMIN")
//                 .requestMatchers("/User/**").hasRole("USER")
//                 .anyRequest().authenticated()
//             )
//             .formLogin(form -> form
//                 .loginPage("/login")
//                 .loginProcessingUrl("/login")
//                 .defaultSuccessUrl("/User/home", true)
//                 .failureUrl("/login?error=true")
//                 .permitAll()
//             )

//             .formLogin(form -> form
//     .loginPage("/login")
//     .usernameParameter("email") // 👈 this is critical if your input field name is "email"
//     .passwordParameter("password")
//     // .defaultSuccessUrl("/User/home", true)
//     .successHandler(customLoginSuccessHandler) 
//     .failureUrl("/login?error=true")
//     .permitAll()
// )

//             .logout(logout -> logout
//                 .logoutUrl("/logout")
//                 .logoutSuccessUrl("/home?logout=true")
//                 .permitAll()
//             );

//         return http.build();
//     }

//     @Bean
//     public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder)
//             throws Exception {
//         AuthenticationManagerBuilder authManagerBuilder =
//                 http.getSharedObject(AuthenticationManagerBuilder.class);

//         authManagerBuilder
//                 .userDetailsService(customUserDetailsService)
//                 .passwordEncoder(passwordEncoder);

//         return authManagerBuilder.build();
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
// }

package com.example.evehicle_booking_system.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService customUserDetailsService;
    private final CustomLoginSuccessHandler customLoginSuccessHandler;

    public SecurityConfig(UserDetailsService customUserDetailsService,
            CustomLoginSuccessHandler customLoginSuccessHandler) {
        this.customUserDetailsService = customUserDetailsService;
        this.customLoginSuccessHandler = customLoginSuccessHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/", "/home", "/register", "/saveUser", "/login",
                                "/css/**", "/js/**", "/images/**", "/webjars/**")
                        .permitAll()

                        // Important: specific User URLs BEFORE generic /User/**
                        .requestMatchers("/User/addtocart", "/User/payment").authenticated()

                        .requestMatchers("/Admin/**").hasRole("ADMIN")
                        .requestMatchers("/User/**").hasRole("USER")

                        .anyRequest().authenticated())

                // Required for fetch POST
                // .authorizeHttpRequests(auth -> auth
                // .requestMatchers(
                // "/", "/home", "/register", "/saveUser", "/login",
                // "/css/**", "/js/**", "/images/**", "/webjars/**"
                // ).permitAll()
                // .requestMatchers("/User/addtocart", "/User/payment").authenticated()
                // .requestMatchers("/Admin/**").hasRole("ADMIN")
                // .requestMatchers("/User/**").hasRole("USER")
                // .anyRequest().authenticated()
                // )

                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .successHandler(customLoginSuccessHandler)
                        .failureUrl("/login?error=true")
                        .permitAll())
                .sessionManagement(session -> session
                        .invalidSessionUrl("/login?invalidSession=true")
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(false))

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/home?logout=true")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll());

        // .formLogin(form -> form
        // .loginPage("/login")
        // .loginProcessingUrl("/login") // IMPORTANT
        // .usernameParameter("email") // <input name="email">
        // .passwordParameter("password") // <input name="password">
        // .successHandler(customLoginSuccessHandler)
        // .failureUrl("/login?error=true")
        // .permitAll())
        // .logout(logout -> logout
        // .logoutUrl("/logout")
        // .logoutSuccessUrl("/home?logout=true")
        // .permitAll());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,
            PasswordEncoder passwordEncoder)
            throws Exception {

        AuthenticationManagerBuilder authBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);

        authBuilder
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder);

        return authBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
