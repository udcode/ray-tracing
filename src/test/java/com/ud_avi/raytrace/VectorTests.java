/**
 *
	Course: 151055 Software Engineering intro, Lab
	Student1: Avi margali 305645137
    Student2: Yeuda Nuiman 301759692
    Teacher: Dan Zilberstein
 */
package com.ud_avi.raytrace;

import static java.lang.Math.sqrt;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.ud_avi.raytrace.primitives.*;

public class VectorTests {
	private Vector vector1 = new Vector(1,1,1);
	private Vector vector1e = new Vector(1.00000000000000001,1.00000000000000001,1.00000000000000001);
	private Vector vector1me = new Vector(0.9999999999999999,0.9999999999999999,0.9999999999999999);
	private Vector vector2 = new Vector(2,2,2);
	private Vector vector2md = new Vector(-2,-2,-2);
	private Vector vectorBig = new Vector(100000,100000,100000);
	private Vector vectorBig_e = new Vector(100000.0000000000001,100000.000000000001,100000.0000000000001);
	private Vector vectorBig_me = new Vector(99999.9999999999999,99999.9999999999999,99999.9999999999999);

	private Vector vecX = new Vector(1,0,0);
	private Vector vecY = new Vector(0,1,0);
	private Vector vecZ = new Vector(0,0,1);
	private Vector vecMX = new Vector(-1,0,0);
	private Vector vecMY = new Vector(0,-1,0);
	private Vector vecXY = new Vector(1,1,0);
	private Vector vecMXY = new Vector(-1,1,0);
	private Vector vecXMY = new Vector(1,-1,0);
	private Vector vecXYZ = vector1;
	private Vector vecXHY = new Vector(1,0.5,0);
	private Vector vecMXHY = new Vector(-1,0.5,0);
	private Vector vecMXMHY = new Vector(-1,-0.5,0);
	private Vector vecXMHY = new Vector(1,-0.5,0);
	private Vector vecMXMY = new Vector(-1,-1,0);
	private Vector vecXYHZ = new Vector(1,1,0.5);

	private Coordinate one = new Coordinate(1);

	@Test
	public void testConstructor(){
		//test vector 0
		try {
			new Vector(0,0,0);
			fail("Expected IllegalArgumentException");
		}catch (Exception e){}
		try {
			new Vector(Point3D.ZERO_P3D);
			fail("Expected IllegalArgumentException");
		}catch (Exception e){}

		//test vectors 0+e
		try {
			new Vector(0.0000000000001,0.0000000000001,0.0000000000001);
			fail("Expected IllegalArgumentException");
		}catch (Exception e){}
		try {
			new Vector(new Point3D(0.0000000000001,0.0000000000001,0.0000000000001));
			fail("Expected IllegalArgumentException");
		}catch (Exception e){}
		try {
			new Vector(-0.0000000000001,-0.0000000000001,-0.0000000000001);
			fail("Expected IllegalArgumentException");
		}catch (Exception e){}
		try {
			new Vector(new Point3D(-0.0000000000001,-0.0000000000001,-0.0000000000001));
			fail("Expected IllegalArgumentException");
		}catch (Exception e){}

		try {
			new Vector(0.001,0.001,0.001);
			new Vector(-0.001,-0.001,-0.001);
		} catch (Exception e) {
			fail("Expected not Exception");
		}

		//test vectors real value
		Vector vector1_1d = new Vector(1.001,1.001,1.001);
		Vector vector1_1md = new Vector(0.99,0.99,0.99);
		Vector vector1_2 = new Vector(new Point3D(1,1,1));

		assertEquals("Vector 1",vector1,vector1_2);
		assertEquals("Vector 1.00000001",vector1,vector1e);
		assertEquals("Vector 0.9999999",vector1,vector1me);
		assertNotEquals("Vector 1.001",vector1,vector1_1d);
		assertNotEquals("Vector Point(1)",vector1,vector1_1md);
		assertNotEquals("Vector 2",vector2,vector1);
		assertNotEquals("Vector -2",vector2,vector2md);
	}

