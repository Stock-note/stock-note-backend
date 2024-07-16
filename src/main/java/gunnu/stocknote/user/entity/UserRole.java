package gunnu.stocknote.user.entity;

import lombok.Getter;

@Getter
public enum UserRole {

    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private String authority;

    UserRole(String authority) {
        this.authority = authority;
    }
}
