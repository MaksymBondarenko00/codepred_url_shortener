package com.cpr.url_shortener.controller.impl;

import com.cpr.url_shortener.controller.ShortenerController;
import com.cpr.url_shortener.entity.Redirect;
import com.cpr.url_shortener.request.ShortenerCreationRequest;
import com.cpr.url_shortener.service.ShortenerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/shortener/v1")
@RequiredArgsConstructor
public class ShortenerControllerImpl implements ShortenerController {

    private final ShortenerService service;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{alias}")
    public ResponseEntity<?> handleShortener(@PathVariable String alias) throws URISyntaxException {
        Redirect redirect = service.getRedirect(alias);
        URI uri = new URI(redirect.getUrl());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public ResponseEntity<?> createShortener(@RequestBody ShortenerCreationRequest creationRequest) {
        return ResponseEntity.ok(service.reduce(creationRequest));
    }
}
