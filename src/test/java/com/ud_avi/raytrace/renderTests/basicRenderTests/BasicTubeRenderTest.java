package com.ud_avi.raytrace.renderTests.basicRenderTests;

import com.ud_avi.raytrace.elements.Camera;
import com.ud_avi.raytrace.geometries.Geometries;
import com.ud_avi.raytrace.geometries.Tube;
import com.ud_avi.raytrace.lights.AmbientLight;
import com.ud_avi.raytrace.primitives.*;
import com.ud_avi.raytrace.renderTests.BasicRenderTest;
import com.ud_avi.raytrace.renderer.ImageWriter;
import com.ud_avi.raytrace.renderer.Render;
import com.ud_avi.raytrace.scene.Scene;
import org.junit.Test;

public class BasicTubeRenderTest extends BasicRenderTest {

    @Test
    public void basicTubeRender(){
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+" now running...");
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, -1)));
        scene.setDistance(200);
        scene.setBackgroundColor(new Color(0, 0, 0));
        scene.setAmbientLight(new AmbientLight(Color.WHITE,1));
        Geometries geometries = new Geometries();
        scene.setGeometries(geometries);
        geometries.addGeometry(new Tube(new Color(0,245,0),
                new Ray(new Vector(1,1,0), new Point3D(0,0,-25)),3,new Material(500,0.5, 0, 0, 100)));

        ImageWriter imageWriter = new ImageWriter("basicTube", 500, 500, 500, 500);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();
    }
}
