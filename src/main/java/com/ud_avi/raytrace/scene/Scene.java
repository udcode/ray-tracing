/**
 *
 Course: 151055 Software Engineering intro, Lab
 Student1: Avi margali 305645137
 Student2: Yeuda Nuiman 301759692
 Teacher: Dan Zilberstein
 */


package com.ud_avi.raytrace.scene;

import java.util.List;

import com.ud_avi.raytrace.elements.Camera;
import com.ud_avi.raytrace.geometries.Geometries;
import com.ud_avi.raytrace.geometries.Geometry;
import com.ud_avi.raytrace.lights.AmbientLight;
import com.ud_avi.raytrace.lights.LightSource;
import com.ud_avi.raytrace.primitives.Color;

/**
 * Scene is the Class that represent Scene
 */
public class Scene {
    private String _name;
    private Color _backgroundColor;
    private Geometries _geometriesList;
    private Camera _camera;
    private double _distance;
    private AmbientLight _ambientLight;
    private List<LightSource> _lights;

    // ***************** Constructors ********************** //

    /**
     * Build default scene
     * @param name _name of the scene
     */
    public Scene(String name) {
        _name = name;
    }

    // ***************** Getters/Setters ********************** //

    /**
     * @return the background color
     */
    public Color getBackgroundColor() {
        return _backgroundColor;
    }

    /**
     * @param color of the background
     */
    public void setBackgroundColor(Color color) {
        _backgroundColor = new Color(color);
    }

    /**
     * @return the name of the scene
     */
    public String getName() {
        return _name;
    }

    /**
     * @return the camera of the scene
     */
    public Camera getCamera() {
        return _camera;
    }

    /**
     * @param camera for the scene
     */
    public void setCamera(Camera camera) {
        _camera = camera;
    }

    /**
     * @return the distance from the view
     */
    public double getDistance() {
        return _distance;
    }

    /**
     * @param distance from the view
     */
    public void setDistance(double distance) {
        _distance = distance;
    }

    /**
     * @return the ambient light of the scene
     */
    public AmbientLight getAmbientLight() {
        return _ambientLight;
    }

    /**
     * @param ambientLight for the scene
     */
    public void setAmbientLight(AmbientLight ambientLight) {
        _ambientLight = ambientLight;
    }

    /**
     * @param geometriesList for the scene
     */
    public void setGeometries(Geometries geometriesList) {
        _geometriesList = geometriesList;
    }

    /**
     * @return the Geometries of the scene
     */
    public Geometries getGeometries(){
        return _geometriesList;
    }

    /**
     * @return list of the lights in the scene
     */
    public List<LightSource> getLightSources(){
    	return _lights;
    }

    /**
     *
     * @param lightSources list of the lights in the scene
     */
    public void setLightSources(List<LightSource> lightSources){
         _lights = lightSources;
    }

    // ***************** Operations ******************** //

    /**
     * Add geometry to the scene
     * @param geometry Geometry to add
     */
    public void addGeometry(Geometry geometry){
        _geometriesList.addGeometry(geometry);
    }
    
    /**
     * adding a light source to the lightSource list
     * @param light light to add to scene
     */
    public void addLightSource(LightSource light) {
    	_lights.add(light);
    }

}