	@Test
	public void testAdd(){
		Vector expectedResult = new Vector(100001,100001,100001);

		Vector vec2X3HY = new Vector(2,(double) 3/2,0);
		Vector vecM2X3HY = new Vector(-2,(double)3/2,0);
		Vector vecM2XM3HY = new Vector(-2,(double)-3/2,0);
		Vector vec2XM3HY = new Vector(2,(double)-3/2,0);
		Vector vec2X2Y3HZ = new Vector(2,2,(double)3/2);
		Vector vecXZ = new Vector(1,0,1);

		assertEquals("Add 1+veryBig",vector1.add(vectorBig),expectedResult);
		assertEquals("Add 1+e+veryBig",vector1e.add(vectorBig),expectedResult);
		assertEquals("Add 1-e+veryBig",vector1me.add(vectorBig),expectedResult);

		assertEquals("Add veryBig+1",vectorBig.add(vector1),expectedResult);
		assertEquals("Add veryBig+e+1",vectorBig_e.add(vector1),expectedResult);
		assertEquals("Add veryBig-e+1",vectorBig_me.add(vector1),expectedResult);

		assertNotEquals("Add 2+veryBig",vector2.add(vectorBig),expectedResult);
		assertNotEquals("Add -2+veryBig",vector2md.add(vectorBig),expectedResult);

		//black box
		assertEquals("Add: (0,1,0)+(1,0,0)",vecXY,vecY.add(vecX));
		assertEquals("Add: (0,1,0)+(-1,0,0)",vecMXY,vecY.add(vecMX));
		assertEquals("Add: (-1,0,0)+(0,-1,0)",vecMXMY,vecMX.add(vecMY));
		assertEquals("Add: (0,-1,0)+(1,0,0)",vecXMY,vecMY.add(vecX));
		assertEquals("Add: (1,1,0)+(1,0.5,0)",vec2X3HY,vecXY.add(vecXHY));
		assertEquals("Add: (-1,1,0)+(-1,0.5,0)",vecM2X3HY,vecMXY.add(vecMXHY));
		assertEquals("Add: (-1,-1,0)+(-1,-0.5,0)",vecM2XM3HY,vecMXMY.add(vecMXMHY));
		assertEquals("Add: (1,-1,0)+(1,-0.5,0)",vec2XM3HY,vecXMY.add(vecXMHY));
		assertEquals("Add: (1,0,0)+(0,0,1)",vecXZ,vecX.add(vecZ));
		assertEquals("Add: (1,1,1)+(1,1,0.5)",vec2X2Y3HZ,vecXYZ.add(vecXYHZ));


		//add the negative vector
		try {
			vector2.add(vector2md);
			fail("Expected IllegalArgumentException");
		}catch (Exception e){}

	}
	@Test
	public void testSubtract(){

		Vector expectedResult1MinusB = new Vector(-99999,-99999,-99999);
		Vector expectedResultBMinus1 = new Vector(99999,99999,99999);

		Vector vecHY = new Vector(0,0.5,0);
		Vector vecMHY = new Vector(0,-0.5,0);
		Vector vecXMZ = new Vector(1,0,-1);
		Vector vecHZ = new Vector(0,0,0.5);

		assertEquals("Subtract 1-veryBig",vector1.subtract(vectorBig),expectedResult1MinusB);
		assertEquals("Subtract 1+e-veryBig",vector1e.subtract(vectorBig),expectedResult1MinusB);
		assertEquals("Subtract 1-e-veryBig",vector1me.subtract(vectorBig),expectedResult1MinusB);

		assertEquals("Subtract veryBig-1",vectorBig.subtract(vector1),expectedResultBMinus1);
		assertEquals("Subtract veryBig+e-1",vectorBig_e.subtract(vector1),expectedResultBMinus1);
		assertEquals("Subtract veryBig-e-1",vectorBig_me.subtract(vector1),expectedResultBMinus1);

		assertNotEquals("Subtract 2-veryBig",vector2.subtract(vectorBig),expectedResult1MinusB);
		assertNotEquals("Subtract -2-veryBig",vector2md.subtract(vectorBig),expectedResult1MinusB);

		//black box
		assertEquals("Subtract: (0,1,0)-(1,0,0)",vecMXY,vecY.subtract(vecX));
		assertEquals("Subtract: (0,1,0)-(-1,0,0)",vecXY,vecY.subtract(vecMX));
		assertEquals("Subtract: (-1,0,0)-(0,-1,0)",vecMXY,vecMX.subtract(vecMY));
		assertEquals("Subtract: (0,-1,0)-(1,0,0)",vecMXMY,vecMY.subtract(vecX));
		assertEquals("Subtract: (1,1,0)-(1,0.5,0)",vecHY,vecXY.subtract(vecXHY));
		assertEquals("Subtract: (-1,1,0)-(-1,0.5,0)",vecHY,vecMXY.subtract(vecMXHY));
		assertEquals("Subtract: (-1,-1,0)-(-1,-0.5,0)",vecMHY,vecMXMY.subtract(vecMXMHY));
		assertEquals("Subtract: (1,-1,0)-(1,-0.5,0)",vecMHY,vecXMY.subtract(vecXMHY));
		assertEquals("Subtract: (1,0,0)-(0,0,1)",vecXMZ,vecX.subtract(vecZ));
		assertEquals("Subtract: (1,1,1)-(1,1,0.5)",vecHZ,vecXYZ.subtract(vecXYHZ));


		try {
			vector2.subtract(vector2);
			fail("Expected IllegalArgumentException");
		}catch (Exception e){}
	}
	@Test
	public void testScaling(){
		assertEquals("Scale * 1",vector1.scale(1),vector1);
		assertEquals("Scale * 1.0000001",vector1.scale(1.0000000000001),vector1);
		assertEquals("Scale * big",vector1.scale(100000),vectorBig);

		try {
			vector2.scale(0);
			fail("Expected IllegalArgumentException");
		}catch (Exception e){}
		try {
			vector2.scale(0.0000000000001);
			fail("Expected IllegalArgumentException");
		}catch (Exception e){}
	}

