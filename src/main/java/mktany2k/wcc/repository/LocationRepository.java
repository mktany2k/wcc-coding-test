package mktany2k.wcc.repository;

import mktany2k.wcc.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, String> {
    Optional<Location> findOneByPostcode(String postcode);
}
