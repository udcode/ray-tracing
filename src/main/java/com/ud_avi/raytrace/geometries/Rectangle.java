/**
 *
 Course: 151055 Software Engineering intro, Lab
 Student1: Avi margali 305645137
 Student2: Yeuda Nuiman 301759692
 Teacher: Dan Zilberstein
 */
package com.ud_avi.raytrace.geometries;

import com.ud_avi.raytrace.primitives.*;

import java.util.List;
import java.util.Map;

/**
 * Rectangle is class that represents Rectangle in 3D space.
 */
public class Rectangle extends Plane {
    private Point3D _A,  _B,  _C;

    /**
     * Constructor of Rectangle from 3 points
     * @param A Point a
     * @param B Point b
     * @param C Point c
     * @param color the emission color
     * @param material the material
     */
    public Rectangle(Point3D A, Point3D B, Point3D C, Color color, Material material) {
        super(A, B, C, color, material);
        _A = new Point3D(A);
        _B = new Point3D(B);
        _C = new Point3D(C);
        Vector AB = _B.subtract(_A);
        Vector AC = _C.subtract(_A);
        if(!Coordinate.ZERO.equals(AB.dotProduct(AC)))
            throw new IllegalArgumentException("AB must be orthogonal to AC");
    }

    /**
     * finds intersection of ray and this Rectangle
     * @param ray ray the ray to find intersection with
     * @return List<Point3d> list which will contain all the intersections
     */
    @Override
    public Map<Geometry, List<Point3D>> findIntersections(Ray ray) {
        //get the plane intersection
        Map<Geometry, List<Point3D>> map = super.findIntersections(ray);
        if (map.isEmpty())
            return map;

        List<Point3D> list = map.entrySet().iterator().next().getValue();
        //calc if (0 < PA * AB < AB * AB) AND (0 < PA * AC < AC * AC)
        //so the point is inside the rectangle
        Vector AB = _B.subtract(_A);
        Vector AC = _C.subtract(_A);

        // if A is P
        if(list.get(0).equals(_A))
            return map;

        Vector PA = list.get(0).subtract(_A);
        double u = PA.dotProduct(AB);
        double v = PA.dotProduct(AC);
        if (!(u >= 0.0 && u <= AB.dotProduct(AB)
                && v >= 0.0 && v <= AC.dotProduct(AC)))
            map.clear();

        return map;
    }

    /**
     * @return the max point of the "Cube" contains the geometry
     */
    @Override
    public Point3D getMax() {
        Point3D D = new Point3D(new Vector(_B.subtract(_A)).add(new Vector(_C.subtract(_A))).getHead());
        double x = Double.max(_A.getX().getCoord(), Double.max(_B.getX().getCoord(), Double.max( _C.getX().getCoord(),D.getX().getCoord())));
        double y = Double.max(_A.getY().getCoord(), Double.max(_B.getY().getCoord(), Double.max( _C.getY().getCoord(),D.getY().getCoord())));
        double z = Double.max(_A.getZ().getCoord(), Double.max(_B.getZ().getCoord(), Double.max( _C.getZ().getCoord(),D.getZ().getCoord())));
        return new Point3D(x,y,z);
    }

    /**
     * @return the min point of the "Cube" contains the geometry
     */
    @Override
    public Point3D getMin() {
        Point3D D = new Point3D(new Vector(_B.subtract(_A)).add(new Vector(_C.subtract(_A))).getHead());
        double x =Double.min(_A.getX().getCoord(), Double.min(_B.getX().getCoord(), Double.min( _C.getX().getCoord(),D.getX().getCoord())));
        double y =Double.min(_A.getY().getCoord(), Double.min(_B.getY().getCoord(), Double.min( _C.getY().getCoord(),D.getY().getCoord())));
        double z =Double.min(_A.getZ().getCoord(), Double.min(_B.getZ().getCoord(), Double.min( _C.getZ().getCoord(),D.getZ().getCoord())));
        return new Point3D(x,y,z);
    }
}
