
## Core information 
- Components
	- Pokemon Simulator UI (React)
	- Command Processor (Spring Component)
	- Pokemon Management Services (Spring Component)
	- Data Access Layer (Spring Data JPA)
		- Includes the repositories (Pokemon, Skill)
	- PostgreSQL Database (External)
	- Pokemon API (External)
- Relationships/Dependencies
	- PSU uses CP
	- CP uses PMS
	- PMS uses DAL
	- DAL depends on PD
	- PMS depends on PA
	

---
### Resources/inspiration
- [Component Diagram Tutorial](https://www.lucidchart.com/pages/uml-component-diagram)
- Example of a component diagram (library management system)
	- https://d2slcw3kip6qmk.cloudfront.net/marketing/pages/chart/component-diagram-for-library-management-system-UML/component_diagram_library_management_system-893x634.PNG

- Example of a component diagram for an ATM system
	- https://d2slcw3kip6qmk.cloudfront.net/marketing/pages/chart/component-diagram-for-ATM-system-UML/component_diagram_ATM_system-843x746.PNG
	