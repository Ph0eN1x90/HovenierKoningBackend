package com.hovenierkoning.backend.event;

public class TreeDeletedEvent {
    private final Long addressId;
    
    public TreeDeletedEvent(Long addressId) {
        this.addressId = addressId;
    }
    
    public Long getAddressId() {
        return addressId;
    }
}
