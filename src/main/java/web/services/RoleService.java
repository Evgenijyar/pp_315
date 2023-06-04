package web.services;

import web.models.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();

    void addRole(Role role);

}
