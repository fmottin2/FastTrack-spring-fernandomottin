package br.com.fasttrack.desafio.processorder.kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fasttrack.desafio.processorder.dto.OrderDTO;
import br.com.fasttrack.desafio.processorder.entity.Order;
import br.com.fasttrack.desafio.processorder.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository repository;
	
	public void process(OrderDTO orderDto) {
		repository.save(new Order(orderDto));
	}
}
