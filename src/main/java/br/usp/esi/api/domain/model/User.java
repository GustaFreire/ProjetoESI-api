package br.usp.esi.api.domain.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.usp.esi.api.domain.dto.UserCadastroDto;
import br.usp.esi.api.domain.enums.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "User")
@Table(name = "usuarios")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private UserRole role;

    public User(UserCadastroDto dto) {
        this.username = dto.username();
        this.password = dto.password();
        this.role = dto.role();
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {   
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<? extends GrantedAuthority> userRoleList;

        if (this.role == UserRole.ADMIN) {
            userRoleList = List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else if (this.role == UserRole.DISCENTE) {
            userRoleList = List.of(new SimpleGrantedAuthority("ROLE_DISCENTE"));
        } else if (this.role == UserRole.DOSCENTE) {
            userRoleList = List.of(new SimpleGrantedAuthority("ROLE_DOSCENTE"));
        } else {
            userRoleList = List.of(new SimpleGrantedAuthority("ROLE_CCP"));
        }
        
        return userRoleList;
    }
}