package it.uniroma3.siw.choma.federazionecalcio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private String birthPlace;
    @NotBlank
    private String birthDate;
    @ManyToOne
    private Team team;
    @NotBlank
    private String membershipFirstDay;
    @NotBlank
    private String membershipLastDay;
}
