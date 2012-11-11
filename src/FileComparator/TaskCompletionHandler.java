package FileComparator;

public class TaskCompletionHandler
{
	public void OnComplete()
	{
		taskCompleted = true;
		try
		{
			synchronized( waitObject )
			{
				waitObject.wait();
			}
		}
		catch( InterruptedException e )
		{
			e.printStackTrace();
		}
	}
	
	public boolean IsTaskCompleted()
	{
		return taskCompleted;
	}
	
	public void CompletionAccepted()
	{
		synchronized( waitObject )
		{
			waitObject.notify();
		}
	}
	
	private boolean taskCompleted = false;
	private Object waitObject = new Object();
}
