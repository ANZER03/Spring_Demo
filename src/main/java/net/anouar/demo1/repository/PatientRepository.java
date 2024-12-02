package net.anouar.demo1.repository;
import net.anouar.demo1.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {


    Page<Patient> findByNameContainsIgnoreCase(String keyword, Pageable pageable);
}
