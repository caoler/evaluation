package cn.caoler.evaluation.service.impl;

import cn.caoler.evaluation.dataobject.Department;
import cn.caoler.evaluation.exception.EvaluationException;
import cn.caoler.evaluation.repository.DepartmentRepository;
import cn.caoler.evaluation.enums.ResultEnum;
import cn.caoler.evaluation.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department create(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department updateDepartment(Department department) {
        Department dep;
        if (department.getDepartmentId()!= null){
            dep = departmentRepository.save(department);
        }else {
            throw new EvaluationException(ResultEnum.DEPARTMENT_NOT_EXIST);
        }
        return  dep;
    }

    @Override
    public Page<Department> findDepartmentIfDepartmentNameIsNotNull(String departmentName, Pageable pageable) {
        Page<Department> departmentPage=departmentRepository.findDepartmentIfDepartmentNameIsNotNull(departmentName,pageable);
        return departmentPage;
    }

    @Override
    public Integer countDepartmentForPage(String departmentName) {
        Integer count=departmentRepository.countDepartmentForPage(departmentName);
        return count;
    }

    @Override
    public Department findByDepartmentId(Long departmentId) {
        Department department=departmentRepository.findByDepartmentId(departmentId);
        if (department==null){
            throw new EvaluationException(ResultEnum.DEPARTMENT_NOT_EXIST);
        }
        return department;
    }

    @Override
    public Department findByDepartmentNo(String departmentNo) {
        return departmentRepository.findByDepartmentNo(departmentNo);
    }

    @Override
    public void deleteByDepartmentId(Long departmentId) {
        departmentRepository.deleteByDepartmentId(departmentId);

    }
}
