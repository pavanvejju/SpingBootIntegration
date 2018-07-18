package com.wc;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
//@EnableEurekaServer
//@EnableEurekaClient
public class Application  {
	public static void main(String args[]){
	//	SpringApplication.run(Application.class, args);
		
		
		ConfigurableApplicationContext configurableApplicationContext	=	new SpringApplicationBuilder(Application.class)
				.properties("spring.config.name:application,conf", 
						    "spring.config.location:classpath:/external/properties/application/,classpath:/external/properties/configuration/")
				.build().run(args);
 
		
		ConfigurableEnvironment configurableEnvironment	=	configurableApplicationContext.getEnvironment();
		configurableEnvironment.setActiveProfiles("dev");
		System.out.println(configurableEnvironment.getProperty("app.firstName"));
		System.out.println(configurableEnvironment.getProperty("conf.lastName"));
		System.out.println(configurableEnvironment.getActiveProfiles()[0]);
		System.out.println(configurableEnvironment.getProperty("spring.datasource.url"));
		
		
	}
	
	/*@Override
	 public void onStartup(ServletContext servletContext) throws ServletException {
		System.out.println("on startup");
	        servletContext.setInitParameter("spring.profiles.active", "qa");
	    }*/
}
