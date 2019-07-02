/**
 * Course: 151055 Software Engineering intro, Lab
 * Student1: Avi margali 305645137
 * Student2: Yeuda Nuiman 301759692
 * Teacher: Dan Zilberstein
 */

package com.ud_avi.raytrace.renderer;

import com.ud_avi.raytrace.geometries.Geometry;
import com.ud_avi.raytrace.lights.LightSource;
import com.ud_avi.raytrace.primitives.*;
import com.ud_avi.raytrace.scene.Grid;
import com.ud_avi.raytrace.scene.Scene;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Render is the class that renderTests the image from scene
 */
public class Render {
    private static final int MAX_COLOR_LEVEL = 2;
    private ImageWriter _imageWriter;
    private Scene _scene;
    private Boolean _multiThread = false;
    private Boolean _gridOpt = false;
    private Grid _grid;
    private double _lambdaGrid = 5.0;

    /**
     * GeometryPoint is pair of Geometry and Point
     */
    private static class GeometryPoint {
        public Geometry geometry;
        public Point3D point;
    }

    // ***************** Constructors ********************** //

    /**
     * Constructor
     * @param imageWriter ImageWriter object
     * @param scene the Scene
     */
    public Render(ImageWriter imageWriter, Scene scene) {
        _imageWriter = imageWriter;
        _scene = scene;
    }

    // ***************** Getters/Setters ********************** //

    /**
     * @return the imageWriter object
     */
    public ImageWriter getImageWriter() {
        return _imageWriter;
    }

    /**
     * @return the scene object
     */
    public Scene getScene() {
        return _scene;
    }

    /**
     * @param multiThread set the multi thread option
     */
    public void setMultiThread(Boolean multiThread) {
        _multiThread = multiThread;
    }

    /**
     * @return if the renderTests is working with grid
     */
    public Boolean getGridOpt() {
        return _gridOpt;
    }

    /**
     * @return the lambda factor of the grid
     */
    public double getLambdaGrid() {
        return _lambdaGrid;
    }

    /**
     * @param lambdaGrid the lambda factor of the grid [3,5]
     */
    public void setLambdaGrid(double lambdaGrid) {
        _lambdaGrid = lambdaGrid;
    }

    /**
     * @param gridOpt set if the renderTests is working with grid
     */
    public void setGridOpt(Boolean gridOpt) {
        _gridOpt = gridOpt;
        if(gridOpt)
            _grid = new Grid(_scene.getGeometries(), getLambdaGrid());
    }

    // ***************** Operations ******************** //

