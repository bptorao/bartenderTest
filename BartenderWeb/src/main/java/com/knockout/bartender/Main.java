package com.knockout.bartender;

import java.util.Optional;
import org.apache.catalina.startup.Tomcat;
import org.apache.log4j.xml.DOMConfigurator;




public class Main {

    

    public static final Optional<String> PORT = Optional.ofNullable(System.getenv("PORT"));

    public static final Optional<String> HOSTNAME = Optional.ofNullable(System.getenv("HOSTNAME"));
    
    private static final String LOG4J_LOCATION = "classes/META-INF/resources/WEB-INF/log4j.xml";
    

    public static void main(String[] args) throws Exception {
    	
    	System.out.println("Starting WebAPP - Knockout Test");
    	
    	System.out.println("Configuring log4j. FileAppender: bartender.log");
    	DOMConfigurator.configure(System.getProperty("user.dir")+System.getProperty("file.separator")+LOG4J_LOCATION);
    	
    	System.out.println("Starting Tomcat for BartenderWeb App");
        String contextPath = "/" ;

        String appBase = ".";

        Tomcat tomcat = new Tomcat();   

        tomcat.setPort(Integer.valueOf(PORT.orElse("8080") ));

        tomcat.setHostname(HOSTNAME.orElse("localhost"));

        tomcat.getHost().setAppBase(appBase);

        tomcat.addWebapp(contextPath, appBase);

        tomcat.start();

        tomcat.getServer().await();
        

    }

}