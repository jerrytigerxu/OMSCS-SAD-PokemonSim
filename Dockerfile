# Compile our Java files in this container (builder stage)
FROM gradle:7.5-jdk17 AS builder

# Set the working directory
WORKDIR /usr/src/cs6310

# Copy the entire project source code
COPY . .

# Build the project
RUN gradle build --no-daemon

# Final stage: Create the runtime container
FROM openjdk:22-slim

# Set the working directory
WORKDIR /usr/src/cs6310

# Copy the test scenarios and results from the host to the container
COPY test_scenarios ./
COPY test_results ./

# Copy the JAR file created in the builder stage (from the build/libs directory)
COPY --from=builder /usr/src/cs6310/build/libs/pokemon-1.0-SNAPSHOT.jar ./pokemon.jar

EXPOSE 8080


# Set the default command to run the JAR file
ENTRYPOINT ["java", "-jar", "pokemon.jar"]
