/**
 *
	Course: 151055 Software Engineering intro, Lab
	Student1: Avi margali 305645137
    Student2: Yeuda Nuiman 301759692
    Teacher: Dan Zilberstein
 */
package com.ud_avi.raytrace.geometries;

import com.ud_avi.raytrace.primitives.Color;
import com.ud_avi.raytrace.primitives.Material;

/**
 * Bsae class for all Radial Geometry shapes
 *
 */
public abstract class RadialGeometry extends Geometry implements Intersectable {
	protected double _radius;

	// ***************** Constructors ********************** //

	/**
	 * Constructor
	 * @param color emission color
	 * @param radius of the Geometry
	 * @param material of the Geometry
	 */
	public RadialGeometry(Color color, double radius, Material material) {
		super(color, material);
		_radius = radius;
	}

	// ***************** Getters/Setters ********************** //
	/**
	 * @return the radius
	 */
	public double getRadius() {
		return _radius;
	}

	// ***************** Administration  ******************** //

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "R: " + getRadius();
	}
}
