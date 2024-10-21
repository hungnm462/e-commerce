package vn.gs.notify.config.env;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author hungnm
 * @created 19/10/2024 - 15:23
 * @description ...
 */
@Getter
@Setter
@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE)
@ConfigurationProperties(prefix = "green-snake.mail")
public class MailEnv {

  String host;

  String port;

  String auth;

  String sslProtocols;

  String sslTrust;

  String starttls;

  String username;

  String password;
}
