package com.cpr.codepred_url_shortener.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "redirect")
@NoArgsConstructor
public class Redirect {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    @JsonIgnore
    private UUID id;

    @Column(nullable = false, unique = true, name = "url")
    private String url;

    @Column(nullable = false, name = "alias")
    private String alias;

    public Redirect(String url, String alias) {
        this.url = url;
        this.alias = alias;
    }

}
