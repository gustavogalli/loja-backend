package com.galli.loja.config.security.model;

import com.galli.loja.config.security.enums.RoleName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.Long;

@Entity(name = "roles")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleModel implements GrantedAuthority, Serializable {
    private static final long serialVersionUid = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleName rolename;

    @Override
    public String getAuthority() {
        return this.rolename.toString();
    }
}
