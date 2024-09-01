package com.cpr.url_shortener.service;


import com.cpr.url_shortener.entity.Redirect;
import com.cpr.url_shortener.request.ShortenerCreationRequest;

public interface ShortenerService {

    Redirect reduce(ShortenerCreationRequest shortenerCreationRequest);

    Redirect getRedirect(String alias);
}
