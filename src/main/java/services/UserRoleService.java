package services;

import entity.UserRole;

import java.util.List;

public interface UserRoleService {
    UserRole getUserRoleById(int id);
    UserRole getUserRoleByRole(UserRole.Role role);

    List<UserRole> getAllRoles();
}
