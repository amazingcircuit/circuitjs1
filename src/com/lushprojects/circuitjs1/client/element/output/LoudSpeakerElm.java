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

package com.lushprojects.circuitjs1.client.element.output;

import com.lushprojects.circuitjs1.client.element.Capacitor;
import com.lushprojects.circuitjs1.client.element.CircuitElm;
import com.lushprojects.circuitjs1.client.element.Inductor;
import com.lushprojects.circuitjs1.client.gui.EditInfo;
import com.lushprojects.circuitjs1.client.gui.Graphics;
import com.lushprojects.circuitjs1.client.gui.Point;
import com.lushprojects.circuitjs1.client.gui.Polygon;
import com.lushprojects.circuitjs1.client.sim.StringTokenizer;

public class LoudSpeakerElm extends CircuitElm {
	Inductor le;
	double re;
	Capacitor ces;
	Inductor les;
	double res;
	final int POST1 = 0;
	final int POST2 = 1;
	final int INODE1 = 2;
	final int INODE2 = 3;
	
	//double voltdiff;
	
	public LoudSpeakerElm(int xx, int yy) {
		super(xx, yy);
		
		le = new Inductor(sim);
		le.setup(1.5e-6, current, flags);
		re = 6.2;
		
		ces = new Capacitor(sim);
		ces.setup(700e-6, current, flags);
		les = new Inductor(sim);
		les.setup(50e-6, current, flags);
		res = 44;
	}

	public LoudSpeakerElm(int xa, int ya, int xb, int yb, int f,
			StringTokenizer st) {
		super(xa, ya, xb, yb, f);
		double leValue = new Double(st.nextToken()).doubleValue();
		double reValue = new Double(st.nextToken()).doubleValue();
		double cesValue = new Double(st.nextToken()).doubleValue();
		double lesValue = new Double(st.nextToken()).doubleValue();
		double resValue = new Double(st.nextToken()).doubleValue();
		le = new Inductor(sim);
		le.setup(leValue, current, flags);
		re = reValue;
		ces = new Capacitor(sim);
		ces.setup(cesValue, current, flags);
		les = new Inductor(sim);
		les.setup(lesValue, current, flags);
		res = resValue;
		//voltdiff = new Double(st.nextToken()).doubleValue();
	}

	protected int getInternalNodeCount() {
		return 2;
	}
	
	protected int getDumpType() {
		return 202;
	}

	public String dump() {
		return super.dump() + " "+le.inductance+ " "+re+" "+ces.capacitance+ " "+les.inductance+ " "+res;
	}

	   Polygon polyCone;
	   Polygon polyBox;
	    Point top[];
	    Point bottom[];
	    Point boxTop[];
	    Point boxBottom[];
	    final int hs = 8;
		Point mid = new Point();
	    
	protected void setPoints() {
		super.setPoints();
		calcLeads(32);
		//ps3 = new Point();
		//ps4 = new Point();
		//cathode = newPointArray(2);
		top = newPointArray(2);
		bottom = newPointArray(2);
		boxTop = newPointArray(2);
		boxBottom = newPointArray(2);
		//Point pa[] = newPointArray(2);
		//interpPoint2(lead1, lead2, pa[0], pa[1], 0, hs);
		//interpPoint2(lead1, lead2, cathode[0], cathode[1], 1, hs);
		
		interpPoint2(lead1, lead2, bottom[0], bottom[1], 1, hs*2);
		interpPoint2(lead1, lead2, top[0], top[1], 0, hs*2);
		
		interpPoint2(lead1, lead2, boxBottom[0], boxBottom[1], 1, hs/2);
		interpPoint2(lead1, lead2, boxTop[0], boxTop[1], 0, hs/2);

		//poly = createPolygon(pa[0], pa[1], lead2);
		interpPoint(lead1, lead2, mid, 0.5);

		polyCone = createPolygon(mid, top[0], bottom[0]);
		polyBox = createPolygon(boxBottom[0], boxBottom[1],boxTop[1],boxTop[0]);
	}

