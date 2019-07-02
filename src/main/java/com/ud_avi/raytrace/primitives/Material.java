/**
 *
	Course: 151055 Software Engineering intro, Lab
	Student1: Avi margali 305645137
    Student2: Yeuda Nuiman 301759692
    Teacher: Dan Zilberstein
 */
package com.ud_avi.raytrace.primitives;

/**
 * Material is class that represent Material of geometry
 */
public class Material {
	private double _Kd;
	private double _Ks;
	private double _Kr;
	private double _Kt;
	private int _nShininess;

	// ***************** Constructors ********************** //
	/**
	 * Constructor
	 * @param Ks Specular factor
	 * @param Kd Diffuse factor
	 * @param Kr Reflection factor
	 * @param Kt Transparency factor
	 * @param nShininess object Shininess
	 */
	public Material(double Kd, double Ks, double Kr, double Kt, int nShininess) {
		_Kd = Kd;
		_Ks = Ks;
		_Kr = Kr;
		_Kt = Kt;
		_nShininess = nShininess;
	}

	/**
	 * Copy Constructor
	 * @param other Material
	 */
	public Material(Material other) {
		_Kd = other.getKd();
		_Ks = other.getKs();
		_Kt = other.getKt();
		_Kr = other.getKr();
		_nShininess = other.getnShininess();
	}


	// ***************** Getters/Setters ********************** //

	/**
	 * @return the Diffuse factor
	 */
	public double getKd() {
		return _Kd; 
	}

	/**
	 * @return the Specular factor
	 */
	public double getKs() {
		return _Ks;
	}

	/**
	 * @return the object Shininess
	 */
	public int getnShininess() {
		return _nShininess;
	}

	/**
	 * @return The Reflation factor
	 */
	public double getKr() {
		return _Kr;
	}

	/**
	 * @return The Transparency factor
	 */
	public double getKt() {
		return _Kt;
	}
}
