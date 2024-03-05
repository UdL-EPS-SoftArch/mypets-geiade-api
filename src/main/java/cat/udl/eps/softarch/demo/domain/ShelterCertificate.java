package cat.udl.eps.softarch.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class ShelterCertificate {

    @Id
    private Long id;

    @NotNull
    private LocalDateTime expirationDate;

}
