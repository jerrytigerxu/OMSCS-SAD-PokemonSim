Build:

docker build -t pokemon/history .

Run:

docker run -d --name my_postgres -p 5432:5432 -e POSTGRES_DB=game -e POSTGRES_USER=wizard -e POSTGRES_PASSWORD=wizardOfOZ pokemon/history





