/*
Copyright (c) 2011, Kyle D. Sweeney
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
    * Neither the name of the <organization> nor the
      names of its contributors may be used to endorse or promote products
      derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package Utilities;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;

/*Copyright 2011 Kyle Dieter Sweeney
 * This file is part of the Sweeney Game Development Environment.

Sweeney Game Development Environment is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Sweeney Game Development Environment is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Sweeney Game Development Environment.  If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * An extension of the Rectangle class. Primary functionality includes new constructors and cloning.
 * 
 * Rotation collision detection adapted from code samples by George W. Clingerman.
 * Online source of code: http://www.xnadevelopment.com/tutorials/rotatedrectanglecollisions/rotatedrectanglecollisions.shtml
 * @author Kyle Maximilian Dieter Sweeney
 */
public class Rect extends Rectangle implements Cloneable, Serializable {

    Vector2 origin;
    float angle;
    Circle colision;
    Vector2 UpperLeftCorner;
    Vector2 UpperRightCorner;
    Vector2 LowerLeftCorner;
    Vector2 LowerRightCorner;

    /**
     * Default constructor for a Rect. Gives a rect with position 0,0, and width and height 0,0
     */
    public Rect() {
        super();
        origin = new Vector2();
        angle = 0;
        colision = new Circle(origin, 0);
        UpperLeftCorner = new Vector2();
        UpperRightCorner = new Vector2();
        LowerLeftCorner = new Vector2();
        LowerRightCorner = new Vector2();
    }

    /**
     * constructs a new Rect out of an X,Y, Width, and Height, and Angle
     * @param x the upper left hand corner X
     * @param y the upper left hand corner Y
     * @param width width of the rectangle
     * @param height height of the rectangle
     * @param angle angle of the rectangle
     */
    public Rect(int x, int y, int width, int height, float angle) {
        super(x, y, width, height);
        this.angle = angle;
        origin = new Vector2(width / 2, height / 2);
        UpperLeftCorner = UpperLeftCorner();
        UpperRightCorner = UpperRightCorner();
        LowerLeftCorner = LowerLeftCorner();
        LowerRightCorner = LowerRightCorner();
        double a = UpperLeftCorner.distance(LowerRightCorner);
        double b = UpperRightCorner.distance(LowerLeftCorner);
        if (a > b) {
            colision = new Circle(origin, a / 2);
        } else {
            colision = new Circle(origin, b / 2);
        }
    }

    /**
     * Constructs a Rectangle out of a Vector2, width, and Height, and angle
     * @param pos upper left hand corner
     * @param width width of the rectangle
     * @param height height of the rectangle
     * @param angle the angle of the rectangle
     */
    public Rect(Vector2 pos, int width, int height, float angle) {
        this((int) pos.getX(), (int) pos.getY(), width, height, angle);
    }

    /**
     * Constructs a Rectangle out of a Point, width, and Height, and angle
     * @param p upper left and corner
     * @param width width of the rectangle
     * @param height height of the rectangle
     * @param angle the angle of the rectangle
     */
    public Rect(Point p, int width, int height, float angle) {
        this(p.x, p.y, width, height, angle);
    }

    /**
     * creates a new rectangle out of an old one
     * @param r old rectangle to be duplicated
     */
    public Rect(Rectangle r) {
        this(r.x, r.y, (int) r.getWidth(), (int) r.getHeight(), 0f);
    }

    /**
     * Constructs a new Rect out of an Old Rect
     * @param r old rect to make the new Rect
     */
    public Rect(Rect r) {
        this(r.x, r.y, (int) r.getWidth(), (int) r.getHeight(), r.angle);
    }

    /**
     * Constructs a new Rect based on a Rectangle and the angle it is rotated at
     * @param r the rectangle
     * @param angle the angle the rectangle is rotated at
     */
    public Rect(Rectangle r, float angle) {
        this(r.x, r.y, (int) r.getHeight(), (int) r.getWidth(), angle);
    }

    /**
     * Makes a new Rectangle based on upper left x, upper left y, a width, and a height, angle at 0
     * @param x upper left corner X value
     * @param y upper left corner Y value
     * @param width width of the rectangle
     * @param height height of the rectangle
     */
    public Rect(int x, int y, int width, int height) {
        this(x, y, width, height, 0f);
    }

