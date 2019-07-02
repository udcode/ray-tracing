/**
 *
	Course: 151055 Software Engineering intro, Lab
	Student1: Avi margali 305645137
    Student2: Yeuda Nuiman 301759692
    Teacher: Dan Zilberstein
 */
package com.ud_avi.raytrace.geometries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ud_avi.raytrace.primitives.Point3D;
import com.ud_avi.raytrace.primitives.Ray;

/**
 * Geometries is class that hold collection of Geometry
 *
 */
public class Geometries implements Intersectable {
	private List<Geometry> _geometriesList = new ArrayList<>();
	
	/* (non-Javadoc)
	 * @see com.ud_avi.raytrace.geometries.Intersectable#findIntersections(com.ud_avi.raytrace.primitives.Ray)
	 */
	public Map<Geometry, List<Point3D>> findIntersections(Ray ray) {
		Map<Geometry, List<Point3D>> intersections  = new HashMap<>();
		for(Geometry geometry: _geometriesList) {
                intersections.putAll(geometry.findIntersections(ray));//findIntersections can be empty
		}
		return intersections;
	}

	/**
	 * @return the geometry list
	 */
	public List<Geometry> getGeometriesList() {
		return _geometriesList;
	}

	/**
	 * @return the max point of the "Cube" contains the geometry
	 */
	@Override
	public Point3D getMax() {
		double maxX = Double.MIN_VALUE;
		double maxY = Double.MIN_VALUE;
		double maxZ = Double.MIN_VALUE;
		for (Geometry geometry : _geometriesList) {
			Point3D maxP = geometry.getMax();
			maxX = maxP.getX().getCoord() > maxX ? maxP.getX().getCoord() : maxX;
			maxY = maxP.getY().getCoord() > maxY ? maxP.getY().getCoord() : maxY;
			maxZ = maxP.getZ().getCoord() > maxZ ? maxP.getZ().getCoord() : maxZ;
		}
		return new Point3D(maxX, maxY, maxZ);
	}

	/**
	 * @return the min point of the "Cube" contains the geometry
	 */
	@Override
	public Point3D getMin() {
		double minX = Double.MAX_VALUE;
		double minY = Double.MAX_VALUE;
		double minZ = Double.MAX_VALUE;
		for (Geometry geometry : _geometriesList) {
			Point3D minP = geometry.getMin();
			minX = minP.getX().getCoord() < minX ? minP.getX().getCoord() : minX;
			minY = minP.getY().getCoord() < minY ? minP.getY().getCoord() : minY;
			minZ = minP.getZ().getCoord() < minZ ? minP.getZ().getCoord() : minZ;
		}
		return new Point3D(minX, minY, minZ);
	}

	/**
	 * Add geometry into Geometries
	 * @param geometry object
	 */
	public void addGeometry(Geometry geometry) {
		_geometriesList.add(geometry);
	}

	/**
	 * @return The number of Geometries
	 */
	public int namOfGeometries(){
		return  _geometriesList.size();
	}

}
