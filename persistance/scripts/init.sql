
-- Create the database 'game' if it doesn't exist
DO
$$
BEGIN
    IF NOT EXISTS (
        SELECT FROM pg_database WHERE datname = 'game'
    ) THEN
        CREATE DATABASE game;
    END IF;
END
$$;

-- Connect to the 'game' database
\c game;

-- Create the 'TournamentHistory' table
CREATE TABLE IF NOT EXISTS TournamentHistory (
    id SERIAL PRIMARY KEY,
    num_pokemons INT NOT NULL,
    name_pokemons TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create the 'BattleHistory' table
CREATE TABLE IF NOT EXISTS BattleHistory (
    id SERIAL PRIMARY KEY,
    pokemonA TEXT NOT NULL,
    pokemonB TEXT NOT NULL,
    winner BOOLEAN,
    tournament_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_tournament
        FOREIGN KEY (tournament_id)
        REFERENCES TournamentHistory(id)
        ON DELETE CASCADE
);


