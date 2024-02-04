package it.uniroma3.siw.choma.federazionecalcio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    @Min(1850)
    @Max(2024)
    private Integer yearOfFoundation;
    @NotBlank
    private String address;
    @OneToOne
    private User president;
    @OneToMany(mappedBy = "team")
    private Set<Player> players;

    private String picFilename;


}
