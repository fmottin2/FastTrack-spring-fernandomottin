package br.com.fasttrack.desafio.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.fasttrack.desafio.controller.form.OrderForm;
import br.com.fasttrack.desafio.dto.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Data
public class Order {

	@Id
	protected UUID id;
	private String name;
	private String description;
	private Double total;
	private StatusOrder status;

	public Order(OrderForm form) {
		this.id = UUID.randomUUID();
		this.name = form.getName();
		this.description = form.getDescription();
		this.total = this.getTotal();
		this.status = StatusOrder.NOT_PROCESSED;
	}
	
	
	public Order(UUID id, OrderForm dto, StatusOrder status) {
		this.id = id;
		this.name = dto.getName();
		this.description = dto.getDescription();
		this.total = dto.getTotal();
	}
}
