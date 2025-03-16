FROM mcr.microsoft.com/mssql/server:2019-latest

ENV ACCEPT_EULA=Y
ENV SA_PASSWORD=Pass1234

USER root

RUN apt-get update && \
    apt-get install -y msodbcsql17 mssql-tools

RUN mkdir -p /sql && chown -R mssql /sql

COPY ./sql/ /sql
RUN chmod +x /sql/init-db.sh

ENTRYPOINT /bin/bash -c "/opt/mssql/bin/sqlservr & sleep 30 && /sql/init-db.sh && tail -f /dev/null"