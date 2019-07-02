/**
 *
 Course: 151055 Software Engineering intro, Lab
 Student1: Avi margali 305645137
 Student2: Yeuda Nuiman 301759692
 Teacher: Dan Zilberstein
 */

package com.ud_avi.raytrace;

import com.ud_avi.raytrace.elements.Camera;
import com.ud_avi.raytrace.primitives.Point3D;
import com.ud_avi.raytrace.primitives.Ray;
import com.ud_avi.raytrace.primitives.Vector;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.fail;


public class CameraTest {
    private Vector vecX= new Vector(1,0,0);
    private Vector vecMY = new Vector(0,-1,0);
    private Vector vecZ = new Vector(0, 0, 1);
    private Vector vecYe= new Vector(0,1.000000001,0);
    private Vector vecYme= new Vector(0,0.99999999,0);
   

    @Test
    public void testConstructor() {

        try {
            new Camera(Point3D.ZERO_P3D,vecX,new Vector(1,2,3));
            fail("not orthogonal");
        }
        catch (Exception e){}

        try {
            new Camera(Point3D.ZERO_P3D,vecX, vecMY);
            new Camera(Point3D.ZERO_P3D,vecX,vecYe);
            new Camera(Point3D.ZERO_P3D,vecX,vecYme);
        } catch (Exception e) {
            fail("Expected not Exception");
        }

        //check the right vector
        Camera testCamera = new Camera(Point3D.ZERO_P3D, vecMY,vecZ);
        assertEquals("the right vector must be (1,0,0)",vecX,testCamera.getRightVector());
    }

    @Test
    public void testConstructRay(){
    	 Camera testCamera = new Camera(Point3D.ZERO_P3D, vecMY,vecZ); //camera on center

        //view 3x3
        Ray ray33_3x3 = testCamera.constructRay(3, 3, 3, 3, 1, 9, 9);
        Ray ray11_3x3 = testCamera.constructRay(3, 3, 1, 1,1, 9, 9);
        Ray ray22_3x3 = testCamera.constructRay(3, 3, 2, 2,1, 9, 9);

        assertEquals("Ray 3,3 3x3",new Ray(new Vector(3,3,1).normalize(),new Point3D(3,3,1)), ray33_3x3);
        assertEquals("Ray 1,1 3x3",new Ray(new Vector(-3,-3,1).normalize(),new Point3D(-3,-3,1)), ray11_3x3);
        assertEquals("Ray 2,2 3x3",new Ray(new Vector(0,0,1),new Point3D(0,0,1)), ray22_3x3);

        //view 4x3
        Ray ray43_4x3 = testCamera.constructRay(4, 3, 4, 3, 2, 12, 9);
        Ray ray13_4x3 = testCamera.constructRay(4, 3, 1, 3,2, 12, 9);
        Ray ray22_4x3 = testCamera.constructRay(4, 3, 2, 2,2, 12, 9);
        Ray ray41_4x3 = testCamera.constructRay(4, 3, 4, 1,2, 12, 9);

        assertEquals("Ray 4,3 4x3",new Ray(new Vector(9/2d,3,2).normalize(),new Point3D(9/2d,3,2)), ray43_4x3);
        assertEquals("Ray 1,3 4x3",new Ray(new Vector(-9/2d,3,2).normalize(),new Point3D(-9/2d,3,2)), ray13_4x3);
        assertEquals("Ray 2,2 4x3",new Ray(new Vector(-3/2d,0,2).normalize(),new Point3D(-3/2d,0,2)), ray22_4x3);
        assertEquals("Ray 4,1 4x3",new Ray(new Vector(9/2d,-3,2).normalize(),new Point3D(9/2d,-3,2)), ray41_4x3);

        //view 4x4
        Ray ray44_4x4 = testCamera.constructRay(4, 4, 4, 4, 4, 12, 12);
        Ray ray13_4x4 = testCamera.constructRay(4, 4, 1, 3,4, 12, 12);
        Ray ray22_4x4 = testCamera.constructRay(4, 4, 2, 2,4, 12, 12);
        Ray ray11_4x4 = testCamera.constructRay(4, 4, 1, 1,4, 12, 12);

        assertEquals("Ray 4,4 4x4",new Ray(new Vector(9/2d,9/2d,4).normalize(),new Point3D(9/2d,9/2d,4)), ray44_4x4);
        assertEquals("Ray 1,3 4x4",new Ray(new Vector(-9/2d,3/2d,4).normalize(),new Point3D(-9/2d,3/2d,4)), ray13_4x4);
        assertEquals("Ray 2,2 4x4",new Ray(new Vector(-3/2d,-3/2d,4).normalize(),new Point3D(-3/2d,-3/2d,4)), ray22_4x4);
        assertEquals("Ray 1,1 4x4",new Ray(new Vector(-9/2d,-9/2d,4).normalize(),new Point3D(-9/2d,-9/2d,4)), ray11_4x4);
    }
}