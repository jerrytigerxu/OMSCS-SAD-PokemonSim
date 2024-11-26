FROM postgres:15.10-alpine

# Set environment variables for PostgreSQL
ENV POSTGRES_DB=game
ENV POSTGRES_USER=wizard
ENV POSTGRES_PASSWORD=wizardOfOZ

# Expose PostgreSQL default port
EXPOSE 5432

# initialization scripts
COPY ./scripts/init.sql /docker-entrypoint-initdb.d/