    /**
     * renders the image to the screen
     */
    @SuppressWarnings("Duplicates") //ignore intellij warning
    public void renderImage() throws RuntimeException {
        int Nx = _imageWriter.getNx();
        int Ny = _imageWriter.getNy();
        double distance = _scene.getDistance();
        int width = _imageWriter.getWidth();
        int height = _imageWriter.getHeight();

        if (_multiThread) {//use multi thread
            final ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 4, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
            System.out.print("Number of Threads: " + numberOfThreads().toString());
            //go for every pixel and calculate the color
            for (int i = 1; i < Nx; ++i) {
                for (int j = 1; j < Ny; ++j) {
                    final int ii = i;
                    final int jj = j;
                    executor.execute(() -> {
                        try {
                            //calculate the ray
                            List<Ray> listRays = _scene.getCamera().constructRays(Nx, Ny, ii, jj, distance, width, height);
                            //find intersections
                            Map<Ray, GeometryPoint> intersectionsMap = new HashMap<>();
                            for (Ray r : listRays) {
                                Map<Geometry, List<Point3D>> intersections = _gridOpt ? _grid.findIntersection(r) : _scene.getGeometries().findIntersections(r);
                                if (!intersections.isEmpty()) {
                                    GeometryPoint closestPoint = getClosestPoint(intersections);
                                    intersectionsMap.put(r, closestPoint);
                                }
                            }
                            //for intersection color it
                            if (!intersectionsMap.isEmpty()) {
                                _imageWriter.writePixel(ii, jj, calcColor(intersectionsMap).getColor());
                               } else {
                                _imageWriter.writePixel(ii, jj, _scene.getBackgroundColor().getColor());
                            }
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
            }
            executor.shutdown();
            try {
                executor.awaitTermination(550, TimeUnit.MINUTES);
            }catch (Exception e){//timeout
                //throw new RuntimeException(e);
            }
        } else {
            //go for every pixel and calculate the color
            for (int i = 1; i < Nx; ++i) {
                for (int j = 1; j < Ny; ++j) {
                    //calculate the ray
                    List<Ray> listRays = _scene.getCamera().constructRays(Nx, Ny, i, j, distance, width, height);
                    //find intersections
                    Map<Ray, GeometryPoint> intersectionsMap = new HashMap<>();
                    for (Ray r : listRays) {
                        Map<Geometry, List<Point3D>> intersections = _gridOpt ? _grid.findIntersection(r) : _scene.getGeometries().findIntersections(r);
                        if (!intersections.isEmpty()) {
                            GeometryPoint closestPoint = getClosestPoint(intersections);
                            intersectionsMap.put(r, closestPoint);
                        }
                    }
                    //for intersection color it
                    if (!intersectionsMap.isEmpty()) {
                        _imageWriter.writePixel(i, j, calcColor(intersectionsMap).getColor());
                    } else {
                        _imageWriter.writePixel(i, j, _scene.getBackgroundColor().getColor());
                    }
                }
            }
        }
    }

    /**
     * @return Number of Threads in system
     */
    private Integer numberOfThreads() {
        if (System.getenv("THREADS") != null)
            return Math.max(1, Integer.parseInt(System.getenv("THREADS")));
        else
            return Runtime.getRuntime().availableProcessors();
    }

    /**
     * Print grid on the background of the image
     * @param interval divide the grid by interval
     */
    public void printGrid(int interval) {
        try {
            for (int i = 1; i < _imageWriter.getHeight(); i++) {
                for (int j = 1; j < _imageWriter.getWidth(); j++) {
                    if (i % interval == 0 || j % interval == 0) {
                        _imageWriter.writePixel(j, i, _scene.getAmbientLight().getIntensity().getColor());
                    }
                }
            }
            _imageWriter.writeToimage();
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    /**
     * calculate the color of point on Geometry
     * @param intersectionsMap intersections map
     * @return the color at this point
     */
    private Color calcColor( Map<Ray,GeometryPoint> intersectionsMap) {
        Color color = new Color(Color.BLACK);
        for(Map.Entry<Ray, GeometryPoint> entry : intersectionsMap.entrySet()){
            color.add(calcColor(entry.getValue(), entry.getKey()));
        }
        color.reduce(_scene.getCamera().getNumOfRays());
        return color;
    }

    /**
     * calculate the color of point on Geometry
     * @param ray  ray to trace
     * @param geometryPoint  point and geometry
     * @return the color at this point
     */
    private Color calcColor(GeometryPoint geometryPoint, Ray ray) {
        return calcColor(geometryPoint, ray, MAX_COLOR_LEVEL, 1.0);
    }

    /**
     * calculate the color of point on Geometry, recursively.
     * @param k factor
     * @param level how deep to trace
     * @param ray  ray to trace
     * @param geometryPoint  point and geometry
     * @return the color at this point
     */
    private Color calcColor(GeometryPoint geometryPoint, Ray ray, int level, double k) {
        if (level == 0 || Coordinate.ZERO.equals(k)) return Color.BLACK;

        double kr = geometryPoint.geometry.getMaterial().getKr();
        double kt = geometryPoint.geometry.getMaterial().getKt();

        Color color = new Color(_scene.getAmbientLight().getIntensity().scale(Math.max(0, 1 - kr - kt)));
        color.add(geometryPoint.geometry.getEmission());

        Vector v = ray.getDirection();

        List<LightSource> lightsList = _scene.getLightSources();
        if (lightsList == null || lightsList.isEmpty()) //no lights in the scene
            return color;

        Vector N = geometryPoint.geometry.getNormal(geometryPoint.point);
        int nShininess = geometryPoint.geometry.getMaterial().getnShininess();
        double kd = geometryPoint.geometry.getMaterial().getKd();
        double ks = geometryPoint.geometry.getMaterial().getKs();
        //add lights
        for (LightSource lightSource : lightsList) {
            Vector L = lightSource.getL(geometryPoint.point);
            if (L.dotProduct(N) * v.dotProduct(N) > 0 && !Coordinate.ZERO.equals(L.dotProduct(N))) {
                double o = occluded(lightSource, L, geometryPoint);
                if (!Coordinate.ZERO.equals(o * k)) {
                    Color lightIntensity = new Color(lightSource.getIntensity(geometryPoint.point).scale(o));
                    color.add(calcDiffusive(kd, L, N, lightIntensity));
                    color.add(calcSpecular(ks, L, N, v, nShininess, lightIntensity));
                }
            }
        }
        //add reflection
        Ray reflectedRay = constructReflectedRay(N, geometryPoint.point, v);
        Map<Geometry, List<Point3D>> reflectedIntersections = _gridOpt ?_grid.findIntersection(reflectedRay) : _scene.getGeometries().findIntersections(reflectedRay);
        Color reflectedLight = new Color(Color.BLACK);
        if (!reflectedIntersections.isEmpty()) {
            GeometryPoint reflectedPoint = getClosestPoint(reflectedIntersections);
            reflectedLight = calcColor(reflectedPoint, reflectedRay, level - 1, k * kr).scale(kr);
        }
        //add refraction
        Ray refractedRay = constructRefractedRay(N, geometryPoint.point, v);
        Map<Geometry, List<Point3D>> refractedIntersections = _gridOpt ?_grid.findIntersection(refractedRay) : _scene.getGeometries().findIntersections(refractedRay);
        Color refractedLight = new Color(Color.BLACK);
        if (!refractedIntersections.isEmpty()) {
            GeometryPoint refractedPoint = getClosestPoint(refractedIntersections);
            refractedLight = calcColor(refractedPoint, refractedRay, level - 1, k * kt).scale(kt);
        }
        color.add(reflectedLight);
        color.add(refractedLight);
        return color;
    }

    /**
     * Construct Reflected Ray
     * @param n normal
     * @param point point on the geometry
     * @param direction of the original ray
     * @return the Reflected Ray
     */
    private Ray constructReflectedRay(Vector n, Point3D point, Vector direction) {
        Vector reflectedVector = direction.subtract(n.scale(2 * direction.dotProduct(n))).normalize();
        Point3D epsPoint = movePointEps(n, point, reflectedVector);
        return new Ray(reflectedVector,epsPoint);
    }

    /**
     * Construct Refracted Ray
     * @param n normal
     * @param point point on the geometry
     * @param direction of the original ray
     * @return the Refracted Ray
     */
    private Ray constructRefractedRay(Vector n, Point3D point, Vector direction) {
        Point3D epsPoint = movePointEps(n, point, direction);
        return new Ray(direction, epsPoint);
    }

    /**
     * calculating the Diffusive factor
     * @param kd Diffuse factor
     * @param L light direction
     * @param N normal
     * @param lightIntensity li
     * @return Color
     */
    private Color calcDiffusive(double kd, Vector L, Vector N, Color lightIntensity) {
        Color Li = new Color(lightIntensity);
        return Li.scale(kd * Math.abs(L.dotProduct(N)));
    }

    /**
     * calculating the Specular  factor
     * @param ks Specular factor
     * @param L Light direction
     * @param N normal
     * @param V vector form the camera to the object
     * @param nShininess shininess factor
     * @param lightIntensity light Intensity
     * @return Color
     */
    private Color calcSpecular(double ks, Vector L, Vector N, Vector V, int nShininess, Color lightIntensity) {
        Color Li = new Color(lightIntensity);
        Vector r = L.subtract(N.scale(2 * L.dotProduct(N))).normalize();
        double vr = V.dotProduct(r);
        return (vr < 0) ? Li.scale(ks * Math.pow(-vr, nShininess)) : new Color(0, 0, 0);
    }

    /**
     * add Epsilon to point
     * @param normal normal
     * @param point point to move
     * @param v ray
     * @return new point with little "push"
     */
    private Point3D movePointEps(Vector normal, Point3D point, Vector v) {
        Vector epsVector = normal.scale(normal.dotProduct(v) > 0 ? 2 : -2);
        return point.addVector(epsVector);
    }

    /**
     * get's the closest point to the starting point of the ray
     * @param pointsMap Map of points with geometries
     * @return the closest point to the view
     */
    private GeometryPoint getClosestPoint(Map<Geometry, List<Point3D>> pointsMap) {
        GeometryPoint min = new GeometryPoint();
        Point3D cameraPoint = _scene.getCamera().getCenterPoint();
        double minDist = Double.MAX_VALUE;
        double tempDist;
        //goes through every geometry and find's the closest point
        for (Map.Entry<Geometry, List<Point3D>> entry : pointsMap.entrySet()) {
            //goes through the list and find the closest point of that list
            for (Point3D p : entry.getValue()) {
                tempDist = p.distance(cameraPoint);
                if (tempDist < minDist) {
                    minDist = tempDist;
                    min.geometry = entry.getKey();
                    min.point = p;
                }
            }
        }
        return min;
    }

    /**
     * write the renderer to image
     */
    public void writeToImage() {
        _imageWriter.writeToimage();
    }

    /**
     * calculate the level of the shadow
     * @param L vector
     * @param geometryPoint Geometry
     * @return the level of the shadow
     */
    private double occluded(LightSource lightSource, Vector L, GeometryPoint geometryPoint) {
        Vector lightDirection = L.scale(-1);
        //move the point by eps in the normal direction
        Vector normal = geometryPoint.geometry.getNormal(geometryPoint.point);
        Point3D epsPoint = movePointEps(normal, geometryPoint.point, lightDirection);

        //shadow ray
        Ray fromObjToLight = new Ray(lightDirection, epsPoint);
        //intersection withe the shadow ray
        Map<Geometry, List<Point3D>> intersections = _gridOpt ?_grid.findIntersection(fromObjToLight) : _scene.getGeometries().findIntersections(fromObjToLight);
        //calc the accumulated value of the shadow
        double maxD = lightSource.getDistance(geometryPoint.point);
        double shadowK = 1;
        for (Map.Entry<Geometry, List<Point3D>> entry : intersections.entrySet()) {
            //for every point check if it is behind the light
            for (Point3D p : entry.getValue()) {
                double d = geometryPoint.point.distance(p);
                if (d < maxD)
                    shadowK *= entry.getKey().getMaterial().getKt();
            }
        }
        return shadowK;
    }
}
