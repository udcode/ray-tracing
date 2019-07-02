package com.ud_avi.raytrace.renderTests;

import com.ud_avi.raytrace.geometries.Geometries;
import com.ud_avi.raytrace.geometries.Rectangle;
import com.ud_avi.raytrace.geometries.Sphere;
import com.ud_avi.raytrace.geometries.Triangle;
import com.ud_avi.raytrace.lights.LightSource;
import com.ud_avi.raytrace.lights.PointLight;
import com.ud_avi.raytrace.lights.SpotLight;
import com.ud_avi.raytrace.primitives.Color;
import com.ud_avi.raytrace.primitives.Material;
import com.ud_avi.raytrace.primitives.Point3D;
import com.ud_avi.raytrace.primitives.Vector;

import java.util.List;
import java.util.logging.Logger;


public class AdvanceRenderTest {
    protected static Logger log = Logger.getLogger(AdvanceRenderTest.class.getName());


    //====extra tests====//
   protected void buildRoad(Point3D middlePoint, double width, double length, int redLineNumber,int middleLineNumber,
                           int ligthNumber,
                           Geometries geometries, Boolean lights, List<LightSource> lightSources,Boolean latitude){
        double sideWalkWidth = width / 6;
        double sideWalkHigh = 2;
        double startZ = middlePoint.getZ().getCoord();
        double startX = middlePoint.getX().getCoord() ;
        double startY = middlePoint.getY().getCoord();



        Color bulbColor = new Color(255,240,179);
        Material bulbMaterial =new Material(0,0,0.5,0.9,1);
        Color bulbStandColor = new Color(143, 168, 162);
        Material bulbStandMaterial = new Material(0.2,0.5,0,0,100);

        double bulbRadius = 3;
        double bulbHigh = 14;
        double lineLengthWithSpace = length/middleLineNumber;
        double lineLength = lineLengthWithSpace * (2/3d);
        double lineWidth = 2;
        double sideWalkRedWitheLength = length/redLineNumber;
        double distance = length / ligthNumber;
        double bulbStandWidth = sideWalkWidth/8;
        double nstartZ = startZ + distance/2;

        if(latitude){
            Rectangle road = new Rectangle(
                    new Point3D(startZ, startY-0.1, startX - width/2 + sideWalkWidth),
                    new Point3D(startZ,startY-0.1,startX + width/2 - sideWalkWidth),
                    new Point3D(startZ - length,startY-0.1,startX - width/2 + sideWalkWidth),
                    new Color(117,117,117),new Material(0.2,0.2,0,0,20));
            geometries.addGeometry(road);

            for (int i = 0; i < middleLineNumber; i++){
                Rectangle line = new Rectangle(
                        new Point3D( startZ- (i * lineLengthWithSpace) ,startY - 0.2,startX - lineWidth/2),
                        new Point3D(startZ - (i * lineLengthWithSpace),startY - 0.2,startX + lineWidth/2),
                        new Point3D(startZ - lineLength - (i * lineLengthWithSpace),startY - 0.2,startX - lineWidth/2 ),
                        new Color(240,240,240),new Material(1,1,0.01,0,20));
                geometries.addGeometry(line);
            }


            //sidewalk
            Rectangle sideWalkRight = new Rectangle(
                    new Point3D(startZ,startY-sideWalkHigh,startX + sideWalkWidth-(width/2)),
                    new Point3D(startZ,startY-sideWalkHigh, startX - (width/2)),
                    new Point3D(startZ-length,startY-sideWalkHigh, startX + sideWalkWidth-(width/2)),
                    new Color(82, 42, 32),new Material(1,1,0.01,0,20));
            Rectangle sideWalkHeightRight = new Rectangle(
                    new Point3D(startZ,startY, startX + sideWalkWidth-(width/2)),
                    new Point3D(startZ,startY-sideWalkHigh, startX - (width/2)),
                    new Point3D(startZ-length,startY, startX + sideWalkWidth-(width/2)),
                    new Color(36, 16, 14),new Material(1,1,0.01,0,20));

            for (int i = 0; i < redLineNumber; i++){
                Rectangle redLine = new Rectangle(
                        new Point3D(startZ - (i * sideWalkRedWitheLength) ,startY,startX - (width/2)+sideWalkWidth+0.1),
                        new Point3D(startZ - sideWalkRedWitheLength/2 - (i * sideWalkRedWitheLength),startY, startX - (width/2)+sideWalkWidth+0.1),
                        new Point3D(startZ - (i * sideWalkRedWitheLength) ,startY-sideWalkHigh,startX - (width/2)+sideWalkWidth+0.1),
                        new Color(175,10,38),new Material(1,1,0,0,20));

                Rectangle witheLine = new Rectangle(
                        new Point3D(startZ - sideWalkRedWitheLength/2 - (i * sideWalkRedWitheLength),startY, startX-(width/2)+sideWalkWidth+0.1),
                        new Point3D(startZ - sideWalkRedWitheLength - (i * sideWalkRedWitheLength),startY, startX-(width/2)+sideWalkWidth+0.1),
                        new Point3D(startZ - sideWalkRedWitheLength/2 - (i * sideWalkRedWitheLength),startY-sideWalkHigh, startX-(width/2)+sideWalkWidth+0.1),
                        new Color(240,240,240),new Material(1,1,0.01,0,20));
                geometries.addGeometry(redLine);
                geometries.addGeometry(witheLine);
            }

            for (int i = 0; i < redLineNumber; i++){
                Rectangle redLine = new Rectangle(
                        new Point3D(startZ - (i * sideWalkRedWitheLength),startY - sideWalkHigh + 0.001, startX-(width/2)+sideWalkWidth-sideWalkHigh),
                        new Point3D(startZ - sideWalkRedWitheLength/2 - (i * sideWalkRedWitheLength) ,startY - sideWalkHigh + 0.001,startX-(width/2)+sideWalkWidth-sideWalkHigh),
                        new Point3D(startZ - (i * sideWalkRedWitheLength) , startY - sideWalkHigh + 0.001,startX-(width/2)+sideWalkWidth+0.1),
                        new Color(175,10,38),new Material(1,1,0,0,20));

                Rectangle witheLine = new Rectangle(
                        new Point3D(startZ - sideWalkRedWitheLength/2 - (i * sideWalkRedWitheLength) ,startY - sideWalkHigh + 0.001,startX-(width/2)+sideWalkWidth-sideWalkHigh),
                        new Point3D(startZ - sideWalkRedWitheLength - (i * sideWalkRedWitheLength),startY - sideWalkHigh + 0.001, startX-(width/2)+sideWalkWidth-sideWalkHigh),
                        new Point3D(startZ - sideWalkRedWitheLength/2 - (i * sideWalkRedWitheLength),startY - sideWalkHigh + 0.001, startX-(width/2)+sideWalkWidth+0.1),
                        new Color(240,240,240),new Material(1,1,0.01,0,20));
                geometries.addGeometry(redLine);
                geometries.addGeometry(witheLine);
            }

            Rectangle sideWalkLeft = new Rectangle(
                    new Point3D(startZ,startY-sideWalkHigh, startX +  (width/2) - sideWalkWidth),
                    new Point3D(startZ,startY-sideWalkHigh, startX + (width/2)),
                    new Point3D(startZ-length,startY-sideWalkHigh, startX +  (width/2) - sideWalkWidth),
                    new Color(82, 42, 32),new Material(1,1,0.01,0,20));
            Rectangle sideWalkHeightLeft = new Rectangle(
                    new Point3D(startZ,startY, startX +  (width/2) - sideWalkWidth),
                    new Point3D(startZ,startY-sideWalkHigh, startX + (width/2)),
                    new Point3D(startZ-length,startY, startX +  (width/2) - sideWalkWidth),
                    new Color(36, 16, 14),new Material(1,1,0.01,0,20));


            for (int i = 0; i < redLineNumber; i++){
                Rectangle redLine = new Rectangle(
                        new Point3D(startZ - (i * sideWalkRedWitheLength),startY, startX + (width/2) - sideWalkWidth - 0.1),
                        new Point3D(startZ - sideWalkRedWitheLength/2 - (i * sideWalkRedWitheLength),startY, startX + (width/2)- sideWalkWidth- 0.1),
                        new Point3D(startZ - (i * sideWalkRedWitheLength),startY-sideWalkHigh, startX + (width/2)- sideWalkWidth- 0.1),
                        new Color(175,10,38),new Material(1,1,0,0,20));

                Rectangle witheLine = new Rectangle(
                        new Point3D(startZ - sideWalkRedWitheLength/2 - (i * sideWalkRedWitheLength),startY, startX+(width/2)- sideWalkWidth - 0.1),
                        new Point3D(startZ - sideWalkRedWitheLength - (i * sideWalkRedWitheLength) ,startY,startX+(width/2) - sideWalkWidth- 0.1),
                        new Point3D(startZ - sideWalkRedWitheLength/2 - (i * sideWalkRedWitheLength),startY-2, startX+(width/2) - sideWalkWidth- 0.1),
                        new Color(240,240,240),new Material(1,1,0.01,0,20));
                geometries.addGeometry(redLine);
                geometries.addGeometry(witheLine);
            }

            for (int i = 0; i < redLineNumber; i++){
                Rectangle redLine = new Rectangle(
                        new Point3D(startZ - (i * sideWalkRedWitheLength),startY - sideWalkHigh + 0.001, startX+(width/2) - sideWalkWidth+ sideWalkHigh),
                        new Point3D(startZ - sideWalkRedWitheLength/2 - (i * sideWalkRedWitheLength),startY - sideWalkHigh + 0.001,startX+(width/2) - sideWalkWidth+ sideWalkHigh),
                        new Point3D(startZ - (i * sideWalkRedWitheLength),startY - sideWalkHigh + 0.001,startX+(width/2)- sideWalkWidth - 0.1),
                        new Color(175,10,38),new Material(1,1,0,0,20));

                Rectangle witheLine = new Rectangle(
                        new Point3D(startZ - sideWalkRedWitheLength/2 - (i * sideWalkRedWitheLength),startY - sideWalkHigh + 0.001,startX+(width/2)- sideWalkWidth + sideWalkHigh),
                        new Point3D(startZ - sideWalkRedWitheLength - (i * sideWalkRedWitheLength) ,startY - sideWalkHigh + 0.001,startX+(width/2) - sideWalkWidth+ sideWalkHigh),
                        new Point3D( startZ - sideWalkRedWitheLength/2 - (i * sideWalkRedWitheLength),startY - sideWalkHigh + 0.001, startX+(width/2)- sideWalkWidth-0.1),
                        new Color(240,240,240),new Material(1,1,0.01,0,20));
                geometries.addGeometry(redLine);
                geometries.addGeometry(witheLine);
            }

            geometries.addGeometry(sideWalkLeft);
            geometries.addGeometry(sideWalkHeightLeft);
            geometries.addGeometry(sideWalkRight);
            geometries.addGeometry(sideWalkHeightRight);

            for (int i = 1; i <= ligthNumber; i++){
                Sphere bulb = new Sphere(bulbColor,
                        new Point3D( nstartZ - (i * distance),startY-bulbHigh , startX - (width/2) + sideWalkWidth/3),
                        bulbRadius,bulbMaterial);
                Rectangle bulb1StandRight = new Rectangle(
                        new Point3D(nstartZ - (i * distance) ,startY,startX -(width/2) + sideWalkWidth/3 - bulbStandWidth),
                        new Point3D(nstartZ - (i * distance),startY, startX -(width/2) + sideWalkWidth/3 + bulbStandWidth),
                        new Point3D(nstartZ - (i * distance),startY-bulbHigh,startX -(width/2) + sideWalkWidth/3 - bulbStandWidth) ,
                        bulbStandColor,bulbStandMaterial);

                SpotLight pLight = new SpotLight(bulbColor,
                        new Point3D(nstartZ - (i * distance),startY - bulbHigh, startX -(width/2) + sideWalkWidth/3),1,0.01,0.01,new Vector(0,1,0));
                geometries.addGeometry(bulb);
                geometries.addGeometry(bulb1StandRight);
                if(lights) {
                    lightSources.add(pLight);
                }
            }

            for (int i = 1; i <= ligthNumber; i++){
                Sphere bulb = new Sphere(bulbColor,
                        new Point3D( nstartZ - (i * distance),startY-bulbHigh , startX + (width/2) - sideWalkWidth/3),
                        bulbRadius,bulbMaterial);
                Rectangle bulb1StandRight = new Rectangle(
                        new Point3D(nstartZ - (i * distance),startY, startX +(width/2) - sideWalkWidth/3 - bulbStandWidth),
                        new Point3D(nstartZ - (i * distance) ,startY,startX +(width/2) - sideWalkWidth/3 + bulbStandWidth),
                        new Point3D(nstartZ - (i * distance),startY-bulbHigh, startX +(width/2) - sideWalkWidth/3 - bulbStandWidth),
                        bulbStandColor,bulbStandMaterial);

                SpotLight pLight = new SpotLight(bulbColor,
                        new Point3D(nstartZ - (i * distance) ,startY - bulbHigh,startX +(width/2) - sideWalkWidth/3),1,0.01,0.01,new Vector(0,1,0));
                geometries.addGeometry(bulb);
                geometries.addGeometry(bulb1StandRight);
                if(lights) {
                    lightSources.add(pLight);
                }
            }

        } else {

            Rectangle road = new Rectangle(new Point3D(startX - width / 2 + sideWalkWidth, startY-0.1, startZ),
                    new Point3D(startX + width / 2 - sideWalkWidth, startY-0.1, startZ),
                    new Point3D(startX - width / 2 + sideWalkWidth, startY-0.1, startZ - length),
                    new Color(117, 117, 117), new Material(0.2, 0.2, 0, 0, 20));
            geometries.addGeometry(road);

            //lines
            for (int i = 0; i < middleLineNumber; i++) {
                Rectangle line = new Rectangle(
                        new Point3D(startX - lineWidth / 2, startY - 0.2, startZ - (i * lineLengthWithSpace)),
                        new Point3D(startX + lineWidth / 2, startY - 0.2, startZ - (i * lineLengthWithSpace)),
                        new Point3D(startX - lineWidth / 2, startY - 0.2, startZ - lineLength - (i * lineLengthWithSpace)),
                        new Color(240, 240, 240), new Material(1, 1, 0.01, 0, 20));
                geometries.addGeometry(line);
            }


            //sidewalk
            Rectangle sideWalkRight = new Rectangle(
                    new Point3D(startX + sideWalkWidth - (width / 2), startY - sideWalkHigh, startZ),
                    new Point3D(startX - (width / 2), startY - sideWalkHigh, startZ),
                    new Point3D(startX + sideWalkWidth - (width / 2), startY - sideWalkHigh, startZ - length),
                    new Color(82, 42, 32), new Material(1, 1, 0.01, 0, 20));
            Rectangle sideWalkHeightRight = new Rectangle(
                    new Point3D(startX + sideWalkWidth - (width / 2), startY, startZ),
                    new Point3D(startX - (width / 2), startY - sideWalkHigh, startZ),
                    new Point3D(startX + sideWalkWidth - (width / 2), startY, startZ - length),
                    new Color(36, 16, 14), new Material(1, 1, 0.01, 0, 20));

            for (int i = 0; i < redLineNumber; i++) {
                Rectangle redLine = new Rectangle(
                        new Point3D(startX - (width / 2) + sideWalkWidth + 0.1, startY, startZ - (i * sideWalkRedWitheLength)),
                        new Point3D(startX - (width / 2) + sideWalkWidth + 0.1, startY, startZ - sideWalkRedWitheLength / 2 - (i * sideWalkRedWitheLength)),
                        new Point3D(startX - (width / 2) + sideWalkWidth + 0.1, startY - sideWalkHigh, startZ - (i * sideWalkRedWitheLength)),
                        new Color(175, 10, 38), new Material(1, 1, 0, 0, 20));

                Rectangle witheLine = new Rectangle(
                        new Point3D(startX - (width / 2) + sideWalkWidth + 0.1, startY, startZ - sideWalkRedWitheLength / 2 - (i * sideWalkRedWitheLength)),
                        new Point3D(startX - (width / 2) + sideWalkWidth + 0.1, startY, startZ - sideWalkRedWitheLength - (i * sideWalkRedWitheLength)),
                        new Point3D(startX - (width / 2) + sideWalkWidth + 0.1, startY - sideWalkHigh, startZ - sideWalkRedWitheLength / 2 - (i * sideWalkRedWitheLength)),
                        new Color(240, 240, 240), new Material(1, 1, 0.01, 0, 20));
                geometries.addGeometry(redLine);
                geometries.addGeometry(witheLine);
            }

            for (int i = 0; i < redLineNumber; i++) {
                Rectangle redLine = new Rectangle(
                        new Point3D(startX - (width / 2) + sideWalkWidth - sideWalkHigh, startY - sideWalkHigh + 0.001, startZ - (i * sideWalkRedWitheLength)),
                        new Point3D(startX - (width / 2) + sideWalkWidth - sideWalkHigh, startY - sideWalkHigh + 0.001, startZ - sideWalkRedWitheLength / 2 - (i * sideWalkRedWitheLength)),
                        new Point3D(startX - (width / 2) + sideWalkWidth + 0.1, startY - sideWalkHigh + 0.001, startZ - (i * sideWalkRedWitheLength)),
                        new Color(175, 10, 38), new Material(1, 1, 0, 0, 20));

                Rectangle witheLine = new Rectangle(
                        new Point3D(startX - (width / 2) + sideWalkWidth - sideWalkHigh, startY - sideWalkHigh + 0.001, startZ - sideWalkRedWitheLength / 2 - (i * sideWalkRedWitheLength)),
                        new Point3D(startX - (width / 2) + sideWalkWidth - sideWalkHigh, startY - sideWalkHigh + 0.001, startZ - sideWalkRedWitheLength - (i * sideWalkRedWitheLength)),
                        new Point3D(startX - (width / 2) + sideWalkWidth + 0.1, startY - sideWalkHigh + 0.001, startZ - sideWalkRedWitheLength / 2 - (i * sideWalkRedWitheLength)),
                        new Color(240, 240, 240), new Material(1, 1, 0.01, 0, 20));
                geometries.addGeometry(redLine);
                geometries.addGeometry(witheLine);
            }

            Rectangle sideWalkLeft = new Rectangle(
                    new Point3D(startX + (width / 2) - sideWalkWidth, startY - sideWalkHigh, startZ),
                    new Point3D(startX + (width / 2), startY - sideWalkHigh, startZ),
                    new Point3D(startX + (width / 2) - sideWalkWidth, startY - sideWalkHigh, startZ - length),
                    new Color(82, 42, 32), new Material(1, 1, 0.01, 0, 20));
            Rectangle sideWalkHeightLeft = new Rectangle(
                    new Point3D(startX + (width / 2) - sideWalkWidth, startY, startZ),
                    new Point3D(startX + (width / 2), startY - sideWalkHigh, startZ),
                    new Point3D(startX + (width / 2) - sideWalkWidth, startY, startZ - length),
                    new Color(36, 16, 14), new Material(1, 1, 0.01, 0, 20));


            for (int i = 0; i < redLineNumber; i++) {
                Rectangle redLine = new Rectangle(
                        new Point3D(startX + (width / 2) - sideWalkWidth - 0.1, startY, startZ - (i * sideWalkRedWitheLength)),
                        new Point3D(startX + (width / 2) - sideWalkWidth - 0.1, startY, startZ - sideWalkRedWitheLength / 2 - (i * sideWalkRedWitheLength)),
                        new Point3D(startX + (width / 2) - sideWalkWidth - 0.1, startY - sideWalkHigh, startZ - (i * sideWalkRedWitheLength)),
                        new Color(175, 10, 38), new Material(1, 1, 0, 0, 20));

                Rectangle witheLine = new Rectangle(
                        new Point3D(startX + (width / 2) - sideWalkWidth - 0.1, startY, startZ - sideWalkRedWitheLength / 2 - (i * sideWalkRedWitheLength)),
                        new Point3D(startX + (width / 2) - sideWalkWidth - 0.1, startY, startZ - sideWalkRedWitheLength - (i * sideWalkRedWitheLength)),
                        new Point3D(startX + (width / 2) - sideWalkWidth - 0.1, startY - 2, startZ - sideWalkRedWitheLength / 2 - (i * sideWalkRedWitheLength)),
                        new Color(240, 240, 240), new Material(1, 1, 0.01, 0, 20));
                geometries.addGeometry(redLine);
                geometries.addGeometry(witheLine);
            }

            for (int i = 0; i < redLineNumber; i++) {
                Rectangle redLine = new Rectangle(
                        new Point3D(startX + (width / 2) - sideWalkWidth + sideWalkHigh, startY - sideWalkHigh + 0.001, startZ - (i * sideWalkRedWitheLength)),
                        new Point3D(startX + (width / 2) - sideWalkWidth + sideWalkHigh, startY - sideWalkHigh + 0.001, startZ - sideWalkRedWitheLength / 2 - (i * sideWalkRedWitheLength)),
                        new Point3D(startX + (width / 2) - sideWalkWidth - 0.1, startY - sideWalkHigh + 0.001, startZ - (i * sideWalkRedWitheLength)),
                        new Color(175, 10, 38), new Material(1, 1, 0, 0, 20));

                Rectangle witheLine = new Rectangle(
                        new Point3D(startX + (width / 2) - sideWalkWidth + sideWalkHigh, startY - sideWalkHigh + 0.001, startZ - sideWalkRedWitheLength / 2 - (i * sideWalkRedWitheLength)),
                        new Point3D(startX + (width / 2) - sideWalkWidth + sideWalkHigh, startY - sideWalkHigh + 0.001, startZ - sideWalkRedWitheLength - (i * sideWalkRedWitheLength)),
                        new Point3D(startX + (width / 2) - sideWalkWidth - 0.1, startY - sideWalkHigh + 0.001, startZ - sideWalkRedWitheLength / 2 - (i * sideWalkRedWitheLength)),
                        new Color(240, 240, 240), new Material(1, 1, 0.01, 0, 20));
                geometries.addGeometry(redLine);
                geometries.addGeometry(witheLine);
            }

            geometries.addGeometry(sideWalkLeft);
            geometries.addGeometry(sideWalkHeightLeft);
            geometries.addGeometry(sideWalkRight);
            geometries.addGeometry(sideWalkHeightRight);

           
            for (int i = 1; i <= ligthNumber; i++) {
                Sphere bulb = new Sphere(bulbColor,
                        new Point3D(startX - (width / 2) + sideWalkWidth / 3, startY - bulbHigh, nstartZ - (i * distance)),
                        bulbRadius, bulbMaterial);
                Rectangle bulb1StandRight = new Rectangle(
                        new Point3D(startX - (width / 2) + sideWalkWidth / 3 - bulbStandWidth, startY, nstartZ - (i * distance)),
                        new Point3D(startX - (width / 2) + sideWalkWidth / 3 + bulbStandWidth, startY, nstartZ - (i * distance)),
                        new Point3D(startX - (width / 2) + sideWalkWidth / 3 - bulbStandWidth, startY - bulbHigh, nstartZ - (i * distance)),
                        bulbStandColor, bulbStandMaterial);

                SpotLight pLight = new SpotLight(bulbColor,
                        new Point3D(startX - (width / 2) + sideWalkWidth / 3, startY - bulbHigh, nstartZ - (i * distance)), 1, 0.01, 0.01, new Vector(0, 1, 0));
                geometries.addGeometry(bulb);
                geometries.addGeometry(bulb1StandRight);
                if (lights) {
                    lightSources.add(pLight);
                }
            }

            for (int i = 1; i <= ligthNumber; i++) {
                Sphere bulb = new Sphere(bulbColor,
                        new Point3D(startX + (width / 2) - sideWalkWidth / 3, startY - bulbHigh, nstartZ - (i * distance)),
                        bulbRadius, bulbMaterial);
                Rectangle bulb1StandRight = new Rectangle(
                        new Point3D(startX + (width / 2) - sideWalkWidth / 3 - bulbStandWidth, startY, nstartZ - (i * distance)),
                        new Point3D(startX + (width / 2) - sideWalkWidth / 3 + bulbStandWidth, startY, nstartZ - (i * distance)),
                        new Point3D(startX + (width / 2) - sideWalkWidth / 3 - bulbStandWidth, startY - bulbHigh, nstartZ - (i * distance)),
                        bulbStandColor, bulbStandMaterial);

                SpotLight pLight = new SpotLight(bulbColor,
                        new Point3D(startX + (width / 2) - sideWalkWidth / 3, startY - bulbHigh, nstartZ - (i * distance)), 1, 0.01, 0.01, new Vector(0, 1, 0));
                geometries.addGeometry(bulb);
                geometries.addGeometry(bulb1StandRight);
                if (lights) {
                    lightSources.add(pLight);
                }
            }
        }

    }
    protected void buildCross(Point3D startPoint, double width, double sideWalkWidth, double sideWalkHigh,
                            int middleLineNumber, Geometries geometries){

        double startZ = startPoint.getZ().getCoord();
        double startX = startPoint.getX().getCoord() ;
        double startY = startPoint.getY().getCoord();
        double lineWidth = (width-sideWalkWidth*2)/middleLineNumber;

        Rectangle road = new Rectangle(
                new Point3D(startX, startY-0.1, startZ),
                new Point3D(startX,startY-0.1,startZ+width),
                new Point3D(startX+width,startY-0.1,startZ),
                new Color(117,117,117),new Material(0.2,0.2,0,0,20));
        geometries.addGeometry(road);

        double nstartX = startX + sideWalkWidth + lineWidth/4;
        for (int i = 0; i < middleLineNumber; i++){
            Rectangle line = new Rectangle(
                    new Point3D( nstartX + (i * lineWidth) ,startY - 0.2,startZ),
                    new Point3D(nstartX + (i * lineWidth),startY - 0.2,startZ +sideWalkWidth),
                    new Point3D(nstartX + lineWidth/2 + (i * lineWidth), startY - 0.2,startZ ),
                    new Color(240,240,240),new Material(1,1,0.01,0,20));
            geometries.addGeometry(line);
        }

        for (int i = 0; i < middleLineNumber; i++){
            Rectangle line = new Rectangle(
                    new Point3D( nstartX + (i * lineWidth) ,startY - 0.2,startZ + width),
                    new Point3D(nstartX + (i * lineWidth),startY - 0.2,startZ +  width - sideWalkWidth),
                    new Point3D(nstartX + lineWidth/2 + (i * lineWidth), startY - 0.2,startZ + width),
                    new Color(240,240,240),new Material(1,1,0.01,0,20));
            geometries.addGeometry(line);
        }
        double nstartZ = startZ + sideWalkWidth + lineWidth/4;
        for (int i = 0; i < middleLineNumber; i++){
            Rectangle line = new Rectangle(
                    new Point3D( startX  ,startY - 0.2,nstartZ + (i * lineWidth)),
                    new Point3D(startX + sideWalkWidth,startY - 0.2,nstartZ +  (i * lineWidth)),
                    new Point3D(startX, startY - 0.2,nstartZ + lineWidth/2 + (i * lineWidth)),
                    new Color(240,240,240),new Material(1,1,0.01,0,20));
            geometries.addGeometry(line);
        }
        for (int i = 0; i < middleLineNumber; i++){
            Rectangle line = new Rectangle(
                    new Point3D( startX +width ,startY - 0.2,nstartZ + (i * lineWidth)),
                    new Point3D(startX +width -sideWalkWidth,startY - 0.2,nstartZ +  (i * lineWidth)),
                    new Point3D(startX+width, startY - 0.2,nstartZ + lineWidth/2 + (i * lineWidth)),
                    new Color(240,240,240),new Material(1,1,0.01,0,20));
            geometries.addGeometry(line);
        }
        //sidewalk
        Rectangle sideWalkRightBottom = new Rectangle(
                new Point3D(startX,startY-sideWalkHigh,startZ ),
                new Point3D(startX,startY-sideWalkHigh, startZ + sideWalkWidth),
                new Point3D(startX + sideWalkWidth,startY-sideWalkHigh, startZ),
                new Color(82, 42, 32),new Material(1,1,0.01,0,20));
        Rectangle sideWalkHeightRightBottom1 = new Rectangle(
                new Point3D(startX + sideWalkWidth, startY, startZ),
                new Point3D(startX + sideWalkWidth,startY-sideWalkHigh, startZ),
                new Point3D(startX + sideWalkWidth,startY, startZ+ sideWalkWidth),
                new Color(36, 16, 14),new Material(1,1,0.01,0,20));
        Rectangle sideWalkHeightRightBottom2 = new Rectangle(
                new Point3D(startX , startY, startZ+ sideWalkWidth),
                new Point3D(startX ,startY-sideWalkHigh, startZ+ sideWalkWidth),
                new Point3D(startX + sideWalkWidth,startY, startZ+ sideWalkWidth),
                new Color(36, 16, 14),new Material(1,1,0.01,0,20));

        Rectangle sideWalkLeftBottom = new Rectangle(
                new Point3D(startX + width,startY-sideWalkHigh,startZ ),
                new Point3D(startX + width,startY-sideWalkHigh, startZ + sideWalkWidth),
                new Point3D(startX + width - sideWalkWidth,startY-sideWalkHigh, startZ),
                new Color(82, 42, 32),new Material(1,1,0.01,0,20));
        Rectangle sideWalkHeightLeftBottom1 = new Rectangle(
                new Point3D(startX + width - sideWalkWidth, startY, startZ),
                new Point3D(startX + width - sideWalkWidth,startY-sideWalkHigh, startZ),
                new Point3D(startX + width - sideWalkWidth,startY, startZ+ sideWalkWidth),
                new Color(36, 16, 14),new Material(1,1,0.01,0,20));
        Rectangle sideWalkHeightLeftBottom2 = new Rectangle(
                new Point3D(startX + width , startY, startZ+ sideWalkWidth),
                new Point3D(startX + width,startY-sideWalkHigh, startZ+ sideWalkWidth),
                new Point3D(startX + width - sideWalkWidth,startY, startZ+ sideWalkWidth),
                new Color(36, 16, 14),new Material(1,1,0.01,0,20));

        Rectangle sideWalkLeftUp = new Rectangle(
                new Point3D(startX + width,startY-sideWalkHigh,startZ +width),
                new Point3D(startX + width,startY-sideWalkHigh, startZ +width- sideWalkWidth),
                new Point3D(startX + width - sideWalkWidth,startY-sideWalkHigh, startZ+width),
                new Color(82, 42, 32),new Material(1,1,0.01,0,20));
        Rectangle sideWalkHeightLeftUp1 = new Rectangle(
                new Point3D(startX + width - sideWalkWidth, startY, startZ+width),
                new Point3D(startX + width - sideWalkWidth,startY-sideWalkHigh, startZ+width),
                new Point3D(startX + width - sideWalkWidth,startY, startZ+width- sideWalkWidth),
                new Color(36, 16, 14),new Material(1,1,0.01,0,20));
        Rectangle sideWalkHeightLeftUp2 = new Rectangle(
                new Point3D(startX + width, startY, startZ +width- sideWalkWidth),
                new Point3D(startX + width,startY-sideWalkHigh, startZ +width- sideWalkWidth),
                new Point3D(startX + width - sideWalkWidth,startY, startZ+width- sideWalkWidth),
                new Color(36, 16, 14),new Material(1,1,0.01,0,20));

        Rectangle sideWalkRightUp = new Rectangle(
                new Point3D(startX,startY-sideWalkHigh,startZ +width),
                new Point3D(startX,startY-sideWalkHigh, startZ +width- sideWalkWidth),
                new Point3D(startX + sideWalkWidth,startY-sideWalkHigh, startZ+width),
                new Color(82, 42, 32),new Material(1,1,0.01,0,20));
        Rectangle sideWalkHeightRightUp1 = new Rectangle(
                new Point3D(startX + sideWalkWidth, startY, startZ+width),
                new Point3D(startX + sideWalkWidth,startY-sideWalkHigh, startZ+width),
                new Point3D(startX + sideWalkWidth,startY, startZ+width- sideWalkWidth),
                new Color(36, 16, 14),new Material(1,1,0.01,0,20));
        Rectangle sideWalkHeightRightUp2 = new Rectangle(
                new Point3D(startX , startY, startZ + width- sideWalkWidth),
                new Point3D(startX ,startY-sideWalkHigh, startZ+width - sideWalkWidth),
                new Point3D(startX + sideWalkWidth,startY, startZ+width- sideWalkWidth),
                new Color(36, 16, 14),new Material(1,1,0.01,0,20));
        geometries.addGeometry(sideWalkRightBottom);
        geometries.addGeometry(sideWalkHeightRightBottom1);
        geometries.addGeometry(sideWalkHeightRightBottom2);
        geometries.addGeometry(sideWalkLeftBottom);
        geometries.addGeometry(sideWalkHeightLeftBottom1);
        geometries.addGeometry(sideWalkHeightLeftBottom2);
        geometries.addGeometry(sideWalkLeftUp);
        geometries.addGeometry(sideWalkHeightLeftUp1);
        geometries.addGeometry(sideWalkHeightLeftUp2);
        geometries.addGeometry(sideWalkRightUp);
        geometries.addGeometry(sideWalkHeightRightUp1);
        geometries.addGeometry(sideWalkHeightRightUp2);


    }


