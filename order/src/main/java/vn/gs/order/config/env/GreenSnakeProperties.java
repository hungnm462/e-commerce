package vn.gs.order.config.env;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ConfigurationProperties(prefix = "green-snake")
public class GreenSnakeProperties {

    Url url;
    Token token;
    Mail mail;
    Algorithm algorithm;
    Endpoints endpoints;

    @Value("${green-snake.url.white-list:no,yes}")
    List<String> whiteList;

    @Getter
    @Setter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Url {
        List<String> whiteList;
        String charSplit;
    }

    @Getter
    @Setter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Token {
        String prefix;
    }

    @Getter
    @Setter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Mail {
        String host;
        int port;
        String username;
        String password;
    }

    @Getter
    @Setter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Algorithm {
        Aes aes;

        @Getter
        @Setter
        @FieldDefaults(level = AccessLevel.PRIVATE)
        public static class Aes {
            String key;
        }
    }

    @Getter
    @Setter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Endpoints {
        Cors cors;

        @Getter
        @Setter
        @FieldDefaults(level = AccessLevel.PRIVATE)
        public static class Cors {
            String maxAge;
            String allowedHeaders;
            String allowedOrigins;
            String allowedCredentials;
            String allowedMethods;
        }
    }
}