	@Test
	public void testDotProduct(){

		Vector vec2Y = new Vector(0,2,0);
		Vector vecXYe = new Vector(1,1.0000001,0.000001);
		Vector vecPos1 = new Vector(5,3,0);
		Vector vecPos2 = new Vector(2,6,4);
		Vector vecNeg = new Vector(-12,6,4);

		Coordinate mone = new Coordinate(-1);
		Coordinate two = new Coordinate(2);
		Coordinate threeOverTwo = new Coordinate((double)3/2);
		Coordinate fiveOverTwo = new Coordinate((double)5/2);

		//black box
		assertEquals("DotProduct: (0,1,0)(1,0,0)",Coordinate.ZERO,vecY.dotProduct(vecX));
		assertEquals("DotProduct: (0,1,0)(-1,0,0)",Coordinate.ZERO,vecY.dotProduct(vecMX));
		assertEquals("DotProduct: (-1,0,0)(0,-1,0)",Coordinate.ZERO,vecMX.dotProduct(vecMY));
		assertEquals("DotProduct: (0,-1,0)(1,0,0)",Coordinate.ZERO,vecMY.dotProduct(vecX));
		assertEquals("DotProduct: (1,1,0)(1,0.5,0)",threeOverTwo,vecXY.dotProduct(vecXHY));
		assertEquals("DotProduct: (-1,1,0)(-1,0.5,0)",threeOverTwo,vecMXY.dotProduct(vecMXHY));
		assertEquals("DotProduct: (-1,-1,0)(-1,-0.5,0)",threeOverTwo,vecMXMY.dotProduct(vecMXMHY));
		assertEquals("DotProduct: (1,-1,0)(1,-0.5,0)",threeOverTwo,vecXMY.dotProduct(vecXMHY));
		assertEquals("DotProduct: (1,0,0)(0,0,1)",Coordinate.ZERO,vecX.dotProduct(vecZ));
		assertEquals("DotProduct: (1,1,1)(1,1,0.5)",fiveOverTwo,vecXYZ.dotProduct(vecXYHZ));

		assertEquals("DotProduct 45",one,vecX.dotProduct(vecXY));
		assertEquals("DotProduct 45-e",one,vecX.dotProduct(vecXYe));

		assertEquals("DotProduct 135",mone,vecX.dotProduct(vecMXY));
		assertEquals("DotProduct 225/-135",mone,vecX.dotProduct(vecMXMY));
		assertEquals("DotProduct 315/-45",one,vecX.dotProduct(vecXMY));

		assertTrue("Positive cos",vecPos1.dotProduct(vecPos2) > 0);
		assertTrue("Negative cos",vecNeg.dotProduct(vecPos1) < 0);

		assertEquals("DotProduct same direction",two,vec2Y.dotProduct(vecY));
		assertEquals("DotProduct opp direction",mone,vecMY.dotProduct(vecY));
	}

