package DiskAndFAT;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	//全局变量
	public static Disk d = new Disk();
   
	public static FAT fat = new FAT();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("DisplayDiskAndFAT.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		if(fat.hasFreeDiskBlock()) {
			int i=fat.allocateFreeDiskBlock();
			
			fat.searchOccupiedBlockIndex(i);
			fat.recycleAllocatedDiskBlock(i);
		}
		if(fat.hasFreeDiskBlock(10)) {
			int i=fat.allocateFreeDiskBlock(10);
			
			fat.searchOccupiedBlockIndex(10);
		}
		if(fat.hasFreeDiskBlock(20)) {
			int i=fat.allocateFreeDiskBlock(20);
			
			fat.searchOccupiedBlockIndex(20);
			fat.recycleAllocatedDiskBlock(i);
			System.out.println(Disk.getOccupiedBlockNumber());
		}
		
		launch(args);
		
	}
}
