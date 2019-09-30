package DiskAndFAT;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.shape.Shape;
import javafx.util.Callback;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Rectangle;

public class Controller implements Initializable {

	@FXML
	TableView<Item> theTable;
	@FXML
	TableColumn<Item, Integer> theItem;
	@FXML
	TableColumn<Item, Integer> theContent;
	@FXML
	Arc diskCircle;
	@FXML
	Rectangle db0, db1, db2;

	// 请勿直接调用
	public void paintFATTable() {
		ObservableList<Item> Items = FXCollections.observableArrayList();
		ArrayList<Item> list = FAT.getContents();
		for (int i = 0; i < list.size(); i++) {
			Items.add(list.get(i));
		}

		theItem.setCellValueFactory(new PropertyValueFactory<>("row"));
		theContent.setCellValueFactory(new PropertyValueFactory<>("content"));

		theTable.setItems(Items);
	}

	// 请勿直接调用
	public void paintDiskBlocks() {
		if (Disk.getDiskBlocks().get(2).isAllocated()) {
			db2.setFill(Color.BLANCHEDALMOND);
		}
		if (!Disk.getDiskBlocks().get(2).isAllocated()) {
			db2.setFill(Color.AQUAMARINE);
		}

	}

	// 请勿直接调用
	public void paintDiskCircle() {
		int occupied = Disk.getOccupiedBlockNumber();
		double areaFree = 360 - ((double) occupied / 128) * 360;
		diskCircle.setLength(areaFree);
	}

	// 选中文件对应FAT行变色（参数：文件的起始盘块号）------组员调用
	public void rowFATLightUp(int luminate) {
		FAT.lightUpCurrentFile(luminate);

		theTable.setRowFactory(tv -> {
			TableRow<Item> row = new TableRow<Item>();
			row.setTextFill(Color.CHARTREUSE);
			return row;
		});

	}

	// 若你的操作与文件分配表和磁盘的显示（变更）有关，务必在你的方法中调用该方法-------组员调用
	public void paintAll() {
		paintFATTable();
		paintDiskBlocks();
		paintDiskCircle();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		rowFATLightUp(3);
		paintAll();
		paintAll();
	}

}
