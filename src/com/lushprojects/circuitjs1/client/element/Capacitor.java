/*    
    Copyright (C) Paul Falstad and Iain Sharp
    
    This file is part of CircuitJS1.

    CircuitJS1 is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 2 of the License, or
    (at your option) any later version.

    CircuitJS1 is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with CircuitJS1.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.lushprojects.circuitjs1.client.element;



public class Capacitor {
	
	public double capacitance;
	public double compResistance, current;
	
	public static final int FLAG_BACK_EULER = 2;
	double curSourceValue;
    CirSim sim;
    //int nodes[];
    public int flags;
    
	public Capacitor(CirSim s) {
		sim = s;
		//nodes = new int[2];
	}

	public void setup(double ic, double cr, int f) {
		capacitance = ic;
		current = cr;
		flags = f;
	}

	public boolean isTrapezoidal() {
		return (flags & FLAG_BACK_EULER) == 0;
	}

	public void reset() {
		current = 0;
	}

	// capacitor companion model using trapezoidal approximation
	// (Norton equivalent) consists of a current source in
	// parallel with a resistor. Trapezoidal is more accurate
	// than backward euler but can cause oscillatory behavior
	// if RC is small relative to the timestep.
/*
 *  if (isTrapezoidal())
		compResistance = sim.timeStep/(2*capacitance);
	    else
		compResistance = sim.timeStep/capacitance;
	    sim.stampResistor(nodes[0], nodes[1], compResistance);
	    sim.stampRightSide(nodes[0]);
	    sim.stampRightSide(nodes[1]);
 */
	public void stamp(int n0, int n1) {
		//nodes[0] = n0;
		//nodes[1] = n1;
		if (isTrapezoidal())
			compResistance = sim.timeStep / (2 * capacitance);
		else
			compResistance = sim.timeStep / capacitance;
		sim.stampResistor(n0, n1, compResistance);
		sim.stampRightSide(n0);//signify right side changes in doStep()
		sim.stampRightSide(n1);
	}

	public boolean nonLinear() {
		return false;
	}

	public void startIteration(double voltdiff) {
		if (isTrapezoidal())
			curSourceValue = -voltdiff / compResistance - current;
		else
			curSourceValue = -voltdiff / compResistance;
		// System.out.println("cap " + compResistance + " " + curSourceValue +
		// " " + current + " " + voltdiff);
	}

	public double calculateCurrent(double voltdiff) {
		// we check compResistance because this might get called
				// before stamp(), which sets compResistance, causing
				// infinite current
				if (compResistance > 0)
					current = voltdiff / compResistance + curSourceValue;
				return current;
	}

	public void doStep(int n0, int n1) {
		sim.stampCurrentSource(n0, n1, curSourceValue);
	}
}
