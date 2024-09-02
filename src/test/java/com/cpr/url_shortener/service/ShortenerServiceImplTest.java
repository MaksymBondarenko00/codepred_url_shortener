package com.cpr.url_shortener.service;

import com.cpr.url_shortener.entity.Redirect;
import com.cpr.url_shortener.request.ShortenerCreationRequest;
import com.cpr.url_shortener.service.impl.ShortenerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/db/drop-tables.sql")
@Sql("/db/create-tables.sql")
@Sql("/db/insert-tables.sql")
public class ShortenerServiceImplTest {

    @Autowired
    private ShortenerServiceImpl shortenerService;

    @Test
    void reduce(){
        ShortenerCreationRequest request = new ShortenerCreationRequest();
        request.setUrl("https://www.google.com");
        request.setAlias("ggl");

        Redirect expected = new Redirect();
        expected.setUrl("https://www.google.com");
        expected.setAlias("ggl");

        Redirect actual = shortenerService.reduce(request);

        Assertions.assertEquals(expected.getAlias(), actual.getAlias());
        Assertions.assertEquals(expected.getUrl(), actual.getUrl());
    }

    @Test
    void getRedirect(){
        Redirect expected = get();

        Redirect actual = shortenerService.getRedirect(expected.getAlias());
        Assertions.assertEquals(expected, actual);
    }

    private Redirect get(){
        Redirect redirect = new Redirect();
        redirect.setUrl("https://mvnrepository.com/");
        redirect.setAlias("maven");
        redirect.setId(UUID.fromString("d1fd8b79-90aa-4f4a-ae0c-8ae2069443e5"));
        return redirect;
    }
}
