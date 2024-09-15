package com.aleksey.eventboardbackend.repository.user;

import com.aleksey.eventboardbackend.entity.user.Manager;
import com.aleksey.eventboardbackend.entity.user.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends UserBaseRepository<Student>{
}
