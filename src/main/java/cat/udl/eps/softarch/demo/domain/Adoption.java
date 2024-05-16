package cat.udl.eps.softarch.demo.domain;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Setter
@Getter
@Entity
@Data
@EqualsAndHashCode (callSuper = false)

public class Adoption {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @NotNull
    private User user;

    @OneToOne
    @NotNull
    private Pet pet;

    @NotNull
    private LocalDateTime dateOfAdoption;

    public Adoption() {
        this.dateOfAdoption = LocalDateTime.now(ZoneOffset.UTC);
    }

}
