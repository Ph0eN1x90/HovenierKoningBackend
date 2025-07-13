package com.hovenierkoning.backend.service.impl;

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
        //check
        addressRepository.findById(id).orElseThrow(()-> new RuntimeException());
        //delete
        addressRepository.deleteById(id);
    }

    @Override
    public List<Address> getAddressesByStreetname(String streetname) {
        return addressRepository.getAddressesByStreetname(streetname);
    }
}