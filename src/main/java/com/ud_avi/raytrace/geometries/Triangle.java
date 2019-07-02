/**
 * Course: 151055 Software Engineering intro, Lab
 * Student1: Avi margali 305645137
 * Student2: Yeuda Nuiman 301759692
 * Teacher: Dan Zilberstein
 */
package com.ud_avi.raytrace.geometries;

import java.util.List;
import java.util.Map;

import com.ud_avi.raytrace.primitives.*;

/**
 *
 * Triangle is the Class that represent Triangle in tree Dimensions 
 */
public class Triangle extends Plane {
    private Point3D _pointA;
    private Point3D _pointB;
    private Point3D _pointC;

    // ***************** Constructors ********************** //

    /**
     * Constructor of Triangle from 3 points
     * @param a Point a
     * @param b Point b
     * @param c Point c
     * @param color the emission color
     * @param material the material
     */
    public Triangle(Point3D a, Point3D b, Point3D c, Color color, Material material) {
        super(a, b, c, color, material);
        _pointA = new Point3D(a);
        _pointB = new Point3D(b);
        _pointC = new Point3D(c);
    }

    // ***************** Getters/Setters ********************** //

    /**
     * @return the pointA
     */
    public Point3D getPointA() {
        return _pointA;
    }

    /**
     * @return the pointB
     */
    public Point3D getPointB() {
        return _pointB;
    }

    /**
     * @return the pointC
     */
    public Point3D getPointC() {
        return _pointC;
    }

    // ***************** Administration  ******************** //

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "A: " + _pointA + " B: " + _pointB + " C: " + _pointC;
    }
    // ***************** Operations ******************** //

    /**
     * finds intersection of ray and this Triangle
     * @param ray ray the ray to find intersection with
     * @return List<Point3d> list which will contain all the intersections
     */
    @Override
    public Map<Geometry, List<Point3D>> findIntersections(Ray ray) {
        Map<Geometry, List<Point3D>> map = super.findIntersections(ray);
        if (map.isEmpty())
            return map;

        List<Point3D> list = map.entrySet().iterator().next().getValue();

        Point3D p0 = ray.getPoint();
        Vector v1 = _pointA.subtract(p0);
        Vector v2 = _pointB.subtract(p0);
        Vector v3 = _pointC.subtract(p0);
        Vector n1 = v1.crossProduct(v2);
        Vector n2 = v2.crossProduct(v3);
        Vector n3 = v3.crossProduct(v1);

        try {
            Vector u = list.get(0).subtract(p0);
            double x1 = u.dotProduct(n1);
            double x2 = u.dotProduct(n2);
            double x3 = u.dotProduct(n3);

            if (!(x1 > 0 && x2 > 0 && x3 > 0 || x1 < 0 && x2 < 0 && x3 < 0))
                map.clear();
        } catch (Exception e) {
            map.clear();
        }

        return map;
    }

    /**
     * @return the max point of the "Cube" contains the geometry
     * TODO: make sure that this is correct
     */
    @Override
    public Point3D getMax() {
        double x =Double.max(_pointA.getX().getCoord(), Double.max(_pointB.getX().getCoord(), _pointC.getX().getCoord()));
        double y =Double.max(_pointA.getY().getCoord(), Double.max(_pointB.getY().getCoord(), _pointC.getY().getCoord()));
        double z =Double.max(_pointA.getZ().getCoord(), Double.max(_pointB.getZ().getCoord(), _pointC.getZ().getCoord()));
        return new Point3D(x,y,z);
    }

    /**
     * @return the min point of the "Cube" contains the geometry
     */
    @Override
    public Point3D getMin() {
        double x =Double.min(_pointA.getX().getCoord(), Double.min(_pointB.getX().getCoord(), _pointC.getX().getCoord()));
        double y =Double.min(_pointA.getY().getCoord(), Double.min(_pointB.getY().getCoord(), _pointC.getY().getCoord()));
        double z =Double.min(_pointA.getZ().getCoord(), Double.min(_pointB.getZ().getCoord(), _pointC.getZ().getCoord()));
        return new Point3D(x,y,z);
    }
}
