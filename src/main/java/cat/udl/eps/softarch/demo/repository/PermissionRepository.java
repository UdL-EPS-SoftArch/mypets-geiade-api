package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Permission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PermissionRepository extends CrudRepository<Permission, String>, PagingAndSortingRepository<Permission, String> {
}
