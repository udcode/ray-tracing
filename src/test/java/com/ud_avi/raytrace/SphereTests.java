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

public class SphereTests {

    @Test
    public void testGetNormal(){
        Vector vecX = new Vector(1,0,0);
        Sphere s = new Sphere(Color.WHITE, Point3D.ZERO_P3D,1,new Material(500,0.5, 1, 1, 100));

        assertEquals("Point (1,0,0)",vecX,s.getNormal(new Point3D(1,0,0)));
        assertEquals("Point (1+e,0,0)",vecX,s.getNormal(new Point3D(1.00000001,0,0)));
        assertEquals("Point (1-e,0,0)",vecX,s.getNormal(new Point3D(0.99999999,0,0)));
     }
    @Test
    public void testFindIntersections() {
        Scene scene = new Scene("test scene");
        Camera camera = new Camera(new Point3D(0,0,0.5),new Vector(0,-1,0),new Vector(0,0,-1));
        scene.setCamera(camera);
        scene.setDistance(4);

		//2 points
        Sphere sphere1 = new Sphere(Color.WHITE, new Point3D(0,0,-7),1,new Material(500,0.5, 1, 1, 100));
        List<Point3D> list1 = getIntersectionsHelper(scene,sphere1);
        assertEquals("2 points",2,list1.size());

        //18 points
        Sphere sphere2 = new Sphere(Color.WHITE, new Point3D(0,0,-3),3,new Material(500,0.5, 1, 1, 100));
        List<Point3D> list2 = getIntersectionsHelper(scene,sphere2);
        assertEquals("1 points",1,list2.size());

        //10 points
        Sphere sphere3 = new Sphere(Color.WHITE, new Point3D(0,0,-3),2.5,new Material(500,0.5, 1, 1, 100));
        List<Point3D> list3 = getIntersectionsHelper(scene,sphere3);
        assertEquals("1 points",1,list3.size());

        //9 points
        Sphere sphere6 = new Sphere(Color.WHITE, new Point3D(0,0,0.5),3,new Material(500,0.5, 1, 1, 100));
        List<Point3D> list6 = getIntersectionsHelper(scene,sphere6);
        assertEquals("0 points",0,list6.size());

        //0 points
        Sphere sphere5 = new Sphere(Color.WHITE, new Point3D(0,0,5),3,new Material(500,0.5, 1, 1, 100));
        List<Point3D> list5 = getIntersectionsHelper(scene,sphere5);
        assertEquals("0 points",0,list5.size());
    }
	@SuppressWarnings("Duplicates")
    private List<Point3D> getIntersectionsHelper(Scene scene, Sphere sphere){
        List<Point3D> list = new ArrayList<>();
        for(int i = 1 ; i < 4 ;++i) {
            for (int j = 1; j < 4; ++j) {
                Ray r = scene.getCamera().constructRay(3, 3, i, j, scene.getDistance(), 9, 9);
                Map<Geometry, List<Point3D>> map = sphere.findIntersections(r);
                if(!map.isEmpty())
                list.addAll(map.get(sphere));
            }
        }
        return list;
    }

    @Test
    public void testGetMax(){
        Sphere sphere = new Sphere(Color.WHITE, new Point3D(0,0,-7),1,new Material(500,0.5, 1, 1, 100));
        Point3D maxPoint = sphere.getMax();
        Point3D expectedPoint = new Point3D(1,1,-6);
        assertEquals(expectedPoint, maxPoint);

    }

    @Test
    public void testGetMin(){
        Sphere sphere = new Sphere(Color.WHITE, new Point3D(0,0,-7),1,new Material(500,0.5, 1, 1, 100));
        Point3D maxPoint = sphere.getMin();
        Point3D expectedPoint = new Point3D(-1,-1,-8);
        assertEquals(expectedPoint, maxPoint);
    }

}
