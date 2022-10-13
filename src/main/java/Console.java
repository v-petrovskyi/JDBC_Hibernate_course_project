import dao.impl.IncidentDAOImpl;
import dao.impl.ServiceDAOImpl;
import dao.impl.UserDAO_Impl;
import dao.impl.UserRoleDAOImpl;
import entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import services.IncidentService;
import services.ServiceService;
import services.UserRoleService;
import services.UserService;
import services.impl.IncidentServiceImpl;
import services.impl.ServiceServiceImpl;
import services.impl.UserRoleServiceImpl;
import services.impl.UserServiceImpl;
import utils.Authorization;
import utils.TableList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Pattern;

public class Console {
    private static final UserService userService;
    private static final IncidentService incidentService;
    private static final ServiceService serviceService;
    private static final UserRoleService userRoleService;
    private static final Logger LOG = LogManager.getLogger(Console.class);

    static {
        userService = new UserServiceImpl(new UserDAO_Impl());
        incidentService = new IncidentServiceImpl(new IncidentDAOImpl());
        serviceService = new ServiceServiceImpl(new ServiceDAOImpl());
        userRoleService = new UserRoleServiceImpl(new UserRoleDAOImpl());
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
            fetchAllUsers();
        } else if (Pattern.matches("fetch_all_incidents", string)) {
            fetchAllIncidents();
        } else if (Pattern.matches("fetch_all_active_incidents", string)) {
            fetchAllActiveIncidents();
        } else if (Pattern.matches("fetch_user_by_\\d+", string)) {
            fetchUserById(string);
        } else if (Pattern.matches("add_user", string)) {
            addUser();
        } else if (Pattern.matches("update_user_\\d+", string)) {
            // method fetch_user_by_
        } else if (Pattern.matches("delete_user_\\d+", string)) {
            deleteUserById(string);
        } else if (Pattern.matches("subscribe_service_\\d+", string)) {
            subscribeServiceWithId(string);
        } else if (Pattern.matches("unsubscribe_service_\\d+", string)) {
            unsubscribeServiceWithId(string);
        } else if (Pattern.matches("create_incident", string)) {
            createIncident();
        } else if (Pattern.matches("close_incident_\\d+", string)) {
            closeIncident(string);
        } else if (Pattern.matches("q", string)) {
            exit();
        } else {
            System.out.println("невірна команда");
            mainMenu();
        }
        mainMenu();
    }

    private void addUser() {
        if (Authorization.role.equals(UserRole.Role.ADMIN) ||
                Authorization.role.equals(UserRole.Role.SUPER_ADMIN)) {
            System.out.println("Input username");
            String userName = readFromConsole();
            System.out.println("input password");
            String pass = readFromConsole();
            UserRole role = null;
            do {
                System.out.println("Input user role USER, ADMIN, SUPER_ADMIN");
                String str = readFromConsole();
                List<UserRole> allRoles = userRoleService.getAllRoles();
                for (UserRole currentRole : allRoles) {
                    if (currentRole.getRole().toString().equals(str.trim().toUpperCase())) {
                        role = currentRole;
                    }
                }
                if (role == null) System.out.println("this role not exists");
            } while (role == null);
            User user = new User();
            user.setUserName(userName);
            user.setPassword(pass);
            user.setUserRole(role);
            userService.addUser(user);
            System.out.println("would you like to add extra info about user? Y/N");
            while (true) {
                String input = readFromConsole().trim();
                if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("n")) {
                    if (input.equalsIgnoreCase("n")) {
                        mainMenu();
                    } else {
                        break;
                    }
                }
            }
            System.out.println("Input first name");
            String firstName = readFromConsole().trim();
            System.out.println("Input last name");
            String lastName = readFromConsole().trim();
            System.out.println("Input email");
            String email = readFromConsole().trim();
            System.out.println("Input phone number");
            String phoneNumber = readFromConsole().trim();
            System.out.println("Input postal code");
            String postalCode = readFromConsole().trim();
            Profile profile = new Profile(firstName, lastName, email, phoneNumber, postalCode);
            user.setProfile(profile);
            userService.updateUser(user);
        } else {
            System.out.println("Немає доступу");
        }
    }

    private void fetchAllUsers() {
        if (Authorization.role.equals(UserRole.Role.ADMIN) ||
                Authorization.role.equals(UserRole.Role.SUPER_ADMIN)) {
            List<User> allUsers = userService.getAllUsers();
            for (User user : allUsers) {
                fetchUserById(String.valueOf(user.getId()));
            }
        } else {
            System.out.println("Немає доступу");
        }
    }

    private void fetchAllIncidents() {
        if (Authorization.role.equals(UserRole.Role.ADMIN) ||
                Authorization.role.equals(UserRole.Role.SUPER_ADMIN)) {
            List<Incident> incidents = incidentService.getAllIncidents();
            if (incidents.size() == 0) {
                System.out.println("не знайдено інцидентів");
                mainMenu();
            }
            TableList tableList = new TableList("id", "service_name", "is_active", "problem_description", "user_name");
            for (Incident incident : incidents) {
                String userName;
                if (incident.getUser() == null) {
                    userName = "";
                } else {
                    userName = incident.getUser().getUserName();
                }
                tableList.addRow(
                        String.valueOf(incident.getId()),
                        incident.getServiceName(),
                        String.valueOf(incident.isActive()),
                        incident.getProblemDescription(),
                        userName
                );
            }
            System.out.println();
            tableList.print();
            System.out.println();
        } else {
            System.out.println("Немає доступу");
        }
    }

    private void fetchUserById(String id) {
        if (Authorization.role.equals(UserRole.Role.ADMIN) ||
                Authorization.role.equals(UserRole.Role.SUPER_ADMIN)) {
            User user = userService.getUserById(Integer.parseInt(id.replaceAll("\\D+", "")));
            TableList tableList = new TableList("", "Дані");
            tableList.addRow("id", String.valueOf(user.getId()));
            tableList.addRow("user_name", user.getUserName());
            tableList.addRow("password", user.getPassword());
            tableList.addRow("user role", String.valueOf(user.getUserRole().getRole()));
            Profile userProfile = user.getProfile();
            if (userProfile != null) {
                tableList.addRow("first name", userProfile.getFirstName());
                tableList.addRow("last name", userProfile.getLastName());
                tableList.addRow("email", userProfile.getEmail());
                tableList.addRow("phone number", userProfile.getPhoneNumber());
                tableList.addRow("postal code", userProfile.getPostalCode());
            }
            System.out.println("User info");
            tableList.print();
            System.out.println("\n");
            List<Incident> userIncidents = user.getIncidents();
            if (!userIncidents.isEmpty()) {
                TableList tableListIncidents = new TableList("service name", "is active", "problem description");
                for (Incident userIncident : userIncidents) {
                    tableListIncidents.addRow(userIncident.getServiceName(), String.valueOf(userIncident.isActive()), userIncident.getProblemDescription());
                }
                System.out.println("User's incidents");
                tableListIncidents.print();
                System.out.println("\n");
            }
            List<Service> userServices = user.getServices();
            if (!userServices.isEmpty()) {
                TableList tableListServices = new TableList("service name", "is active", "service month price", "customer id");
                for (Service service : userServices) {
                    tableListServices.addRow(service.getServiceName(), String.valueOf(service.isActive()), String.valueOf(service.getServiceMonthPrice()), String.valueOf(service.getCustomerId()));
                }
                System.out.println("User's services");
                tableListServices.print();
                System.out.println("\n");
            }
        } else {
            System.out.println("Немає доступу");
        }
    }

    void deleteUserById(String id) {
        if (Authorization.role.equals(UserRole.Role.ADMIN) ||
                Authorization.role.equals(UserRole.Role.SUPER_ADMIN)) {
            userService.deleteUserById(Integer.parseInt(id.replaceAll("\\D+", "")));
        } else {
            System.out.println("Немає доступу");
        }
    }

    private void subscribeServiceWithId(String id) {
        Service service = serviceService.getServiceById(Integer.parseInt(id.replaceAll("\\D+", "")));
        if (service == null) {
            System.out.println("не знайдено сервіс з id=" + Integer.parseInt(id.replaceAll("\\D+", "")));
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
    }

    private void unsubscribeServiceWithId(String id) {
        Service service = serviceService.getServiceById(Integer.parseInt(id.replaceAll("\\D+", "")));
        if (service == null) {
            System.out.println("не знайдено сервіс з id=" + Integer.parseInt(id.replaceAll("\\D+", "")));
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
    }

    private void createIncident() {
        System.out.println("input service name");
        String serviceName = readFromConsole();
        System.out.println("input problem description");
        String problemDescription = readFromConsole();
        if (incidentService.createIncident(new Incident(serviceName, true, problemDescription, Authorization.currentUser))) {
            System.out.println("інцидент успішно створено");
        }
    }

    private void closeIncident(String Id) {
        if (Authorization.role.equals(UserRole.Role.ADMIN) ||
                Authorization.role.equals(UserRole.Role.SUPER_ADMIN)) {
            incidentService.closeIncident(Integer.parseInt(Id.replaceAll("\\D+", "")));
        } else {
            System.out.println("Немає доступу");
        }
    }

    private void fetchAllActiveIncidents() {
        if (Authorization.role.equals(UserRole.Role.ADMIN) ||
                Authorization.role.equals(UserRole.Role.SUPER_ADMIN)) {
            List<Incident> incidents = incidentService.getAllIncidents();
            if (incidents.size() == 0) {
                System.out.println("не знайдено інцидентів");
                mainMenu();
            }
            TableList tableList = new TableList("id", "service_name", "is_active", "problem_description", "user_name");
            for (Incident incident : incidents) {
                if (!incident.isActive()) continue;
                String userName;
                if (incident.getUser() == null) {
                    userName = "";
                } else {
                    userName = incident.getUser().getUserName();
                }
                tableList.addRow(
                        String.valueOf(incident.getId()),
                        incident.getServiceName(),
                        String.valueOf(incident.isActive()),
                        incident.getProblemDescription(),
                        userName
                );
            }
            System.out.println();
            tableList.print();
            System.out.println();
        } else {
            System.out.println("Немає доступу");
        }
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
