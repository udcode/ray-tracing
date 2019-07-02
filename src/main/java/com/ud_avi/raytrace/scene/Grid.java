/**
 * @author Avi margali
 * @author Yeuda Nuiman
 */


package com.ud_avi.raytrace.scene;

import com.ud_avi.raytrace.geometries.BBox;
import com.ud_avi.raytrace.geometries.Geometries;
import com.ud_avi.raytrace.geometries.Geometry;
import com.ud_avi.raytrace.primitives.Point3D;
import com.ud_avi.raytrace.primitives.Ray;
import com.ud_avi.raytrace.primitives.Vector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *    Grid Class represent a 3D grid that holds the scene in it
 */
public class Grid {
    private BBox _bbox;

    // The two Voxel's in the extreme corners of the grid
    private VoxelIndex _voxelIndexMax;
    private VoxelIndex _voxelIndexMin;

    // Resolution of the grid
    private double _gridResX;
    private double _gridResY;
    private double _gridResZ;

    // Size of Voxel
    private double _voxelSizeX;
    private double _voxelSizeY;
    private double _voxelSizeZ;

    private VoxelIndex voxelArray[];
    /**
     * Inner class to represent a 3D cell in the grid
     */
    private class VoxelIndex {
        private int _x, _y, _z;
        private Geometries _geometries = new Geometries();

        /**
         * Constructor - get 3 indexes
         *
         * @param x index
         * @param y index
         * @param z index
         */
        private VoxelIndex(int x, int y, int z) {
            _x = x;
            _y = y;
            _z = z;
        }

        /**
         * Insert geometry into Geometry list
         * @param geometry to insert
         */
        void insert(Geometry geometry){
            _geometries.addGeometry(geometry);
        }

        /**
         * Optimized Find Intersection
         * @param ray the ray to intersect with
         * @param pathRay the geometries that the ray already check for intersection
         * @return map of geometries and points
         */
        public Map<Geometry, List<Point3D>> findIntersections(Ray ray, Map<Geometry, Boolean> pathRay){
            Map<Geometry, List<Point3D>> intersections  = new HashMap<>();
            for(Geometry geometry: _geometries.getGeometriesList())
                if( pathRay.get(geometry)== null || pathRay.size() == 0 || !pathRay.get(geometry)) {
                    intersections.putAll(geometry.findIntersections(ray));//findIntersections can be empty

                //update all the geometries
                pathRay.put(geometry,true);
            }

            return intersections;
        }

        /**
         * @return the geometries list hold's by this voxel
         */
        public Geometries getGeometries() {
            return _geometries;
        }
    }

    /**
     * Construct the Grid.
     * for now you need to give the sizes of the voxel
     * @param geometries the geometries
     * @param lambda factor that the user can control the resolution of the grid [3,5]
     */
    public Grid(Geometries geometries, double lambda) {
        _bbox = new BBox(geometries.getMin(),geometries.getMax());

        // calculates the size of the grid
        double _gridSizeX = _bbox.getMax().getX().getCoord() - _bbox.getMin().getX().getCoord();
        double _gridSizeY = _bbox.getMax().getY().getCoord() - _bbox.getMin().getY().getCoord();
        double _gridSizeZ = _bbox.getMax().getZ().getCoord() - _bbox.getMin().getZ().getCoord();

        //make sure that the grid has some volume
        if(_gridSizeX == 0.0)
            _gridSizeX = 0.01;
        if(_gridSizeY == 0.0)
            _gridSizeY = 0.01;
        if(_gridSizeZ == 0.0)
            _gridSizeZ = 0.01;

        //calculate the voxel size by the formula
        //Nx = dx * sqr(λN/V,3)
        //Nx is the grid resolution
        //dx is the size of the grid
        //N is the number of the geometries
        //V is the volume of the grid
        //λ is a user define parameter (3-5)
        double cubeRoot = Math.pow(lambda * geometries.namOfGeometries() / (_gridSizeX * _gridSizeY * _gridSizeZ), 1 / 3d);
        _gridResX = Math.floor(_gridSizeX * cubeRoot);
        _gridResX = Math.max(1, Math.min(_gridResX, 128));

        _gridResY = Math.floor(_gridSizeY * cubeRoot);
        _gridResY = Math.max(1, Math.min(_gridResY, 128));

        _gridResZ = Math.floor(_gridSizeZ * cubeRoot);
        _gridResZ = Math.max(1, Math.min(_gridResZ, 128));

        //the size of the grid
        _voxelSizeX = _gridSizeX / _gridResX;
        _voxelSizeY = _gridSizeY / _gridResY;
        _voxelSizeZ = _gridSizeZ / _gridResZ;

        _voxelIndexMax = convertPointToVoxel(_bbox.getMax());
        _voxelIndexMin = convertPointToVoxel(_bbox.getMin());

        //allocate memory for the grid voxel's
        voxelArray = new VoxelIndex[(int)(_gridResX * _gridResY *_gridResZ)];
        // Insert all the geometry in scene into the voxel's
        insertToVoxel(geometries.getGeometriesList());

    }

