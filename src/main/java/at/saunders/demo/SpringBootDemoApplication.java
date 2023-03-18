package at.saunders.demo;

import at.saunders.demo.topic.Topic;
import at.saunders.demo.topic.TopicRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class SpringBootDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);
	}


	@Bean
	CommandLineRunner init(TopicRepository topicRepository) {
		return args -> {
			Stream.of("java", "javaScript", "JSON").forEach(name -> {
				Topic topic = new Topic(name, name, name + " description");
				topicRepository.save(topic);
			});
			topicRepository.findAll().forEach(System.out::println);
		};
	}

}
