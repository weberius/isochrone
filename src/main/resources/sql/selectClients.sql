select 
  client, 
  ST_X(center) as x,
  ST_Y(center) as y
from isochrone 
group by client, x, y