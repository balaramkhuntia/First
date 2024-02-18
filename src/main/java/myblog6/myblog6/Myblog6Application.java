package myblog6.myblog6;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Myblog6Application {

	public static void main(String[] args) {
		SpringApplication.run(Myblog6Application.class, args);
	}
	@Bean
 public ModelMapper modelMapper(){

		return new ModelMapper();

 }

}
