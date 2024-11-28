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

* **Frontend:** The frontend will be accessible at `http://localhost:3000`.
* **Backend:** The backend API will be accessible at `http://localhost:8080`.


## Usage

### Battles
- [maybe include images?]

### Tournaments
- [maybe include images?]


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



