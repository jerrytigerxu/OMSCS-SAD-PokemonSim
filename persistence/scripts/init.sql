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

-- Create the 'TournamentResult' table
CREATE TABLE IF NOT EXISTS tournament_result (
    id SERIAL PRIMARY KEY,
    winnerPokemon TEXT,
    createdTimestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create the 'BattleResult' table
CREATE TABLE IF NOT EXISTS battle_result (
    id SERIAL PRIMARY KEY,
    winnerPokemon TEXT,
    loserPokemon TEXT,
    orderOfBattle TEXT,
    createdTimestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    tournament_id INT,
    CONSTRAINT fk_tournament
        FOREIGN KEY (tournament_id)
        REFERENCES tournament_result(id)
        ON DELETE CASCADE
);


