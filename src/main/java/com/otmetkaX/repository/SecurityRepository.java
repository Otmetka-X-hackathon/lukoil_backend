package com.otmetkaX.repository;

import com.otmetkaX.model.Security;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface SecurityRepository extends JpaRepository<Security, Long> {

    Optional<Security> findByPhone(String phone);
    Optional<Security> findByToken(String token);
    Optional<Security> findById(Long id);
    List<Security> findAll();
}
