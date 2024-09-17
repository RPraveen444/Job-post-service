package com.revature.jobpostservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "skills")
public class Skills {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id")
    private long skillId;

    @Column(name = "skill_name", nullable = false, length = 255)
    private String skillName;

    @Column(name = "skill_description", columnDefinition = "TEXT")
    private String skillDescription;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}

