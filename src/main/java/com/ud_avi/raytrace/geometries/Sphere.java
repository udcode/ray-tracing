/**
 * Course: 151055 Software Engineering intro, Lab
 * Student1: Avi margali 305645137
 * Student2: Yeuda Nuiman 301759692
 * Teacher: Dan Zilberstein
 */
package com.ud_avi.raytrace.geometries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ud_avi.raytrace.primitives.*;

/**
 *
 * Sphere is the Class that represent Sphere in tree Dimensions 
 */
public class Sphere extends RadialGeometry {
   private Point3D _point;

    // ***************** Constructors ********************** //

    /**
     * Constructor
     * @param color the emission color
     * @param point center of the Sphere
     * @param radius the radius of the Sphere
     */
    public Sphere(Color color, Point3D point, double radius, Material material) {
        super(color, radius, material);
        _point = new Point3D(point);
    }

    // ***************** Getters/Setters ********************** //

    /**
     * @return the center point
     */
    public Point3D getPoint() {
        return _point;
    }

    // ***************** Administration  ******************** //

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return _point + " " + super.toString();
    }

    // ***************** Operations ******************** //

    /**
     * Get normal to the sphere in point
     * The normal is the vector form the center point to the point given.
     * @param point on the sphere
     * @return Vector normal to the sphere
     */
    public Vector getNormal(Point3D point) {
        Vector n = point.subtract(_point);//build vector from center point to the point
        return n.normalize();
    }

    /**
     * finds intersection of ray and this Triangle
     * @param ray ray the ray to find intersection with
     * @return List<Point3d> list which will contain all the intersections
     */
    @Override
    public Map<Geometry, List<Point3D>> findIntersections(Ray ray) {
        Map<Geometry, List<Point3D>> map = new HashMap<>();
        List<Point3D> list = new ArrayList<>();

        Vector rayDirection = ray.getDirection();
        Point3D rayPoint = ray.getPoint();
        //in case that the center point of the sphere is the same point as the point of the ray
        //return the point scale by the radius
        if (_point.equals(rayPoint)) {
            list.add(rayPoint.addVector(rayDirection.scale(_radius)));
            map.put(this, list);
            return map;
        }

        Vector u = _point.subtract(rayPoint);
        double tm = rayDirection.dotProduct(u);
        double d = Math.sqrt(u.dotProduct(u) - tm * tm);
        if (d > _radius) //no intersections
            return map;

        double th = Math.sqrt(_radius * _radius - d * d);
        if (Coordinate.ZERO.equals(th))
            list.add(rayPoint.addVector(rayDirection.scale(tm)));
        else {
            double t1 = tm - th;
            double t2 = tm + th;

            if (t1 > 0 && ! Coordinate.ZERO.equals(t1))//one intersections
                list.add(rayPoint.addVector(rayDirection.scale(t1)));

            if (t2 > 0 && ! Coordinate.ZERO.equals(t2)) //another intersections
                list.add(rayPoint.addVector(rayDirection.scale(t2)));
        }

        if (!list.isEmpty())
            map.put(this, list);

        return map;
    }

    /**
     * @return the max point of the "Cube" contains the geometry
     */
    @Override
    public Point3D getMax() {
        return new Point3D(
                _point.getX().getCoord()+_radius,
                _point.getY().getCoord()+_radius,
                _point.getZ().getCoord()+_radius);
    }

    /**
     * @return the min point of the "Cube" contains the geometry
     */
    @Override
    public Point3D getMin() {
        return new Point3D(
                _point.getX().getCoord()-_radius,
                _point.getY().getCoord()-_radius,
                _point.getZ().getCoord()-_radius);
    }
}
