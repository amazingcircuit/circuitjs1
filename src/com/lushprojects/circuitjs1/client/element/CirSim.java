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

// GWT conversion (c) 2015 by Iain Sharp

// For information about the theory behind this, see Electronic Circuit & System Simulation Methods by Pillage

import java.util.Random;
import java.util.Vector;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window.Navigator;
import com.lushprojects.circuitjs1.client.element.active.AnalogSwitch2Elm;
import com.lushprojects.circuitjs1.client.element.active.AnalogSwitchElm;
import com.lushprojects.circuitjs1.client.element.active.CC2Elm;
import com.lushprojects.circuitjs1.client.element.active.CC2NegElm;
import com.lushprojects.circuitjs1.client.element.active.InvertingSchmittElm;
import com.lushprojects.circuitjs1.client.element.active.OpAmpElm;
import com.lushprojects.circuitjs1.client.element.active.OpAmpSwapElm;
import com.lushprojects.circuitjs1.client.element.active.SchmittElm;
import com.lushprojects.circuitjs1.client.element.active.TriStateElm;
import com.lushprojects.circuitjs1.client.element.digital.CounterElm;
import com.lushprojects.circuitjs1.client.element.digital.DFlipFlopElm;
import com.lushprojects.circuitjs1.client.element.digital.DeMultiplexerElm;
import com.lushprojects.circuitjs1.client.element.digital.DecadeElm;
import com.lushprojects.circuitjs1.client.element.digital.FullAdderElm;
import com.lushprojects.circuitjs1.client.element.digital.HalfAdderElm;
import com.lushprojects.circuitjs1.client.element.digital.JKFlipFlopElm;
import com.lushprojects.circuitjs1.client.element.digital.LatchElm;
import com.lushprojects.circuitjs1.client.element.digital.MonostableElm;
import com.lushprojects.circuitjs1.client.element.digital.MultiplexerElm;
import com.lushprojects.circuitjs1.client.element.digital.PisoShiftElm;
import com.lushprojects.circuitjs1.client.element.digital.SeqGenElm;
import com.lushprojects.circuitjs1.client.element.digital.SevenSegDecoderElm;
import com.lushprojects.circuitjs1.client.element.digital.SevenSegElm;
import com.lushprojects.circuitjs1.client.element.digital.SipoShiftElm;
import com.lushprojects.circuitjs1.client.element.digital.TFlipFlopElm;
import com.lushprojects.circuitjs1.client.element.hybrid.ADCElm;
import com.lushprojects.circuitjs1.client.element.hybrid.DACElm;
import com.lushprojects.circuitjs1.client.element.hybrid.PhaseCompElm;
import com.lushprojects.circuitjs1.client.element.hybrid.TimerElm;
import com.lushprojects.circuitjs1.client.element.hybrid.VCOElm;
import com.lushprojects.circuitjs1.client.element.logic.AndGateElm;
import com.lushprojects.circuitjs1.client.element.logic.InverterElm;
import com.lushprojects.circuitjs1.client.element.logic.LogicInputElm;
import com.lushprojects.circuitjs1.client.element.logic.LogicOutputElm;
import com.lushprojects.circuitjs1.client.element.logic.NandGateElm;
import com.lushprojects.circuitjs1.client.element.logic.NorGateElm;
import com.lushprojects.circuitjs1.client.element.logic.OrGateElm;
import com.lushprojects.circuitjs1.client.element.logic.XorGateElm;
import com.lushprojects.circuitjs1.client.element.output.BoxElm;
import com.lushprojects.circuitjs1.client.element.output.LEDElm;
import com.lushprojects.circuitjs1.client.element.output.LampElm;
import com.lushprojects.circuitjs1.client.element.output.LoudSpeakerElm;
import com.lushprojects.circuitjs1.client.element.output.OutputElm;
import com.lushprojects.circuitjs1.client.element.output.ProbeElm;
import com.lushprojects.circuitjs1.client.element.output.TextElm;
import com.lushprojects.circuitjs1.client.element.passive.Capacitor2Elm;
import com.lushprojects.circuitjs1.client.element.passive.CapacitorElm;
import com.lushprojects.circuitjs1.client.element.passive.InductorElm;
import com.lushprojects.circuitjs1.client.element.passive.MemristorElm;
import com.lushprojects.circuitjs1.client.element.passive.PotElm;
import com.lushprojects.circuitjs1.client.element.passive.PushSwitchElm;
import com.lushprojects.circuitjs1.client.element.passive.RelayElm;
import com.lushprojects.circuitjs1.client.element.passive.ResistorElm;
import com.lushprojects.circuitjs1.client.element.passive.SparkGapElm;
import com.lushprojects.circuitjs1.client.element.passive.Switch2Elm;
import com.lushprojects.circuitjs1.client.element.passive.SwitchElm;
import com.lushprojects.circuitjs1.client.element.passive.TappedTransformerElm;
import com.lushprojects.circuitjs1.client.element.passive.TransLineElm;
import com.lushprojects.circuitjs1.client.element.passive.TransformerElm;
import com.lushprojects.circuitjs1.client.element.semi.DiacElm;
import com.lushprojects.circuitjs1.client.element.semi.DiodeElm;
import com.lushprojects.circuitjs1.client.element.semi.JfetElm;
import com.lushprojects.circuitjs1.client.element.semi.MosfetElm;
import com.lushprojects.circuitjs1.client.element.semi.NJfetElm;
import com.lushprojects.circuitjs1.client.element.semi.NMosfetElm;
import com.lushprojects.circuitjs1.client.element.semi.NTransistorElm;
import com.lushprojects.circuitjs1.client.element.semi.PJfetElm;
import com.lushprojects.circuitjs1.client.element.semi.PMosfetElm;
import com.lushprojects.circuitjs1.client.element.semi.PTransistorElm;
import com.lushprojects.circuitjs1.client.element.semi.SCRElm;
import com.lushprojects.circuitjs1.client.element.semi.TransistorElm;
import com.lushprojects.circuitjs1.client.element.semi.TriacElm;
import com.lushprojects.circuitjs1.client.element.semi.TriodeElm;
import com.lushprojects.circuitjs1.client.element.semi.TunnelDiodeElm;
import com.lushprojects.circuitjs1.client.element.semi.ZenerElm;
import com.lushprojects.circuitjs1.client.element.source.ACRailElm;
import com.lushprojects.circuitjs1.client.element.source.ACVoltageElm;
import com.lushprojects.circuitjs1.client.element.source.AMElm;
import com.lushprojects.circuitjs1.client.element.source.AntennaElm;
import com.lushprojects.circuitjs1.client.element.source.ClockElm;
import com.lushprojects.circuitjs1.client.element.source.CurrentElm;
import com.lushprojects.circuitjs1.client.element.source.DCVoltageElm;
import com.lushprojects.circuitjs1.client.element.source.FMElm;
import com.lushprojects.circuitjs1.client.element.source.GroundElm;
import com.lushprojects.circuitjs1.client.element.source.RailElm;
import com.lushprojects.circuitjs1.client.element.source.SquareRailElm;
import com.lushprojects.circuitjs1.client.element.source.SweepElm;
import com.lushprojects.circuitjs1.client.element.source.VarRailElm;
import com.lushprojects.circuitjs1.client.element.source.VoltageElm;
import com.lushprojects.circuitjs1.client.gui.Color;
import com.lushprojects.circuitjs1.client.gui.Font;
import com.lushprojects.circuitjs1.client.gui.Graphics;
import com.lushprojects.circuitjs1.client.gui.Gui;
import com.lushprojects.circuitjs1.client.gui.LoadFile;
import com.lushprojects.circuitjs1.client.gui.Point;
import com.lushprojects.circuitjs1.client.gui.QueryParameters;
import com.lushprojects.circuitjs1.client.gui.Rectangle;
import com.lushprojects.circuitjs1.client.gui.Scope;
import com.lushprojects.circuitjs1.client.sim.CircuitNode;
import com.lushprojects.circuitjs1.client.sim.CircuitNodeLink;
import com.lushprojects.circuitjs1.client.sim.RowInfo;
import com.lushprojects.circuitjs1.client.sim.StringTokenizer;

public class CirSim {

	Logger logger = Logger.getLogger("CirSim");
	Random random;
	public int hintType = -1;
	int hintItem1;
	int hintItem2;

	public static final int sourceRadius = 7;
	public static final double freqMult = 3.14159265 * 2 * 4;

	static final int HINT_LC = 1;
	static final int HINT_RC = 2;
	static final int HINT_3DB_C = 3;
	static final int HINT_TWINT = 4;
	static final int HINT_3DB_L = 5;
	boolean isMac;
	public LoadFile loadFileInput;
	static final double pi = 3.14159265358979323846;
	public int circuitBottom;
	// boolean analyzeFlag;
	boolean dumpMatrix;
	long myframes = 1;
	long mytime = 0;
	long myruntime = 0;
	long mydrawtime = 0;
	public double timeStep;
	public double t;
	boolean circuitNonLinear;
	int voltageSourceCount;
	int circuitMatrixSize, circuitMatrixFullSize;
	boolean circuitNeedsMap;
	double circuitMatrix[][], circuitRightSide[], origRightSide[],
			origMatrix[][];
	RowInfo circuitRowInfo[];
	int circuitPermute[];
	public Gui gui;
	boolean shown = false;
	public Vector<CircuitElm> elmList;
	public boolean analyzeFlag;

	public boolean converged;
	public int subIterations;

	static final int resct = 6;
	long lastTime = 0, lastFrameTime, lastIterTime, secTime = 0;
	int frames = 0;
	int steps = 0;
	int framerate = 0, steprate = 0;
	public Vector<CircuitNode> nodeList;
	CircuitElm voltageSources[];

	// Class addingClass;
	final Timer timer = new Timer() {
		public void run() {
			updateCircuit();
		}
	};
	final int FASTTIMER = 40;

