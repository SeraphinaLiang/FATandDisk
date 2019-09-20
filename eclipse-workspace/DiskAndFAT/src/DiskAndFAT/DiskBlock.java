/**
 * 
 */
package DiskAndFAT;
import java.lang.*;

/**
 * @author pc
 *
 */
public class DiskBlock {

	// 该磁盘盘块是否已经分配
	private boolean isAllocated;
	// 该磁盘盘块的盘块号
	private int blockIndex;
	// 盘块存储的数据，最大64个字符
	private StringBuffer blockData = new StringBuffer(64);
	// 盘块的字节指针（读写到第几个字节）
	private int blockPointer;

	public DiskBlock(int blockIndex,boolean isAllocated) {
		this.isAllocated = isAllocated;
		this.blockIndex = blockIndex;
		blockPointer = 0;
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

	public StringBuffer getBlockData() {
		return blockData;
	}

	public void setBlockData(StringBuffer blockData) {
		this.blockData = blockData;
	}

	public int getBlockPointer() {
		return blockPointer;
	}

	public void setBlockPointer(int blockPointer) {
		this.blockPointer = blockPointer;
	}

}
