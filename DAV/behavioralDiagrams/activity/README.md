
- [UML Activity Diagram Tutorial](https://www.lucidchart.com/pages/uml-activity-diagram)

---
## Skill Point System
- Start
- Check Pokemon's current SP
- If SP sufficient:
	- Select move (from available moves with enough SP)
	- Execute move
	- Deduct SP cost from Pokemon's current SP
- If SP insufficient:
	- Display "Not enough SP" message (or similar)
	- (Optional) Allow alternative action (like a basic attack)
- Restore SP (at the end of the turn)
- End

## System Robustness with Retries
- Start
- Attempt database operation
- If successful:
	- Proceed with normal flow
- If database error:
	- Retry operation (up to a configured number of times)
	- If successful after retry:
		- Proceed with normal flow
	- If all retries fail:
		- Handle error (log, throw exception, etc.)
- End

## Archivability through Purging
- Start
- Trigger purge process (by CRON job)
- Acquire distributed lock (if applicable)
- Fetch battle results from the database
- Filter results based on creation timestamp (older than 1 year)
- Delete filtered results 
- Release distributed lock (if applicable)
- End
