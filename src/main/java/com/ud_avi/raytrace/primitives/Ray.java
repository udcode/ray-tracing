/**
 *
	Course: 151055 Software Engineering intro, Lab
	Student1: Avi margali 305645137
    Student2: Yeuda Nuiman 301759692
    Teacher: Dan Zilberstein
 */
package com.ud_avi.raytrace.primitives;
/**
 * 
 * Ray is the Class that represents Ray in three Dimensions
 *
 */
public class Ray {
	private Vector _direction;
	private Point3D _point;

	// ***************** Constructors ********************** //
	/**
	 * @param direction Direction of the Ray
	 * @param point Point of the Ray
	 */
	public Ray(Vector direction, Point3D point) {
		_direction = new Vector(direction.normalize());
		_point = new Point3D(point);
	}

	/**
	 * Copy constructor
	 * @param other ray
	 */
	public Ray(Ray other) {
		_direction = new Vector(other.getDirection());
		_point = new Point3D(other.getPoint());
	}
	// ***************** Getters/Setters ********************** //

	/**
	 * 
	 * @return The Direction
	 */
	public Vector getDirection() {
		return _direction;
	}

	/**
	 * @return The point
	 */
	public Point3D getPoint() {
		return _point;
	}

	/**
	 * @param point the Point
	 */
	public void setPoint(Point3D point) {
		_point = point;
	}

	// ***************** Administration  ******************** //
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ray other = (Ray) obj;
		return _direction.equals(other.getDirection()) && _point.equals(other.getPoint());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return _point + " + t" + _direction;
	}
}
