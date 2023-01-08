package com.okamor.mmbeauty.service;

import com.okamor.mmbeauty.model.Course;
import com.okamor.mmbeauty.model.enums.CourseSatatus;

import java.util.List;

public interface CourseService {

    List<Course> getAllCourse();

    Course getCourseById(Long id);

    void newCourse(Course course);

    void editCourse(Course course);

    void changeCourseStatus(Long id, CourseSatatus status);
}
