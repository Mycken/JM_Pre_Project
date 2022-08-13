package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД

    private static ServiceRegistry serviceRegistry;
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        Configuration configuration = null;
        try {
            Properties settings = new Properties();
            settings.put(Environment.URL, "jdbc:mysql://localhost/mytestdb?useUnicode=true&" +
                    "useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false");
            settings.put(Environment.USER, "root");
            settings.put(Environment.PASS, "120247");
            settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
            //settings.put(Environment.HBM2DDL_AUTO, "create-drop");
            // settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            //      settings.put(Environment.SHOW_SQL, "true");
            //      settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

            configuration = new Configuration()
                .setProperties(settings)
                .addAnnotatedClass(User.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            return configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return configuration.buildSessionFactory(serviceRegistry);
    }
    public static SessionFactory getSessionFactory() {
            return sessionFactory;
        }

    public static void close() throws Exception{
        if(serviceRegistry!= null) {
            StandardServiceRegistryBuilder.destroy(serviceRegistry);
        }
    }
}
