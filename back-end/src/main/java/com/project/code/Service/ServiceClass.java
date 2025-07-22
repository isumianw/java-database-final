package com.project.code.Service;

import com.project.code.Model.Inventory;
import com.project.code.Model.Product;
import com.project.code.Repo.InventoryRepository;
import com.project.code.Repo.ProductRepository;

import org.springframework.stereotype.Service;

@Service
public class ServiceClass {

    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;

    public ServiceClass(ProductRepository productRepository, InventoryRepository inventoryRepository) {
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
    }

    public boolean validateInventory(Inventory inventory) {
        Inventory result = inventoryRepository.findByProductIdAndStoreId(inventory.getProduct().getId(), inventory.getStore().getId());
        if (result != null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean validateProduct(Product product) {
        Product result = productRepository.findByName(product.getName());
        if (result != null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean validateProductId(Long id) {
        Product result = productRepository.findById(id);

        if (result == null) {
            return false;
        } else {
            return true;
        }
    }

    public Inventory getInventoryId(Inventory inventory) {
        Inventory result = inventoryRepository.findByProductIdAndStoreId(inventory.getProduct().getId(), inventory.getStore().getId());
        return result;
    }
}
