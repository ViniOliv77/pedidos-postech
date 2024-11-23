package com.fiap.tech.pedidos_postech.repository.repository;




import com.fiap.tech.pedidos_postech.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Long> {
}
