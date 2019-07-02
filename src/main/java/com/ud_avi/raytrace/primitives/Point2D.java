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
 * Point2D is the Class that represent Point in tow Dimensions *
 */
public class Point2D {

	protected Coordinate _x;
	protected Coordinate _y;

	// ***************** Constructors ********************** //
	/**
	 * Constructor from double
	 * @param x Coordinate
	 * @param y Coordinate
	 */
	public Point2D(double x, double y) {
		_x = new Coordinate(x);
		_y = new Coordinate(y);
	}

	/**
	 * Constructor from coordinates
	 * @param x Coordinate
	 * @param y Coordinate
	 */
	public Point2D(Coordinate x, Coordinate y) {
		_x = new Coordinate(x);
		_y = new Coordinate(y);
	}

	/**
	 * Copy Constructor
	 * @param other point
	 */
	public Point2D(Point2D other) {
		_x = new Coordinate(other.getX());
		_y = new Coordinate(other.getY());
	}

	// ***************** Getters/Setters ********************** //
	/**
	 * 
	 * @return X coordinate
	 */
	public Coordinate getX() {
		return _x;
	}

	/**
	 * 
	 * @return Y coordinate
	 */
	public Coordinate getY() {
		return _y;
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
		if (!(obj instanceof Point2D))
			return false;
		Point2D other = (Point2D) obj;
		if (_x == null) {
			if (other._x != null)
				return false;
		} else if (!_x.equals(other.getX()))
			return false;
		if (_y == null) {
			if (other._y != null)
				return false;
		} else if (!_y.equals(other.getY()))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "(" + getX() + "," + getY() + ")";
	}
}
