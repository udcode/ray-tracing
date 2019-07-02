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
 * Plane is an abstract Class that represent Plane in 3D world
 **** This class is an abstract class because you can't wrap a plane into Voxel ****
 */
public abstract class Plane extends Geometry implements Intersectable {
    private Point3D _point;
    private Vector _normal;
    // ***************** Constructors ********************** //

    /**
     * Constructor from point and normal to plane
     *
     * @param point    point on the plane
     * @param normal   normal to the plane
     * @param color    emission color
     * @param material of the plane
     */
    public Plane(Point3D point, Vector normal, Color color, Material material) {
        super(color, material);
        _point = new Point3D(point);
        _normal = new Vector(normal).normalize();
    }

    /**
     * Constructor from three points
     *
     * @param A        point A
     * @param B        point B
     * @param C        point C
     * @param color    emission Color
     * @param material of the plane
     */
    public Plane(Point3D A, Point3D B, Point3D C, Color color, Material material) {
        super(color, material);
        //
        Vector vectorA = B.subtract(A);
        Vector vectorB = C.subtract(A);
        //
        _normal = vectorA.crossProduct(vectorB).normalize();
        _point = new Point3D(A);
    }

    // ***************** Getters/Setters ********************** //

    /**
     * @return the point
     */
    public Point3D getPoint() {
        return _point;
    }

    /**
     * @return the normal
     */
    public Vector getNormal() {
        return _normal;
    }

    // ***************** Operations ******************** //

    /**
     * get the normal to the plane
     *
     * @param point point on the plane
     * @return Vector normal to plane
     */
    public Vector getNormal(Point3D point) {
        return _normal;
    }

    /**
     * finds intersection of ray and this plane
     *
     * @param ray ray the ray to find intersection with
     * @return List<Point3d> list which will contain all the intersections
     */
    @Override
    public Map<Geometry, List<Point3D>> findIntersections(Ray ray) {
        Map<Geometry, List<Point3D>> map = new HashMap<>();
        List<Point3D> list = new ArrayList<>();
        Vector rayDirection = ray.getDirection();
        Point3D rayPoint = ray.getPoint();
        try {
            double NQP = _normal.dotProduct(_point.subtract(rayPoint));
            if(Coordinate.ZERO.equals(NQP))
                return map;

            double NV = _normal.dotProduct(rayDirection);
            if(Coordinate.ZERO.equals(NV))
                return map;

            double t = NQP / NV;
            if (t > 0 && !Coordinate.ZERO.equals(t)) {
                list.add(rayPoint.addVector(rayDirection.scale(t)));
                map.put(this, list);
            }
            return map;
        } catch (Exception e) {//if the vector is 0 no intersections
            return map;
        }
    }
}
