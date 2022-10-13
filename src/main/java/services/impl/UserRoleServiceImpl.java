package services.impl;

import dao.UserRoleDAO;
import entity.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import services.UserRoleService;

import java.util.List;

public class UserRoleServiceImpl implements UserRoleService {
    private static final Logger LOG = LogManager.getLogger(UserRoleServiceImpl.class);
    private final UserRoleDAO userRoleDAO;

    public UserRoleServiceImpl(UserRoleDAO userRoleDAO) {
        this.userRoleDAO = userRoleDAO;
    }


    @Override
    public UserRole getUserRoleById(int id) {
        return userRoleDAO.getUserRoleById(id);
    }

    @Override
    public UserRole getUserRoleByRole(UserRole.Role role) {
        return userRoleDAO.getUserRoleByRole(role);
    }

    @Override
    public List<UserRole> getAllRoles() {
        return userRoleDAO.getAllRoles();
    }
}
