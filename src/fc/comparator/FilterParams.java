package fc.comparator;

import java.util.ArrayList;

public class FilterParams
{
	private long sizeFrom, sizeTo;
	private long timeFrom, timeTo;
	private ArrayList< String >	filterNames;
	private boolean useFilter;
	
	public FilterParams()
	{
		sizeFrom = sizeTo = -1;
		timeFrom = timeTo = -1;
		filterNames = new ArrayList< String >();
	}
	
	public long GetSizeFrom()
	{
		return sizeFrom;
	}
	
	public long GetSizeTo()
	{
		return sizeTo;
	}
	
	public void SetSize( long from, long to )
	{
		sizeFrom = from;
		sizeTo = to;
	}
	
	public long GetTimeFrom()
	{
		return timeFrom;
	}
	
	public long GetTimeTo()
	{
		return timeTo;
	}
	
	public void SetTime( long from, long to )
	{
		timeFrom = from;
		timeTo = to;
	}
	
	public void AddNames( ArrayList< String > names )
	{
		filterNames.clear();
		filterNames.addAll( names );
	}
	
	public ArrayList< String > GetNames()
	{
		return filterNames;
	}
	
	public void SetUseFilter( boolean use )
	{
		useFilter = use;
	}
	
	public boolean IsUseFilter()
	{
		return useFilter;
	}
}
