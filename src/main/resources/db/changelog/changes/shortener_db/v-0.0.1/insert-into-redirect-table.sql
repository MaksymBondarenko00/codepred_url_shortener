-- liquibase formatted sql

-- changeset liquibase:1

INSERT INTO redirect(id, url, alias)
VALUES
    (UNHEX('d1fd8b7990aa4f4aae0c8ae2069443e3'), 'https://open.spotify.com/user/9rybo9ukif9c4ss56929yi2r1', 'my_spotify'),
    (UNHEX('d1fd8b7990aa4f4aae0c8ae2069443e4'), 'https://github.com/BondarenkoMM/url_shortener_codepred', 'project_on_github'),
    (UNHEX('d1fd8b7990aa4f4aae0c8ae2069443e5'), 'https://mvnrepository.com/', 'maven');