    //direction is vector with values of 1,-1
    private void buildSimpleHouse(Point3D startPoint,double width, double high, double length, Vector direction,
                                  Geometries geometries, Boolean lights, List<LightSource> lightSources){

        Material wallsMaterial = new Material(0.2,0.5,0,0,100);
        Color wallsColor = new Color(143, 168, 162);
        Color windowColor= new Color(0,0,0);
        Material windowMaterial = new Material(2,3,0,0.5,100);

        double startZ = startPoint.getZ().getCoord();
        double startX = startPoint.getX().getCoord();
        double startY = startPoint.getY().getCoord();

        double directionX = direction.getHead().getX().getCoord();
        double directionY = direction.getHead().getY().getCoord();
        double directionZ = direction.getHead().getZ().getCoord();
        double windowHigh = high/4;
        double windowWidth = width /3;
        double doorHigh = high /3;
        double doorWidth = length /5;
        Rectangle backWallHouse = new Rectangle(new Point3D(startX + directionX * width,startY,startZ),
                new Point3D(startX + directionX * width , startY, startZ + directionZ * length),
                new Point3D(startX + directionX * width ,startY + directionY * high, startZ),
                wallsColor,wallsMaterial);

        Rectangle side1BottomWallHouse = new Rectangle(
                new Point3D(startX ,startY,startZ),
                new Point3D(startX + directionX *  width,startY,startZ),
                new Point3D(startX, startY + directionY * ((high - windowHigh) /2),startZ),
                wallsColor,wallsMaterial);

        Rectangle side1Middle1WallHouse = new Rectangle(
                new Point3D(startX,startY + directionY * ((high - windowHigh) /2),startZ),
                new Point3D(startX,startY + directionY * ((high + windowHigh) /2),startZ),
                new Point3D(startX + directionX * ((width-windowWidth)/2),startY + directionY * ((high - windowHigh) /2),startZ),
                wallsColor,wallsMaterial);

        Rectangle side1Middle2WallHouse = new Rectangle(
                new Point3D(startX + directionX *  ((width+windowWidth)/2),startY + directionY * ((high - windowHigh) /2),startZ),
                new Point3D(startX + directionX * ((width+windowWidth)/2),startY + directionY * ((high + windowHigh) /2),startZ),
                new Point3D(startX + directionX *  width,startY + directionY * ((high - windowHigh) /2),startZ),
                wallsColor,wallsMaterial);

        Rectangle side1TopWallHouse = new Rectangle(
                new Point3D(startX,startY + directionY * ((high - windowHigh) /2),startZ),
                new Point3D(startX + directionX *  width,startY + directionY * ((high - windowHigh) /2),startZ),
                new Point3D(startX,startY + directionY * high,startZ),
                wallsColor,wallsMaterial);

        Rectangle side1WindowHouse = new Rectangle(
                new Point3D(startX + directionX * ((width - windowWidth)/2),startY + directionY * ((high - windowHigh) /2),startZ),
                new Point3D(startX + directionX * ((width + windowWidth)/2), startY + directionY * ((high - windowHigh) /2),startZ),
                new Point3D(startX + directionX * ((width - windowWidth)/2),startY + directionY * ((high + windowHigh) /2),startZ),
                windowColor,windowMaterial);

        Rectangle side2BottomWallHouse = new Rectangle(
                new Point3D(startX ,startY,startZ + directionZ * length),
                new Point3D(startX + directionX * width,startY,startZ + directionZ *  length),
                new Point3D(startX, startY + directionY * ((high - windowHigh) /2),startZ +directionZ * length),
                wallsColor,wallsMaterial);

        Rectangle side2Middle1WallHouse = new Rectangle(
                new Point3D(startX,startY + directionY * ((high - windowHigh) /2),startZ + directionZ * length),
                new Point3D(startX,startY + directionY * ((high + windowHigh) /2),startZ + directionZ * length),
                new Point3D(startX + directionX * ((width-windowWidth)/2),startY + directionY * ((high - windowHigh) /2),startZ + directionZ * length),
                wallsColor,wallsMaterial);

        Rectangle side2Middle2WallHouse = new Rectangle(
                new Point3D(startX + directionX *  ((width + windowWidth)/2),startY + directionY * ((high - windowHigh) /2),startZ + directionZ * length),
                new Point3D(startX + directionX * ((width + windowWidth)/2),startY + directionY * ((high + windowHigh) /2),startZ + directionZ * length),
                new Point3D(startX + directionX *  width,startY + directionY * ((high - windowHigh) /2),startZ + directionZ * length),
                wallsColor,wallsMaterial);

        Rectangle side2TopWallHouse = new Rectangle(
                new Point3D(startX,startY + directionY * ((high - windowHigh) /2),startZ + directionZ * length),
                new Point3D(startX + directionX *  width,startY + directionY *  ((high - windowHigh) /2),startZ + directionZ * length),
                new Point3D(startX,startY + directionY *  high,startZ + directionZ * length),
                wallsColor,wallsMaterial);

        Rectangle side2WindowHouse = new Rectangle(
                new Point3D(startX + directionX * ((width-windowWidth)/2),startY + directionY * ((high - windowHigh) /2),startZ + directionZ * length),
                new Point3D(startX + directionX * ((width+windowWidth)/2), startY + directionY * ((high - windowHigh) /2),startZ + directionZ * length),
                new Point3D(startX + directionX * ((width-windowWidth)/2),startY + directionY * ((high + windowHigh) /2),startZ + directionZ * length),
                windowColor,windowMaterial);

        Rectangle frontWallLeftHouse = new Rectangle(
                new Point3D(startX, startY ,startZ),
                new Point3D(startX, startY,startZ + directionZ * ((length - doorWidth) /2)),
                new Point3D(startX,startY+directionY * high,startZ),
                wallsColor,wallsMaterial);

        Rectangle frontWallRightHouse = new Rectangle(
                new Point3D(startX,startY,startZ + directionZ *  length),
                new Point3D(startX,startY,startZ + directionZ * ((length + doorWidth)/2)),
                new Point3D(startX,startY + directionY * high,startZ + directionZ *  length),
                wallsColor,wallsMaterial);

        Rectangle frontWallAboveDoorHouse = new Rectangle(
                new Point3D(startX,startY+directionY * doorHigh,startZ + directionZ * ((length - doorWidth)/2)),
                new Point3D(startX,startY+directionY * doorHigh,startZ + directionZ * ((length + doorWidth)/2)),
                new Point3D(startX,startY+directionY * high,startZ + directionZ * ((length - doorWidth)/2)),
                wallsColor,wallsMaterial);

        Rectangle frontDoorHouse = new Rectangle(
                new Point3D(startX,startY,startZ + directionZ * ((length - doorWidth)/2)),
                new Point3D(startX,startY,startZ + directionZ * ((length + doorWidth)/2)),
                new Point3D(startX,startY+ directionY * doorHigh,startZ + directionZ * ((length - doorWidth)/2)),
                windowColor,windowMaterial);

        Rectangle roofHouse = new Rectangle(
                new Point3D(startX,startY + directionY * high,startZ),
                new Point3D(startX,startY + directionY * high, startZ + directionZ * length),
                new Point3D(startX + directionX *  width,startY + directionY * high, startZ),
                wallsColor,wallsMaterial);

        Rectangle floorHouse = new Rectangle(
                new Point3D(startX,startY,startZ),
                new Point3D(startX,startY, startZ + directionZ * length),
                new Point3D(startX + directionX *  width,startY,startZ),
                wallsColor,wallsMaterial);

        geometries.addGeometry(backWallHouse);

        geometries.addGeometry(side1BottomWallHouse);
        geometries.addGeometry(side1Middle1WallHouse);
        geometries.addGeometry(side1Middle2WallHouse);
        geometries.addGeometry(side1TopWallHouse);
        geometries.addGeometry(side1WindowHouse);

        geometries.addGeometry(side2BottomWallHouse);
        geometries.addGeometry(side2Middle1WallHouse);
        geometries.addGeometry(side2Middle2WallHouse);
        geometries.addGeometry(side2TopWallHouse);
        geometries.addGeometry(side2WindowHouse);

        geometries.addGeometry(frontWallLeftHouse);
        geometries.addGeometry(frontWallRightHouse);
        geometries.addGeometry(frontWallAboveDoorHouse);
        geometries.addGeometry(frontDoorHouse);

        geometries.addGeometry(roofHouse);
        geometries.addGeometry(floorHouse);

        PointLight houseLightLeft = new PointLight(
                new Color(255,240,240),
                new Point3D(startX + directionX * (width/2),startY + directionY * (high - 3),startZ + directionZ * (length/2)),1,0.001,0.001);
        if (lights) {
            lightSources.add(houseLightLeft);
        }

    }

