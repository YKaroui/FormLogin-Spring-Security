package onetech.onetech.entities;

import org.springframework.security.core.GrantedAuthority;

public enum Role  implements GrantedAuthority {
    ADMIN, CONSULTANT, COMMERCIAL;

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }

}
