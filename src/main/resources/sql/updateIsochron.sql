update isochrone set donut = geom
where 
  client = ? and 
  center = ? and 
  id = ?