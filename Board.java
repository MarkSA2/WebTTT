package com.mark.WebTTT.client;
import com.google.gwt.user.client.ui.Grid;

/**
 * A generic tic tac toe board of specified rows and columns that is completely changeable. 
 * 
 * @author Mark Adams
 * @version 2013-05-21
 * 
 */
public class Board extends Grid 
{
  /**
	 * Initializes the Board to single space Strings.
	 * 
	 * @param rows -> the amount of rows for the Board to contain.
	 * @param columns -> the amount of columns for the Board to contain.
	 */
	public Board(int rows, int columns)
	{
		super (rows, columns);
		for (int column = 0; column < columns; column++)
			for (int row = 0; row < rows; row++)
			{
				this.setText(row, column, " ");
			}
	}
}
