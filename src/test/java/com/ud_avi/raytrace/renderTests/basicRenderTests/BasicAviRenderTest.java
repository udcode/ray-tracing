package com.ud_avi.raytrace.renderTests.basicRenderTests;

import com.ud_avi.raytrace.elements.Camera;
import com.ud_avi.raytrace.geometries.Geometries;
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


public class BasicAviRenderTest extends BasicRenderTest {

    @Test
    public void basicAviRender() {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+" now running...");
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
        scene.setDistance(100);
        scene.setBackgroundColor(new Color(135, 206, 250));
        scene.setAmbientLight(new AmbientLight(Color.WHITE,1));
        Geometries geometries = new Geometries();
        scene.setGeometries(geometries);

        geometries.addGeometry(new Triangle(new Point3D(-150, 0, 149), new Point3D(75, 0, 149), new Point3D(75, -50, 149), new Color(255, 255, 255),
                new Material(500, 0.5, 0, 0, 100)));

        geometries.addGeometry(new Triangle(new Point3D(-150, 0, 149), new Point3D(75, -50, 149), new Point3D(-150, -50, 149), new Color(255, 255, 255),
                new Material(500, 0.5, 0, 0, 100)));

        geometries.addGeometry(new Triangle(new Point3D(-150, -200, 149), new Point3D(50, -200, 149), new Point3D(50, -150, 149), new Color(255, 255, 255),
                new Material(500, 0.5, 0, 0, 100)));

        geometries.addGeometry(new Triangle(new Point3D(-150, -200, 149), new Point3D(50, -150, 149), new Point3D(-150, -150, 149), new Color(255, 255, 255),
                new Material(500, 0.5, 0, 0, 100)));

        geometries.addGeometry(new Triangle(new Point3D(0, -200, 149), new Point3D(50, -200, 149), new Point3D(50, 0, 149), new Color(255, 255, 255),
                new Material(500, 0.5, 0, 0, 100)));

        geometries.addGeometry(new Triangle(new Point3D(0, -200, 149), new Point3D(50, 0, 149), new Point3D(0, 0, 149), new Color(255, 255, 255),
                new Material(500, 0.5, 0, 0, 100)));

        geometries.addGeometry(new Triangle(new Point3D(-250, -200, 149), new Point3D(-200, -200, 149), new Point3D(-200, -100, 149), new Color(255, 255, 255),
                new Material(500, 0.5, 0, 0, 100)));

        geometries.addGeometry(new Triangle(new Point3D(-250, -200, 149), new Point3D(-250, -100, 149), new Point3D(-200, -100, 149), new Color(255, 255, 255),
                new Material(500, 0.5, 0, 0, 100)));

        geometries.addGeometry(new Triangle(new Point3D(100, -200, 149), new Point3D(150, -200, 149), new Point3D(350, 0, 149), new Color(255, 255, 255),
                new Material(500, 0.5, 0, 0, 100)));

        geometries.addGeometry(new Triangle(new Point3D(100, -200, 149), new Point3D(300, 0, 149), new Point3D(350, 0, 149), new Color(255, 255, 255),
                new Material(500, 0.5, 0, 0, 100)));

        geometries.addGeometry(new Triangle(new Point3D(275, -200, 149), new Point3D(325, -200, 149), new Point3D(325, -125, 149), new Color(255, 255, 255),
                new Material(500, 0.5, 0, 0, 100)));

        geometries.addGeometry(new Triangle(new Point3D(275, -200, 149), new Point3D(275, -125, 149), new Point3D(325, -125, 149), new Color(255, 255, 255),
                new Material(500, 0.5, 0, 0, 100)));

        geometries.addGeometry(new Triangle(new Point3D(275, -125, 149), new Point3D(325, -125, 149), new Point3D(275, -75, 149), new Color(255, 255, 255),
                new Material(500, 0.5, 0, 0, 100)));

        geometries.addGeometry(new Triangle(new Point3D(275, -125, 149), new Point3D(275, -75, 149), new Point3D(250, -100, 149), new Color(255, 255, 255),
                new Material(500, 0.5, 0, 0, 100)));

        geometries.addGeometry(new Triangle(new Point3D(100, -100, 149), new Point3D(150, -100, 149), new Point3D(150, 0, 149), new Color(255, 255, 255),
                new Material(500, 0.5, 0, 0, 100)));

        geometries.addGeometry(new Triangle(new Point3D(100, -100, 149), new Point3D(100, 0, 149), new Point3D(150, 0, 149), new Color(255, 255, 255),
                new Material(500, 0.5, 0, 0, 100)));

        geometries.addGeometry(new Triangle(new Point3D(150, -150, 149), new Point3D(100, -100, 149), new Point3D(150, -100, 149), new Color(255, 255, 255),
                new Material(500, 0.5, 0, 0, 100)));

        geometries.addGeometry(new Triangle(new Point3D(150, -150, 149), new Point3D(150, -100, 149), new Point3D(175, -125, 149), new Color(255, 255, 255),
                new Material(500, 0.5, 0, 0, 100)));

        ImageWriter imageWriter = new ImageWriter("BasicAvi", 500, 500, 500, 500);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
        render.printGrid(50);
        render.writeToImage();

    }
}
