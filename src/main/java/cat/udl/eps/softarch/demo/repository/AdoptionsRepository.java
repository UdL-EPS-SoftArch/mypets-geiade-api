package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Adoptions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AdoptionsRepository extends CrudRepository<Adoptions, String>, PagingAndSortingRepository<Adoptions, String> {
}
