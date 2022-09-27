create table campaign_ad_contents_ratio
(
    id         bigint auto_increment
        primary key,
    type       varchar(6)  null,
    ad         int         not null,
    contents   int         not null,
    created_at datetime(6) null,
    updated_at datetime(6) null,
    constraint UK1wguf1n2r3ikdokiwodd9cfg9
        unique (type)
);

create table campaign_ads
(
    id                     bigint auto_increment
        primary key,
    name                   varchar(64)  not null,
    image_url              varchar(256) not null,
    first_display_priority int          not null,
    first_display_weight   int          not null,
    frequency              int          not null,
    landing_url            varchar(256) not null
);

create table campaign_contents
(
    id                     bigint auto_increment
        primary key,
    name                   varchar(64)  not null,
    image_url              varchar(256) not null,
    first_display_priority int          not null,
    first_display_weight   int          not null,
    frequency              int          not null,
    landing_url            varchar(256) not null
);

create index IDXnfvhydxyeeplnjs2jrxbgy2cu on campaign_ads (first_display_priority, first_display_weight DESC);

INSERT INTO campaign_ad_contents_ratio (id, type, ad, contents, created_at, updated_at) VALUES (1, 'FIRST', 3, 1, '2022-09-23 12:44:17', '2022-09-23 12:44:18');

INSERT INTO campaign_ads (id, name, image_url, first_display_priority, first_display_weight, frequency, landing_url) VALUES (1, 'test campaign 1', 'http://google.com', 10, 1, 1800, 'http://google.com');
INSERT INTO campaign_ads (id, name, image_url, first_display_priority, first_display_weight, frequency, landing_url) VALUES (2, 'test campaign 2', 'http://google.com', 10, 1, 900, 'http://www.naver.com');
INSERT INTO campaign_ads (id, name, image_url, first_display_priority, first_display_weight, frequency, landing_url) VALUES (3, 'test campaign 3', 'http://google.com', 9, 1, 300, 'http://www.daum.net');
INSERT INTO campaign_ads (id, name, image_url, first_display_priority, first_display_weight, frequency, landing_url) VALUES (4, 'test campaign 4', 'http://google.com', 10, 1, 450, 'http://google.com');
INSERT INTO campaign_ads (id, name, image_url, first_display_priority, first_display_weight, frequency, landing_url) VALUES (5, 'test campaign 5', 'http://google.com', 10, 1, 1400, 'http://www.facebook.com');
INSERT INTO campaign_ads (id, name, image_url, first_display_priority, first_display_weight, frequency, landing_url) VALUES (6, 'test campaign 6', 'http://google.com', 9, 2, 800, 'http://trello.com');
INSERT INTO campaign_ads (id, name, image_url, first_display_priority, first_display_weight, frequency, landing_url) VALUES (7, 'test campaign 7', 'http://google.com', 10, 1, 1200, 'http://slack.com ');
INSERT INTO campaign_ads (id, name, image_url, first_display_priority, first_display_weight, frequency, landing_url) VALUES (8, 'test campaign 8', 'http://google.com', 10, 1, 450, 'https://facebook.github.io/react-native/ ');
INSERT INTO campaign_ads (id, name, image_url, first_display_priority, first_display_weight, frequency, landing_url) VALUES (9, 'test campaign 9', 'http://google.com', 9, 3, 300, 'https://www.djangoproject.com/ ');
INSERT INTO campaign_ads (id, name, image_url, first_display_priority, first_display_weight, frequency, landing_url) VALUES (10, 'test campaign 10', 'http://google.com', 10, 1, 600, 'http://www.django-rest-framework.org/ ');
INSERT INTO campaign_ads (id, name, image_url, first_display_priority, first_display_weight, frequency, landing_url) VALUES (11, 'test campaign 11', 'http://google.com', 10, 1, 800, 'https://nodejs.org/en/ ');
INSERT INTO campaign_ads (id, name, image_url, first_display_priority, first_display_weight, frequency, landing_url) VALUES (12, 'test campaign 12', 'http://google.com', 10, 1, 1600, 'https://angularjs.org/ ');
INSERT INTO campaign_ads (id, name, image_url, first_display_priority, first_display_weight, frequency, landing_url) VALUES (13, 'test campaign 13', 'http://google.com', 10, 1, 500, 'https://aws.amazon.com/documentation/dynamodb/ ');
INSERT INTO campaign_ads (id, name, image_url, first_display_priority, first_display_weight, frequency, landing_url) VALUES (14, 'test campaign 14', 'http://google.com', 10, 1, 300, 'http://github.com ');
INSERT INTO campaign_ads (id, name, image_url, first_display_priority, first_display_weight, frequency, landing_url) VALUES (15, 'test campaign 15', 'http://google.com', 10, 1, 1100, 'http://apple.com ');
INSERT INTO campaign_ads (id, name, image_url, first_display_priority, first_display_weight, frequency, landing_url) VALUES (16, 'test campaign 16', 'http://google.com', 10, 1, 420, 'https://techcrunch.com/ ');
INSERT INTO campaign_ads (id, name, image_url, first_display_priority, first_display_weight, frequency, landing_url) VALUES (17, 'test campaign 17', 'http://google.com', 10, 1, 720, 'https://www.amazon.com/ ');
INSERT INTO campaign_ads (id, name, image_url, first_display_priority, first_display_weight, frequency, landing_url) VALUES (18, 'test campaign 18', 'http://google.com', 10, 1, 600, 'https://www.jetbrains.com/');

