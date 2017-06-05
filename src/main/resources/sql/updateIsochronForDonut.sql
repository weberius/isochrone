update isochrone set donut = (ST_Multi(ST_Difference(
      (select geom from isochrone where center = ? and id = ?),
      (select geom from isochrone where center = ? and id = ?)
    )
  ))
where
  client = ? and
  center = ? and
  id = ?