    /**
     * Runs on all the geometry in scene and insert each geometry to the voxel
     * @param geometries the geometries
     */
    private void insertToVoxel(List<Geometry> geometries) {
        for (Geometry geometry : geometries) {
            VoxelIndex maxVox = convertPointToVoxel(geometry.getMax());
            VoxelIndex minVox = convertPointToVoxel(geometry.getMin());

            //fill the voxel's with the geometries
            for (int z = minVox._z; z <= maxVox._z; ++z) {
                for (int y = minVox._y; y <= maxVox._y; ++y) {
                    for (int x = minVox._x; x <= maxVox._x; ++x) {
                        int o = (int) (z * _gridResX  * _gridResY + y * _gridResX + x);
                        if(voxelArray[o] == null)
                            voxelArray[o] = new VoxelIndex(x,y,z);
                        voxelArray[o].insert(geometry);
                    }
                }
            }
        }
    }

    /**
     * Find intersection point with the grid
     * @param ray the ray to check with
     * @return the "enter point"
     */
    private Point3D findIntersectionPoint(Ray ray){
        Map<Geometry, List<Point3D>> intersectionPoints = _bbox.findIntersections(ray);
        // The closest intersection point = the enter point into the grid
        double tempDist, minDist = Double.MAX_VALUE;

        Point3D P0 = ray.getPoint();
        Point3D closestPoint = null;
        //find the minimum point
        for (Map.Entry<Geometry, List<Point3D>> entry : intersectionPoints.entrySet()) {
            for (Point3D p : entry.getValue()) {
                tempDist = p.distance(P0);
                if (tempDist < minDist) {
                    closestPoint = p;
                    minDist = tempDist;
                }
            }
        }
        return closestPoint;
    }

