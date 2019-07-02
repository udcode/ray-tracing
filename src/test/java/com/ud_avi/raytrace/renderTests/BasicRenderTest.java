package com.ud_avi.raytrace.renderTests;

import com.ud_avi.raytrace.elements.*;
import com.ud_avi.raytrace.geometries.*;
import com.ud_avi.raytrace.lights.*;
import com.ud_avi.raytrace.primitives.*;
import com.ud_avi.raytrace.renderer.ImageWriter;
import com.ud_avi.raytrace.renderer.Render;
import com.ud_avi.raytrace.scene.Scene;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BasicRenderTest {
    protected static Logger log = Logger.getLogger(BasicRenderTest.class.getName());
    //@Test
    public void testSphere() {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+" now running...");
        Sphere sphere = new Sphere(new Color(16, 71, 160),  new Point3D(-30,-20,150),60,new Material(0.7,0.8, 1, 1, 100));
        Sphere noose = new Sphere(new Color(86, 7, 32),  new Point3D(-30,35,100),10,new Material(0.7,0.8, 1, 1, 100));
        Sphere rightEye = new Sphere(new Color(86, 7, 32),  new Point3D(-17,20,110),5,new Material(0.7,0.8, 1, 1, 100));
        Sphere leftEye = new Sphere(new Color(86, 7, 32),  new Point3D(-40,18,108),5,new Material(0.7,0.8, 1, 1, 100));

        Triangle t2 = new Triangle(new Point3D(0, -80, 210),new Point3D(-70, 70, 210),new Point3D(-100, 70, 210), new Color(168, 18, 65),new Material(0.7,0.8, 1, 1, 100));
        Camera camera = new Camera(Point3D.ZERO_P3D, new Vector(0,-1,0), new Vector(0,0,1));
        Scene scene = new Scene("test2");
        Triangle t3 = new Triangle(new Point3D(-10, -80, 210),new Point3D(-30, 70, 210),new Point3D(0, 70, 210), new Color(168, 18, 65),new Material(0.7,0.8, 1, 1, 100));

        SpotLight spot = new SpotLight(Color.WHITE,new Point3D(50,0,0),  1,0, 0, new Vector(-50,0,80));

        Geometries geometries = new Geometries();
        scene.setGeometries(geometries);
        List<LightSource> lightSources = new ArrayList<>();
        scene.addGeometry(sphere);
        scene.addGeometry(rightEye);
        scene.addGeometry(leftEye);
        scene.addGeometry(noose);
        Triangle t1 = new Triangle(new Point3D(150,0,370), new Point3D(-350,-700,370), new Point3D(-350,700,370), new Color(170, 158, 111), new Material(0.7,0.8, 1, 1, 20));
        scene.addGeometry(t1);
        scene.addGeometry(t3);
        scene.addGeometry(t2);
        scene.setLightSources(lightSources);
        scene.setAmbientLight(new AmbientLight(new Color(50,50,50),0.5));
        scene.addLightSource(spot);
        scene.setBackgroundColor(Color.BLACK);
        scene.setDistance(100);
        scene.setCamera(camera);

        ImageWriter imageWriter = new ImageWriter("sphere1",500,500,500,500);
        Render render = new Render(imageWriter,scene);

        render.renderImage();
        render.writeToImage();

    }
   // @Test
    public void testPlaneWithTowLights() {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+" now running...");
        Camera camera = new Camera(Point3D.ZERO_P3D, new Vector(0,-1,0), new Vector(0,0,-1));
        Scene scene = new Scene("test");

        SpotLight spot1 = new SpotLight(new Color(221,190,117),Point3D.ZERO_P3D,  1,0, 0, new Vector(-1,-1,-1));
        SpotLight spot2 = new SpotLight(new Color(200,221,117),Point3D.ZERO_P3D,  1,0, 0, new Vector(1,1,1));

        Geometries geometries = new Geometries();
        scene.setGeometries(geometries);
        List<LightSource> lightSources = new ArrayList<>();
        scene.setLightSources(lightSources);
        scene.setAmbientLight(new AmbientLight(new Color(50,50,50),0.5));
        scene.addLightSource(spot1);
        scene.addLightSource(spot2);

        scene.setBackgroundColor(Color.BLACK);
        scene.setDistance(100);
        scene.setCamera(camera);

        ImageWriter imageWriter = new ImageWriter("plane_with_two_lights",1000,1000,1000,1000);
        Render render = new Render(imageWriter,scene);

        render.renderImage();
        render.writeToImage();

    }
   // @Test
    public void testRectangle() {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+" now running...");
        Rectangle plane = new Rectangle(new Point3D(-25,-25,-120),new Point3D(-25,25,-120),
                new Point3D(25,-25,-120),
                new Color(150,150,150), new Material(1,1, 0, 0, 500));
        Camera camera = new Camera(Point3D.ZERO_P3D, new Vector(0,1,0), new Vector(0,0,-1));
        Scene scene = new Scene("test");

        SpotLight spot1 = new SpotLight(new Color(221,190,117),Point3D.ZERO_P3D,  1,0, 0, new Vector(-1,-1,-1));
        SpotLight spot2 = new SpotLight(new Color(200,221,117),Point3D.ZERO_P3D,  1,0, 0, new Vector(1,1,1));

        Geometries geometries = new Geometries();
        scene.setGeometries(geometries);
        List<LightSource> lightSources = new ArrayList<>();
        scene.addGeometry(plane);
        scene.setLightSources(lightSources);
        scene.setAmbientLight(new AmbientLight(new Color(50,50,50),0.5));
        scene.addLightSource(spot1);
        scene.addLightSource(spot2);

        scene.setBackgroundColor(Color.BLACK);
        scene.setDistance(100);
        scene.setCamera(camera);

        ImageWriter imageWriter = new ImageWriter("rectangle",500,500,500,500);
        Render render = new Render(imageWriter,scene);

        render.renderImage();
        render.writeToImage();

    }
   // @Test
    public void testDirectionLight() {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+" now running...");
        //Camera camera = new Camera(new Point3D(0,-6,0), new Vector(8,-80,-16), new Vector(4,2,-8));
        Camera camera = new Camera(new Point3D(0,-6,0), new Vector(0,-1,0), new Vector(0,0,-1));
        camera.rotateX(12);
        camera.rotateY(-29);

        Scene scene = new Scene("test");
        Point3D F = new Point3D(4,0,-4);
        Point3D G = new Point3D(4,0,-8);
        Point3D K = new Point3D(8,0,-8);
        Point3D J = new Point3D(8,0,-4);
        Point3D M = new Point3D(4,-3.5,-4);
        Point3D N = new Point3D(8,-3.5,-4);
        Point3D O = new Point3D(8,-3.5,-8);
        Point3D L = new Point3D(4,-3.5,-8);
        Material simpleMaterial = new Material(1,1, 0, 0, 500);

        Color qColor = new Color(170,228,48);
        Rectangle rec1 = new Rectangle(J,F,K, qColor, simpleMaterial);
        Rectangle rec2 = new Rectangle(N,M,O, qColor, simpleMaterial);
        Rectangle rec3 = new Rectangle(M,F,L, qColor, simpleMaterial);
        Rectangle rec4 = new Rectangle(L,G,O, qColor, simpleMaterial);
        Rectangle rec5 = new Rectangle(M,F,N, qColor, simpleMaterial);
        Rectangle rec6 = new Rectangle(O,K,N, qColor, simpleMaterial);

        Sphere sphere1 = new Sphere(new Color(66,134,44),new Point3D(6,-2,-12),2,simpleMaterial);
        DirectionalLight directionalLight = new DirectionalLight(new Color(255,255,172),new Vector(-8,2,-8));

        Geometries geometries = new Geometries();
        geometries.addGeometry(rec1);
        geometries.addGeometry(rec2);
        geometries.addGeometry(rec3);
        geometries.addGeometry(rec4);
        geometries.addGeometry(rec5);
        geometries.addGeometry(rec6);
        geometries.addGeometry(sphere1);
        scene.setGeometries(geometries);

        List<LightSource> lightSources = new ArrayList<>();
        lightSources.add(directionalLight);

        scene.setLightSources(lightSources);
        scene.setAmbientLight(new AmbientLight(new Color(50,50,50),0.1));


        scene.setBackgroundColor(Color.BLACK);
        scene.setDistance(100);
        scene.setCamera(camera);

        ImageWriter imageWriter = new ImageWriter("directionLight",500,500,500,500);
        Render render = new Render(imageWriter,scene);

        render.renderImage();
        render.writeToImage();

    }



    //@Test
    public void reflectTest() {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+" now running...");
        Scene scene = new Scene("Test reflect");
        scene.setCamera(new Camera(new Point3D(0, 0, 0),new Vector(0, -1, 0), new Vector(0, 0, 1)));
        scene.setDistance(100);
        scene.setBackgroundColor(new Color(0,0,0));
        scene.setAmbientLight(new AmbientLight(new Color(50, 50, 50), 0.5));
        Geometries geometries = new Geometries();
        Sphere sphere = new Sphere( new Color(0, 19, 86),new Point3D(0, 0, 162),60,new Material(500,700,0.5, 0.8, 18));
        Sphere sphere1 = new Sphere( new Color(241, 110, 151),new Point3D( 0,0, 162),40, new Material(500,700,0.8, 0.2, 18));
        geometries.addGeometry(sphere);
        geometries.addGeometry(sphere1);
        scene.setGeometries(geometries);
        List<LightSource> lights = new ArrayList<>();
        lights.add(new SpotLight( new Color(241, 60, 151),new Point3D(15,15,-10) ,1, 10,0.1,new Vector(-35,-35,100)));
        scene.setLightSources(lights);
        ImageWriter imageWriter = new ImageWriter("reflect_test", 500, 500, 500, 500);
        Render testRender = new Render( imageWriter,scene);

        testRender.renderImage();
        testRender.writeToImage();

    }

    //@Test
    public void tubeWithLightTest(){
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+" now running...");
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, -1)));
        scene.setDistance(50);
        scene.setBackgroundColor(new Color(0, 0, 0));
        scene.setAmbientLight(new AmbientLight(new Color(50,50,50),0.5));
        Sphere sphere = new Sphere( new Color(0, 19, 86),new Point3D(100, 0, -100),60,new Material(1,1,0.5, 0.9, 18));

        SpotLight spot = new SpotLight(new Color(221,190,117),Point3D.ZERO_P3D,  1,0, 0, new Vector(-20,-1,-1));
        List<LightSource> lightSources = new ArrayList<>();
        lightSources.add(spot);
        Geometries geometries = new Geometries();
        geometries.addGeometry(sphere);
        scene.setGeometries(geometries);
        scene.setLightSources(lightSources);
        geometries.addGeometry(new Tube(new Color(98,115,120),
                new Ray(new Vector(-1,-1,0), new Point3D(0,0,-150)),60,new Material(0.2,1, 0.5, 0, 500)));

        ImageWriter imageWriter = new ImageWriter("tubeWithLight", 500, 500, 500, 500);
        Render render = new Render(imageWriter, scene);


        render.renderImage();
        render.writeToImage();

    }




    //@Test
    public void mirrorsTest(){
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+" now running...");
        Scene scene = new Scene("Test mirror");
        scene.setCamera(new Camera(new Point3D(0, -78, -505),new Vector(0, -1, 0), new Vector(0,0,1)));
        //scene.getCamera().rotateX(10);
        scene.setDistance(500);
        scene.setBackgroundColor(new Color(0,0,0));
        scene.setAmbientLight(new AmbientLight(new Color(50, 50, 50), 0.1));
        Geometries geometries = new Geometries();


        Sphere sphereRed = new Sphere( new Color(255, 0, 0),new Point3D(-170, -78, 80),75,
                new Material(3,1,0, 0, 100));
        Sphere sphereGreen = new Sphere( new Color(0, 255, 0),new Point3D(0, -78, 180),75,
                new Material(5,5,0, 0, 100));
        Sphere sphereBlue = new Sphere( new Color(0, 0, 255),new Point3D(170, -78, 280),75,
                new Material(10,3,0, 0, 100));

        geometries.addGeometry(sphereRed);
        geometries.addGeometry(sphereGreen);
        geometries.addGeometry(sphereBlue);

        //geometries.addGeometry(p2);
        //geometries.addGeometry(rec3);

        scene.setGeometries(geometries);
        List<LightSource> lights = new ArrayList<>();
        lights.add(new SpotLight(new Color(0,0,200),new Point3D(-170,-90,-30),1,0,0.001,new Vector(0,0,1)));
        lights.add(new SpotLight(new Color(0,200,0),new Point3D(170,-90,100),1,0,0.001,new Vector(0,0,1)));
        lights.add(new SpotLight(new Color(200,0,0),new Point3D(0,-90,50),1,0,0.001,new Vector(0,0,1)));

        scene.setLightSources(lights);
        ImageWriter imageWriter = new ImageWriter("mirror_effect1", 500, 500, 500, 500);
        Render testRender = new Render(imageWriter,scene);

        testRender.renderImage();
        testRender.writeToImage();


    }

   // @Test
    public void testTransparent() {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+" now running...");
        Scene scene = new Scene("Test");
        scene.setAmbientLight(new AmbientLight(new Color(50, 50, 50), 0.2));
        scene.setBackgroundColor(new Color(0, 0, 0));
        scene.setCamera(new Camera(Point3D.ZERO_P3D, new Vector(0, -1, 0), new Vector(0, 0, -1)));
        scene.setDistance(200);
        Geometries geometries = new Geometries();
        List<LightSource> lights = new ArrayList<>();

        Triangle t1 = new Triangle(new Point3D(100, -200, -500), new Point3D(-100,0,-500), new Point3D(200,0,-500),
                new Color(10, 10, 62),new Material(2, 1, 0, 0, 100));
        geometries.addGeometry(t1);

        SpotLight spot = new SpotLight(new Color(100, 100, 100), new Point3D(0, 0, -350), 1,0,0, new Vector(1, -1, -1));
        lights.add(spot);
        Rectangle rec = new Rectangle(new Point3D(-550,-550,-250),new Point3D(-550,550,-250),new Point3D(550,-550,-250),
                new Color(250,0,0), new Material(30,10,0,0.8,50));
        geometries.addGeometry(rec);

        scene.setLightSources(lights);
        scene.setGeometries(geometries);
        ImageWriter imageWriter = new ImageWriter("transparent_effect", 500, 500, 500, 500);
        Render render = new Render( imageWriter,scene);

        render.renderImage();
        render.writeToImage();

    }

}
