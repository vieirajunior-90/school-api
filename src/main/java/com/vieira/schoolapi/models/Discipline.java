package com.vieira.schoolapi.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "TB_DISCIPLINE")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Discipline implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false, length = 100)
    private String name;

    @NonNull
    @Column(nullable = false)
    private String description;

//    private List<Student> students;
//    private List<Grade> grade;

    @Builder
    public Discipline(@NonNull String name, @NonNull String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discipline that = (Discipline) o;
        return getId().equals(that.getId()) && getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
