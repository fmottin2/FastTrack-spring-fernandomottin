package br.com.fasttrack.desafio.createorder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fasttrack.desafio.createorder.dto.OrderDTO;
import br.com.fasttrack.desafio.createorder.entity.Order;
import br.com.fasttrack.desafio.createorder.kafka.OrderProducer;
import br.com.fasttrack.desafio.createorder.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository repository;
	
	@Autowired
	OrderProducer producer;
	
	public void createOrder(OrderDTO orderDTO) {
		repository.save(new Order(orderDTO));
		producer.send(orderDTO); 
	}
	
	
}
