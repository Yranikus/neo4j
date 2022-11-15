package com.example.neo4j.service;

import com.example.neo4j.ExcelParser;
import com.example.neo4j.entity.*;
import com.example.neo4j.repositories.MarksRepo;
import com.example.neo4j.repositories.StudentRepo;
import com.example.neo4j.repositories.TeamsRepo;
import com.example.neo4j.repositories.VisitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private MarksRepo marksRepo;
    @Autowired
    private TeamsRepo teamsRepo;
    @Autowired
    private VisitRepo visitRepo;

    @Autowired
    private ExcelParser excelParser;

    public void saveListOfUsers(InputStream inputStream) throws IOException {
        ArrayList<StudentEntity> studentEntities = excelParser.parseListOfUsers(inputStream);
        for (StudentEntity i : studentEntities){
            studentRepo.customSave(i.getName(),i.getPrimaryscore(),i.getScore(),i.isStatus());
        }
    }

    public List<StudentWithCheck> getPresentStudents(String date){
        List<StudentWithCheck> studentWithChecks = new ArrayList<>();
        List<StudentEntity> studentEntities1 = studentRepo.getActive(true);
        if (visitRepo.getDate(date) != null){
            List<StudentEntity> studentEntities = studentRepo.getPresentStudents(date);
            for (StudentEntity i : studentEntities1){
                for (StudentEntity j : studentEntities){
                    if (i.getName().equals(j.getName())){
                        StudentWithCheck student = new StudentWithCheck(i.getName(),i.getPrimaryscore(),i.getScore(),i.isStatus(),1);
                        student.setId(i.getId());
                        studentWithChecks.add(student);
                        System.out.println(student);
                        break;
                    }
                    else {
                        StudentWithCheck student =new StudentWithCheck(i.getName(),i.getPrimaryscore(),i.getScore(),i.isStatus(),0);
                        student.setId(i.getId());
                        studentWithChecks.add(student);
                    }
                }
            }
            return studentWithChecks;
        }
        for (StudentEntity i : studentEntities1){
            StudentWithCheck student = new StudentWithCheck(i.getName(),i.getPrimaryscore(),i.getScore(),i.isStatus(),0);
            student.setId(i.getId());
            studentWithChecks.add(student);

        }
        return studentWithChecks;
    }


    public List<Team> getTeams(String date){
        int numbers = teamsRepo.countTeams();
        ArrayList<Team> teams = new ArrayList<>();
        for (int i = 0 ; i < numbers; i++){
            StudentEntity leader = studentRepo.getLeader(i);
            List<StudentEntity> aLLteam = studentRepo.getTeamByLeaderName(leader.getName());
            Team team = new Team(i);
            for (StudentEntity s : aLLteam){
                StudentEntity studentEntity = studentRepo.checkAttendens(s.getName(),date);
                if (studentEntity != null) {
                    StudentWithMarks studentWithMarks = new StudentWithMarks();
                    studentWithMarks.setId(studentEntity.getId());
                    studentWithMarks.setName(studentEntity.getName());
                    studentWithMarks.setStatus(studentEntity.isStatus());
                    studentWithMarks.setPrimaryscore(studentEntity.getPrimaryscore());
                    studentWithMarks.setScore(studentEntity.getScore());
                    MarksForLesson marksForLesson = marksRepo.getMarksByNameAndDate(date,studentEntity.getName());
                    if ( marksForLesson != null){
                        studentWithMarks.setMarksForLesson(marksForLesson);
                    }
                    studentWithMarks.setMarksForLesson(new MarksForLesson(studentEntity.getName(),0,0));
                    team.addStudent(studentWithMarks);
                }
            }
            StudentEntity checkLEader = studentRepo.checkAttendens(leader.getName(),date);
            if (checkLEader != null ) {
                StudentWithMarks studentWithMarks = new StudentWithMarks();
                studentWithMarks.setId(checkLEader.getId());
                studentWithMarks.setName(checkLEader.getName());
                studentWithMarks.setStatus(checkLEader.isStatus());
                studentWithMarks.setPrimaryscore(checkLEader.getPrimaryscore());
                studentWithMarks.setScore(checkLEader.getScore());
                MarksForLesson marksForLesson = marksRepo.getMarksByNameAndDate(date,checkLEader.getName());
                if ( marksForLesson != null){
                    studentWithMarks.setMarksForLesson(marksForLesson);
                }
                studentWithMarks.setMarksForLesson(new MarksForLesson(checkLEader.getName(),0,0));
                team.addStudent(studentWithMarks);
            }
            team.setLeader(leader.getName());
            team.setRepo("none");
            System.out.println(team);
            teams.add(team);

        }
        return teams;
    }


    public void updateStatus(String name, boolean status){
        studentRepo.deactivateOrActivateStudent(name, status);
    }

    public void updateMarks(PresentStudentsWithMarks s){
        Date date = s.getDate();
        String sdate = date.getDate() + "" + date.getMonth() + "" + date.getYear();
        for (StudentWithMarks i : s.getStudentsWithMarks()){
            marksRepo.createMarksForLesson(i.getName(),sdate,i.getMarksForLesson().getAnswer(),i.getMarksForLesson().getQuestion());
            int score = i.getScore();
            score += i.getMarksForLesson().getAnswer() + i.getMarksForLesson().getQuestion();
            studentRepo.updateScore(i.getName(),score);
        }
    }




    public List<StudentEntity> getActiveStudents(){
        return studentRepo.getActive(true);
    }


    public ArrayList<StudentEntity> getAll(){
        return (ArrayList<StudentEntity>) studentRepo.findAll();
    }

    public void createTeams(){
        List<StudentEntity> students = studentRepo.findAll();
        int teams;
        if (students.size() % 4 == 0) {
            teams = students.size() / 4;
        }
        else {
            teams = students.size() / 4 + 1;
        }
        int counter = 0;
        for (int i = 0; i < teams; i++) {
            teamsRepo.save(new TeamEntity(i));
            teamsRepo.setLeader(students.get(counter).getName(),i);
            String leader_name = students.get(counter).getName();
            System.out.println(leader_name);
            counter++;
            while (counter % 4 != 0) {
                studentRepo.setTeammates(leader_name,students.get(counter).getName());
                counter++;
            }
        }

    }

}
