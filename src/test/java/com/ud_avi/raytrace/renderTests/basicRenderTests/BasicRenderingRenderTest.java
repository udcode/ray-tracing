package com.ud_avi.raytrace.renderTests.basicRenderTests;

import com.ud_avi.raytrace.elements.Camera;
import com.ud_avi.raytrace.geometries.Geometries;
import com.ud_avi.raytrace.geometries.Sphere;
import com.ud_avi.raytrace.geometries.Triangle;
import com.ud_avi.raytrace.lights.AmbientLight;
import com.ud_avi.raytrace.primitives.Color;
import com.ud_avi.raytrace.primitives.Material;
import com.ud_avi.raytrace.primitives.Point3D;
import com.ud_avi.raytrace.primitives.Vector;
import com.ud_avi.raytrace.renderTests.BasicRenderTest;
import com.ud_avi.raytrace.renderer.ImageWriter;
import com.ud_avi.raytrace.renderer.Render;
import com.ud_avi.raytrace.scene.Scene;
import org.junit.Test;

public class BasicRenderingRenderTest extends BasicRenderTest {

    @Test
    public void basicRendering(){
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+" now running...");
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
        scene.setDistance(100);
        scene.setBackgroundColor(new Color(0, 0, 0));
        scene.setAmbientLight(new AmbientLight(new Color(255,255,255),1));

        Geometries geometries = new Geometries();
        scene.setGeometries(geometries);
        geometries.addGeometry(new Sphere(new Color(255, 255, 255), new Point3D(0, 0, 150),50,new Material(500,0.5, 1, 1, 100)));

        geometries.addGeometry(new Triangle(new Point3D( 100, 0, 149), new Point3D(  0, 100, 149), new Point3D( 100, 100, 149), new Color(255, 255, 255),
                new Material(500, 0.5, 1, 1, 100)));

        geometries.addGeometry(new Triangle(new Point3D( 100, 0, 149), new Point3D(  0, -100, 149), new Point3D( 100,-100, 149), new Color(255, 255, 255),
                new Material(500, 0.5, 1, 1, 100)));

        geometries.addGeometry(new Triangle(new Point3D(-100, 0, 149), new Point3D(  0, 100, 149), new Point3D(-100, 100, 149), new Color(255, 255, 255),
                new Material(500, 0.5, 1, 1, 100)));

        geometries.addGeometry(new Triangle(new Point3D(-100, 0, 149), new Point3D(  0,  -100, 149), new Point3D(-100, -100, 149), new Color(255, 255, 255),
                new Material(500, 0.5, 1, 1, 100)));

        ImageWriter imageWriter = new ImageWriter("BasicRendering", 500, 500, 500, 500);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
        render.setGridOpt(false);
        render.setMultiThread(false);
        render.printGrid(50);
        render.writeToImage();
    }
}
