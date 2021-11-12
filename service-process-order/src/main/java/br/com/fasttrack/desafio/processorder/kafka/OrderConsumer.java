package br.com.fasttrack.desafio.processorder.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import br.com.fasttrack.desafio.processorder.dto.OrderDTO;
import br.com.fasttrack.desafio.processorder.kafka.service.OrderService;

@Component
public class OrderConsumer {

	@Autowired
	OrderService service;
	
	@Value(value = "${topic.process.name}")
	private String topic;

	@Value(value = "${spring.kafka.group-id}")
	private String groupId;

	@KafkaListener(topics = "${topic.process.name}", groupId = "${spring.kafka.group-id}", containerFactory = "orderKafkaListenerContainerFactory")
	public void listenTopicORDER(ConsumerRecord<String, OrderDTO> record) {
		System.out.println("Consumindo ##############");
		System.out.println(record.value().getName());
		
		service.process(record.value());
	}
}
