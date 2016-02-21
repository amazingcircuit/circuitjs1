package com.lushprojects.circuitjs1.client.gui;

import static com.google.gwt.event.dom.client.KeyCodes.KEY_A;
import static com.google.gwt.event.dom.client.KeyCodes.KEY_BACKSPACE;
import static com.google.gwt.event.dom.client.KeyCodes.KEY_C;
import static com.google.gwt.event.dom.client.KeyCodes.KEY_DELETE;
import static com.google.gwt.event.dom.client.KeyCodes.KEY_ENTER;
import static com.google.gwt.event.dom.client.KeyCodes.KEY_ESCAPE;
import static com.google.gwt.event.dom.client.KeyCodes.KEY_SPACE;
import static com.google.gwt.event.dom.client.KeyCodes.KEY_V;
import static com.google.gwt.event.dom.client.KeyCodes.KEY_X;
import static com.google.gwt.event.dom.client.KeyCodes.KEY_Y;
import static com.google.gwt.event.dom.client.KeyCodes.KEY_Z;

import java.util.Vector;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ContextMenuEvent;
import com.google.gwt.event.dom.client.ContextMenuHandler;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.http.client.URL;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;
import com.google.gwt.user.client.Window.Location;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.lushprojects.circuitjs1.client.circuitjs1;
import com.lushprojects.circuitjs1.client.element.CirSim;
import com.lushprojects.circuitjs1.client.element.CircuitElm;
import com.lushprojects.circuitjs1.client.element.GraphicElm;
import com.lushprojects.circuitjs1.client.element.passive.Capacitor2Elm;
import com.lushprojects.circuitjs1.client.element.passive.CapacitorElm;
import com.lushprojects.circuitjs1.client.element.passive.InductorElm;
import com.lushprojects.circuitjs1.client.element.passive.ResistorElm;
import com.lushprojects.circuitjs1.client.element.passive.SwitchElm;

