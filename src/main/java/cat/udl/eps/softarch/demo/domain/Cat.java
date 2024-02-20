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
public class Cat extends Pet {

    private Integer meowingLevel;

    @Override
    public Long getId() {
        return super.getId();
    }

}
