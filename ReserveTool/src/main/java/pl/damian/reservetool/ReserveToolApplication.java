package pl.damian.reservetool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@EntityScan(basePackageClasses = { 
		ReserveToolApplication.class,
		Jsr310JpaConverters.class 
})
public class ReserveToolApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReserveToolApplication.class, args);
	}

}
