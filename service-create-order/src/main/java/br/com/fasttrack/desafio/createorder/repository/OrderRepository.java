package br.com.fasttrack.desafio.createorder.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fasttrack.desafio.createorder.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID>{

}
