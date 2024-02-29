package cat.udl.eps.softarch.demo.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class ShelterCertificate {
    @OneToOne
    @NotNull
    @JsonIdentityReference(alwaysAsId = true)
    private Shelter shelter;

    @Id
    private Long id;

    @NotNull
    private LocalDateTime expirationDate;

}
