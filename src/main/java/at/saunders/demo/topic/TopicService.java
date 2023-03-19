package at.saunders.demo.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TopicService {

    private TopicRepository repository;

    @Autowired
    public TopicService(TopicRepository repo) {
        repository = repo;
    }

    public List<Topic> getAllTopics() {
        List<Topic> topics = new ArrayList<>();
        repository.findAll().forEach(topics::add);
        return topics;
    }

    public Topic getTopic(String id) {
        return repository.findById(id).get();
    }

    public Topic addTopic(Topic topic) {
        return repository.save(topic);
    }

    public Topic updateTopic(String id, Topic newTopic) {
        return repository.save(newTopic);
    }

    public void deleteTopic(String id) {
        repository.deleteById(id);
    }
}
