ALTER TABLE site_images ADD COLUMN approval_status VARCHAR(10) DEFAULT 'SUBMITTED';
ALTER TABLE site_images ADD COLUMN anonymous BOOLEAN DEFAULT FALSE;
