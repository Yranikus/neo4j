package com.example.neo4j.repositories;

import com.example.neo4j.entity.TeamEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface TeamsRepo extends Neo4jRepository<TeamEntity,Long> {

    TeamEntity save(TeamEntity entity);

    @Query("MATCH (n:student), (m:team) WHERE n.name = $name AND m.number = $number CREATE (n)-[r:LEADEROFTEAM]->(m)")
    void setLeader(String name,int number);

    @Query("MATCH (n:team) return count(n)")
    Integer countTeams();

}
