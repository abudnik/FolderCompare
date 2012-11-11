package fc.comparator;

import android.os.Handler;
import android.os.Message;
import FileComparator.ProgressHandler;

public class CompareProgressHandler extends ProgressHandler
{
	private Handler mainHandler;
	
	public CompareProgressHandler( Handler mainHandler )
	{
		this.mainHandler = mainHandler;
	}

	@Override
	protected void OnProgress()
	{
		if ( mainHandler != null )
		{
			Message msg = mainHandler.obtainMessage( progress, "OnProgress" );
			mainHandler.sendMessage( msg );
		}
	}
}