    protected void buildBuilding(Point3D startPoint, int numFloors, double width, double floorHigh, double length, Vector direction,
                               Geometries geometries, Boolean[] light, List<LightSource> lightSources,
                               Color decoColor,Color wallsColor){

        buildGroundFloor(startPoint,
                width, floorHigh,10, length, direction, geometries, light[0], lightSources, decoColor, wallsColor);

        startPoint = new Point3D(startPoint.getX().getCoord(),startPoint.getY().getCoord() + direction.getHead().getY().getCoord() * (floorHigh + 10),startPoint.getZ().getCoord() );

        for(int i=0;i<numFloors-1;++i) {
            buildFloor(new Point3D(startPoint.getX().getCoord(),startPoint.getY().getCoord() +(i* direction.getHead().getY().getCoord() * floorHigh),startPoint.getZ().getCoord()),
                    width, floorHigh, length, direction, geometries, light[i], lightSources, decoColor, wallsColor);

        }
        buildTopOfBuilding(new Point3D(startPoint.getX().getCoord(),startPoint.getY().getCoord() +((numFloors-1)* direction.getHead().getY().getCoord() * floorHigh),startPoint.getZ().getCoord()),
                width, 20, length, direction, geometries, lightSources, decoColor, wallsColor);
    }
    //direction is vector with values of 1,-1
    private void buildFloor(Point3D startPoint,double width, double high, double length, Vector direction,
                            Geometries geometries, Boolean light, List<LightSource> lightSources,
                            Color decoColor,Color wallsColor){

        Material wallsMaterial = new Material(1,1,0,0,2);
        Color windowColor= new Color(5,5,5);
        Material windowMaterial = new Material(2,3,0,0.9,2);

        double startZ = startPoint.getZ().getCoord();
        double startX = startPoint.getX().getCoord();
        double startY = startPoint.getY().getCoord();

        double directionX = direction.getHead().getX().getCoord();
        double directionY = direction.getHead().getY().getCoord();
        double directionZ = direction.getHead().getZ().getCoord();

        double frontPart = length/3;
        double windowHigh = high * 3/4;
        double windowWidth = frontPart * 3/4;


        Rectangle backWall = new Rectangle(new Point3D(startX + directionX * width,startY,startZ),
                new Point3D(startX + directionX * width , startY, startZ + directionZ * length),
                new Point3D(startX + directionX * width ,startY + directionY * high, startZ),
                wallsColor,wallsMaterial);

        Rectangle side1Wall = new Rectangle(
                new Point3D(startX ,startY,startZ),
                new Point3D(startX + directionX *  width ,startY,startZ),
                new Point3D(startX, startY + directionY * high ,startZ),
                wallsColor,wallsMaterial);

        Rectangle side2Wall = new Rectangle(
                new Point3D(startX ,startY,startZ + directionZ * length),
                new Point3D(startX + directionX * width,startY,startZ + directionZ *  length),
                new Point3D(startX, startY + directionY * high ,startZ + directionZ * length),
                wallsColor,wallsMaterial);


        Rectangle frontWallLeftBottom = new Rectangle(
                new Point3D(startX, startY ,startZ),
                new Point3D(startX, startY,startZ + directionZ * frontPart),
                new Point3D(startX,startY + directionY * ((high-windowHigh)/2), startZ),
                wallsColor,wallsMaterial);
        Rectangle frontWallLeftLeft = new Rectangle(
                new Point3D(startX,startY + directionY * ((high + windowHigh)/2),startZ),
                new Point3D(startX,startY + directionY * ((high + windowHigh)/2),startZ + directionZ * ((frontPart-windowWidth)/2)),
                new Point3D(startX,startY + directionY * ((high - windowHigh)/2),startZ ),
                wallsColor,wallsMaterial);

        Rectangle frontWallLeftRight = new Rectangle(
                new Point3D(startX,startY + directionY * ((high + windowHigh)/2),startZ + directionZ * frontPart),
                new Point3D(startX,startY + directionY * ((high + windowHigh)/2),startZ + directionZ * ((frontPart+windowWidth)/2)),
                new Point3D(startX,startY + directionY * ((high - windowHigh)/2),startZ + directionZ *  frontPart),
                wallsColor,wallsMaterial);

        Rectangle frontWallLeftTop = new Rectangle(
                new Point3D(startX,startY + directionY * high,startZ),
                new Point3D(startX,startY + directionY * high,startZ + directionZ * frontPart),
                new Point3D(startX,startY + directionY * ((high+windowHigh)/2),startZ ),
                wallsColor,wallsMaterial);

        Rectangle frontWallLeftWindow = new Rectangle(
                new Point3D(startX,startY + directionY * ((high - windowHigh)/2),startZ + directionZ * ((frontPart-windowWidth)/2)),
                new Point3D(startX,startY + directionY * ((high + windowHigh)/2),startZ + directionZ * ((frontPart-windowWidth)/2)),
                new Point3D(startX,startY + directionY * ((high - windowHigh)/2),startZ + directionZ * ((frontPart+windowWidth)/2)),
                windowColor,windowMaterial);

        double middleStartZ = startZ + directionZ * frontPart;
        double middleStartX = startX + -1 * directionX * (length/20);
        double midWindowHigh = windowHigh * 3/5;
        double midWindowWidth = windowWidth * 5/7;

        Rectangle frontWallMiddleSide1 = new Rectangle(
                new Point3D(startX, startY ,middleStartZ),
                new Point3D(startX, startY + directionY * high,middleStartZ),
                new Point3D(middleStartX,startY, middleStartZ),
                decoColor,wallsMaterial);
        Rectangle frontWallMiddleSide2 = new Rectangle(
                new Point3D(startX, startY ,middleStartZ + directionZ *  frontPart ),
                new Point3D(startX, startY + directionY * high,middleStartZ + directionZ *  frontPart),
                new Point3D(middleStartX,startY, middleStartZ + directionZ *  frontPart),
                decoColor,wallsMaterial);

        Rectangle frontWallMiddleBottom = new Rectangle(
                new Point3D(middleStartX, startY ,middleStartZ),
                new Point3D(middleStartX, startY,middleStartZ + directionZ * frontPart),
                new Point3D(middleStartX,startY+directionY * ((high-midWindowHigh)/2), middleStartZ),
                decoColor,wallsMaterial);

        Rectangle frontWallMiddleLeft = new Rectangle(
                new Point3D(middleStartX,startY + directionY * ((high + midWindowHigh)/2),middleStartZ),
                new Point3D(middleStartX,startY + directionY * ((high + midWindowHigh)/2),middleStartZ + directionZ * ((frontPart-midWindowWidth)/2)),
                new Point3D(middleStartX,startY + directionY * ((high - midWindowHigh)/2),middleStartZ ),
                decoColor,wallsMaterial);

        Rectangle frontWallMiddleRight = new Rectangle(
                new Point3D(middleStartX,startY + directionY * ((high + midWindowHigh)/2),middleStartZ + directionZ * frontPart),
                new Point3D(middleStartX,startY + directionY * ((high + midWindowHigh)/2),middleStartZ + directionZ * ((frontPart+midWindowWidth)/2)),
                new Point3D(middleStartX,startY + directionY * ((high - midWindowHigh)/2),middleStartZ + directionZ *  frontPart),
                decoColor,wallsMaterial);

        Rectangle frontWallMiddleTop = new Rectangle(
                new Point3D(middleStartX,startY + directionY * high,middleStartZ),
                new Point3D(middleStartX,startY + directionY * high,middleStartZ + directionZ * frontPart),
                new Point3D(middleStartX,startY + directionY * ((high+midWindowHigh)/2),middleStartZ ),
                decoColor,wallsMaterial);

        Rectangle frontWallMiddleWindow = new Rectangle(
                new Point3D(middleStartX,startY + directionY * ((high - midWindowHigh)/2),middleStartZ + directionZ * ((frontPart-midWindowWidth)/2)),
                new Point3D(middleStartX,startY + directionY * ((high + midWindowHigh)/2),middleStartZ + directionZ * ((frontPart-midWindowWidth)/2)),
                new Point3D(middleStartX,startY + directionY * ((high - midWindowHigh)/2),middleStartZ + directionZ * ((frontPart+midWindowWidth)/2)),
                windowColor,windowMaterial);


        double lastStartZ = startZ + directionZ * frontPart * 2;
        Rectangle frontWallRightBottom = new Rectangle(
                new Point3D(startX, startY ,lastStartZ),
                new Point3D(startX, startY,lastStartZ + directionZ * frontPart),
                new Point3D(startX,startY+directionY * ((high-windowHigh)/2), lastStartZ),
                wallsColor,wallsMaterial);
        Rectangle frontWallRightLeft = new Rectangle(
                new Point3D(startX,startY + directionY * ((high + windowHigh)/2),lastStartZ),
                new Point3D(startX,startY + directionY * ((high + windowHigh)/2),lastStartZ + directionZ * ((frontPart-windowWidth)/2)),
                new Point3D(startX,startY + directionY * ((high - windowHigh)/2),lastStartZ ),
                wallsColor,wallsMaterial);

        Rectangle frontWallRightRight = new Rectangle(
                new Point3D(startX,startY + directionY * ((high + windowHigh)/2),lastStartZ + directionZ * frontPart),
                new Point3D(startX,startY + directionY * ((high + windowHigh)/2),lastStartZ + directionZ * ((frontPart+windowWidth)/2)),
                new Point3D(startX,startY + directionY * ((high - windowHigh)/2),lastStartZ + directionZ *  frontPart),
                wallsColor,wallsMaterial);

        Rectangle frontWallRightTop = new Rectangle(
                new Point3D(startX,startY + directionY * high,lastStartZ),
                new Point3D(startX,startY + directionY * high,lastStartZ + directionZ * frontPart),
                new Point3D(startX,startY + directionY * ((high+windowHigh)/2),lastStartZ ),
                wallsColor,wallsMaterial);

        Rectangle frontWallRightWindow = new Rectangle(
                new Point3D(startX,startY + directionY * ((high - windowHigh)/2),lastStartZ + directionZ * ((frontPart-windowWidth)/2)),
                new Point3D(startX,startY + directionY * ((high + windowHigh)/2),lastStartZ + directionZ * ((frontPart-windowWidth)/2)),
                new Point3D(startX,startY + directionY * ((high - windowHigh)/2),lastStartZ + directionZ * ((frontPart+windowWidth)/2)),
                windowColor,windowMaterial);


        Rectangle floor = new Rectangle(
                new Point3D(startX,startY,startZ),
                new Point3D(startX,startY, startZ + directionZ * length),
                new Point3D(startX + directionX *  width,startY,startZ),
                wallsColor,wallsMaterial);

        geometries.addGeometry(backWall);
        geometries.addGeometry(side1Wall);
        geometries.addGeometry(side2Wall);
        geometries.addGeometry(frontWallLeftBottom);
        geometries.addGeometry(frontWallLeftLeft);
        geometries.addGeometry(frontWallLeftRight);
        geometries.addGeometry(frontWallLeftTop);
        geometries.addGeometry(frontWallLeftWindow);

        geometries.addGeometry(frontWallMiddleBottom);
        geometries.addGeometry(frontWallMiddleLeft);
        geometries.addGeometry(frontWallMiddleRight);
        geometries.addGeometry(frontWallMiddleTop);
        geometries.addGeometry(frontWallMiddleWindow);
        geometries.addGeometry(frontWallMiddleSide1);
        geometries.addGeometry(frontWallMiddleSide2);

        geometries.addGeometry(frontWallRightBottom);
        geometries.addGeometry(frontWallRightLeft);
        geometries.addGeometry(frontWallRightRight);
        geometries.addGeometry(frontWallRightTop);
        geometries.addGeometry(frontWallRightWindow);

        geometries.addGeometry(floor);

        PointLight p_light = new PointLight(
                new Color(255,240,240),
                new Point3D(startX + directionX * (width/2),startY + directionY * (high + 2),startZ + directionZ * (length/2)),1,0.001,0.001);
        if (light) {
            lightSources.add(p_light);
        }

    }

