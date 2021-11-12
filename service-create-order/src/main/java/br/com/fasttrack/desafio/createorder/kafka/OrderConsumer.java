package br.com.fasttrack.desafio.createorder.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import br.com.fasttrack.desafio.createorder.dto.OrderDTO;
import br.com.fasttrack.desafio.createorder.service.OrderService;

@Component
public class OrderConsumer {
	
	@Autowired
	OrderService service;
	
	@Value(value = "${topic.create.name}")
	private String topic;

	@Value(value = "${spring.kafka.group-id}")
	private String groupId;

	@KafkaListener(topics = "${topic.create.name}", groupId = "${spring.kafka.group-id}", containerFactory = "orderKafkaListenerContainerFactory")
	public void listenTopicORDER(ConsumerRecord<String, OrderDTO> record) {
		System.out.println("Consumindo ##############");
		System.out.println(record.value().getName());
		
		service.createOrder(record.value());
	}
}
