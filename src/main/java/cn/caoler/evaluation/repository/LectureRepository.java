package cn.caoler.evaluation.repository;

import cn.caoler.evaluation.dataobject.Lecture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by caole on 2019/4/10 10:42
 */
public interface LectureRepository extends JpaRepository<Lecture,Long> {

    @Transactional
    @Query(value = "select * from lecture where if(?1 !='',teacher_id=?1,1=1)",nativeQuery = true)
    Page<Lecture> findLecturesIfTeacherIdIsNotNull(Long teacherId, Pageable pageable);


    @Transactional
    @Query(value = "select count(*) from lecture where if(?1 !='',teacher_id=?1,1=1)",nativeQuery = true)
    Integer countLectureForPage(Long teacherId);

    Lecture findByLectureId(Long lectureId);

    void deleteByLectureId(Long lectureId);




}
