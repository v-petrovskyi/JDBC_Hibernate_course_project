package dao;

import entity.UserRole;

import java.util.List;

public interface UserRoleDAO {
    UserRole getUserRoleById(int id);
    UserRole getUserRoleByRole(UserRole.Role role);

    List<UserRole> getAllRoles();
}