    private void buildGroundFloor(Point3D startPoint,double width, double high,double decorateHigh, double length, Vector direction,
                                  Geometries geometries, Boolean light, List<LightSource> lightSources
                                    , Color decoColor,Color wallsColor){

        Material wallsMaterial = new Material(1,1,0,0,2);
        Color windowColor= new Color(5,5,5);
        Material windowMaterial = new Material(2,3,0,0.9,2);

        double startZ = startPoint.getZ().getCoord();
        double startX = startPoint.getX().getCoord();
        double startY = startPoint.getY().getCoord();

        double directionX = direction.getHead().getX().getCoord();
        double directionY = direction.getHead().getY().getCoord();
        double directionZ = direction.getHead().getZ().getCoord();

        double frontPart = length/3;
        double windowHigh = high * 3/4;
        double windowWidth = frontPart * 3/4;
        high += decorateHigh;
        Rectangle backWallBottom = new Rectangle(
                new Point3D(startX + directionX * width,startY,startZ),
                new Point3D(startX + directionX * width , startY, startZ + directionZ * length),
                new Point3D(startX + directionX * width ,startY + directionY * decorateHigh, startZ),
                decoColor,wallsMaterial);

        Rectangle side1WallBottom = new Rectangle(
                new Point3D(startX ,startY,startZ),
                new Point3D(startX + directionX *  width ,startY,startZ),
                new Point3D(startX, startY + directionY * decorateHigh ,startZ),
                decoColor,wallsMaterial);

        Rectangle side2WallBottom = new Rectangle(
                new Point3D(startX ,startY,startZ + directionZ * length),
                new Point3D(startX + directionX * width ,startY,startZ + directionZ *  length),
                new Point3D(startX, startY + directionY * decorateHigh ,startZ + directionZ * length),
                decoColor,wallsMaterial);

        Rectangle frontWallLeftBottomDeco = new Rectangle(
                new Point3D(startX , startY,startZ),
                new Point3D(startX, startY,startZ + directionZ *  frontPart),
                new Point3D(startX, startY + directionY * decorateHigh ,startZ ),
                decoColor,wallsMaterial);

        Rectangle frontWallRightBottomDeco = new Rectangle(
                new Point3D(startX , startY,startZ + directionZ *  frontPart * 2),
                new Point3D(startX, startY,startZ + directionZ * length),
                new Point3D(startX, startY + directionY * decorateHigh ,startZ + directionZ *  frontPart * 2),
                decoColor,wallsMaterial);

        double groundStartY = startY + directionY * decorateHigh;
        Rectangle backWall = new Rectangle(new Point3D(startX + directionX * width,groundStartY,startZ),
                new Point3D(startX + directionX * width , groundStartY, startZ + directionZ * length),
                new Point3D(startX + directionX * width ,groundStartY + directionY * high, startZ),
                wallsColor,wallsMaterial);

        Rectangle side1Wall = new Rectangle(
                new Point3D(startX ,groundStartY,startZ),
                new Point3D(startX + directionX *  width ,groundStartY,startZ),
                new Point3D(startX, groundStartY + directionY * high ,startZ),
                wallsColor,wallsMaterial);

        Rectangle side2Wall = new Rectangle(
                new Point3D(startX ,groundStartY,startZ + directionZ * length),
                new Point3D(startX + directionX * width,groundStartY,startZ + directionZ *  length),
                new Point3D(startX, groundStartY + directionY * high ,startZ + directionZ * length),
                wallsColor,wallsMaterial);


        Rectangle frontWallLeftBottom = new Rectangle(
                new Point3D(startX, groundStartY ,startZ),
                new Point3D(startX, groundStartY,startZ + directionZ * frontPart),
                new Point3D(startX,groundStartY + directionY * ((high-windowHigh)/2), startZ),
                wallsColor,wallsMaterial);
        Rectangle frontWallLeftLeft = new Rectangle(
                new Point3D(startX,groundStartY + directionY * ((high + windowHigh)/2),startZ),
                new Point3D(startX,groundStartY + directionY * ((high + windowHigh)/2),startZ + directionZ * ((frontPart-windowWidth)/2)),
                new Point3D(startX,groundStartY + directionY * ((high - windowHigh)/2),startZ ),
                wallsColor,wallsMaterial);

        Rectangle frontWallLeftRight = new Rectangle(
                new Point3D(startX,groundStartY + directionY * ((high + windowHigh)/2),startZ + directionZ * frontPart),
                new Point3D(startX,groundStartY + directionY * ((high + windowHigh)/2),startZ + directionZ * ((frontPart+windowWidth)/2)),
                new Point3D(startX,groundStartY + directionY * ((high - windowHigh)/2),startZ + directionZ *  frontPart),
                wallsColor,wallsMaterial);

        Rectangle frontWallLeftTop = new Rectangle(
                new Point3D(startX,groundStartY + directionY * high,startZ),
                new Point3D(startX,groundStartY + directionY * high,startZ + directionZ * frontPart),
                new Point3D(startX,groundStartY + directionY * ((high+windowHigh)/2),startZ ),
                wallsColor,wallsMaterial);

        Rectangle frontWallLeftWindow = new Rectangle(
                new Point3D(startX,groundStartY + directionY * ((high - windowHigh)/2),startZ + directionZ * ((frontPart-windowWidth)/2)),
                new Point3D(startX,groundStartY + directionY * ((high + windowHigh)/2),startZ + directionZ * ((frontPart-windowWidth)/2)),
                new Point3D(startX,groundStartY + directionY * ((high - windowHigh)/2),startZ + directionZ * ((frontPart+windowWidth)/2)),
                windowColor,windowMaterial);

        double middleStartZ = startZ + directionZ * frontPart;
        double middleStartX = startX + -1 * directionX * (length/20);
        //here it is the door
        double midWindowHigh = frontPart * 19/20;
        double midWindowWidth = frontPart * 7/10;

        Rectangle frontWallMiddleSide1 = new Rectangle(
                new Point3D(startX, startY ,middleStartZ),
                new Point3D(startX, groundStartY + directionY * high,middleStartZ),
                new Point3D(middleStartX,startY, middleStartZ),
                decoColor,wallsMaterial);
        Rectangle frontWallMiddleSide2 = new Rectangle(
                new Point3D(startX, startY ,middleStartZ + directionZ *  frontPart ),
                new Point3D(startX, groundStartY + directionY * high,middleStartZ + directionZ *  frontPart),
                new Point3D(middleStartX,startY, middleStartZ + directionZ *  frontPart),
                decoColor,wallsMaterial);

        Rectangle frontWallMiddleLeft = new Rectangle(
                new Point3D(middleStartX,startY ,middleStartZ),
                new Point3D(middleStartX,startY + directionY  * midWindowHigh,middleStartZ ),
                new Point3D(middleStartX,startY ,middleStartZ + directionZ * ((frontPart-midWindowWidth)/2) ),
                decoColor,wallsMaterial);

        Rectangle frontWallMiddleRight = new Rectangle(
                new Point3D(middleStartX,startY ,middleStartZ + directionZ * frontPart),
                new Point3D(middleStartX,startY + directionY * midWindowHigh,middleStartZ + directionZ *  frontPart),
                new Point3D(middleStartX,startY ,middleStartZ + directionZ * ((frontPart+midWindowWidth)/2)),
                decoColor,wallsMaterial);

        Rectangle frontWallMiddleTop = new Rectangle(
                new Point3D(middleStartX,startY + directionY * high , middleStartZ),
                new Point3D(middleStartX,startY + directionY * high,middleStartZ + directionZ * frontPart),
                new Point3D(middleStartX,startY + directionY * midWindowHigh,middleStartZ ),
                decoColor,wallsMaterial);

        Rectangle frontWallMiddleWindow = new Rectangle(
                new Point3D(middleStartX,startY ,middleStartZ + directionZ * ((frontPart-midWindowWidth)/2)),
                new Point3D(middleStartX,startY + directionY * midWindowHigh,middleStartZ + directionZ * ((frontPart-midWindowWidth)/2)),
                new Point3D(middleStartX,startY ,middleStartZ + directionZ * ((frontPart+midWindowWidth)/2)),
                windowColor,windowMaterial);


        double lastStartZ = startZ + directionZ * frontPart * 2;
        Rectangle frontWallRightBottom = new Rectangle(
                new Point3D(startX, groundStartY ,lastStartZ),
                new Point3D(startX, groundStartY,lastStartZ + directionZ * frontPart),
                new Point3D(startX,groundStartY+directionY * ((high-windowHigh)/2), lastStartZ),
                wallsColor,wallsMaterial);
        Rectangle frontWallRightLeft = new Rectangle(
                new Point3D(startX,groundStartY + directionY * ((high + windowHigh)/2),lastStartZ),
                new Point3D(startX,groundStartY + directionY * ((high + windowHigh)/2),lastStartZ + directionZ * ((frontPart-windowWidth)/2)),
                new Point3D(startX,groundStartY + directionY * ((high - windowHigh)/2),lastStartZ ),
                wallsColor,wallsMaterial);

        Rectangle frontWallRightRight = new Rectangle(
                new Point3D(startX,groundStartY + directionY * ((high + windowHigh)/2),lastStartZ + directionZ * frontPart),
                new Point3D(startX,groundStartY + directionY * ((high + windowHigh)/2),lastStartZ + directionZ * ((frontPart+windowWidth)/2)),
                new Point3D(startX,groundStartY + directionY * ((high - windowHigh)/2),lastStartZ + directionZ *  frontPart),
                wallsColor,wallsMaterial);

        Rectangle frontWallRightTop = new Rectangle(
                new Point3D(startX,groundStartY + directionY * high,lastStartZ),
                new Point3D(startX,groundStartY + directionY * high,lastStartZ + directionZ * frontPart),
                new Point3D(startX,groundStartY + directionY * ((high+windowHigh)/2),lastStartZ ),
                wallsColor,wallsMaterial);

        Rectangle frontWallRightWindow = new Rectangle(
                new Point3D(startX,groundStartY + directionY * ((high - windowHigh)/2),lastStartZ + directionZ * ((frontPart-windowWidth)/2)),
                new Point3D(startX,groundStartY + directionY * ((high + windowHigh)/2),lastStartZ + directionZ * ((frontPart-windowWidth)/2)),
                new Point3D(startX,groundStartY + directionY * ((high - windowHigh)/2),lastStartZ + directionZ * ((frontPart+windowWidth)/2)),
                windowColor,windowMaterial);


        Rectangle floor = new Rectangle(
                new Point3D(startX,groundStartY,startZ),
                new Point3D(startX,groundStartY, startZ + directionZ * length),
                new Point3D(startX + directionX *  width,groundStartY,startZ),
                wallsColor,wallsMaterial);

        geometries.addGeometry(backWallBottom);
        geometries.addGeometry(side1WallBottom);
        geometries.addGeometry(side2WallBottom);
        geometries.addGeometry(frontWallLeftBottomDeco);
        geometries.addGeometry(frontWallRightBottomDeco);

        geometries.addGeometry(backWall);
        geometries.addGeometry(side1Wall);
        geometries.addGeometry(side2Wall);

        geometries.addGeometry(frontWallLeftBottom);
        geometries.addGeometry(frontWallLeftLeft);
        geometries.addGeometry(frontWallLeftRight);
        geometries.addGeometry(frontWallLeftTop);
        geometries.addGeometry(frontWallLeftWindow);

        geometries.addGeometry(frontWallMiddleLeft);
        geometries.addGeometry(frontWallMiddleRight);
        geometries.addGeometry(frontWallMiddleTop);
        geometries.addGeometry(frontWallMiddleWindow);
        geometries.addGeometry(frontWallMiddleSide1);
        geometries.addGeometry(frontWallMiddleSide2);

        geometries.addGeometry(frontWallRightBottom);
        geometries.addGeometry(frontWallRightLeft);
        geometries.addGeometry(frontWallRightRight);
        geometries.addGeometry(frontWallRightTop);
        geometries.addGeometry(frontWallRightWindow);

        geometries.addGeometry(floor);

        PointLight p_light = new PointLight(
                new Color(255,240,240),
                new Point3D(startX + directionX * (width/2),groundStartY + directionY * (high + 2),startZ + directionZ * (length/2)),1,0.001,0.001);
        if (light) {
            lightSources.add(p_light);
        }

    }

