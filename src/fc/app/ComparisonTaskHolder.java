package fc.app;

import fc.comparator.ComparePresentation;

class ComparisonTaskHolder
{
	private ComparePresentation cmpPresentation;
	
	
	public ComparisonTaskHolder( ComparePresentation cmpPresentation )
	{
		this.cmpPresentation = cmpPresentation;
	}
	
	public void Interrupt()
	{
		cmpPresentation.Interrupt();
	}
}
