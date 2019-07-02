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
 * Color class represent color wrapper
 *
 */
public class Color {
	private double _R,_G,_B;
	public static final Color WHITE = new Color(255,255,255);
	public static final Color BLACK = new Color(0,0,0);

	// ***************** Constructors ********************** //

	/**
	 * constructor
	 * @param R Red
	 * @param G Green
	 * @param B Blue
	 */
	public Color(double R, double G, double B) {
		_R = R;
		_G = G;
		_B = B;
	}
	/**
	 * constructor
	 * @param other Color
	 */
	public Color(Color other) {
		_R = other.getR();
		_G = other.getG();
		_B = other.getB();
	}
	// ***************** Getters/Setters ********************** //

	/**
	 * @return red value
	 */
	public double getR() {
		return _R;
	}

	/**
	 * @return green value
	 */
	public double getG() {
		return _G;
	}

	/**
	 * @return blue value
	 */
	public double getB() {
		return _B;
	}

	/**
	 * get java color
	 * @return awt.color
	 */
	public java.awt.Color getColor() {
		//max value is 255
		_R = _R > 255 ? 255 : _R;
		_G = _G > 255 ? 255 : _G;
		_B = _B > 255 ? 255 : _B;
		//min value is 0
		_R = _R < 0 ? 0 : _R;
		_G = _G < 0 ? 0 : _G;
		_B = _B < 0 ? 0 : _B;

		return new java.awt.Color((int)_R,(int)_G,(int)_B);
	}
	// ***************** Operations ******************** //

	/**
	 * adds two colors
	 * @param color the sum of the two colors
	 * @return this Color
	 */
	public Color add(Color color) {
		_R += color.getR();
		_G += color.getG();
		_B += color.getB();
		return this;
	}

	/**
	 * scales the color 
	 * @param k the scaling factor
	 * @return this Color
	 */
	public Color scale(double k) {
		_R *= k;
		_G *= k;
		_B *= k;
		return this;
	}

	/**
	 * reduces the color 
	 * @param k the reducing factor
	 * @return this Color
	 */
	public Color reduce(double k) {
		_R /= k;
		_G /= k;
		_B /= k;
		return this;
	}
}
