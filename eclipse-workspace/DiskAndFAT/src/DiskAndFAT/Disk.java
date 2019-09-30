
package DiskAndFAT;

import java.util.*;

public class Disk {

	// 全部磁盘盘块数量-128个盘块
	private static final int TOTAL_BLOCK_NUMBER = 128;
	// 文件已经占用的盘块数量
	public static int occupiedBlockNumber = 0;
	// 128个磁盘盘块
	private static ArrayList<DiskBlock> diskBlocks = new ArrayList<>(TOTAL_BLOCK_NUMBER);
	
	// 模拟磁盘-对象流输入输出<起始盘块号，相应文件内容>
	private static HashMap<Integer,StringBuffer> diskStimulator = new HashMap<>();

	// 当前文件存储的全部数据
	private static StringBuffer totalData = new StringBuffer();
	// 当前文件的长度
	private static int dataLength;

	public Disk() {
		initalDiskBlock();
	}

	// 初始化磁盘盘块
	public void initalDiskBlock() {
		// FAT文件分配表已占用盘块0和盘块1
		diskBlocks.add(0, new DiskBlock(0, true));
		diskBlocks.add(1, new DiskBlock(1, true));
		occupiedBlockNumber++;
		occupiedBlockNumber++;
		// 盘块2-127块初始化为空盘块
		for (int n = 2; n <= 127; n++) {
			diskBlocks.add(n, new DiskBlock(n, false));
		}
	}

	// 分配空闲磁盘盘块
	public static void allocateFreeDiskBlock(int index) {
		occupiedBlockNumber++;
		DiskBlock dbtemp = diskBlocks.get(index);
		dbtemp.setAllocated(true);
	}

	// 分配指定数量个空闲磁盘盘块
	public static void allocateFreeDiskBlock(int list[], int count) {

		occupiedBlockNumber += count;
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
		occupiedBlockNumber -= count;
		for (int i = 0; i < count; i++) {
			int temp = list[i];
			DiskBlock dbtemp = diskBlocks.get(temp);
			dbtemp.setAllocated(false);
		}

	}

	// 获取用户输入的数据
	public static void readInData(String data) {
		totalData.append(data);
	}

	// 将当前文件的数据写入模拟磁盘（文件流输入）
	public static void inputDataToStimulator(int startIndex) {
		diskStimulator.put(startIndex,totalData);
		totalData.setLength(0);// 清除当前文件数据
	}

	// 将模拟磁盘中的数据读出（参数：该文件的起始盘块号）
	public static StringBuffer getDataFromDisk(int startBlockIndex) {
		return totalData;
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

	public static StringBuffer getTotalData() {
		return totalData;
	}

	public static void setTotalData(StringBuffer totalData) {
		Disk.totalData = totalData;
	}

}