    private void buildTopOfBuilding(Point3D startPoint,double width, double high, double length, Vector direction,
                                    Geometries geometries, List<LightSource> lightSources,
                                    Color decoColor,Color wallsColor){

        Material wallsMaterial = new Material(1,1,0,0,2);


        double startZ = startPoint.getZ().getCoord();
        double startX = startPoint.getX().getCoord();
        double startY = startPoint.getY().getCoord();

        double directionX = direction.getHead().getX().getCoord();
        double directionY = direction.getHead().getY().getCoord();
        double directionZ = direction.getHead().getZ().getCoord();

        double frontPart = length/3;
        double decorateHigh = high/2;

        Rectangle bottomTopDeco = new Rectangle(
                new Point3D(startX ,startY,startZ),
                new Point3D(startX  , startY, startZ + directionZ * length),
                new Point3D(startX + directionX * width ,startY, startZ),
                wallsColor,wallsMaterial);

        Rectangle backWallTopDeco = new Rectangle(
                new Point3D(startX + directionX * width,startY,startZ),
                new Point3D(startX + directionX * width , startY, startZ + directionZ * length),
                new Point3D(startX + directionX * width ,startY + directionY * decorateHigh, startZ),
                decoColor,wallsMaterial);

        Rectangle side1WallTopDeco = new Rectangle(
                new Point3D(startX ,startY,startZ),
                new Point3D(startX + directionX *  width ,startY,startZ),
                new Point3D(startX, startY + directionY * decorateHigh ,startZ),
                decoColor,wallsMaterial);

        Rectangle side2WallTopDeco = new Rectangle(
                new Point3D(startX ,startY,startZ + directionZ * length),
                new Point3D(startX + directionX * width ,startY,startZ + directionZ *  length),
                new Point3D(startX, startY + directionY * decorateHigh ,startZ + directionZ * length),
                decoColor,wallsMaterial);

        Rectangle frontWallLeftTopDeco = new Rectangle(
                new Point3D(startX , startY,startZ),
                new Point3D(startX, startY,startZ + directionZ *  frontPart),
                new Point3D(startX, startY + directionY * decorateHigh ,startZ ),
                decoColor,wallsMaterial);

        Rectangle frontWallRightTopDeco = new Rectangle(
                new Point3D(startX , startY,startZ + directionZ *  frontPart * 2),
                new Point3D(startX, startY,startZ + directionZ * length),
                new Point3D(startX, startY + directionY * decorateHigh ,startZ + directionZ *  frontPart * 2),
                decoColor,wallsMaterial);


        double middleStartZ = startZ + directionZ * frontPart;
        double middleStartX = startX + -1 * directionX * (length/20);

        Rectangle frontWallMiddleSide1 = new Rectangle(
                new Point3D(startX, startY ,middleStartZ),
                new Point3D(startX, startY + directionY * decorateHigh ,middleStartZ),
                new Point3D(middleStartX,startY, middleStartZ),
                decoColor,wallsMaterial);

        Rectangle frontWallMiddleSide2 = new Rectangle(
                new Point3D(startX, startY ,middleStartZ + directionZ *  frontPart ),
                new Point3D(startX, startY + directionY * decorateHigh,middleStartZ + directionZ *  frontPart),
                new Point3D(middleStartX,startY, middleStartZ + directionZ *  frontPart),
                decoColor,wallsMaterial);

        Rectangle frontWallMiddle = new Rectangle(
                new Point3D(middleStartX,startY + directionY * decorateHigh , middleStartZ),
                new Point3D(middleStartX,startY + directionY * decorateHigh,middleStartZ + directionZ * frontPart),
                new Point3D(middleStartX,startY ,middleStartZ),
                decoColor,wallsMaterial);
        geometries.addGeometry(bottomTopDeco);
        geometries.addGeometry(backWallTopDeco);
        geometries.addGeometry(side1WallTopDeco);
        geometries.addGeometry(side2WallTopDeco);
        geometries.addGeometry(frontWallLeftTopDeco);
        geometries.addGeometry(frontWallRightTopDeco);
        geometries.addGeometry(frontWallMiddle);

        geometries.addGeometry(frontWallMiddleSide1);
        geometries.addGeometry(frontWallMiddleSide2);

        double zizOffset =decorateHigh/2;
        startY = startY + directionY * decorateHigh;
        startX = startX + directionX * (-zizOffset);
        startZ = startZ + directionZ * (-zizOffset);
        width = width + zizOffset * 2;
        length = length + zizOffset * 2;
        frontPart = length/3;
        Rectangle backWallTopDecoZiz = new Rectangle(
                new Point3D(startX + directionX * width,startY,startZ),
                new Point3D(startX + directionX * width , startY, startZ + directionZ * length),
                new Point3D(startX + directionX * width ,startY + directionY * decorateHigh, startZ),
                wallsColor,wallsMaterial);

        Rectangle side1WallTopDecoZiz = new Rectangle(
                new Point3D(startX ,startY,startZ),
                new Point3D(startX + directionX *  width ,startY,startZ),
                new Point3D(startX, startY + directionY * decorateHigh ,startZ),
                wallsColor,wallsMaterial);

        Rectangle side2WallTopDecoZiz = new Rectangle(
                new Point3D(startX ,startY,startZ + directionZ * length),
                new Point3D(startX + directionX * width ,startY,startZ + directionZ *  length),
                new Point3D(startX, startY + directionY * decorateHigh ,startZ + directionZ * length),
                wallsColor,wallsMaterial);

        Rectangle frontWallLeftTopDecoZiz = new Rectangle(
                new Point3D(startX , startY,startZ),
                new Point3D(startX, startY,startZ + directionZ *  frontPart),
                new Point3D(startX, startY + directionY * decorateHigh ,startZ ),
                wallsColor,wallsMaterial);

        Rectangle frontWallRightTopDecoZiz = new Rectangle(
                new Point3D(startX , startY,startZ + directionZ *  frontPart * 2),
                new Point3D(startX, startY,startZ + directionZ * length),
                new Point3D(startX, startY + directionY * decorateHigh ,startZ + directionZ *  frontPart * 2),
                wallsColor,wallsMaterial);


        middleStartZ = startZ + directionZ * frontPart;
        middleStartX = startX + -1 * directionX * (length/20);

        Rectangle frontWallMiddleSide1Ziz = new Rectangle(
                new Point3D(startX, startY ,middleStartZ),
                new Point3D(startX, startY + directionY * decorateHigh ,middleStartZ),
                new Point3D(middleStartX,startY, middleStartZ),
                wallsColor,wallsMaterial);

        Rectangle frontWallMiddleSide2Ziz = new Rectangle(
                new Point3D(startX, startY ,middleStartZ + directionZ *  frontPart ),
                new Point3D(startX, startY + directionY * decorateHigh,middleStartZ + directionZ *  frontPart),
                new Point3D(middleStartX,startY, middleStartZ + directionZ *  frontPart),
                wallsColor,wallsMaterial);

        Rectangle frontWallMiddleZiz = new Rectangle(
                new Point3D(middleStartX,startY + directionY * decorateHigh , middleStartZ),
                new Point3D(middleStartX,startY + directionY * decorateHigh,middleStartZ + directionZ * frontPart),
                new Point3D(middleStartX,startY ,middleStartZ),
                wallsColor,wallsMaterial);

        geometries.addGeometry(backWallTopDecoZiz);
        geometries.addGeometry(side1WallTopDecoZiz);
        geometries.addGeometry(side2WallTopDecoZiz);
        geometries.addGeometry(frontWallLeftTopDecoZiz);
        geometries.addGeometry(frontWallRightTopDecoZiz);
        geometries.addGeometry(frontWallMiddleZiz);

        geometries.addGeometry(frontWallMiddleSide1Ziz);
        geometries.addGeometry(frontWallMiddleSide2Ziz);

        Rectangle topWallTopDeco = new Rectangle(
                new Point3D(startX ,startY + directionY * decorateHigh,startZ),
                new Point3D(startX  , startY + directionY * decorateHigh, startZ + directionZ * length),
                new Point3D(startX + directionX * width ,startY + directionY * decorateHigh, startZ),
                wallsColor,wallsMaterial);
        Rectangle topWallTopDecoZiz = new Rectangle(
                new Point3D(middleStartX ,startY + directionY * decorateHigh,middleStartZ),
                new Point3D(middleStartX  , startY + directionY * decorateHigh, middleStartZ + directionZ *  frontPart),
                new Point3D(startX ,startY + directionY * decorateHigh, middleStartZ),
                wallsColor,wallsMaterial);
        geometries.addGeometry(topWallTopDeco);
        geometries.addGeometry(topWallTopDecoZiz);


    }



