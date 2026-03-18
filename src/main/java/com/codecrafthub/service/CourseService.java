package com.codecrafthub.service;

import com.codecrafthub.model.Course;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    private static final String FILE_PATH = "courses.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    // Allowed status values
    private final List<String> validStatus =
            List.of("Not Started", "In Progress", "Completed");

    // Read all courses from JSON
    private List<Course> readCourses() {
        try {
            File file = new File(FILE_PATH);

            // Create file if not exists
            if (!file.exists()) {
                file.createNewFile();
                objectMapper.writeValue(file, new ArrayList<>());
            }

            return objectMapper.readValue(file, new TypeReference<List<Course>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Error reading courses file");
        }
    }

    // Write courses to JSON
    private void writeCourses(List<Course> courses) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(FILE_PATH), courses);
        } catch (Exception e) {
            throw new RuntimeException("Error writing courses file");
        }
    }

    // Validate course fields
    private void validateCourse(Course course) {
        if (course.getName() == null || course.getName().isEmpty())
            throw new RuntimeException("Course name is required");

        if (course.getDescription() == null || course.getDescription().isEmpty())
            throw new RuntimeException("Course description is required");

        if (course.getTargetDate() == null)
            throw new RuntimeException("Target date is required");

        if (course.getStatus() == null || !validStatus.contains(course.getStatus()))
            throw new RuntimeException("Invalid status value");
    }

    // Generate next ID
    private Long generateId(List<Course> courses) {
        return courses.stream()
                .mapToLong(c -> c.getId() == null ? 0 : c.getId())
                .max()
                .orElse(0) + 1;
    }

    // CREATE
    public Course addCourse(Course course) {
        validateCourse(course);

        List<Course> courses = readCourses();

        course.setId(generateId(courses));
        course.setCreatedAt(LocalDateTime.now());

        courses.add(course);
        writeCourses(courses);

        return course;
    }

    // READ ALL
    public List<Course> getAllCourses() {
        return readCourses();
    }

    // READ ONE
    public Course getCourseById(Long id) {
        return readCourses().stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    // UPDATE
    public Course updateCourse(Long id, Course updated) {
        validateCourse(updated);

        List<Course> courses = readCourses();

        for (Course c : courses) {
            if (c.getId().equals(id)) {
                c.setName(updated.getName());
                c.setDescription(updated.getDescription());
                c.setTargetDate(updated.getTargetDate());
                c.setStatus(updated.getStatus());

                writeCourses(courses);
                return c;
            }
        }

        throw new RuntimeException("Course not found");
    }

    // DELETE
    public void deleteCourse(Long id) {
        List<Course> courses = readCourses();

        boolean removed = courses.removeIf(c -> c.getId().equals(id));

        if (!removed)
            throw new RuntimeException("Course not found");

        writeCourses(courses);
    }
}