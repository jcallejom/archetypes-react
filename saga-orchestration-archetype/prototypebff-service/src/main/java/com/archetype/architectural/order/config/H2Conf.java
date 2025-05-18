package com.archetype.architectural.order.config;

import org.h2.tools.Server;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class H2Conf {
//    private Server webServer;
//
//    private org.h2.tools.Server tcpServer;
//
//    @EventListener(org.springframework.context.event.ContextRefreshedEvent.class)
//    public void start() throws java.sql.SQLException {
//        this.webServer =Server.createWebServer("-webPort", "8070", "-tcpAllowOthers").start();
//        this.tcpServer = org.h2.tools.Server.createTcpServer("-tcpPort", "8088", "-tcpAllowOthers").start();
//    }
//
//    @EventListener(org.springframework.context.event.ContextClosedEvent.class)
//    public void stop() {
//        this.tcpServer.stop();
//        this.webServer.stop();
//    }    
}
