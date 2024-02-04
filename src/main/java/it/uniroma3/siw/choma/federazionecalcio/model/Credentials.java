package it.uniroma3.siw.choma.federazionecalcio.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Credentials {
    public static final String PRESIDENT_ROLE = "PRESIDENT";
    public static final String ADMIN_ROLE = "ADMIN";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String role;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;
}
