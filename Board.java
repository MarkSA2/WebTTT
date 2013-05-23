package com.mark.WebTTT.client;
import com.google.gwt.user.client.ui.Grid;

/**
 * A generic tic tac toe board of specified rows and columns that is completely changeable. 
 * 
 * @author Mark Adams
 * @version 2013-05-23
 * 
 */
public class Board extends Grid 
{
	private int rows, columns;
	private final int MAX_BOARDNAME_LENGTH = 50;
	
	/**
	 * Initializes the Board to single space Strings.
	 * 
	 * @param rows -> the amount of rows for the Board to contain.
	 * @param columns -> the amount of columns for the Board to contain.
	 */
	public Board(int rows, int columns)
	{
		super (rows, columns);
		setStyleName("board");
		
		this.rows = rows;
		this.columns = columns;
		
		for (int column = 0; column < this.columns; column++)
		{
			
			for (int row = 0; row < this.rows; row++)
			{
				this.setText(row, column, " ");
			
				if(row != this.rows - 1)
					this.getRowFormatter().setStyleName(row, "bottom");
			}
			
			if (column != this.columns - 1)
				this.getColumnFormatter().setStyleName(column, "side");
		}
		
	}
	
	/**
	 * Rotates the Board 90 degrees clockwise.
	 * 
	 * @return A clone of the given Board that has been rotated 90 degrees clockwise.
	 * 
	 * >>>exampleBoard.rotatedCW();
	 * 
	 *  0 | 1 | 2            6 | 3 | 0
	 * ---+---+---          ---+---+---
	 *  3 | 4 | 5     =>     7 | 4 | 1
	 * ---+---+---          ---+---+---
	 *  6 | 7 | 8            8 | 5 | 2    
	 */
	public Board rotatedCW()
	{
		return this.cloneBoard().transpose().flipColumns();
	}

	/**
	 * Rotates the Board 90 degrees counter clockwise.
	 * 
	 * @return A clone of the given Board that has been rotated 90 degrees counter clockwise.
	 * 
	 * >>>exampleBoard.rotatedCC();
	 * 
	 *  0 | 1 | 2            2 | 5 | 8
	 * ---+---+---          ---+---+---
	 *  3 | 4 | 5     =>     1 | 4 | 7
	 * ---+---+---          ---+---+---
	 *  6 | 7 | 8            0 | 3 | 6      
	 */
	public Board rotatedCC()
	{
		return this.cloneBoard().transpose().flipRows();
	}
	
	/**
	 * Flips the Board 180 degrees about the y-axis.
	 * 
	 * @return A clone of the given Board that has had its contents flipped about the y-axis.
	 * 
	 * >>>exampleBoard.flipColumns();
	 * 
	 *  0 | 1 | 2            2 | 1 | 0
	 * ---+---+---          ---+---+---
	 *  3 | 4 | 5     =>     5 | 4 | 3
	 * ---+---+---          ---+---+---
	 *  6 | 7 | 8            8 | 7 | 6         
	 */
	public Board flipColumns()
	{
		Board result = new Board(this.rows, this.columns);
		
		for(int column = 0; column < this.columns; column++)
			for (int row = 0; row < this.rows; row++)
				result.setText(row, column, this.getText(row, this.columns - column - 1));
		
		return result;
	}
	
	/**
	 * Flips the Board 180 degrees about the x-axis.
	 * 
	 * @return A clone of the given Board that has had its contents flipped about the x-axis.
	 * 
	 * >>>exampleBoard.flipRows();
	 * 
	 *  0 | 1 | 2            6 | 7 | 8
	 * ---+---+---          ---+---+---
	 *  3 | 4 | 5     =>     3 | 4 | 5
	 * ---+---+---          ---+---+---
	 *  6 | 7 | 8            0 | 7 | 8         
	 */
	public Board flipRows()
	{
		Board result = new Board(this.rows, this.columns);
		
		for(int column = 0; column < this.columns; column++)
			for (int row = 0; row < this.rows; row++)
				result.setText(row, column, this.getText(this.rows - row - 1, column));
		
		return result;
	}
	
	/**
	 * Moves every column on the Board in a specified direction.
	 * 
	 * @return A clone of the given Board that has had its columns shifted in a specified direction.
	 * 
	 * 	>>>exampleBoard.shiftColumns(1);
	 * 
	 *  0 | 1 | 2              | 0 | 1 
	 * ---+---+---          ---+---+---
	 *  3 | 4 | 5     =>       | 3 | 4
	 * ---+---+---          ---+---+---
	 *  6 | 7 | 8              | 6 | 7       
	 */
	public Board shiftColumns(int direction)
	{
		Board result = new Board(this.rows, this.columns);
		
		if (direction == -1)
			for (int column = 0; column < this.columns; column++)
				for (int row = 0; row < this.rows; row++)
				{
					String temp = this.getText(row, column);
					
					if (!((column + direction) > this.columns || (column + direction) < 0))	
						result.setText(row, column + direction, temp);
				}
		else if (direction == 1)
			for (int column = 0; column < this.columns; column++)
				for (int row = 0; row < this.rows; row++)
				{
					String temp = " ";
					
					if (!((column - direction) > this.columns || (column - direction < 0)))
							temp = this.getText(row, column - direction);
					
					result.setText(row, column, temp);
				}
		
		return result;
	}
	
