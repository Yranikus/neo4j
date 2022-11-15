package com.example.neo4j.service;

import com.example.neo4j.entity.DateEntity;
import com.example.neo4j.entity.PresentStudents;
import com.example.neo4j.repositories.VisitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitService {

    @Autowired
    private VisitRepo visitRepo;

    public void updateVisit(PresentStudents presentStudents){
        String sdate = presentStudents.getDate().getDate() + "" + presentStudents.getDate().getMonth() + "" + presentStudents.getDate().getYear();
        DateEntity dateEntity = visitRepo.getDate(sdate);
        if (dateEntity == null) visitRepo.createDate(sdate);
        dateEntity = visitRepo.getDate(sdate);
        System.out.println(dateEntity.getDate());
        for (String i : presentStudents.getPresentStudents()){
            System.out.println(sdate);
            System.out.println(i);
            visitRepo.updateVisits(sdate,i);
        }
    }

}
