package com.hovenierkoning.backend.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import com.hovenierkoning.backend.model.Address;

public interface AddressRepo extends JpaRepository<Address, Long> {

    List<Address> getAddressesByStreetname(@Param("streetname") String streetname);

}
