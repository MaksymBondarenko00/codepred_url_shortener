package com.cpr.codepred_url_shortener.repository;

import com.cpr.codepred_url_shortener.entity.Redirect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ShortenerRepository extends JpaRepository<Redirect, UUID> {
    Boolean existsByAlias(String alias);

    Redirect findByAlias(String alias);
}
