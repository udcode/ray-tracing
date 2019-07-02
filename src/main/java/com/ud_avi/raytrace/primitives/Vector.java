/**
 *
	Course: 151055 Software Engineering intro, Lab
	Student1: Avi margali 305645137
    Student2: Yeuda Nuiman 301759692
    Teacher: Dan Zilberstein
 */
package com.ud_avi.raytrace.primitives;
/**
 * Vector is the Class that represent Vector in tree Dimensions 
 */
public class Vector {
	
	private Point3D _head;

	// ***************** Constructors ********************** //
	/**
	 * @param head haed of the vector
	 */
	public Vector(Point3D head){
        setHead(head);
	}

	/**
	 * Constructor from coordinates
	 * @param x coord x
	 * @param y coord y
	 * @param z coord z
	 */
	public Vector(double x, double y,double z) {
        setHead(new Point3D(x,y,z));
	}

	/**
	 * Constructor from other vector
	 * @param other Vector
	 */
	public Vector(Vector other){
		_head = new Point3D(other.getHead());
	}

	// ***************** Getters/Setters ********************** //
	/**
	 * Vector head getter
	 * @return the head
	 */
	public Point3D getHead() {
		return _head;
	}

    /**
     * Sets the head. Just from this class!
     * @param head of the vector
     */
    private void setHead(Point3D head){
        if(head.equals(Point3D.ZERO_P3D))
            throw new IllegalArgumentException("Cannot create Vector 0");
        _head = new Point3D(head);
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
		Vector other = (Vector) obj;
		if (_head == null) {
			if (other._head != null)
				return false;
		} else if (!_head.equals(other.getHead()))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	    return _head.toString();
	}

	// ***************** Operations ******************** //
	/**
	 * subtract two Vectors
	 * @param other Vector
	 * @return new Vector
	 */
	public Vector subtract(Vector other) {
		return _head.subtract(other.getHead());
		
	}

	/**
	 * add tow Vectors
	 * @param other Vector
	 * @return new Vector
	 */
	public Vector add(Vector other) {
		return new Vector(
			(_head.getX().add(other._head.getX().getCoord()).getCoord()),
			(_head.getY().add(other._head.getY().getCoord()).getCoord()),
			(_head.getZ().add(other._head.getZ().getCoord()).getCoord()));
	}
	
	/**
	 * do the Dot Product
	 * @param other Vector
	 * @return scalar
	 */
	public double dotProduct(Vector other) {
		return _head.getX().getCoord() * other.getHead().getX().getCoord()
				+ _head.getY().getCoord() * other.getHead().getY().getCoord()
				+ _head.getZ().getCoord() * other.getHead().getZ().getCoord();
	}

	/**
	 * do the Cross Product
	 * @param other Vector
	 * @return new vector
	 */
	public Vector crossProduct(Vector other) {
		double x = (_head.getY().getCoord() * other.getHead().getZ().getCoord())
				- (_head.getZ().getCoord() * other.getHead().getY().getCoord());
		double y = (_head.getZ().getCoord() * other.getHead().getX().getCoord())
				- (_head.getX().getCoord() * other.getHead().getZ().getCoord());
		double z = (_head.getX().getCoord() * other.getHead().getY().getCoord())
				- (_head.getY().getCoord() * other.getHead().getX().getCoord());
		return new Vector(x, y, z);
	}

	/**
	 * Multiply By scalar
	 * scalar(a,b,c)=(a*scalar,b*scalar,c*scalar)
	 * @param scalar to Multi
	 * @return new Vector
	 */
	public Vector scale(double scalar) {
		double x = _head.getX().getCoord() * scalar;
		double y = _head.getY().getCoord() * scalar;
		double z = _head.getZ().getCoord() * scalar;
		return new Vector(x,y,z);
	}

	/**
	 * @return the length of the vector
	 */
	public double length() {
		 return _head.distance(Point3D.ZERO_P3D);//calculate the distance from the origin
	}

	/**
	 * @return Normalize The Vector 
	 */
	public Vector normalize() {
		double length = length();
		double x = _head.getX().getCoord()/length;
		double y = _head.getY().getCoord()/length;
		double z = _head.getZ().getCoord()/length;
		_head = new Point3D(x,y,z);
		return this;
	}

	/**
	 * Rotate the vector around the X axis
	 * @param angle to rotate
	 * @return rotated vector
	 */
	@SuppressWarnings("Duplicates")
	public Vector rotateX(double angle){
		double x = _head.getX().getCoord();
		double y = _head.getY().getCoord();
		double z = _head.getZ().getCoord();
		double cos = Math.cos(Math.toRadians(angle));
		double sin = Math.sin(Math.toRadians(angle));
		double y1 = cos * y - sin * z;
		double z1 = sin * y + cos * z;
		_head = new Point3D(x,y1,z1);
		return this;
	}

	/**
	 * Rotate the vector around the Y axis
	 * @param angle to rotate
	 * @return rotated vector
	 */
	public Vector rotateY(double angle){
		double x = _head.getX().getCoord();
		double y = _head.getY().getCoord();
		double z = _head.getZ().getCoord();
		double cos = Math.cos(Math.toRadians(angle));
		double sin = Math.sin(Math.toRadians(angle));
		double x1 = cos * x + sin * z;
		double z1 = -sin * x + cos * z;
		_head = new Point3D(x1,y,z1);
		return this;
	}

	/**
	 * Rotate the vector around the Z axis
	 * @param angle to rotate
	 * @return rotated vector
	 */
	public Vector rotateZ(double angle){
		double x = _head.getX().getCoord();
		double y = _head.getY().getCoord();
		double z = _head.getZ().getCoord();
		double cos = Math.cos(Math.toRadians(angle));
		double sin = Math.sin(Math.toRadians(angle));
		double x1 = cos * x - sin * y;
		double y1 = sin * x + cos * y;
		_head = new Point3D(x1,y1,z);
		return this;
	}
}
