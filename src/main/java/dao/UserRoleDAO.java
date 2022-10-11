package dao;

import entity.UserRole;

public interface UserRoleDAO {
    UserRole getUserRoleById(int id);
    UserRole getUserRoleByRole(String role);
}
