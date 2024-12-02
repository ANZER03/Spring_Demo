package net.anouar.demo1;

import net.anouar.demo1.entities.Patient;
import net.anouar.demo1.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class Demo1ApplicationTests {

    @Autowired
    private PatientRepository repository;

    @Test
    void testStartMethod() {
        // Clear the repository before the test
        repository.deleteAll();

        // Create and save patients
        Patient p1 = Patient.builder().name("anoir").date(new Date()).malade(true).build();
        Patient p2 = Patient.builder().name("Zer").date(new Date()).malade(false).build();
        repository.save(p1);
        repository.save(p2);

        // Verify patients are saved
        List<Patient> patients = repository.findAll();
        assertEquals(2, patients.size());

        // Delete one patient
        repository.delete(p2);

        // Verify the patient is deleted
        List<Patient> remainingPatients = repository.findAll();
        assertEquals(1, remainingPatients.size());
        assertTrue(remainingPatients.contains(p1));
    }
}
