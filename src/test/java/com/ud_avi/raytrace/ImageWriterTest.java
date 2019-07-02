/**
 *
 Course: 151055 Software Engineering intro, Lab
 Student1: Avi margali 305645137
 Student2: Yeuda Nuiman 301759692
 Teacher: Dan Zilberstein
 */

package com.ud_avi.raytrace;

import com.ud_avi.raytrace.renderer.ImageWriter;
import org.junit.Test;

import static org.junit.Assert.fail;

public class ImageWriterTest {
    @Test
    public void writeImageTest(){
        ImageWriter imageWriter = new ImageWriter("test", 500,500,50,50);
        try {
            for (int i = 0; i < imageWriter.getHeight()-1; i++) {
                for (int j = 0; j < imageWriter.getWidth()-1; j++) {
                    if((i+1) % imageWriter.getNx() == 0 || (j+1) % imageWriter.getNy() == 0) {
                        imageWriter.writePixel(j, i,255,255,255);
                    }
                }
            }
            imageWriter.writeToimage();
        } catch (Exception e) {
            fail("fail");
        }
    }
}