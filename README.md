# Pokemon Battle Simulator (CS 6310 Assignment #3 - Group 55)

## Overview
This project is a Pokémon battle simulator that allows users to create teams of Pokémon, engage in battles, and even conduct tournaments. It features a graphical user interface (GUI) for intuitive interaction and visualization of the simulation state.

**Key Features:**
* **Tournament Mode:** Conduct knockout-style tournaments with four or more Pokémon.
* **Skill Point System:**  Adds a tactical layer to battles by limiting move usage based on SP.
* **Enhanced Error Handling:**  Provides informative feedback for invalid inputs and prevents crashes.
* **System Robustness:** Utilizes retries to handle temporary database or service disruptions.
* **Data Archiving:** Automatically purges old battle history to maintain database efficiency.

---
## Getting Started

### Prerequisites
* Java 17
* Docker or Docker Desktop (recommended)
### Installation
1. **Clone the repository:** `git clone https://github.gatech.edu/umilojkovic3/PokemonA3.git`
1. **Navigate to the project directory:** `cd PokemonA3`
1. **Ensure Docker or Docker Desktop is running.**
1. **Start the application:** `docker-compose up -d`

This will build and start all the services defined in `docker-compose.yml`.

### Accessing the Application
* **Frontend:** The frontend will be accessible at `http://localhost:3000`.
* **Backend:** The backend API will be accessible at `http://localhost:8080`.

---
## Usage

### Battles
To simulate a battle:
1. Select two Pokemon from the list.
2. Click "Start Battle".
3. View the battle simulation and results.

### Tournaments
To run a tournament:
1. Select four or more Pokemon (use Ctrl + click for multiple selections).
2. Click "Start Tournament".
3. View the tournament progression, individual battle results, and the overall winner.

---
## Contact
- Uros Milojkovic - umilojkovic@gatech.edu 
- Eduardo Rodriguez - escareno@gatech.edu 
- Sahal Shaikh - sshaikh65@gatech.edu 
- Samuel Hrncir - shrncir3@gatech.edu 
- Jere Xu - jxu372@gatech.edu



