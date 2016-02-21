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

package com.lushprojects.circuitjs1.client.sim;

// info about each row/column of the matrix for simplification purposes
    public class RowInfo {
    	public static final int ROW_NORMAL = 0;  // ordinary value
    	public static final int ROW_CONST  = 1;  // value is constant
    	public static final int ROW_EQUAL  = 2;  // value is equal to another value
	public int nodeEq, type, mapCol, mapRow;
	public double value;
	public boolean rsChanges; // row's right side changes
	public boolean lsChanges; // row's left side changes
	public boolean dropRow;   // row is not needed in matrix
	 public RowInfo() { type = ROW_NORMAL; }
    }