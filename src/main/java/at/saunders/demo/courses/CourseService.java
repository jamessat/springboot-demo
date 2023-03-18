package at.saunders.demo.courses;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    private Log log = LogFactory.getLog(CourseService.class);


    @Autowired
    private CourseRepository repository;

    public List<Course> getAllCourses(String topicId) {
        log.info("Getting all courses");
        List<Course> courses = new ArrayList<>();
        repository.findByTopicId(topicId).forEach(courses::add);
        return courses;
    }

    public Course getCourse(String id) {
        log.info("Getting course with id " + id);
        return repository.findById(id).get();
    }

    public void addCourse(Course course) {
        log.info("Saving Course " + course);
        repository.save(course);
    }

    public void updateCourse(Course newCourse) {
        log.info("Updating Course " + newCourse );
        repository.save(newCourse);
    }

    public void deleteCourse(String id) {
        log.info("Deleting Course " + id);
        repository.deleteById(id);
    }
}
