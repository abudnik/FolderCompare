package FileComparator;

public abstract class ProgressHandler
{
	protected int dirsCompared;
	protected int dirsTotal;
	protected int progress;
	
	public void SetDirsTotal( int dirsTotal )
	{
		this.dirsTotal = dirsTotal;
	}
	
	public void SetDirsCompared( int dirsCompared )
	{
		this.dirsCompared = dirsCompared;
		progress = (int)( ( (float)dirsCompared / (float)dirsTotal ) * 100.0f );
		OnProgress();
	}
	
	public int GetProgress()
	{
		return progress;
	}
	
	protected abstract void OnProgress();
}
