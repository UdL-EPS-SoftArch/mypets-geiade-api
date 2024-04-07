package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Pet;
import cat.udl.eps.softarch.demo.domain.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RoleRepository extends CrudRepository<Role, Long>, PagingAndSortingRepository<Role, Long> {
}
