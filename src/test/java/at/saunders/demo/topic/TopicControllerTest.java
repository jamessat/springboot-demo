package at.saunders.demo.topic;

import at.saunders.demo.topic.factory.TopicFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class TopicControllerTest {

    private TopicController topicController;

    @Mock
    private TopicRepository repo;

    private TopicFactory factory = new TopicFactory();

    @BeforeEach
    public void setUp(){
        topicController = new TopicController(new TopicService(repo));
    }

    @Test
    public void getNoTopics(){

        Mockito.when(repo.findAll())
                .thenReturn(Collections.emptyList());

        List<Topic> topics = topicController.getAllTopics();
        Assertions.assertEquals(0, topics.size());
    }

    @Test
    public void getOneTopicVaiFindAll(){

        Mockito.when(repo.findAll())
                .thenReturn(
                        Stream.of(factory.createTopic())
                                .collect(Collectors.toList())
                );

        List<Topic> topics = topicController.getAllTopics();
        Assertions.assertEquals(1, topics.size());
    }


    @Test
    public void getATopic(){
        Topic topic = factory.createTopic();
        Mockito.when(repo.findById("java"))
                .thenReturn(Optional.of(topic));
        Topic result = topicController.getTopic("java");
        Assertions.assertEquals(topic, result);
    }


    @Test
    public void addATopic(){
        Topic newTopic = factory.createTopic();

        Mockito.when(repo.save(newTopic)).thenReturn(newTopic);
        Topic created = topicController.addTopic(newTopic);

        ArgumentCaptor<Topic> topicCaptor = ArgumentCaptor.forClass(Topic.class);
        Mockito.verify(repo, Mockito.times(1))
                .save(topicCaptor.capture());

        Topic savedTopic = topicCaptor.getValue();
        Assertions.assertEquals(created, savedTopic);

    }

    @Test
    public void updateTopic(){
        Topic newTopic = factory.createTopic();
        Mockito.when(repo.save(newTopic)).thenReturn(newTopic);

        Topic updated = topicController.updateTopic(newTopic, "java");
        ArgumentCaptor<Topic> topicCaptor = ArgumentCaptor.forClass(Topic.class);
        Mockito.verify(repo, Mockito.times(1))
                .save(topicCaptor.capture());

        Topic savedTopic = topicCaptor.getValue();
        Assertions.assertEquals(updated, savedTopic);
    }

    @Test
    public void deleteTopic(){
        Topic newTopic = factory.createTopic();
        Mockito.doNothing().when(repo).deleteById("java");

        topicController.deleteTopic("java");

        ArgumentCaptor<String> topicCaptor = ArgumentCaptor.forClass(String.class);
        Mockito.verify(repo, Mockito.times(1))
                .deleteById(topicCaptor.capture());
        String param = topicCaptor.getValue();
        Assertions.assertEquals("java", param);
    }

}
