package com.vieira.schoolapi.models;

import com.vieira.schoolapi.dtos.StudentDto;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "TB_ADDRESS")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
public class Address implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "street", nullable = false, length = 100)
    private String street;

    @NonNull
    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "complement", length = 100)
    private String complement;

    @NonNull
    @Column(name = "district", nullable = false, length = 100)
    private String district;

    @NonNull
    @Column(name = "city", nullable = false, length = 100)
    private String city;

    @NonNull
    @Column(name = "state", nullable = false, length = 2)
    private String state;

    @NonNull
    @Column(name = "country", nullable = false, length = 50)
    private String country;

    @NonNull
    @Column(name = "zip_code", nullable = false, length = 10)
    private String zipCode;

    @OneToMany(
            mappedBy = "address",
            cascade = CascadeType.ALL
    )
    @ToString.Exclude
    private List<Student> students;

    @Builder
    public Address(String street, String number, String complement, String district,
                   String city, String state, String country, String zipCode) {
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.district = district;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
    }

}
