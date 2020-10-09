package com.soaint.exam.controller;

import com.soaint.exam.entities.Product;
import com.soaint.exam.model.ErrorObject;
import com.soaint.exam.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
@Log4j2
public class ProductRestController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            List<Product> products = new ArrayList<>();
            productRepository.findAll().forEach(products::add);

            if (products.isEmpty()) {
                log.debug("{}", ErrorObject.builder().httpStatus(HttpStatus.NO_CONTENT.name()).code(HttpStatus.NO_CONTENT.value()).message("No se encontraron productos").backendMessage("No se encontraron productos en el sistema").build().toString());
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            log.error("{}", ErrorObject.builder().httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.name()).code(HttpStatus.INTERNAL_SERVER_ERROR.value()).message("Error al cargar los productos").backendMessage(e.getMessage()).build().toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") long id) {
        Product product = productRepository.findOne(id);

        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            log.debug("{}", ErrorObject.builder().httpStatus(HttpStatus.NOT_FOUND.name()).code(HttpStatus.NOT_FOUND.value()).message("No se encontró el producto").backendMessage("No se encontró el producto en el sistema").build().toString());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        try {
            Product _product = productRepository
                    .save(Product.builder().name(product.getName()).price(product.getPrice()).build());
            return new ResponseEntity<>(_product, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("{}", ErrorObject.builder().httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.name()).code(HttpStatus.INTERNAL_SERVER_ERROR.value()).message("Error al grabar el producto").backendMessage(e.getMessage()).build().toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
        Product productFinded = productRepository.findOne(id);

        if (productFinded != null) {
            productFinded.setName(product.getName());
            productFinded.setPrice(product.getPrice());
            return new ResponseEntity<>(productRepository.save(productFinded), HttpStatus.OK);
        } else {
            log.debug("{}", ErrorObject.builder().httpStatus(HttpStatus.NOT_FOUND.name()).code(HttpStatus.NOT_FOUND.value()).message("No se encontró el producto").backendMessage("No se encontró el producto en el sistema").build().toString());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") long id) {
        try {
            productRepository.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error("{}", ErrorObject.builder().httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.name()).code(HttpStatus.INTERNAL_SERVER_ERROR.value()).message("Error al eliminar el producto").backendMessage(e.getMessage()).build().toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}

