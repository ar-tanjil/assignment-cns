package com.cns.assignment.model;

import com.cns.assignment.enums.ProjectStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "projects")
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    private User owner;


    @Column(nullable = false)
    private ProjectStatus status;

    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "project_memebers",
            joinColumns = @JoinColumn(
                    name = "porject_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "member_id", referencedColumnName = "id"
            )
    )
    @Size(max = 5)
    private Set<User> members;

}
