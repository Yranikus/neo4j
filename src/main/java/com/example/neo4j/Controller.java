package com.example.neo4j;

import com.example.neo4j.entity.StudentEntity;
import com.example.neo4j.repositories.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class Controller {

    @Autowired
    private StudentRepo studentRepo;


    @RequestMapping("/")
    public void test(){
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setName("kek");
        studentEntity.setPrimaryscore(0);
        studentEntity.setScore(10);
        studentEntity.setStatus(true);

        studentRepo.save(studentEntity);

    }


}
