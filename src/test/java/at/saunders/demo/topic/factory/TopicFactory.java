package at.saunders.demo.topic.factory;

import at.saunders.demo.topic.Topic;

public class TopicFactory {

    public Topic createTopic(){
        return new Topic("java", "Java", "Java Desc");
    }



}

