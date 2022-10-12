package services;

import entity.UserRole;

public interface UserRoleService {
    UserRole getUserRoleById(int id);
    UserRole getUserRoleByRole(String role);
}
