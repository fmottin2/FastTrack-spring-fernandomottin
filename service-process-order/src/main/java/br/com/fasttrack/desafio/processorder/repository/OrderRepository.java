package br.com.fasttrack.desafio.processorder.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fasttrack.desafio.processorder.entity.Order;

public interface OrderRepository extends JpaRepository<Order, UUID>{

}
