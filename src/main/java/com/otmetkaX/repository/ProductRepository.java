package com.otmetkaX.repository;

import com.otmetkaX.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long>  {

    Optional<Product> findByName(String name);

    @Modifying
    @Query("UPDATE Product p SET p.countToDay = 0")
    void resetCountToDay();
}
