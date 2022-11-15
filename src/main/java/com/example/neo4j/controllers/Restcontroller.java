package com.example.neo4j.controllers;



import com.example.neo4j.entity.*;
import com.example.neo4j.service.StudentService;
import com.example.neo4j.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/")
public class Restcontroller {



    @Autowired
    private VisitService visitService;
//    @Autowired
//    private MarksDao marksDaoDao;
//    @Autowired
//    private TeamsDao teamsDao;
    @Autowired
    private StudentService studentService;

    @GetMapping("/getpresentstudents")
    public List<StudentWithCheck> getPresentStudents(@RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date){
        String sdate = date.getDate() + "" + date.getMonth() + "" + date.getYear();

        return studentService.getPresentStudents(sdate);
    }

    @GetMapping("/getlistofstudents")
    public ArrayList<StudentEntity> getStudents(){
        return studentService.getAll();
    }

    @GetMapping("/getactivelistofstudents")
    public List<StudentEntity> getActiveStudents(){
        return studentService.getActiveStudents();
    }

    @PostMapping("/updateVisits")
    public void presentDate(@RequestBody PresentStudents s){
        visitService.updateVisit(s);
    }

    @PostMapping("/updateMarks")
    public void updateMarks(@RequestBody PresentStudentsWithMarks s){
        studentService.updateMarks(s);
    }

    @GetMapping("/getteams")
    public List<Team> getTeams(@RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date){
        String sdate = date.getDate() + "" + date.getMonth() + "" + date.getYear();
        return studentService.getTeams(sdate);
    }

}
