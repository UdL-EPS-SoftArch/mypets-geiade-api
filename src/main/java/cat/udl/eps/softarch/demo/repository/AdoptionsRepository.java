package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Adoptions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AdoptionsRepository extends CrudRepository<Adoptions, Long>, PagingAndSortingRepository<Adoptions, Long> {
}
