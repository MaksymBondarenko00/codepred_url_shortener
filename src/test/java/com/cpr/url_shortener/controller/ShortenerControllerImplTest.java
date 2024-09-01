package com.cpr.url_shortener.controller;

import com.cpr.url_shortener.entity.Redirect;
import com.cpr.url_shortener.exception.AliasAlreadyExistException;
import com.cpr.url_shortener.exception.AliasNotFoundException;
import com.cpr.url_shortener.request.ShortenerCreationRequest;
import com.cpr.url_shortener.service.ShortenerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ShortenerControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ShortenerService service;

    @Test
    void handleShortenerTest() throws Exception {
        String alias = "ytb";
        String url = "https://www.youtube.com";
        Redirect expected = new Redirect(url, alias);

        given(service.getRedirect(alias)).willReturn(expected);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/shortener/v1/{alias}", expected.getAlias()))
                .andExpect(status().isMovedPermanently())
                .andExpect(header().string("Location", url))
                .andReturn();
    }

    @Test
    void handleShortenerAliasNotFoundTest() throws Exception {
        String alias = "nonexistent";

        given(service.getRedirect(alias)).willThrow(new AliasNotFoundException("Alias not found: " + alias));

        mockMvc.perform(MockMvcRequestBuilders.get("/shortener/v1/{alias}", alias)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createShortenerPositiveTest() throws Exception {
        ShortenerCreationRequest creationRequest = new ShortenerCreationRequest("https://www.youtube.com", "short");

        mockMvc.perform(MockMvcRequestBuilders.post("/shortener/v1/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(creationRequest)))
                .andExpect(status().isOk());
    }

    @Test
    void createShortenerAliasAlreadyExistsTest() throws Exception {
        ShortenerCreationRequest creationRequest = new ShortenerCreationRequest("https://www.youtube.com", "unique");

        Mockito.when(service.reduce(Mockito.any(ShortenerCreationRequest.class)))
                .thenThrow(new AliasAlreadyExistException("This alias is already exist"));

        mockMvc.perform(MockMvcRequestBuilders.post("/shortener/v1/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(creationRequest)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}