	public int getrand(int x) {
		int q = random.nextInt();
		if (q < 0)
			q = -q;
		return q % x;
	}

	// Circuit applet;

	public CirSim() {
		// super("Circuit Simulator v1.6d");
		// applet = a;
		// useFrame = false;
	}

	// String baseURL = "http://www.falstad.com/circuit/";

	public void init() {

		boolean printable = false;
		boolean convention = true;
		boolean euro = false;

		random = new Random();

		gui = new Gui(this);

		CircuitElm.initClass(this);

		QueryParameters qp = new QueryParameters();

		try {
			String cct = qp.getValue("cct");

			if (cct != null)
				gui.startCircuitText = cct.replace("%24", "$");

			gui.startCircuit = qp.getValue("startCircuit");
			gui.startLabel = qp.getValue("startLabel");
			euro = qp.getBooleanValue("euroResistors", true);
			printable = qp.getBooleanValue("whiteBackground", false);
			convention = qp.getBooleanValue("conventionalCurrent", true);
		} catch (Exception e) {
		}

		String os = Navigator.getPlatform();
		isMac = (os.toLowerCase().contains("mac"));
		gui.ctrlMetaKey = (isMac) ? "Cmd" : "Ctrl";

		// main.setLayout(new CircuitLayout());
		elmList = new Vector<CircuitElm>();

		gui.generateLayout(printable, convention, euro);

		timer.scheduleRepeating(FASTTIMER);

	}

	public void updateCircuit() {
		long mystarttime;
		long myrunstarttime;
		long mydrawstarttime;
		CircuitElm realMouseElm;

		mystarttime = System.currentTimeMillis();
		if (analyzeFlag) {
			analyzeCircuit();
			analyzeFlag = false;
		}

		realMouseElm = gui.mouseElm;
		if (gui.mouseElm == null)
			gui.mouseElm = gui.stopElm;

		gui.setupScopes();

		CircuitElm.selectColor = Color.cyan;

		Graphics g = gui.beginRender();
		myrunstarttime = System.currentTimeMillis();

		if (!gui.stoppedCheck.getState()) {
			try {
				runCircuit();
			} catch (Exception e) {
				e.printStackTrace();
				analyzeFlag = true;
				// cv.repaint();
				return;
			}
			myruntime += System.currentTimeMillis() - myrunstarttime;
		}

		long sysTime = System.currentTimeMillis();

		if (!gui.stoppedCheck.getState()) {

			if (lastTime != 0) {
				int inc = (int) (sysTime - lastTime);
				double c = gui.currentBar.getValue();
				c = java.lang.Math.exp(c / 3.5 - 14.2);
				CircuitElm.currentMult = 1.7 * inc * c;
				if (!gui.conventionCheckItem.getState())
					CircuitElm.currentMult = -CircuitElm.currentMult;
			}

			lastTime = sysTime;
		} else
			lastTime = 0;

		if (sysTime - secTime >= 1000) {
			framerate = frames;
			steprate = steps;
			frames = 0;
			steps = 0;
			secTime = sysTime;
		}
		CircuitElm.powerMult = Math.exp(gui.powerBar.getValue() / 4.762 - 7);

		int i;

		Font oldfont = CircuitElm.unitsFont;
		g.setFont(oldfont);
		
		mydrawstarttime = System.currentTimeMillis();

		for (i = 0; i != elmList.size(); i++) {
			if (gui.powerCheckItem.getState())
				g.setColor(Color.gray);
			/*
			 * else if (conductanceCheckItem.getState())
			 * g.setColor(Color.white);
			 */
			getElm(i).draw(g);
		}
		mydrawtime += System.currentTimeMillis() - mydrawstarttime;
		
		if (gui.tempMouseMode == Gui.MODE_DRAG_ROW
				|| gui.tempMouseMode == Gui.MODE_DRAG_COLUMN
				|| gui.tempMouseMode == Gui.MODE_DRAG_POST
				|| gui.tempMouseMode == Gui.MODE_DRAG_SELECTED)
			for (i = 0; i != elmList.size(); i++) {

				CircuitElm ce = getElm(i);
				// ce.drawPost(g, ce.x , ce.y );
				// ce.drawPost(g, ce.x2, ce.y2);
				if (ce != gui.mouseElm
						|| gui.tempMouseMode != Gui.MODE_DRAG_POST) {
					g.setColor(Color.gray);
					g.fillOval(ce.x - 3, ce.y - 3, 7, 7);
					g.fillOval(ce.x2 - 3, ce.y2 - 3, 7, 7);
				} else {
					ce.drawHandles(g, Color.cyan);
				}
			}
		if (gui.tempMouseMode == Gui.MODE_SELECT && gui.mouseElm != null) {
			gui.mouseElm.drawHandles(g, Color.cyan);
		}
		int badnodes = 0;
		// find bad connections, nodes not connected to other elements which
		// intersect other elements' bounding boxes
		// debugged by hausen: nullPointerException
		if (nodeList != null)
			for (i = 0; i != nodeList.size(); i++) {
				CircuitNode cn = getCircuitNode(i);
				if (!cn.internal && cn.links.size() == 1) {
					int bb = 0, j;
					CircuitNodeLink cnl = cn.links.elementAt(0);
					for (j = 0; j != elmList.size(); j++) { // TODO: (hausen)
															// see if this
															// change does not
															// break stuff
						CircuitElm ce = getElm(j);
						if (ce instanceof GraphicElm)
							continue;
						if (cnl.elm != ce
								&& getElm(j).boundingBox.contains(cn.x, cn.y))
							bb++;
					}
					if (bb > 0) {
						g.setColor(Color.red);
						g.fillOval(cn.x - 3, cn.y - 3, 7, 7);
						badnodes++;
					}
				}
			}

		gui.render(g, oldfont, badnodes, t);

		gui.mouseElm = realMouseElm;

		frames++;

		lastFrameTime = lastTime;
		mytime = mytime + System.currentTimeMillis() - mystarttime;
		myframes++;
	}

	public void drawElmWithHandles(CircuitElm elm, Graphics g) {
		elm.draw(g);
		elm.drawHandles(g, Color.cyan);

	}

	public String[] getInfo() {

		String info[] = new String[10];
		if (gui.mouseElm != null) {

			if (gui.mousePost == -1)
				gui.mouseElm.getInfo(info);
			else
				info[0] = "V = "
						+ CircuitElm
								.getUnitText(gui.mouseElm
										.getPostVoltage(gui.mousePost), "V");
			/*
			 * //shownodes for (i = 0; i != mouseElm.getPostCount(); i++)
			 * info[0] += " " + mouseElm.nodes[i]; if
			 * (mouseElm.getVoltageSourceCount() > 0) info[0] += ";" +
			 * (mouseElm.getVoltageSource()+nodeList.size());
			 */

		} else {
			// IES Eliminate hack of showFormat
			// CircuitElm.showFormat.setMinimumFractionDigits(2);
			info[0] = "t = " + CircuitElm.getUnitText(t, "s");
			// CircuitElm.showFormat.setMinimumFractionDigits(0);
		}
		return info;
	}


	public String getHint() {
		CircuitElm c1 = getElm(hintItem1);
		CircuitElm c2 = getElm(hintItem2);
		if (c1 == null || c2 == null)
			return null;
		if (hintType == HINT_LC) {
			if (!(c1 instanceof InductorElm))
				return null;
			if (!(c2 instanceof CapacitorElm /* || c2 instanceof Capacitor2Elm */))
				return null;
			InductorElm ie = (InductorElm) c1;
			CapacitorElm ce = (CapacitorElm) c2;
			return "res.f = "
					+ CircuitElm.getUnitText(
							1 / (2 * pi * Math.sqrt(ie.inductance
									* ce.capacitance)), "Hz");
		}
		if (hintType == HINT_RC) {
			if (!(c1 instanceof ResistorElm))
				return null;
			if (!(c2 instanceof CapacitorElm))
				return null;
			ResistorElm re = (ResistorElm) c1;
			CapacitorElm ce = (CapacitorElm) c2;
			return "RC = "
					+ CircuitElm.getUnitText(re.resistance * ce.capacitance,
							"s");
		}
		if (hintType == HINT_3DB_C) {
			if (!(c1 instanceof ResistorElm))
				return null;
			if (!(c2 instanceof CapacitorElm))
				return null;
			ResistorElm re = (ResistorElm) c1;
			CapacitorElm ce = (CapacitorElm) c2;
			return "f.3db = "
					+ CircuitElm
							.getUnitText(
									1 / (2 * pi * re.resistance * ce.capacitance),
									"Hz");
		}
		if (hintType == HINT_3DB_L) {
			if (!(c1 instanceof ResistorElm))
				return null;
			if (!(c2 instanceof InductorElm))
				return null;
			ResistorElm re = (ResistorElm) c1;
			InductorElm ie = (InductorElm) c2;
			return "f.3db = "
					+ CircuitElm.getUnitText(re.resistance
							/ (2 * pi * ie.inductance), "Hz");
		}
		if (hintType == HINT_TWINT) {
			if (!(c1 instanceof ResistorElm))
				return null;
			if (!(c2 instanceof CapacitorElm))
				return null;
			ResistorElm re = (ResistorElm) c1;
			CapacitorElm ce = (CapacitorElm) c2;
			return "fc = "
					+ CircuitElm
							.getUnitText(
									1 / (2 * pi * re.resistance * ce.capacitance),
									"Hz");
		}
		return null;
	}

	// public void toggleSwitch(int n) {
	// int i;
	// for (i = 0; i != elmList.size(); i++) {
	// CircuitElm ce = getElm(i);
	// if (ce instanceof SwitchElm) {
	// n--;
	// if (n == 0) {
	// ((SwitchElm) ce).toggle();
	// analyzeFlag = true;
	// cv.repaint();
	// return;
	// }
	// }
	// }
	// }

