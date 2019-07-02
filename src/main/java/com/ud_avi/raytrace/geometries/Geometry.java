/**
 * Course: 151055 Software Engineering intro, Lab
 * Student1: Avi margali 305645137
 * Student2: Yeuda Nuiman 301759692
 * Teacher: Dan Zilberstein
 */
package com.ud_avi.raytrace.geometries;


import com.ud_avi.raytrace.primitives.*;

/**
 * Base class for all Geometry shapes
 */
public abstract class Geometry implements Intersectable {
    protected Color _emission;
    protected Material _material;
    // ***************** Constructors ********************** //
    /**
     * Constructor
     * @param color of the Geometry
     * @param material of the Geometry
     */
    public Geometry(Color color, Material material) {
        _emission = new Color(color);
        _material = new Material(material);
    }

    // ***************** Getters/Setters ********************** //
    /**
     * @return the material of the Geometry
     */
    public Material getMaterial() {
        return _material;
    }

    /**
     * @param material of the Geometry
     */
    public void setMaterial(Material material) {
        _material = new Material(material);
    }

    /**
     * @return the emission color of the Geometry
     */
    public Color getEmission() {
        return _emission;
    }

    // ***************** Operations ******************** //

    /**
     * Get the normal to the Geometry on given
     * @param point to get the normal
     * @return normal to Geometry
     */
    public abstract Vector getNormal(Point3D point);
}
