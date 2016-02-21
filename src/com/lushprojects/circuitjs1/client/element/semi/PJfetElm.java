package com.lushprojects.circuitjs1.client.element.semi;


public class PJfetElm extends JfetElm {
	public PJfetElm(int xx, int yy) { super(xx, yy, true); }
	@SuppressWarnings("rawtypes")
	protected Class getDumpClass() { return JfetElm.class; }
 }

