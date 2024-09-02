package com.cpr.url_shortener.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
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

    @Override
    public String toString() {
        return "Redirect{" +
                "alias='" + alias + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Redirect redirect = (Redirect) o;
        return Objects.equals(id, redirect.id) && Objects.equals(alias, redirect.alias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, alias);
    }
}
