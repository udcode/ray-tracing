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
 * AmbientLight is class that represent the Ambient in scene
 */
public class AmbientLight extends Light{
    private double _ka;

    // ***************** Constructors ********************** //

    /**
     *
     * @param color of the light
     * @param ka factor of light
     */
    public AmbientLight(Color color, double ka) {
        super(color.scale(ka));
        _ka = ka;
    }
    // ***************** Getters/Setters ********************** //

    public double getKa() {
        return _ka;
    }

}
