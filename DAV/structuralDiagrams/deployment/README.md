
## Core information 
- Nodes
	- User's browser
	- Docker Container (Spring Boot application)
	- Docker Container (PostgreSQL database)
	- External Pokemon API (optional)
- Artifacts
	- React web application (within the browser)
	- Spring Boot application JAR (within Docker container)
	- PostgreSQL database server (within Docker container)
- Relationships/Connections
	- User's browser - (HTTP) -> Docker container (Spring Boot application)
	- Docker container (Spring Boot application) - (JDBC/TCP) -> Docker Container (PostgreSQL database)
	- Docker container (Spring Boot application) - (HTTP) -> External Pokemon API (optional)

---
### Resources/inspiration
- [Deployment Diagram Tutorial](https://www.lucidchart.com/pages/uml-deployment-diagram)
- Example of a deployment diagram
	- https://d2slcw3kip6qmk.cloudfront.net/marketing/pages/chart/uml/deployment-diagram/deployment-diagram-example-700x412@2x.jpeg