    /**
     * Find intersection of ray
     * @param ray to find intersection with
     * @return map of Geometries that ray intersect with
     */
    public Map<Geometry, List<Point3D>> findIntersection(Ray ray) {
        Map<Geometry, List<Point3D>> intersectionMap = new HashMap<>();
        Map<Geometry, Boolean> pathOfRayGeometries = new HashMap<>();

        Point3D orig = ray.getPoint();
        //check if the point is inside the grid
        if (orig.getX().getCoord() >  _bbox.getMax().getX().getCoord()
                || orig.getX().getCoord() < _bbox.getMin().getX().getCoord()
                || orig.getY().getCoord() >  _bbox.getMax().getX().getCoord()
                || orig.getY().getCoord() < _bbox.getMin().getX().getCoord()
                || orig.getZ().getCoord() >  _bbox.getMax().getX().getCoord()
                || orig.getZ().getCoord() <  _bbox.getMin().getX().getCoord()) {
            orig = findIntersectionPoint(ray);
        }

        double dirX = ray.getDirection().getHead().getX().getCoord();
        double dirY = ray.getDirection().getHead().getY().getCoord();
        double dirZ = ray.getDirection().getHead().getZ().getCoord();
        double invdirX = 1d/dirX;
        double invdirY = 1d/dirY;
        double invdirZ = 1d/dirZ;
        double deltaX, deltaY, deltaZ, nctX, nctY, nctZ;

        if(null == orig)
            return intersectionMap;

        VoxelIndex rayOrigVoxel = convertPointToVoxel(orig);
        Vector rayOrigGrid = orig.subtract(_bbox.getMin());
        double rayOrigGridX = rayOrigGrid.getHead().getX().getCoord();
        double rayOrigGridY = rayOrigGrid.getHead().getY().getCoord();
        double rayOrigGridZ = rayOrigGrid.getHead().getZ().getCoord();

        if(dirX<0){
            deltaX = -_voxelSizeX * invdirX;
            nctX = (rayOrigVoxel._x * _voxelSizeX - rayOrigGridX) * invdirX;
        }else{
            deltaX = _voxelSizeX * invdirX;
            nctX = ((rayOrigVoxel._x + 1) * _voxelSizeX - rayOrigGridX) * invdirX;
        }

        if(dirY<0){
            deltaY = -_voxelSizeY * invdirY;
            nctY = (rayOrigVoxel._y * _voxelSizeY - rayOrigGridY) * invdirY;
        }else{
            deltaY = _voxelSizeY * invdirY;
            nctY = ((rayOrigVoxel._y + 1) * _voxelSizeY - rayOrigGridY) * invdirY;
        }

        if(dirZ<0){
            deltaZ = -_voxelSizeZ * invdirZ;
            nctZ = (rayOrigVoxel._z * _voxelSizeZ - rayOrigGridZ) * invdirZ;
        }else{
            deltaZ = _voxelSizeZ * invdirZ;
            nctZ = ((rayOrigVoxel._z + 1) * _voxelSizeZ - rayOrigGridZ) * invdirZ;
        }

        //travel over the Grid
        while(true){
            int o = (int)(rayOrigVoxel._z * _gridResX * _gridResY + rayOrigVoxel._y * _gridResX + rayOrigVoxel._x);
            if (voxelArray[o] != null) {
                intersectionMap.putAll(voxelArray[o].findIntersections(ray,pathOfRayGeometries));
                Map<Geometry, List<Point3D>> geoIntersections = voxelArray[o].findIntersections(ray,pathOfRayGeometries);
                intersectionMap.putAll(geoIntersections);//findIntersections can be empty
                //this is more optimized then just "grid"
                //while you have even one intersection, return.
                if(geoIntersections.size()>0){
                    break;
                }
            }

            if (nctX < nctY) {
                if (nctX < nctZ) {
                    nctX += deltaX;
                    rayOrigVoxel._x += (dirX < 0) ? -1 : 1;
                    if (rayOrigVoxel._x < _voxelIndexMin._x || rayOrigVoxel._x > _voxelIndexMax._x) break;
                } else {
                    nctZ += deltaZ;
                    rayOrigVoxel._z += (dirZ < 0) ? -1 : 1;
                    if (rayOrigVoxel._z < _voxelIndexMin._z || rayOrigVoxel._z > _voxelIndexMax._z) break;
                }
            } else {
                if (nctY < nctZ) {
                    nctY += deltaY;
                    rayOrigVoxel._y += (dirY < 0) ? -1 : 1;
                    if (rayOrigVoxel._y < _voxelIndexMin._y || rayOrigVoxel._y > _voxelIndexMax._y) break;
                } else {
                    nctZ += deltaZ;
                    rayOrigVoxel._z += (dirZ < 0) ? -1 : 1;
                    if (rayOrigVoxel._z < _voxelIndexMin._z || rayOrigVoxel._z > _voxelIndexMax._z) break;
                }
            }
        }
        return intersectionMap;
    }

    /**
     * find the Voxel of a point
     * @param point the point on the geometry
     * @return the voxel that the point is inside it
     */
    private VoxelIndex convertPointToVoxel(Point3D point) {
        VoxelIndex voxel = new VoxelIndex((int) ((point.getX().getCoord() - _bbox.getMin().getX().getCoord()) / _voxelSizeX),
                (int) ((point.getY().getCoord() - _bbox.getMin().getY().getCoord()) / _voxelSizeY),
                (int) ((point.getZ().getCoord() - _bbox.getMin().getZ().getCoord()) / _voxelSizeZ));

        voxel._x = Math.max(0, Math.min(voxel._x, (int)(_gridResX -1)));
        voxel._y = Math.max(0, Math.min(voxel._y, (int)(_gridResY -1)));
        voxel._z = Math.max(0, Math.min(voxel._z, (int)(_gridResZ -1)));
        return voxel;
    }
}
