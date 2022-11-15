package com.example.neo4j.entity;

public class StudentWithMarks extends StudentEntity{



    private MarksForLesson marksForLesson;



    public StudentWithMarks() {

    }

    @Override
    public String toString() {
        return "StudentWithMarks{" +
                "marksForLesson=" + marksForLesson +
                '}';
    }

    public StudentWithMarks(MarksForLesson marksForLesson) {
        this.marksForLesson = marksForLesson;
    }

    public StudentWithMarks(String name, int primaryscore, int score, boolean status, MarksForLesson marksForLesson) {
        super(name, primaryscore, score, status);
        this.marksForLesson = marksForLesson;
    }

    public MarksForLesson getMarksForLesson() {
        return marksForLesson;
    }

    public void setMarksForLesson(MarksForLesson marksForLesson) {
        this.marksForLesson = marksForLesson;
    }
}
