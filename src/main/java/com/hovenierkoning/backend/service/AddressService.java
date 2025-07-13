package com.hovenierkoning.backend.service;

import java.util.List;
import com.hovenierkoning.backend.model.Address;

public interface AddressService {
    
    Address saveAddress(Address address);
    List<Address> getAllAddress();
    Address getAddressById(long id);
    List<Address> getAddressesByStreetname(String streetName);
    Address updateAddress(Address address,long id);
    void deleteAddress(long id);

}
