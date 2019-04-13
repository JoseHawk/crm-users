CREATE TABLE authenticated_user
(
    email VARCHAR(50) NOT NULL PRIMARY KEY,
    secret VARCHAR(255),
    user_role VARCHAR(50)
);

CREATE TABLE oauth_access_token
(
    authentication_id VARCHAR(255) NOT NULL PRIMARY KEY,
    token_id VARCHAR(255),
    token BLOB,
    user_name VARCHAR(255),
    client_id VARCHAR(255),
    authentication BLOB,
    refresh_token VARCHAR(255)
);

CREATE TABLE oauth_refresh_token
(
    token_id       VARCHAR(255),
    token          BLOB,
    authentication BLOB
);

INSERT INTO authenticated_user (email, secret, user_role)
VALUES ('admin@joselara.com', '$2a$10$wT.xcX..aemTE7CA9Y26ZO0X.92WOgHeDkhMUh9suDySRmsxDa6Ti', 'ROLE_ADMIN');

INSERT INTO authenticated_user (email, secret, user_role)
VALUES ('user@joselara.com', '$2a$10$o4J7xJRo8dNgoote.q6rBuKQuBODOfj2hLH8t2Wz13pxZAdiKic9K', 'ROLE_USER');