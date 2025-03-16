#!/bin/bash

/opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P Pass1234 -d master -i /sql/init.sql