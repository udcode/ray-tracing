/**
 * @author Avi margali
 * @author Yeuda Nuiman
 */

package com.ud_avi.raytrace.geometries;

import com.ud_avi.raytrace.primitives.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BBox is the class that represent Bounding Box
 */
public class BBox extends Geometry implements Intersectable{
    private Point3D _minPoint, _maxPoint;


    public BBox(Point3D minPoint, Point3D maxPoint){
        super(Color.BLACK,new Material(0,0,0,0,0));
        _minPoint = minPoint;
        _maxPoint = maxPoint;
    }


    /**
     * Find the intersections of given Ray.
     *
     * @param ray to find intersections
     * @return Map of intersection points
     */
    @Override
    public Map<Geometry, List<Point3D>> findIntersections(Ray ray) {
        //this code using the IEEE numeric properties to calculate the intersection
        //if you find a better way, let me know
        Map<Geometry, List<Point3D>> map = new HashMap<>();
        List<Point3D> list = new ArrayList<>();

        double origX = ray.getPoint().getX().getCoord();
        double origY = ray.getPoint().getY().getCoord();
        double origZ = ray.getPoint().getZ().getCoord();

        double dirX = ray.getDirection().getHead().getX().getCoord();
        double dirY = ray.getDirection().getHead().getY().getCoord();
        double dirZ = ray.getDirection().getHead().getZ().getCoord();

        double tmin, tmax, tymin, tymax, tzmin, tzmax;

        double invdirX = 1d/dirX;
        double invdirY = 1d/dirY;
        double invdirZ = 1d/dirZ;

        boolean signDirX = invdirX < 0;
        boolean signDirY = invdirY < 0;
        boolean signDirZ = invdirZ < 0;

        Point3D bb = signDirX ? _maxPoint : _minPoint ;
        tmin = (bb.getX().getCoord() - origX) * invdirX;
        bb = signDirX ? _minPoint :_maxPoint;
        tmax = (bb.getX().getCoord() - origX) * invdirX;
        bb = signDirY ? _maxPoint : _minPoint ;
        tymin = (bb.getY().getCoord() - origY) * invdirY;
        bb = signDirY ? _minPoint :_maxPoint;
        tymax = (bb.getY().getCoord() - origY) * invdirY;

        if(Double.isNaN(tmin))
            tmin = Double.NEGATIVE_INFINITY;
        if(Double.isNaN(tmax))
            tmin = Double.POSITIVE_INFINITY;
        if(Double.isNaN(tymin))
            tmin = Double.NEGATIVE_INFINITY;
        if(Double.isNaN(tymax))
            tmin = Double.POSITIVE_INFINITY;

        if ((tmin > tymax) || (tymin > tmax))
            return map;
        if (tymin > tmin)
            tmin = tymin;
        if (tymax < tmax)
            tmax = tymax;

        bb = signDirZ ? _maxPoint : _minPoint ;
        tzmin = (bb.getZ().getCoord() - origZ) * invdirZ;
        bb = signDirZ ? _minPoint :_maxPoint;
        tzmax = (bb.getZ().getCoord() - origZ) * invdirZ;

        if(Double.isNaN(tzmin))
            tmin = Double.NEGATIVE_INFINITY;
        if(Double.isNaN(tzmax))
            tmin = Double.POSITIVE_INFINITY;

        if ((tmin > tzmax) || (tzmin > tmax))
            return map;
        if (tzmin > tmin)
            tmin = tzmin;
        if (tzmax < tmax)
            tmax = tzmax;

        if(!Coordinate.ZERO.equals(tmin) && tmin > 0 && Double.isFinite(tmin))
            list.add(ray.getPoint().addVector(ray.getDirection().scale(tmin)));

        if(!Coordinate.ZERO.equals(tmax) && tmax > 0 &&Double.isFinite(tmax))
            list.add(ray.getPoint().addVector(ray.getDirection().scale(tmax)));

        if(list.size()!=0)
            map.put(this,list);

        return map;
    }

    /**
     * @return the max point of the "Cube" contains the geometry
     */
    @Override
    public Point3D getMax() {
        return _maxPoint;
    }

    /**
     * @return the min point of the "Cube" contains the geometry
     */
    @Override
    public Point3D getMin() {
        return _minPoint;
    }

    /**
     * Get the normal to the Geometry on given
     *
     * @param point to get the normal
     * @return normal to Geometry
     */
    @Override
    public Vector getNormal(Point3D point) {
        return null;
    }
}
