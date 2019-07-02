/**
 * @author Avi margali
 * @author Yeuda Nuiman
 */


package com.ud_avi.raytrace;

import com.ud_avi.raytrace.elements.Camera;
import com.ud_avi.raytrace.geometries.Geometries;
import com.ud_avi.raytrace.geometries.Rectangle;
import com.ud_avi.raytrace.geometries.Sphere;
import com.ud_avi.raytrace.lights.AmbientLight;
import com.ud_avi.raytrace.lights.DirectionalLight;
import com.ud_avi.raytrace.lights.LightSource;
import com.ud_avi.raytrace.lights.SpotLight;
import com.ud_avi.raytrace.primitives.Color;
import com.ud_avi.raytrace.primitives.Material;
import com.ud_avi.raytrace.primitives.Point3D;
import com.ud_avi.raytrace.primitives.Vector;
import com.ud_avi.raytrace.renderer.ImageWriter;
import com.ud_avi.raytrace.renderer.Render;
import com.ud_avi.raytrace.scene.Grid;
import com.ud_avi.raytrace.scene.Scene;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.fail;


public class GridTest {

    @Test
    public void testConstructor(){

        Geometries geometries = new Geometries();

        Sphere sphere00 = new Sphere(
                new Color(10, 70, 200),
                new Point3D(0, 0, 50),50,
                new Material(2,1,0, 0.9, 2));
        Sphere sphere01 = new Sphere(
                new Color(150, 70, 200),
                new Point3D(0, 0, -50),50,
                new Material(2,1,0, 0, 2));
        Sphere sphere10 = new Sphere(
                new Color(48, 240, 48),
                new Point3D(150, 0, 50),50,
                new Material(2,1,0, 0, 2));
        Sphere sphere11 = new Sphere(
                new Color(180, 180, 200),
                new Point3D(150, 0, -150),50,
                new Material(2,1,0, 0, 2));

        geometries.addGeometry(sphere00);
        geometries.addGeometry(sphere01);
        geometries.addGeometry(sphere10);
        geometries.addGeometry(sphere11);

        Grid grid = new Grid(geometries,3);
        try {
            //get the private fields
            Field voxelArray = grid.getClass().getDeclaredField("voxelArray");
            voxelArray.setAccessible(true);

            Object [] arr = (Object []) voxelArray.get(grid);

            for(int i = 0;i<arr.length; ++i){
                if(arr[i] == null) {
                    continue;
                }
                Field _geometries = arr[i].getClass().getDeclaredField("_geometries");
                _geometries.setAccessible(true);
                Geometries g1 = (Geometries) _geometries.get(arr[i]);
                if(i==1||i==2||i==3||i==5)
                    assertEquals(1, g1.namOfGeometries());
                else
                    assertEquals(2, g1.namOfGeometries());

            }
        }catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testIntersections() {


    }
    //TODO: fix this test
    //@Test
    public void basicGridTest(){

        Scene scene = new Scene("Test");
        scene.setCamera(new Camera(new Point3D(0, 0, -900),new Vector(0, -1, 0), new Vector(0,0,1)));

        scene.setDistance(900);
        scene.setBackgroundColor(new Color(0,0,0));
        scene.setAmbientLight(new AmbientLight(new Color(50, 50, 50), 0.1));
        Geometries geometries = new Geometries();

        Sphere sphere00 = new Sphere(
                new Color(10, 70, 200),
                new Point3D(0, 0, 50),50,
                new Material(2,1,0, 0.9, 2));
        Sphere sphere01 = new Sphere(
                new Color(150, 70, 200),
                new Point3D(0, 0, -50),50,
                new Material(2,1,0, 0, 2));
        Sphere sphere10 = new Sphere(
                new Color(48, 240, 48),
                new Point3D(150, 0, 50),50,
                new Material(2,1,0, 0, 2));
        Sphere sphere11 = new Sphere(
                new Color(180, 180, 200),
                new Point3D(150, 0, -150),50,
                new Material(2,1,0, 0, 2));
        Sphere sphere001 = new Sphere(
                new Color(10, 70, 200),
                new Point3D(0, 100, -50),50,
                new Material(2,1,0, 0, 2));
        Sphere sphere011 = new Sphere(
                new Color(150, 70, 200),
                new Point3D(0, 100, -150),50,
                new Material(2,1,0, 0, 2));
        Sphere sphere101 = new Sphere(
                new Color(48, 240, 48),
                new Point3D(150, 100, -50),50,
                new Material(2,1,0, 0, 2));
        Sphere sphere111 = new Sphere(
                new Color(180, 180, 200),
                new Point3D(150, 100, 150),50,
                new Material(2,1,0, 0, 2));
        geometries.addGeometry(sphere00);
        geometries.addGeometry(sphere01);
        geometries.addGeometry(sphere10);
        geometries.addGeometry(sphere11);
//
//        geometries.addGeometry(sphere001);
//        geometries.addGeometry(sphere011);
//        geometries.addGeometry(sphere101);
//        geometries.addGeometry(sphere111);

        scene.setGeometries(geometries);

        List<LightSource> lights = new ArrayList<>();
        lights.add(new DirectionalLight(new Color(0,0,200),new Vector(0,1,1)));
        scene.setLightSources(lights);
        ImageWriter imageWriter = new ImageWriter("GridTestOptThread", 500, 500, 500, 500);
        Render testRender = new Render(imageWriter,scene);
        testRender.setMultiThread(true);
        testRender.setGridOpt(true);

        testRender.renderImage();
        testRender.writeToImage();
    }
    @Test
    public void recGridTest(){

        Scene scene = new Scene("Test");
        scene.setCamera(new Camera(new Point3D(0, 0, -930),new Vector(0, -1, 0), new Vector(0,0,1)));

        scene.setDistance(800);
        scene.setBackgroundColor(new Color(0,0,0));
        scene.setAmbientLight(new AmbientLight(new Color(50, 50, 50), 0.1));
        Geometries geometries = new Geometries();

        Rectangle rec = new Rectangle(new Point3D(0,0,0),new Point3D(50,0,0),new Point3D(0,50,0),
                new Color(40,50,40),new Material(1,2,3,4,5));
        geometries.addGeometry(rec);
        scene.setGeometries(geometries);

        List<LightSource> lights = new ArrayList<>();
        lights.add(new DirectionalLight(new Color(0,0,200),new Vector(0,1,1)));
        scene.setLightSources(lights);
        ImageWriter imageWriter = new ImageWriter("recGrid", 250, 250, 250, 250);
        Render testRender = new Render(imageWriter,scene);
        testRender.setMultiThread(true);
        testRender.setGridOpt(true);

        testRender.renderImage();
        testRender.writeToImage();
    }

}
