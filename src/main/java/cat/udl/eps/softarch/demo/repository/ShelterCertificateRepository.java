package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.ShelterCertificate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ShelterCertificateRepository extends CrudRepository<ShelterCertificate, String>, PagingAndSortingRepository<ShelterCertificate, String> {

}