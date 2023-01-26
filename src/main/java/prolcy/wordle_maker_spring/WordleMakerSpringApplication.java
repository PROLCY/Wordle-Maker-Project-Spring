package prolcy.wordle_maker_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WordleMakerSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(WordleMakerSpringApplication.class, args);
    }

}
