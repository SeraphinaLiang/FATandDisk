/**
 * 
 */
package DiskAndFAT;
import java.lang.*;

/**
 * @author pc
 *
 */
public class DiskBlock implements java.io.Serializable{

	// 该磁盘盘块是否已经分配
	private boolean isAllocated;
	// 该磁盘盘块的盘块号
	private int blockIndex;

	public DiskBlock(int blockIndex,boolean isAllocated) {
		this.isAllocated = isAllocated;
		this.blockIndex = blockIndex;
		
	}

	public boolean isAllocated() {
		return isAllocated;
	}

	public void setAllocated(boolean isAllocated) {
		this.isAllocated = isAllocated;
	}

	public int getBlockIndex() {
		return blockIndex;
	}

}
