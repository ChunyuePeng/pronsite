package com.pcy.pronsite.dao.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * @description:
 * @author: 彭椿悦
 * @data: 2021/5/12 11:47
 */
@Entity
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String permissionsName;
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "role_permission",joinColumns = {@JoinColumn(name = "permissionId")},inverseJoinColumns =
            {@JoinColumn(name = "roleId")})
    private Set<Role> roles;

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPermissionsName() {
        return permissionsName;
    }

    public void setPermissionsName(String permissionsName) {
        this.permissionsName = permissionsName;
    }
}
