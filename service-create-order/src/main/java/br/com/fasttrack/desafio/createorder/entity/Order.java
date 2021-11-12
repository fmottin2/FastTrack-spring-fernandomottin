package br.com.fasttrack.desafio.createorder.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.fasttrack.desafio.createorder.dto.OrderDTO;
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
	
	public Order(OrderDTO orderDTO) {
		this.id = orderDTO.getId();
		this.name = orderDTO.getName();
		this.total = orderDTO.getTotal();
		this.description = orderDTO.getDescription();
		this.status = orderDTO.getStatus();
	}
}