    /**
     * Constructs a rectangle out of a Vector2 for the upper left corner, a width, and a height, angle at 0
     * @param pos upper left corner of the Rectangle
     * @param width width of the rectangle
     * @param height height of the rectangle
     */
    public Rect(Vector2 pos, int width, int height) {
        this(pos, width, height, 0);
    }

    /**
     * Constructs a rectangle out of a point for the upper left corner, a width, a height, angle at 0
     * @param p upper left corner
     * @param width width of the rectangle
     * @param height height of the rectangle
     */
    public Rect(Point p, int width, int height) {
        this(p, width, height, 0);
    }

    @Override
    public Rect clone() {
        return new Rect(this);
    }

    private Vector2 rotatedPoint(Vector2 point, Vector2 origin, float theta) {
        Vector2 aTranslatedPoint = new Vector2();
        double ox = origin.getX();
        double px = point.getX();
        double oy = origin.getY();
        double py = point.getY();
        aTranslatedPoint.setX((px - ox) * Math.cos(theta)
                - (py - oy) * Math.sin(theta));
        aTranslatedPoint.setY((py - oy) * Math.cos(theta)
                + (px - ox) * Math.sin(theta));

        aTranslatedPoint.dX(ox);
        aTranslatedPoint.dY(oy);
        return aTranslatedPoint;
    }

    public Vector2 UpperLeftCorner() {
        Vector2 aUpperLeft = new Vector2(this.x, this.y);
        Vector2 other = aUpperLeft.clone();
        other.add(origin);
        aUpperLeft = rotatedPoint(aUpperLeft, other, angle);
        return aUpperLeft;
    }

    public Vector2 UpperRightCorner() {
        Vector2 aUpperRight = new Vector2(this.x + this.width, this.y);
        Vector2 other = aUpperRight.clone();
        other.add(new Vector2(-origin.getX(), origin.getY()));
        aUpperRight = rotatedPoint(aUpperRight, other, angle);
        return aUpperRight;
    }

    public Vector2 LowerLeftCorner() {
        Vector2 aLowerLeft = new Vector2(this.x, this.y + height);
        Vector2 other = aLowerLeft.clone();
        other.add(new Vector2(origin.getX(), -origin.getY()));
        aLowerLeft = rotatedPoint(aLowerLeft, other, angle);
        return aLowerLeft;
    }

    public Vector2 LowerRightCorner() {
        Vector2 aLowerRight = new Vector2(this.x + width, y + height);
        Vector2 other = aLowerRight.clone();
        other.add(new Vector2(-origin.getX(), -origin.getY()));
        aLowerRight = rotatedPoint(aLowerRight, other, angle);
        return aLowerRight;
    }

    private int GenerateScalar(Vector2 theRectangleCorner, Vector2 theAxis) {
        //Using the formula for Vector projection. Take the corner being passed in
        //and project it onto the given Axis
        double RCX = theRectangleCorner.getX();
        double RCY = theRectangleCorner.getY();
        double AX = theAxis.getX();
        double AY = theAxis.getY();

        float aNumerator = (float) ((RCX * AX) + (RCY * AY));
        float aDenominator = (float) ((AX * AY) + (AY * AY));
        float aDivisionResult = aNumerator / aDenominator;
        Vector2 aCornerProjected = new Vector2(aDivisionResult * AX, aDivisionResult * AY);

        //Now that we have our projected Vector, calculate a scalar of that projection
        //that can be used to more easily do comparisons
        float aScalar = (float) ((AX * aCornerProjected.getX()) + (AY * aCornerProjected.getY()));
        return (int) aScalar;
    }

    @Override
    public boolean intersects(Rectangle r) {
        return intersects(new Rect(r, 0f));
    }

