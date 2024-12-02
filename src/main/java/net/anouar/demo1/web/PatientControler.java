package net.anouar.demo1.web;


import net.anouar.demo1.entities.Patient;
import net.anouar.demo1.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
public class PatientControler {

    @Autowired
    PatientRepository repository;


    @GetMapping("/index")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "size" , defaultValue = "5") int size,
                        @RequestParam(name = "keyword" , defaultValue = "") String keyword){
        //Page<Patient> patientList = repository.findAll(PageRequest.of(page,size));
        Page<Patient> patientList = repository.findByNameContainsIgnoreCase(keyword, PageRequest.of(page,size));
        model.addAttribute("patientList" , patientList.getContent());
        model.addAttribute("pages" ,new int[patientList.getTotalPages()]);
        model.addAttribute("CurrentPage" ,page);
        model.addAttribute("keyword" ,keyword);
        return "Patient";
    }
    @GetMapping("/deletepatient")
    public String delete(@RequestParam(name = "id") Long id){
        repository.deleteById(id);
        return "redirect:/index";
    }

    @GetMapping("/reindex")
    public String Start(){
        Patient p1 = Patient.builder().name("Anoir").date(new Date()).malade(true).build();
        repository.save(p1);
        Patient p2 = Patient.builder().name("Zer").date(new Date()).malade(false).build();
        repository.save(p2);
        return "redirect:/index";
    }

    @GetMapping("/restart")
    public String reStart(){
        Patient p1 = Patient.builder().name("Anoir").date(new Date()).malade(true).build();
        repository.save(p1);
        Patient p2 = Patient.builder().name("Zer").date(new Date()).malade(false).build();
        repository.save(p2);
        return "redirect:/index";
    }

    @GetMapping("/patients")
    @ResponseBody
    public List<Patient> getAllPatients() {
        return repository.findAll();
    }
}
