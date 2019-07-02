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

public class BasicShadowRenderTest extends BasicRenderTest{

    @Test
    public void basicShadowRender() {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+" now running...");
        Scene scene = new Scene("Test shadow");
        scene.setCamera(new Camera(new Point3D(0, 0, -150),new Vector(0, -1, 0), new Vector(0, 0, 1)));
        scene.setDistance(100);
        scene.setBackgroundColor(new Color(0,0,0));
        scene.setAmbientLight(new AmbientLight(new Color(50, 50, 50), 0.5));
        Geometries geometries = new Geometries();
        Sphere sphere = new Sphere( new Color(241, 6, 151),new Point3D(0, 0, 80),60, new Material(0.9,0.8,0, 0, 100));
        Triangle triangle1 = new Triangle(new Point3D(-250,-250,120),new Point3D(-250,250,120),new Point3D(250,-250,120),new Color(0,0,0),new Material(0.9,0.8, 0, 0, 100));
        Triangle triangle2 = new Triangle(new Point3D(250,250,120),new Point3D(-250,250,120),new Point3D(250,-250,120),new Color(0,0,0),new Material(0.9,0.8, 0, 0, 100));
        geometries.addGeometry(sphere);
        geometries.addGeometry(triangle1);
        geometries.addGeometry(triangle2);
        scene.setGeometries(geometries);
        List<LightSource> lights = new ArrayList<>();
        lights.add(new SpotLight(new Color(255,255,255),new Point3D(25,0,-70),1, 0,0,new Vector(-25,0,80) ));
        scene.setLightSources(lights);
        ImageWriter imageWriter = new ImageWriter("basicShadow", 500, 500, 500, 500);
        Render testRender = new Render(imageWriter,scene);

        testRender.renderImage();
        testRender.writeToImage();


    }
}
