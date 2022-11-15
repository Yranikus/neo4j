package com.example.neo4j.entity;

import java.util.ArrayList;

public class Team {

    private int numberOfTeam;
    private ArrayList students;
    private String leader;
    private String repo;


    public String getRepo() {
        return repo;
    }

    public void setRepo(String repo) {
        this.repo = repo;
    }

    public Team() {
    }

    public Team(int index) {
        this.numberOfTeam = index;
        this.students = new ArrayList<StudentEntity>();
    }


    public Team(int numberOfTeam, ArrayList students, String leader) {
        this.leader = leader;
        this.numberOfTeam = numberOfTeam;
        this.students = students;
    }

    public void addStudent(StudentEntity student){
        this.students.add(student);
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public int getNumberOfTeam() {
        return numberOfTeam;
    }

    public void setNumberOfTeam(int numberOfTeam) {
        this.numberOfTeam = numberOfTeam;
    }

    public ArrayList<StudentEntity> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<StudentEntity> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Teams{" +
                "numberOfTeam=" + numberOfTeam +
                ", students=" + students +
                ", leader='" + leader + '\'' +
                '}';
    }
}
