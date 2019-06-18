package cz.homecredit.assignment.config;

import static com.google.common.base.Predicates.not;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    public static final String TAG_USERS = "user";
    private static final Tag USERS = new Tag(TAG_USERS, "Operations related to User domain");

    @Bean
    public Docket publicApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .tags(USERS)
                .groupName("public")
                .apiInfo(publicApiInfo())
                .useDefaultResponseMessages(false)
                .select()
                // exclude all the Spring Controllers (such as Error, Management, etc.)
                .apis(not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
                .build();
    }

    private ApiInfo publicApiInfo() {
        return new ApiInfoBuilder()
                .title("Basic API")
                .description("The Basic API is a RESTful API that provides functionality for JSONPlaceholder.typicode."
                        + " You can obtain user's todo list with additional information about user")
                .version("1.0")
                .build();
    }
}
