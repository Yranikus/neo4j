package com.example.neo4j.entity;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;


@Node(labels = "team")
public class TeamEntity {

    @Id
    @GeneratedValue
    private long id;
    private int number;

    @Relationship(type = "LEADEROFTEAM", direction = Relationship.Direction.INCOMING)
    public StudentEntity leader;

    public TeamEntity(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public StudentEntity getLeader() {
        return leader;
    }

    public void setLeader(StudentEntity leader) {
        this.leader = leader;
    }
}
