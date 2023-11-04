package tn.esprit.SkiStationProject.Spring.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.SkiStationProject.entities.Course;
import tn.esprit.SkiStationProject.entities.enums.Support;
import tn.esprit.SkiStationProject.entities.enums.TypeCourse;
import tn.esprit.SkiStationProject.repositories.CourseRepository;
import tn.esprit.SkiStationProject.services.CourseServicesImpl;
import tn.esprit.SkiStationProject.services.ICourseServices;

@SpringBootTest
public class CourseServicesImplMockTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private ICourseServices courseServices = new CourseServicesImpl(courseRepository);

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRetrieveAllCourses() {
        List<Course> courseList = new ArrayList<>();
        // Add some courses to the list

        // Mock the behavior of courseRepository.findAll() to return courseList
        when(courseRepository.findAll()).thenReturn(courseList);

        List<Course> retrievedCourses = courseServices.retrieveAllCourses();

        assertNotNull(retrievedCourses);
        assertEquals(courseList.size(), retrievedCourses.size());
    }

    @Test
    public void testAddCourse() {
        Course course = new Course();
        course.setLevel(3);
        course.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);
        course.setSupport(Support.SKI);
        course.setPrice(50.0f);
        course.setTimeSlot(60);

        // Mock the behavior of courseRepository.save to return the course
        when(courseRepository.save(any(Course.class))).thenReturn(course);

        Course savedCourse = courseServices.addCourse(course);

        assertNotNull(savedCourse);
        assertEquals(3, savedCourse.getLevel());
        assertEquals(TypeCourse.COLLECTIVE_CHILDREN, savedCourse.getTypeCourse());
        assertEquals(Support.SKI, savedCourse.getSupport());
        assertEquals(50.0f, savedCourse.getPrice());
        assertEquals(60, savedCourse.getTimeSlot());
    }


    @Test
    public void testUpdateCourse() {
        Long id = 1L;
        Course course = new Course();
        course.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);

        // Mock the behavior of courseRepository.findById and courseRepository.save
        when(courseRepository.findById(id)).thenReturn(Optional.of(course));
        when(courseRepository.save(any(Course.class))).thenReturn(course);

        course.setTypeCourse(TypeCourse.INDIVIDUAL);
        Course updatedCourse = courseServices.updateCourse(course);

        assertNotNull(updatedCourse);
        assertEquals(TypeCourse.INDIVIDUAL, updatedCourse.getTypeCourse());
    }

    @Test
    public void testRetrieveCourse() {
        Long id = 1L;
        String expectedTypeCourse = "COLLECTIVE_CHILDREN";

        Course expectedCourse = new Course();
        expectedCourse.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);

        // Mock the behavior of courseRepository.findById
        when(courseRepository.findById(id)).thenReturn(Optional.of(expectedCourse));

        Course foundCourse = courseServices.retrieveCourse(id);

        assertNotNull(foundCourse);
        assertEquals(expectedTypeCourse, foundCourse.getTypeCourse().toString());
    }
}
