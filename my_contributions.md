### Big contributions
- Implemented the initial stage of the non-functional modifications (Archivability with CRON job and system robustness with retries)
  - Set up a scheduled task (CRON job) using Spring Batch to periodically delete old battle and tournament results from the PostgreSQL database. This will help manage data storage and keep the database performance-optimized. Also use ShedLock to prevent duplicate tasks from running if there are multiple instances of the application.
  - Utilizing Spring Retry to apply Retry to the data repository methods
- Implemented parts of the React UI layer (Battle, Set/Remove Seed, and Tournament functionality)


### Other contributions (I took the lead for the design artifacts)
- Ask Sam if he would be willing to speak for the video (we could simply record one of our calls with screen record through Zoom or Skype | split the total recording into 3-4 clips) ﻿Tue, Nov 26﻿ 
- Create GitHub issues (related to DAV) for me, ER, SS, and SH to work on ﻿Tue, Nov 19﻿ 
- Compile all of the core information for the five remaining diagrams (DC-USA) in their respective directories ﻿Tue, Nov 19﻿ 
- Create "alpha" versions of the deployment and component diagrams ﻿Fri, Nov 22﻿ 
- Create new issues for the refinement phase of the diagrams ﻿Sun, Nov 24
  Ask Eduardo to work on the activity diagrams and Archivability modification with Sahal ﻿Tue, Nov 26﻿ 
- Sequence diagram for the skills modification ﻿Wed, Nov 27 - Thu, Nov 28﻿ 
- Beta versions of the deployment and component diagrams ﻿Wed, Nov 27﻿ 
- Consolidate all of the DAV deliverables in preparation for submission ﻿Thu, Nov 28 - Sun, Dec 1﻿ 

  ---
- Tue, Nov 26﻿ prettify Battle UI component | deployment and component diagrams (1) | ask Eduardo to help Sahal with activity diagrams and Archivability modification 
- ﻿﻿Wed, Nov 27﻿ create Tournament UI | Archivability modification (1) | skills sequence diagram
﻿﻿- Thu, Nov 28﻿ | Archivability modification (2) | deployment and component diagrams (2) | double-check all of the requirements again (and plan out more tasks if necessary) | fix / clean up Tournament UI 
﻿﻿- Fri, Nov 29﻿ | configure the frontend to be included in the docker-compose.yml file
﻿﻿- Sat, Nov 30﻿ | video (1) | finalize all six UML diagrams | consolidate all of the DAV deliverables (1) | finish updating README of GitHub repo 
﻿﻿- Sun, Dec 1﻿ | video (2) | consolidate all of the DAV deliverables (2) | update your personal GitHub repo remote with the final product of the project

