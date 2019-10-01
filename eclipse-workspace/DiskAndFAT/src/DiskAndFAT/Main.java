package DiskAndFAT;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	// 全局变量
	public static Disk d = new Disk();

	public static FAT fat = new FAT();

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("DisplayDiskAndFAT.fxml"));
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws ClassNotFoundException {

		try {
			FAT.keepAllocateFreeDiskBlock(true, "");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			FAT.keepAllocateFreeDiskBlock(false, "hhhhhhhhhhhhhhhhhhhh");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		FAT.readFileDataFromDisk(2);
        
		
			try {
		      	FAT.modifiedFileContent("aaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"
		      			+ "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh"
		      			+ "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh"
		      			+ "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhha");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  //	FAT.recycleAllocatedDiskBlock(2);
		try {
			FAT.saveFile(true);
			FAT.initFile(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		launch(args);

	}
}
