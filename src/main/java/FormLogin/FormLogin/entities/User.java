package onetech.onetech.entities;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Table(name = "USER_TABLE")
@Entity
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements UserDetails{
    /**
    	 * 
    	 */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "FIRST_NAME", nullable = false)
    @NotBlank(message = "Firstname required")
    String firstName;
    @Column(name = "LAST_NAME", nullable = false)
    @NotBlank(message = "Lastname required")
    String lastName;
    @Column(name = "BIRTHDATE", nullable = false)
    @Temporal(TemporalType.DATE)
    @Past
    Date birthdate;
    @Column(name = "EMAIL", nullable = false)
    @NotBlank(message = "Email required")
    @Email
    String email;
    @Column(name = "PASSWORD", nullable = false)
    @NotBlank(message = "Password required")
    @Size(min = 8, max = 100, message = "password must have at least 8 caracters.")
    String password;
    @Column(name = "ROLE", nullable = false)
    @Enumerated(EnumType.STRING)
    Role role;
    @Column(name = "LOCKED", nullable = false)
    Boolean locked = false;
    @Column(name = "ENABLED", nullable = false)
    Boolean enabled = true;

    // @Column(name = "RESET_PASSWORD_TOKEN", nullable = true)
    // String resetPasswordToken;

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getAuthority());
        return Collections.singletonList(authority);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
