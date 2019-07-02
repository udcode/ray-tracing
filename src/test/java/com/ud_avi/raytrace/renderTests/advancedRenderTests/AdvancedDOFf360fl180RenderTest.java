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
 * Benchmark - 388.973 s with all opts. 4 cores
 */
public class AdvancedDOFf360fl180RenderTest extends AdvanceRenderTest {
    @Test
    public void advancedDOFf360fl180Render(){
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+" now running...");
        Scene scene = new Scene("Test");
        scene.setCamera(new Camera(new Point3D(0, -500, -930),new Vector(0, -1, 0), new Vector(0,0,1)));
        //scene.getCamera().rotateX(10);
        scene.getCamera().setApertureSize(0.5);
        scene.getCamera().setFocalLength(180);
        scene.getCamera().setDOF(true);

        scene.setDistance(900);
        scene.setBackgroundColor(new Color(0,0,0));
        scene.setAmbientLight(new AmbientLight(new Color(50, 50, 50), 0.1));
        Geometries geometries = new Geometries();

        Rectangle rec = new Rectangle(new Point3D(-1700, 0, 2000),new Point3D(-1700, -1700, 2000) ,new Point3D(1700, 0, 2000),
                new Color(70, 50, 60),
                new Material(1, 1, 0.1, 0, 20));
        List<LightSource> lights = new ArrayList<>();

        double startX = -500;
        double startZ = -30;
        double radius = 50;
        for (int i=1;i<=7;++i){
            startX += 90 * i;
            startZ += 90 * i;
            Sphere sphere1 = new Sphere( new Color(200, 10, 20),new Point3D(startX , -radius-3, startZ ),radius,
                    new Material(4,1,0, 0, 100));
            Sphere sphere2 = new Sphere( new Color(10, 70, 200),new Point3D(startX  + radius * 2 , -radius-3, startZ),radius,
                    new Material(3,1,0, 0, 100));
            Sphere sphere3 = new Sphere( new Color(152, 70, 200),new Point3D(startX  + radius  , -radius * 2-3, startZ),radius,
                    new Material(4,1,0.1, 0.4, 100));
            Sphere sphere11 = new Sphere( new Color(200, 10, 20),new Point3D(startX , -radius*3-3, startZ ),radius,
                    new Material(3,1,0, 0, 100));
            Sphere sphere22 = new Sphere( new Color(10, 70, 200),new Point3D(startX  + radius * 2 , -radius*3-3, startZ),radius,
                    new Material(2,1,0, 0, 100));

            lights.add(new SpotLight(new Color(195,147,141),new Point3D(startX + radius,-radius*4,startZ-50),1,0,0.001,new Vector(0,0,1)));
            lights.add(new SpotLight(new Color(195,147,141),new Point3D(startX + radius*3,0,startZ-80),1,0,0.001,new Vector(1,0,1)));

            geometries.addGeometry(sphere1);
            geometries.addGeometry(sphere2);
            geometries.addGeometry(sphere11);
            geometries.addGeometry(sphere22);
            geometries.addGeometry(sphere3);

        }

        geometries.addGeometry(rec);
        //geometries.addGeometry(rec3);

        scene.setGeometries(geometries);
        lights.add(new SpotLight(new Color(0,0,200),new Point3D(-100,-50,-70),1,0,0.001,new Vector(0,0,1)));
        lights.add(new SpotLight(new Color(0,200,0),new Point3D(170,-90,100),1,0,0.001,new Vector(0,0,1)));
        lights.add(new SpotLight(new Color(0,200,0),new Point3D(170,-20,-70),1,0,0.001,new Vector(0,0,1)));
        lights.add(new SpotLight(new Color(200,0,0),new Point3D(0,-90,50),1,0,0.001,new Vector(0,0,1)));
        lights.add(new SpotLight(new Color(150,150,45),new Point3D(-50,-350,150),1,0,0.001,new Vector(0,0,1)));
        //lights.add(new DirectionalLight(new Color(200,200,200),new Vector(0,-2,1)));
        scene.setLightSources(lights);
        ImageWriter imageWriter = new ImageWriter("advancedDOFf360fl180", 1000, 1000, 1000, 1000);
        Render testRender = new Render(imageWriter,scene);
        testRender.setMultiThread(true);
        testRender.setLambdaGrid(5);
        testRender.setGridOpt(true);

        testRender.renderImage();
        testRender.writeToImage();

    }
}
