package br.com.fasttrack.desafio.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fasttrack.desafio.controller.form.OrderForm;
import br.com.fasttrack.desafio.dto.OrderDTO;
import br.com.fasttrack.desafio.entity.Order;
import br.com.fasttrack.desafio.exception.OrderNotExistsException;
import br.com.fasttrack.desafio.kafka.OrderProducer;
import br.com.fasttrack.desafio.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	@Autowired OrderProducer producer;
	
	public List<Order> findAll(){
		return repository.findAll();
	}

	public OrderDTO save(OrderForm order) {
		OrderDTO orderDTO = new OrderDTO(order);
		producer.send(orderDTO);
		return orderDTO;
	}
	
	public void update(UUID id,OrderForm orderForm) throws OrderNotExistsException {
		Optional<Order> optional = repository.findById(id);
		if(optional.isPresent()) {
			Order order = new Order(id,orderForm,optional.get().getStatus());
			repository.save(order);
		}else {
			throw new OrderNotExistsException("Ordem não encontrada com o id: "+id);
		}
		
	}

	public Optional<Order> findById(UUID id) {
		return repository.findById(id);
	}

	public void delete(UUID id) throws OrderNotExistsException {
		Optional<Order> optional = repository.findById(id);
		if(optional.isPresent()) {
			repository.deleteById(id);
		}else {
			throw new OrderNotExistsException("Ordem não encontrada com o id: "+id);
		}
		
	}
}
