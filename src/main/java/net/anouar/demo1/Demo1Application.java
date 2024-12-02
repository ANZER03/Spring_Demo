package net.anouar.demo1;

import net.anouar.demo1.entities.Patient;
import net.anouar.demo1.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;

//@SpringBootApplication
@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class Demo1Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo1Application.class, args);
    }

    @Bean
    public CommandLineRunner start(PatientRepository repository) {
        return args -> {
            Patient p1 = Patient.builder().name("anoir").date(new Date()).malade(true).build();

            repository.save(p1);
            Patient p2 = Patient.builder().name("Zer").date(new Date()).malade(false).build();

            repository.save(p2);

            List<Patient> patients = repository.findAll();
            patients.forEach(patient -> System.out.println(patient.toString()));
            //repository.delete(p2);
            System.out.println("#################################");

//            List<Patient> patients_2 = repository.findAll();
//            patients_2.forEach(patient -> System.out.println(patient.toString()));
        };

    }
}
