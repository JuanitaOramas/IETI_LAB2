package org.adaschool.api.controller.product;

import org.adaschool.api.exception.ProductNotFoundException;
import org.adaschool.api.exception.UserNotFoundException;
import org.adaschool.api.repository.product.Product;
import org.adaschool.api.repository.product.ProductDto;
import org.adaschool.api.repository.user.User;
import org.adaschool.api.repository.user.UserDto;
import org.adaschool.api.service.product.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/products/")
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController(@Autowired ProductsService productsService) {
        this.productsService = productsService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto) {
        Product productCreated = productsService.save(new Product(productDto));
        URI createdProductUri = URI.create("/v1/products/" + productCreated.getId());
        return ResponseEntity.created(createdProductUri).body(productCreated);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productsService.all());
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") String id) {
        Optional<Product> product = productsService.findById(id);
        if (product.isPresent()) return ResponseEntity.ok(product.get());
        throw new ProductNotFoundException(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") String id, @RequestBody ProductDto productDto) {
        Optional<Product> product = productsService.findById(id);
        if (!product.isPresent()== true ){
            throw new ProductNotFoundException(id);
        }
        productsService.update(new Product(productDto),id);
        return ResponseEntity.ok(productsService.save(product.get()));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") String id) {
        Optional<Product> product = productsService.findById(id);
        if (product.isPresent()== true ){
            productsService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        throw new ProductNotFoundException(id);
    }
}
