ALTER TABLE countryng
ADD COLUMN last_modify_date TIMESTAMP;

-- Update existing records with current timestamp
UPDATE countryng
SET last_modify_date = CURRENT_TIMESTAMP;
