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
 * SpotLight is class that represent Spot Light in scene
 */
public class SpotLight extends PointLight {
	private Vector _direction;

	/**
	 * Constructor
	 * @param color of the light
	 * @param position of the light in scene
	 * @param Kc const factor
	 * @param Kl linear factor
	 * @param Kq qubic factor
	 * @param direction of the spot
	 */
	public SpotLight(Color color, Point3D position, double Kc, double Kl, double Kq, Vector direction) {
		super(color, position, Kc, Kl, Kq);
		_direction = new Vector(direction).normalize();
	}

	/* (non-Javadoc)
	 * @see com.ud_avi.raytrace.lights.LightSource#getIntensity(com.ud_avi.raytrace.primitives.Point3D)
	 */
	@Override
	public Color getIntensity(Point3D point) {
		//clac with formula - il = (_color.scale ((_direction.dot(L)))   divided by   (_Kc + (_Kl * d) + (_Kq * (d)^2))
		double scale = _direction.dotProduct(getL(point));
		return scale > 0 ? super.getIntensity(point).scale(scale) : new Color(Color.BLACK);
	}

	/* (non-Javadoc)
	 * @see com.ud_avi.raytrace.lights.LightSource#getL(com.ud_avi.raytrace.primitives.Point3D)
	 */
	@Override
	public Vector getL(Point3D point) {
		return point.subtract(_position).normalize();
	}

	/* (non-Javadoc)
	 * @see com.ud_avi.raytrace.lights.LightSource#getD(com.ud_avi.raytrace.primitives.Point3D)
	 */
	@Override
	public Vector getD(Point3D point) {
		return new Vector(_direction);
	}
}
