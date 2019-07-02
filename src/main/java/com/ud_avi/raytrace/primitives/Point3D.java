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
 * Point3D is the Class that represent Point in tree Dimensions 
 */
public class Point3D extends Point2D {
	public static final Point3D ZERO_P3D = new Point3D(0,0,0);
	private Coordinate _z;

	// ***************** Constructors ********************** //
	/**
	 * Constructor from double
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param z z coordinate
	 */
	public Point3D(double x,double y,double z) {
		super(x,y);
		_z = new Coordinate(z);
	}

	/**
	 * Constructor from coordinates
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param z z coordinate
	 */
	public Point3D(Coordinate x, Coordinate y, Coordinate z) {
		super(x, y);
		_z = new Coordinate(z);
	}

	/**
	 * Copy Constructor
	 * @param other Point
	 */
	public Point3D(Point3D other) {
		super(other.getX(),other.getY());
		_z = new Coordinate(other.getZ());
	}

	// ***************** Getters/Setters ********************** //

	/**
	 * @return Z coordinate
	 */
	public Coordinate getZ() {
		return _z;
	}

	// ***************** Administration  ******************** //	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Point3D))
			return false;
		Point3D other = (Point3D) obj;
		if (_z == null) {
			if (other._z != null)
				return false;
		} else if (!_z.equals(other.getZ()))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "(" + getX() + "," + getY() + "," + getZ() + ")";
	}

	// ***************** Operations ******************** //
	/**
	 * Get pointer from tow points
	 * @param other Point
	 * @return Vector
	 */
	public Vector subtract(Point3D other) {
		return new Vector(
				_x.subtract(other.getX().getCoord()),
				_y.subtract(other.getY().getCoord()),
				_z.subtract(other.getZ().getCoord()));
	}
	
	/**
	 * add vector to point
	 * @param vector Vector to add
	 * @return new Point
	 */
	public Point3D addVector(Vector vector) {
		return new Point3D(
				(_x.add(vector.getHead().getX().getCoord())),
				(_y.add(vector.getHead().getY().getCoord())),
				(_z.add(vector.getHead().getZ().getCoord())));
	}

	/**
	 * get the distance from point to point
	 * @param other Point
	 * @return the distance
	 */
	public double distance(Point3D other) {
		return Math.sqrt(
				(_x.getCoord()-other.getX().getCoord()) * (_x.getCoord()-other.getX().getCoord()) +
				(_y.getCoord()-other.getY().getCoord()) * (_y.getCoord()-other.getY().getCoord()) +
				(_z.getCoord()-other.getZ().getCoord()) * (_z.getCoord()-other.getZ().getCoord()));
	}
}