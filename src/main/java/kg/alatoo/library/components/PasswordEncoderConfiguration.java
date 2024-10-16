package kg.alatoo.library.components;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class PasswordEncoderConfiguration {

    @Bean
    public PasswordEncoder bCryptPassword(){
        return new BCryptPasswordEncoder();
    }
}
