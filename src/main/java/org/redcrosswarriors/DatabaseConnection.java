/*
Assignment #5 Singleton design pattern.
This class will provide a single instance of our entity manager.
Which will allow only a single connection to our database througout the program.
 */
package org.redcrosswarriors;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

public class DatabaseConnection {
    // Used to make queries to the database based on raw SQL queries or our entities in the model package
    // need to ensure we only have one instance of the entity manager
    private EntityManager manager;

    // used to store our database connection info
    private DataSource dataSource;

    // instance of our singleton database connection
    private static DatabaseConnection connection;

    // database connection information.
    private static final String databaseURL = "jdbc:mysql://localhost:3306/testdb";
    private static final String databasePassword = "admin";
    private static final String databaseUserName = "root";

    public DatabaseConnection(){
        // initialize our data source
        initDataSource();
        // used to create a new Entity manager factory
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        //set path to our entities
        factoryBean.setPackagesToScan("org.redcrosswarriors.model");
        factoryBean.setPersistenceUnitName("redCrossWarriors");
        // set the datasource (the url, username, and password of our database)
        factoryBean.setDataSource(dataSource);
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.afterPropertiesSet();

        EntityManagerFactory factory = factoryBean.getNativeEntityManagerFactory();
        // set our entity manager by getting a new entity manager from the factory.
        manager = factory.createEntityManager();

    }

    public EntityManager getManager(){
        return this.manager;
    }

    // ensure that there is only one database connection instance
    // this way they all share the same datasource and entity manager
    // and there is only one active connection to our database
    public static DatabaseConnection getDatabaseConnection(){
        if(connection == null){
            connection = new DatabaseConnection();
        }
        return connection;
    }

    private void initDataSource(){
        DriverManagerDataSource driver = new DriverManagerDataSource(
                databaseURL, databaseUserName, databasePassword
        );
        this.dataSource = driver;
    }
}
