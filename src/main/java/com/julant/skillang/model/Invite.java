package com.julant.skillang.model;

import jakarta.persistence.*;

@Entity
public class Invite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "from",nullable = false)
    @ManyToOne
    private User from;

    @JoinColumn(name = "to",nullable = false)
    @ManyToOne
    private User to;

    @Enumerated(EnumType.ORDINAL)
    private InviteStatus status;


}
