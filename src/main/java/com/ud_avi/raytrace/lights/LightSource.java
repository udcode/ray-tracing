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
 * LightSource is Interface for all the Lights that have source
 */
public interface LightSource {

	/**
	 * get the intensity of the light at a specific point
	 * @param point to find Intensity
	 * @return Color
	 */
	Color getIntensity(Point3D point);

	/**
	 * get Vector from the light to the point
	 * @param point to find
	 * @return Vector L
	 */
	Vector getL(Point3D point);

	/**
	 * get direction of the light
	 * @param point to find
	 * @return Vector L
	 */
	Vector getD(Point3D point);

	/**
	 *	get the distance from the light to the point
	 * @param point some point
	 * @return Distance
	 */
	double getDistance(Point3D point);
}
