package com.example.neo4j.repositories;

import com.example.neo4j.entity.StudentEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface StudentRepo extends Neo4jRepository<StudentEntity, Long> {


    @Query("CREATE (a:student {name: $name, primaryscore: $primaryscore, score: $score, status: $status})")
    void customSave(String name, int primaryscore, int score, boolean status);

    @Query("MATCH (n:student) WHERE n.name = $name SET n.status = $status")
    void deactivateOrActivateStudent(String name, Boolean status);

    @Query("MATCH (n:student), (m:student) WHERE n.name = $leader AND m.name = $teammate CREATE (n)-[r:TEAMMATE]->(m)")
    void setTeammates(String leader, String teammate);

    @Query("MATCH (n:student) WHERE n.status = $status RETURN (n)")
    List<StudentEntity> getActive(boolean status);

    @Query("MATCH (n:student)-[r:ATTEND]->(m) WHERE m.date= $date RETURN (n)")
    List<StudentEntity> getPresentStudents(String date);

    @Query("MATCH (n:student)-[r:LEADEROFTEAM]->(m:team) WHERE m.number = $number RETURN (n)")
    StudentEntity getLeader(int number);

    @Query("match (n:student)-[:TEAMMATE]->(m:student) WHERE n.name = $leader return m")
    List<StudentEntity> getTeamByLeaderName(String leader);

    @Query("match (n:student)-[:ATTEND]->(d:date) WHERE d.date = $date AND n.name = $name return n")
    StudentEntity checkAttendens(String name, String date);

    @Query("MATCH (n:student) WHERE n.name = $name SET n.score = $score ")
    void updateScore(String name, int score);

}

