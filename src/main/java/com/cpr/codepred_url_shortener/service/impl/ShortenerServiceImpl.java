package com.cpr.codepred_url_shortener.service.impl;

import com.cpr.codepred_url_shortener.entity.Redirect;
import com.cpr.codepred_url_shortener.exception.AliasAlreadyExistException;
import com.cpr.codepred_url_shortener.exception.AliasNotFoundException;
import com.cpr.codepred_url_shortener.repository.ShortenerRepository;
import com.cpr.codepred_url_shortener.request.ShortenerCreationRequest;
import com.cpr.codepred_url_shortener.service.ShortenerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShortenerServiceImpl implements ShortenerService {

    private final ShortenerRepository repository;

    @Override
    public Redirect reduce(ShortenerCreationRequest request) {
        if (repository.existsByAlias(request.getAlias())) {
            throw new AliasAlreadyExistException("This alias is already exist");
        }

        return repository.save(new Redirect(request.getUrl(), request.getAlias()));
    }

    @Override
    public Redirect getRedirect(String alias) {
        try {
            return repository.findByAlias(alias);
        } catch (Exception e) {
            throw new AliasNotFoundException("Alias with name: '" + alias + "' not found");
        }
    }
}
