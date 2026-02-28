package com.hovenierkoning.backend.event;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hovenierkoning.backend.model.Address;
import com.hovenierkoning.backend.model.Tree;
import com.hovenierkoning.backend.repository.AddressRepo;

@Component
public class TreeEventListener {

    @Autowired
    private AddressRepo addressRepository;
    
    @EventListener
    @Transactional
    public void handleTreeSaved(TreeSavedEvent event) {
        Tree tree = event.getTree();
        
        if (tree.getAddress() != null && tree.getAddress().getId() != null) {
            long addressId = tree.getAddress().getId();
            
            // Reload address from database to get the trees collection
            Address address = addressRepository.findById(addressId).orElse(null);
            
            if (address != null && address.getTrees() != null && !address.getTrees().isEmpty()) {
                // Check of alle trees van dit address finished zijn
                boolean allFinished = address.getTrees().stream()
                    .allMatch(t -> t.getFinished() != null && t.getFinished());
                
                // Update address.finished and date_finished to match actual tree status
                if (address.getFinished() != allFinished) {
                    address.setFinished(allFinished);
                    // Set date_finished only when all trees are finished
                    if (allFinished) {
                        address.setDate_finished(LocalDate.now());
                    } else {
                        address.setDate_finished(null);
                    }
                    addressRepository.save(address);
                }
            }
        }
    }
    
    @EventListener
    @Transactional
    public void handleTreeDeleted(TreeDeletedEvent event) {
        Long addressId = event.getAddressId();
        
        if (addressId != null) {
            long nonNullAddressId = addressId;
            // Reload address from database to get the remaining trees
            Address address = addressRepository.findById(nonNullAddressId).orElse(null);
            
            if (address != null) {
                // Check if there are remaining trees
                if (address.getTrees() == null || address.getTrees().isEmpty()) {
                    // No trees left, set address to not finished
                    if (address.getFinished()) {
                        address.setFinished(false);
                        address.setDate_finished(null);
                        addressRepository.save(address);
                    }
                } else {
                    // Check if all remaining trees are finished
                    boolean allFinished = address.getTrees().stream()
                        .allMatch(t -> t.getFinished() != null && t.getFinished());
                    
                    // Update address.finished and date_finished to match actual tree status
                    if (address.getFinished() != allFinished) {
                        address.setFinished(allFinished);
                        if (allFinished) {
                            address.setDate_finished(LocalDate.now());
                        } else {
                            address.setDate_finished(null);
                        }
                        addressRepository.save(address);
                    }
                }
            }
        }
    }
}
