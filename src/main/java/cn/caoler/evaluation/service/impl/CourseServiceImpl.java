package cn.caoler.evaluation.service.impl;


import cn.caoler.evaluation.dataobject.Course;
import cn.caoler.evaluation.exception.EvaluationException;
import cn.caoler.evaluation.repository.CourseRepository;
import cn.caoler.evaluation.enums.ResultEnum;
import cn.caoler.evaluation.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static cn.caoler.evaluation.converter.User2UserDTOConverter.convert;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;


    @Override
    public Course create(Course course) {
        return  courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Course course) {
        Course result;
        if (course.getCourseId()!= null){
              result = courseRepository.save(course);
        }else {
            throw new EvaluationException(ResultEnum.COURSE_NOT_EXIST);
        }
        return  result;
    }

    @Override
    public Page<Course> findCourseIfCourseNameIsNotNull(String courseName, Pageable pageable) {

        Page<Course> coursePage = courseRepository.findCourseIfCourseNameIsNotNull(courseName,pageable);

        return coursePage;
    }

    @Override
    public Integer countCourseForPage(String courseName) {
        Integer count = courseRepository.countCourseForPage(courseName);

        return count;
    }

    @Override
    public Course findByCourseId(Long courseId) {

        Course course = courseRepository.findByCourseId(courseId);
        if (course == null){
            throw new EvaluationException(ResultEnum.USER_NOT_EXIST);
        }

        return course;
    }

    @Override
    public void deleteByCourseId(Long courseId) {
        courseRepository.deleteByCourseId(courseId);
    }
}
