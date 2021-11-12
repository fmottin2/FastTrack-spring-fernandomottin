package br.com.fasttrack.desafio.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fasttrack.desafio.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID>{

}
