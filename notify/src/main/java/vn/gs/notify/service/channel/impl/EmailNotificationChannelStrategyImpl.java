package vn.gs.notify.service.channel.impl;

import java.util.Objects;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import vn.gs.notify.config.env.MailEnv;
import vn.gs.notify.dto.event.NotificationMultiContentDto;
import vn.gs.notify.enums.NotificationChannelEnum;
import vn.gs.notify.repository.NotificationRepository;
import vn.gs.notify.service.channel.NotificationChannelStrategy;

/**
 * @author hungnm
 * @created 19/10/2024 - 18:52
 * @description ...
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailNotificationChannelStrategyImpl
    implements NotificationChannelStrategy {

  private final MailEnv mailEnv;

  private final NotificationRepository notificationRepository;

  @Override
  public NotificationChannelEnum getChannel() {
    return NotificationChannelEnum.EMAIL;
  }

  @Override
  public void send(@NonNull NotificationMultiContentDto dto) {

    val urlImgParam = "urlImg";
    val props = this.getProperties();
    val session = Session.getInstance(props, this.getAuthenticator());
    session.setDebug(false);

    try {
      MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress(mailEnv.getUsername()));

      Address[] recipients = new Address[dto.getTos().size()];
      for (int i = 0; i < dto.getTos().size(); i++) {
        recipients[i] = new InternetAddress(dto.getTos().get(i));
      }

      message.setRecipients(Message.RecipientType.TO, recipients);
      message.setSubject(dto.getSubject(), "utf-8");

      val urlImg = dto.getParams().get(urlImgParam);
      val content = dto.getContent(this.getChannel());

      if (Objects.nonNull(urlImg)) {
        MimeMultipart multipart = new MimeMultipart("related");
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(content, "text/html;charset=UTF-8");
        multipart.addBodyPart(messageBodyPart);
        messageBodyPart = new MimeBodyPart();
        DataSource fds = new FileDataSource(String.valueOf(urlImg));
        messageBodyPart.setDataHandler(new DataHandler(fds));
        messageBodyPart.setHeader("Content-ID", "<image>");

        // add image to the multipart
        multipart.addBodyPart(messageBodyPart);

        message.setContent(multipart);
      } else {
        message.setContent(content, "text/html;charset=UTF-8");
      }

      Transport.send(message);

      log.info(
          "SEND MAIL SUCCESS: from: {}, to: {}, subject: {}, content: {}",
          mailEnv.getUsername(),
          dto.getTos(),
          dto.getSubject(),
          dto.getContent(this.getChannel())
      );
    } catch (MessagingException e) {
      log.info(
          "SEND MAIL ERROR: from: {}, to: {}, subject: {}, content: {}",
          mailEnv.getUsername(),
          dto.getTos(),
          dto.getSubject(),
          dto.getContent(this.getChannel())
      );
      throw new RuntimeException(e);
    }
  }

  //////////////////////////////////////
  ////        PRIVATE METHOD        ////
  //////////////////////////////////////

  private Properties getProperties() {
    val props = new Properties();
    props.put("mail.smtp.host", mailEnv.getHost());
    props.put("mail.smtp.port", mailEnv.getPort());
    props.put("mail.smtp.auth", mailEnv.getAuth());
    props.put("mail.smtp.ssl.protocols", mailEnv.getSslProtocols());
    props.put("mail.smtp.ssl.trust", mailEnv.getSslTrust());
    props.put("mail.smtp.starttls.enable", mailEnv.getStarttls());

    return props;
  }

  private Authenticator getAuthenticator() {
    return new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(mailEnv.getUsername(), mailEnv.getPassword());
      }
    };
  }
}
