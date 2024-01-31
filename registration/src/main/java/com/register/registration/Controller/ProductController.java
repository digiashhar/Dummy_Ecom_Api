package com.register.registration.Controller;

import com.register.registration.Service.ServiceImpl.ProductService;
import com.register.registration.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);

        if (product != null) {
            return ResponseEntity.ok(product); // OK (200) response with the product as the body
        } else {
            String message = "Product with ID " + id + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message); // Not Found (404) response with a message
        }
    }



    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product newProduct) {
        Product savedProduct = productService.createProduct(newProduct);
        return ResponseEntity.ok(savedProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        Product product = productService.updateProduct(id, updatedProduct);

        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            String message = "Product with ID " + id + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }


    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
