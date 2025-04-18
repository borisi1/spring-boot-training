package dev.usdev.spring.boot.training.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.h2.tools.Server;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.sql.SQLException;

@Configuration
@Profile({"dev", "prod"})
public class H2Config {

    private Server tcpServer;

    @PostConstruct
    public void startTcpServer() throws SQLException {
        this.tcpServer = Server.createTcpServer(
                "-tcp", "-tcpAllowOthers", "-tcpPort", "9092").start();
        System.out.println("H2 TCP server started on port 9092");
    }

    @PreDestroy
    public void stopTcpServer() {
        if (this.tcpServer != null) {
            this.tcpServer.stop();
            System.out.println("H2 TCP server stopped");
        }
    }
}
