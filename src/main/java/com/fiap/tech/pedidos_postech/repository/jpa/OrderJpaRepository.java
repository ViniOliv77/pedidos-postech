package com.fiap.tech.pedidos_postech.repository.jpa;

import com.fiap.tech.pedidos_postech.repository.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {

}
