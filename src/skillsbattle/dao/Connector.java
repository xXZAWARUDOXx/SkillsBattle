package skillsbattle.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import skillsbattle.model.Game;
import skillsbattle.model.Player;

public class Connector {

    private static Connector instance;
    private SessionFactory sessionFactory;
    private Configuration configuration;

    private Connector() {
        this.configuration = new Configuration();
    }

    public static Connector getInstance() {
        if (instance == null) {
            instance = new Connector();
        }
        return instance;
    }

    public void setConfiguration() {
        configuration = new Configuration();
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/fiveinarow");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "1234");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialect");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.addAnnotatedClass(Player.class);
        configuration.addAnnotatedClass(Game.class);

        //sessionFactory = configuration.buildSessionFactory();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(builder.build());
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void closeConnection() {
        this.sessionFactory.close();
    }
}
