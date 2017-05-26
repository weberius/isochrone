# isochrone

Das Projekt isochrone erlaubt es, isochrone in einer Datenbank zu speichern. Es wird vorausgesetzt, dass die isochronen im geojson Format vorliegen. Das Projekt beruht zur Zeit auf dem export-Ergebnis von [openrouteserivce](https://www.openrouteservice.org/reach). Da der Service allgemeingültigkeit besitzen soll, ist es möglich, verschiedene Clients zu verwenden. Die Trennung schnittstellen-seitig erfolgt durch einen entsprechenden Pfad.

# Status

Die Applikation befindet sich in der Entwicklung

# Verwendete Technologien

- Java
- Postgres/ Postgis

# REST Endpoints

## /isochrone/service/ping

Diese Schnittstelle wird verwendet um zu prüfen, ob der Service selbst erreichbar ist.

## /isochrone/service/<client>

TODD: /Diese Schnittstelle liefert alle für den Client gespeicherten isochronen aus. 

# Datenbank

## DB User auf Postgres einrichten

    sudo -u postgres createuser -P isochrone
    
## Datenbank baumkataster anlegen

    sudo -u postgres createdb -O isochrone isochrone

## Postgis topology

    sudo -u postgres psql -c "CREATE EXTENSION postgis; CREATE EXTENSION postgis_topology;" isochrone
    
## Tabelle

### isochrone

| Spalte | Typ | Beschreibung |
| ------ | --- | ------------ |
| number | serial | eindeutige nummer über alle gespeicherten Datensätze |
| id | id eines rings eines isochron | integer |
| client |  | varchar(128) |
| value |  | integer |
| area |  | double precision |
| reachfactor |  | double precision |
| center |  | POINT |
| geom |  | MULTIPOLYGON |



    CREATE TABLE ISOCHRONE (
      number      SERIAL PRIMARY KEY, 
      id          integer,
      client      varchar(128),
      value       integer, 
      area        double precision,
      reachfactor double precision,
    );
    SELECT AddGeometryColumn ('public','isochrone','center',4326,'POINT',2);
    SELECT AddGeometryColumn ('public','isochrone','geom',4326,'MULTIPOLYGON',2);
    
## Verbindungsparameter

Die Datenbankverbindungsparameter werden per JNDI zur Verfügung gestellt. Dies bedeutet, dass sie im Container definiert sein müssen. Für den Online-Betrieb mit
Tomcat sind folgende Parameter zu setzen:

context.xml

    <Context>
        <ResourceLink 
             name="jdbc/isochrone" 
             global="jdbc/isochrone"
             type="javax.sql.DataSource" />
    </Context> 

server.xml

    <GlobalNamingResources>
        <Resource 
            name="jdbc/isochrone"
            auth="Container"
            driverClassName="org.postgresql.Driver"
            maxTotal="25" 
            maxIdle="10"
            username="username"
            password="password"
            type="javax.sql.DataSource"
            url="jdbc:postgresql://localhost:5432/isochrone"
            validationQuery="select 1"/>

Zu Testzwecken muss die Datei _src/test/resources/jndi.properties.template_ in _jndi.properties_ umbenannt und die Verbindungsparameter angepasst werden.

# Installation

Die Applikation ist eine Java-Webapplikation und setzt einen Tomcat voraus. Die Installation setzt eine Postgres/ Postgis Datenbank voraus. Die Datenstruktur dort muss bereits vorhanden sein. 

    git clone https://github.com/weberius/isochrone.git
    cd isochrone
    mvn clean install

# License

<a rel="license" href="http://creativecommons.org/licenses/by-sa/4.0/"><img alt="Creative Commons Lizenzvertrag" style="border-width:0" src="https://i.creativecommons.org/l/by-sa/4.0/88x31.png" /></a><br />Dieses Werk ist lizenziert unter einer <a rel="license" href="http://creativecommons.org/licenses/by-sa/4.0/">Creative Commons Namensnennung - Weitergabe unter gleichen Bedingungen 4.0 International Lizenz</a>.