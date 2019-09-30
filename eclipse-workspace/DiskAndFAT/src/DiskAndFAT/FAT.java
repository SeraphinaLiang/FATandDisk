/**
 * 
 */
package DiskAndFAT;

import java.util.ArrayList;

/**
 * @author pc
 *
 */
public class FAT {

	// FAT表的内容
	public static int FATtable[] = new int[128];

	// 当前文件的起始盘块号
	public static int fileIndexStart;
	// 当前文件占用的所有盘块号
	public static int fileIndex[] = new int[120];
	// 当前文件共占用多少个盘块
	public static int fileOccupiedBlockNumber = 0;

	// tableView的FAT表中content显示
	public static ArrayList<Item> contents = new ArrayList<>();
	// 总分配函数-分配几个盘块
	public static int totalBlockNeed = 0;
	// 总分配函数-分配盘块时的起始盘块号
	public static int startIndex = 300;

	public FAT() {
		FATtable[0] = FATtable[1] = 255; // FAT已占用0和1块
		contents.add(0, new Item(0, 255));
		contents.add(1, new Item(1, 255));
		for (int i = 2; i < FATtable.length; i++) {
			FATtable[i] = 0; // 其他盘块空闲
			contents.add(i, new Item(i, 0));
		}
	}

	// 修改文件内容（参数：文件内容）------组员调用
	public static void midifiedFileContent(String newData) throws Exception {
		// 判断newData增加了还是减少了
		// 减少：删除相应长度，判断是否需要释放磁盘空间
		// 增加：分配新磁盘空间，修改Stringbuffer内容，异常：磁盘空间不足异常
	}

	// 读取文件数据（参数：该文件的起始盘块号）------组员调用
	public static StringBuffer readFileDataFromDisk(int startBlockIndex) {
		searchOccupiedBlockIndex(startBlockIndex);
		return Disk.getDataFromDisk(startBlockIndex);
	}

	// 修改FAT-回收该文件已分配的磁盘盘块（参数：该文件的起始盘块号）------组员调用
	public static void recycleAllocatedDiskBlock(int startBlockIndex) {

		searchOccupiedBlockIndex(startBlockIndex);
		for (int i = 0; i < fileOccupiedBlockNumber; i++) {
			int index = fileIndex[i];
			FATtable[index] = 0;
			Item itemp = contents.get(index);
			itemp.setContent(0);
		}
		Disk.recycleAllocatedDiskBlock();
	}

	// 分配空闲磁盘盘块并判断是否继续，并把需要放入上一磁盘(小于等于64个char)的数据传入-------组员调用（文件）
	// 新文件第一次调用该函数传入空字符串
	// 最后一次调用该函数即参数（false，string）之后，调用getFileStartIndex()获取起始盘块号
	// 待用户点击‘保存’时再判断字符长度传入数据,请勿在用户输入时（还要改来改去时）调用
	public static void keepAllocateFreeDiskBlock(boolean keepAllocate, String data) throws Exception {
		if (keepAllocate) {
			totalBlockNeed++;
			if (hasFreeDiskBlock(totalBlockNeed)) {
				Disk.readInData(data);
				return;
			} else if (!hasFreeDiskBlock(totalBlockNeed)) {
				// 盘块数量不足异常，待用户减少长度再重头调用该函数
				totalBlockNeed = 0;
				Disk.getTotalData().setLength(0);
				throw new NotEnoughBlockException("not enough block");
			}

		} else { // 不需要下一盘块
			Disk.readInData(data);
			if (totalBlockNeed == 1) {
				startIndex = allocateFreeDiskBlock();
			} else {
				startIndex = allocateFreeDiskBlock(totalBlockNeed);
			}
			Disk.inputDataToStimulator(startIndex);
			totalBlockNeed = 0;// 用完之后清零
		}
	}

	// 返回起始盘块号-----组员调用（文件）
	public static int getFileStartIndex() {
		return startIndex;
	}

	// 修改FAT-分配空闲磁盘盘块（无参数，默认为1个盘块）返回分配的磁盘盘块号------组员调用（文件夹）
	public static int allocateFreeDiskBlock() {

		int allocateBlockIndex = 300;
		for (int i = 2; i <= 127; i++) {
			if (FATtable[i] == 0) {// 未分配
				FATtable[i] = 255; // 则分配
				Item itemp = contents.get(i);
				itemp.setContent(255);
				allocateBlockIndex = i;
				break;
			}
		}
		Disk.allocateFreeDiskBlock(allocateBlockIndex);
		return allocateBlockIndex;
	}

