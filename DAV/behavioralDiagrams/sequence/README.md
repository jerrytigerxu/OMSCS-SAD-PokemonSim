## Skill Point System
- Objects: Pokemon1, Pokemon2, Move, Battle
- Sequence:
	- Battle checks Pokemon's currentSP of the active Pokemon
	- Pokemon filters available Moves based on SPCost and its current SP
	- Pokemon selects a Move from the list of affordable moves (randomly from those with sufficient SP)
	- Battle executes the Move, applying its effects (damage, damage, etc.) to the inactive Pokemon
	- The active Pokemon's currentSP is reduced by Move's SP Cost
	- (At the end of the turn) Pokemon restores SP (similar to restoration of HP)
## System Robustness with Retries
- Objects: PokemonService, PokemonRepository, Database
- Sequence:
	- PokemonService attempts a database operation (such as saving a new Pokemon)
	- PokemonRepository executes a database query
	- Alternative frame:
		- If database error occurs: Spring Retry annotation triggers retry mechanism -> PokemonRepository re-attempts the database query (for a number of times that can be configured) => if all retries fail, PokemonService handles the error (logs the error or throws an exception)
		- If successful: PokemonService proceeds

## Archivability through Purging
- Objects: PurgeService, ResultRepository, Database, ShedLock
- Sequence
	- CRON job triggers PurgeService at scheduled time
	- (optional) ShedLock ensures only one instance of PurgeService is running
	- PurgeService calls findOldResults() on ResultRepository
	- ResultRepository queries the Database for Result objects with createTimestamp older than 1 year
	- Database returns the list of old Result objects to ResultRepository
	- ResultRepository returns the list of old Result objects to PurgeService
	- PurgeService calls deleteAll() on ResultRepository, passing the list of old Result objects
	- ResultRepository executes the delete operation on the Database