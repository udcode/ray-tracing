package com.ud_avi.raytrace.renderTests.basicRenderTests;

import com.ud_avi.raytrace.elements.Camera;
import com.ud_avi.raytrace.geometries.Geometries;
import com.ud_avi.raytrace.geometries.Rectangle;
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

public class BasicDOFRenderTest extends BasicRenderTest {

    @Test
    public void basicDofRender(){
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+" now running...");
        Scene scene = new Scene("Test mirror");
        scene.setCamera(new Camera(new Point3D(0, -200, -705),new Vector(0, -1, 0), new Vector(0,0,1)));
        //scene.getCamera().rotateX(10);
        scene.getCamera().setDOF(true);
        scene.getCamera().setApertureSize(2);
        scene.getCamera().setFocalLength(80);

        scene.setDistance(700);
        scene.setBackgroundColor(new Color(0,0,0));
        scene.setAmbientLight(new AmbientLight(new Color(50, 50, 50), 0.1));
        Geometries geometries = new Geometries();

        Rectangle rec = new Rectangle(new Point3D(-500, 0, 250),new Point3D(-500, -500, 250) ,new Point3D(500, 0, 250),
                new Color(70, 50, 60),
                new Material(1, 1, 0.1, 0, 120));
        Rectangle rec3 = new Rectangle(new Point3D(-500, 0, 0),new Point3D(-500, 0, 250) ,new Point3D(500, 0, 0),
                new Color(70, 50, 60),
                new Material(1, 1, 0.1, 0, 120));


        Sphere sphereRed = new Sphere( new Color(255, 0, 0),new Point3D(-170, -78, 80),75,
                new Material(3,1,0, 0, 100));
        Sphere sphereGreen = new Sphere( new Color(0, 255, 0),new Point3D(0, -78, 180),75,
                new Material(5,5,0, 0, 100));
        Sphere sphereBlue = new Sphere( new Color(0, 0, 255),new Point3D(170, -78, 280),75,
                new Material(10,3,0, 0, 100));

        geometries.addGeometry(sphereRed);
        geometries.addGeometry(sphereGreen);
        geometries.addGeometry(sphereBlue);

        geometries.addGeometry(rec);
        geometries.addGeometry(rec3);

        scene.setGeometries(geometries);
        List<LightSource> lights = new ArrayList<>();
        lights.add(new SpotLight(new Color(0,0,200),new Point3D(-170,-90,-30),1,0,0.001,new Vector(0,0,1)));
        lights.add(new SpotLight(new Color(0,200,0),new Point3D(170,-90,100),1,0,0.001,new Vector(0,0,1)));
        lights.add(new SpotLight(new Color(200,0,0),new Point3D(0,-90,50),1,0,0.001,new Vector(0,0,1)));

        scene.setLightSources(lights);
        ImageWriter imageWriter = new ImageWriter("basicDof", 500, 500, 500, 500);
        Render testRender = new Render(imageWriter,scene);
        testRender.setMultiThread(true);

        testRender.renderImage();
        testRender.writeToImage();

    }

}
