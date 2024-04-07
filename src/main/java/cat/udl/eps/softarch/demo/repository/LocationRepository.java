package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Location;
import cat.udl.eps.softarch.demo.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface LocationRepository extends CrudRepository<Location, Long>, PagingAndSortingRepository<Location, Long> {
    Location findLocationByAddressAndProvinceAndCityAndPostalCode(@Param("address") String address, @Param("province") String province, @Param("city") String city, @Param("postalCode") Integer postalCode);

}