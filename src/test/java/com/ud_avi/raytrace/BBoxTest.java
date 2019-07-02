/**
 * @author Avi margali
 * @author Yeuda Nuiman
 */
package com.ud_avi.raytrace;

import com.ud_avi.raytrace.geometries.BBox;
import com.ud_avi.raytrace.geometries.Geometry;
import com.ud_avi.raytrace.primitives.Point3D;
import com.ud_avi.raytrace.primitives.Ray;
import com.ud_avi.raytrace.primitives.Vector;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class BBoxTest {

    @Test
    public void intersectionTest(){
        BBox bbox = new BBox(Point3D.ZERO_P3D,new Point3D(3,3,3));

        Ray ray0 = new Ray(new Vector(0,0,1),new Point3D(0,0,15));
        Map<Geometry,List<Point3D>> map0 =bbox.findIntersections(ray0);
        assertNull(map0.get(bbox));

        Ray ray00 = new Ray(new Vector(0,0,1),new Point3D(0,-0.001,0));
        Map<Geometry,List<Point3D>> map00 =bbox.findIntersections(ray00);
        assertNull(map00.get(bbox));

        Ray ray1 = new Ray(new Vector(0,0,1),new Point3D(0,0,2));
        Map<Geometry,List<Point3D>> map1 = bbox.findIntersections(ray1);
        assertEquals(1,map1.get(bbox).size());

        Ray ray2 = new Ray(new Vector(0,0,1),new Point3D(0,1,-15));
        Map<Geometry,List<Point3D>> map2 =bbox.findIntersections(ray2);
        assertEquals(2,map2.get(bbox).size());


    }
}
