package com.example.careermanagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityYapilandirma {
    private final KullaniciDetayServisi kullaniciDetayServisi;
    private final YetkisizGirisHatasi yetkisizGirisHatasi;

    public SecurityYapilandirma(KullaniciDetayServisi kullaniciDetayServisi,
                                YetkisizGirisHatasi yetkisizGirisHatasi) {
        this.kullaniciDetayServisi = kullaniciDetayServisi;
        this.yetkisizGirisHatasi = yetkisizGirisHatasi;
    }

    @Bean
    public JwtKimlikDogrulamaFiltresi kimlikDogrulamaJwtTokenFiltresi() {
        return new JwtKimlikDogrulamaFiltresi();
    }

    @Bean
    public PasswordEncoder sifreKodlayici() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(yetkisizGirisHatasi)
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/test/**").permitAll()
                        .anyRequest().authenticated()
                );

        http.addFilterBefore(kimlikDogrulamaJwtTokenFiltresi(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}