package fc.comparator;

import java.util.ArrayList;
import java.util.Date;

import FileComparator.CompareInfo;

class CompareFilter
{
	private ArrayList< CompareInfo > compareInfoLeft, compareInfoRight;
	private CompareConfig cmpConfig;
	private FilterParams fltParams;
	
	
	CompareFilter( ArrayList< CompareInfo > compareInfoLeft, ArrayList< CompareInfo > compareInfoRight,
					CompareConfig cmpConfig, FilterParams fltParams )
	{
		this.compareInfoLeft = compareInfoLeft;
		this.compareInfoRight = compareInfoRight;
		this.cmpConfig = cmpConfig;
		this.fltParams = fltParams;
	}
	
	private void RemoveNotUniqueFiles( ArrayList< CompareInfo > list )
	{
		int size = list.size();
		for( int i = 0; i < size; i++ )
		{
			CompareInfo cmpInfo = list.get( i );
			if ( !cmpInfo.IsUnique() )
			{
				list.remove( i );
				i--;
				size--;
			}
		}
	}
	
	private void RemoveNotEqual( ArrayList< CompareInfo > list )
	{
		int size = list.size();
		for( int i = 0; i < size; i++ )
		{
			CompareInfo cmpInfo = list.get( i );
			if ( !cmpInfo.IsEqualToOpposite() )
			{
				list.remove( i );
				i--;
				size--;
			}
		}
	}
	
	private void RemoveNotCompared( ArrayList< CompareInfo > list )
	{
		int size = list.size();
		for( int i = 0; i < size; i++ )
		{
			CompareInfo cmpInfo = list.get( i );
			if ( !cmpInfo.IsCompared() )
			{
				list.remove( i );
				i--;
				size--;
			}
		}
	}
	
	private void RemoveHidden( ArrayList< CompareInfo > list )
	{
		int size = list.size();
		for( int i = 0; i < size; i++ )
		{
			CompareInfo cmpInfo = list.get( i );
			if ( cmpInfo.GetFile().isHidden() )
			{
				list.remove( i );
				i--;
				size--;
			}
		}
	}
	
	private boolean FilterTest( CompareInfo cmpInfo )
	{
		if ( !cmpInfo.GetFile().isDirectory() )
		{
			long size = cmpInfo.GetFile().length();
			
			long sizeFrom = fltParams.GetSizeFrom();
			if ( sizeFrom != -1 )
			{
				if ( size < sizeFrom )
					return true;
			}
			
			long sizeTo = fltParams.GetSizeTo();
			if ( sizeTo != -1 )
			{
				if ( size > sizeTo )
					return true;
			}
		}
		
		long time = cmpInfo.GetFile().lastModified();
		Date modified = new Date( time );
		
		if ( fltParams.GetTimeFrom() != -1 )
		{
			if ( modified.before( new Date(fltParams.GetTimeFrom()) ) )
				return true;
		}
		
		if ( fltParams.GetTimeTo() != -1 )
		{
			if ( modified.after( new Date(fltParams.GetTimeTo()) ) )
				return true;
		}
		
		for( String filter : fltParams.GetNames() )
		{
			String regex = filter.replace("?", ".?").replace("*", ".*?");
			if ( cmpInfo.GetFile().getName().matches( regex ) )
				return true;
		}
		
		return false;
	}
	
	private void Filter( ArrayList< CompareInfo > list )
	{
		int size = list.size();
		for( int i = 0; i < size; i++ )
		{
			CompareInfo cmpInfo = list.get( i );
			if ( FilterTest( cmpInfo ) )
			{
				list.remove( i );
				i--;
				size--;
			}
		}
	}
	
	public void RemoveHidenFiles()
	{
		if ( cmpConfig.GetShowFilesNotExistsOnly() )
		{
			RemoveNotUniqueFiles( compareInfoLeft );
			RemoveNotUniqueFiles( compareInfoRight );
		}
		
		if ( cmpConfig.GetShowOnlyEq() )
		{
			RemoveNotEqual( compareInfoLeft );
			RemoveNotEqual( compareInfoRight );
		}
		
		if ( cmpConfig.GetShowOnlyCompared() )
		{
			RemoveNotCompared( compareInfoLeft );
			RemoveNotCompared( compareInfoRight );
		}
		
		if ( !cmpConfig.GetShowHidden() )
		{
			RemoveHidden( compareInfoLeft );
			RemoveHidden( compareInfoRight );
		}
		
		if ( fltParams.IsUseFilter() )
		{
			Filter( compareInfoLeft );
			Filter( compareInfoRight );
		}
	}
}
