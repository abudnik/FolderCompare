package fc.app;

import android.graphics.Color;

class ListSelector
{
	private FileListView left, right;
	private FileListView selected;
	private boolean attemptExit;
	
	
	public void SetListView( FileListView left, FileListView right )
	{
		this.left = left;
		this.right = right;
	}
	
	private void Unselect( FileListView v )
	{
		v.GetDirView().setBackgroundColor( Color.BLACK );
	}
	
	private void Select( FileListView v )
	{
		v.GetDirView().setBackgroundColor( Color.BLUE );
	}
	
	public void SetAttemptExit( boolean attemptExit )
	{
		this.attemptExit = attemptExit;
	}
	
	public boolean GetAttemptExit()
	{
		return attemptExit;
	}
	
	public void SetView( FileListView v )
	{
		if ( selected != null )
			Unselect( selected );
		
		Select( v );
		
		attemptExit = false;
		selected = v;
	}
	
	public void SetView( int listType )
	{
		if ( listType == FileListView.LIST_LEFT )
		{
			SetView( left );
		}
		else
		if ( listType == FileListView.LIST_RIGHT )
		{
			SetView( right );
		}
	}
	
	public FileListView GetView()
	{
		return selected;
	}
	
	public int GetListType()
	{
		return selected.GetListType();
	}
}
