/**
 *
 Course: 151055 Software Engineering intro, Lab
 Student1: Avi margali 305645137
 Student2: Yeuda Nuiman 301759692
 Teacher: Dan Zilberstein
 */
package com.ud_avi.raytrace;


import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import com.ud_avi.raytrace.elements.Camera;
import com.ud_avi.raytrace.scene.Scene;
import com.ud_avi.raytrace.geometries.*;
import com.ud_avi.raytrace.primitives.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TubeTests {
    private Ray ray = new Ray(new Vector(0,5,0),new Point3D(0,0,0));
    private Ray rayN = new Ray(new Vector(0,-5,0),new Point3D(0,0,0));
    private Color white = new Color(255, 255, 255);

    @Test
    public void testGetNormal(){
        Vector vecX = new Vector(1,0,0);
        Vector vecMX = new Vector(-1,0,0);

        Tube t = new Tube(white, ray,1,new Material(500,0.5, 1, 1, 100));
        Tube tN = new Tube(white, rayN,1,new Material(500,0.5, 1, 1, 100));

        assertEquals("Point (1,2,0)",vecX,t.getNormal(new Point3D(1,2,0)));
        assertEquals("Point (1+e,2,0)",vecX,t.getNormal(new Point3D(1.00000001,2,0)));
        assertEquals("Point (1-e,2,0)",vecX,t.getNormal(new Point3D(0.99999999,2,0)));

        assertEquals("Point (-1,2,0)",vecMX,t.getNormal(new Point3D(-1,2,0)));
        assertEquals("Point (-1+e,2,0)",vecMX,t.getNormal(new Point3D(-1.00000001,2,0)));
        assertEquals("Point (-1-e,2,0)",vecMX,t.getNormal(new Point3D(-0.99999999,2,0)));
        //negative ray
        assertEquals("Point (1,2,0) negative ray",vecX,tN.getNormal(new Point3D(1,2,0)));
        assertEquals("Point (1+e,2,0) negative ray",vecX,tN.getNormal(new Point3D(1.00000001,2,0)));
        assertEquals("Point (1-e,2,0) negative ray",vecX,tN.getNormal(new Point3D(0.99999999,2,0)));

        assertEquals("Point (-1,2,0)",vecMX,tN.getNormal(new Point3D(-1,2,0)));
        assertEquals("Point (-1+e,2,0) negative ray",vecMX,tN.getNormal(new Point3D(-1.00000001,2,0)));
        assertEquals("Point (-1-e,2,0) negative ray",vecMX,tN.getNormal(new Point3D(-0.99999999,2,0)));

    }
    @Test
	public void testFindIntersections() {
        Scene scene = new Scene("test scene");
        Camera camera = new Camera(new Point3D(0,0,1),new Vector(0,-1,0),new Vector(0,0,-1));
        scene.setCamera(camera);
        scene.setDistance(1);

        Ray r1 = new Ray(new Vector(0,0,1),new Point3D(0,0,0));
        Tube t1 = new Tube(white, r1,3,new Material(500,0.5, 1, 1, 100));
        List<Point3D> list1 = getIntersectionsHelper(scene,t1);
        assertEquals("0 points",0,list1.size());

        Ray r2 = new Ray(new Vector(0,1,0),new Point3D(0,0,0));
        Tube t2 = new Tube(white, r2,3,new Material(500,0.5, 1, 1, 100));
        List<Point3D> list2 = getIntersectionsHelper(scene,t2);
        assertEquals("3 points",3,list2.size());

        Ray r3 = new Ray(new Vector(1,1,0), new Point3D(4,-10,0));
        Tube t3 = new Tube(white,r3,3,new Material(500,0.5, 1, 1, 100));
        List<Point3D> list3 = getIntersectionsHelper(scene,t3);
        assertEquals("2 points",2,list3.size());

        Ray r4 = new Ray(new Vector(1,0,0), new Point3D(0,0,5));
        Tube t4 = new Tube(white,r4,2,new Material(500,0.5, 1, 1, 100));
        List<Point3D> list4 = getIntersectionsHelper(scene,t4);
        assertEquals("0 points",0,list4.size());
	}
    @SuppressWarnings("Duplicates")
    private List<Point3D> getIntersectionsHelper(Scene scene, Tube tube){
        List<Point3D> list = new ArrayList<>();
        for(int i = 1 ; i < 4 ;++i) {
            for (int j = 1; j < 4; ++j) {
                Ray r = scene.getCamera().constructRay(3, 3, i, j, scene.getDistance(), 9, 9);
                Map<Geometry, List<Point3D>> map = tube.findIntersections(r);
                if(!map.isEmpty())
                    list.addAll(map.get(tube));
            }
        }
        return list;
    }
    @Test
    public void testGetMax(){

    }

    @Test
    public void testGetMin(){

    }

}
