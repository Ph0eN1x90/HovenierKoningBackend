package com.hovenierkoning.backend.controller;

import java.util.List;
import java.util.stream.Collectors;
import jakarta.validation.Valid;
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
import com.hovenierkoning.backend.dto.AddressDTO;
import com.hovenierkoning.backend.dto.BulkFinishRequest;
import com.hovenierkoning.backend.mapper.AddressMapper;
import com.hovenierkoning.backend.model.Address;
import com.hovenierkoning.backend.service.AddressService;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private AddressService addressService;
    
    @Autowired
    private AddressMapper addressMapper;

    @PostMapping("/save")
    public ResponseEntity<AddressDTO> saveAddress(@RequestBody @Valid AddressDTO addressDTO){
        System.out.println("saveAdres() called with: " + addressDTO);
        Address address = addressMapper.toEntity(addressDTO);
        Address savedAddress = addressService.saveAddress(address);
        return new ResponseEntity<>(addressMapper.toDTO(savedAddress), HttpStatus.CREATED);
    }

    @GetMapping("/")	
    public List<AddressDTO> getAllAdressen(){
        // System.out.println("getAllAdressen() called" + addressService.getAllAddress());
        return addressService.getAllAddress().stream()
                .map(addressMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable("id") long addressId){
        Address address = addressService.getAddressById(addressId);
        return new ResponseEntity<>(addressMapper.toDTO(address), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable("id") long addressId,
                                                   @RequestBody @Valid AddressDTO addressDTO){
        Address address = addressMapper.toEntity(addressDTO);
        Address updatedAddress = addressService.updateAddress(address, addressId);
        return new ResponseEntity<>(addressMapper.toDTO(updatedAddress), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable("id") long addressId){
        addressService.deleteAddress(addressId);
        return new ResponseEntity<>("Adres deleted successfully.",HttpStatus.OK);
    }

    @GetMapping("/street/{streetname}")
    public List<AddressDTO> findAllByName(@PathVariable("streetname") String streetname){
        System.out.println("getAdressesByName() called with straatnaam: " + streetname);
        return addressService.getAddressesByStreetname(streetname).stream()
                .map(addressMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @PutMapping("/bulk-finish")
    public ResponseEntity<String> bulkFinishAddresses(@RequestBody @Valid BulkFinishRequest request){
        System.out.println("bulkFinishAddresses() called with IDs: " + request.getIds());
        addressService.bulkFinishAddresses(request.getIds());
        return new ResponseEntity<>("Addresses marked as finished successfully.", HttpStatus.OK);
    }

}