public class Gui implements MouseDownHandler, MouseMoveHandler, MouseUpHandler,
		ClickHandler, DoubleClickHandler, ContextMenuHandler,
		NativePreviewHandler, MouseOutHandler, MouseWheelHandler {
	/**
	boolean ALLOW_MAIN_MENUBAR = false;
	boolean ALLOW_ELEMENT_MENU = false;
	boolean ALLOW_CONTEXT_MENU = false;
	boolean ALLOW_SCOPE_MENU = false;
	boolean ALLOW_MOUSE_WHEEL = false;
	boolean ALLOW_MOUSE_EDIT = false;
**/
	boolean ALLOW_MAIN_MENUBAR = true;
	boolean ALLOW_ELEMENT_MENU = true;
	boolean ALLOW_CONTEXT_MENU = true;
	boolean ALLOW_SCOPE_MENU = true;
	boolean ALLOW_MOUSE_WHEEL = true;
	boolean ALLOW_MOUSE_EDIT = true;

	CirSim sim;

	public String ctrlMetaKey;
	// static Container main;
	// IES - remove interaction
	// Label titleLabel;
	Button resetButton;
	// Button dumpMatrixButton;
	MenuItem aboutItem;
	MenuItem importFromLocalFileItem, importFromTextItem, exportAsUrlItem,
			exportAsLocalFileItem, exportAsTextItem;
	MenuItem undoItem, redoItem, cutItem, copyItem, pasteItem, selectAllItem,
			optionsItem;
	MenuBar optionsMenuBar;
	public Checkbox stoppedCheck;
	public CheckboxMenuItem dotsCheckItem;
	public CheckboxMenuItem voltsCheckItem;
	public CheckboxMenuItem powerCheckItem;
	public CheckboxMenuItem smallGridCheckItem;
	public CheckboxMenuItem showValuesCheckItem;
	public CheckboxMenuItem conductanceCheckItem;
	public CheckboxMenuItem euroResistorCheckItem;
	public CheckboxMenuItem printableCheckItem;
	public CheckboxMenuItem conventionCheckItem;
	private Label powerLabel;
	public Scrollbar speedBar;
	public Scrollbar currentBar;
	public Scrollbar powerBar;
	MenuBar elmMenuBar;
	MenuItem elmEditMenuItem;
	MenuItem elmCutMenuItem;
	MenuItem elmCopyMenuItem;
	MenuItem elmDeleteMenuItem;
	MenuItem elmScopeMenuItem;
	public MenuBar scopeMenuBar;
	public MenuBar transScopeMenuBar;
	MenuBar mainMenuBar;
	public CheckboxMenuItem scopeVMenuItem;
	public CheckboxMenuItem scopeIMenuItem;
	public CheckboxMenuItem scopeScaleMenuItem;
	public CheckboxMenuItem scopeMaxMenuItem;
	public CheckboxMenuItem scopeMinMenuItem;
	public CheckboxMenuItem scopeFreqMenuItem;
	public CheckboxMenuItem scopeFFTMenuItem;
	public CheckboxMenuItem scopePowerMenuItem;
	public CheckboxMenuItem scopeIbMenuItem;
	public CheckboxMenuItem scopeIcMenuItem;
	public CheckboxMenuItem scopeIeMenuItem;
	public CheckboxMenuItem scopeVbeMenuItem;
	public CheckboxMenuItem scopeVbcMenuItem;
	public CheckboxMenuItem scopeVceMenuItem;
	public CheckboxMenuItem scopeVIMenuItem;
	public CheckboxMenuItem scopeXYMenuItem;
	public CheckboxMenuItem scopeResistMenuItem;
	public CheckboxMenuItem scopeVceIcMenuItem;
	public MenuItem scopeSelectYMenuItem;

	PopupPanel contextPanel = null;
	public int mouseMode = MODE_SELECT;
	public int tempMouseMode = MODE_SELECT;
	String mouseModeStr = "Select";
	static final double pi = 3.14159265358979323846;
	static final int MODE_ADD_ELM = 0;
	static final int MODE_DRAG_ALL = 1;
	public static final int MODE_DRAG_ROW = 2;
	public static final int MODE_DRAG_COLUMN = 3;
	public static final int MODE_DRAG_SELECTED = 4;
	public static final int MODE_DRAG_POST = 5;
	public static final int MODE_SELECT = 6;
	public static final int infoWidth = 120;

	int dragX, dragY, initDragX, initDragY;
	int selectedSource;
	public Rectangle selectedArea;
	public int gridSize;
	int gridMask;
	int gridRound;
	boolean dragging;

	// boolean useBufferedImage;

	int pause = 10;
	public int scopeSelected = -1;
	int menuScope = -1;
	public String stopMessage;
	// Vector setupList;
	public CircuitElm dragElm;
	CircuitElm menuElm;
	public CircuitElm stopElm;
	public CircuitElm mouseElm = null;
	boolean didSwitch = false;
	public int mousePost = -1;
	public CircuitElm plotXElm;
	public CircuitElm plotYElm;
	int draggingPost;
	public SwitchElm heldSwitchElm;

	// public boolean useFrame;
	public int scopeCount;
	public Scope scopes[];
	public int scopeColCount[];
	public static EditDialog editDialog;
	static ExportAsUrlDialog exportAsUrlDialog;
	static ExportAsTextDialog exportAsTextDialog;
	static ExportAsLocalFileDialog exportAsLocalFileDialog;
	static ImportFromTextDialog importFromTextDialog;
	static ScrollValuePopup scrollValuePopup;
	static AboutBox aboutBox;
	// Class dumpTypes[], shortcuts[];
	String shortcuts[];
	public static String muString = "u";
	public static String ohmString = "ohm";
	String clipboard;
	public Rectangle circuitArea;
	Vector<String> undoStack, redoStack;

	DockLayoutPanel layoutPanel;
	public MenuBar menuBar;
	MenuBar fileMenuBar;
	public VerticalPanel verticalPanel;
	private boolean mouseDragging;

	Vector<CheckboxMenuItem> mainMenuItems = new Vector<CheckboxMenuItem>();
	Vector<String> mainMenuItemNames = new Vector<String>();

	Frame iFrame;

	public Canvas cv;
	public Context2d cvcontext;
	Canvas backcv;
	public Context2d backcontext;
	static final int MENUBARHEIGHT = 30;
	public static final int VERTICALPANELWIDTH = 166;
	static final int POSTGRABSQ = 16;

	public String startCircuit = null;
	public String startLabel = null;
	public String startCircuitText = null;

	public Gui(CirSim sim) {
		this.sim = sim;
		shortcuts = new String[127];
	}

	public void generateLayout(boolean printable, boolean convention,
			boolean euro) {
		layoutPanel = new DockLayoutPanel(Unit.PX);

		fileMenuBar = new MenuBar(true);
		importFromLocalFileItem = new MenuItem("Import From Local File",
				new MyCommand("file", "importfromlocalfile"));
		importFromLocalFileItem.setEnabled(LoadFile.isSupported());
		fileMenuBar.addItem(importFromLocalFileItem);
		importFromTextItem = new MenuItem("Import From Text", new MyCommand(
				"file", "importfromtext"));
		fileMenuBar.addItem(importFromTextItem);
		exportAsUrlItem = new MenuItem("Export as Link", new MyCommand("file",
				"exportasurl"));
		fileMenuBar.addItem(exportAsUrlItem);
		exportAsLocalFileItem = new MenuItem("Export as Local File",
				new MyCommand("file", "exportaslocalfile"));
		exportAsLocalFileItem.setEnabled(ExportAsLocalFileDialog
				.downloadIsSupported());
		fileMenuBar.addItem(exportAsLocalFileItem);
		exportAsTextItem = new MenuItem("Export as Text", new MyCommand("file",
				"exportastext"));
		fileMenuBar.addItem(exportAsTextItem);
		fileMenuBar.addSeparator();
		aboutItem = new MenuItem("About", (Command) null);
		fileMenuBar.addItem(aboutItem);
		aboutItem.setScheduledCommand(new MyCommand("file", "about"));

		// fileMenuBar.addItem("Exit", cmd);

		menuBar = new MenuBar();
		menuBar.addItem("File", fileMenuBar);
		verticalPanel = new VerticalPanel();

		// IES - remove interaction
		/*
		 * mainMenu = new PopupMenu(); MenuBar mb = null; if (useFrame) mb = new
		 * MenuBar(); Menu m = new Menu("File"); if (useFrame) mb.add(m); else
		 * mainMenu.add(m);
		 */
		// IES - remove import expoert
		/*
		 * m.add(importItem = getMenuItem("Import")); m.add(exportItem =
		 * getMenuItem("Export")); m.add(exportLinkItem =
		 * getMenuItem("Export Link")); m.addSeparator();
		 */
		// IES - remove interaction

		// m.add(exitItem = getMenuItem("Exit"));
		MenuBar m;
		m = new MenuBar(true);
		final String edithtml = "<div style=\"display:inline-block;width:80px;\">";
		String sn = edithtml + "Undo</div>Ctrl-Z";
		m.addItem(undoItem = new MenuItem(SafeHtmlUtils.fromTrustedString(sn),
				new MyCommand("edit", "undo")));
		// undoItem.setShortcut(new MenuShortcut(KeyEvent.VK_Z));
		sn = edithtml + "Redo</div>Ctrl-Y";
		m.addItem(redoItem = new MenuItem(SafeHtmlUtils.fromTrustedString(sn),
				new MyCommand("edit", "redo")));
		// redoItem.setShortcut(new MenuShortcut(KeyEvent.VK_Z, true));
		m.addSeparator();
		// m.addItem(cutItem = new MenuItem("Cut", new
		// MyCommand("edit","cut")));
		sn = edithtml + "Cut</div>Ctrl-X";
		m.addItem(cutItem = new MenuItem(SafeHtmlUtils.fromTrustedString(sn),
				new MyCommand("edit", "cut")));
		// cutItem.setShortcut(new MenuShortcut(KeyEvent.VK_X));
		sn = edithtml + "Copy</div>Ctrl-C";
		m.addItem(copyItem = new MenuItem(SafeHtmlUtils.fromTrustedString(sn),
				new MyCommand("edit", "copy")));
		sn = edithtml + "Paste</div>Ctrl-V";
		m.addItem(pasteItem = new MenuItem(SafeHtmlUtils.fromTrustedString(sn),
				new MyCommand("edit", "paste")));
		// pasteItem.setShortcut(new MenuShortcut(KeyEvent.VK_V));
		pasteItem.setEnabled(false);
		m.addSeparator();
		sn = edithtml + "Select All</div>Ctrl-A";
		m.addItem(selectAllItem = new MenuItem(SafeHtmlUtils
				.fromTrustedString(sn), new MyCommand("edit", "selectAll")));
		// selectAllItem.setShortcut(new MenuShortcut(KeyEvent.VK_A));
		m.addItem(new MenuItem("Centre Circuit", new MyCommand("edit",
				"centrecircuit")));
		menuBar.addItem("Edit", m);

		MenuBar drawMenuBar = new MenuBar(true);
		drawMenuBar.setAutoOpen(true);

		menuBar.addItem("Draw", drawMenuBar);

		m = new MenuBar(true);
		m.addItem(new MenuItem("Stack All", new MyCommand("scopes", "stackAll")));
		m.addItem(new MenuItem("Unstack All", new MyCommand("scopes",
				"unstackAll")));
		menuBar.addItem("Scopes", m);

		optionsMenuBar = m = new MenuBar(true);
		menuBar.addItem("Options", optionsMenuBar);
		m.addItem(dotsCheckItem = new CheckboxMenuItem("Show Current"));
		dotsCheckItem.setState(true);
		m.addItem(voltsCheckItem = new CheckboxMenuItem("Show Voltage",
				new Command() {
					public void execute() {
						if (voltsCheckItem.getState())
							powerCheckItem.setState(false);
						setPowerBarEnable();
					}
				}));
		voltsCheckItem.setState(true);
		m.addItem(powerCheckItem = new CheckboxMenuItem("Show Power",
				new Command() {
					public void execute() {
						if (powerCheckItem.getState())
							voltsCheckItem.setState(false);
						setPowerBarEnable();
					}
				}));
		m.addItem(showValuesCheckItem = new CheckboxMenuItem("Show Values"));
		showValuesCheckItem.setState(true);
		// m.add(conductanceCheckItem = getCheckItem("Show Conductance"));
		m.addItem(smallGridCheckItem = new CheckboxMenuItem("Small Grid",
				new Command() {
					public void execute() {
						setGrid();
					}
				}));
		m.addItem(euroResistorCheckItem = new CheckboxMenuItem(
				"European Resistors"));
		euroResistorCheckItem.setState(euro);
		m.addItem(printableCheckItem = new CheckboxMenuItem("White Background",
				new Command() {
					public void execute() {
						int i;
						for (i = 0; i < scopeCount; i++)
							scopes[i].setRect(scopes[i].rect);
					}
				}));
		printableCheckItem.setState(printable);
		m.addItem(conventionCheckItem = new CheckboxMenuItem(
				"Conventional Current Motion"));
		conventionCheckItem.setState(convention);
		m.addItem(optionsItem = new CheckboxAlignedMenuItem("Other Options...",
				new MyCommand("options", "other")));
		/*
		 * 
		 * Menu circuitsMenu = new Menu("Circuits"); if (useFrame)
		 * mb.add(circuitsMenu); else mainMenu.add(circuitsMenu);
		 */
		mainMenuBar = new MenuBar(true);
		mainMenuBar.setAutoOpen(true);

		composeMainMenu(mainMenuBar);
		composeMainMenu(drawMenuBar);

		if (ALLOW_MAIN_MENUBAR) {
			layoutPanel.addNorth(menuBar, MENUBARHEIGHT);
			layoutPanel.addEast(verticalPanel, VERTICALPANELWIDTH);
		}

		RootLayoutPanel.get().add(layoutPanel);

		cv = Canvas.createIfSupported();
		if (cv == null) {
			RootPanel
					.get()
					.add(new Label(
							"Not working. You need a browser that supports the CANVAS element."));
			return;
		}

		cvcontext = cv.getContext2d();
		backcv = Canvas.createIfSupported();
		backcontext = backcv.getContext2d();
		setCanvasSize();
		layoutPanel.add(cv);

		verticalPanel.add(resetButton = new Button("Reset"));
		resetButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				sim.resetAction();
			}
		});
		// dumpMatrixButton = new Button("Dump Matrix");
		// main.add(dumpMatrixButton);// IES for debugging
		stoppedCheck = new Checkbox("Stopped");
		verticalPanel.add(stoppedCheck);

		if (LoadFile.isSupported())
			verticalPanel.add(sim.loadFileInput = new LoadFile(sim));

		Label l;
		verticalPanel.add(l = new Label("Simulation Speed"));
		l.addStyleName("topSpace");

		// was max of 140
		verticalPanel.add(speedBar = new Scrollbar(Scrollbar.HORIZONTAL, 3, 1,
				0, 260));

		verticalPanel.add(l = new Label("Current Speed"));
		l.addStyleName("topSpace");
		currentBar = new Scrollbar(Scrollbar.HORIZONTAL, 50, 1, 1, 100);
		verticalPanel.add(currentBar);
		verticalPanel.add(powerLabel = new Label("Power Brightness"));
		powerLabel.addStyleName("topSpace");
		verticalPanel.add(powerBar = new Scrollbar(Scrollbar.HORIZONTAL, 50, 1,
				1, 100));
		setPowerBarEnable();
		verticalPanel.add(iFrame = new Frame("iframe.html"));
		iFrame.setWidth(VERTICALPANELWIDTH + "px");
		iFrame.setHeight("100 px");
		iFrame.getElement().setAttribute("scrolling", "no");

		// main.add(new Label("www.falstad.com"));
		/*
		 * if (useFrame) main.add(new Label("")); Font f = new Font("SansSerif",
		 * 0, 10); Label l; l = new Label("Current Circuit:"); l.setFont(f);
		 * titleLabel = new Label("Label"); titleLabel.setFont(f); if (useFrame)
		 * { main.add(l); main.add(titleLabel); }
		 */

		setGrid();

		// setupList = new Vector();
		undoStack = new Vector<String>();
		redoStack = new Vector<String>();

		scopes = new Scope[20];
		scopeColCount = new int[20];
		scopeCount = 0;

		// cv.setBackground(Color.black);
		// cv.setForeground(Color.lightGray);

		elmMenuBar = new MenuBar(true);
		elmMenuBar.addItem(elmEditMenuItem = new MenuItem("Edit",
				new MyCommand("elm", "edit")));
		elmMenuBar.addItem(elmScopeMenuItem = new MenuItem("View in Scope",
				new MyCommand("elm", "viewInScope")));
		elmMenuBar.addItem(elmCutMenuItem = new MenuItem("Cut", new MyCommand(
				"elm", "cut")));
		elmMenuBar.addItem(elmCopyMenuItem = new MenuItem("Copy",
				new MyCommand("elm", "copy")));
		elmMenuBar.addItem(elmDeleteMenuItem = new MenuItem("Delete",
				new MyCommand("elm", "delete")));
		// main.add(elmMenu);

		scopeMenuBar = buildScopeMenu(false);
		transScopeMenuBar = buildScopeMenu(true);

		// IES - remove interaction
		// getSetupList(circuitsMenu, false);
		// if (useFrame)
		// setMenuBar(mb);

		if (startCircuitText != null) {
			sim.getSetupList(false);
			sim.readSetup(startCircuitText, false);
		} else {
			sim.readSetup(null, 0, false, false);
			if (stopMessage == null && startCircuit != null) {
				sim.getSetupList(false);
				sim.readSetupFile(startCircuit, startLabel, true);
			} else
				sim.getSetupList(true);
		}

		// IES - hardcode circuit
		// if (startCircuitText != null)
		// readSetup(startCircuitText);
		// else if (stopMessage == null && startCircuit != null)
		// readSetupFile(startCircuit, startLabel);
		// else
		// readSetup(null, 0, false);

		// if (useFrame) {
		// Dimension screen = getToolkit().getScreenSize();
		// resize(860, 640);
		// handleResize();
		// Dimension x = getSize();
		// setLocation((screen.width - x.width)/2,
		// (screen.height - x.height)/2);
		// show();
		// } else {
		// if (!powerCheckItem.getState()) {
		// main.remove(powerBar);
		// main.remove(powerLabel);
		// main.validate();
		// }
		// hide();
		// handleResize();
		// applet.validate();
		// }
		// requestFocus();

		// addWindowListener(new WindowAdapter()
		// {
		// public void windowClosing(WindowEvent we)
		// {
		// destroyFrame();
		// }
		// }
		// );
		enableUndoRedo();
		setiFrameHeight();
		cv.addMouseDownHandler(this);
		cv.addMouseMoveHandler(this);
		cv.addMouseUpHandler(this);
		cv.addClickHandler(this);
		cv.addDoubleClickHandler(this);
		cv.addDomHandler(this, ContextMenuEvent.getType());
		menuBar.addDomHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				doMainMenuChecks();
			}
		}, ClickEvent.getType());
		Event.addNativePreviewHandler(this);
		cv.addMouseWheelHandler(this);
		// setup timer

	}

	public void centreCircuit() {
		// void handleResize() {
		// winSize = cv.getSize();
		// if (winSize.width == 0)
		// return;
		// dbimage = main.createImage(winSize.width, winSize.height);
		// int h = winSize.height / 5;
		/*
		 * if (h < 128 && winSize.height > 300) h = 128;
		 */
		// circuitArea = new Rectangle(0, 0, winSize.width, winSize.height-h);
		int i;
		int minx = 1000, maxx = 0, miny = 1000, maxy = 0;
		for (i = 0; i != sim.elmList.size(); i++) {
			CircuitElm ce = sim.getElm(i);
			// centered text causes problems when trying to center the circuit,
			// so we special-case it here
			if (!ce.isCenteredText()) {
				minx = min(ce.x, min(ce.x2, minx));
				maxx = max(ce.x, max(ce.x2, maxx));
			}
			miny = min(ce.y, min(ce.y2, miny));
			maxy = max(ce.y, max(ce.y2, maxy));
		}
		// center circuit; we don't use snapGrid() because that rounds
		int dx = gridMask
				& ((circuitArea.getWidth() - (maxx - minx)) / 2 - minx);
		int dy = gridMask
				& ((circuitArea.getHeight() - (maxy - miny)) / 2 - miny);
		if (dx + minx < 0)
			dx = gridMask & (-minx);
		if (dy + miny < 0)
			dy = gridMask & (-miny);
		for (i = 0; i != sim.elmList.size(); i++) {
			CircuitElm ce = sim.getElm(i);
			ce.move(dx, dy);
		}
		// after moving elements, need this to avoid singular matrix probs
		sim.needAnalyze();
		sim.circuitBottom = 0;
	}

	// public void triggerShow() {
	// if (!shown)
	// show();
	// shown = true;
	// }

	// public void requestFocus()
	// {
	// super.requestFocus();
	// cv.requestFocus();
	// }
	// IES - remove interaction
	/*
	 * MenuItem getMenuItem(String s) { MenuItem mi = new MenuItem(s);
	 * mi.addActionListener(this); return mi; }
	 * 
	 * MenuItem getMenuItem(String s, String ac) { MenuItem mi = new
	 * MenuItem(s); mi.setActionCommand(ac); mi.addActionListener(this); return
	 * mi; }
	 * 
	 * CheckboxMenuItem getCheckItem(String s) { CheckboxMenuItem mi = new
	 * CheckboxMenuItem(s); mi.addItemListener(this); mi.setActionCommand("");
	 * return mi; }
	 */

	CheckboxMenuItem getClassCheckItem(String s, String t) {
		// try {
		// Class c = Class.forName(t);
		String shortcut = "";
		CircuitElm elm = sim.constructElement(t, 0, 0);
		CheckboxMenuItem mi;
		// register(c, elm);
		if (elm != null) {
			if (elm.needsShortcut()) {
				shortcut += (char) elm.getShortcut();
				shortcuts[elm.getShortcut()] = t;
			}
			elm.delete();
		}
		// else
		// GWT.log("Coudn't create class: "+t);
		// } catch (Exception ee) {
		// ee.printStackTrace();
		// }
		if (shortcut == "")
			mi = new CheckboxMenuItem(s);
		else
			mi = new CheckboxMenuItem(s, shortcut);
		mi.setScheduledCommand(new MyCommand("main", t));
		mainMenuItems.add(mi);
		mainMenuItemNames.add(t);
		return mi;
	}

	// CheckboxMenuItem getCheckItem(String s, String t) {
	// CheckboxMenuItem mi = new CheckboxMenuItem(s);
	// mi.addItemListener(this);
	// mi.setActionCommand(t);
	// return mi;
	// }

	// void register(Class c, CircuitElm elm) {
	// int t = elm.getDumpType();
	// if (t == 0) {
	// System.out.println("no dump type: " + c);
	// return;
	// }
	//
	// int s = elm.getShortcut();
	// if (elm.needsShortcut() && s == 0) {
	// if (s == 0) {
	// System.err.println("no shortcut " + c + " for " + c);
	// return;
	// } else if (s <= ' ' || s >= 127) {
	// System.err.println("invalid shortcut " + c + " for " + c);
	// return;
	// }
	// }
	//
	// Class dclass = elm.getDumpClass();
	//
	// if (dumpTypes[t] != null && dumpTypes[t] != dclass) {
	// System.out.println("dump type conflict: " + c + " " + dumpTypes[t]);
	// return;
	// }
	// dumpTypes[t] = dclass;
	//
	// Class sclass = elm.getClass();
	//
	// if (elm.needsShortcut() && shortcuts[s] != null
	// && shortcuts[s] != sclass) {
	// System.err.println("shortcut conflict: " + c
	// + " (previously assigned to " + shortcuts[s] + ")");
	// } else {
	// shortcuts[s] = sclass;
	// }
	// }

	public void setCanvasSize() {
		int width, height;
		width = (int) RootLayoutPanel.get().getOffsetWidth();
		height = (int) RootLayoutPanel.get().getOffsetHeight();
		if (ALLOW_MAIN_MENUBAR) {
			height = height - MENUBARHEIGHT;
			width = width - VERTICALPANELWIDTH;
		}
		if (cv != null) {
			cv.setWidth(width + "PX");
			cv.setHeight(height + "PX");
			cv.setCoordinateSpaceWidth(width);
			cv.setCoordinateSpaceHeight(height);
		}
		if (backcv != null) {
			backcv.setWidth(width + "PX");
			backcv.setHeight(height + "PX");
			backcv.setCoordinateSpaceWidth(width);
			backcv.setCoordinateSpaceHeight(height);
		}
		int h = height / 5;
		/*
		 * if (h < 128 && winSize.height > 300) h = 128;
		 */
		circuitArea = new Rectangle(0, 0, width, height - h);

	}

	public void composeMainMenu(MenuBar mainMenuBar) {
		mainMenuBar.addItem(getClassCheckItem("Add Wire", "WireElm"));
		mainMenuBar.addItem(getClassCheckItem("Add Resistor", "ResistorElm"));

		MenuBar passMenuBar = new MenuBar(true);
		passMenuBar.addItem(getClassCheckItem("Add Capacitor", "CapacitorElm"));
		passMenuBar
				.addItem(getClassCheckItem("Add Capacitor2", "Capacitor2Elm"));
		passMenuBar.addItem(getClassCheckItem("Add Inductor", "InductorElm"));
		passMenuBar.addItem(getClassCheckItem("Add Switch", "SwitchElm"));
		passMenuBar.addItem(getClassCheckItem("Add Push Switch",
				"PushSwitchElm"));
		passMenuBar.addItem(getClassCheckItem("Add SPDT Switch", "Switch2Elm"));
		passMenuBar.addItem(getClassCheckItem("Add Potentiometer", "PotElm"));
		passMenuBar.addItem(getClassCheckItem("Add Transformer",
				"TransformerElm"));
		passMenuBar.addItem(getClassCheckItem("Add Tapped Transformer",
				"TappedTransformerElm"));
		passMenuBar.addItem(getClassCheckItem("Add Transmission Line",
				"TransLineElm"));
		passMenuBar.addItem(getClassCheckItem("Add Relay", "RelayElm"));
		passMenuBar.addItem(getClassCheckItem("Add Memristor", "MemristorElm"));
		passMenuBar.addItem(getClassCheckItem("Add Spark Gap", "SparkGapElm"));
		mainMenuBar.addItem(
				SafeHtmlUtils.fromTrustedString(CheckboxMenuItem.checkBoxHtml
						+ "&nbsp;</div>Passive Components"), passMenuBar);

		MenuBar inputMenuBar = new MenuBar(true);
		inputMenuBar.addItem(getClassCheckItem("Add Ground", "GroundElm"));
		inputMenuBar.addItem(getClassCheckItem(
				"Add Voltage Source (2-terminal)", "DCVoltageElm"));
		inputMenuBar.addItem(getClassCheckItem(
				"Add A/C Voltage Source (2-terminal)", "ACVoltageElm"));
		inputMenuBar.addItem(getClassCheckItem(
				"Add Voltage Source (1-terminal)", "RailElm"));
		inputMenuBar.addItem(getClassCheckItem(
				"Add A/C Voltage Source (1-terminal)", "ACRailElm"));
		inputMenuBar.addItem(getClassCheckItem(
				"Add Square Wave Source (1-terminal)", "SquareRailElm"));
		inputMenuBar.addItem(getClassCheckItem("Add Clock", "ClockElm"));
		inputMenuBar.addItem(getClassCheckItem("Add A/C Sweep", "SweepElm"));
		inputMenuBar.addItem(getClassCheckItem("Add Variable Voltage",
				"VarRailElm"));
		inputMenuBar.addItem(getClassCheckItem("Add Antenna", "AntennaElm"));
		inputMenuBar.addItem(getClassCheckItem("Add AM Source", "AMElm"));
		inputMenuBar.addItem(getClassCheckItem("Add FM Source", "FMElm"));
		inputMenuBar.addItem(getClassCheckItem("Add Current Source",
				"CurrentElm"));

		mainMenuBar.addItem(
				SafeHtmlUtils.fromTrustedString(CheckboxMenuItem.checkBoxHtml
						+ "&nbsp;</div>Inputs and Sources"), inputMenuBar);

		MenuBar outputMenuBar = new MenuBar(true);
		outputMenuBar.addItem(getClassCheckItem("Add Analog Output",
				"OutputElm"));
		outputMenuBar.addItem(getClassCheckItem("Add LED", "LEDElm"));
		outputMenuBar.addItem(getClassCheckItem("Add Lamp (beta)", "LampElm"));
		outputMenuBar.addItem(getClassCheckItem("Add Text", "TextElm"));
		outputMenuBar.addItem(getClassCheckItem("Add Box", "BoxElm"));
		outputMenuBar.addItem(getClassCheckItem("Add Scope Probe", "ProbeElm"));
		outputMenuBar.addItem(getClassCheckItem("Add Loudspeaker",
				"LoudSpeakerElm"));
		mainMenuBar.addItem(
				SafeHtmlUtils.fromTrustedString(CheckboxMenuItem.checkBoxHtml
						+ "&nbsp;</div>Outputs and Labels"), outputMenuBar);

		MenuBar activeMenuBar = new MenuBar(true);
		activeMenuBar.addItem(getClassCheckItem("Add Diode", "DiodeElm"));
		activeMenuBar.addItem(getClassCheckItem("Add Zener Diode", "ZenerElm"));
		activeMenuBar.addItem(getClassCheckItem(
				"Add Transistor (bipolar, NPN)", "NTransistorElm"));
		activeMenuBar.addItem(getClassCheckItem(
				"Add Transistor (bipolar, PNP)", "PTransistorElm"));
		activeMenuBar.addItem(getClassCheckItem("Add MOSFET (N-Channel)",
				"NMosfetElm"));
		activeMenuBar.addItem(getClassCheckItem("Add MOSFET (P-Channel)",
				"PMosfetElm"));
		activeMenuBar.addItem(getClassCheckItem("Add JFET (N-Channel)",
				"NJfetElm"));
		activeMenuBar.addItem(getClassCheckItem("Add JFET (P-Channel)",
				"PJfetElm"));
		activeMenuBar.addItem(getClassCheckItem("Add SCR", "SCRElm"));
		// activeMenuBar.addItem(getClassCheckItem("Add Varactor/Varicap",
		// "VaractorElm"));
		activeMenuBar.addItem(getClassCheckItem("Add Tunnel Diode",
				"TunnelDiodeElm"));
		activeMenuBar.addItem(getClassCheckItem("Add Triode", "TriodeElm"));
	    activeMenuBar.addItem(getClassCheckItem("Add Diac", "DiacElm"));
		activeMenuBar.addItem(getClassCheckItem("Add Triac", "TriacElm"));
		 activeMenuBar.addItem(getClassCheckItem("Add Photoresistor",
		 "PhotoResistorElm"));
		 activeMenuBar.addItem(getClassCheckItem("Add Thermistor",
		 "ThermistorElm"));
		mainMenuBar.addItem(
				SafeHtmlUtils.fromTrustedString(CheckboxMenuItem.checkBoxHtml
						+ "&nbsp;</div>Active Components"), activeMenuBar);

		MenuBar activeBlocMenuBar = new MenuBar(true);
		activeBlocMenuBar.addItem(getClassCheckItem("Add Op Amp (- on top)",
				"OpAmpElm"));
		activeBlocMenuBar.addItem(getClassCheckItem("Add Op Amp (+ on top)",
				"OpAmpSwapElm"));
		activeBlocMenuBar.addItem(getClassCheckItem("Add Analog Switch (SPST)",
				"AnalogSwitchElm"));
		activeBlocMenuBar.addItem(getClassCheckItem("Add Analog Switch (SPDT)",
				"AnalogSwitch2Elm"));
		activeBlocMenuBar.addItem(getClassCheckItem("Add Tristate Buffer",
				"TriStateElm"));
		activeBlocMenuBar.addItem(getClassCheckItem("Add Schmitt Trigger",
				"SchmittElm"));
		activeBlocMenuBar.addItem(getClassCheckItem(
				"Add Schmitt Trigger (Inverting)", "InvertingSchmittElm"));
		activeBlocMenuBar.addItem(getClassCheckItem("Add CCII+", "CC2Elm"));
		activeBlocMenuBar.addItem(getClassCheckItem("Add CCII-", "CC2NegElm"));
		mainMenuBar.addItem(
				SafeHtmlUtils.fromTrustedString(CheckboxMenuItem.checkBoxHtml
						+ "&nbsp;</div>Active Building Blocks"),
				activeBlocMenuBar);

		MenuBar gateMenuBar = new MenuBar(true);
		gateMenuBar.addItem(getClassCheckItem("Add Logic Input",
				"LogicInputElm"));
		gateMenuBar.addItem(getClassCheckItem("Add Logic Output",
				"LogicOutputElm"));
		gateMenuBar.addItem(getClassCheckItem("Add Inverter", "InverterElm"));
		gateMenuBar.addItem(getClassCheckItem("Add NAND Gate", "NandGateElm"));
		gateMenuBar.addItem(getClassCheckItem("Add NOR Gate", "NorGateElm"));
		gateMenuBar.addItem(getClassCheckItem("Add AND Gate", "AndGateElm"));
		gateMenuBar.addItem(getClassCheckItem("Add OR Gate", "OrGateElm"));
		gateMenuBar.addItem(getClassCheckItem("Add XOR Gate", "XorGateElm"));
		mainMenuBar.addItem(
				SafeHtmlUtils.fromTrustedString(CheckboxMenuItem.checkBoxHtml
						+ "&nbsp;</div>Logic Gates, Input and Output"),
				gateMenuBar);

		MenuBar chipMenuBar = new MenuBar(true);
		chipMenuBar
				.addItem(getClassCheckItem("Add D Flip-Flop", "DFlipFlopElm"));
		chipMenuBar.addItem(getClassCheckItem("Add JK Flip-Flop",
				"JKFlipFlopElm"));
		chipMenuBar
				.addItem(getClassCheckItem("Add T Flip-Flop", "TFlipFlopElm"));
		chipMenuBar.addItem(getClassCheckItem("Add 7 Segment LED",
				"SevenSegElm"));
		chipMenuBar.addItem(getClassCheckItem("Add 7 Segment Decoder",
				"SevenSegDecoderElm"));
		chipMenuBar.addItem(getClassCheckItem("Add Multiplexer",
				"MultiplexerElm"));
		chipMenuBar.addItem(getClassCheckItem("Add Demultiplexer",
				"DeMultiplexerElm"));
		chipMenuBar.addItem(getClassCheckItem("Add SIPO shift register",
				"SipoShiftElm"));
		chipMenuBar.addItem(getClassCheckItem("Add PISO shift register",
				"PisoShiftElm"));
		chipMenuBar.addItem(getClassCheckItem("Add Counter", "CounterElm"));
		chipMenuBar
				.addItem(getClassCheckItem("Add Decade Counter", "DecadeElm"));
		chipMenuBar.addItem(getClassCheckItem("Add Latch", "LatchElm"));
		// chipMenuBar.addItem(getClassCheckItem("Add Static RAM", "SRAMElm"));
		chipMenuBar.addItem(getClassCheckItem("Add Sequence generator",
				"SeqGenElm"));
		chipMenuBar
				.addItem(getClassCheckItem("Add Full Adder", "FullAdderElm"));
		chipMenuBar
				.addItem(getClassCheckItem("Add Half Adder", "HalfAdderElm"));
		mainMenuBar.addItem(
				SafeHtmlUtils.fromTrustedString(CheckboxMenuItem.checkBoxHtml
						+ "&nbsp;</div>Digital Chips"), chipMenuBar);

		MenuBar achipMenuBar = new MenuBar(true);
		achipMenuBar.addItem(getClassCheckItem("Add 555 Timer", "TimerElm"));
		achipMenuBar.addItem(getClassCheckItem("Add Phase Comparator",
				"PhaseCompElm"));
		achipMenuBar.addItem(getClassCheckItem("Add DAC", "DACElm"));
		achipMenuBar.addItem(getClassCheckItem("Add ADC", "ADCElm"));
		achipMenuBar.addItem(getClassCheckItem("Add VCO", "VCOElm"));
		achipMenuBar.addItem(getClassCheckItem("Add Monostable",
				"MonostableElm"));
		mainMenuBar.addItem(
				SafeHtmlUtils.fromTrustedString(CheckboxMenuItem.checkBoxHtml
						+ "&nbsp;</div>Analog and Hybrid Chips"), achipMenuBar);

		MenuBar otherMenuBar = new MenuBar(true);
		CheckboxMenuItem mi;
		otherMenuBar.addItem(mi = getClassCheckItem("Drag All", "DragAll"));
		mi.addShortcut("(Alt-drag)");
		otherMenuBar.addItem(mi = getClassCheckItem("Drag Row", "DragRow"));
		mi.addShortcut("(S-right)");
		otherMenuBar.addItem(getClassCheckItem("Drag Column", "DragColumn"));
		otherMenuBar
				.addItem(getClassCheckItem("Drag Selected", "DragSelected"));
		otherMenuBar.addItem(mi = getClassCheckItem("Drag Post", "DragPost"));
		mi.addShortcut("(" + ctrlMetaKey + "-drag)");

		mainMenuBar.addItem(
				SafeHtmlUtils.fromTrustedString(CheckboxMenuItem.checkBoxHtml
						+ "&nbsp;</div>Drag"), otherMenuBar);

		mainMenuBar
				.addItem(mi = getClassCheckItem("Select/Drag Sel", "Select"));
		mi.addShortcut("(space or Shift-drag)");
	}

	MenuBar buildScopeMenu(boolean t) {
		MenuBar m = new MenuBar(true);
		m.addItem(new CheckboxAlignedMenuItem("Remove", new MyCommand(
				"scopepop", "remove")));
		m.addItem(new CheckboxAlignedMenuItem("Speed 2x", new MyCommand(
				"scopepop", "speed2")));
		m.addItem(new CheckboxAlignedMenuItem("Speed 1/2x", new MyCommand(
				"scopepop", "speed1/2")));
		m.addItem(new CheckboxAlignedMenuItem("Scale 2x", new MyCommand(
				"scopepop", "scale")));
		m.addItem(new CheckboxAlignedMenuItem("Max Scale", new MyCommand(
				"scopepop", "maxscale")));
		m.addItem(new CheckboxAlignedMenuItem("Stack", new MyCommand(
				"scopepop", "stack")));
		m.addItem(new CheckboxAlignedMenuItem("Unstack", new MyCommand(
				"scopepop", "unstack")));
		m.addItem(new CheckboxAlignedMenuItem("Reset", new MyCommand(
				"scopepop", "reset")));
		if (t) {
			m.addItem(scopeIbMenuItem = new CheckboxMenuItem("Show Ib",
					new MyCommand("scopepop", "showib")));
			m.addItem(scopeIcMenuItem = new CheckboxMenuItem("Show Ic",
					new MyCommand("scopepop", "showic")));
			m.addItem(scopeIeMenuItem = new CheckboxMenuItem("Show Ie",
					new MyCommand("scopepop", "showie")));
			m.addItem(scopeVbeMenuItem = new CheckboxMenuItem("Show Vbe",
					new MyCommand("scopepop", "showvbe")));
			m.addItem(scopeVbcMenuItem = new CheckboxMenuItem("Show Vbc",
					new MyCommand("scopepop", "showvbc")));
			m.addItem(scopeVceMenuItem = new CheckboxMenuItem("Show Vce",
					new MyCommand("scopepop", "showvce")));
			m.addItem(scopeVceIcMenuItem = new CheckboxMenuItem(
					"Show Vce vs Ic", new MyCommand("scopepop", "showvcevsic")));
		} else {
			m.addItem(scopeVMenuItem = new CheckboxMenuItem("Show Voltage",
					new MyCommand("scopepop", "showvoltage")));
			m.addItem(scopeIMenuItem = new CheckboxMenuItem("Show Current",
					new MyCommand("scopepop", "showcurrent")));
			m.addItem(scopePowerMenuItem = new CheckboxMenuItem(
					"Show Power Consumed", new MyCommand("scopepop",
							"showpower")));
			m.addItem(scopeScaleMenuItem = new CheckboxMenuItem("Show Scale",
					new MyCommand("scopepop", "showscale")));
			m.addItem(scopeMaxMenuItem = new CheckboxMenuItem(
					"Show Peak Value", new MyCommand("scopepop", "showpeak")));
			m.addItem(scopeMinMenuItem = new CheckboxMenuItem(
					"Show Negative Peak Value", new MyCommand("scopepop",
							"shownegpeak")));
			m.addItem(scopeFreqMenuItem = new CheckboxMenuItem(
					"Show Frequency", new MyCommand("scopepop", "showfreq")));
			
			m.addItem(scopeFFTMenuItem = new CheckboxMenuItem(
					"Show FFT", new MyCommand("scopepop", "showfft")));
			
			m.addItem(scopeVIMenuItem = new CheckboxMenuItem("Show V vs I",
					new MyCommand("scopepop", "showvvsi")));
			m.addItem(scopeXYMenuItem = new CheckboxMenuItem("Plot X/Y",
					new MyCommand("scopepop", "plotxy")));
			m.addItem(scopeSelectYMenuItem = new CheckboxAlignedMenuItem(
					"Select Y", new MyCommand("scopepop", "selecty")));
			m.addItem(scopeResistMenuItem = new CheckboxMenuItem(
					"Show Resistance", new MyCommand("scopepop",
							"showresistance")));
		}
		return m;
	}

	public void setiFrameHeight() {
		if (iFrame == null)
			return;
		int i;
		int cumheight = 0;
		for (i = 0; i < verticalPanel.getWidgetIndex(iFrame); i++) {
			if (verticalPanel.getWidget(i) != sim.loadFileInput) {
				cumheight = cumheight
						+ verticalPanel.getWidget(i).getOffsetHeight();
				if (verticalPanel.getWidget(i).getStyleName()
						.contains("topSpace"))
					cumheight += 12;
			}
		}
		// int
		// ih=RootLayoutPanel.get().getOffsetHeight()-(iFrame.getAbsoluteTop()-RootLayoutPanel.get().getAbsoluteTop());
		int ih = RootLayoutPanel.get().getOffsetHeight() - MENUBARHEIGHT
				- cumheight;
		// GWT.log("Root OH="+RootLayoutPanel.get().getOffsetHeight());
		// GWT.log("iF top="+iFrame.getAbsoluteTop() );
		// GWT.log("RP top="+RootLayoutPanel.get().getAbsoluteTop());
		// GWT.log("ih="+ih);
		// GWT.log("if left="+iFrame.getAbsoluteLeft());
		if (ih < 0)
			ih = 0;
		iFrame.setHeight(ih + "px");
	}

	void stackScope(int s) {
		if (s == 0) {
			if (scopeCount < 2)
				return;
			s = 1;
		}
		if (scopes[s].position == scopes[s - 1].position)
			return;
		scopes[s].position = scopes[s - 1].position;
		for (s++; s < scopeCount; s++)
			scopes[s].position--;
	}

	void unstackScope(int s) {
		if (s == 0) {
			if (scopeCount < 2)
				return;
			s = 1;
		}
		if (scopes[s].position != scopes[s - 1].position)
			return;
		for (; s < scopeCount; s++)
			scopes[s].position++;
	}

	void stackAll() {
		int i;
		for (i = 0; i != scopeCount; i++) {
			scopes[i].position = 0;
			scopes[i].showMax = scopes[i].showMin = false;
		}
	}

	void unstackAll() {
		int i;
		for (i = 0; i != scopeCount; i++) {
			scopes[i].position = i;
			scopes[i].showMax = true;
		}
	}

	void doEdit(Editable eable) {
		clearSelection();
		pushUndo();
		if (editDialog != null) {
			// requestFocus();
			editDialog.setVisible(false);
			editDialog = null;
		}
		editDialog = new EditDialog(eable, sim);
		editDialog.show();
	}

	// IES - remove import export
	/*
	 * void doImport() { if (impDialog == null) impDialog =
	 * ImportExportDialogFactory.Create(this, ImportExportDialog.Action.IMPORT);
	 * // impDialog = new ImportExportClipboardDialog(this, //
	 * ImportExportDialog.Action.IMPORT); pushUndo(); impDialog.execute(); }
	 */

	void doExportAsUrl() {
		String start[] = Location.getHref().split("\\?");
		String dump = sim.dumpCircuit();
		dump = dump.replace(' ', '+');
		dump = start[0] + "?cct=" + URL.encode(dump);
		// if (expDialog == null) {
		// expDialog = ImportExportDialogFactory.Create(this,
		// ImportExportDialog.Action.EXPORT);
		// // expDialog = new ImportExportClipboardDialog(this,
		// // ImportExportDialog.Action.EXPORT);
		// }
		// expDialog.setDump(dump);
		// expDialog.execute();
		exportAsUrlDialog = new ExportAsUrlDialog(dump);
		exportAsUrlDialog.show();
	}

	void doExportAsText() {
		String dump = sim.dumpCircuit();
		exportAsTextDialog = new ExportAsTextDialog(dump);
		exportAsTextDialog.show();
	}

	void doExportAsLocalFile() {
		String dump = sim.dumpCircuit();
		exportAsLocalFileDialog = new ExportAsLocalFileDialog(dump);
		exportAsLocalFileDialog.show();
	}

	public int snapGrid(int x) {
		return (x + gridRound) & gridMask;
	}

	public void mouseDragged(MouseMoveEvent e) {
		// ignore right mouse button with no modifiers (needed on PC)
		if (!ALLOW_MOUSE_EDIT)
			return;

		if (e.getNativeButton() == NativeEvent.BUTTON_RIGHT) {
			if (!(e.isMetaKeyDown() || e.isShiftKeyDown()
					|| e.isControlKeyDown() || e.isAltKeyDown()))
				return;
		}

		if (!circuitArea.contains(e.getX(), e.getY()))
			return;
		if (dragElm != null)
			dragElm.drag(e.getX(), e.getY());
		boolean success = true;
		switch (tempMouseMode) {
		case MODE_DRAG_ALL:
			dragAll(snapGrid(e.getX()), snapGrid(e.getY()));
			break;
		case MODE_DRAG_ROW:
			dragRow(snapGrid(e.getX()), snapGrid(e.getY()));
			break;
		case MODE_DRAG_COLUMN:
			dragColumn(snapGrid(e.getX()), snapGrid(e.getY()));
			break;
		case MODE_DRAG_POST:
			if (mouseElm != null)
				dragPost(snapGrid(e.getX()), snapGrid(e.getY()));
			break;
		case MODE_SELECT:
			if (mouseElm == null)
				selectArea(e.getX(), e.getY());
			else {
				tempMouseMode = MODE_DRAG_SELECTED;
				success = dragSelected(e.getX(), e.getY());
			}
			break;
		case MODE_DRAG_SELECTED:
			success = dragSelected(e.getX(), e.getY());
			break;
		}
		dragging = true;
		if (success) {
			if (tempMouseMode == MODE_DRAG_SELECTED
					&& mouseElm instanceof GraphicElm) {
				dragX = e.getX();
				dragY = e.getY();
			} else {
				dragX = snapGrid(e.getX());
				dragY = snapGrid(e.getY());
			}
		}
		// cv.repaint(pause);
	}

	void dragAll(int x, int y) {
		int dx = x - dragX;
		int dy = y - dragY;
		if (dx == 0 && dy == 0)
			return;
		int i;
		for (i = 0; i != sim.elmList.size(); i++) {
			CircuitElm ce = sim.getElm(i);
			ce.move(dx, dy);
		}
		removeZeroLengthElements();
	}

	void dragRow(int x, int y) {
		int dy = y - dragY;
		if (dy == 0)
			return;
		int i;
		for (i = 0; i != sim.elmList.size(); i++) {
			CircuitElm ce = sim.getElm(i);
			if (ce.y == dragY)
				ce.movePoint(0, 0, dy);
			if (ce.y2 == dragY)
				ce.movePoint(1, 0, dy);
		}
		removeZeroLengthElements();
	}

	void dragColumn(int x, int y) {
		int dx = x - dragX;
		if (dx == 0)
			return;
		int i;
		for (i = 0; i != sim.elmList.size(); i++) {
			CircuitElm ce = sim.getElm(i);
			if (ce.x == dragX)
				ce.movePoint(0, dx, 0);
			if (ce.x2 == dragX)
				ce.movePoint(1, dx, 0);
		}
		removeZeroLengthElements();
	}

	boolean dragSelected(int x, int y) {
		boolean me = false;
		if (mouseElm != null && !mouseElm.isSelected())
			mouseElm.setSelected(me = true);

		// snap grid, unless we're only dragging text elements
		int i;
		for (i = 0; i != sim.elmList.size(); i++) {
			CircuitElm ce = sim.getElm(i);
			if (ce.isSelected() && !(ce instanceof GraphicElm))
				break;
		}
		if (i != sim.elmList.size()) {
			x = snapGrid(x);
			y = snapGrid(y);
		}

		int dx = x - dragX;
		int dy = y - dragY;
		if (dx == 0 && dy == 0) {
			// don't leave mouseElm selected if we selected it above
			if (me)
				mouseElm.setSelected(false);
			return false;
		}
		boolean allowed = true;

		// check if moves are allowed
		for (i = 0; allowed && i != sim.elmList.size(); i++) {
			CircuitElm ce = sim.getElm(i);
			if (ce.isSelected() && !ce.allowMove(dx, dy))
				allowed = false;
		}

		if (allowed) {
			for (i = 0; i != sim.elmList.size(); i++) {
				CircuitElm ce = sim.getElm(i);
				if (ce.isSelected())
					ce.move(dx, dy);
			}
			sim.needAnalyze();
		}

		// don't leave mouseElm selected if we selected it above
		if (me)
			mouseElm.setSelected(false);

		return allowed;
	}

	void dragPost(int x, int y) {
		if (draggingPost == -1) {
			draggingPost = (distanceSq(mouseElm.x, mouseElm.y, x, y) > distanceSq(
					mouseElm.x2, mouseElm.y2, x, y)) ? 1 : 0;
		}
		int dx = x - dragX;
		int dy = y - dragY;
		if (dx == 0 && dy == 0)
			return;
		mouseElm.movePoint(draggingPost, dx, dy);
		sim.needAnalyze();
	}

	void selectArea(int x, int y) {
		int x1 = min(x, initDragX);
		int x2 = max(x, initDragX);
		int y1 = min(y, initDragY);
		int y2 = max(y, initDragY);
		selectedArea = new Rectangle(x1, y1, x2 - x1, y2 - y1);
		int i;
		for (i = 0; i != sim.elmList.size(); i++) {
			CircuitElm ce = sim.getElm(i);
			ce.selectRect(selectedArea);
		}
	}

	// void setSelectedElm(CircuitElm cs) {
	// int i;
	// for (i = 0; i != elmList.size(); i++) {
	// CircuitElm ce = getElm(i);
	// ce.setSelected(ce == cs);
	// }
	// mouseElm = cs;
	// }

	public void setMouseElm(CircuitElm ce) {
		if (ce != mouseElm) {
			if (mouseElm != null)
				mouseElm.setMouseElm(false);
			if (ce != null)
				ce.setMouseElm(true);
			mouseElm = ce;
		}
	}

	void removeZeroLengthElements() {
		int i;
		boolean changed = false;
		for (i = sim.elmList.size() - 1; i >= 0; i--) {
			CircuitElm ce = sim.getElm(i);
			if (ce.x == ce.x2 && ce.y == ce.y2) {
				sim.elmList.removeElementAt(i);
				ce.delete();
				changed = true;
			}
		}
		sim.needAnalyze();
	}

	public void onMouseMove(MouseMoveEvent e) {
		e.preventDefault();
		if (mouseDragging) {
			mouseDragged(e);
			return;
		}
		// The following is in the original, but seems not to work/be needed for
		// GWT
		// if (e.getNativeButton()==NativeEvent.BUTTON_LEFT)
		// return;
		CircuitElm newMouseElm = null;
		int x = e.getX();
		int y = e.getY();
		dragX = snapGrid(x);
		dragY = snapGrid(y);
		draggingPost = -1;
		int i;
		// CircuitElm origMouse = mouseElm;

		mousePost = -1;
		plotXElm = plotYElm = null;
		if (mouseElm != null
				&& (distanceSq(x, y, mouseElm.x, mouseElm.y) <= POSTGRABSQ || distanceSq(
						x, y, mouseElm.x2, mouseElm.y2) <= POSTGRABSQ)) {
			newMouseElm = mouseElm;
		} else {
			int bestDist = 100000;
			int bestArea = 100000;
			for (i = 0; i != sim.elmList.size(); i++) {
				CircuitElm ce = sim.getElm(i);
				if (ce.boundingBox.contains(x, y)) {
					int j;
					int area = ce.boundingBox.getWidth()
							* ce.boundingBox.getHeight();
					int jn = ce.getPostCount();
					if (jn > 2)
						jn = 2;
					for (j = 0; j != jn; j++) {
						Point pt = ce.getPost(j);
						int dist = distanceSq(x, y, pt.x, pt.y);

						// if multiple elements have overlapping bounding boxes,
						// we prefer selecting elements that have posts close
						// to the mouse pointer and that have a small bounding
						// box area.
						if (dist <= bestDist && area <= bestArea) {
							bestDist = dist;
							bestArea = area;
							newMouseElm = ce;
						}
					}
					if (ce.getPostCount() == 0)
						newMouseElm = ce;
				}
			} // for
		}
		scopeSelected = -1;
		if (newMouseElm == null) {
			for (i = 0; i != scopeCount; i++) {
				Scope s = scopes[i];
				if (s.rect.contains(x, y)) {
					newMouseElm = s.elm;
					if (s.plotXY) {
						plotXElm = s.elm;
						plotYElm = s.yElm;
					}
					scopeSelected = i;
				}
			}
			// // the mouse pointer was not in any of the bounding boxes, but we
			// // might still be close to a post
			for (i = 0; i != sim.elmList.size(); i++) {
				CircuitElm ce = sim.getElm(i);
				if (mouseMode == MODE_DRAG_POST) {
					if (distanceSq(ce.x, ce.y, x, y) < 26) {
						newMouseElm = ce;
						break;
					}
					if (distanceSq(ce.x2, ce.y2, x, y) < 26) {
						newMouseElm = ce;
						break;
					}
				}
				int j;
				int jn = ce.getPostCount();
				for (j = 0; j != jn; j++) {
					Point pt = ce.getPost(j);
					// int dist = distanceSq(x, y, pt.x, pt.y);
					if (distanceSq(pt.x, pt.y, x, y) < 26) {
						newMouseElm = ce;
						mousePost = j;
						break;
					}
				}
			}
		} else {
			mousePost = -1;
			// look for post close to the mouse pointer
			for (i = 0; i != newMouseElm.getPostCount(); i++) {
				Point pt = newMouseElm.getPost(i);
				if (distanceSq(pt.x, pt.y, x, y) < 26)
					mousePost = i;
			}
		}
		// if (mouseElm != origMouse)
		// cv.repaint();
		setMouseElm(newMouseElm);
	}

	int distanceSq(int x1, int y1, int x2, int y2) {
		x2 -= x1;
		y2 -= y1;
		return x2 * x2 + y2 * y2;
	}

	public void onContextMenu(ContextMenuEvent e) {
		e.preventDefault();
		int x, y;
		menuElm = mouseElm;
		menuScope = -1;
		if (scopeSelected != -1 && ALLOW_SCOPE_MENU) {
			MenuBar m = scopes[scopeSelected].getMenu();
			menuScope = scopeSelected;
			if (m != null) {
				contextPanel = new PopupPanel(true);
				contextPanel.add(m);
				y = Math.max(
						0,
						Math.min(e.getNativeEvent().getClientY(),
								cv.getCoordinateSpaceHeight() - 400));
				contextPanel.setPopupPosition(e.getNativeEvent().getClientX(),
						y);
				contextPanel.show();
			}
		} else if (mouseElm != null && ALLOW_ELEMENT_MENU) {
			elmScopeMenuItem.setEnabled(mouseElm.canViewInScope());
			elmEditMenuItem.setEnabled(mouseElm.getEditInfo(0) != null);
			contextPanel = new PopupPanel(true);
			contextPanel.add(elmMenuBar);
			contextPanel.setPopupPosition(e.getNativeEvent().getClientX(), e
					.getNativeEvent().getClientY());
			contextPanel.show();
		} else if (ALLOW_CONTEXT_MENU) {
			doMainMenuChecks();
			contextPanel = new PopupPanel(true);
			contextPanel.add(mainMenuBar);
			x = Math.max(
					0,
					Math.min(e.getNativeEvent().getClientX(),
							cv.getCoordinateSpaceWidth() - 400));
			y = Math.max(
					0,
					Math.min(e.getNativeEvent().getClientY(),
							cv.getCoordinateSpaceHeight() - 450));
			contextPanel.setPopupPosition(x, y);
			contextPanel.show();
		}
	}

	// public void mouseClicked(MouseEvent e) {
	public void onClick(ClickEvent e) {
		e.preventDefault();
		// //IES - remove inteaction
		// // if ( e.getClickCount() == 2 && !didSwitch )
		// // doEditMenu(e);
		// if (e.getNativeButton() == NativeEvent.BUTTON_LEFT) {
		// if (mouseMode == MODE_SELECT || mouseMode == MODE_DRAG_SELECTED)
		// clearSelection();
		// }
		if ((e.getNativeButton() == NativeEvent.BUTTON_MIDDLE))
			scrollValues(e.getNativeEvent().getClientX(), e.getNativeEvent()
					.getClientY(), 0);
	}

	public void onDoubleClick(DoubleClickEvent e) {
		e.preventDefault();
		// if (!didSwitch && mouseElm != null)
		if (mouseElm != null)
			doEdit(mouseElm);
	}

	// public void mouseEntered(MouseEvent e) {
	// }

	public void onMouseOut(MouseOutEvent e) {
		// public void mouseExited(MouseEvent e) {
		scopeSelected = -1;
		mouseElm = plotXElm = plotYElm = null;
		// cv.repaint();
	}

	public void onMouseDown(MouseDownEvent e) {
		// public void mousePressed(MouseEvent e) {
		e.preventDefault();
		// IES - hack to only handle left button events in the web version.
		if (e.getNativeButton() != NativeEvent.BUTTON_LEFT)
			return;
		mouseDragging = true;
		didSwitch = false;
		//
		// System.out.println(e.getModifiers());
		// int ex = e.getModifiersEx();
		// // IES - remove interaction
		// // if ((ex & (MouseEvent.META_DOWN_MASK|
		// // MouseEvent.SHIFT_DOWN_MASK)) == 0 && e.isPopupTrigger()) {
		// // doPopupMenu(e);
		// // return;
		// // }
		if (e.getNativeButton() == NativeEvent.BUTTON_LEFT) {
			// // left mouse
			tempMouseMode = mouseMode;
			// if ((ex & MouseEvent.ALT_DOWN_MASK) != 0 &&
			// (ex & MouseEvent.META_DOWN_MASK) != 0)
			if (e.isAltKeyDown() && e.isMetaKeyDown())
				tempMouseMode = MODE_DRAG_COLUMN;
			// else if ((ex & MouseEvent.ALT_DOWN_MASK) != 0 &&
			// (ex & MouseEvent.SHIFT_DOWN_MASK) != 0)
			else if (e.isAltKeyDown() && e.isShiftKeyDown())
				tempMouseMode = MODE_DRAG_ROW;
			// else if ((ex & MouseEvent.SHIFT_DOWN_MASK) != 0)
			else if (e.isShiftKeyDown())
				tempMouseMode = MODE_SELECT;
			// else if ((ex & MouseEvent.ALT_DOWN_MASK) != 0)
			else if (e.isAltKeyDown())
				tempMouseMode = MODE_DRAG_ALL;
			else if (e.isControlKeyDown() || e.isMetaKeyDown())
				tempMouseMode = MODE_DRAG_POST;
		}
		// } else if ((e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) {
		// // right mouse
		// if ((ex & MouseEvent.SHIFT_DOWN_MASK) != 0)
		// tempMouseMode = MODE_DRAG_ROW;
		// else if ((ex & (MouseEvent.CTRL_DOWN_MASK|
		// MouseEvent.META_DOWN_MASK)) != 0)
		// tempMouseMode = MODE_DRAG_COLUMN;
		// else
		// return;
		// }
		//

		// IES - Grab resize handles in select mode if they are far enough apart
		// and you are on top of them
		if (tempMouseMode == MODE_SELECT
				&& mouseElm != null
				&& distanceSq(mouseElm.x, mouseElm.y, mouseElm.x2, mouseElm.y2) >= 256
				&& (distanceSq(e.getX(), e.getY(), mouseElm.x, mouseElm.y) <= POSTGRABSQ || distanceSq(
						e.getX(), e.getY(), mouseElm.x2, mouseElm.y2) <= POSTGRABSQ)
				&& !anySelectedButMouse())
			tempMouseMode = MODE_DRAG_POST;

		if (tempMouseMode != MODE_SELECT && tempMouseMode != MODE_DRAG_SELECTED)
			clearSelection();
		if (doSwitch(e.getX(), e.getY())) {
			didSwitch = true;
			return;
		}

		pushUndo();
		initDragX = e.getX();
		initDragY = e.getY();
		dragging = true;
		if (tempMouseMode != MODE_ADD_ELM)
			return;
		// if (tempMouseMode != MODE_ADD_ELM || addingClass == null)
		// return;
		//
		int x0 = snapGrid(e.getX());
		int y0 = snapGrid(e.getY());
		if (!circuitArea.contains(x0, y0))
			return;

		dragElm = sim.constructElement(mouseModeStr, x0, y0);
	}

	// IES - remove interaction

	// CircuitElm constructElement(Class c, int x0, int y0) {
	// // find element class
	// Class carr[] = new Class[2];
	// //carr[0] = getClass();
	// carr[0] = carr[1] = int.class;
	// Constructor cstr = null;
	// try {
	// cstr = c.getConstructor(carr);
	// } catch (NoSuchMethodException ee) {
	// System.out.println("caught NoSuchMethodException " + c);
	// return null;
	// } catch (Exception ee) {
	// ee.printStackTrace();
	// return null;
	// }
	//
	// // invoke constructor with starting coordinates
	// Object oarr[] = new Object[2];
	// oarr[0] = new Integer(x0);
	// oarr[1] = new Integer(y0);
	// try {
	// return (CircuitElm) cstr.newInstance(oarr);
	// } catch (Exception ee) { ee.printStackTrace(); }
	// return null;
	// }

	// hausen: add doEditMenu
	// void doEditMenu(DoubleClickEvent e) {
	// if( mouseElm != null )
	// doEdit(mouseElm);
	// }

	// void doPopupMenu(MouseEvent e) {
	// menuElm = mouseElm;
	// // IES - removal of scopes
	// // menuScope = -1;
	// // if (scopeSelected != -1) {
	// // PopupMenu m = scopes[scopeSelected].getMenu();
	// // menuScope = scopeSelected;
	// // if (m != null)
	// // m.show(e.getComponent(), e.getX(), e.getY());
	// // } else if (mouseElm != null) {
	// if (mouseElm != null) {
	// elmEditMenuItem .setEnabled(mouseElm.getEditInfo(0) != null);
	// // IES removal of scopes
	// // elmScopeMenuItem.setEnabled(mouseElm.canViewInScope());
	// elmMenu.show(e.getComponent(), e.getX(), e.getY());
	// } else {
	// doMainMenuChecks(mainMenu);
	// mainMenu.show(e.getComponent(), e.getX(), e.getY());
	// }
	// }
	//
	void doMainMenuChecks() {
		int c = mainMenuItems.size();
		int i;
		for (i = 0; i < c; i++)
			mainMenuItems.get(i).setState(
					mainMenuItemNames.get(i) == mouseModeStr);
	}

	// void doMainMenuChecks(Menu m) {
	// int i;
	// if (m == optionsMenu)
	// return;
	// for (i = 0; i != m.getItemCount(); i++) {
	// MenuItem mc = m.getItem(i);
	// if (mc instanceof Menu)
	// doMainMenuChecks((Menu) mc);
	// if (mc instanceof CheckboxMenuItem) {
	// CheckboxMenuItem cmi = (CheckboxMenuItem) mc;
	// cmi.setState(
	// mouseModeStr.compareTo(cmi.getActionCommand()) == 0);
	// }
	// }
	// }
	//
	// public void mouseReleased(MouseEvent e) {
	public void onMouseUp(MouseUpEvent e) {
		e.preventDefault();
		mouseDragging = false;
		// int ex = e.getModifiersEx();
		// // if ((ex & (MouseEvent.SHIFT_DOWN_MASK|MouseEvent.CTRL_DOWN_MASK|
		// // MouseEvent.META_DOWN_MASK)) == 0 && e.isPopupTrigger()) {
		// // doPopupMenu(e);
		// // return;
		// // }
		tempMouseMode = mouseMode;
		selectedArea = null;
		dragging = false;
		boolean circuitChanged = false;
		if (heldSwitchElm != null) {
			heldSwitchElm.mouseUp();
			heldSwitchElm = null;
			circuitChanged = true;
		}
		if (dragElm != null) {
			// if the element is zero size then don't create it
			// IES - and disable any previous selection
			if (dragElm.x == dragElm.x2 && dragElm.y == dragElm.y2) {
				dragElm.delete();
				if (mouseMode == MODE_SELECT || mouseMode == MODE_DRAG_SELECTED)
					clearSelection();
			} else {
				sim.elmList.addElement(dragElm);
				circuitChanged = true;
			}
			dragElm = null;
		}
		if (circuitChanged)
			sim.needAnalyze();
		if (dragElm != null)
			dragElm.delete();
		dragElm = null;
		// cv.repaint();
	}

	public void onMouseWheel(MouseWheelEvent e) {
		e.preventDefault();
		if (ALLOW_MOUSE_WHEEL) {
			scrollValues(e.getNativeEvent().getClientX(), e.getNativeEvent()
					.getClientY(), e.getDeltaY());
			if (mouseElm instanceof MouseWheelHandler)
				((MouseWheelHandler) mouseElm).onMouseWheel(e);
		}
	}

	public void setPowerBarEnable() {
		if (powerCheckItem.getState()) {
			powerLabel.setStyleName("disabled", false);
			powerBar.enable();
		} else {
			powerLabel.setStyleName("disabled", true);
			powerBar.disable();
		}
	}

	void scrollValues(int x, int y, int deltay) {
		if (mouseElm != null && !dialogIsShowing())
			if (mouseElm instanceof ResistorElm
					|| mouseElm instanceof CapacitorElm
					|| mouseElm instanceof Capacitor2Elm
					|| mouseElm instanceof InductorElm) {
				scrollValuePopup = new ScrollValuePopup(x, y, deltay, mouseElm,
						sim);
			}
	}

	public void setGrid() {
		gridSize = (smallGridCheckItem.getState()) ? 8 : 16;
		gridMask = ~(gridSize - 1);
		gridRound = gridSize / 2 - 1;
	}

	void setMouseMode(int mode) {
		mouseMode = mode;
		if (mode == MODE_ADD_ELM) {
			cv.addStyleName("cursorCross");
			cv.removeStyleName("cursorPointer");
		} else {
			cv.addStyleName("cursorPointer");
			cv.removeStyleName("cursorCross");
		}
	}

	void setMenuSelection() {
		if (menuElm != null) {
			if (menuElm.selected)
				return;
			clearSelection();
			menuElm.setSelected(true);
		}
	}

	// public void keyPressed(KeyEvent e) {}
	// public void keyReleased(KeyEvent e) {}

	boolean dialogIsShowing() {
		if (editDialog != null && editDialog.isShowing())
			return true;
		if (exportAsUrlDialog != null && exportAsUrlDialog.isShowing())
			return true;
		if (exportAsTextDialog != null && exportAsTextDialog.isShowing())
			return true;
		if (exportAsLocalFileDialog != null
				&& exportAsLocalFileDialog.isShowing())
			return true;
		if (contextPanel != null && contextPanel.isShowing())
			return true;
		if (scrollValuePopup != null && scrollValuePopup.isShowing())
			return true;
		if (aboutBox != null && aboutBox.isShowing())
			return true;
		if (importFromTextDialog != null && importFromTextDialog.isShowing())
			return true;
		return false;
	}

	public void onPreviewNativeEvent(NativePreviewEvent e) {
		int cc = e.getNativeEvent().getCharCode();
		int t = e.getTypeInt();
		int code = e.getNativeEvent().getKeyCode();
		if (dialogIsShowing()) {
			if (scrollValuePopup != null && scrollValuePopup.isShowing()
					&& (t & Event.ONKEYDOWN) != 0) {
				if (code == KEY_ESCAPE || code == KEY_SPACE)
					scrollValuePopup.close(false);
				if (code == KEY_ENTER)
					scrollValuePopup.close(true);
			}
			if (editDialog != null && editDialog.isShowing()
					&& (t & Event.ONKEYDOWN) != 0) {
				if (code == KEY_ESCAPE)
					editDialog.closeDialog();
				if (code == KEY_ENTER) {
					editDialog.apply();
					editDialog.closeDialog();
				}
			}
			return;
		}
		if ((t & Event.ONKEYDOWN) != 0) {

			if (code == KEY_BACKSPACE || code == KEY_DELETE) {
				doDelete();
				e.cancel();
			}
			if (code == KEY_ESCAPE) {
				setMouseMode(MODE_SELECT);
				mouseModeStr = "Select";
				tempMouseMode = mouseMode;
				e.cancel();
			}
			if (e.getNativeEvent().getCtrlKey()
					|| e.getNativeEvent().getMetaKey()) {
				if (code == KEY_C) {
					menuPerformed("key", "copy");
					e.cancel();
				}
				if (code == KEY_X) {
					menuPerformed("key", "cut");
					e.cancel();
				}
				if (code == KEY_V) {
					menuPerformed("key", "paste");
					e.cancel();
				}
				if (code == KEY_Z) {
					menuPerformed("key", "undo");
					e.cancel();
				}
				if (code == KEY_Y) {
					menuPerformed("key", "redo");
					e.cancel();
				}
				if (code == KEY_A) {
					menuPerformed("key", "selectAll");
					e.cancel();
				}
			}
		}
		if ((t & Event.ONKEYPRESS) != 0) {
			if (cc > 32 && cc < 127) {
				String c = shortcuts[cc];
				e.cancel();
				if (c == null)
					return;
				setMouseMode(MODE_ADD_ELM);
				mouseModeStr = c;
				tempMouseMode = mouseMode;
			}
			if (cc == 32) {
				setMouseMode(MODE_SELECT);
				mouseModeStr = "Select";
				tempMouseMode = mouseMode;
				e.cancel();
			}
		}
	}

	public void addWidgetToVerticalPanel(Widget w) {
		if (iFrame != null) {
			int i = verticalPanel.getWidgetIndex(iFrame);
			verticalPanel.insert(w, i);
			setiFrameHeight();
		} else
			verticalPanel.add(w);
	}

	public void removeWidgetFromVerticalPanel(Widget w) {
		verticalPanel.remove(w);
		if (iFrame != null)
			setiFrameHeight();
	}

	// IES - remove interaction
	public void menuPerformed(String menu, String item) {
		if (item == "about")
			aboutBox = new AboutBox(circuitjs1.versionString);
		if (item == "importfromlocalfile") {
			pushUndo();
			sim.loadFileInput.click();
		}
		if (item == "importfromtext") {
			importFromTextDialog = new ImportFromTextDialog(sim);
		}
		if (item == "exportasurl") {
			doExportAsUrl();
		}
		if (item == "exportaslocalfile")
			doExportAsLocalFile();
		if (item == "exportastext")
			doExportAsText();
		if ((menu == "elm" || menu == "scopepop") && contextPanel != null)
			contextPanel.hide();
		if (menu == "options" && item == "other")
			doEdit(new EditOptions(sim));
		// public void actionPerformed(ActionEvent e) {
		// String ac = e.getActionCommand();
		// if (e.getSource() == resetButton) {
		// int i;
		//
		// // on IE, drawImage() stops working inexplicably every once in
		// // a while. Recreating it fixes the problem, so we do that here.
		// dbimage = main.createImage(winSize.width, winSize.height);
		//
		// for (i = 0; i != elmList.size(); i++)
		// getElm(i).reset();
		// // IES - removal of scopes
		// // for (i = 0; i != scopeCount; i++)
		// // scopes[i].resetGraph();
		// analyzeFlag = true;
		// t = 0;
		// stoppedCheck.setState(false);
		// cv.repaint();
		// }
		// if (e.getSource() == dumpMatrixButton)
		// dumpMatrix = true;
		// IES - remove import export
		// if (e.getSource() == exportItem)
		// doExport(false);
		// if (e.getSource() == optionsItem)
		// doEdit(new EditOptions(this));
		// if (e.getSource() == importItem)
		// doImport();
		// if (e.getSource() == exportLinkItem)
		// doExport(true);
		if (item == "undo")
			doUndo();
		if (item == "redo")
			doRedo();
		if (item == "cut") {
			if (menu != "elm")
				menuElm = null;
			doCut();
		}
		if (item == "copy") {
			if (menu != "elm")
				menuElm = null;
			doCopy();
		}
		if (item == "paste")
			doPaste();
		if (item == "selectAll")
			doSelectAll();
		// if (e.getSource() == exitItem) {
		// destroyFrame();
		// return;
		// }

		if (item == "centrecircuit") {
			pushUndo();
			centreCircuit();
		}
		if (item == "stackAll")
			stackAll();
		if (item == "unstackAll")
			unstackAll();
		if (menu == "elm" && item == "edit")
			doEdit(menuElm);
		if (item == "delete") {
			if (menu == "elm")
				menuElm = null;
			doDelete();
		}

		if (item == "viewInScope" && menuElm != null) {
			int i;
			for (i = 0; i != scopeCount; i++)
				if (scopes[i].elm == null)
					break;
			if (i == scopeCount) {
				if (scopeCount == scopes.length)
					return;
				scopeCount++;
				scopes[i] = new Scope(sim);
				scopes[i].position = i;
				// handleResize();
			}
			scopes[i].setElm(menuElm);
		}
		if (menu == "scopepop") {
			pushUndo();
			if (item == "remove")
				scopes[menuScope].setElm(null);
			if (item == "speed2")
				scopes[menuScope].speedUp();
			if (item == "speed1/2")
				scopes[menuScope].slowDown();
			if (item == "scale")
				scopes[menuScope].adjustScale(.5);
			if (item == "maxscale")
				scopes[menuScope].adjustScale(1e-50);
			if (item == "stack")
				stackScope(menuScope);
			if (item == "unstack")
				unstackScope(menuScope);
			if (item == "selecty")
				scopes[menuScope].selectY();
			if (item == "reset")
				scopes[menuScope].resetGraph();
			if (item.indexOf("show") == 0 || item == "plotxy" || item=="showfft")
				scopes[menuScope].handleMenu(item);
			// cv.repaint();
		}
		if (menu == "circuits" && item.indexOf("setup ") == 0) {
			pushUndo();
			sim.readSetupFile(item.substring(6), "", true);
		}

		// if (ac.indexOf("setup ") == 0) {
		// pushUndo();
		// readSetupFile(ac.substring(6),
		// ((MenuItem) e.getSource()).getLabel());
		// }

		// IES: Moved from itemStateChanged()
		if (menu == "main") {
			if (contextPanel != null)
				contextPanel.hide();
			// MenuItem mmi = (MenuItem) mi;
			// int prevMouseMode = mouseMode;
			setMouseMode(MODE_ADD_ELM);
			String s = item;
			if (s.length() > 0)
				mouseModeStr = s;
			if (s.compareTo("DragAll") == 0)
				setMouseMode(MODE_DRAG_ALL);
			else if (s.compareTo("DragRow") == 0)
				setMouseMode(MODE_DRAG_ROW);
			else if (s.compareTo("DragColumn") == 0)
				setMouseMode(MODE_DRAG_COLUMN);
			else if (s.compareTo("DragSelected") == 0)
				setMouseMode(MODE_DRAG_SELECTED);
			else if (s.compareTo("DragPost") == 0)
				setMouseMode(MODE_DRAG_POST);
			else if (s.compareTo("Select") == 0)
				setMouseMode(MODE_SELECT);
			// else if (s.length() > 0) {
			// try {
			// addingClass = Class.forName(s);
			// } catch (Exception ee) {
			// ee.printStackTrace();
			// }
			// }
			// else
			// setMouseMode(prevMouseMode);
			tempMouseMode = mouseMode;
		}
	}

	public void pushUndo() {
		redoStack.removeAllElements();
		String s = sim.dumpCircuit();
		if (undoStack.size() > 0 && s.compareTo(undoStack.lastElement()) == 0)
			return;
		undoStack.add(s);
		enableUndoRedo();
	}

	void doUndo() {
		if (undoStack.size() == 0)
			return;
		redoStack.add(sim.dumpCircuit());
		String s = undoStack.remove(undoStack.size() - 1);
		sim.readSetup(s, false);
		enableUndoRedo();
	}

	void doRedo() {
		if (redoStack.size() == 0)
			return;
		undoStack.add(sim.dumpCircuit());
		String s = redoStack.remove(redoStack.size() - 1);
		sim.readSetup(s, false);
		enableUndoRedo();
	}

	void enableUndoRedo() {
		redoItem.setEnabled(redoStack.size() > 0);
		undoItem.setEnabled(undoStack.size() > 0);
	}

	void doCut() {
		int i;
		pushUndo();
		setMenuSelection();
		clipboard = "";
		for (i = sim.elmList.size() - 1; i >= 0; i--) {
			CircuitElm ce = sim.getElm(i);
			if (ce.isSelected()) {
				clipboard += ce.dump() + "\n";
				ce.delete();
				sim.elmList.removeElementAt(i);
			}
		}
		enablePaste();
		sim.needAnalyze();
	}

	void doDelete() {
		int i;
		pushUndo();
		setMenuSelection();
		boolean hasDeleted = false;

		for (i = sim.elmList.size() - 1; i >= 0; i--) {
			CircuitElm ce = sim.getElm(i);
			if (ce.isSelected()) {
				ce.delete();
				sim.elmList.removeElementAt(i);
				hasDeleted = true;
			}
		}

		if (!hasDeleted) {
			for (i = sim.elmList.size() - 1; i >= 0; i--) {
				CircuitElm ce = sim.getElm(i);
				if (ce == mouseElm) {
					ce.delete();
					sim.elmList.removeElementAt(i);
					hasDeleted = true;
					setMouseElm(null);
					break;
				}
			}
		}

		if (hasDeleted)
			sim.needAnalyze();
	}

	void doCopy() {
		int i;
		clipboard = "";
		setMenuSelection();
		for (i = sim.elmList.size() - 1; i >= 0; i--) {
			CircuitElm ce = sim.getElm(i);
			if (ce.isSelected())
				clipboard += ce.dump() + "\n";
		}
		enablePaste();
	}

	void enablePaste() {
		pasteItem.setEnabled(clipboard.length() > 0);
	}

	void doPaste() {
		pushUndo();
		clearSelection();
		int i;
		Rectangle oldbb = null;
		for (i = 0; i != sim.elmList.size(); i++) {
			CircuitElm ce = sim.getElm(i);
			Rectangle bb = ce.getBoundingBox();
			if (oldbb != null)
				oldbb = oldbb.union(bb);
			else
				oldbb = bb;
		}
		int oldsz = sim.elmList.size();
		sim.readSetup(clipboard, true, false);

		// select new items
		Rectangle newbb = null;
		for (i = oldsz; i != sim.elmList.size(); i++) {
			CircuitElm ce = sim.getElm(i);
			ce.setSelected(true);
			Rectangle bb = ce.getBoundingBox();
			if (newbb != null)
				newbb = newbb.union(bb);
			else
				newbb = bb;
		}
		if (oldbb != null && newbb != null && oldbb.intersects(newbb)) {
			// find a place for new items
			int dx = 0, dy = 0;
			int spacew = circuitArea.getWidth() - oldbb.getWidth()
					- newbb.getWidth();
			int spaceh = circuitArea.getHeight() - oldbb.getHeight()
					- newbb.getHeight();
			if (spacew > spaceh)
				dx = snapGrid(oldbb.getX() + oldbb.getWidth() - newbb.getX()
						+ gridSize);
			else
				dy = snapGrid(oldbb.getY() + oldbb.getHeight() - newbb.getY()
						+ gridSize);
			for (i = oldsz; i != sim.elmList.size(); i++) {
				CircuitElm ce = sim.getElm(i);
				ce.move(dx, dy);
			}
			// center circuit
			// handleResize();
		}
		sim.needAnalyze();
	}

	void clearSelection() {
		int i;
		for (i = 0; i != sim.elmList.size(); i++) {
			CircuitElm ce = sim.getElm(i);
			ce.setSelected(false);
		}
	}

	void doSelectAll() {
		int i;
		for (i = 0; i != sim.elmList.size(); i++) {
			CircuitElm ce = sim.getElm(i);
			ce.setSelected(true);
		}
	}

	boolean anySelectedButMouse() {
		for (int i = 0; i != sim.elmList.size(); i++)
			if (sim.getElm(i) != mouseElm && sim.getElm(i).selected)
				return true;
		return false;
	}

	boolean doSwitch(int x, int y) {
		if (mouseElm == null || !(mouseElm instanceof SwitchElm))
			return false;
		SwitchElm se = (SwitchElm) mouseElm;
		se.toggle();
		if (se.momentary)
			heldSwitchElm = se;
		sim.needAnalyze();
		return true;
	}

	public void stop(String s, CircuitElm ce) {
		stopMessage = s;
		stoppedCheck.setState(true);
		stopElm = ce;
	}

	public double getIterCount() {
		// IES - remove interaction
		if (speedBar.getValue() == 0)
			return 0;

		return .1 * Math.exp((speedBar.getValue() - 61) / 24.);

	}

	public Graphics beginRender(){
	Graphics g = new Graphics(backcontext);
	
	if (printableCheckItem.getState()) {
		CircuitElm.whiteColor = Color.black;
		CircuitElm.lightGrayColor = Color.black;
		g.setColor(Color.white);
	} else {
		CircuitElm.whiteColor = Color.white;
		CircuitElm.lightGrayColor = Color.lightGray;
		g.setColor(Color.black);
	}
	
	g.fillRect(0, 0, g.context.getCanvas().getWidth(), g.context
			.getCanvas().getHeight());
	return g;
}
	
	public void render(Graphics g, Font oldfont,int badnodes,double t){	
		int i;
	/*
	 * if (mouseElm != null) { g.setFont(oldfont); g.drawString("+",
	 * mouseElm.x+10, mouseElm.y); }
	 */
	if (dragElm != null
			&& (dragElm.x != dragElm.x2 || dragElm.y != dragElm.y2)) {
		sim.drawElmWithHandles(dragElm,g);
	}
	
	g.setFont(oldfont);
	int ct = scopeCount;
	if (stopMessage != null)
		ct = 0;
	for (i = 0; i != ct; i++)
		scopes[i].draw(g);
	g.setColor(CircuitElm.whiteColor);
	if (stopMessage != null) {
		g.drawString(stopMessage, 10, circuitArea.getHeight() - 10);
	} else {
		if (sim.circuitBottom == 0)
			sim.calcCircuitBottom();
		
		String[] info = sim.getInfo();
		
		if (sim.hintType != -1) {
			for (i = 0; info[i] != null; i++)
				;
			String s = sim.getHint();
			if (s == null)
				sim.hintType = -1;
			else
				info[i] = s;
		}
		int x = 0;
		if (ct != 0)
			x = scopes[ct - 1].rightEdge() + 20;
		x = max(x, cv.getCoordinateSpaceWidth() * 2 / 3);
		// x=cv.getCoordinateSpaceWidth()*2/3;

		// count lines of data
		for (i = 0; info[i] != null; i++)
			;
		if (badnodes > 0)
			info[i++] = badnodes
					+ ((badnodes == 1) ? " bad connection"
							: " bad connections");

		// find where to show data; below circuit, not too high unless we
		// need it
		// int ybase = winSize.height-15*i-5;
		int ybase = cv.getCoordinateSpaceHeight() - 15 * i - 5;
		ybase = min(ybase, circuitArea.getHeight());
		ybase = max(ybase, sim.circuitBottom);
		for (i = 0; info[i] != null; i++)
			g.drawString(info[i], x, ybase + 15 * (i + 1));
	}
	if (selectedArea != null) {
		g.setColor(CircuitElm.selectColor);
		g.drawRect(selectedArea.getX(), selectedArea.getY(),
				selectedArea.getWidth(), selectedArea.getHeight());
	}

	g.setColor(Color.white);
	// g.drawString("Framerate: " + CircuitElm.showFormat.format(framerate),
	// 10, 10);
	// g.drawString("Steprate: " + CircuitElm.showFormat.format(steprate),
	// 10, 30);
	// g.drawString("Steprate/iter: " +
	// CircuitElm.showFormat.format(steprate/getIterCount()), 10, 50);
	// g.drawString("iterc: " +
	// CircuitElm.showFormat.format(getIterCount()), 10, 70);
	// g.drawString("Frames: "+ frames,10,90);
	// g.drawString("ms per frame (other): "+
	// CircuitElm.showFormat.format((mytime-myruntime-mydrawtime)/myframes),10,110);
	// g.drawString("ms per frame (sim): "+
	// CircuitElm.showFormat.format((myruntime)/myframes),10,130);
	// g.drawString("ms per frame (draw): "+
	// CircuitElm.showFormat.format((mydrawtime)/myframes),10,150);

	cvcontext.drawImage(backcontext.getCanvas(), 0.0, 0.0);
	// IES - remove interaction and delay
	// if (!stoppedCheck.getState() && circuitMatrix != null) {
	// Limit to 50 fps (thanks to Jurgen Klotzer for this)
	// long delay = 1000/50 - (System.currentTimeMillis() - lastFrameTime);
	// realg.drawString("delay: " + delay, 10, 110);
	// if (delay > 0) {
	// try {
	// Thread.sleep(delay);
	// } catch (InterruptedException e) {
	// }
	// }

	// cv.repaint(0);
	// }
}
	
	int min(int a, int b) {
		return (a < b) ? a : b;
	}

	int max(int a, int b) {
		return (a > b) ? a : b;
	}

	public void processSetupList(byte b[], int len, final boolean openDefault) {
		MenuBar currentMenuBar;
		MenuBar stack[] = new MenuBar[6];
		int stackptr = 0;
		currentMenuBar = new MenuBar(true);
		currentMenuBar.setAutoOpen(true);
		menuBar.addItem("Circuits", currentMenuBar);
		stack[stackptr++] = currentMenuBar;
		int p;
		for (p = 0; p < len;) {
			int l;
			for (l = 0; l != len - p; l++)
				if (b[l + p] == '\n') {
					l++;
					break;
				}
			String line = new String(b, p, l - 1);
			if (line.charAt(0) == '#')
				;
			else if (line.charAt(0) == '+') {
				// MenuBar n = new Menu(line.substring(1));
				MenuBar n = new MenuBar(true);
				n.setAutoOpen(true);
				currentMenuBar.addItem(line.substring(1), n);
				currentMenuBar = stack[stackptr++] = n;
			} else if (line.charAt(0) == '-') {
				currentMenuBar = stack[--stackptr - 1];
			} else {
				int i = line.indexOf(' ');
				if (i > 0) {
					String title = line.substring(i + 1);
					boolean first = false;
					if (line.charAt(0) == '>')
						first = true;
					String file = line.substring(first ? 1 : 0, i);
					// menu.add(getMenuItem(title, "setup " + file));
					currentMenuBar.addItem(new MenuItem(title, new MyCommand(
							"circuits", "setup " + file)));
					if (first && startCircuit == null) {
						startCircuit = file;
						startLabel = title;
						if (openDefault && stopMessage == null)
							sim.readSetupFile(startCircuit, startLabel,
									true);
					}
				}
			}
			p += l;
		}
	}

	
	public void setupScopes() {
		int i;

		// check scopes to make sure the elements still exist, and remove
		// unused scopes/columns
		int pos = -1;
		for (i = 0; i < scopeCount; i++) {
			if (sim.locateElm(scopes[i].elm) < 0)
				scopes[i].setElm(null);
			if (scopes[i].elm == null) {
				int j;
				for (j = i; j != scopeCount; j++)
					scopes[j] = scopes[j + 1];
				scopeCount--;
				i--;
				continue;
			}
			if (scopes[i].position > pos + 1)
				scopes[i].position = pos + 1;
			pos = scopes[i].position;
		}
		while (scopeCount > 0 && scopes[scopeCount - 1].elm == null)
			scopeCount--;
		int h = cv.getCoordinateSpaceHeight() - circuitArea.getHeight();
		pos = 0;
		for (i = 0; i != scopeCount; i++)
			scopeColCount[i] = 0;
		for (i = 0; i != scopeCount; i++) {
			pos = max(scopes[i].position, pos);
			scopeColCount[scopes[i].position]++;
		}
		int colct = pos + 1;
		int iw = infoWidth;
		if (colct <= 2)
			iw = iw * 3 / 2;
		int w = (cv.getCoordinateSpaceWidth() - iw) / colct;
		int marg = 10;
		if (w < marg * 2)
			w = marg * 2;
		pos = -1;
		int colh = 0;
		int row = 0;
		int speed = 0;
		for (i = 0; i != scopeCount; i++) {
			Scope s = scopes[i];
			if (s.position > pos) {
				pos = s.position;
				colh = h / scopeColCount[pos];
				row = 0;
				speed = s.speed;
			}
			if (s.speed != speed) {
				s.speed = speed;
				s.resetGraph();
			}
			Rectangle r = new Rectangle(pos * w,
					cv.getCoordinateSpaceHeight() - h + colh * row, w
							- marg, colh);
			row++;
			if (!r.equals(s.rect))
				s.setRect(r);
		}
	}

}
