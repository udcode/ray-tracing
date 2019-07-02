/**
 *
	Course: 151055 Software Engineering intro, Lab
	Student1: Avi margali 305645137
    Student2: Yeuda Nuiman 301759692
    Teacher: Dan Zilberstein
 */
package com.ud_avi.raytrace.geometries;

import java.util.List;
import java.util.Map;

import com.ud_avi.raytrace.primitives.Point3D;
import com.ud_avi.raytrace.primitives.Ray;
/**
 * this interface holds the intersectable geometries
 */
public interface Intersectable {

	/**
	 * Find the intersections of given Ray.
	 * @param ray to find intersections
	 * @return Map of intersection points
	 */
	Map<Geometry, List<Point3D>> findIntersections(Ray ray);

	/**
	 * @return the max point of the "Cube" contains the geometry
	 */
	Point3D getMax();

	/**
	 * @return the min point of the "Cube" contains the geometry
	 */
	Point3D getMin();
}
