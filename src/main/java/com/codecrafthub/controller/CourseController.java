package com.codecrafthub.controller;

import com.codecrafthub.model.Course;
import com.codecrafthub.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    // POST - Create course
    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return service.addCourse(course);
    }

    // GET all courses
    @GetMapping
    public List<Course> getAllCourses() {
        return service.getAllCourses();
    }

    // GET single course
    @GetMapping("/{id}")
    public Course getCourse(@PathVariable Long id) {
        return service.getCourseById(id);
    }

    // PUT update
    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable Long id, @RequestBody Course course) {
        return service.updateCourse(id, course);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable Long id) {
        service.deleteCourse(id);
        return "Course deleted successfully";
    }

    // Global Exception Handling (simple)
    @ExceptionHandler(RuntimeException.class)
    public String handleError(RuntimeException ex) {
        return ex.getMessage();
    }
}