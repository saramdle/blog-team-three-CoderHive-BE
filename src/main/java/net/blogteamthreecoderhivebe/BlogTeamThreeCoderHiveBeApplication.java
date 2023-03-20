package net.blogteamthreecoderhivebe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class BlogTeamThreeCoderHiveBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogTeamThreeCoderHiveBeApplication.class, args);
    }

}
