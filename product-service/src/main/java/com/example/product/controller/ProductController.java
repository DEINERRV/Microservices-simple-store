package com.example.product.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.product.entity.Product;
import com.example.product.service.ProductService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> listProduct(){
        List<Product> products = productService.listAllProduct();
        
        if(products.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
        Product product = productService.getProduct(id);
        if(product==null)
            return ResponseEntity.notFound().build();
        
        return ResponseEntity.ok(product);
    }
    
    @PostMapping
    public ResponseEntity<Void> createProduct(@Valid @RequestBody Product product, BindingResult result, UriComponentsBuilder ucb){
        if(result.hasErrors()){
            return ResponseEntity.badRequest().build();
        }

        Product createdProduct = productService.createProduct(product);
        URI locationOfNewProduct = ucb
            .path("products/{id}")
            .buildAndExpand(createdProduct.getId())
            .toUri();
        return ResponseEntity.created(locationOfNewProduct).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody Product product, BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.notFound().build();
        }

        product.setId(id);
        Product savedProduct = productService.updateProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id){
        Product deletedProduct = productService.deleteProduct(id);
        if(deletedProduct == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(deletedProduct);
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<Product> updateStockProduct(@PathVariable Long id, @RequestParam(value = "quantity", required = true) Double stock){
        Product updatedProduct = productService.updateStock(id, stock);
        if(updatedProduct == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedProduct);
    }
}