	public void needAnalyze() {
		analyzeFlag = true;
		// cv.repaint();
	}


	public CircuitNode getCircuitNode(int n) {
		if (n >= nodeList.size())
			return null;
		return nodeList.elementAt(n);
	}

	public CircuitElm getElm(int n) {
		if (n >= elmList.size())
			return null;
		return elmList.elementAt(n);
	}

	void analyzeCircuit() {
		calcCircuitBottom();
		if (elmList.isEmpty())
			return;
		gui.stopMessage = null;
		gui.stopElm = null;
		int i, j;
		int vscount = 0;
		nodeList = new Vector<CircuitNode>();
		boolean gotGround = false;
		boolean gotRail = false;
		CircuitElm volt = null;

		// System.out.println("ac1");
		// look for voltage or ground element
		for (i = 0; i != elmList.size(); i++) {
			CircuitElm ce = getElm(i);
			if (ce instanceof GroundElm) {
				gotGround = true;
				break;
			}
			if (ce instanceof RailElm)
				gotRail = true;
			if (volt == null && ce instanceof VoltageElm)
				volt = ce;
		}

		// if no ground, and no rails, then the voltage elm's first terminal
		// is ground
		if (!gotGround && volt != null && !gotRail) {
			CircuitNode cn = new CircuitNode();
			Point pt = volt.getPost(0);
			cn.x = (int) pt.x;
			cn.y = (int) pt.y;
			nodeList.addElement(cn);
		} else {
			// otherwise allocate extra node for ground
			CircuitNode cn = new CircuitNode();
			cn.x = cn.y = -1;
			nodeList.addElement(cn);
		}
		// System.out.println("ac2");

		// allocate nodes and voltage sources
		for (i = 0; i != elmList.size(); i++) {
			CircuitElm ce = getElm(i);
			int inodes = ce.getInternalNodeCount();
			int ivs = ce.getVoltageSourceCount();
			int posts = ce.getPostCount();

			// allocate a node for each post and match posts to nodes
			for (j = 0; j != posts; j++) {
				Point pt = ce.getPost(j);
				int k;
				for (k = 0; k != nodeList.size(); k++) {
					CircuitNode cn = getCircuitNode(k);
					if (pt.x == cn.x && pt.y == cn.y)
						break;
				}
				if (k == nodeList.size()) {
					CircuitNode cn = new CircuitNode();
					cn.x = (int) pt.x;
					cn.y = (int) pt.y;
					CircuitNodeLink cnl = new CircuitNodeLink();
					cnl.num = j;
					cnl.elm = ce;
					cn.links.addElement(cnl);
					ce.setNode(j, nodeList.size());
					nodeList.addElement(cn);
				} else {
					CircuitNodeLink cnl = new CircuitNodeLink();
					cnl.num = j;
					cnl.elm = ce;
					getCircuitNode(k).links.addElement(cnl);
					ce.setNode(j, k);
					// if it's the ground node, make sure the node voltage is 0,
					// cause it may not get set later
					if (k == 0)
						ce.setNodeVoltage(j, 0);
				}
			}
			for (j = 0; j != inodes; j++) {
				CircuitNode cn = new CircuitNode();
				cn.x = cn.y = -1;
				cn.internal = true;
				CircuitNodeLink cnl = new CircuitNodeLink();
				cnl.num = j + posts;
				cnl.elm = ce;
				cn.links.addElement(cnl);
				ce.setNode(cnl.num, nodeList.size());
				nodeList.addElement(cn);
			}
			vscount += ivs;
		}
		voltageSources = new CircuitElm[vscount];
		vscount = 0;
		circuitNonLinear = false;
		// System.out.println("ac3");

		// determine if circuit is nonlinear
		for (i = 0; i != elmList.size(); i++) {
			CircuitElm ce = getElm(i);
			if (ce.nonLinear())
				circuitNonLinear = true;
			int ivs = ce.getVoltageSourceCount();
			for (j = 0; j != ivs; j++) {
				voltageSources[vscount] = ce;
				ce.setVoltageSource(j, vscount++);
			}
		}
		voltageSourceCount = vscount;

		int matrixSize = nodeList.size() - 1 + vscount;
		circuitMatrix = new double[matrixSize][matrixSize];
		circuitRightSide = new double[matrixSize];
		origMatrix = new double[matrixSize][matrixSize];
		origRightSide = new double[matrixSize];
		circuitMatrixSize = circuitMatrixFullSize = matrixSize;
		circuitRowInfo = new RowInfo[matrixSize];
		circuitPermute = new int[matrixSize];
		int vs = 0;
		for (i = 0; i != matrixSize; i++)
			circuitRowInfo[i] = new RowInfo();
		circuitNeedsMap = false;

		// stamp linear circuit elements
		for (i = 0; i != elmList.size(); i++) {
			CircuitElm ce = getElm(i);
			ce.stamp();
		}
		// System.out.println("ac4");

		// determine nodes that are unconnected
		boolean closure[] = new boolean[nodeList.size()];
		boolean tempclosure[] = new boolean[nodeList.size()];
		boolean changed = true;
		closure[0] = true;
		while (changed) {
			changed = false;
			for (i = 0; i != elmList.size(); i++) {
				CircuitElm ce = getElm(i);
				// loop through all ce's nodes to see if they are connected
				// to other nodes not in closure
				for (j = 0; j < ce.getPostCount(); j++) {
					if (!closure[ce.getNode(j)]) {
						if (ce.hasGroundConnection(j))
							closure[ce.getNode(j)] = changed = true;
						continue;
					}
					int k;
					for (k = 0; k != ce.getPostCount(); k++) {
						if (j == k)
							continue;
						int kn = ce.getNode(k);
						if (ce.getConnection(j, k) && !closure[kn]) {
							closure[kn] = true;
							changed = true;
						}
					}
				}
			}
			if (changed)
				continue;

			// connect unconnected nodes
			for (i = 0; i != nodeList.size(); i++)
				if (!closure[i] && !getCircuitNode(i).internal) {
					System.out.println("node " + i + " unconnected");
					stampResistor(0, i, 1e8);
					closure[i] = true;
					changed = true;
					break;
				}
		}
		// System.out.println("ac5");

		for (i = 0; i != elmList.size(); i++) {
			CircuitElm ce = getElm(i);
			// look for inductors with no current path
			if (ce instanceof InductorElm) {
				FindPathInfo fpi = new FindPathInfo(FindPathInfo.INDUCT, ce,
						ce.getNode(1));
				// first try findPath with maximum depth of 5, to avoid
				// slowdowns
				if (!fpi.findPath(ce.getNode(0), 5)
						&& !fpi.findPath(ce.getNode(0))) {
					System.out.println(ce + " no path");
					ce.reset();
				}
			}
			// look for current sources with no current path
			if (ce instanceof CurrentElm) {
				FindPathInfo fpi = new FindPathInfo(FindPathInfo.INDUCT, ce,
						ce.getNode(1));
				if (!fpi.findPath(ce.getNode(0))) {
					stop("No path for current source!", ce);
					return;
				}
			}
			// look for voltage source loops
			// IES
			if ((ce instanceof VoltageElm && ce.getPostCount() == 2)
					|| ce instanceof WireElm) {
				FindPathInfo fpi = new FindPathInfo(FindPathInfo.VOLTAGE, ce,
						ce.getNode(1));
				if (fpi.findPath(ce.getNode(0))) {
					stop("Voltage source/wire loop with no resistance!", ce);
					return;
				}
			}
			// look for shorted caps, or caps w/ voltage but no R
			if (ce instanceof CapacitorElm || ce instanceof Capacitor2Elm) {
				FindPathInfo fpi = new FindPathInfo(FindPathInfo.SHORT, ce,
						ce.getNode(1));
				if (fpi.findPath(ce.getNode(0))) {
					System.out.println(ce + " shorted");
					ce.reset();
				} else {
					fpi = new FindPathInfo(FindPathInfo.CAP_V, ce,
							ce.getNode(1));
					if (fpi.findPath(ce.getNode(0))) {
						stop("Capacitor loop with no resistance!", ce);
						return;
					}
				}
			}
		}
		// System.out.println("ac6");

		// simplify the matrix; this speeds things up quite a bit
		for (i = 0; i != matrixSize; i++) {
			int qm = -1, qp = -1;
			double qv = 0;
			RowInfo re = circuitRowInfo[i];
			/*
			 * System.out.println("row " + i + " " + re.lsChanges + " " +
			 * re.rsChanges + " " + re.dropRow);
			 */
			if (re.lsChanges || re.dropRow || re.rsChanges)
				continue;
			double rsadd = 0;

			// look for rows that can be removed
			for (j = 0; j != matrixSize; j++) {
				double q = circuitMatrix[i][j];
				GWT.log("i="+i+" j="+j+" q="+q);
				if (circuitRowInfo[j].type == RowInfo.ROW_CONST) {
					// keep a running total of const values that have been
					// removed already
					rsadd -= circuitRowInfo[j].value * q;
					continue;
				}
				if (q == 0)
					continue;
				if (qp == -1) {
					qp = j;
					qv = q;
					continue;
				}
				if (qm == -1 && q == -qv) {
					qm = j;
					continue;
				}
				break;
			}
			// System.out.println("line " + i + " " + qp + " " + qm + " " + j);
			/*
			 * if (qp != -1 && circuitRowInfo[qp].lsChanges) {
			 * System.out.println("lschanges"); continue; } if (qm != -1 &&
			 * circuitRowInfo[qm].lsChanges) { System.out.println("lschanges");
			 * continue; }
			 */
			if (j == matrixSize) {
				if (qp == -1) {
					stop("Matrix error: "+matrixSize, null);
					logger.warning("Matrix Error");
					GWT.log("Matrix Error");
					return;
				}
				RowInfo elt = circuitRowInfo[qp];
				if (qm == -1) {
					// we found a row with only one nonzero entry; that value
					// is a constant
					int k;
					for (k = 0; elt.type == RowInfo.ROW_EQUAL && k < 100; k++) {
						// follow the chain
						/*
						 * System.out.println("following equal chain from " + i
						 * + " " + qp + " to " + elt.nodeEq);
						 */
						qp = elt.nodeEq;
						elt = circuitRowInfo[qp];
					}
					if (elt.type == RowInfo.ROW_EQUAL) {
						// break equal chains
						// System.out.println("Break equal chain");
						elt.type = RowInfo.ROW_NORMAL;
						continue;
					}
					if (elt.type != RowInfo.ROW_NORMAL) {
						System.out.println("type already " + elt.type + " for "
								+ qp + "!");
						continue;
					}
					elt.type = RowInfo.ROW_CONST;
					elt.value = (circuitRightSide[i] + rsadd) / qv;
					circuitRowInfo[i].dropRow = true;
					// System.out.println(qp + " * " + qv + " = const " +
					// elt.value);
					i = -1; // start over from scratch
				} else if (circuitRightSide[i] + rsadd == 0) {
					// we found a row with only two nonzero entries, and one
					// is the negative of the other; the values are equal
					if (elt.type != RowInfo.ROW_NORMAL) {
						// System.out.println("swapping");
						int qq = qm;
						qm = qp;
						qp = qq;
						elt = circuitRowInfo[qp];
						if (elt.type != RowInfo.ROW_NORMAL) {
							// we should follow the chain here, but this
							// hardly ever happens so it's not worth worrying
							// about
							System.out.println("swap failed");
							continue;
						}
					}
					elt.type = RowInfo.ROW_EQUAL;
					elt.nodeEq = qm;
					circuitRowInfo[i].dropRow = true;
					// System.out.println(qp + " = " + qm);
				}
			}
		}
		// System.out.println("ac7");

		// find size of new matrix
		int nn = 0;
		for (i = 0; i != matrixSize; i++) {
			RowInfo elt = circuitRowInfo[i];
			if (elt.type == RowInfo.ROW_NORMAL) {
				elt.mapCol = nn++;
				// System.out.println("col " + i + " maps to " + elt.mapCol);
				continue;
			}
			if (elt.type == RowInfo.ROW_EQUAL) {
				RowInfo e2 = null;
				// resolve chains of equality; 100 max steps to avoid loops
				for (j = 0; j != 100; j++) {
					e2 = circuitRowInfo[elt.nodeEq];
					if (e2.type != RowInfo.ROW_EQUAL)
						break;
					if (i == e2.nodeEq)
						break;
					elt.nodeEq = e2.nodeEq;
				}
			}
			if (elt.type == RowInfo.ROW_CONST)
				elt.mapCol = -1;
		}
		for (i = 0; i != matrixSize; i++) {
			RowInfo elt = circuitRowInfo[i];
			if (elt.type == RowInfo.ROW_EQUAL) {
				RowInfo e2 = circuitRowInfo[elt.nodeEq];
				if (e2.type == RowInfo.ROW_CONST) {
					// if something is equal to a const, it's a const
					elt.type = e2.type;
					elt.value = e2.value;
					elt.mapCol = -1;
					// System.out.println(i + " = [late]const " + elt.value);
				} else {
					elt.mapCol = e2.mapCol;
					// System.out.println(i + " maps to: " + e2.mapCol);
				}
			}
		}
		// System.out.println("ac8");

		/*
		 * System.out.println("matrixSize = " + matrixSize);
		 * 
		 * for (j = 0; j != circuitMatrixSize; j++) { System.out.println(j +
		 * ": "); for (i = 0; i != circuitMatrixSize; i++)
		 * System.out.print(circuitMatrix[j][i] + " "); System.out.print("  " +
		 * circuitRightSide[j] + "\n"); } System.out.print("\n");
		 */

		// make the new, simplified matrix
		int newsize = nn;
		double newmatx[][] = new double[newsize][newsize];
		double newrs[] = new double[newsize];
		int ii = 0;
		for (i = 0; i != matrixSize; i++) {
			RowInfo rri = circuitRowInfo[i];
			if (rri.dropRow) {
				rri.mapRow = -1;
				continue;
			}
			newrs[ii] = circuitRightSide[i];
			rri.mapRow = ii;
			// System.out.println("Row " + i + " maps to " + ii);
			for (j = 0; j != matrixSize; j++) {
				RowInfo ri = circuitRowInfo[j];
				if (ri.type == RowInfo.ROW_CONST)
					newrs[ii] -= ri.value * circuitMatrix[i][j];
				else
					newmatx[ii][ri.mapCol] += circuitMatrix[i][j];
			}
			ii++;
		}

		circuitMatrix = newmatx;
		circuitRightSide = newrs;
		matrixSize = circuitMatrixSize = newsize;
		for (i = 0; i != matrixSize; i++)
			origRightSide[i] = circuitRightSide[i];
		for (i = 0; i != matrixSize; i++)
			for (j = 0; j != matrixSize; j++)
				origMatrix[i][j] = circuitMatrix[i][j];
		circuitNeedsMap = true;

		/*
		 * System.out.println("matrixSize = " + matrixSize + " " +
		 * circuitNonLinear); for (j = 0; j != circuitMatrixSize; j++) { for (i
		 * = 0; i != circuitMatrixSize; i++)
		 * System.out.print(circuitMatrix[j][i] + " "); System.out.print("  " +
		 * circuitRightSide[j] + "\n"); } System.out.print("\n");
		 */

		// if a matrix is linear, we can do the lu_factor here instead of
		// needing to do it every frame
		if (!circuitNonLinear) {
			if (!lu_factor(circuitMatrix, circuitMatrixSize, circuitPermute)) {
				stop("Singular matrix!", null);
				return;
			}
		}
	}

