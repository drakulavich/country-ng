-- Add unique constraint to country_code column
ALTER TABLE countryng
ADD CONSTRAINT unique_country_code UNIQUE (country_code);