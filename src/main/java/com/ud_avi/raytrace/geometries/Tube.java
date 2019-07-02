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
 * Tube is the Class that represent Tube in tree Dimensions
 */
public class Tube extends RadialGeometry {
     protected Ray _ray;

    // ***************** Constructors ********************** //

    /**
     * Constructor of a Tube
     *
     * @param color  the emission color
     * @param ray    the direction of the Tube
     * @param radius the radius of the tube
     */
    public Tube(Color color, Ray ray, double radius, Material material) {
        super(color, radius,material);
        _ray = new Ray(ray);
    }

    // ***************** Getters/Setters ********************** //

    /**
     * @return the ray
     */
    public Ray getRay() {
        return _ray;
    }

    // ***************** Administration  ******************** //

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return super.toString() + " Ray: " + getRay();
    }
    // ***************** Operations ******************** //

    /**
     * get normal to the Tube
     * we are using this formula C = (AB/(|B|)^2)*B to find the normal
     *
     * @param point on the Tube
     * @return The normal Vector
     */
    public Vector getNormal(Point3D point) {
        //vector from the ray point to the point
        Vector vecA = point.subtract(_ray.getPoint());
        //AB
        double ab = vecA.dotProduct(_ray.getDirection());
        //vecA iin 90d to the direction of the tube
        if(Coordinate.ZERO.equals(ab))
            return vecA.normalize();

        //C is projection vector from A on B
        Vector vecC = _ray.getDirection().scale(ab);
        //the normal
        Vector n = point.subtract(vecC.getHead());

        return n.normalize();
    }

    /**
     * Find intersections of tube. solve quadratic equation of tube
     * A = (v - (v * va) * va)^2
     * B = 2 * (v - (v * va) * va) * (dp - (dp * vs) * vs)^2
     * C = (dp - (dp * va) * va)^2 - r^2
     * note: this code gets very complicated to avoid vector (0,0,0)
     * (non-Javadoc)
     *
     * @see com.ud_avi.raytrace.geometries.Geometry#findIntersections(com.ud_avi.raytrace.primitives.Ray)
     */
    //TODO: make it smarter!!
    @Override
    public Map<Geometry, List<Point3D>> findIntersections(Ray ray) {
        Map<Geometry, List<Point3D>> map = new HashMap<>();
        List<Point3D> list = new ArrayList<>();

        Vector v = ray.getDirection();
        Vector va = _ray.getDirection();
        Vector a, b;
        double A, B, C;

        double vDotVa = v.dotProduct(va);

        if (v.equals(va)) { //the ray is in the tube direction. no intersection
            return map;
        }

        if (Coordinate.ZERO.equals(vDotVa)) {//v*va=0
            A = v.dotProduct(v);
        } else {
            if (v.equals(va.scale(vDotVa))) {
               A = 0;
            } else {
                a = v.subtract(va.scale(vDotVa));
                A = a.dotProduct(a); // A = (v - (v * va) * va)^2
            }
        }

        if (ray.getPoint().equals(_ray.getPoint())) { //p=pa
            B = 0;
            C = -_radius * _radius;
        } else {
            Vector dp = ray.getPoint().subtract(_ray.getPoint());

            double dpDotVa = dp.dotProduct(va);

            if(!Coordinate.ZERO.equals(dpDotVa) && dp.equals(va.scale(dp.dotProduct(va)))){
                B = 0;
                C = -_radius * _radius;
            }else{
                if (Coordinate.ZERO.equals(dpDotVa)) {//dp*va=0
                    b = dp;
                } else {
                    b = dp.subtract(va.scale(dpDotVa));
                }

                C = b.dotProduct(b) - getRadius() * getRadius();//C = (dp - (dp * va) * va)^2 - r^2

                if (Coordinate.ZERO.equals(vDotVa)) {//v*va=0
                    B = 2 * v.dotProduct(b);
                } else {
                    if (v.equals(va.scale(vDotVa))) {
                        B = 0;
                    } else {
                        a = v.subtract(va.scale(v.dotProduct(va)));
                        B = 2 * a.dotProduct(b); //B = 2 * (v - (v * va) * va) * (dp - (dp * vs) * vs)^2
                    }
                }
            }
        }
        //square root equation
        double discr = (B * B) - (4.0 * A * C);
        if (discr < 0) //no roots
            return map;
        double delta = Math.sqrt(discr);
        double A2 = 2.0 * A;

        double t1 = (-B - delta) / A2;
        double t2 = (-B + delta) / A2;

        if (t1 > 0) {//one intersections
            list.add(ray.getPoint().addVector(ray.getDirection().scale(t1)));
            map.put(this, list);
        }
        if (t2 > 0) {//another intersections
            list.add(ray.getPoint().addVector(ray.getDirection().scale(t2)));
            map.put(this, list);
        }
        return map;
    }

    /**
     * @return the max point of the "Cube" contains the geometry
     */
    @Override
    public Point3D getMax() {
        return null;
    }

    /**
     * @return the min point of the "Cube" contains the geometry
     */
    @Override
    public Point3D getMin() {
        return null;
    }
}
