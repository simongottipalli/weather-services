db:
	docker build -t mssql-server . && docker run -e 'ACCEPT_EULA=Y' -e 'SA_PASSWORD=Pass1234' -p 1433:1433 --name mssql-container mssql-server
