/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playground;

import javax.swing.JFrame;
import java.awt.Graphics;
import java.lang.Math;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dmikkelsen
 */
public class Playground extends JFrame {

    int screenSizeX;
    int screenSizeY;
    int screenCenterY;
    int screenCenterX;

    public Playground() {
        screenSizeX = 1200;
        screenSizeY = 1000;
        screenCenterX = screenSizeX / 2;
        screenCenterY = screenSizeY / 2;

        setTitle("test");
        setSize(screenSizeX, screenSizeY);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void drawCircles(Graphics g, int Steps, int subCircles, int widerRadius, int circleSize) {
        drawCircles(g, Steps, subCircles, 100, 100, widerRadius, widerRadius, circleSize);
    }

    public void drawCircles(Graphics g, int Steps, int subCircles, int widerRadiusX, int widerRadiusY, int circleSize) {
        drawCircles(g, Steps, subCircles, 100, 100, widerRadiusX, widerRadiusY, circleSize);
    }

    public void drawCircles(Graphics g, int Steps, int subCircles, int innerRadiusX, int innerRadiusY, int widerRadiusX, int widerRadiusY, int circleSize) {
        double StepSize = (double) 360 / Steps;
        double RadConv = (double) Math.PI / 180;
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, screenSizeX - 1, screenSizeY - 1);
        g.setColor(Color.RED);
        for (int i = 0; i < Steps; i++) {
            try {
                Thread.sleep(2000 / Steps);
                int y = (screenSizeY / 2) + (int) (innerRadiusX * Math.sin(i * StepSize * RadConv));
                int x = (screenSizeX / 2) + (int) (innerRadiusY * Math.cos(i * StepSize * RadConv));

//                g.drawLine(prevX,PrevY, x,y);
                double SubStepSize = (double) 360 / subCircles;

                for (int j = 0; j < subCircles; j++) {
                    g.setColor(new Color(
                            (int) Math.abs(255 - (j * ((double) (255 * 2) / subCircles))),
                            (int) Math.abs(255 - i * ((double) (255 * 2) / Steps)),
                            (int) Math.abs(255 - i * ((double) (255 * 2) / Steps)))
                    );

                    g.drawOval(
                            x + (int) (widerRadiusX * Math.cos(j * SubStepSize * RadConv)),
                            y + (int) (widerRadiusY * Math.sin(j * SubStepSize * RadConv)),
                            circleSize,
                            circleSize
                    );
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Playground.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void drawTubes(Graphics g, int Steps, int subCircles, int innerRadiusX, int innerRadiusY, int widerRadiusX, int widerRadiusY, int circleSize) {
        double StepSize = (double) 360 / Steps;
        double RadConv = (double) Math.PI / 180;
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, screenSizeX - 1, screenSizeY - 1);
        g.setColor(Color.RED);
        for (int i = 0; i < Steps; i++) {
            try {
                Thread.sleep(2000 / Steps);
                int y = (screenSizeY / 2) + (int) (innerRadiusX * Math.sin(i * StepSize * RadConv));
                int x = (screenSizeX / 2) + (int) (innerRadiusY * Math.cos(i * StepSize * RadConv));

//                g.drawLine(prevX,PrevY, x,y);
                double SubStepSize = (double) 360 / subCircles;

                for (int j = 0; j < subCircles; j++) {
                    g.setColor(new Color(
                            (int) Math.abs(255 - (j * ((double) (255 * 2) / subCircles))),
                            255 - (int) Math.abs(255 - i * ((double) (255 * 2) / Steps)),
                            (int) Math.abs(255 - i * ((double) (255 * 2) / Steps)))
                    );

                    int plotX = x + (int) (510 * Math.cos(j * RadConv));
                    int plotY = y + (int) (170 * Math.sin(j * SubStepSize * SubStepSize * (RadConv)));

                    g.drawOval(plotX, plotY, circleSize, circleSize);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Playground.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    private class Star {

        public int xPos;
        public int yPos;
        public double zPos;
        public double speed;

        public Star() {
            xPos = (int) ((Math.random() - 0.5) * (screenSizeX));
            yPos = (int) ((Math.random() - 0.5) * (screenSizeY));
            zPos = 1 + Math.random() * 1;
            speed = 0.019; //+ (Math.random() * 0.012);
        }

    }

    public void StarField(Graphics g, int Secs, int numStars) throws InterruptedException {
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, screenSizeX - 1, screenSizeY - 1);
        g.setColor(Color.RED);

        int frames = Secs * 50;
        Star[] starArray = new Star[numStars];
        for (int starIdx = 0; starIdx < numStars; starIdx++) {
            starArray[starIdx] = new Star();
        }

        for (int step = 0; step < frames; step++) {
            for (int starIdx = 0; starIdx < numStars; starIdx++) {
                g.setColor(Color.BLACK);
                g.drawOval((screenSizeX / 2) + (int) (starArray[starIdx].xPos * 1 / starArray[starIdx].zPos),
                        (screenSizeY / 2) + (int) (starArray[starIdx].yPos * 1 / starArray[starIdx].zPos),
                        1,
                        1
                );
                g.setColor(new Color(50, 50, 255));
                starArray[starIdx].zPos = starArray[starIdx].zPos - starArray[starIdx].speed;
                if (starArray[starIdx].zPos < 0) {
                    starArray[starIdx] = new Star();
                }
                g.drawOval((screenSizeX / 2) + (int) (starArray[starIdx].xPos * 1 / starArray[starIdx].zPos),
                        (screenSizeY / 2) + (int) (starArray[starIdx].yPos * 1 / starArray[starIdx].zPos),
                        1,
                        1
                );

            }
            Thread.sleep(20);
        }
    }
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    class tPoint {

        public double posX;
        public double posY;
        public double posZ;
        public Color color;

        public tPoint(double posX, double posY, double posZ, Color color) {
            this.posX = posX;
            this.posY = posY;
            this.posZ = posZ;
            this.color = color;
        }

    }

    double RadConv = (double) Math.PI / 180;

    class tCircle {

        public tPoint[] points;

        double circleStepSize;
        int numPoints;

        public tCircle(int numCirclePoints, int xRadius, int yRadius) {
            numPoints = numCirclePoints;
            points = new tPoint[numCirclePoints];
            circleStepSize = (double) 360 / numCirclePoints;
            double colorStep = (255 * 2) / numCirclePoints;

            for (int circlePointIdx = 0; circlePointIdx < numCirclePoints; circlePointIdx++) {
                double posX = (xRadius * Math.cos(Math.toRadians(circlePointIdx * circleStepSize)));
                double posY = (yRadius * Math.sin(Math.toRadians(circlePointIdx * circleStepSize)));

                Color pointColor = new Color(100, (int) Math.abs((colorStep * circlePointIdx) - 255), (int) Math.abs((colorStep * circlePointIdx) - 255));
                points[circlePointIdx] = new tPoint(posX, posY, 1, pointColor);
            }
        }

        public void drawCircle(Graphics g, int centerX, int centerY, int pointSizeX, int pointSizeY) {
            for (int i = 0; i < numPoints; i++) {
                g.drawOval(centerX + (int) points[i].posX, centerY + (int) points[i].posY, pointSizeX, pointSizeY);
            }
        }

        tPoint RotateZ(tPoint p, double rotateAngleX, int rotatePointX, int rotatePointY) {
            double cos = Math.cos(Math.toRadians(rotateAngleX));
            double sin = Math.sin(Math.toRadians(rotateAngleX));
            tPoint returnPoint = new tPoint(
                    ((p.posX - rotatePointX) * cos - (p.posY - rotatePointY) * sin),
                    ((p.posY - rotatePointY) * cos + (p.posX - rotatePointX) * sin),
                    0,
                    p.color
            );
            if (returnPoint.posZ == 0) {
                returnPoint.posZ = 1;
            }
            return returnPoint;
        }

        tPoint RotateX(tPoint p, double rotateAngleX, int rotatePointY, int rotatePointZ) {
            double cos = Math.cos(Math.toRadians(rotateAngleX));
            double sin = Math.sin(Math.toRadians(rotateAngleX));
            tPoint returnPoint = new tPoint(
                    p.posX,
                    ((p.posY - rotatePointY) * cos - (p.posZ - rotatePointZ) * sin),
                    ((p.posZ - rotatePointZ) * cos + (p.posY - rotatePointY) * sin),
                    p.color
            );

            return returnPoint;
        }

        public void drawCircle(Graphics g, int centerX, int centerY, int pointSizeX, int pointSizeY, double rotateAngleX, double rotateAngleY) {
            int zOffset = 5;
            for (int i = 0; i < numPoints; i++) {
                tPoint p2 = RotateZ(points[i], rotateAngleX, 0, 0);
                tPoint p = RotateX(p2, rotateAngleY, 0, 0);
                System.out.format("Z = %f\n", p.posZ);

                g.setColor(points[i].color);
                g.drawOval(
                        centerX + (int) (p.posX / (1 + (p.posZ / 600))),
                        centerY + (int) (p.posY / (1 + (p.posZ / 600))),
                        //                        centerX + (int)(pointX / (pointZ / zOffset)),
                        //                        centerY + (int)(pointY /(pointZ / zOffset)),
                        pointSizeX,
                        pointSizeY
                );
            }

        }
    };

    public void draw3d(Graphics screen, int numCircles, int numCirclePoints, int innerRadiusX, int innerRadiusY, int widerRadiusX, int widerRadiusY, int pointSize, int tiltY) throws InterruptedException {
        screen.setColor(new Color(0, 0, 0));
        screen.fillRect(0, 0, screenSizeX - 1, screenSizeY - 1);
        screen.setColor(Color.RED);

        BufferedImage image = new BufferedImage(screenSizeX, screenSizeY, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();

        tCircle circle = new tCircle(40, 150, 150); //innerRadiusX, innerRadiusY);
        for (int i = 0; i < 36 * 2; i++) {
            g.setColor(new Color(0, 0, 0));
            g.fillRect(0, 0, screenSizeX - 1, screenSizeY - 1);
            g.setColor(new Color(0, 0, 255));
            circle.drawCircle(g, screenCenterX, screenCenterY, 5, 5, i * 5, i*10);
            screen.drawImage(image, 0, 0, null);
            Thread.sleep(100);
        }
        /*        for (int x = 0; x < 2; x++) {
                double rotateX = (Math.cos((double) i * 10 * RadConv));
                double rotateY = (Math.sin((double) i * 10 * RadConv));
                g.setColor(new Color(0, 0, 0));
                g.fillRect(0, 0, screenSizeX - 1, screenSizeY - 1);
                circle.drawCircle(g, screenCenterX, screenCenterY, 2, 2, rotateX, rotateY);
                screen.drawImage(image, 0, 0, null);
                Thread.sleep(50);

            }
        }
         */
 /*
    double StepSize = (double) 360 / numCircles;
        double circleStepSize = (double) 360 / numCirclePoints;
        int circleSizeX = 20;
        int circleSizeY = 20;

        BufferedImage image = new BufferedImage(screenSizeX, screenSizeY, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        int numFrames = 1;
        double ZStepSize = 360 / numFrames;
        for (int frameIdx = 0; frameIdx < numFrames; frameIdx++) {
            Thread.sleep(150);
            int zTiltDegrees = 90; // frameIdx * ZStepSize;
            g.setColor(new Color(0, 0, 0));
            g.fillRect(0, 0, screenSizeX - 1, screenSizeY - 1);
            g.setColor(Color.BLUE);
            double tiltZ_sin = Math.sin(zTiltDegrees * RadConv);
            int ringRadiusZ_y = (int) (300 * tiltZ_sin);

            for (int circleIdx = 0; circleIdx < numCircles; circleIdx++) {

                double RingX = Math.sin(circleIdx * StepSize * RadConv);
                int circleCenterX = +(int) (300 * RingX);
                int circleCenterY = +(int) (ringRadiusZ_y * Math.cos(circleIdx * StepSize * RadConv));

//                double tiltZ_cos = Math.cos(((double)360 / ) * RadConv);
                double posZ = 1; //(1 + (tiltZ_cos * 0.5));
                int circleRadiusX = (int) (circleSizeX * posZ);
                int circleRadiusY = (int) (circleSizeY * posZ);
                for (int circlePointIdx = 0; circlePointIdx < numCirclePoints; circlePointIdx++) {

                    double plotZ_Y = Math.sin(circleIdx * StepSize * RadConv);
                    int plotX = screenCenterX + circleCenterX + (int) ((plotZ_Y * circleRadiusX) * Math.cos(circlePointIdx * circleStepSize * RadConv));
                    int plotY = screenCenterY + circleCenterY + (int) (circleRadiusY * Math.sin(circlePointIdx * circleStepSize * RadConv));

                    g.drawOval(plotX, plotY, pointSize, pointSize);
                }

            }
            screen.drawImage(image, 0, 0, null);
        }
         */
    }

// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    public void paint(Graphics g) {
        try {
            /*
            drawCircles(g, 360, 30, 200, 2);
            Thread.sleep(2000);
            drawCircles(g, 160, 20, 100, 2);
            Thread.sleep(2000);
            drawCircles(g, 60, 70, 400, 20);
            Thread.sleep(2000);
            drawCircles(g, 150, 70, 40, 40, 300, 70, 3);
            Thread.sleep(2000);
            drawCircles(g, 150, 70, 40, 40, 300, 70, 3);
            Thread.sleep(2000);
            StarField(g, 5, 400);
            drawTubes(g, 150, 170, 40, 40, 300, 70, 3);
            Thread.sleep(2000);
             */
            draw3d(g, 60, 60, 40, 40, 300, 30, 3, 600);
            Thread.sleep(10);

        } catch (InterruptedException ex) {
            Logger.getLogger(Playground.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void paint2(Graphics g) {
        int Steps = 360;
        double StepSize = 360 / Steps;
        double RadConv = Math.PI / 180;
        g.fillRect(0, 0, 1200, 1200);
        g.setColor(Color.RED);
        int prevX = 600;
        int PrevY = 500;
        for (int i = 0; i < Steps; i++) {
            try {
                Thread.sleep(2000 / Steps);
//                int y = 500 + (int)(100 * Math.sin( Math.toRadians(  i*StepSize)));
//                int x = 500 + (int)(100 * Math.cos(Math.toRadians(  i*StepSize)));
//        g.setColor(new Color(255, (int)(i*((double)255/Steps)), (int)(i*((double)255/Steps))));
                int y = 500 + (int) (100 * Math.sin(i * StepSize * RadConv));
                int x = 500 + (int) (100 * Math.cos(i * StepSize * RadConv));

//                g.drawLine(prevX,PrevY, x,y);
                double SubStepSize = 360 / 30;

                for (int j = 0; j < 30; j++) {
                    g.setColor(new Color((int) Math.abs(255 - (j * ((double) (255 * 2) / 30))), (int) Math.abs(255 - i * ((double) (255 * 2) / Steps)), (int) Math.abs(255 - i * ((double) (255 * 2) / Steps))));
                    g.drawOval(x + (int) (200 * Math.cos(j * SubStepSize * RadConv)), y + (int) (200 * Math.sin(j * SubStepSize * RadConv)), 2, 2);
//                g.drawOval(x + (int)(200 * Math.cos(j)), y + (int)(200 * Math.sin(j)), 2,2);
                }
//                g.drawOval(x+100,y+100, 2,2);
//                g.drawOval(x+200,y+200, 2,2);
                prevX = x;
                PrevY = y;

            } catch (InterruptedException ex) {
                Logger.getLogger(Playground.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Playground x = new Playground();
    }

}
