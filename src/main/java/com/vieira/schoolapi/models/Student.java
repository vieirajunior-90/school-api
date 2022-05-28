package com.vieira.schoolapi.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "TB_STUDENT")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Student implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false, length = 100)
    private String name;

    @NonNull
    @Email(message = "Email is invalid", regexp = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$")
    @Column(nullable = false, length = 100)
    private String email;

    @NonNull
    @Column(nullable = false, length = 20)
    @Pattern(regexp = "^\\([1-9]{2}\\)[2-9]\\d{4,5}-\\d{4}$",
            message = "Phone is invalid. The format must be (99)99999-9999")
    private String phone;

    @NonNull
    @NotBlank(message = "Password is required and must be at least 8 characters")
    @Size(min = 8, max = 20)
    @Column(nullable = false, length = 20)
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Password must contain at least one digit, one uppercase, one lowercase letter, " +
                      "one special character and no whitespace")
    private String password;

//    private Address address;
//
//    private List<Discipline> disciplines;

    @Builder
    public Student(@NonNull String name, @NonNull String email, @NonNull String phone, @NonNull String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id.equals(student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
