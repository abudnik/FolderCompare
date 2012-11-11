package FileComparator;

public class CompareStatistics
{
	private int uniqueLeftCnt, uniqueRightCnt;
	private int changedFilesCnt, unchangedFilesCnt;
	private int totalCompared;
	
	public int getUniqueLeftCnt() {
		return uniqueLeftCnt;
	}
	public void setUniqueLeftCnt(int uniqueLeftCnt) {
		this.uniqueLeftCnt = uniqueLeftCnt;
	}
	public int getUniqueRightCnt() {
		return uniqueRightCnt;
	}
	public void setUniqueRightCnt(int uniqueRightCnt) {
		this.uniqueRightCnt = uniqueRightCnt;
	}
	public int getChangedFilesCnt() {
		return changedFilesCnt;
	}
	public void setChangedFilesCnt(int changedFilesCnt) {
		this.changedFilesCnt = changedFilesCnt;
	}
	public int getUnchangedFilesCnt() {
		return unchangedFilesCnt;
	}
	public void setUnchangedFilesCnt(int unchangedFilesCnt) {
		this.unchangedFilesCnt = unchangedFilesCnt;
	}
	public int getTotalCompared() {
		return totalCompared;
	}
	public void setTotalCompared(int totalCompared) {
		this.totalCompared = totalCompared;
	}
	
	public void Reset()
	{
		uniqueLeftCnt = uniqueRightCnt = 0;
		changedFilesCnt = unchangedFilesCnt = 0;
		totalCompared = 0;
	}
}
