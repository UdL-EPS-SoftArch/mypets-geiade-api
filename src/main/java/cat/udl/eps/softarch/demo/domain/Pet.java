package cat.udl.eps.softarch.demo.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Pet extends UriEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    @NotBlank
    private String chip;

    @NotBlank
    private String name;

    private LocalDate dateOfBirth;

    private boolean isAdopted;

    private String colour;

    private Integer size;

    private String sex;

    private String race;

    private boolean isDangerous;

    @OneToOne
    @JsonIdentityReference(alwaysAsId = true)
    private Adoption adoption;
    
    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    private Shelter shelter;

}