INSERT INTO campaign_contents (id, name, image_url, first_display_priority, first_display_weight, frequency, landing_url) VALUES (10001, 'test campaign 1', 'http://google.com', 10, 1, 1800, 'http://google.com ');
INSERT INTO campaign_contents (id, name, image_url, first_display_priority, first_display_weight, frequency, landing_url) VALUES (10002, 'test campaign 2', 'http://google.com', 10, 1, 900, 'http://www.naver.com ');
INSERT INTO campaign_contents (id, name, image_url, first_display_priority, first_display_weight, frequency, landing_url) VALUES (10003, 'test campaign 3', 'http://google.com', 9, 1, 300, 'http://www.daum.net ');
INSERT INTO campaign_contents (id, name, image_url, first_display_priority, first_display_weight, frequency, landing_url) VALUES (10004, 'test campaign 4', 'http://google.com', 10, 1, 450, 'http://google.com');
INSERT INTO campaign_contents (id, name, image_url, first_display_priority, first_display_weight, frequency, landing_url) VALUES (10005, 'test campaign 5', 'http://google.com', 10, 1, 1400, 'http://www.facebook.com ');
INSERT INTO campaign_contents (id, name, image_url, first_display_priority, first_display_weight, frequency, landing_url) VALUES (10006, 'test campaign 6', 'http://google.com', 9, 2, 800, 'http://trello.com ');
INSERT INTO campaign_contents (id, name, image_url, first_display_priority, first_display_weight, frequency, landing_url) VALUES (10007, 'test campaign 7', 'http://google.com', 10, 1, 1200, 'http://slack.com ');
INSERT INTO campaign_contents (id, name, image_url, first_display_priority, first_display_weight, frequency, landing_url) VALUES (10008, 'test campaign 8', 'http://google.com', 10, 1, 450, 'https://facebook.github.io/react-native/ ');
INSERT INTO campaign_contents (id, name, image_url, first_display_priority, first_display_weight, frequency, landing_url) VALUES (10009, 'test campaign 9', 'http://google.com', 9, 3, 300, 'https://www.djangoproject.com/ ');
INSERT INTO campaign_contents (id, name, image_url, first_display_priority, first_display_weight, frequency, landing_url) VALUES (100010, 'test campaign 10', 'http://google.com', 10, 1, 600, 'http://www.django-rest-framework.org/ ');
INSERT INTO campaign_contents (id, name, image_url, first_display_priority, first_display_weight, frequency, landing_url) VALUES (100011, 'test campaign 11', 'http://google.com', 10, 1, 800, 'https://nodejs.org/en/ ');
INSERT INTO campaign_contents (id, name, image_url, first_display_priority, first_display_weight, frequency, landing_url) VALUES (100012, 'test campaign 12', 'http://google.com', 10, 1, 1600, 'https://angularjs.org/ ');
INSERT INTO campaign_contents (id, name, image_url, first_display_priority, first_display_weight, frequency, landing_url) VALUES (100013, 'test campaign 13', 'http://google.com', 10, 1, 500, 'https://aws.amazon.com/documentation/dynamodb/ ');
INSERT INTO campaign_contents (id, name, image_url, first_display_priority, first_display_weight, frequency, landing_url) VALUES (100014, 'test campaign 14', 'http://google.com', 10, 1, 300, 'http://github.com ');
INSERT INTO campaign_contents (id, name, image_url, first_display_priority, first_display_weight, frequency, landing_url) VALUES (100015, 'test campaign 15', 'http://google.com', 10, 1, 1100, 'http://apple.com ');
INSERT INTO campaign_contents (id, name, image_url, first_display_priority, first_display_weight, frequency, landing_url) VALUES (100016, 'test campaign 16', 'http://google.com', 10, 1, 420, 'https://techcrunch.com/ ');
INSERT INTO campaign_contents (id, name, image_url, first_display_priority, first_display_weight, frequency, landing_url) VALUES (100017, 'test campaign 17', 'http://google.com', 10, 1, 720, 'https://www.amazon.com/ ');
INSERT INTO campaign_contents (id, name, image_url, first_display_priority, first_display_weight, frequency, landing_url) VALUES (100018, 'test campaign 18', 'http://google.com', 10, 1, 600, 'https://www.jetbrains.com/');
