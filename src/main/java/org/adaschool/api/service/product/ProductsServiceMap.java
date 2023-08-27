package org.adaschool.api.service.product;

import org.adaschool.api.repository.product.Product;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsServiceMap implements ProductsService {

    HashMap<Integer, Product> productStore = new HashMap<>();
    static Integer id = 1;

    @Override
    public Product save(Product product) {
        productStore.put(id,product);
        id++;
        return product;
    }

    @Override
    public Optional<Product> findById(String id) {
        return Optional.of(productStore.get(Integer.valueOf(id)));
    }

    @Override
    public List<Product> all() {
        return (List<Product>) productStore;
    }

    @Override
    public void deleteById(String id) {
        productStore.remove(Integer.valueOf(id));
    }

    @Override
    public Product update(Product product, String productId) {
        productStore.put(Integer.valueOf(id),product);
        return product;
    }
}
