package co.com.sbaqueroa.gads;

import org.eclipse.jdt.internal.compiler.batch.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import co.com.sbaqueroa.gads.services.AssetImpl;
import co.com.sbaqueroa.gads.webservices.HomeController;

/**
 * @author sergio
 * Project Main class which contains some basic configuration
 */
@SpringBootApplication
@EnableScheduling
@ComponentScan
@EnableAutoConfiguration
@EntityScan(basePackages={"co.com.sbaqueroa.gads.model.implementation"})

public class GdasApplication extends SpringBootServletInitializer{

	private static final Logger logger = LoggerFactory.getLogger(GdasApplication.class);
	
	/**
	 * Runs the Spring App.
	 * @param args Arguments to evaluate.
	 */
	public static void main(String[] args) {
		SpringApplication.run(GdasApplication.class, args);
		logger.info("starting App");
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.boot.builder.SpringApplicationBuilder
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Main.class);
	}
	
	/**
	 * @return Bean of type ViewResolver to manage JSTL Views.
	 */
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.ViewResolver
	 */
	@Bean
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		return resolver;
	}
	
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
