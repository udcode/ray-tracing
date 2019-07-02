package com.ud_avi.raytrace.renderTests.advancedRenderTests;

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
import com.ud_avi.raytrace.renderTests.AdvanceRenderTest;
import com.ud_avi.raytrace.renderer.ImageWriter;
import com.ud_avi.raytrace.renderer.Render;
import com.ud_avi.raytrace.scene.Scene;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
/**
 * Benchmark - 41.632 s with all opts. 4 cores
 */
public class AdvancedDOFBallsRenderTest extends AdvanceRenderTest {
    @Test
    public void advancedDOFBallsRender(){
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+" now running...");
        Scene scene = new Scene("Test");
        scene.setCamera(new Camera(new Point3D(0, -200, -730),new Vector(0, -1, 0), new Vector(0,0,1)));
        //scene.getCamera().rotateX(10);
        scene.getCamera().setApertureSize(1.5);
        scene.getCamera().setNumOfDOFRays(30);
        scene.getCamera().setFocalLength(80);
        scene.getCamera().setDOF(true);

        scene.setDistance(700);
        scene.setBackgroundColor(new Color(0,0,0));
        scene.setAmbientLight(new AmbientLight(new Color(50, 50, 50), 0.1));
        Geometries geometries = new Geometries();

        Rectangle rec = new Rectangle(new Point3D(-500, 0, 500),new Point3D(-500, -500, 500) ,new Point3D(500, 0, 500),
                new Color(70, 50, 60),
                new Material(1, 1, 0.1, 0, 120));


        Sphere sphereRed = new Sphere( new Color(200, 10, 20),new Point3D(-95, -58, 20),45,
                new Material(3,1,0, 0, 100));
        Sphere sphereGreen = new Sphere( new Color(0, 200, 20),new Point3D(0, -78, 180),75,
                new Material(5,5,0, 0, 100));
        Sphere sphereBlue = new Sphere( new Color(10, 20, 200),new Point3D(170, -98, 290),95,
                new Material(10,3,0, 0, 100));
        Sphere sphere1 = new Sphere( new Color(117, 50, 50),new Point3D(170, -38, 50),35,
                new Material(4,1,0.1, 0, 50));
        Sphere sphere2 = new Sphere( new Color(35, 54, 84),new Point3D(100, -58, 100),55,
                new Material(5,2,0, 0.3, 50));
        Sphere sphere3 = new Sphere( new Color(76, 76, 76),new Point3D(-90, -158, 370),155,
                new Material(8,4,0.1, 0, 20));
        Sphere sphere4 = new Sphere( new Color(71, 52, 76),new Point3D(-170, -98, 120),95,
                new Material(4,1,0.2, 0.2, 50));
        Sphere sphere5 = new Sphere( new Color(1, 91, 58),new Point3D(0, -38, 20),35,
                new Material(4,1,0.1, 0, 50));

        geometries.addGeometry(sphereRed);
        geometries.addGeometry(sphereGreen);
        geometries.addGeometry(sphereBlue);
        geometries.addGeometry(sphere1);
        geometries.addGeometry(sphere2);
        geometries.addGeometry(sphere3);
        geometries.addGeometry(sphere4);
        geometries.addGeometry(sphere5);

        geometries.addGeometry(rec);
        //geometries.addGeometry(rec3);

        scene.setGeometries(geometries);
        List<LightSource> lights = new ArrayList<>();
        lights.add(new SpotLight(new Color(0,0,200),new Point3D(-100,-50,-70),1,0,0.001,new Vector(0,0,1)));
        lights.add(new SpotLight(new Color(0,200,0),new Point3D(170,-90,100),1,0,0.001,new Vector(0,0,1)));
        lights.add(new SpotLight(new Color(0,200,0),new Point3D(170,-20,-70),1,0,0.001,new Vector(0,0,1)));
        lights.add(new SpotLight(new Color(200,0,0),new Point3D(0,-90,50),1,0,0.001,new Vector(0,0,1)));
        lights.add(new SpotLight(new Color(150,150,45),new Point3D(-50,-350,150),1,0,0.001,new Vector(0,0,1)));

        scene.setLightSources(lights);
        ImageWriter imageWriter = new ImageWriter("advancedDOFBalls", 500, 500, 500, 500);
        Render testRender = new Render(imageWriter,scene);
        testRender.setMultiThread(true);
        testRender.setGridOpt(true);
        testRender.renderImage();
        testRender.writeToImage();

    }
}
