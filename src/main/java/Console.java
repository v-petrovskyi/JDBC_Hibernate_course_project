import dao.impl.IncidentDAOImpl;
import dao.impl.ServiceDAOImpl;
import dao.impl.UserDAO_Impl;
import entity.Incident;
import entity.Service;
import entity.User;
import entity.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import services.IncidentService;
import services.ServiceService;
import services.UserService;
import services.impl.IncidentServiceImpl;
import services.impl.ServiceServiceImpl;
import services.impl.UserServiceImpl;
import utils.Authorization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Console {
    private static final UserService userService;
    private static final IncidentService incidentService;
    private static final ServiceService serviceService;
    private static final Logger LOG = LogManager.getLogger(Console.class);

    static {
        userService = new UserServiceImpl(new UserDAO_Impl());
        incidentService = new IncidentServiceImpl(new IncidentDAOImpl());
        serviceService = new ServiceServiceImpl(new ServiceDAOImpl());
    }


    public void start() {
        while (true) {
            System.out.println("Введіть логін");
            String userName = readFromConsole().trim();
            if (userName.equalsIgnoreCase("q")) {
                exit();
            }
            System.out.println("Введіть пароль");
            String password = readFromConsole().trim();
            LOG.info("the user entered the following data username = {}, password = {}", userName, password);
            Authorization authorization = new Authorization(userName, password);
            if (authorization.authorizationMethod()) {
                break;
            }
        }
        mainMenu();

    }

    private void mainMenu() {
        System.out.println("Введіть запит:");
        String string = readFromConsole();
        if (Pattern.matches("fetch_all_users", string)) {
            System.out.println("method fetch_all_users");
            // method fetch_all_users
        } else if (Pattern.matches("fetch_all_incidents", string)) {
            System.out.println("method \"fetch_all_incidents\"");
            // method "fetch_all_incidents"
        } else if (Pattern.matches("fetch_all_active_incidents", string)) {
            System.out.println("method fetch_all_active_incidents ");
            // method fetch_all_active_incidents
        } else if (Pattern.matches("fetch_user_by_\\d+", string)) {
            System.out.println("method \"fetch_user_by_{id}\"");
            // method "fetch_user_by_{id}"
        } else if (Pattern.matches("add_user", string)) {
            // method add_user
        } else if (Pattern.matches("update_user_\\d+", string)) {
            // method fetch_user_by_
        } else if (Pattern.matches("delete_user_\\d+", string)) {
            if (Authorization.role.equals(UserRole.Role.ADMIN) ||
                    Authorization.role.equals(UserRole.Role.SUPER_ADMIN)) {
                userService.deleteUserById(Integer.parseInt(string.replaceAll("\\D+", "")));
            } else {
                System.out.println("Немає доступу");
            }
        } else if (Pattern.matches("subscribe_service_\\d+", string)) {
            Service service = serviceService.getServiceById(Integer.parseInt(string.replaceAll("\\D+", "")));
            if (service == null) {
                mainMenu();
            }
            User user;
            if (Authorization.role.equals(UserRole.Role.ADMIN) ||
                    Authorization.role.equals(UserRole.Role.SUPER_ADMIN)) {
                System.out.println("Введіть id користувача");
                user = userService.getUserById(Integer.parseInt(readFromConsole()));
                if (user == null) {
                    mainMenu();
                }
            } else {
                user = Authorization.currentUser;
            }
            userService.subscribeUserToService(user, service);
        } else if (Pattern.matches("unsubscribe_service_\\d+", string)) {
            Service service = serviceService.getServiceById(Integer.parseInt(string.replaceAll("\\D+", "")));
            if (service == null) {
                mainMenu();
            }
            User user;
            if (Authorization.role.equals(UserRole.Role.ADMIN) ||
                    Authorization.role.equals(UserRole.Role.SUPER_ADMIN)) {
                System.out.println("Введіть id користувача");
                user = userService.getUserById(Integer.parseInt(readFromConsole()));
                if (user == null) {
                    mainMenu();
                }
            } else {
                user = Authorization.currentUser;
            }
            userService.unsubscribeUserFromService(user, service);

        } else if (Pattern.matches("create_incident", string)) {
            System.out.println("input service name");
            String serviceName = readFromConsole();
            System.out.println("input problem description");
            String problemDescription = readFromConsole();
            if (incidentService.createIncident(new Incident(serviceName, true, problemDescription, Authorization.currentUser))) {
                System.out.println("інцидент успішно створено");
            }
        } else if (Pattern.matches("close_incident_\\d+", string)) {
            if (Authorization.role.equals(UserRole.Role.ADMIN) ||
                    Authorization.role.equals(UserRole.Role.SUPER_ADMIN)) {
                incidentService.closeIncident(Integer.parseInt(string.replaceAll("\\D+", "")));
            } else {
                System.out.println("Немає доступу");
            }
        } else if (Pattern.matches("q", string)) {
            exit();
        } else {
            System.out.println("невірна команда");
            mainMenu();
        }
        mainMenu();
    }


    private void exit() {
        LOG.info("Close app");
        System.out.println("Exit....");
        System.exit(0);
    }

    private String readFromConsole() {
        LOG.info("start read from console");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String readLine = br.readLine();
            LOG.info("user typed \"{}\"", readLine);
            return readLine;
        } catch (IOException e) {
            LOG.error(e);
        }
        return "";
    }

}
