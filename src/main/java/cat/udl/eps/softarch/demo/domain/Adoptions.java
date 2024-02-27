package cat.udl.eps.softarch.demo.domain;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;


@Setter
@Getter
@Entity
@Data
@EqualsAndHashCode (callSuper = false)

public class Adoptions {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @NotNull
    @NotBlank
    private User user;

    @OneToOne
    @NotNull
    @NotBlank
    private Pet pet;

    @NotNull
    @NotBlank
    private LocalDateTime dateOfAdoption;

    public Adoptions (User user, Pet pet) {
        this.user = user;
        this.pet = pet;
        this.dateOfAdoption = LocalDateTime.now();
    }
}