	@Test
	public void testLength(){
		Coordinate sqrt3 = new Coordinate(sqrt(3));
		double sqrt14 = sqrt(14);
		Vector size1 = new Vector(1 /sqrt14,2/sqrt14,3/sqrt14);

		assertEquals("Length sqrt3",sqrt3,vector1.length());
		assertEquals("Length sqrt3+e",sqrt3,vector1e.length());
		assertEquals("Length sqrt3-e",sqrt3,vector1me.length());
		assertNotEquals("Length not sqrt3",sqrt3,vector2.length());

		assertEquals("Length 1*",one,size1.length());

	}

	@Test
	public void testNormalize(){
		Vector vecX = new Vector(1,0,0);
		double sqrt14 = sqrt(14);
		Vector size1 = new Vector(1 *sqrt14,2*sqrt14,3*sqrt14);

		assertEquals("Length 1",one,vector1.normalize().length());
		assertEquals("Length 1",one,vector2.normalize().length());
		assertEquals("Length 1",one,vector2md.normalize().length());
		assertEquals("Length 1",one,vectorBig.normalize().length());
		assertEquals("Length 1",one,vecX.normalize().length());
		assertEquals("Length 1",one,size1.normalize().length());

	}

	@Test
	public void testCrossProduct(){
		Vector vecX = new Vector(1,0,0);
		Vector vecXe = new Vector(1,0.0000001,0);
		Vector vecXme = new Vector(1,-0.0000001,0);
		Vector vecY = new Vector(0,1,0);
		Vector vecYe = new Vector(0.000001,1,0);
		Vector vecYme = new Vector(-0.000001,1,0);
		Vector vecZ = new Vector(0,0,1);

		try {
			vector1.crossProduct(vector1);//self cross product = (0,0,0)
			fail("Expected IllegalArgumentException");
		}catch (Exception e){}
		try {
			vector1.crossProduct(vector2);//self cross product = (0,0,0)
			fail("Expected IllegalArgumentException");
		}catch (Exception e){}

		assertEquals("X x Y = Z",vecZ,vecX.crossProduct(vecY));
		assertEquals("X+e x Y = Z",vecZ,vecXe.crossProduct(vecY));
		assertEquals("X+e x Y+e = Z",vecZ,vecXe.crossProduct(vecYe));
		assertEquals("X-e x Y = Z",vecZ,vecXme.crossProduct(vecY));
		assertEquals("X-e x Y-e = Z",vecZ,vecXme.crossProduct(vecYme));
		assertEquals("Y x X = -Z",vecZ.scale(-1),vecY.crossProduct(vecX));
		assertEquals("Y x X+e = -Z",vecZ.scale(-1),vecY.crossProduct(vecXe));
		assertEquals("Y+e x X+e = -Z",vecZ.scale(-1),vecYe.crossProduct(vecXe));
		assertEquals("Y-e x X = -Z",vecZ.scale(-1),vecYme.crossProduct(vecX));
		assertEquals("Y-e x X-e = -Z",vecZ.scale(-1),vecYme.crossProduct(vecXme));

		assertNotEquals("X x Y != 1",vector1,vecX.crossProduct(vecY));
	}

	@Test
	public void testDistance(){
		assertEquals("distance 1",1d,vecX.getHead().distance(Point3D.ZERO_P3D));
		assertEquals("distance 0",0d,vecX.getHead().distance(vecX.getHead()));
		assertEquals("distance 1",2d,vecX.getHead().distance(new Point3D(-1,0,0)));
		assertEquals("distance 1",Math.sqrt(2d),vecX.getHead().distance(new Point3D(0,1,0)));
	}
}
