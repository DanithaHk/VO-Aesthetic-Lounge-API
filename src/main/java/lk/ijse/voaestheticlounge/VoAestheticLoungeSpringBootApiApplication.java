package lk.ijse.voaestheticlounge;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@SpringBootApplication
public class VoAestheticLoungeSpringBootApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(VoAestheticLoungeSpringBootApiApplication.class, args);
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();

    }
    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("danithahk@gmail.com");
        mailSender.setPassword("gjzc awwn oqbj chyn");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
