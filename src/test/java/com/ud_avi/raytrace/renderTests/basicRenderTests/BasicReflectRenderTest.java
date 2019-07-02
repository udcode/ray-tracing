package com.ud_avi.raytrace.renderTests.basicRenderTests;

import com.ud_avi.raytrace.elements.Camera;
import com.ud_avi.raytrace.geometries.Geometries;
import com.ud_avi.raytrace.geometries.Sphere;
import com.ud_avi.raytrace.geometries.Triangle;
import com.ud_avi.raytrace.lights.AmbientLight;
import com.ud_avi.raytrace.lights.LightSource;
import com.ud_avi.raytrace.lights.SpotLight;
import com.ud_avi.raytrace.primitives.Color;
import com.ud_avi.raytrace.primitives.Material;
import com.ud_avi.raytrace.primitives.Point3D;
import com.ud_avi.raytrace.primitives.Vector;
import com.ud_avi.raytrace.renderTests.BasicRenderTest;
import com.ud_avi.raytrace.renderer.ImageWriter;
import com.ud_avi.raytrace.renderer.Render;
import com.ud_avi.raytrace.scene.Scene;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BasicReflectRenderTest extends BasicRenderTest {

    @Test
    public void basicReflectRender() {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+" now running...");
        Scene scene = new Scene("Test reflect1");
        scene.setCamera(new Camera(new Point3D(0,0,-100),new Vector(0, -1, 0), new Vector(0, 0, 1)));
        scene.setDistance(100);
        scene.setBackgroundColor(new Color(0, 0, 0));
        scene.setAmbientLight(new AmbientLight(new Color(50, 50, 50), 0.5));
        Geometries geometries = new Geometries();
        Sphere sphere = new Sphere(new Color(0, 19, 86),new Point3D(80, 80, 80), 60,  new Material(400, 500, 0, 1, 18));
        Sphere sphere1 = new Sphere(new Color(247, 59, 165),new Point3D(80, 80, 80), 40,
                new Material(300, 700, 0.8, 0.2, 18));
        Triangle triangle = new Triangle(new Point3D(500, 500, 200), new Point3D(500, -500, 200),
                new Point3D(-500, 500, 200), new Color(0, 0, 0), new Material(1000, 500, 1, 1, 10));
        Triangle triangle1 = new Triangle(new Point3D(-60, -60, 30), new Point3D(500, -500, 200),
                new Point3D(-500, 500, 200), new Color(0, 0, 0), new Material(1000, 500, 1, 10, 1));
        geometries.addGeometry(sphere);
        geometries.addGeometry(sphere1);
        geometries.addGeometry(triangle);
        geometries.addGeometry(triangle1);
        scene.setGeometries(geometries);
        List<LightSource> lights = new ArrayList<>();
        lights.add(new SpotLight(new Color(241, 60, 151), new Point3D(15, 15, -110), 1,10, 0,new Vector(-35, -35, 100)));
        scene.setLightSources(lights);
        ImageWriter imageWriter = new ImageWriter("basicReflect", 500, 500, 500, 500);
        Render testRender = new Render(imageWriter,scene);

        testRender.renderImage();
        testRender.writeToImage();


    }
}
