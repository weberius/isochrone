select 
  client, 
  ST_X(center) as x,
  ST_Y(center) as y,
  ST_AsGeoJSON(center) as geojson
from isochrone 
group by client, x, y, geojson