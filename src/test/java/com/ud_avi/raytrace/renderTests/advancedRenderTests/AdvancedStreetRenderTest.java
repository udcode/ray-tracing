package com.ud_avi.raytrace.renderTests.advancedRenderTests;

import com.ud_avi.raytrace.elements.Camera;
import com.ud_avi.raytrace.geometries.Geometries;
import com.ud_avi.raytrace.geometries.Rectangle;
import com.ud_avi.raytrace.lights.AmbientLight;
import com.ud_avi.raytrace.lights.DirectionalLight;
import com.ud_avi.raytrace.lights.LightSource;
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
 * Benchmark - 111.382 s with all opts. 4 cores
 * without grid - 480.888 s
 */
public class AdvancedStreetRenderTest extends AdvanceRenderTest {
    @Test
    public void advancedStreetRender(){
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+" now running...");

        Scene scene = new Scene("Test");
        Camera camera = new Camera(new Point3D(-270, -280, 710), new Vector(0, -1, 0), new Vector(0, 0, -1));
        camera.rotateY(-20);
        camera.setFocalLength(200);
        camera.setApertureSize(2);
        // camera.setDOF(true);
//        Camera camera = new Camera(new Point3D(-170, -250, -250), new Vector(0, -1, 0), new Vector(0, 0, -1));
//        camera.rotateY(-90);
        // Camera camera = new Camera(new Point3D(0, -500, -250), new Vector(0, 0, -1), new Vector(0, 1, 0));

        scene.setCamera(camera);
        scene.setDistance(550);
        //scene.setBackgroundColor(new Color(135, 206, 235));
        scene.setBackgroundColor(new Color(0, 0, 0));

        scene.setAmbientLight(new AmbientLight(new Color(50,50,50),0.1));

        List<LightSource> lightSources = new ArrayList<>();
        Geometries geometries = new Geometries();

        //ground
        Color wallsColor1 = new Color(143, 168, 162);
        Color wallsColor2 = new Color(169, 178, 71);
        Color wallsColor3 = new Color(163, 153, 196);

        Color middleWallsColor1 = new Color(102, 0, 51);
        Color middleWallsColor2 = new Color(36, 102, 68);

        buildBuilding (new Point3D(50,-2,0),
                2,100,40,122,new Vector(1,-1,-1),geometries,
                new Boolean[]{false,false}, lightSources,middleWallsColor1,wallsColor1);
        buildBuilding (new Point3D(50,-2,-137),
                6,100,40,100,new Vector(1,-1,-1),geometries,
                new Boolean[]{false,true,false,true,false,true}, lightSources,middleWallsColor2,wallsColor2);
        buildBuilding (new Point3D(50,-2,-255),
                2,100,40,100,new Vector(1,-1,-1),geometries,
                new Boolean[]{false,true}, lightSources,middleWallsColor1,wallsColor3);

        buildBuilding (new Point3D(-50,-2,0),
                2,100,40,122,new Vector(-1,-1,-1),geometries,
                new Boolean[]{false,false}, lightSources,middleWallsColor1,wallsColor3);
        buildBuilding (new Point3D(-50,-2,-137),
                3,100,40,100,new Vector(-1,-1,-1),geometries,
                new Boolean[]{false,false,false}, lightSources,middleWallsColor1,wallsColor2);
        buildBuilding (new Point3D(-50,-2,-255),
                4,100,40,100,new Vector(-1,-1,-1),geometries,
                new Boolean[]{false,false,false,false}, lightSources,middleWallsColor2,wallsColor2);
//
        buildRoad(new Point3D(0,0,0),100,355,15,15,4,
                geometries,true, lightSources,false);
        buildRoad(new Point3D(50,0,400),100,355,15,15,4,
                geometries,true, lightSources,true);
        buildCross(new Point3D(-50,0,0),100,16,2,5,geometries);
        buildCross(new Point3D(-50,0,-455),100,16,2,5,geometries);
        buildRoad(new Point3D(0,0,455),100,355,15,15,4,
                geometries,true, lightSources,false);

//
        buildRoad(new Point3D(50,0,-50),100,355,15,15,4,
                geometries,true, lightSources,true);
        Rectangle side1 = new Rectangle(new Point3D(-150,-2,0),new Point3D(-375,-2,0),new Point3D(-150,-2,-100),
                new Color(96, 128, 56),new Material(1,1,0,0,2));
        geometries.addGeometry(side1);
        buildTree(new Point3D(-190,-2,-25), 20, 50,geometries);
        buildTree(new Point3D(-220,-2,-70), 20, 50,geometries);

        buildTree(new Point3D(-250,-2,-25), 20, 50,geometries);
        buildTree(new Point3D(-280,-2,-70), 20, 50,geometries);

        buildTree(new Point3D(-310,-2,-25), 20, 50,geometries);
        buildTree(new Point3D(-340,-2,-70), 20, 50,geometries);

        Rectangle side2 = new Rectangle(new Point3D(150,-2,0),new Point3D(375,-2,0),new Point3D(150,-2,-100),
                new Color(96, 128, 56),new Material(1,1,0,0,2));
        geometries.addGeometry(side2);
        buildTree(new Point3D(190,-2,-25), 20, 50,geometries);
        buildTree(new Point3D(220,-2,-70), 20, 50,geometries);

        buildTree(new Point3D(250,-2,-25), 20, 50,geometries);
        buildTree(new Point3D(280,-2,-70), 20, 50,geometries);

        buildTree(new Point3D(310,-2,-25), 20, 50,geometries);
        buildTree(new Point3D(340,-2,-70), 20, 50,geometries);

        DirectionalLight directionalLight = new DirectionalLight(new Color(180,190,120).scale(0.2),new Vector(1,2,1));
        //lightSources.add(directionalLight);

        scene.setGeometries(geometries);
        scene.setLightSources(lightSources);

        ImageWriter imageWriter = new ImageWriter("advancedStreet", 500, 500, 500, 500);
        Render render = new Render(imageWriter, scene);
        render.setMultiThread(true);
        //render.setGridOpt(true);
        render.renderImage();
        render.writeToImage();

    }

}