    protected void buildTree(Point3D startPoint,double width,double high,Geometries geometries){

        double startX = startPoint.getX().getCoord();
        double startY = startPoint.getY().getCoord();
        double startZ = startPoint.getZ().getCoord();

        Rectangle base = new Rectangle(
                new Point3D(startX + ((width-width/4)/2),startY,startZ+width/2),
                new Point3D(startX + ((width-width/4)/2),startY-high/6,startZ+width/2),
                new Point3D(startX + ((width+width/4)/2),startY,startZ+width/2),new Color(83, 49, 24),new Material(1,1,0,0,2));
        geometries.addGeometry(base);
        Color leafC = new Color(11, 20, 0);
        Material leafM = new Material(1,1,0,0,2);
        double nwidth = width/2;
        double moveZ = 0;
        double accMidHigh = 4 * high / 6;
        for(int i = 1; i<5; ++i) {
            Point3D middlePoint = new Point3D(startX + width / 2, startY - accMidHigh, startZ + width / 2);
            accMidHigh = accMidHigh + (high/(6*i*i*i));

            Vector v1 = new Vector(-2, 0, 1).normalize().scale(nwidth);
            Vector v2 = new Vector(0, 0, 1).scale(nwidth );
            Vector v3 = new Vector(2, 0, 1).normalize().scale(nwidth);
            Vector v4 = v1.scale(-1);
            Vector v5 = v2.scale(-1);
            Vector v6 = v3.scale(-1);

            Point3D sPoint = new Point3D(startX +  width / 2 , startY - (high *(i)) /6, startZ + moveZ);
            Point3D nextPoint = sPoint.addVector(v1);
            Triangle t1 = new Triangle(new Point3D(sPoint),
                    new Point3D(middlePoint),
                    new Point3D(nextPoint), leafC, leafM);
            moveZ =+ (nwidth-(nwidth * 0.85));

            nwidth = nwidth * 0.85 ;
            sPoint = nextPoint;
            nextPoint = nextPoint.addVector(v2);
            Triangle t2 = new Triangle(new Point3D(sPoint),
                    new Point3D(middlePoint),
                    new Point3D(nextPoint), leafC, leafM);

            sPoint = nextPoint;
            nextPoint = nextPoint.addVector(v3);
            Triangle t3 = new Triangle(new Point3D(sPoint),
                    new Point3D(middlePoint),
                    new Point3D(nextPoint), leafC, leafM);

            sPoint = nextPoint;
            nextPoint = nextPoint.addVector(v4);
            Triangle t4 = new Triangle(new Point3D(sPoint),
                    new Point3D(middlePoint),
                    new Point3D(nextPoint), leafC, leafM);

            sPoint = nextPoint;
            nextPoint = nextPoint.addVector(v5);
            Triangle t5 = new Triangle(new Point3D(sPoint),
                    new Point3D(middlePoint),
                    new Point3D(nextPoint), leafC, leafM);

            sPoint = nextPoint;
            nextPoint = nextPoint.addVector(v6);
            Triangle t6 = new Triangle(new Point3D(sPoint),
                    new Point3D(middlePoint),
                    new Point3D(nextPoint), leafC, leafM);

            geometries.addGeometry(t1);
            geometries.addGeometry(t2);
            geometries.addGeometry(t3);
            geometries.addGeometry(t4);
            geometries.addGeometry(t5);
            geometries.addGeometry(t6);
        }


    }

}
    