	// 修改FAT-分配指定数量个空闲磁盘盘块，返回分配的第一个盘块号
	public static int allocateFreeDiskBlock(int blockNumber) {

		int[] allocateBlockList = new int[blockNumber];
		int blockObtained = 0;
		boolean togo = true;
		// 得到分配的磁盘盘块号序列
		while (togo) {
			for (int i = 2; i <= 127; i++) {
				if (FATtable[i] == 0) {
					FATtable[i] = 255;
					allocateBlockList[blockObtained] = i;
					blockObtained++;
				}
				if (blockObtained >= blockNumber) {
					togo = false;
					break;
				}
			}
		}
		// 再修改FAT的内容
		int last = 0;
		for (int i = 0; i < blockNumber - 1; i++) {
			int a = allocateBlockList[i];
			FATtable[a] = allocateBlockList[i + 1];
			Item itemp = contents.get(allocateBlockList[i]);
			itemp.setContent(allocateBlockList[i + 1]);
			last = allocateBlockList[i + 1];
		}
		contents.get(last).setContent(255);

		Disk.allocateFreeDiskBlock(allocateBlockList, blockNumber);
		return allocateBlockList[0];
	}

	// 查找FAT-查找当前文件的所有占用盘块
	public static void searchOccupiedBlockIndex(int fileIndexStart) {
		for (int i = 0; i < 120; i++) {
			fileIndex[i] = 0;
		}
		fileOccupiedBlockNumber = 0;
		FAT.fileIndexStart = fileIndexStart;
		fileIndex[0] = fileIndexStart;
		fileOccupiedBlockNumber++;
		int nextIndex = fileIndexStart;
		for (;;) {
			if (FATtable[nextIndex] != 0) {
				nextIndex = FATtable[nextIndex];
				if (nextIndex == 255) {
					break;
				}
				fileIndex[fileOccupiedBlockNumber] = nextIndex;
				fileOccupiedBlockNumber++;
			}
		}
	}

	// 当前文件对应FAT表的行发光（参数：起始盘块号）
	public static void lightUpCurrentFile(int fileIndexStart) {
		for (int i = 0; i < 120; i++) {
			fileIndex[i] = 0;
		}
		fileOccupiedBlockNumber = 0;
		FAT.fileIndexStart = fileIndexStart;
		fileIndex[0] = fileIndexStart;
		fileOccupiedBlockNumber++;
		int nextIndex = fileIndexStart;
		for (;;) {
			if (FATtable[nextIndex] != 0) {
				nextIndex = FATtable[nextIndex];
				if (nextIndex == 255) {
					break;
				}
				fileIndex[fileOccupiedBlockNumber] = nextIndex;
				fileOccupiedBlockNumber++;
			}
		}
		// 发光
		contents.get(fileIndexStart).setFirstOne(true);
		for (int i = 0; i < fileOccupiedBlockNumber; i++) {
			contents.get(fileIndex[i]).setLight(true);
		}
	}

	// 查找FAT-是否有空闲磁盘块（无参数，默认为1个盘块）
	public boolean hasFreeDiskBlock() {

		for (int i = 2; i <= 127; i++) {
			if (FATtable[i] == 0) {
				return true;
			}
		}
		return false;
	}

	// 查找FAT-是否有指定数量个空闲磁盘块
	public static boolean hasFreeDiskBlock(int freeBlock) {

		int actualFreeNumber = 0;
		for (int i = 2; i <= 127; i++) {
			if (FATtable[i] == 0) {
				actualFreeNumber++;
				if (actualFreeNumber >= freeBlock) {
					return true;
				}
			}
		}
		return false;
	}

	public static ArrayList<Item> getContents() {
		return contents;
	}

	public int[] getFATtable() {
		return FATtable;
	}

	public void setFATtable(int[] fATtable) {
		FATtable = fATtable;
	}

	public int getFileIndexStart() {
		return fileIndexStart;
	}

	public void setFileIndexStart(int fileIndexStart) {
		this.fileIndexStart = fileIndexStart;
	}

	public int[] getFileIndex() {
		return fileIndex;
	}

	public void setFileIndex(int[] fileIndex) {
		this.fileIndex = fileIndex;
	}

	public int getFileOccupiedBlockNumber() {
		return fileOccupiedBlockNumber;
	}

	public void setFileOccupiedBlockNumber(int fileOccupiedBlockNumber) {
		this.fileOccupiedBlockNumber = fileOccupiedBlockNumber;
	}

}
