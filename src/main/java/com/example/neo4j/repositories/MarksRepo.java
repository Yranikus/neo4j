package com.example.neo4j.repositories;

import com.example.neo4j.entity.MarksForLesson;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MarksRepo extends Neo4jRepository<MarksForLesson, Long> {

    @Query("MATCH (n:date)<-[:FORLESSON]-(m:marks) WHERE n.date = $date AND m.name= $name RETURN m")
    MarksForLesson getMarksByNameAndDate(String date, String name);

    @Query("MATCH (n:date) WHERE n.date = $date MERGE (n)<-[:FORLESSON]-(m:mark {name: $name, answer: $answer, question: $question})")
    void createMarksForLesson(String name, String date, int answer, int question);



}