	public void calcCircuitBottom() {
		int i;
		circuitBottom = 0;
		for (i = 0; i != elmList.size(); i++) {
			Rectangle rect = getElm(i).boundingBox;
			int bottom = rect.getHeight() + rect.getY();
			if (bottom > circuitBottom)
				circuitBottom = bottom;
		}
	}

	class FindPathInfo {
		static final int INDUCT = 1;
		static final int VOLTAGE = 2;
		static final int SHORT = 3;
		static final int CAP_V = 4;
		boolean used[];
		int dest;
		CircuitElm firstElm;
		int type;

		FindPathInfo(int t, CircuitElm e, int d) {
			dest = d;
			type = t;
			firstElm = e;
			used = new boolean[nodeList.size()];
		}

		boolean findPath(int n1) {
			return findPath(n1, -1);
		}

		boolean findPath(int n1, int depth) {
			if (n1 == dest)
				return true;
			if (depth-- == 0)
				return false;
			if (used[n1]) {
				// System.out.println("used " + n1);
				return false;
			}
			used[n1] = true;
			int i;
			for (i = 0; i != elmList.size(); i++) {
				CircuitElm ce = getElm(i);
				if (ce == firstElm)
					continue;
				if (type == INDUCT) {
					if (ce instanceof CurrentElm)
						continue;
				}
				if (type == VOLTAGE) {
					if (!(ce.isWire() || ce instanceof VoltageElm))
						continue;
				}
				if (type == SHORT && !ce.isWire())
					continue;
				if (type == CAP_V) {
					if (!(ce.isWire() || ce instanceof CapacitorElm
							|| ce instanceof Capacitor2Elm || ce instanceof VoltageElm))
						continue;
				}
				if (n1 == 0) {
					// look for posts which have a ground connection;
					// our path can go through ground
					int j;
					for (j = 0; j != ce.getPostCount(); j++)
						if (ce.hasGroundConnection(j)
								&& findPath(ce.getNode(j), depth)) {
							used[n1] = false;
							return true;
						}
				}
				int j;
				for (j = 0; j != ce.getPostCount(); j++) {
					// System.out.println(ce + " " + ce.getNode(j));
					if (ce.getNode(j) == n1)
						break;
				}
				if (j == ce.getPostCount())
					continue;
				if (ce.hasGroundConnection(j) && findPath(0, depth)) {
					// System.out.println(ce + " has ground");
					used[n1] = false;
					return true;
				}
				if (type == INDUCT && ce instanceof InductorElm) {
					double c = ce.getCurrent();
					if (j == 0)
						c = -c;
					// System.out.println("matching " + c + " to " +
					// firstElm.getCurrent());
					// System.out.println(ce + " " + firstElm);
					if (Math.abs(c - firstElm.getCurrent()) > 1e-10)
						continue;
				}
				int k;
				for (k = 0; k != ce.getPostCount(); k++) {
					if (j == k)
						continue;
					// System.out.println(ce + " " + ce.getNode(j) + "-" +
					// ce.getNode(k));
					if (ce.getConnection(j, k)
							&& findPath(ce.getNode(k), depth)) {
						// System.out.println("got findpath " + n1);
						used[n1] = false;
						return true;
					}
					// System.out.println("back on findpath " + n1);
				}
			}
			used[n1] = false;
			// System.out.println(n1 + " failed");
			return false;
		}
	}

	public void stop(String s, CircuitElm ce) {
		circuitMatrix = null;
		analyzeFlag = false;
		gui.stop(s, ce);
	}

	// control voltage source vs with voltage from n1 to n2 (must
	// also call stampVoltageSource())
	public void stampVCVS(int n1, int n2, double coef, int vs) {
		int vn = nodeList.size() + vs;
		stampMatrix(vn, n1, coef);
		stampMatrix(vn, n2, -coef);
	}

	// stamp independent voltage source #vs, from n1 to n2, amount v
	public void stampVoltageSource(int n1, int n2, int vs, double v) {
		int vn = nodeList.size() + vs;
		stampMatrix(vn, n1, -1);
		stampMatrix(vn, n2, 1);
		stampRightSide(vn, v);
		stampMatrix(n1, vn, 1);
		stampMatrix(n2, vn, -1);
	}

