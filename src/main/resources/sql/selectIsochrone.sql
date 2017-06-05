select 
  number,
  id,
  client, 
  value,
  area,
  reachfactor,
  ST_X(center) as x,
  ST_Y(center) as y,
  ST_AsGeoJSON(donut) as geojson
from isochrone 
