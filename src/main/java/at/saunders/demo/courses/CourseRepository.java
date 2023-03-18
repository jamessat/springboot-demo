package at.saunders.demo.courses;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, String> {

    //This will generate a find my name method
    public List<Course> findByName(String name);

    public List<Course> findByTopicId(String topicId);

}
