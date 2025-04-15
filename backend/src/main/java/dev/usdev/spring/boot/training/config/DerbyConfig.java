package dev.usdev.spring.boot.training.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.apache.derby.drda.NetworkServerControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author borisi
 */
@Configuration
@Slf4j
public class DerbyConfig {

    private NetworkServerControl serverControl;

    @Value("${bi.app.springTraining.networkmode}")
    private boolean networkEnabled;

    @Autowired
    private DataSource dataSource;

    private Connection connection;

    @Bean
    public Connection getConnection() throws SQLException {
        connection = dataSource.getConnection();
        return connection;
    }

    @PostConstruct
    public void startDerby() throws Exception {
        connection = dataSource.getConnection();
        checkResources();
        log.info("Derby server started and connection established.");
    }

    @Bean
    public CommandLineRunner startDerbyServer() {
        if (networkEnabled) {
            return (args) -> {
                try {
                    serverControl = new NetworkServerControl(InetAddress.getByName("localhost"), 1527);
                    serverControl.start(null);
                    log.info("Derby network server started at localhost:1527");
                } catch (Exception e) {
                    log.error("Error starting Derby DB in NET mode: ", e);
                }
            };
        } else {
            //TODO: Fix no network DB instance
            return args -> {
                try {
                    serverControl = new NetworkServerControl(InetAddress.getByName("localhost"), 1527);
                    log.info("Derby network server started at localhost:1527");
                } catch (Exception e) {
                    log.error("Error starting Derby DB in NET mode: ", e);
                }
            };
        }
    }

    @PreDestroy
    public void shutDownDerby() {
        log.info("Shutting down Derby DB");
        try {
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
        } catch (SQLException e) {
//            if (!e.getSQLState().equals("XJ015")) {
//                log.debug("Error stopping derby database", e);
//            }
        }
        log.info("Derby DB shut down successful.");
    }

    private void checkResources() throws SQLException {
        log.info("Checking resources before starting Derby...");

        ResultSet rs = null;
        try (Connection conn = dataSource.getConnection()) {
            // Example check: Perform a test query
            rs = conn.createStatement().executeQuery("SELECT 1 FROM SYSIBM.SYSDUMMY1");
//            if (rs.next()) {
//                log.info("Database derby available");
//            }
            log.info("Resource check passed. Database is available.");
        } catch (SQLException e) {
            log.error("Resource check failed", e);
            throw e;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
        }
    }
}
