package br.com.fasttrack.desafio.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.fasttrack.desafio.controller.form.OrderForm;
import br.com.fasttrack.desafio.entity.Order;
import br.com.fasttrack.desafio.entity.StatusOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {

	private UUID id;
	private String name;
	private String description;
	private Double total;
	private StatusOrder status;
	
	public OrderDTO(Order order) {
		this.id = order.getId();
		this.name = order.getName();
		this.description = order.getDescription();
		this.total = order.getTotal();
		this.status = order.getStatus();
	}
	
	public OrderDTO(OrderForm orderForm) {
		this.id = UUID.randomUUID();
		this.name = orderForm.getName();
		this.description = orderForm.getDescription();
		this.total = orderForm.getTotal();
		this.status = StatusOrder.NOT_PROCESSED;
	}
	
	public static List<OrderDTO> converter(List<Order> orders) {
		List<OrderDTO> list = new ArrayList<OrderDTO>();
		orders.forEach(order -> list.add(new OrderDTO(order)));
		return list;
	}
	
}
