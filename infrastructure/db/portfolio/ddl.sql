CREATE TYPE transaction_type AS ENUM ('investment', 'expense', 'travel_fund');

CREATE TABLE IF NOT EXISTS portfolio (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID REFERENCES users(id),
    extra_cash DOUBLE PRECISION DEFAULT 0.0,
    salary DOUBLE PRECISION DEFAULT 0.0,
    investment DOUBLE PRECISION DEFAULT 0.0,
    expense DOUBLE PRECISION DEFAULT 0.0,
    bank_balance DOUBLE PRECISION DEFAULT 0.0,
    transaction_date DATE NOT NULL DEFAULT CURRENT_DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS investment_category (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name TEXT UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS expense_category (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name TEXT UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS transactions (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID REFERENCES users(id),
    type transaction_type NOT NULL,
    category_id UUID,
    amount DOUBLE PRECISION DEFAULT 0.0,
    transaction_date DATE NOT NULL DEFAULT CURRENT_DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);