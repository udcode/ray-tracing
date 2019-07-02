/**
 * Course: 151055 Software Engineering intro, Lab
 * Student1: Avi margali 305645137
 * Student2: Yeuda Nuiman 301759692
 * Teacher: Dan Zilberstein
 */
package com.ud_avi.raytrace.primitives;

/**
 *
 * Coordinate is the Class that represent Coordinate in some Dimension
 */

public class Coordinate {
    public static final Coordinate ZERO = new Coordinate(0);
    private double _coord;
    // ***************** Constructors ********************** //

    // It is binary, equivalent to ~1/1,000,000 in decimal (6 digits)
    private static final int ACCURACY = -40;

    public Coordinate(double coord) {
        _coord = (getExp(coord) < ACCURACY) ? 0.0 : coord;
    }

    public Coordinate(Coordinate other) {
        _coord = other._coord;
    }

    // ***************** Getters/Setters ********************** //
    public double getCoord() {
        return _coord;
    }

    // ***************** Administration  ******************** //
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;

        if (obj instanceof Double)  //equal of double
            return _subtract((double) obj) == 0;

        if (!(obj instanceof Coordinate))
            return false;

        Coordinate other = (Coordinate) obj;
        return _subtract(other.getCoord()) == 0; //the diff is other then "zero", not equal
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.valueOf(getCoord());
    }

    // ***************** Operations ******************** //
    // double store format: seee eeee eeee (1.)mmmm � mmmm
    // 1 bit sign, 11 bits exponent, 53 bits (52 stored) normalized mantissa
    private int getExp(double num) {
        return (int) ((Double.doubleToRawLongBits(num) >> 52) & 0x7FFL) - 1023;
    }

    /**
     * subtract double from function with ACCURACY
     * @param other double
     * @return double
     */
    public double subtract(double other) {
        return _subtract(other);
    }

    /**
     * subtract double from function with ACCURACY
     * @param other double
     * @return double
     */
    private double _subtract(double other) {
        int otherExp = (other == 0.0) ? 0 : getExp(other);
        int thisExp = (_coord == 0.0) ? 0 : getExp(_coord);
        // if other is too small relatively to our coordinate return the original
        // coordinate
        if (otherExp - thisExp < ACCURACY)
            return _coord;
        // if our coordinate is too small relatively to other return negative of other
        if (thisExp - otherExp < ACCURACY)
            return -other;
        double result = _coord - other;
        int resultExp = getExp(result);
        // if the result is relatively small - tell that it is zero
        return resultExp - thisExp < ACCURACY ? 0.0 : result;
    }

    /**
     * add double to Coordinate
     * @param other double
     * @return new Coordinate
     */
    public Coordinate add(double other) {
        return new Coordinate(_add(other));
    }

    /**
     * add double to double with ACCURACY
     * @param other double
     * @return double
     */
    private double _add(double other) {
        int otherExp = getExp(other);
        int thisExp = getExp(_coord);
        // if other is too small relatively to our coordinate return the original
        // coordinate
        if (otherExp - thisExp < ACCURACY)
            return _coord;
        // if our coordinate is too small relatively to other return other
        if (thisExp - otherExp < ACCURACY)
            return other;
        double result = _coord + other;
        int resultExp = getExp(result);
        // if the result is relatively small - tell that it is zero
        return resultExp - thisExp < ACCURACY ? 0.0 : result;
    }

}
