/**
 *
	Course: 151055 Software Engineering intro, Lab
	Student1: Avi margali 305645137
    Student2: Yeuda Nuiman 301759692
    Teacher: Dan Zilberstein
 */
package com.ud_avi.raytrace;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.fail;
import com.ud_avi.raytrace.geometries.Geometry;
import com.ud_avi.raytrace.primitives.*;

import com.ud_avi.raytrace.elements.Camera;
import com.ud_avi.raytrace.geometries.Triangle;
import com.ud_avi.raytrace.scene.Scene;


public class TriangleTest {
	@Test
    public void testConstructor() {
		try {
			new Triangle(new Point3D(3, 3, 3), new Point3D(1000000.000000001, 1000000.000000001, 1000000.000000001), new Point3D(1000000.000000001, 1000000.000000001, 1000000.000000001), Color.WHITE, new Material(500, 0.5, 1, 1, 100));
			fail("Expected not a valid triangle exception");
		}catch (Exception e) {}
	}

	@Test
    public void testFindIntersections() {
		Scene scene = new Scene("test scene");
        Camera camera = new Camera(new Point3D(0,0,0),new Vector(0,-1,0),new Vector(0,0,-1));
        scene.setCamera(camera);
        scene.setDistance(3.9);

		// 9 points
		Triangle triang2 = new Triangle(new Point3D(-5,-10,-4), new Point3D(-5,10,-4), new Point3D(5,0,-4), Color.WHITE, new Material(500, 0.5, 1, 1, 100));
		ArrayList<Point3D> list2 = getIntersections(scene, triang2);
		assertEquals(7, list2.size());

		// 7 points
        Triangle triang1 = new Triangle(new Point3D(-5,-5,-4), new Point3D(-5,5,-4), new Point3D(5,0,-4), Color.WHITE, new Material(500, 0.5, 1, 1, 100));
        ArrayList<Point3D> list1 = getIntersections(scene, triang1);
        assertEquals(5, list1.size());

        // 5 points
        Triangle triang3 = new Triangle(new Point3D(5,0,-4), new Point3D(-3,-3,-4), new Point3D(-3,3,-4), Color.WHITE, new Material(500, 0.5, 1, 1, 100));
        ArrayList<Point3D> list3 = getIntersections(scene, triang3);
        assertEquals(2, list3.size());

        // 4 points
        Triangle triangle4 = new Triangle(new Point3D(-1.5,-3,-4), new Point3D(-1.5,3,-4), new Point3D(5,0,-4), Color.WHITE, new Material(500, 0.5, 1, 1, 100));
        ArrayList<Point3D> list4 = getIntersections(scene, triangle4);
        assertEquals(2, list4.size());

        // 1 points
        Triangle triangle5 = new Triangle(new Point3D(-0.5,-0.5,-4), new Point3D(-0.5,0.5,-4), new Point3D(0.5,0,-4), Color.WHITE, new Material(500, 0.5, 1, 1, 100));
        ArrayList<Point3D> list5 = getIntersections(scene, triangle5);
        assertEquals(1, list5.size());

        // 0 points
        Triangle triangle6 = new Triangle(new Point3D(-5,-10,2), new Point3D(-5,10,2), new Point3D(5,0,2), Color.WHITE, new Material(500, 0.5, 1, 1, 100));
        ArrayList<Point3D> list6 = getIntersections(scene, triangle6);
        assertEquals(0, list6.size());
	}

	@SuppressWarnings("Duplicates")
    private ArrayList<Point3D> getIntersections(Scene scene, Triangle triangle) {
		ArrayList<Point3D> list = new ArrayList<>();
        for(int i = 1 ; i < 4 ;++i) {
            for (int j = 1; j < 4; ++j) {
                Ray r = scene.getCamera().constructRay(3, 3, i, j, scene.getDistance(), 9, 9);
                Map<Geometry, List<Point3D>> map = triangle.findIntersections(r);
                if(!map.isEmpty())
                    list.addAll(map.get(triangle));
            }
        }
		return list;
	}
    @Test
    public void testGetMax(){
        Triangle triangle = new Triangle(new Point3D(-5,-10,-4), new Point3D(-5,10,-4), new Point3D(5,0,0), Color.WHITE, new Material(500, 0.5, 1, 1, 100));
        Point3D maxPoint = triangle.getMax();
        Point3D expectedPoint = new Point3D(5,10,0);
        assertEquals(expectedPoint, maxPoint);
    }

    @Test
    public void testGetMin(){
        Triangle triangle = new Triangle(new Point3D(-5,-10,-4), new Point3D(-5,10,-4), new Point3D(5,0,0), Color.WHITE, new Material(500, 0.5, 1, 1, 100));
        Point3D minPoint = triangle.getMin();
        Point3D expectedPoint = new Point3D(-5,-10,-4);
        assertEquals(expectedPoint, minPoint);
    }

}
