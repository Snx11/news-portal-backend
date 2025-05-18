-- Create tables with explicit ID generation
CREATE TABLE IF NOT EXISTS news (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    image VARCHAR(255),
    category VARCHAR(255),
    author VARCHAR(255),
    date VARCHAR(255),
    created_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS finance (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(255) NOT NULL,
    value DOUBLE,
    change DOUBLE,
    change_percent DOUBLE
);

CREATE TABLE IF NOT EXISTS weather (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    date VARCHAR(255),
    temperature DOUBLE,
    condition VARCHAR(255),
    icon VARCHAR(255),
    city VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS history (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    news_id INTEGER,
    title VARCHAR(255),
    viewed_at TIMESTAMP,
    user_id VARCHAR(255)
);

-- SQLite'da indeksler oluşturalım (performans için)
CREATE INDEX IF NOT EXISTS idx_news_category ON news(category);
CREATE INDEX IF NOT EXISTS idx_news_created_at ON news(created_at);
CREATE INDEX IF NOT EXISTS idx_weather_city ON weather(city);
CREATE INDEX IF NOT EXISTS idx_history_user_id ON history(user_id);
