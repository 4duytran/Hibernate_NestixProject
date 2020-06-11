package config;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Create a session for Hibernate
 * @author Duy Tran
 *
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    /**
     * Create connection for Hibernate with the method buildSessionFactory() of Hibernate
     * @return Hibernate SessionFactory
     */
    private static SessionFactory buildSessionFactory() {
    	// We will try connect to sql with the class of Hibernate, if have error we will show it through new exception
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    /**
     * Get connection for Hibernate
     * @return SessionFactory
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
