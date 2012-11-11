package fc.comparator;

public class CompareConfig
{
	private boolean showOnlyEq;
	private boolean showFilesNotExistsOnly;
	private boolean showOnlyCompared;
	private boolean showHidden;
	
	public void SetShowOnlyEq( boolean show )
	{
		showOnlyEq = show;
	}
	
	public boolean GetShowOnlyEq()
	{
		return showOnlyEq;
	}
	
	public void SetShowFilesNotExistsOnly( boolean show )
	{
		showFilesNotExistsOnly = show;
	}
	
	public boolean GetShowFilesNotExistsOnly()
	{
		return showFilesNotExistsOnly;
	}
	
	public void SetShowOnlyCompared( boolean show )
	{
		showOnlyCompared = show;
	}
	
	public boolean GetShowOnlyCompared()
	{
		return showOnlyCompared;
	}
	
	public void SetShowHidden( boolean show )
	{
		showHidden = show;
	}
	
	public boolean GetShowHidden()
	{
		return showHidden;
	}
}
