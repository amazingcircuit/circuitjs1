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

package com.lushprojects.circuitjs1.client.element.passive;

import com.lushprojects.circuitjs1.client.element.Capacitor;
import com.lushprojects.circuitjs1.client.element.CircuitElm;
import com.lushprojects.circuitjs1.client.gui.Checkbox;
import com.lushprojects.circuitjs1.client.gui.Color;
import com.lushprojects.circuitjs1.client.gui.EditInfo;
import com.lushprojects.circuitjs1.client.gui.Graphics;
import com.lushprojects.circuitjs1.client.gui.Point;
import com.lushprojects.circuitjs1.client.sim.StringTokenizer;

public class Capacitor2Elm extends CircuitElm {
	Capacitor cap;
	//double capacitance;
	double voltdiff;
	Point plate1[], plate2[];
	
	public Capacitor2Elm(int xx, int yy) {
		super(xx, yy);
		cap = new Capacitor(sim);
		cap.setup(1e-5, current, flags);
	}

	public Capacitor2Elm(int xa, int ya, int xb, int yb, int f,
			StringTokenizer st) {
		super(xa, ya, xb, yb, f);
		double capacitanceValue = new Double(st.nextToken()).doubleValue();
		voltdiff = new Double(st.nextToken()).doubleValue();
		cap = new Capacitor(sim);
		cap.setup(capacitanceValue, current, flags);
	}

	protected int getDumpType() {
		return 'c';
	}

	public String dump() {
		return super.dump() + " " + cap.capacitance + " " + voltdiff;
	}

	protected void setPoints() {
		super.setPoints();
		double f = (dn / 2 - 4) / dn;
		// calc leads
		lead1 = interpPoint(point1, point2, f);
		lead2 = interpPoint(point1, point2, 1 - f);
		// calc plates
		plate1 = newPointArray(2);
		plate2 = newPointArray(2);
		interpPoint2(point1, point2, plate1[0], plate1[1], f, 12);
		interpPoint2(point1, point2, plate2[0], plate2[1], 1 - f, 12);
	}

	public void draw(Graphics g) {
		int hs = 12;
		setBbox(point1, point2, hs);

		// draw first lead and plate
		setVoltageColor(g, volts[0]);
		drawThickLine(g, point1, lead1);
		setPowerColor(g, false);
		drawThickLine(g, plate1[0], plate1[1]);
		if (sim.gui.powerCheckItem.getState())
			g.setColor(Color.gray);

		// draw second lead and plate
		setVoltageColor(g, volts[1]);
		drawThickLine(g, point2, lead2);
		setPowerColor(g, false);
		drawThickLine(g, plate2[0], plate2[1]);

		updateDotCount();
		if (sim.gui.dragElm != this) {
			drawDots(g, point1, lead1, curcount);
			drawDots(g, point2, lead2, -curcount);
		}
		drawPosts(g);
		if (sim.gui.showValuesCheckItem.getState()) {
			String s = getShortUnitText(cap.capacitance, "F");
			drawValues(g, s, hs);
		}
	}

	protected void setNodeVoltage(int n, double c) {
		super.setNodeVoltage(n, c);
		voltdiff = volts[0] - volts[1];
	}

	protected void reset() {
		current = curcount = 0;
		// put small charge on caps when reset to start oscillators
		voltdiff = 1e-3;
		cap.reset();
	}



	public void stamp() {
		cap.flags = flags;
		cap.stamp(nodes[0] , nodes[1]);
	}

	protected void startIteration() {
		
		cap.startIteration(voltdiff);
	}

	protected boolean nonLinear() {
		return cap.nonLinear();
	}
	
	protected void calculateCurrent() {
		//cap.flags = flags;
		double voltdiff = volts[0] - volts[1];
		current = cap.calculateCurrent(voltdiff);
	}


	protected void doStep() {
		//cap.flags = flags;
		cap.doStep(nodes[0] , nodes[1]);
	}

	public void getInfo(String arr[]) {
		arr[0] = "capacitor";
		getBasicInfo(arr);
		arr[3] = "C = " + getUnitText(cap.capacitance, "F");
		arr[4] = "P = " + getUnitText(getPower(), "W");
		// double v = getVoltageDiff();
		// arr[4] = "U = " + getUnitText(.5*capacitance*v*v, "J");
	}

	public EditInfo getEditInfo(int n) {
		if (n == 0)
			return new EditInfo("Capacitance2 (F)", cap.capacitance, 0, 0);
		if (n == 1) {
			EditInfo ei = new EditInfo("", 0, -1, -1);
			ei.checkbox = new Checkbox("Trapezoidal Approximation",
					cap.isTrapezoidal());
			return ei;
		}
		return null;
	}

	public void setEditValue(int n, EditInfo ei) {
		if (n == 0 && ei.value > 0)
			cap.capacitance = ei.value;
		if (n == 1) {
			if (ei.checkbox.getState())
				flags &= ~Capacitor.FLAG_BACK_EULER;
			else
				flags |= Capacitor.FLAG_BACK_EULER;
			cap.flags = flags;
		}
	}

	
}