	/**
	 * Moves every row on the Board in a specified direction.
	 * 
	 * @return A clone of the given Board that has had its rows shifted in a specified direction.
	 * 
	 * 	>>>exampleBoard.shiftRows(1);
	 * 
	 *  0 | 1 | 2              |   |  
	 * ---+---+---          ---+---+---
	 *  3 | 4 | 5     =>     0 | 1 | 2
	 * ---+---+---          ---+---+---
	 *  6 | 7 | 8            3 | 4 | 5       
	 */
	public Board shiftRows(int direction)
	{
		Board result = new Board(this.rows, this.columns);
		
		if (direction == -1)
			for (int column = 0; column < this.columns; column++)
				for (int row = 0; row < this.rows; row++)
				{
					String temp = this.getText(row, column);
					
					if (!((row + direction) > this.columns || (row + direction) < 0))	
						result.setText(row + direction, column, temp);
				}
		else if (direction == 1)
			for (int column = 0; column < this.columns; column++)
				for (int row = 0; row < this.rows; row++)
				{
					String temp = "?";
					
					if (!((row - direction) > this.columns || (row - direction < 0)))
							temp = this.getText(row - direction, column);
					
					result.setText(row, column, temp);
				}
		
		return result;
	}
	
	/**
	 * Switches each values the row position with its column position.
	 * 
	 * @return A clone of the given Board that has been transposed.
	 * 
	 * 	>>>exampleBoard.transpose();
	 * 
	 *  0 | 1 | 2            0 | 3 | 6
	 * ---+---+---          ---+---+---
	 *  3 | 4 | 5     =>     1 | 4 | 7
	 * ---+---+---          ---+---+---
	 *  6 | 7 | 8            2 | 5 | 8       
	 */
	private Board transpose()
	{
		Board result = new Board(this.columns, this.rows);
		
		for (int column = 0; column < this.rows; column++)
			for (int row = 0; row < this.columns; row++)
				result.setText(row, column, this.getText(column, row));
		
		return result;
	}
	
	/**
	 * Copies all of the contents of a Board into a new one.
	 * 
	 * @return A replica of the Board instance.
	 */
	public Board cloneBoard()
	{
		Board result = new Board(this.rows, this.columns);
		
		for (int column = 0; column < this.columns; column++)
			for (int row = 0; row < this.rows; row++)
				result.setText(row, column, this.getText(row, column));

		return result;
	}
	
	/**
	 * Converts the Board into a string representation.
	 * 
	 * @return A string representation of the Board.
	 */
	public String toString()
	{
		String result = "";
		
		for (int row = 0; row < this.rows; row++)
			for (int column = 0; column < this.columns; column++)
			{
				result += " " + this.getText(row, column);
				
				if(column != this.columns - 1)
					result += " |";	
				else if (row != this.rows - 1)
					result += "\n" + generateRowSeparator() + "\n";
			}
		
		result += "\n";
		
		return result;
	}
	
	/**
	 * Outputs a visual representation of the Board instance to the console.
	 * 
	 * @param boardName -> A name to distinguish this Board from others.
	 */
	public void toConsole(String boardName)
	{
		String rowSeparator = generateRowSeparator();
		String offset = generateOffset();
		String result = "" + offset;
		
		printBoardName(boardName);
		
		for (int row = 0; row < this.rows; row++)
		{
			for (int column = 0; column < this.columns; column++)
			{
				result += " " + this.getText(row, column);
				
				if(column != this.columns - 1)
					result += " |";	
			}
			
			if(row != this.rows - 1)
				result += "\n" + offset + rowSeparator + "\n" + offset;
		}
		
		result += "\n\n";
		
		System.out.print(result);
	}

	/**
	 * Outputs the name of the Board to the console in between a border of equals symbols. 
	 * 
	 * @param boardName -> A name to distinguish the Board by.
	 */
	private void printBoardName(String boardName)
	{
		int nameStart = (this.MAX_BOARDNAME_LENGTH - boardName.length()) / 2;
		
		String topBottom = "", spaces = "";
		
		for (int i = 0; i < MAX_BOARDNAME_LENGTH; i++)
			topBottom += "=";
		
		for (int i = 0; i < nameStart - 1; i++)
			spaces += " ";
		
		System.out.println(topBottom + "\n" + "|" + spaces + boardName + spaces + " |\n" + topBottom);
	}
	
	/**
	 * Generates the line used to separate the Board rows from each other.
	 * 
	 * @return A string of the row separator.
	 */
	private String generateRowSeparator()
	{
		String result = "";
		
		for (int column = 0; column < this.columns; column++)
		{                   
			if (column != this.columns - 1)
				result += "---+";
			else
				result += "---";
		}
		
		return result;
	}
	
	/**
	 * Generates a string of spaces to offset the Board to a position centered under the Board's name.
	 * 
	 * @return A string of spaces.
	 */
	private String generateOffset()
	{
		int offset = (MAX_BOARDNAME_LENGTH - ((4 * this.columns) - 1)) / 2;
		String result = "";
		
		for (int i = 0; i < offset; i++)
			result += " ";
		
		return result;
	}
} 