	// use this if the amount of voltage is going to be updated in doStep()
	public void stampVoltageSource(int n1, int n2, int vs) {
		int vn = nodeList.size() + vs;
		stampMatrix(vn, n1, -1);
		stampMatrix(vn, n2, 1);
		stampRightSide(vn);
		stampMatrix(n1, vn, 1);
		stampMatrix(n2, vn, -1);
	}

	public void updateVoltageSource(int n1, int n2, int vs, double v) {
		int vn = nodeList.size() + vs;
		stampRightSide(vn, v);
	}

	public void stampResistor(int n1, int n2, double r) {
		double r0 = 1 / r;
		if (Double.isNaN(r0) || Double.isInfinite(r0)) {
			System.out.print("bad resistance " + r + " " + r0 + "\n");
			int a = 0;
			a /= a;
		}
		stampMatrix(n1, n1, r0);
		stampMatrix(n2, n2, r0);
		stampMatrix(n1, n2, -r0);
		stampMatrix(n2, n1, -r0);
	}

	public void stampConductance(int n1, int n2, double r0) {
		stampMatrix(n1, n1, r0);
		stampMatrix(n2, n2, r0);
		stampMatrix(n1, n2, -r0);
		stampMatrix(n2, n1, -r0);
	}

	// current from cn1 to cn2 is equal to voltage from vn1 to 2, divided by g
	public void stampVCCurrentSource(int cn1, int cn2, int vn1, int vn2, double g) {
		stampMatrix(cn1, vn1, g);
		stampMatrix(cn2, vn2, g);
		stampMatrix(cn1, vn2, -g);
		stampMatrix(cn2, vn1, -g);
	}

	public void stampCurrentSource(int n1, int n2, double i) {
		stampRightSide(n1, -i);
		stampRightSide(n2, i);
	}

	// stamp a current source from n1 to n2 depending on current through vs
	public void stampCCCS(int n1, int n2, int vs, double gain) {
		int vn = nodeList.size() + vs;
		stampMatrix(n1, vn, gain);
		stampMatrix(n2, vn, -gain);
	}

	// stamp value x in row i, column j, meaning that a voltage change
	// of dv in node j will increase the current into node i by x dv.
	// (Unless i or j is a voltage source node.)
	public void stampMatrix(int i, int j, double x) {
		if (i > 0 && j > 0) {
			if (circuitNeedsMap) {
				i = circuitRowInfo[i - 1].mapRow;
				RowInfo ri = circuitRowInfo[j - 1];
				if (ri.type == RowInfo.ROW_CONST) {
					// System.out.println("Stamping constant " + i + " " + j +
					// " " + x);
					circuitRightSide[i] -= x * ri.value;
					return;
				}
				j = ri.mapCol;
				// System.out.println("stamping " + i + " " + j + " " + x);
			} else {
				i--;
				j--;
			}
			circuitMatrix[i][j] += x;
		}
	}

	// stamp value x on the right side of row i, representing an
	// independent current source flowing into node i
	public void stampRightSide(int i, double x) {
		if (i > 0) {
			if (circuitNeedsMap) {
				i = circuitRowInfo[i - 1].mapRow;
				// System.out.println("stamping " + i + " " + x);
			} else
				i--;
			circuitRightSide[i] += x;
		}
	}

	// indicate that the value on the right side of row i changes in doStep()
	public void stampRightSide(int i) {
		// System.out.println("rschanges true " + (i-1));
		if (i > 0)
			circuitRowInfo[i - 1].rsChanges = true;
	}

	// indicate that the values on the left side of row i change in doStep()
	public void stampNonLinear(int i) {
		if (i > 0)
			circuitRowInfo[i - 1].lsChanges = true;
	}

	void runCircuit() {
		if (circuitMatrix == null || elmList.size() == 0) {
			circuitMatrix = null;
			return;
		}
		int iter;
		// int maxIter = getIterCount();
		boolean debugprint = dumpMatrix;
		dumpMatrix = false;
		long steprate = (long) (160 * gui.getIterCount());
		long tm = System.currentTimeMillis();
		long lit = lastIterTime;
		if (1000 >= steprate * (tm - lastIterTime))
			return;

		for (iter = 1;; iter++) {
			int i, j, k, subiter;
			for (i = 0; i != elmList.size(); i++) {
				CircuitElm ce = getElm(i);
				ce.startIteration();
			}
			steps++;
			final int subiterCount = 5000;
			for (subiter = 0; subiter != subiterCount; subiter++) {
				converged = true;
				subIterations = subiter;
				for (i = 0; i != circuitMatrixSize; i++)
					circuitRightSide[i] = origRightSide[i];
				if (circuitNonLinear) {
					for (i = 0; i != circuitMatrixSize; i++)
						for (j = 0; j != circuitMatrixSize; j++)
							circuitMatrix[i][j] = origMatrix[i][j];
				}
				for (i = 0; i != elmList.size(); i++) {
					CircuitElm ce = getElm(i);
					ce.doStep();
				}
				if (gui.stopMessage != null)
					return;
				boolean printit = debugprint;
				debugprint = false;
				for (j = 0; j != circuitMatrixSize; j++) {
					for (i = 0; i != circuitMatrixSize; i++) {
						double x = circuitMatrix[i][j];
						if (Double.isNaN(x) || Double.isInfinite(x)) {
							stop("nan/infinite matrix!", null);
							return;
						}
					}
				}
				if (printit) {
					for (j = 0; j != circuitMatrixSize; j++) {
						for (i = 0; i != circuitMatrixSize; i++)
							System.out.print(circuitMatrix[j][i] + ",");
						System.out.print("  " + circuitRightSide[j] + "\n");
					}
					System.out.print("\n");
				}
				if (circuitNonLinear) {
					if (converged && subiter > 0)
						break;
					if (!lu_factor(circuitMatrix, circuitMatrixSize,
							circuitPermute)) {
						stop("Singular matrix!", null);
						return;
					}
				}
				lu_solve(circuitMatrix, circuitMatrixSize, circuitPermute,
						circuitRightSide);

				for (j = 0; j != circuitMatrixFullSize; j++) {
					RowInfo ri = circuitRowInfo[j];
					double res = 0;
					if (ri.type == RowInfo.ROW_CONST)
						res = ri.value;
					else
						res = circuitRightSide[ri.mapCol];
					/*
					 * System.out.println(j + " " + res + " " + ri.type + " " +
					 * ri.mapCol);
					 */
					if (Double.isNaN(res)) {
						converged = false;
						// debugprint = true;
						break;
					}
					if (j < nodeList.size() - 1) {
						CircuitNode cn = getCircuitNode(j + 1);
						for (k = 0; k != cn.links.size(); k++) {
							CircuitNodeLink cnl = (CircuitNodeLink) cn.links
									.elementAt(k);
							cnl.elm.setNodeVoltage(cnl.num, res);
						}
					} else {
						int ji = j - (nodeList.size() - 1);
						// System.out.println("setting vsrc " + ji + " to " +
						// res);
						voltageSources[ji].setCurrent(ji, res);
					}
				}
				if (!circuitNonLinear)
					break;

			}// end for (subiter = 0; subiter != subiterCount; subiter++)

			if (subiter > 5)
				System.out
						.print("converged after " + subiter + " iterations\n");
			if (subiter == subiterCount) {
				stop("Convergence failed!", null);
				break;
			}
			t += timeStep;
			for (i = 0; i != gui.scopeCount; i++)
				gui.scopes[i].timeStep();
			tm = System.currentTimeMillis();
			lit = tm;
			if (iter * 1000 >= steprate * (tm - lastIterTime)
					|| (tm - lastFrameTime > 500))
				break;
		} // for (iter = 1; ; iter++)
		lastIterTime = lit;
		// System.out.println((System.currentTimeMillis()-lastFrameTime)/(double)
		// iter);
	}

	int min(int a, int b) {
		return (a < b) ? a : b;
	}

	int max(int a, int b) {
		return (a > b) ? a : b;
	}

	void editFuncPoint(int x, int y) {
		// XXX
		// cv.repaint(pause);
	}

	// public void componentHidden(ComponentEvent e){}
	// public void componentMoved(ComponentEvent e){}
	// public void componentShown(ComponentEvent e) {
	// //cv.repaint();
	// }

	// public void componentResized(ComponentEvent e) {
	// // handleResize();
	// //cv.repaint(100);
	// }

	public void resetAction() {
		int i;
		for (i = 0; i != elmList.size(); i++)
			getElm(i).reset();
		for (i = 0; i != gui.scopeCount; i++)
			gui.scopes[i].resetGraph();
		// TODO: Will need to do IE bug fix here?
		analyzeFlag = true;
		t = 0;
		gui.stoppedCheck.setState(false);
	}

	public String dumpCircuit() {
		int i;
		int f = (gui.dotsCheckItem.getState()) ? 1 : 0;
		f |= (gui.smallGridCheckItem.getState()) ? 2 : 0;
		f |= (gui.voltsCheckItem.getState()) ? 0 : 4;
		f |= (gui.powerCheckItem.getState()) ? 8 : 0;
		f |= (gui.showValuesCheckItem.getState()) ? 0 : 16;
		// 32 = linear scale in afilter
		String dump = "$ " + f + " " + timeStep + " " + gui.getIterCount()
				+ " " + gui.currentBar.getValue() + " "
				+ CircuitElm.voltageRange + " " + gui.powerBar.getValue()
				+ "\n";

		for (i = 0; i != elmList.size(); i++)
			dump += getElm(i).dump() + "\n";
		for (i = 0; i != gui.scopeCount; i++) {
			String d = gui.scopes[i].dump();
			if (d != null)
				dump += d + "\n";
		}
		if (hintType != -1)
			dump += "h " + hintType + " " + hintItem1 + " " + hintItem2 + "\n";
		return dump;
	}

