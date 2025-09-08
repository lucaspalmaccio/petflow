package br.com.fatec.ipiranga.clinicar.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    // Define o papel/permissão do usuário. Ex: "ROLE_ADMIN", "ROLE_USUARIO"
    private String role;


    // Métodos da interface UserDetails (Spring Security)

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Retorna a lista de permissões/papéis do usuário.
        // O Spring Security usa isso para autorizar o acesso.
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        // Retorna a senha do usuário.
        return this.senha;
    }

    @Override
    public String getUsername() {
        // Retorna o identificador único do usuário para login.
        // Usaremos o email, que é uma prática comum e segura.
        return this.email;
    }

    // Para um TCC, podemos deixar estes métodos como 'true' por padrão.
    // Em um sistema real, eles controlam se a conta está expirada, bloqueada, etc.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}