package hhn.aib.thesis.postgraph.model;

import jakarta.persistence.*;

@Entity
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long iid;

    @Column(name = "title")
    private String title;

    @Column(name = "state")
    private String state;

    @Column(name = "stateReason")
    private String stateReason;
}
