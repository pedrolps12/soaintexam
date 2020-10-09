package com.soaint.exam.repository;

import com.soaint.exam.entities.Client;
import com.soaint.exam.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {

}
