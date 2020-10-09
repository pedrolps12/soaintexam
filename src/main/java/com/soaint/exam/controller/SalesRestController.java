package com.soaint.exam.controller;

import com.soaint.exam.entities.Sale;
import com.soaint.exam.model.ErrorObject;
import com.soaint.exam.repository.SaleRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
@Log4j2
public class SalesRestController {


    @Autowired
    SaleRepository saleRepository;

    @GetMapping("/sales")
    public ResponseEntity<List<Sale>> getAllSale() {
        try {
            List<Sale> sales = new ArrayList<>();
            saleRepository.findAll().forEach(sales::add);

            if (sales.isEmpty()) {
                log.debug("{}", ErrorObject.builder().httpStatus(HttpStatus.NO_CONTENT.name()).code(HttpStatus.NO_CONTENT.value()).message("No se encontraron ventas").backendMessage("No se encontraron ventas en el sistema").build().toString());
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(sales, HttpStatus.OK);
        } catch (Exception e) {
            log.error("{}", ErrorObject.builder().httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.name()).code(HttpStatus.INTERNAL_SERVER_ERROR.value()).message("Error al cargar las ventas").backendMessage(e.getMessage()).build().toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sales/{id}")
    public ResponseEntity<Sale> getSaleById(@PathVariable("id") long id) {
        Sale sale = saleRepository.findOne(id);

        if (sale != null) {
            return new ResponseEntity<>(sale, HttpStatus.OK);
        } else {
            log.debug("{}", ErrorObject.builder().httpStatus(HttpStatus.NOT_FOUND.name()).code(HttpStatus.NOT_FOUND.value()).message("No se encontró la venta").backendMessage("No se encontró la venta en el sistema").build().toString());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/sales")
    public ResponseEntity<Sale> createSale(@RequestBody Sale sale) {
        try {
            Sale _sale = saleRepository
                    .save(Sale.builder().client(sale.getClient()).fecha(sale.getFecha()).build());
            return new ResponseEntity<>(_sale, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("{}", ErrorObject.builder().httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.name()).code(HttpStatus.INTERNAL_SERVER_ERROR.value()).message("Error al grabar la ventas").backendMessage(e.getMessage()).build().toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}

