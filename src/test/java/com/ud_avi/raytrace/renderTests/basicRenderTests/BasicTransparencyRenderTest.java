package com.ud_avi.raytrace.renderTests.basicRenderTests;

import com.ud_avi.raytrace.elements.Camera;
import com.ud_avi.raytrace.geometries.Geometries;
import com.ud_avi.raytrace.geometries.Sphere;
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

public class BasicTransparencyRenderTest extends BasicRenderTest {

    @Test
    public void basicTransparencyRender() {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+" now running...");
        Camera camera = new Camera(new Point3D(0,0,100), new Vector(0,-1,0), new Vector(0,0,-1));
        Scene scene = new Scene("test");

        SpotLight spot1 = new SpotLight(new Color(196, 249, 220),new Point3D(15,-4,10),
                1,0.001, 0.001, new Vector(-1,-1,-1));

        Sphere innerSphere = new Sphere(new Color(82, 85, 134),new Point3D(0,0,-600),
                250,new Material(4,5, 0, 0, 100));
        Sphere outerSphere = new Sphere(new Color(0, 19, 86),new Point3D(0,0,-600),
                500,new Material(4,3, 0, 0.8, 100));

        Geometries geometries = new Geometries();
        scene.setGeometries(geometries);
        List<LightSource> lightSources = new ArrayList<>();

        scene.addGeometry(innerSphere);
        scene.addGeometry(outerSphere);

        scene.setLightSources(lightSources);
        scene.setAmbientLight(new AmbientLight(new Color(50,50,50),0.2));
        scene.addLightSource(spot1);

        scene.setBackgroundColor(Color.BLACK);
        scene.setDistance(100);
        scene.setCamera(camera);

        ImageWriter imageWriter = new ImageWriter("basicTransparency",500,500,500,500);
        Render render = new Render(imageWriter,scene);

        render.renderImage();
        render.writeToImage();


    }
}
