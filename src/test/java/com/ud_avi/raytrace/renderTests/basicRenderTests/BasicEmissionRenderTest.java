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

public class BasicEmissionRenderTest extends BasicRenderTest {

    @Test
    public void basicEmissionRender() {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+" now running...");
        Triangle t1 = new Triangle(new Point3D(100, 0, -49), new Point3D(0, 100, -49), new Point3D(100, 100, -49), new Color(150, 0, 0), new Material(500, 0.5, 1, 1, 100));
        Triangle t2 = new Triangle(new Point3D(-100, 0, -49), new Point3D(0, 100, -49), new Point3D(-100, 100, -49), new Color(0, 150, 0),
                new Material(500, 0.5, 1, 1, 100));
        Triangle t3 = new Triangle(new Point3D(100, 0, -49), new Point3D(0, -100, -49), new Point3D(100, -100, -49), new Color(0, 0, 150),
                new Material(500, 0.5, 1, 1, 100));
        Triangle t4 = new Triangle(new Point3D(-100, 0, -49), new Point3D(0, -100, -49), new Point3D(-100, -100, -49), new Color(255, 255, 255),
                new Material(500, 0.5, 1, 1, 100));
        Sphere s = new Sphere(new Color(0, 250, 250),new Point3D(0, 0, -50),40, new Material(500, 0.5, 1, 1, 100));
        Scene testScene = new Scene("testScene");
        testScene.setBackgroundColor(new Color(75, 127, 190));
        testScene.setAmbientLight(new AmbientLight(new Color(150, 150, 150), 0.5));
        testScene.setCamera(new Camera(new Point3D(0,0,5), new Vector(0, -1, 0), new Vector(0, 0, -1)));
        testScene.setDistance(50);
        Geometries geometries = new Geometries();
        testScene.setGeometries(geometries);
        geometries.addGeometry(t1);
        geometries.addGeometry(t2);
        geometries.addGeometry(t3);
        geometries.addGeometry(t4);
        geometries.addGeometry(s);
        ImageWriter testImageWriter = new ImageWriter("basicEmission", 500, 500, 500, 500);
        Render testRender = new Render(testImageWriter,testScene);

        testRender.renderImage();
        testRender.printGrid(50);

    }
}
