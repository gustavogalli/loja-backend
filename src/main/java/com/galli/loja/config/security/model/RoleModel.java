package com.galli.loja.config.security.model;

import com.galli.loja.config.security.enums.RoleName;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity(name = "roles")
@Getter @Setter
public class RoleModel implements GrantedAuthority, Serializable {
    private static final long serialVersionUid = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID roleId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleName rolename;

    @Override
    public String getAuthority() {
        return this.rolename.toString();
    }
}
