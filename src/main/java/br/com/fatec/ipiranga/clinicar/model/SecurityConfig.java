package br.com.fatec.ipiranga.clinicar.model; // Ou o pacote onde você criou o arquivo

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // Ativa a configuração de segurança web do Spring
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Desabilita a proteção CSRF. Essencial para APIs REST que não usam sessões.
                .csrf(csrf -> csrf.disable())
                // 2. Inicia a configuração das regras de autorização para as requisições HTTP.
                .authorizeHttpRequests(auth -> auth
                        // 3. A REGRA MAIS IMPORTANTE: Permite que qualquer um acesse o endpoint de cadastro.
                        .requestMatchers("/api/usuarios/cadastro").permitAll()
                        // 4. Para qualquer outra requisição (anyRequest), o usuário precisa estar autenticado.
                        .anyRequest().authenticated()
                );
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}