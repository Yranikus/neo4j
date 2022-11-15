package com.example.neo4j.repositories;

import com.example.neo4j.entity.DateEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface VisitRepo extends Neo4jRepository<DateEntity,Long> {

    @Query("OPTIONAL MATCH (n:date) WHERE n.date = $date RETURN n")
    DateEntity getDate(String date);

    @Query("CREATE (n:date {date: $date})")
    void createDate(String date);

    @Query("MATCH (c:student {name: $id}) WITH c MATCH (g:date {date: $date}) CREATE (c)-[:ATTEND]->(g)")
    void updateVisits(String date, String id);

}
