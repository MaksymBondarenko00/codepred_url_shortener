package com.cpr.codepred_url_shortener.controller;

import com.cpr.codepred_url_shortener.entity.Redirect;
import com.cpr.codepred_url_shortener.request.ShortenerCreationRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;

public interface ShortenerController {


    @Operation(
            summary = "Find redirect link by alias",
            description = "Find a specific redirect link by its alias",
            tags = {"Redirect"},
            parameters = {
                    @Parameter(
                            name = "alias",
                            required = true,
                            description = "The alias of the link",
                            schema = @Schema(type = "string")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The apartment category details",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Redirect.class)
                            )
                    )
            }
    )
    ResponseEntity<?> handleShortener(String alias) throws URISyntaxException;


    @Operation(
            summary = "Create a new redirect link",
            description = "Create a new redirect link that sends to the desired page",
            tags = {"Redirect"},
            requestBody = @RequestBody(
                    description = "The alias of the link that is required to create it",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Redirect.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Created redirect link",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Redirect.class)
                            )
                    )
            }
    )
    ResponseEntity<?> createShortener(ShortenerCreationRequest creationRequest);
}
