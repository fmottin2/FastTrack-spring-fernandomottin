package br.com.fasttrack.desafio.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fasttrack.desafio.controller.form.OrderForm;
import br.com.fasttrack.desafio.dto.OrderDTO;
import br.com.fasttrack.desafio.entity.Order;
import br.com.fasttrack.desafio.exception.OrderNotExistsException;
import br.com.fasttrack.desafio.service.OrderService;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService service;
	
	@GetMapping
	public List<OrderDTO> findAll(){
		return OrderDTO.converter(service.findAll());
	}
	
	@PostMapping
	public ResponseEntity<Object> insert(@RequestBody @Valid OrderForm form) throws Exception {
		try {
			OrderDTO orderDTO = service.save(form);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(orderDTO.getId())
					.toUri();
			return ResponseEntity.created(uri).build();
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Erro ao salvar!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/{id}")
	public OrderDTO findById(@PathVariable UUID id) throws Exception{
		Optional<Order> optional =  service.findById(id);
		if(optional.isPresent()) {
			return new OrderDTO(optional.get());
		}
		
		throw new ObjectNotFoundException("Ordem não encontrada por id: "+id.toString());
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody OrderForm orderForm) throws Exception{
		try {
			service.update(id,orderForm);
			return ResponseEntity.accepted().build();
		}catch (OrderNotExistsException e) {
			return ResponseEntity.notFound().build();
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Erro ao excluir!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") UUID id){
		try {
			service.delete(id);
			return ResponseEntity.ok("Excluido com sucesso");
		}catch (OrderNotExistsException e) {
			return ResponseEntity.notFound().build();
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Erro ao excluir!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
