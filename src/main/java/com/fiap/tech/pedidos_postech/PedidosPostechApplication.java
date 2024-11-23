package com.fiap.tech.pedidos_postech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.fiap.tech.pedidos_postech.repository.jpa"})
public class PedidosPostechApplication {

    public static void main(String[] args) {
        SpringApplication.run(PedidosPostechApplication.class, args);
    }

}
