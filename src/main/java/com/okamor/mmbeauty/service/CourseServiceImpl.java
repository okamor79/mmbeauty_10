package com.okamor.mmbeauty.service;

import com.okamor.mmbeauty.model.Course;
import com.okamor.mmbeauty.model.enums.CourseSatatus;
import com.okamor.mmbeauty.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.getCourseById(id);
    }

    @Override
    public void newCourse(Course course) {

    }

    @Override
    public void editCourse(Course course) {

    }

    @Override
    public void changeCourseStatus(Long id, CourseSatatus status) {

    }
}
