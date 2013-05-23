package com.mark.WebTTT.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Label;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class WebTTTViewer implements EntryPoint 
{
  public String[] pieces = {"X", "O", "?", "_"};
	public int piecesIndex, boardSpace;
	public final int BOARD_OFFSET = 30;
	public Label pieceSelector;
	public Board board, rCW90, rCW180, rCW270;
	
	public void onModuleLoad() 
	{
		RootPanel rootPanel = RootPanel.get();
		
		this.board = new Board(3, 3);
		this.board.setStyleName("editor");
		rootPanel.add(this.board);
		
		//this.board.toConsole("Original");
		
		this.boardSpace = this.board.getColumnCount() * 2 * 16; // 1em == 16px each table cell is 2em^2 
		this.rCW90 = this.board.rotatedCW();
		rootPanel.add(this.rCW90, this.board.getAbsoluteLeft() + this.boardSpace + this.BOARD_OFFSET, this.board.getAbsoluteTop());
		
		//this.rCW90.toConsole("rCW90");
		
		this.rCW180 = this.rCW90.rotatedCW();
		rootPanel.add(this.rCW180, this.board.getAbsoluteLeft(), this.board.getAbsoluteTop() + this.boardSpace + this.BOARD_OFFSET);
		
		//this.rCW180.toConsole("rCW180");
		
		this.rCW270 = this.rCW180.rotatedCW();
		rootPanel.add(this.rCW270, this.rCW180.getAbsoluteLeft() + this.boardSpace + this.BOARD_OFFSET, this.rCW180.getAbsoluteTop());
		
		//this.rCW270.toConsole("rCW270");
		
		this.pieceSelector = new Label(this.pieces[0]);
		rootPanel.add(this.pieceSelector, this.board.getAbsoluteLeft() + (2 * (this.boardSpace + this.BOARD_OFFSET)), this.board.getAbsoluteTop());
		this.pieceSelector.setStyleName("pieceSelector");
		
		board.addClickHandler(new ClickHandler()
		{
			public void onClick(ClickEvent event) 
			{
				//TODO: Reduce the time complexity. 
				//TODO: Makes this work to m X n sized boards.
				WebTTTViewer.this.board.setText(WebTTTViewer.this.board.getCellForEvent(event).getRowIndex(), WebTTTViewer.this.board.getCellForEvent(event).getCellIndex(), WebTTTViewer.this.pieces[WebTTTViewer.this.piecesIndex]);
				
				Board rCW90 = WebTTTViewer.this.board.rotatedCW();
				Board rCW180 = rCW90.rotatedCW();
				Board rCW270 = rCW180.rotatedCW();				
				
				int columns = rCW90.getColumnCount(), rows = rCW90.getRowCount();
				
				for (int column = 0; column < columns; column++)
					for (int row = 0; row < rows; row++)
					{
						WebTTTViewer.this.rCW90.setText(row, column, rCW90.getText(row, column));
						WebTTTViewer.this.rCW180.setText(row, column, rCW180.getText(row, column));
						WebTTTViewer.this.rCW270.setText(row, column, rCW270.getText(row, column));
					}
			}
		});
	
		this.pieceSelector.addClickHandler(new ClickHandler()
		{
			public void onClick(ClickEvent event)
			{
				if (WebTTTViewer.this.piecesIndex < WebTTTViewer.this.pieces.length - 1)
					WebTTTViewer.this.piecesIndex++;
				else
					WebTTTViewer.this.piecesIndex = 0;
				
				WebTTTViewer.this.pieceSelector.setText(WebTTTViewer.this.pieces[WebTTTViewer.this.piecesIndex]);
			}
		});
	}
}
