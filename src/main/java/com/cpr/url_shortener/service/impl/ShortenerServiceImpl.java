package com.cpr.url_shortener.service.impl;

import com.cpr.url_shortener.entity.Redirect;
import com.cpr.url_shortener.exceptionHandler.ErrorMessage;
import com.cpr.url_shortener.exceptionHandler.exception.AliasAlreadyExistException;
import com.cpr.url_shortener.exceptionHandler.exception.AliasNotFoundException;
import com.cpr.url_shortener.repository.ShortenerRepository;
import com.cpr.url_shortener.request.ShortenerCreationRequest;
import com.cpr.url_shortener.service.ShortenerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShortenerServiceImpl implements ShortenerService {

    private final ShortenerRepository repository;

    @Override
    public Redirect reduce(ShortenerCreationRequest request) {
        if (repository.existsByAlias(request.getAlias())) {
            throw new AliasAlreadyExistException(ErrorMessage.ALIAS_ALREADY_EXIST);
        }

        return repository.save(new Redirect(request.getUrl(), request.getAlias()));
    }

    @Override
    public Redirect getRedirect(String alias) {
        try {
            return repository.findByAlias(alias);
        } catch (Exception e) {
            throw new AliasNotFoundException(ErrorMessage.ALIAS_NOT_FOUND);
        }
    }
}
