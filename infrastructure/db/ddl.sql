-- User related tables --

CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    full_name TEXT,
    designation TEXT,
    provider TEXT,
    email_address TEXT NOT NULL UNIQUE,
    timezone TEXT,
    country TEXT,
    country_extension VARCHAR(3) NOT NULL,
    phone_number VARCHAR(10) NOT NULL,
    notification_enabled boolean DEFAULT false,
    active boolean DEFAULT true,
    verified boolean DEFAULT false,
);

CREATE TABLE IF NOT EXISTS event_logs (
    fk_id UUID,
    event_type TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    last_updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);