# Pokemon Battle Simulator (CS 6310 Assignment #3 - Group 55)

## Overview

This project is a Pokémon battle simulator that allows users to create teams of Pokémon, engage in battles, and even conduct tournaments. It features a graphical user interface (GUI) for intuitive interaction and visualization of the simulation state.

**Key Features:**

* **Tournament Mode:** Conduct knockout-style tournaments with four or more Pokémon.
* **Skill Point System:**  Adds a tactical layer to battles by limiting move usage based on SP.
* **Enhanced Error Handling:**  Provides informative feedback for invalid inputs and prevents crashes.
* **System Robustness:** Utilizes retries to handle temporary database or service disruptions.
* **Data Archiving:** Automatically purges old battle history to maintain database efficiency.

## Getting Started

### Prerequisites
* Docker and Docker Compose

### Installation
1. **Clone the repository:** `git clone https://github.gatech.edu/umilojkovic3/PokemonA3.git`
2. **Navigate to the project directory:** `cd PokemonA3`
3. **Start the application:** `docker-compose up -d`

This will build and start all the services defined in `docker-compose.yml`.

### Accessing the Application

* **Frontend:** The frontend will be accessible at `http://localhost:3000` (or the port you've configured).
* **Backend:** The backend API will be accessible at `http://localhost:8080` (or the port you've configured).


## Usage

###  Battling
* **[Step 1]:** Select the Pokemon you want to use in battle.
* **[Step 2]:** Select the moves for your Pokemon.
* **[Step 3]:** Initiate a battle with another user or against a computer opponent.
* **[Step 4]:** During the battle, select moves to attack your opponent and defend against their attacks.
* **[Step 5]:** The battle continues until one player's Pokemon have all fainted.

### Tournament Mode

To start a tournament, use the `tournament` command followed by a list of at least four Pokémon names (separated by commas). The tournament will proceed in knockout rounds, with pairings based on the order of Pokémon provided. The GUI will display the progress and results of each round.

### Skill Points

Each Pokémon has a maximum SP pool and a current SP value. Moves consume SP, and Pokémon can only use moves for which they have sufficient SP. SP is restored after each battle.


## Contributing

[//]: # (Provide guidelines for contributing to the project -  Specify the workflow for contributions (e.g., branching, pull requests).)

## License

[//]: # (Specify the license under which the project is distributed - Choose an appropriate open-source license (e.g., MIT License).)

## Contact

[//]: # (Provide contact information for questions or support)

## Acknowledgements

[//]: # (Acknowledge any third-party libraries, tools, or resources used)



