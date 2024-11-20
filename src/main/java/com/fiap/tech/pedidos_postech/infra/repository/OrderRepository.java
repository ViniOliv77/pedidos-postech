package com.fiap.tech.pedidos_postech.infra.repository;




import com.fiap.tech.pedidos_postech.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