	public void getSetupList(final boolean openDefault) {

		String url;
		url = GWT.getModuleBaseURL() + "setuplist.txt" + "?v="
				+ random.nextInt();
		RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET,
				url);
		try {
			requestBuilder.sendRequest(null, new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					GWT.log("File Error Response", exception);
				}

				public void onResponseReceived(Request request,
						Response response) {
					// processing goes here
					if (response.getStatusCode() == Response.SC_OK) {
						String text = response.getText();
						gui.processSetupList(text.getBytes(), text.length(),
								openDefault);
						// end or processing
					} else
						GWT.log("Bad file server response:"
								+ response.getStatusText());
				}
			});
		} catch (RequestException e) {
			GWT.log("failed file reading", e);
		}
	}


	public void readSetup(String text, boolean centre) {
		readSetup(text, false, centre);
	}

	public void readSetup(String text, boolean retain, boolean centre) {
		readSetup(text.getBytes(), text.length(), retain, centre);
		// IES - remove interaction
		// titleLabel.setText("untitled");
	}

	public void readSetupFile(String str, String title, boolean centre) {
		t = 0;
		System.out.println(str);
		// try {
		// TODO: Maybe think about some better approach to cache management!
		String url = GWT.getModuleBaseURL() + "circuits/" + str + "?v="
				+ random.nextInt();
		loadFileFromURL(url, centre);
		// URL url = new URL(getCodeBase() + "circuits/" + str);
		// ByteArrayOutputStream ba = readUrlData(url);
		// readSetup(ba.toByteArray(), ba.size(), false);
		// } catch (Exception e1) {
		// try {
		// URL url = getClass().getClassLoader().getResource(
		// "circuits/" + str);
		// ByteArrayOutputStream ba = readUrlData(url);
		// readSetup(ba.toByteArray(), ba.size(), false);
		// } catch (Exception e) {
		// e.printStackTrace();
		// stop("Unable to read " + str + "!", null);
		// }
		// }
		// IES - remove interaction
		// titleLabel.setText(title);
	}

	void loadFileFromURL(String url, final boolean centre) {
		RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET,
				url);
		try {
			requestBuilder.sendRequest(null, new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					GWT.log("File Error Response", exception);
				}

				public void onResponseReceived(Request request,
						Response response) {
					if (response.getStatusCode() == Response.SC_OK) {
						String text = response.getText();
						readSetup(text.getBytes(), text.length(), false, centre);
					} else
						GWT.log("Bad file server response:"
								+ response.getStatusText());
				}
			});
		} catch (RequestException e) {
			GWT.log("failed file reading", e);
		}

	}

	public void readSetup(byte b[], int len, boolean retain, boolean centre) {
		int i;
		if (!retain) {
			for (i = 0; i != elmList.size(); i++) {
				CircuitElm ce = getElm(i);
				ce.delete();
			}
			elmList.removeAllElements();
			hintType = -1;
			timeStep = 5e-6;
			CircuitElm.voltageRange = 5;
			
			gui.dotsCheckItem.setState(false);
			gui.smallGridCheckItem.setState(false);
			gui.powerCheckItem.setState(false);
			gui.voltsCheckItem.setState(true);
			gui.showValuesCheckItem.setState(true);
			gui.setGrid();
			gui.speedBar.setValue(117); // 57
			gui.currentBar.setValue(50);
			gui.powerBar.setValue(50);
			gui.scopeCount = 0;
		
		}
		// cv.repaint();
		int p;
		for (p = 0; p < len;) {
			int l;
			int linelen = len - p; // IES - changed to allow the last line to
									// not end with a delim.
			for (l = 0; l != len - p; l++)
				if (b[l + p] == '\n' || b[l + p] == '\r') {
					linelen = l++;
					if (l + p < b.length && b[l + p] == '\n')
						l++;
					break;
				}
			String line = new String(b, p, linelen);
			StringTokenizer st = new StringTokenizer(line, " +\t\n\r\f");
			while (st.hasMoreTokens()) {
				String type = st.nextToken();
				int tint = type.charAt(0);
				try {
					if (tint == 'o') {
						Scope sc = new Scope(this);
						sc.position = gui.scopeCount;
						sc.undump(st);
						gui.scopes[gui.scopeCount++] = sc;
						break;
					}
					if (tint == 'h') {
						readHint(st);
						break;
					}
					if (tint == '$') {
						readOptions(st);
						break;
					}
					if (tint == '%' || tint == '?' || tint == 'B') {
						// ignore afilter-specific stuff
						break;
					}
					if (tint >= '0' && tint <= '9')
						tint = new Integer(type).intValue();
					int x1 = new Integer(st.nextToken()).intValue();
					int y1 = new Integer(st.nextToken()).intValue();
					int x2 = new Integer(st.nextToken()).intValue();
					int y2 = new Integer(st.nextToken()).intValue();
					int f = new Integer(st.nextToken()).intValue();
					// The following lines are functionally replaced by the call
					// to
					// createCe below
					// Class cls = dumpTypes[tint];
					// if (cls == null) {
					// System.out.println("unrecognized dump type: " + type);
					// break;
					// }
					// // find element class
					// Class carr[] = new Class[6];
					// //carr[0] = getClass();
					// carr[0] = carr[1] = carr[2] = carr[3] = carr[4] =
					// int.class;
					// carr[5] = StringTokenizer.class;
					// Constructor cstr = null;
					// cstr = cls.getConstructor(carr);
					//
					// // invoke constructor with starting coordinates
					// Object oarr[] = new Object[6];
					// //oarr[0] = this;
					// oarr[0] = new Integer(x1);
					// oarr[1] = new Integer(y1);
					// oarr[2] = new Integer(x2);
					// oarr[3] = new Integer(y2);
					// oarr[4] = new Integer(f );
					// oarr[5] = st;
					// ce = (CircuitElm) cstr.newInstance(oarr);
					CircuitElm newce = createCe(tint, x1, y1, x2, y2, f, st);
					if (newce == null) {
						System.out.println("unrecognized dump type: " + type);
						break;
					}
					newce.setPoints();
					elmList.addElement(newce);
					// } catch (java.lang.reflect.InvocationTargetException ee)
					// {
					// ee.getTargetException().printStackTrace();
					// break;
				} catch (Exception ee) {
					ee.printStackTrace();
					break;
				}
				break;
			}
			p += l;

		}
		gui.setPowerBarEnable();
		enableItems();
		// if (!retain)
		// handleResize(); // for scopes
		needAnalyze();
		if (centre)
			gui.centreCircuit();
	}

	void readHint(StringTokenizer st) {
		hintType = new Integer(st.nextToken()).intValue();
		hintItem1 = new Integer(st.nextToken()).intValue();
		hintItem2 = new Integer(st.nextToken()).intValue();
	}

	void readOptions(StringTokenizer st) {
		int flags = new Integer(st.nextToken()).intValue();
		// IES - remove inteaction
		gui.dotsCheckItem.setState((flags & 1) != 0);
		gui.smallGridCheckItem.setState((flags & 2) != 0);
		gui.voltsCheckItem.setState((flags & 4) == 0);
		gui.powerCheckItem.setState((flags & 8) == 8);
		gui.showValuesCheckItem.setState((flags & 16) == 0);
		timeStep = new Double(st.nextToken()).doubleValue();
		double sp = new Double(st.nextToken()).doubleValue();
		int sp2 = (int) (Math.log(10 * sp) * 24 + 61.5);
		// int sp2 = (int) (Math.log(sp)*24+1.5);
		gui.speedBar.setValue(sp2);
		gui.currentBar.setValue(new Integer(st.nextToken()).intValue());
		CircuitElm.voltageRange = new Double(st.nextToken()).doubleValue();

		try {
			gui.powerBar.setValue(new Integer(st.nextToken()).intValue());
		} catch (Exception e) {
		}
		gui.setGrid();
	}

	public int locateElm(CircuitElm elm) {
		int i;
		for (i = 0; i != elmList.size(); i++)
			if (elm == elmList.elementAt(i))
				return i;
		return -1;
	}

	void enableItems() {
		// if (powerCheckItem.getState()) {
		// powerBar.enable();
		// powerLabel.enable();
		// } else {
		// powerBar.disable();
		// powerLabel.disable();
		// }
		// enableUndoRedo();
	}

	// factors a matrix into upper and lower triangular matrices by
	// gaussian elimination. On entry, a[0..n-1][0..n-1] is the
	// matrix to be factored. ipvt[] returns an integer vector of pivot
	// indices, used in the lu_solve() routine.
	boolean lu_factor(double a[][], int n, int ipvt[]) {
		double scaleFactors[];
		int i, j, k;

		scaleFactors = new double[n];

		// divide each row by its largest element, keeping track of the
		// scaling factors
		for (i = 0; i != n; i++) {
			double largest = 0;
			for (j = 0; j != n; j++) {
				double x = Math.abs(a[i][j]);
				if (x > largest)
					largest = x;
			}
			// if all zeros, it's a singular matrix
			if (largest == 0)
				return false;
			scaleFactors[i] = 1.0 / largest;
		}

		// use Crout's method; loop through the columns
		for (j = 0; j != n; j++) {

			// calculate upper triangular elements for this column
			for (i = 0; i != j; i++) {
				double q = a[i][j];
				for (k = 0; k != i; k++)
					q -= a[i][k] * a[k][j];
				a[i][j] = q;
			}

			// calculate lower triangular elements for this column
			double largest = 0;
			int largestRow = -1;
			for (i = j; i != n; i++) {
				double q = a[i][j];
				for (k = 0; k != j; k++)
					q -= a[i][k] * a[k][j];
				a[i][j] = q;
				double x = Math.abs(q);
				if (x >= largest) {
					largest = x;
					largestRow = i;
				}
			}

			// pivoting
			if (j != largestRow) {
				double x;
				for (k = 0; k != n; k++) {
					x = a[largestRow][k];
					a[largestRow][k] = a[j][k];
					a[j][k] = x;
				}
				scaleFactors[largestRow] = scaleFactors[j];
			}

			// keep track of row interchanges
			ipvt[j] = largestRow;

			// avoid zeros
			if (a[j][j] == 0.0) {
				System.out.println("avoided zero");
				a[j][j] = 1e-18;
			}

			if (j != n - 1) {
				double mult = 1.0 / a[j][j];
				for (i = j + 1; i != n; i++)
					a[i][j] *= mult;
			}
		}
		return true;
	}

	// Solves the set of n linear equations using a LU factorization
	// previously performed by lu_factor. On input, b[0..n-1] is the right
	// hand side of the equations, and on output, contains the solution.
	void lu_solve(double a[][], int n, int ipvt[], double b[]) {
		int i;

		// find first nonzero b element
		for (i = 0; i != n; i++) {
			int row = ipvt[i];

			double swap = b[row];
			b[row] = b[i];
			b[i] = swap;
			if (swap != 0)
				break;
		}

		int bi = i++;
		for (; i < n; i++) {
			int row = ipvt[i];
			int j;
			double tot = b[row];

			b[row] = b[i];
			// forward substitution using the lower triangular matrix
			for (j = bi; j < i; j++)
				tot -= a[i][j] * b[j];
			b[i] = tot;
		}
		for (i = n - 1; i >= 0; i--) {
			double tot = b[i];

			// back-substitution using the upper triangular matrix
			int j;
			for (j = i + 1; j != n; j++)
				tot -= a[i][j] * b[j];
			b[i] = tot / a[i][i];
		}
	}

	public void createNewLoadFile() {
		// This is a hack to fix what IMHO is a bug in the <INPUT FILE element
		// reloading the same file doesn't create a change event so importing
		// the same file twice
		// doesn't work unless you destroy the original input element and
		// replace it with a new one
		int idx = gui.verticalPanel.getWidgetIndex(loadFileInput);
		LoadFile newlf = new LoadFile(this);
		gui.verticalPanel.insert(newlf, idx);
		gui.verticalPanel.remove(idx + 1);
		loadFileInput = newlf;
	}

	public static CircuitElm createCe(int tint, int x1, int y1, int x2, int y2,
			int f, StringTokenizer st) {
		if (tint == 'g')
			return (CircuitElm) new GroundElm(x1, y1, x2, y2, f, st);
		if (tint == 'r')
			return (CircuitElm) new ResistorElm(x1, y1, x2, y2, f, st);
		if (tint == 'R')
			return (CircuitElm) new RailElm(x1, y1, x2, y2, f, st);
		if (tint == 's')
			return (CircuitElm) new SwitchElm(x1, y1, x2, y2, f, st);
		if (tint == 'S')
			return (CircuitElm) new Switch2Elm(x1, y1, x2, y2, f, st);
		if (tint == 't')
			return (CircuitElm) new TransistorElm(x1, y1, x2, y2, f, st);
		if (tint == 'w')
			return (CircuitElm) new WireElm(x1, y1, x2, y2, f, st);
		if (tint == 'c')
			return (CircuitElm) new CapacitorElm(x1, y1, x2, y2, f, st);
		if (tint == 'l')
			return (CircuitElm) new InductorElm(x1, y1, x2, y2, f, st);
		if (tint == 'v')
			return (CircuitElm) new VoltageElm(x1, y1, x2, y2, f, st);
		if (tint == 172)
			return (CircuitElm) new VarRailElm(x1, y1, x2, y2, f, st);
		if (tint == 174)
			return (CircuitElm) new PotElm(x1, y1, x2, y2, f, st);
		if (tint == 'O')
			return (CircuitElm) new OutputElm(x1, y1, x2, y2, f, st);
		if (tint == 'i')
			return (CircuitElm) new CurrentElm(x1, y1, x2, y2, f, st);
		if (tint == 'p')
			return (CircuitElm) new ProbeElm(x1, y1, x2, y2, f, st);
		if (tint == 'd')
			return (CircuitElm) new DiodeElm(x1, y1, x2, y2, f, st);
		if (tint == 'z')
			return (CircuitElm) new ZenerElm(x1, y1, x2, y2, f, st);
		if (tint == 170)
			return (CircuitElm) new SweepElm(x1, y1, x2, y2, f, st);
		if (tint == 162)
			return (CircuitElm) new LEDElm(x1, y1, x2, y2, f, st);
		if (tint == 'A')
			return (CircuitElm) new AntennaElm(x1, y1, x2, y2, f, st);
		if (tint == 'L')
			return (CircuitElm) new LogicInputElm(x1, y1, x2, y2, f, st);
		if (tint == 'M')
			return (CircuitElm) new LogicOutputElm(x1, y1, x2, y2, f, st);
		if (tint == 'T')
			return (CircuitElm) new TransformerElm(x1, y1, x2, y2, f, st);
		if (tint == 169)
			return (CircuitElm) new TappedTransformerElm(x1, y1, x2, y2, f, st);
		if (tint == 171)
			return (CircuitElm) new TransLineElm(x1, y1, x2, y2, f, st);
		if (tint == 178)
			return (CircuitElm) new RelayElm(x1, y1, x2, y2, f, st);
		if (tint == 'm')
			return (CircuitElm) new MemristorElm(x1, y1, x2, y2, f, st);
		if (tint == 187)
			return (CircuitElm) new SparkGapElm(x1, y1, x2, y2, f, st);
		if (tint == 200)
			return (CircuitElm) new AMElm(x1, y1, x2, y2, f, st);
		if (tint == 201)
			return (CircuitElm) new FMElm(x1, y1, x2, y2, f, st);
		if (tint == 181)
			return (CircuitElm) new LampElm(x1, y1, x2, y2, f, st);
		if (tint == 'a')
			return (CircuitElm) new OpAmpElm(x1, y1, x2, y2, f, st);
		if (tint == 'f')
			return (CircuitElm) new MosfetElm(x1, y1, x2, y2, f, st);
		if (tint == 'j')
			return (CircuitElm) new JfetElm(x1, y1, x2, y2, f, st);
		if (tint == 159)
			return (CircuitElm) new AnalogSwitchElm(x1, y1, x2, y2, f, st);
		if (tint == 160)
			return (CircuitElm) new AnalogSwitch2Elm(x1, y1, x2, y2, f, st);
		if (tint == 180)
			return (CircuitElm) new TriStateElm(x1, y1, x2, y2, f, st);
		if (tint == 182)
			return (CircuitElm) new SchmittElm(x1, y1, x2, y2, f, st);
		if (tint == 183)
			return (CircuitElm) new InvertingSchmittElm(x1, y1, x2, y2, f, st);
		if (tint == 177)
			return (CircuitElm) new SCRElm(x1, y1, x2, y2, f, st);
		if (tint == 203)
			return (CircuitElm) new DiacElm(x1, y1, x2, y2, f, st);
		if (tint == 206)
			return (CircuitElm) new TriacElm(x1, y1, x2, y2, f, st);
		if (tint == 173)
			return (CircuitElm) new TriodeElm(x1, y1, x2, y2, f, st);
		if (tint == 175)
			return (CircuitElm) new TunnelDiodeElm(x1, y1, x2, y2, f, st);
		if (tint == 179)
			return (CircuitElm) new CC2Elm(x1, y1, x2, y2, f, st);
		if (tint == 'I')
			return (CircuitElm) new InverterElm(x1, y1, x2, y2, f, st);
		if (tint == 151)
			return (CircuitElm) new NandGateElm(x1, y1, x2, y2, f, st);
		if (tint == 153)
			return (CircuitElm) new NorGateElm(x1, y1, x2, y2, f, st);
		if (tint == 150)
			return (CircuitElm) new AndGateElm(x1, y1, x2, y2, f, st);
		if (tint == 152)
			return (CircuitElm) new OrGateElm(x1, y1, x2, y2, f, st);
		if (tint == 154)
			return (CircuitElm) new XorGateElm(x1, y1, x2, y2, f, st);
		if (tint == 155)
			return (CircuitElm) new DFlipFlopElm(x1, y1, x2, y2, f, st);
		if (tint == 156)
			return (CircuitElm) new JKFlipFlopElm(x1, y1, x2, y2, f, st);
		if (tint == 157)
			return (CircuitElm) new SevenSegElm(x1, y1, x2, y2, f, st);
		if (tint == 184)
			return (CircuitElm) new MultiplexerElm(x1, y1, x2, y2, f, st);
		if (tint == 185)
			return (CircuitElm) new DeMultiplexerElm(x1, y1, x2, y2, f, st);
		if (tint == 189)
			return (CircuitElm) new SipoShiftElm(x1, y1, x2, y2, f, st);
		if (tint == 186)
			return (CircuitElm) new PisoShiftElm(x1, y1, x2, y2, f, st);
		if (tint == 161)
			return (CircuitElm) new PhaseCompElm(x1, y1, x2, y2, f, st);
		if (tint == 164)
			return (CircuitElm) new CounterElm(x1, y1, x2, y2, f, st);
		if (tint == 163)
			return (CircuitElm) new DecadeElm(x1, y1, x2, y2, f, st);
		if (tint == 165)
			return (CircuitElm) new TimerElm(x1, y1, x2, y2, f, st);
		if (tint == 166)
			return (CircuitElm) new DACElm(x1, y1, x2, y2, f, st);
		if (tint == 167)
			return (CircuitElm) new ADCElm(x1, y1, x2, y2, f, st);
		if (tint == 168)
			return (CircuitElm) new LatchElm(x1, y1, x2, y2, f, st);
		if (tint == 188)
			return (CircuitElm) new SeqGenElm(x1, y1, x2, y2, f, st);
		if (tint == 158)
			return (CircuitElm) new VCOElm(x1, y1, x2, y2, f, st);
		if (tint == 'b')
			return (CircuitElm) new BoxElm(x1, y1, x2, y2, f, st);
		if (tint == 'x')
			return (CircuitElm) new TextElm(x1, y1, x2, y2, f, st);
		if (tint == 193)
			return (CircuitElm) new TFlipFlopElm(x1, y1, x2, y2, f, st);
		if (tint == 197)
			return (CircuitElm) new SevenSegDecoderElm(x1, y1, x2, y2, f, st);
		if (tint == 196)
			return (CircuitElm) new FullAdderElm(x1, y1, x2, y2, f, st);
		if (tint == 195)
			return (CircuitElm) new HalfAdderElm(x1, y1, x2, y2, f, st);
		if (tint == 194)
			return (CircuitElm) new MonostableElm(x1, y1, x2, y2, f, st);
		if (tint == 202)
			return (CircuitElm) new LoudSpeakerElm(x1, y1, x2, y2, f, st);

		return null;
	}

	public static CircuitElm constructElement(String n, int x1, int y1) {
		if (n == "GroundElm")
			return (CircuitElm) new GroundElm(x1, y1);
		if (n == "ResistorElm")
			return (CircuitElm) new ResistorElm(x1, y1);
		if (n == "RailElm")
			return (CircuitElm) new RailElm(x1, y1);
		if (n == "SwitchElm")
			return (CircuitElm) new SwitchElm(x1, y1);
		if (n == "Switch2Elm")
			return (CircuitElm) new Switch2Elm(x1, y1);
		if (n == "NTransistorElm")
			return (CircuitElm) new NTransistorElm(x1, y1);
		if (n == "PTransistorElm")
			return (CircuitElm) new PTransistorElm(x1, y1);
		if (n == "WireElm")
			return (CircuitElm) new WireElm(x1, y1);
		if (n == "CapacitorElm")
			return (CircuitElm) new CapacitorElm(x1, y1);
		if (n == "Capacitor2Elm")
			return (CircuitElm) new Capacitor2Elm(x1, y1);
		if (n == "InductorElm")
			return (CircuitElm) new InductorElm(x1, y1);
		if (n == "DCVoltageElm")
			return (CircuitElm) new DCVoltageElm(x1, y1);
		if (n == "VarRailElm")
			return (CircuitElm) new VarRailElm(x1, y1);
		if (n == "PotElm")
			return (CircuitElm) new PotElm(x1, y1);
		if (n == "OutputElm")
			return (CircuitElm) new OutputElm(x1, y1);
		if (n == "CurrentElm")
			return (CircuitElm) new CurrentElm(x1, y1);
		if (n == "ProbeElm")
			return (CircuitElm) new ProbeElm(x1, y1);
		if (n == "DiodeElm")
			return (CircuitElm) new DiodeElm(x1, y1);
		if (n == "ZenerElm")
			return (CircuitElm) new ZenerElm(x1, y1);
		if (n == "ACVoltageElm")
			return (CircuitElm) new ACVoltageElm(x1, y1);
		if (n == "ACRailElm")
			return (CircuitElm) new ACRailElm(x1, y1);
		if (n == "SquareRailElm")
			return (CircuitElm) new SquareRailElm(x1, y1);
		if (n == "SweepElm")
			return (CircuitElm) new SweepElm(x1, y1);
		if (n == "LEDElm")
			return (CircuitElm) new LEDElm(x1, y1);
		if (n == "AntennaElm")
			return (CircuitElm) new AntennaElm(x1, y1);
		if (n == "LogicInputElm")
			return (CircuitElm) new LogicInputElm(x1, y1);
		if (n == "LogicOutputElm")
			return (CircuitElm) new LogicOutputElm(x1, y1);
		if (n == "TransformerElm")
			return (CircuitElm) new TransformerElm(x1, y1);
		if (n == "TappedTransformerElm")
			return (CircuitElm) new TappedTransformerElm(x1, y1);
		if (n == "TransLineElm")
			return (CircuitElm) new TransLineElm(x1, y1);
		if (n == "RelayElm")
			return (CircuitElm) new RelayElm(x1, y1);
		if (n == "MemristorElm")
			return (CircuitElm) new MemristorElm(x1, y1);
		if (n == "SparkGapElm")
			return (CircuitElm) new SparkGapElm(x1, y1);
		if (n == "ClockElm")
			return (CircuitElm) new ClockElm(x1, y1);
		if (n == "AMElm")
			return (CircuitElm) new AMElm(x1, y1);
		if (n == "FMElm")
			return (CircuitElm) new FMElm(x1, y1);
		if (n == "LampElm")
			return (CircuitElm) new LampElm(x1, y1);
		if (n == "PushSwitchElm")
			return (CircuitElm) new PushSwitchElm(x1, y1);
		if (n == "OpAmpElm")
			return (CircuitElm) new OpAmpElm(x1, y1);
		if (n == "OpAmpSwapElm")
			return (CircuitElm) new OpAmpSwapElm(x1, y1);
		if (n == "NMosfetElm")
			return (CircuitElm) new NMosfetElm(x1, y1);
		if (n == "PMosfetElm")
			return (CircuitElm) new PMosfetElm(x1, y1);
		if (n == "NJfetElm")
			return (CircuitElm) new NJfetElm(x1, y1);
		if (n == "PJfetElm")
			return (CircuitElm) new PJfetElm(x1, y1);
		if (n == "AnalogSwitchElm")
			return (CircuitElm) new AnalogSwitchElm(x1, y1);
		if (n == "AnalogSwitch2Elm")
			return (CircuitElm) new AnalogSwitch2Elm(x1, y1);
		if (n == "SchmittElm")
			return (CircuitElm) new SchmittElm(x1, y1);
		if (n == "InvertingSchmittElm")
			return (CircuitElm) new InvertingSchmittElm(x1, y1);
		if (n == "TriStateElm")
			return (CircuitElm) new TriStateElm(x1, y1);
		if (n == "SCRElm")
			return (CircuitElm) new SCRElm(x1, y1);
		if (n == "DiacElm")
			return (CircuitElm) new DiacElm(x1, y1);
		if (n == "TriacElm")
			return (CircuitElm) new TriacElm(x1, y1);
		if (n == "TriodeElm")
			return (CircuitElm) new TriodeElm(x1, y1);
		if (n == "TunnelDiodeElm")
			return (CircuitElm) new TunnelDiodeElm(x1, y1);
		if (n == "CC2Elm")
			return (CircuitElm) new CC2Elm(x1, y1);
		if (n == "CC2NegElm")
			return (CircuitElm) new CC2NegElm(x1, y1);
		if (n == "InverterElm")
			return (CircuitElm) new InverterElm(x1, y1);
		if (n == "NandGateElm")
			return (CircuitElm) new NandGateElm(x1, y1);
		if (n == "NorGateElm")
			return (CircuitElm) new NorGateElm(x1, y1);
		if (n == "AndGateElm")
			return (CircuitElm) new AndGateElm(x1, y1);
		if (n == "OrGateElm")
			return (CircuitElm) new OrGateElm(x1, y1);
		if (n == "XorGateElm")
			return (CircuitElm) new XorGateElm(x1, y1);
		if (n == "DFlipFlopElm")
			return (CircuitElm) new DFlipFlopElm(x1, y1);
		if (n == "JKFlipFlopElm")
			return (CircuitElm) new JKFlipFlopElm(x1, y1);
		if (n == "SevenSegElm")
			return (CircuitElm) new SevenSegElm(x1, y1);
		if (n == "MultiplexerElm")
			return (CircuitElm) new MultiplexerElm(x1, y1);
		if (n == "DeMultiplexerElm")
			return (CircuitElm) new DeMultiplexerElm(x1, y1);
		if (n == "SipoShiftElm")
			return (CircuitElm) new SipoShiftElm(x1, y1);
		if (n == "PisoShiftElm")
			return (CircuitElm) new PisoShiftElm(x1, y1);
		if (n == "PhaseCompElm")
			return (CircuitElm) new PhaseCompElm(x1, y1);
		if (n == "CounterElm")
			return (CircuitElm) new CounterElm(x1, y1);
		if (n == "DecadeElm")
			return (CircuitElm) new DecadeElm(x1, y1);
		if (n == "TimerElm")
			return (CircuitElm) new TimerElm(x1, y1);
		if (n == "DACElm")
			return (CircuitElm) new DACElm(x1, y1);
		if (n == "ADCElm")
			return (CircuitElm) new ADCElm(x1, y1);
		if (n == "LatchElm")
			return (CircuitElm) new LatchElm(x1, y1);
		if (n == "SeqGenElm")
			return (CircuitElm) new SeqGenElm(x1, y1);
		if (n == "VCOElm")
			return (CircuitElm) new VCOElm(x1, y1);
		if (n == "BoxElm")
			return (CircuitElm) new BoxElm(x1, y1);
		if (n == "TextElm")
			return (CircuitElm) new TextElm(x1, y1);
		if (n == "TFlipFlopElm")
			return (CircuitElm) new TFlipFlopElm(x1, y1);
		if (n == "SevenSegDecoderElm")
			return (CircuitElm) new SevenSegDecoderElm(x1, y1);
		if (n == "FullAdderElm")
			return (CircuitElm) new FullAdderElm(x1, y1);
		if (n == "HalfAdderElm")
			return (CircuitElm) new HalfAdderElm(x1, y1);
		if (n == "MonostableElm")
			return (CircuitElm) new MonostableElm(x1, y1);
		if (n == "LoudSpeakerElm")
			return (CircuitElm) new LoudSpeakerElm(x1, y1);

		return null;
	}

}
