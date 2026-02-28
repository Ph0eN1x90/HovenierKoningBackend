package com.hovenierkoning.backend.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hovenierkoning.backend.model.Address;
import com.hovenierkoning.backend.repository.AddressRepo;
import com.hovenierkoning.backend.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepo addressRepository;

    @Override
    public Address saveAddress(Address address){
        if (address == null) {
            throw new IllegalArgumentException("Address cannot be null");
        }
        return addressRepository.save(address);
    }

    @Override
    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }
    
    @Override
    public Address getAddressById(long id) {
        Optional<Address> address =  addressRepository.findById(id);
        if(address.isPresent()){
            return address.get();
        }else {
            throw new RuntimeException();
        }
    }

    @Override
    public Address updateAddress(Address address, long id) {
        Address existingAdres = addressRepository.findById(id).orElseThrow(
                ()-> new RuntimeException()
        );
        existingAdres.setStreetname(address.getStreetname());
        existingAdres.setCity(address.getCity());
        existingAdres.setHousenumber(address.getHousenumber());
        existingAdres.setZipcode(address.getZipcode());
        existingAdres.setFinished(address.getFinished());
        addressRepository.save(existingAdres);
        return existingAdres;
    }

    @Override
    public void deleteAddress(long id) {
        addressRepository.deleteById(id);
    }

    @Override
    public List<Address> getAddressesByStreetname(String streetname) {
        return addressRepository.getAddressesByStreetname(streetname);
    }
    
    @Override
    public void bulkFinishAddresses(List<Long> ids) {
        if (ids == null) {
            throw new IllegalArgumentException("IDs cannot be null");
        }
        List<Address> addresses = addressRepository.findAllById(ids);
        LocalDate today = LocalDate.now();
        
        addresses.forEach(address -> {
            // Mark address as finished
            address.setFinished(true);
            address.setDate_finished(today);
            
            // Mark all trees of this address as finished with date
            if (address.getTrees() != null && !address.getTrees().isEmpty()) {
                address.getTrees().forEach(tree -> {
                    tree.setFinished(true);
                    tree.setDate_finished(today);
                });
            }
        });
        
        addressRepository.saveAll(addresses);
    }
}