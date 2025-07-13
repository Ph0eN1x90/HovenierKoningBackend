package com.hovenierkoning.backend.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hovenierkoning.backend.model.Address;
import com.hovenierkoning.backend.service.AddressService;

@RestController
// @CrossOrigin(origins = "http://localhost:9000") // Allow requests from the frontend
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private AddressService  addressService;

    @PostMapping("/save")
    public ResponseEntity<Address> saveAddress(@RequestBody Address address){
        System.out.println("saveAdres() called with: " + address);
        return new ResponseEntity<>(addressService.saveAddress(address),HttpStatus.CREATED);
    }

    @GetMapping("/")	
    public List<Address> getAllAdressen(){
        // System.out.println("getAllAdressen() called" + addressService.getAllAddress());
        return addressService.getAllAddress();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable("id") long addressId){
        return new ResponseEntity<>(addressService.getAddressById(addressId),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable("id") long addressId,
                                                   @RequestBody Address address){
        return new ResponseEntity<>(addressService.updateAddress(address,addressId),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable("id") long addressId){
        addressService.deleteAddress(addressId);
        return new ResponseEntity<>("Adres deleted successfully.",HttpStatus.OK);
    }

    @GetMapping("/street/{streetname}")
    public List<Address> findAllByName(@PathVariable("streetname") String streetname){
        System.out.println("getAdressesByName() called with straatnaam: " + streetname);
        return addressService.getAddressesByStreetname(streetname);
    }

}