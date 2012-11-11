package fc.comparator;

import java.io.File;

import FileComparator.CompareInfo;

public class CompareItem
{
	private CompareInfo cmpInfo;
	private boolean isEmpty;
	
	
	public CompareItem( CompareInfo cmpInfo )
	{
		if ( cmpInfo == null )
		{
			isEmpty = true;
			return;
		}
		
		this.cmpInfo = cmpInfo;
	}
	
	public CompareItem( String fileName )
	{
		cmpInfo = new CompareInfo();
		cmpInfo.SetFile( new File( fileName ) );
	}
	
	public boolean IsCompared()
	{
		if ( isEmpty )
			return false;
		
		return cmpInfo.IsCompared();
	}
	
	public boolean IsUnique()
	{
		if ( isEmpty )
			return false;
		
		return cmpInfo.IsUnique();
	}
	
	public boolean IsEqualToOpposite()
	{
		if ( isEmpty )
			return false;
		
		return cmpInfo.IsEqualToOpposite();
	}
	
	public File GetFile()
	{
		return cmpInfo.GetFile();
	}
	
	public String GetFileName()
	{
		if ( isEmpty )
			return "";
		
		return cmpInfo.GetFile().getName();
	}
	
	public String GetFilePath()
	{
		if ( isEmpty )
			return "";
		
		return cmpInfo.GetFile().getAbsolutePath();
	}
	
	public void SetEmpty( boolean empty )
	{
		isEmpty = empty;
	}
	
	public boolean IsEmpty()
	{
		return isEmpty;
	}
	
	public static CompareItem GetEmptyItem()
	{
		return emptyItem;
	}
	
	private static final CompareItem emptyItem = new CompareItem( (CompareInfo)null );
}
