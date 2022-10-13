import dao.impl.IncidentDAOImpl;
import dao.impl.ServiceDAOImpl;
import dao.impl.UserDAO_Impl;
import dao.impl.UserRoleDAOImpl;
import entity.*;
import jakarta.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import services.IncidentService;
import services.ServiceService;
import services.UserRoleService;
import services.UserService;
import services.impl.IncidentServiceImpl;
import services.impl.ServiceServiceImpl;
import services.impl.UserRoleServiceImpl;
import services.impl.UserServiceImpl;

import java.util.List;

public class Main {
    private static Logger LOG = LogManager.getLogger(Main.class);

//    private static final UserService userService;
//    private static final IncidentService incidentService;
//    private static final ServiceService serviceService;
//    private static final UserRoleService userRoleService;
//    private static final Logger LOG = LogManager.getLogger(Console.class);

//    static {
//        userService = new UserServiceImpl(new UserDAO_Impl());
//        incidentService = new IncidentServiceImpl(new IncidentDAOImpl());
//        serviceService = new ServiceServiceImpl(new ServiceDAOImpl());
//        userRoleService = new UserRoleServiceImpl(new UserRoleDAOImpl());
//    }


    public static void main(String[] args) {
        LOG.info("main method started");
        String url = "jdbc:mysql://localhost:3306/hibernate_course_project";
        String user_name = "root";
        String pass = "root1";
        Flyway flyway = new Flyway();
        flyway.setDataSource(url, user_name, pass);
        flyway.migrate();


        Console console = new Console();
        console.start();

    }
}

