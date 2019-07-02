/**
 *
 Course: 151055 Software Engineering intro, Lab
 Student1: Avi margali 305645137
 Student2: Yeuda Nuiman 301759692
 Teacher: Dan Zilberstein
 */


package com.ud_avi.raytrace.lights;

import com.ud_avi.raytrace.primitives.Color;

/**
 * Light class is abstract class for all types of com.ud_avi.raytrace.lights
 */
public abstract class Light {
    protected Color _color;

    /**
     * Basic constructor
     * @param color of the light
     */
    public Light(Color color) {
        _color = new Color(color);
    } 

    // ***************** Operations ******************** //

    /**
     * get the intensity light
     * @return The real color
     */
    public Color getIntensity() {
        return new Color(_color);
    }
}
