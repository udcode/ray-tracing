package com.ud_avi.raytrace.renderTests.advancedRenderTests;

import com.ud_avi.raytrace.elements.Camera;
import com.ud_avi.raytrace.geometries.Geometries;
import com.ud_avi.raytrace.lights.AmbientLight;
import com.ud_avi.raytrace.lights.DirectionalLight;
import com.ud_avi.raytrace.lights.LightSource;
import com.ud_avi.raytrace.primitives.Color;
import com.ud_avi.raytrace.primitives.Point3D;
import com.ud_avi.raytrace.primitives.Vector;
import com.ud_avi.raytrace.renderTests.AdvanceRenderTest;
import com.ud_avi.raytrace.renderer.ImageWriter;
import com.ud_avi.raytrace.renderer.Render;
import com.ud_avi.raytrace.scene.Scene;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AdvancedTreeRenderTest extends AdvanceRenderTest {
    @Test
    public void advancedTreeRender(){
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+" now running...");

        Scene scene = new Scene("Test");
        scene.setCamera(new Camera(new Point3D(0, -200, -250),new Vector(0, -1, 0), new Vector(0,0,1)));
        //scene.setCamera(new Camera(new Point3D(0, -300, 60),new Vector(0, 0, -1), new Vector(0,1,0)));

        //scene.getCamera().rotateX(10);
        //scene.getCamera().setApertureSize(0.5);
        //scene.getCamera().setFocalLength(180);

        scene.setDistance(200);
        scene.setBackgroundColor(new Color(0,0,0));
        scene.setAmbientLight(new AmbientLight(new Color(50, 50, 50), 0.1));

        Geometries geometries = new Geometries();
        List<LightSource> lights = new ArrayList<>();
        scene.setGeometries(geometries);
        scene.setLightSources(lights);
        buildTree(new Point3D(0,0,50),60,180,geometries);
        DirectionalLight directionalLight = new DirectionalLight(new Color(180,190,120),new Vector(1,2,-1));
        lights.add(directionalLight);

        ImageWriter imageWriter = new ImageWriter("advancedTree", 500, 500, 500, 500);
        Render testRender = new Render(imageWriter,scene);
        testRender.setMultiThread(true);
        testRender.setGridOpt(true);

        testRender.renderImage();
        testRender.writeToImage();

    }

}
