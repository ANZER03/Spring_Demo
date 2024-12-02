package net.anouar.demo1.entities;

import jakarta.persistence.*;

import lombok.*;
import java.util.Date;

@Entity
@Table(name = "Patient")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Builder
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String name;
    private boolean malade;
    private Date date;
}
