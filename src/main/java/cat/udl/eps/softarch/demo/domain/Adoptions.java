package cat.udl.eps.softarch.demo.domain;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
    @NotNull
    @NotBlank
    private LocalDateTime dateOfAdoption;
    @Id
    @GeneratedValue
    private int id;

    public Adoptions() {
        this.dateOfAdoption = LocalDateTime.now();
    }

}
