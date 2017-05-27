DROP TABLE IF EXISTS ISOCHRONE;
CREATE TABLE ISOCHRONE (
  number      SERIAL PRIMARY KEY, 
  id          integer,
  client      varchar(128),
  value       integer, 
  area        double precision,
  reachfactor double precision
);
SELECT AddGeometryColumn ('public','isochrone','center',4326,'POINT',2);
SELECT AddGeometryColumn ('public','isochrone','geom',4326,'MULTIPOLYGON',2);
