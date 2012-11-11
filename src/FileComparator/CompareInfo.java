package FileComparator;

import java.io.File;

public class CompareInfo implements Cloneable
{
	public boolean IsCompared()
	{
		return compared;
	}
	
	public void SetCompared( boolean compared )
	{
		this.compared = compared;
	}
	
	public boolean IsUnique()
	{
		return unique;
	}
	
	public void SetUnique( boolean unique )
	{
		this.unique = unique;
	}
	
	public boolean IsEqualToOpposite()
	{
		return equalToOpposite;
	}
	
	public void SetEqualToOpposite( boolean equalToOpposite )
	{
		this.equalToOpposite = equalToOpposite;
	}
	
	public File GetFile()
	{
		return file;
	}
	
	public void SetFile( File f )
	{
		file = f;
	}
	
    public CompareInfo clone() throws CloneNotSupportedException
    {
    	CompareInfo newC = (CompareInfo)super.clone();
    	newC.unique = unique;
    	newC.equalToOpposite = equalToOpposite;
    	newC.compared = compared;
    	if ( file != null )
    		newC.file = new File( file.getAbsolutePath() );
        return newC;
    }
	
	private boolean unique = true;
	private boolean equalToOpposite = false;
	private boolean compared = false;
	private File file;
}
