package core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"core"})
public class SNSAppInitializer
        extends SpringBootServletInitializer {

    // Comment out this to run in local
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SNSAppInitializer.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SNSAppInitializer.class, args);
    }
}

