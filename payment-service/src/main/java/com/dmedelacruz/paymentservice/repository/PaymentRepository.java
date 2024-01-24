package com.dmedelacruz.paymentservice.repository;

import com.dmedelacruz.paymentservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {
}