	public void draw(Graphics g) {
		
		//int i;
	
		//double v1 = volts[0];
		//double v2 = volts[1];
		setBbox(point1, point2, hs);
		draw2Leads(g);
		
		//g.fillOval(lead1.x-3, lead1.y-3, 6, 6);
		//g.fillOval(lead2.x-3, lead2.y-3, 6, 6);
		
		
		//g.fillOval(point1.x-10, point1.y-10, 20, 20);
		//g.fillOval(point2.x-10, point2.y-10, 20, 20);

		//g.fillOval(mid.x-4, mid.y-4, 8, 8);
		
		//g.drawLine(top[0].x,top[0].y, top[1].x, top[1].y);
		//g.drawLine(bottom[0].x,bottom[0].y, bottom[1].x, bottom[1].y);
		
		g.fillPolygon(polyBox);
		g.fillPolygon(polyCone);
		
		//int len = Math.abs(lead1.y-lead2.y);
		//g.drawRect(lead1.x-5, lead1.y, 10, len);
		setPowerColor(g, true);
		
		doDots(g);
		drawPosts(g);
	}


	

	protected void setNodeVoltage(int n, double c) {
		super.setNodeVoltage(n, c);
		//voltdiff = volts[POST1] - volts[POST2];
	}

	protected void reset() {
		current = curcount = 0;
		// put small charge on caps when reset to start oscillators
		//voltdiff = 1e-3;
		le.reset();
		ces.reset();
		les.reset();
	}



	public void stamp() {
		le.stamp(nodes[POST1] , nodes[INODE1]);
		sim.stampResistor(nodes[INODE1], nodes[INODE2], re);
		
		ces.stamp(nodes[INODE2] , nodes[POST2]);
		les.stamp(nodes[INODE2] , nodes[POST2]);
		sim.stampResistor(nodes[INODE2], nodes[POST2], res);

	}

	protected void startIteration() {
		double voltdiff1 = volts[POST1] - volts[INODE1];
		double voltdiff2 = volts[INODE2] - volts[POST2];
	    
		le.startIteration(voltdiff1);
		
		ces.startIteration(voltdiff2);
		les.startIteration(voltdiff2);
	}

	protected boolean nonLinear() {
		return ces.nonLinear() || les.nonLinear()|| le.nonLinear();
	}
	
	protected void calculateCurrent() {
		
		double voltdiff = volts[INODE2] - volts[POST2];
		current = ces.calculateCurrent(voltdiff);
		current += les.calculateCurrent(voltdiff);
		current += voltdiff / res;
		
		/*
		double voltdiff = volts[INODE1] - volts[INODE2];
		current = voltdiff / re;
		*/
	}


	protected void doStep() {
		
		double voltdiff1 = volts[INODE2] - volts[POST2];
		double voltdiff2 = volts[POST1] - volts[INODE1];
		
		ces.doStep(nodes[INODE2] , nodes[POST2]);		
		les.doStep(voltdiff1);
		le.doStep(voltdiff2);
	}

	public void getInfo(String arr[]) {
		arr[0] = "loudspeaker";
		getBasicInfo(arr);
		arr[3] = "Ces = " + getUnitText(ces.capacitance, "F");
		arr[4] = "P = " + getUnitText(getPower(), "W");
		// double v = getVoltageDiff();
		// arr[4] = "U = " + getUnitText(.5*capacitance*v*v, "J");
	}

	public EditInfo getEditInfo(int n) {
		if (n == 0)
			return new EditInfo("Le", le.inductance, 0, 0);
		if (n == 1)
			return new EditInfo("Re", re, 0, 0);
		if (n == 2)
			return new EditInfo("Ces", ces.capacitance, 0, 0);
		if (n == 3)
			return new EditInfo("Les", les.inductance, 0, 0);
		if (n == 4)
			return new EditInfo("Res", res, 0, 0);
		
		/*
		if (n == 1) {
			EditInfo ei = new EditInfo("", 0, -1, -1);
			ei.checkbox = new Checkbox("Trapezoidal Approximation",
					cap.isTrapezoidal());
			return ei;
		}*/
		return null;
	}

	public void setEditValue(int n, EditInfo ei) {
		
		if (n == 0 && ei.value > 0)
			le.inductance = ei.value;
		if (n == 1 && ei.value > 0)
			re = ei.value;
		if (n == 2 && ei.value > 0)
			ces.capacitance = ei.value;
		if (n == 3 && ei.value > 0)
			les.inductance = ei.value;
		if (n == 4 && ei.value > 0)
			res = ei.value;
		
		/*
		if (n == 1) {
			if (ei.checkbox.getState())
				flags &= ~cap.FLAG_BACK_EULER;
			else
				flags |= cap.FLAG_BACK_EULER;
			cap.flags = flags;
		}*/
	}

	
}
