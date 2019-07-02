/**
 *
	Course: 151055 Software Engineering intro, Lab
	Student1: Avi margali 305645137
    Student2: Yeuda Nuiman 301759692
    Teacher: Dan Zilberstein
 */
package com.ud_avi.raytrace.lights;

import com.ud_avi.raytrace.primitives.Color;
import com.ud_avi.raytrace.primitives.Point3D;
import com.ud_avi.raytrace.primitives.Vector;

/**
 * DirectionalLight is class that represent Directional Light in scene (like the sun)
 */
public class DirectionalLight extends Light implements LightSource {
	private Vector _direction;

	/**
	 * @param color of the light
	 * @param direction of the spot
	 */
	public DirectionalLight(Color color, Vector direction) {
		super(color);
		_direction = new Vector(direction).normalize();
	}

	/* (non-Javadoc)
	 * @see com.ud_avi.raytrace.lights.LightSource#getIntensity(com.ud_avi.raytrace.primitives.Point3D)
	 */
	@Override
	public Color getIntensity(Point3D point) {
		return getIntensity();
	}

	/* (non-Javadoc)
	 * @see com.ud_avi.raytrace.lights.LightSource#getL(com.ud_avi.raytrace.primitives.Point3D)
	 */

	@Override
	public Vector getL(Point3D point) {
		return new Vector(_direction);
	}

	/* (non-Javadoc)
	 * @see com.ud_avi.raytrace.lights.LightSource#getD(com.ud_avi.raytrace.primitives.Point3D)
	 */
	@Override
	public Vector getD(Point3D point) {
		return new Vector(_direction);
	}

	/* (non-Javadoc)
	 * @see com.ud_avi.raytrace.lights.LightSource#getDistance(com.ud_avi.raytrace.primitives.Point3D)
	 */
	@Override
	public double getDistance(Point3D point) {
		return Double.MAX_VALUE;
	}
}
