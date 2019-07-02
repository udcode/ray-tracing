/**
 *
	Course: 151055 Software Engineering intro, Lab
	Student1: Avi margali 305645137
    Student2: Yeuda Nuiman 301759692
    Teacher: Dan Zilberstein
 */
package com.ud_avi.raytrace.geometries;
import com.ud_avi.raytrace.primitives.*;

/**
* 
* Cylinder is the Class that represent Cylinder in tree Dimensions
*  @deprecated use Tube instead for now
*/
@Deprecated
public class Cylinder extends Tube {
	private double _high;
	// ***************** Constructors ********************** //

	/**
	 * Constructor of a Cylinder
	 * @param color the emission color
	 * @param ray the direction of the Cylinder
	 * @param high the high of the Cylinder
	 * @param radius the radius of the Cylinder
	 */
	public Cylinder(Color color, Ray ray, double high, double radius,Material material) {
		super(color, ray,radius,material);
		_high = high;
	}
	/**
	 * @param other Cylinder
	 */
	public Cylinder(Cylinder other) {
		super(other.getEmission(), other.getRay(),other.getRadius(),other.getMaterial());
		_high = other.getHigh();
	}
	// ***************** Getters/Setters ********************** //

	/**
	 * @return the high
	 */
	public double getHigh() {
		return _high;
	}
	
	// ***************** Administration  ******************** //

	@Override
	public String toString() {
		return  super.toString() + " Ray: "+ getRay() + " High: "+ getHigh();
	}

	// ***************** Operations ******************** //
	/**
	 * get normal
	 */
	public Vector getNormal(Point3D point) {
		// TODO Auto-generated method stub
		return null;
	}
}
