/**为了TableView而设计的类
 * 显示FAT表的content内容，并且实时更新
 */
package DiskAndFAT;

public class Item {

	//对应FAT行的数值
	public int content;
	// 当前文件选中，对应行发光
	public boolean light = false;
	//是否是占用的第一块盘块
    public boolean firstOne=false;
	
	public Item(int content) {
		this.content = content;
	}

	public boolean isFirstOne() {
		return firstOne;
	}

	public void setFirstOne(boolean firstOne) {
		this.firstOne = firstOne;
	}


	public boolean isLight() {
		return light;
	}

	public void setLight(boolean light) {
		this.light = light;
	}

	public int getContent() {
		return content;
	}

	public void setContent(int content) {
		this.content = content;
	}
}
