package com.ud_avi.raytrace.renderTests.basicRenderTests;

import com.ud_avi.raytrace.elements.Camera;
import com.ud_avi.raytrace.geometries.Geometries;
import com.ud_avi.raytrace.geometries.Sphere;
import com.ud_avi.raytrace.geometries.Triangle;
import com.ud_avi.raytrace.lights.AmbientLight;
import com.ud_avi.raytrace.lights.LightSource;
import com.ud_avi.raytrace.lights.PointLight;
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

public class BasicPointLightRenderTest extends BasicRenderTest {
    @Test
    public void basicPointLightRender() {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+" now running...");
        Scene scene = new Scene("Test light");
        scene.setCamera(new Camera(new Point3D(0, 0, 0),new Vector(0, -1, 0), new Vector(0, 0, -1)));
        scene.setDistance(100);
        scene.setAmbientLight(new AmbientLight(new Color(50, 50, 50), 0.5));

        scene.setBackgroundColor(new Color(1,2,3));
        Geometries geometries = new Geometries();
        scene.setGeometries(geometries);
        List<LightSource> lights = new ArrayList<>();
        scene.setLightSources(lights);
        Sphere sphere = new Sphere(new Color(241, 6, 24),new Point3D(0, 0, -250),100,new Material(0.9,0.8, 1, 1, 1000));
        Triangle t1 = new Triangle(new Point3D(150,0,-400), new Point3D(-150,-200,-400), new Point3D(-150,200,-400), new Color(190, 198, 211), new Material(0.9,0.8, 1, 1, 1000));
        geometries.addGeometry(sphere);
        geometries.addGeometry(t1);
        lights.add(new PointLight( Color.WHITE,new Point3D(-100,0,0), 1,0.2,0.001));
        ImageWriter imageWriter = new ImageWriter("basicPointLight", 500, 500, 500, 500);
        Render testRender = new Render(imageWriter,scene);

        testRender.renderImage();
        testRender.writeToImage();


    }
}
