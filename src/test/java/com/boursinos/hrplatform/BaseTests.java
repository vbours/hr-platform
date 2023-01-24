package com.boursinos.hrplatform;

import com.boursinos.hrplatform.clients.ClientsPackage;
import com.boursinos.hrplatform.model.ModelPackage;
import com.boursinos.hrplatform.repositories.RepositoryPackage;
import com.boursinos.hrplatform.service.ServicePackage;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

@RunWith(SpringRunner.class)
@Configuration
@ComponentScan(
        basePackageClasses = {
                RepositoryPackage.class,
                ServicePackage.class,
                ClientsPackage.class
        })
@EnableJpaRepositories(basePackageClasses = {RepositoryPackage.class})
@EntityScan(basePackageClasses = {ModelPackage.class})
@PropertySource("classpath:application.properties")
@Import({InternalDatabaseConfig.class})
public class BaseTests {

    private final Logger logger = Logger.getLogger(BaseTests.class);

    private static final BlockingQueue<String> REQUESTS_QUEUE = new LinkedBlockingDeque<>();

    private static Connection c = null;

    private static Statement stmt = null;

    private static String CreateSql = null;

    static int containerPort = 5432 ;
    static int localPort = 5432 ;
    static DockerImageName postgres = DockerImageName.parse("postgres:13.1");

    @ClassRule
    public static PostgreSQLContainer postgreDBContainer = new PostgreSQLContainer<>(postgres)
            .withDatabaseName("postgres")
            .withUsername("root")
            .withPassword("root")
            .withReuse(true)
            .withExposedPorts(containerPort)
            .withCreateContainerCmdModifier(cmd -> cmd.withHostConfig(
                    new HostConfig().withPortBindings(new PortBinding(Ports.Binding.bindPort(localPort), new ExposedPort(containerPort)))
            ));

    @BeforeClass
    public static void insertData() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");

        String connectionStr = "jdbc:postgresql://localhost:5432/postgres";
        c = DriverManager.getConnection(

                connectionStr,
                "root", "root");

        System.out.println("Database Connected ..");

        stmt = c.createStatement();

        CreateSql = "Create Table branch(branch_id text primary key, address text, city text, country text, createdAt timestamp, updatedAt timestamp)";

        stmt.executeUpdate(CreateSql);

        CreateSql = "Create Table employee(employee_id text primary key, firstname text, lastname text, gender text, year_of_birth int, address text, post_code text, telNumber text, contract_type text, total_holidays int, remaining_holidays int, salary int, contract_start text, createdAt timestamp, updatedAt timestamp, branch_id text)";
        stmt.executeUpdate(CreateSql);

        CreateSql = "Create Table absence(absence_id text, employee_id text, start_at timestamp, end_at timestamp, requested_days int, absence_type text, absence_status text, createdAt timestamp, updatedAt text)";
        stmt.executeUpdate(CreateSql);

        CreateSql = "Create Table overtime(overtime_id text primary key, employee_id text, overtime_day timestamp, overtime decimal, createdAt timestamp, updatedAt timestamp)";
        stmt.executeUpdate(CreateSql);

        CreateSql = "Create Table file(file_id text primary key, filename text, type text, bucket text, createdAt timestamp, updatedAt timestamp)";
        stmt.executeUpdate(CreateSql);

    }


    @Test
    public void checkConfigTest(){
        Assert.assertEquals(postgreDBContainer.getUsername(),"root");
        Assert.assertEquals(postgreDBContainer.getDatabaseName(),"postgres");
        Assert.assertTrue(postgreDBContainer.getDockerImageName().equals("postgres:13.1"));
        Assert.assertEquals(postgreDBContainer.getDriverClassName(),"org.postgresql.Driver");
        Assert.assertEquals(postgreDBContainer.getHost(),"localhost");
        Assert.assertEquals(postgreDBContainer.getPassword(),"root");
    }
}
