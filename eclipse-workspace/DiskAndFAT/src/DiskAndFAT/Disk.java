/**
 * 
 */
package DiskAndFAT;

import java.util.*;

/**
 * @author pc
 *
 */
public class Disk {

	// 全部磁盘盘块数量-128个盘块
	private static final int TOTAL_BLOCK_NUMBER = 128;
	// 除FAT占用的盘块外，其他文件已经占用的盘块数量
	public static int occupiedBlockNumber = 0;
	// 128个磁盘盘块
	private static ArrayList<DiskBlock> diskBlocks = new ArrayList<>(TOTAL_BLOCK_NUMBER);

	public Disk() {

	}

	// 初始化磁盘盘块
	public void initalDiskBlock() {
		// FAT文件分配表已占用盘块0和盘块1
		diskBlocks.add(0, new DiskBlock(0, true));
		diskBlocks.add(1, new DiskBlock(1, true));
		// 盘块2-127块初始化为空盘块
		for (int n = 2; n <= 127; n++) {
			diskBlocks.add(n, new DiskBlock(n, false));
		}
	}

	// 分配空闲磁盘盘块
	public static void allocateFreeDiskBlock(int index) {
		DiskBlock dbtemp = diskBlocks.get(index);
		dbtemp.setAllocated(true);
	}

	// 分配指定数量个空闲磁盘盘块
	public static void allocateFreeDiskBlock(int list[], int count) {

		for (int i = 0; i < count; i++) {
			int t = list[i];
			DiskBlock dbtemp = diskBlocks.get(t);
			dbtemp.setAllocated(true);
		}
	}

	// 根据FAT回收该文件已分配的磁盘盘块
	public static void recycleAllocatedDiskBlock() {

		int list[] = FAT.fileIndex;
		int count = FAT.fileOccupiedBlockNumber;
		for (int i = 0; i < count; i++) {
			int temp = list[i];
			DiskBlock dbtemp = diskBlocks.get(temp);
			dbtemp.setAllocated(false);
			dbtemp.setBlockPointer(0);
			dbtemp.getBlockData().setLength(0);
		}

	}

	// 将Buffer中的数据写入磁盘
	public void readInDataFromBuffer() {

	}

	// 将磁盘中的数据读出到Buffer中（参数：该文件的起始盘块号）
	public void getDataFromDisk(int startBlockIndex) {

	}

	// 将磁盘中的数据读出到Buffer中（参数：该文件的起始盘块号，磁盘的第几个字节）
	public void getDataFromDisk(int startBlockIndex, int byteIndex) {

	}

	public static int getOccupiedBlockNumber() {
		return occupiedBlockNumber;
	}

	public static void setOccupiedBlockNumber(int occupiedBlockNumber) {
		Disk.occupiedBlockNumber = occupiedBlockNumber;
	}

	public static ArrayList<DiskBlock> getDiskBlocks() {
		return diskBlocks;
	}

}
