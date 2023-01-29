package com.okamor.mmbeauty.repository;

import com.okamor.mmbeauty.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT o FROM Sale o WHERE o.status in ('ORDER_WAIT', 'ORDER_CANCEL')")
    List<Sale> getUnpaidOrders();
}
