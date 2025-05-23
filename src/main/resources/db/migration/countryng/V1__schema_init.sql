create extension if not exists "uuid-ossp";

create table if not exists "countryng"
(
    id           UUID unique  not null default uuid_generate_v1(),
    country_name varchar(255) not null,
    country_code varchar(2)   not null,
    primary key (id)
);

INSERT INTO countryng (country_name, country_code)
VALUES ('Argentina', 'AR'),
       ('Bolivia', 'BO'),
       ('Peru', 'PE'),
       ('Colombia', 'CO'),
       ('Venezuela', 'VE'),
       ('Mexico', 'MX'),
       ('Paraguay', 'PY'),
       ('Brazil', 'BR'),
       ('Chile', 'CL'),
       ('Uruguay', 'UY'),
       ('Guyana', 'GY'),
       ('Suriname', 'SR'),
       ('Ecuador', 'EC'),
       ('Panama', 'PA'),
       ('Costa Rica', 'CR'),
       ('Nicaragua', 'NI'),
       ('Honduras', 'HN'),
       ('El Salvador', 'SV'),
       ('Guatemala', 'GT'),
       ('Belize', 'BZ'),
       ('Cuba', 'CU'),
       ('Jamaica', 'JM'),
       ('Puerto Rico', 'PR'),
       ('Dominican Republic', 'DO'),
       ('Haiti', 'HT'),
       ('Czech Republic', 'CZ'),
       ('Slovakia', 'SK'),
       ('Hungary', 'HU'),
       ('Poland', 'PL'),
       ('Austria', 'AT'),
       ('Switzerland', 'CH'),
       ('Germany', 'DE'),
       ('France', 'FR'),
       ('Belgium', 'BE'),
       ('Netherlands', 'NL'),
       ('Italy', 'IT'),
       ('Spain', 'ES'),
       ('Portugal', 'PT'),
       ('United Kingdom', 'GB'),
       ('Ireland', 'IE'),
       ('Denmark', 'DK'),
       ('Norway', 'NO'),
       ('Sweden', 'SE'),
       ('Finland', 'FI'),
       ('Iceland', 'IS'),
       ('Albania', 'AL'),
       ('Bosnia and Herzegovina', 'BA'),
       ('Montenegro', 'ME'),
       ('Serbia', 'RS'),
       ('North Macedonia', 'MK'),
       ('Kosovo', 'XK'),
       ('Greece', 'GR'),
       ('Turkey', 'TR'),
       ('Cyprus', 'CY'),
       ('Malta', 'MT'),
       ('Russia', 'RU'),
       ('Ukraine', 'UA'),
       ('Belarus', 'BY'),
       ('Moldova', 'MD'),
       ('Lithuania', 'LT'),
       ('Latvia', 'LV'),
       ('Estonia', 'EE'),
       ('Georgia', 'GE'),
       ('Armenia', 'AM'),
       ('Azerbaijan', 'AZ'),
       ('Kazakhstan', 'KZ'),
       ('Uzbekistan', 'UZ'),
       ('Turkmenistan', 'TM'),
       ('Kyrgyzstan', 'KG'),
       ('Tajikistan', 'TJ'),
       ('Afghanistan', 'AF'),
       ('Pakistan', 'PK'),
       ('India', 'IN'),
       ('Nepal', 'NP'),
       ('Bhutan', 'BT'),
       ('Bangladesh', 'BD'),
       ('Myanmar', 'MM'),
       ('Thailand', 'TH'),
       ('Lao PDR', 'LA'),
       ('Vietnam', 'VN'),
       ('Philippines', 'PH'),
       ('Malaysia', 'MY'),
       ('Brunei Darussalam', 'BN'),
       ('Singapore', 'SG'),
       ('Indonesia', 'ID'),
       ('Timor-Leste', 'TL'),
       ('Papua New Guinea', 'PG'),
       ('Solomon Islands', 'SB'),
       ('New Zealand', 'NZ'),
       ('Australia', 'AU'),
       ('Fiji', 'FJ'),
       ('Samoa', 'WS'),
       ('Tonga', 'TO'),
       ('Vanuatu', 'VU'),
       ('Kiribati', 'KI'),
       ('Tuvalu', 'TV'),
       ('Micronesia', 'FM'),
       ('Palau', 'PW'),
       ('Marshall Islands', 'MH'),
       ('Nauru', 'NR'),
       ('Madagascar', 'MG'),
       ('Djibouti', 'DJ'),
       ('Sudan', 'SD'),
       ('South Sudan', 'SS'),
       ('Chad', 'TD'),
       ('Central African Republic', 'CF'),
       ('Cameroon', 'CM'),
       ('Nigeria', 'NG'),
       ('Niger', 'NE'),
       ('Benin', 'BJ'),
       ('Togo', 'TG'),
       ('Ghana', 'GH'),
       ('Burkina Faso', 'BF'),
       ('Mali', 'ML'),
       ('Senegal', 'SN'),
       ('The Gambia', 'GM'),
       ('Guinea', 'GN'),
       ('Guinea-Bissau', 'GW'),
       ('Liberia', 'LR'),
       ('Sierra Leone', 'SL'),
       ('Ivory Coast', 'CI'),
       ('Tanzania', 'TZ'),
       ('Uganda', 'UG'),
       ('Rwanda', 'RW'),
       ('Burundi', 'BI'),
       ('Zambia', 'ZM'),
       ('Malawi', 'MW'),
       ('Angola', 'AO'),
       ('Namibia', 'NA'),
       ('Botswana', 'BW'),
       ('Zimbabwe', 'ZW'),
       ('South Africa', 'ZA'),
       ('Lesotho', 'LS'),
       ('Eswatini', 'SZ'),
       ('Mauritania', 'MR'),
       ('Mozambique', 'MZ'),
       ('Western Sahara', 'EH'),
       ('Morocco', 'MA'),
       ('Algeria', 'DZ'),
       ('Tunisia', 'TN'),
       ('Libya', 'LY'),
       ('Egypt', 'EG'),
       ('Eritrea', 'ER'),
       ('Somalia', 'SO'),
       ('Seychelles', 'SC'),
       ('Comoros', 'KM'),
       ('Mauritius', 'MU');