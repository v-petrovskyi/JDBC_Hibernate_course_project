package utils;

import dao.UserDAO;
import dao.impl.UserDAO_Impl;
import entity.User;
import entity.UserRole;
import lombok.Data;

@Data
public class Authorization {
    public static UserRole.Role role;
    private User currentUser;
    private String currentUserName;
    private String currentPassword;


    public Authorization(String currentUserName, String currentPassword) {
        this.currentUserName = currentUserName;
        this.currentPassword = currentPassword;
    }

    public boolean authorizationMethod() {
        UserDAO userDAO = new UserDAO_Impl();
        User user = userDAO.getUserByUserName(currentUserName);
        if (user.getUserName().equals(currentUserName) && user.getPassword().equals(currentPassword)) {
            System.out.printf("Вітаю %s! вхід успішно виконано\n", currentUserName);
            currentUser = user;
            role = user.getUserRole().getRole();
            return true;
        }
        System.out.println("логін або пароль введено не вірно");
        return false;
    }

}