    public boolean intersects(Rect theRectangle) {
        if (this.angle == 0 && theRectangle.angle == 0) {
            return super.intersects(theRectangle);
        }

        //Calculate the Axis we will use to determine if a collision has occurred
        //Since the objects are rectangles, we only have to generate 4 Axis (2 for
        //each rectangle) since we know the other 2 on a rectangle are parallel.
        ArrayList<Vector2> aRectangleAxis = new ArrayList<Vector2>();

        Vector2 ur = UpperRightCorner.clone();
        ur.subtract(UpperLeftCorner);
        aRectangleAxis.add(ur);

        Vector2 ura = UpperRightCorner.clone();
        ura.subtract(LowerRightCorner);
        aRectangleAxis.add(ura);

        Vector2 j = theRectangle.UpperLeftCorner.clone();
        j.subtract(theRectangle.LowerLeftCorner.clone());
        aRectangleAxis.add(j);

        Vector2 i = theRectangle.UpperLeftCorner.clone();
        i.subtract(theRectangle.UpperRightCorner.clone());
        aRectangleAxis.add(i);

        //Cycle through all of the Axis we need to check. If a collision does not occur
        //on ALL of the Axis, then a collision is NOT occurring. We can then exit out 
        //immediately and notify the calling function that no collision was detected. If
        //a collision DOES occur on ALL of the Axis, then there is a collision occurring
        //between the rotated rectangles. We know this to be true by the Seperating Axis Theorem
        for (Vector2 aAxis : aRectangleAxis) {
            if (!IsAxisCollision(theRectangle, aAxis)) {
                return false;
            }
        }

        return true;
    }

    /// <summary>
    /// Determines if a collision has occurred on an Axis of one of the
    /// planes parallel to the Rectangle
    /// </summary>
    /// <param name="theRectangle"></param>
    /// <param name="aAxis"></param>
    /// <returns></returns>
    private boolean IsAxisCollision(Rect theRectangle, Vector2 aAxis) {
        //Project the corners of the Rectangle we are checking on to the Axis and
        //get a scalar value of that project we can then use for comparison
        ArrayList<Integer> aRectangleAScalars = new ArrayList<Integer>();
        aRectangleAScalars.add(GenerateScalar(theRectangle.UpperLeftCorner, aAxis));
        aRectangleAScalars.add(GenerateScalar(theRectangle.UpperRightCorner, aAxis));
        aRectangleAScalars.add(GenerateScalar(theRectangle.LowerLeftCorner, aAxis));
        aRectangleAScalars.add(GenerateScalar(theRectangle.LowerRightCorner, aAxis));

        //Project the corners of the current Rectangle on to the Axis and
        //get a scalar value of that project we can then use for comparison
        ArrayList<Integer> aRectangleBScalars = new ArrayList<Integer>();
        aRectangleBScalars.add(GenerateScalar(UpperLeftCorner, aAxis));
        aRectangleBScalars.add(GenerateScalar(UpperRightCorner, aAxis));
        aRectangleBScalars.add(GenerateScalar(LowerLeftCorner, aAxis));
        aRectangleBScalars.add(GenerateScalar(LowerRightCorner, aAxis));

        //Get the Maximum and Minium Scalar values for each of the Rectangles
        int aRectangleAMinimum = min(aRectangleAScalars);
        int aRectangleAMaximum = max(aRectangleAScalars);
        int aRectangleBMinimum = min(aRectangleBScalars);
        int aRectangleBMaximum = max(aRectangleBScalars);

        //If we have overlaps between the Rectangles (i.e. Min of B is less than Max of A)
        //then we are detecting a collision between the rectangles on this Axis
        if (aRectangleBMinimum <= aRectangleAMaximum && aRectangleBMaximum >= aRectangleAMaximum) {
            return true;
        } else if (aRectangleAMinimum <= aRectangleBMaximum && aRectangleAMaximum >= aRectangleBMaximum) {
            return true;
        }

        return false;
    }

    private int min(ArrayList<Integer> a) {
        int r = Integer.MAX_VALUE;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) < r) {
                r = a.get(i);
            }
        }
        return r;
    }

    private int max(ArrayList<Integer> a) {
        int r = Integer.MIN_VALUE;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) > r) {
                r = a.get(i);
            }
        }
        return r;
    }

    /**
     * Sees if the passed in point is inside the rectangle, assuming the angle is 0
     * @param v the point
     * @return if the point is inside the rectangle
     */
    public boolean contains(Vector2 v) {
        return this.contains(v.getX(), v.getY());
    }
}
