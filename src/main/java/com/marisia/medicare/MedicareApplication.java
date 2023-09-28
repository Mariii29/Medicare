package com.marisia.medicare;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.marisia.medicare.model.AppUser;
import com.marisia.medicare.model.Drug;
import com.marisia.medicare.repository.AppUserRepository;
import com.marisia.medicare.repository.DrugRepository;
import com.marisia.medicare.service.storage.StorageProperties;
import com.marisia.medicare.service.storage.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class MedicareApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicareApplication.class, args);
	}

	@Bean
	CommandLineRunner prePopulateData(DrugRepository drugRepository, AppUserRepository usersRepository,
			PasswordEncoder encoder) {
		return args -> {

			var seller = new AppUser(2, "mimi", encoder.encode("123"), "USER");
			usersRepository.save(seller);
			usersRepository.save(new AppUser(1, "admin", encoder.encode("123"), "USER,ADMIN"));

			// drugRepository.save(new Drug(1L, "Panadol", 500.00f, "Treats Headaches", true, seller));
			// drugRepository.save(new Drug(2L, "Malafin", 4500.00f, "For Malaria", true, seller));
			// drugRepository.save(new Drug(3L, "Piriton", 200.00f, "Calms sinuses, and helps to sleep", false, seller));

		};
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return args -> {
			storageService.init();
		};
	}

}
