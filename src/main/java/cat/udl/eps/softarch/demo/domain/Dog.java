package cat.udl.eps.softarch.demo.domain;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Dog extends Pet {

    private Integer barkingLevel;

    @Override
    public Long getId() {
        return super.getId();
    }
}
