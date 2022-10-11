import entity.*;
import jakarta.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    private static Logger LOG = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        LOG.info("main method started");

        //        Flyway flyway = Flyway.configure().dataSource(url,user_name,pass).load();
        //        flyway.migrate();

        Console console = new Console();
        console.start();


//        String url = "jdbc:mysql://localhost:3306/hibernate_course_project";
//        String user_name = "root";
//        String pass = "root1";


//        SessionFactory sessionFactory = new Configuration()
//                .configure("hibernate.cfg.xml")
//                .buildSessionFactory();
//
//        Flyway flyway = Flyway.configure().dataSource(url,user_name,pass).load();
//        flyway.migrate();

//        Session session = sessionFactory.getCurrentSession();
//        session.beginTransaction();
//
//
//        Query query = session.createQuery("from User where userName=:userName", User.class);
//        query.setParameter("userName", "mar_an");
//        User result = (User) query.getSingleResult();
//        System.out.println(result.getPassword());
//        System.out.println(result.getUserName());


        //-----------------------------------------------------------

//        sessionFactory = new Configuration()
//                .configure("hibernate.cfg.xml")
//                .buildSessionFactory();
//
//        session = sessionFactory.getCurrentSession();
//        session.beginTransaction();
//        UserRole userRole = session.get(UserRole.class, 1);
//        Profile profile = new Profile("Ivan", "ivanov", "fhfh@.com", "651235664", 546521);
//        User user1 = new User("vanja", "jfdjf", userRole, profile);
//        session.persist(user1);
//        session.getTransaction().commit();

        //-----------------------------------------------------------
//        sessionFactory = new Configuration()
//                .configure("hibernate.cfg.xml")
//                .buildSessionFactory();
//
//        session = sessionFactory.getCurrentSession();
//        session.beginTransaction();
//        session.remove(session.get(User.class, 3));
////        session.remove(session.get(User.class, 2));
//        session.getTransaction().commit();
        //-----------------------------------------------------------

//        sessionFactory = new Configuration()
//                .configure("hibernate.cfg.xml")
//                .addAnnotatedClass(User.class)
//                .buildSessionFactory();
//
//        session = sessionFactory.getCurrentSession();
//        session.beginTransaction();
//        UserRole userRole = session.get(UserRole.class, 1);
//        Profile profile = new Profile("Ivan", "ivanov", "fhfh@.com", "651235664", 546521);
//        User user1 = new User("vanja", "jfdjf", userRole, profile);
//        Incident incident = new Incident("bla_service", true,"big problem",user1);
//        session.persist(incident);
//        session.getTransaction().commit();


        //-----------------------------------------------------------

//        sessionFactory = new Configuration()
//                .configure("hibernate.cfg.xml")
//                .buildSessionFactory();
//
//        session = sessionFactory.getCurrentSession();
//        session.beginTransaction();
//        session.remove(session.get(User.class, 5));
//        session.getTransaction().commit();

        //-----------------------------------------------------------
//        sessionFactory = new Configuration()
//                .configure("hibernate.cfg.xml")
//                .buildSessionFactory();

//        session = sessionFactory.getCurrentSession();
//        session.beginTransaction();
//        session.remove(session.get(Incident.class, 3));
//        session.getTransaction().commit();
        //-----------------------------------------------------------


    }
}
