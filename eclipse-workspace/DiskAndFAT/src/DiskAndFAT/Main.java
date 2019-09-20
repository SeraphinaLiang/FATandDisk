package DiskAndFAT;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//launch(args);
		Disk d = new Disk();
		d.initalDiskBlock();
		FAT fat = new FAT();

		
		if(fat.hasFreeDiskBlock()) {
			int i=fat.allocateFreeDiskBlock();
			System.out.println(i);
			fat.searchOccupiedBlockIndex(i);
		}
		if(fat.hasFreeDiskBlock(10)) {
			int i=fat.allocateFreeDiskBlock(10);
			System.out.println(i);
			fat.searchOccupiedBlockIndex(10);
		}
		if(fat.hasFreeDiskBlock(20)) {
			int i=fat.allocateFreeDiskBlock(20);
			System.out.println(i);
			fat.searchOccupiedBlockIndex(20);
		}
		
	}
}
