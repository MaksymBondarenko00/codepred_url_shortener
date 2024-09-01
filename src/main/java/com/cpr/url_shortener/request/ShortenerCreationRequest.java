package com.cpr.url_shortener.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShortenerCreationRequest {

    @NonNull
    private String url;

    @NonNull
    private String alias;
}
