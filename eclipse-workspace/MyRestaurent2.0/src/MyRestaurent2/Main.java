package MyRestaurent2;

import java.util.*;
import java.lang.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.*;
import javafx.stage.*;
import java.util.HashMap;
import javafx.event.EventType;
import javafx.scene.image.*;
import javafx.scene.paint.Color;

/************************
 * 
 * 201615100219 18计算机2班 梁茵琪（个人作品）
 * 
 ************************/
public class Main extends Application {
	// 全局变量
	// manager account & keyword
	public static final String manager_account = "IAMMANAGER";
	public static final String manager_key = "20198888";
	// tea
	public static ArrayList<Tea> tealist = new ArrayList<Tea>();
	// food
	public static ArrayList<Dish> dishes = new ArrayList<Dish>();
	public static ArrayList<Dianxin> dianxins = new ArrayList<Dianxin>();
	public static ArrayList<Dessert> desserts = new ArrayList<Dessert>();
	public static ArrayList<Drink> drinks = new ArrayList<Drink>();
	// food number
	public static ArrayList<TextField> tfdish = new ArrayList<TextField>();
	public static ArrayList<TextField> tfdianxin = new ArrayList<TextField>();
	public static ArrayList<TextField> tfdessert = new ArrayList<TextField>();
	public static ArrayList<TextField> tfdrink = new ArrayList<TextField>();
	// comsumer
	public static HashMap<String, VIP> viplist = new HashMap<String, VIP>();
	public static ArrayList<NOTVIP> nonviplist = new ArrayList<NOTVIP>();
	// 总营业额
	public static double totalRevenue = 0;
	// scene的大小
	final static double width = 430;
	final static double height = 600;
	// 切换页面
	public static StageController stagecontrol;
	// 当前用户属性
	public static currentCustomer CurrentCustomer = new currentCustomer();
	// 菜单修改 STAGE 11
	public static ArrayList<CheckBox> cb11 = new ArrayList<CheckBox>();
	public static ArrayList<TextField> tf11 = new ArrayList<TextField>();
	public static ArrayList<Food> allfood = new ArrayList<Food>();

	@Override
	public void start(Stage primaryStage) {

		Stage s1 = new Stage(); // start page
		s1.setResizable(false);
		Stage s2 = new Stage(); // create account
		s2.setResizable(false);
		Stage s3 = new Stage(); // tea_guest number_if perserve
		s3.setResizable(false);
		Stage s4 = new Stage(); // select food page dishes and dianxin
		s4.setResizable(false);
		Stage s5 = new Stage(); // manager login
		s5.setResizable(false);
		Stage s6 = new Stage(); // management page
		s6.setResizable(false);
		Stage s7 = new Stage(); // management page choose manage or add new food to the menu
		s7.setResizable(false);
		Stage s8 = new Stage(); // total Revenue page
		s8.setResizable(false);
		Stage s9 = new Stage(); // vip management page
		s9.setResizable(false);
		Stage s10 = new Stage(); // add food page included name,price and type
		s10.setResizable(false);
		Stage s11 = new Stage(); // set new price or ia available for the menu
		s11.setResizable(false);
		Stage s12 = new Stage(); // select food page dessert and drink
		s12.setResizable(false);
		Stage s13 = new Stage();// payment page
		s13.setResizable(false);
		Stage s14 = new Stage();// successfully pay page
		s14.setResizable(false);
		Stage s15 = new Stage(); // check out food order and choose whether want to change order
		s15.setResizable(false);

		// s8 total Revenue page START
		StackPane pane8 = new StackPane();
		Text t8 = new Text();
		pane8.setStyle("-fx-border-color:null;-fx-background-color:gold;");
		pane8.setAlignment(Pos.CENTER);
		pane8.getChildren().add(t8);
		Scene scene8 = new Scene(pane8, 100, 70);
		s8.setScene(scene8);
		s8.setTitle("Total Revenue");
		// s8 total revenue page END

		// s9 vip management page SRART
		Button bt9return = new Button("确认并返回");
		VBox vb9 = new VBox(5);
		vb9.setAlignment(Pos.CENTER);
		vb9.setPadding(new Insets(5));
		vb9.setStyle("-fx-border-color:grey;-fx-background-color:gold;");
		ScrollPane sp9 = new ScrollPane(vb9);
		sp9.setStyle("-fx-border-color:grey;-fx-background-color:gold;");
		sp9.setMinWidth(200);

		Set<String> set = viplist.keySet(); // key的集合

		TextArea ta91 = new TextArea(set.toString());
		ta91.setWrapText(true);
		ta91.setEditable(false);

		TextArea ta92 = new TextArea();
		ta92.setWrapText(true);
		ta92.setEditable(true);

		vb9.getChildren().addAll(ta91, ta92);
		GridPane pane9 = new GridPane();// 主面板
		pane9.add(new Text("会员名单\n若需删除此会员，请在下方输入会员名称（账号）。\n" + "以回车键隔开,即每行输一个。"), 0, 0);
		pane9.add(sp9, 0, 1);
		pane9.add(bt9return, 0, 2);
		pane9.setAlignment(Pos.CENTER);
		pane9.setPadding(new Insets(10));
		pane9.setHgap(10);
		pane9.setVgap(10);
		pane9.setStyle("-fx-border-color:null;-fx-background-color:gold;");
		Scene scene9 = new Scene(pane9, width, height);
		s9.setTitle("VIP management");
		s9.setScene(scene9);

		bt9return.setOnMouseClicked(e -> {
			String[] tokens = ta92.getText().split("\n"); // 正则表达式

			for (int j = 0; j < tokens.length; j++) {
				if (viplist.containsKey(tokens[j])) {
					viplist.remove(tokens[j]);
				}
			}

			stagecontrol.setStage(s6, s9);

		});

		// s9 vip management page END

		// s11 set new price or is available for the menu popup START
		Button bt11return = new Button("确认并返回");
		VBox vb11 = new VBox(5);
		vb11.setAlignment(Pos.CENTER_LEFT);
		vb11.setPadding(new Insets(5));
		vb11.setStyle("-fx-border-color:grey;-fx-background-color:gold;");
		ScrollPane sp11 = new ScrollPane(vb11);
		sp11.setStyle("-fx-border-color:grey;-fx-background-color:gold;");
		sp11.setMinWidth(200);

		AdjustMenuInitial(vb11); // paint

		GridPane pane11 = new GridPane();// 主面板
		pane11.add(new Text("右侧打勾即删除此菜品\n价格调整：在下方输入新价格\n" + "每次只可删除一样菜/修改一样菜。"), 0, 0);
		pane11.add(sp11, 0, 1);
		pane11.add(bt11return, 0, 2);
		pane11.setAlignment(Pos.CENTER);
		pane11.setPadding(new Insets(10));
		pane11.setHgap(10);
		pane11.setVgap(10);
		pane11.setStyle("-fx-border-color:null;-fx-background-color:gold;");
		Scene scene11 = new Scene(pane11, width, height);
		s11.setTitle("Menu Adjust");
		s11.setScene(scene11);
		bt11return.setOnMouseClicked(e -> {
			AdjustMenuDeuxFois(vb11);
			stagecontrol.setStage(s7, s11);
		});
		// s11 set new price or is available for the menu popup END

		// primary stage start
		Image image = new Image("file:///C:/Users/pc/eclipse-workspace/MyRestaurent2.0/src/MyRestaurent2/picture.png");// build-classes-myrestaurant2
		Button enter = new Button("进入餐厅");
		enter.setOnMouseClicked(e -> {
			stagecontrol.setStage(s1, primaryStage);
		});
		VBox vbox = new VBox(5);
		vbox.setPadding(new Insets(5));
		vbox.getChildren().addAll(new ImageView(image), enter);
		vbox.setAlignment(Pos.TOP_RIGHT);
		vbox.setStyle("-fx-background-color:grey;");
		Scene scene = new Scene(vbox);
		primaryStage.setTitle("中式餐厅-翊坤宫御膳");
		primaryStage.setScene(scene);
		primaryStage.show();
		// primary stage end

		// s15 check out food order and choose whether want to change order START
		Button btreorder = new Button("重新选择");
		btreorder.setOnMouseClicked(e -> {
			orderStartOver();
			stagecontrol.setStage(s4, s15);
		});
		Text t_sum = new Text(); // s13
		Button btpay = new Button("结账");
		btpay.setOnMouseClicked(epay -> {
			t_sum.setText(String.valueOf(getTotalPrice()));
			stagecontrol.setStage(s13, s15);
		});
		TextArea ta15 = new TextArea(orderPrintOut());
		ta15.setEditable(false);
		ta15.setWrapText(true);
		ta15.setPrefColumnCount(20);
		ta15.setPrefRowCount(20);

		GridPane pane15 = new GridPane();// 主面板
		pane15.setAlignment(Pos.CENTER);
		pane15.setPadding(new Insets(10));
		pane15.setHgap(10);
		pane15.setVgap(10);
		pane15.setStyle("-fx-border-color:null;-fx-background-color:gold;");
		pane15.add(new Text("您已选择如下菜品"), 0, 0);
		pane15.add(ta15, 0, 1);
		pane15.add(btreorder, 0, 2);
		pane15.add(btpay, 1, 2);
		Scene scene15 = new Scene(pane15, width, height);
		s15.setTitle("Check Your Food Order");
		s15.setScene(scene15);
		// s15 check out food order and choose whether want to change order END

		// s2 start
		///////// create account pop up//////////////
		VBox vb_popup = new VBox();
		Button bt_popup = new Button("确定");
		vb_popup.getChildren().addAll(new Text("自动返回登陆界面。"), bt_popup);
		vb_popup.setAlignment(Pos.CENTER);
		Stage stage_popup = new Stage();
		Scene scene_popup = new Scene(vb_popup, 350, 50);
		stage_popup.setScene(scene_popup);
		stage_popup.setTitle("Successfully Create Account!");
		///////// create account pop up//////////////
		GridPane p2 = new GridPane();
		p2.setAlignment(Pos.CENTER); // 对齐方式
		p2.setPadding(new Insets(5));
		p2.setHgap(5.5);
		p2.setVgap(5.5);
		p2.setStyle("-fx-border-color:null;-fx-background-color:gold;");
		p2.add(new Label("Account: "), 0, 1);
		TextField tfaccount2 = new TextField();
		tfaccount2.setPromptText("account");
		p2.add(tfaccount2, 1, 1);
		p2.add(new Label("Keyword:"), 0, 2);
		TextField tfkeyword2 = new TextField();
		tfkeyword2.setPromptText("password");
		p2.add(tfkeyword2, 1, 2);
		Button btloginAccount2 = new Button("Create Account");
		btloginAccount2.setOnMouseClicked(e -> {
			VIP v = new VIP(tfaccount2.getText(), tfkeyword2.getText()); // 注册并把新对象加入到HashMap
			viplist.put(tfaccount2.getText(), v);
			stage_popup.show();
			bt_popup.setOnMouseClicked(e1 -> {
				stagecontrol.setStage(s1, s2);
				stage_popup.close();
			});
		});
		p2.add(btloginAccount2, 1, 3);
		p2.add(new Label("只需300元即可办理会员卡，\n费用在结账时清算。"), 0, 0);
		Button btreturn2 = new Button("返回主菜单");
		btreturn2.setOnMouseClicked(e -> {
			stagecontrol.setStage(s1, s2);
		});
		p2.add(btreturn2, 0, 4);
		Scene scene2 = new Scene(p2, width, height);
		s2.setTitle("Create Account");
		s2.setScene(scene2);
		// s2 end

		// s1 start
		/////////// login fail page////////////////////
		VBox vb_lf = new VBox();
		Button bt_lf = new Button("重新输入");
		vb_lf.getChildren().addAll(new Text("账号或密码输入错误"), bt_lf);
		vb_lf.setAlignment(Pos.CENTER);
		Stage stage_lf = new Stage();
		Scene scene_lf = new Scene(vb_lf, 350, 50);
		stage_lf.setScene(scene_lf);
		stage_lf.setTitle("Wrong Input!!!");
		//////////// login fail page////////////////////
		GridPane p1 = new GridPane();
		p1.add(new Label("I AM VIP"), 0, 0);
		p1.setAlignment(Pos.CENTER); // 对齐方式
		p1.setPadding(new Insets(5));
		p1.setHgap(5.5);
		p1.setVgap(5.5);
		p1.add(new Label("Account: "), 0, 1);
		TextField tfaccount = new TextField();
		tfaccount.setPromptText("account");
		p1.add(tfaccount, 1, 1);
		p1.add(new Label("Keyword:"), 0, 2);
		PasswordField tfkeyword = new PasswordField();
		tfkeyword.setPromptText("password");
		p1.add(tfkeyword, 1, 2);
		Button btloginAccount = new Button("Login");
		p1.add(btloginAccount, 1, 3);
		btloginAccount.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				String ac = tfaccount.getText();
				String key = tfkeyword.getText();
				if (viplogin(ac, key)) {
					currentCustomer.setVip_current(viplist.get(ac)); // 登陆即设定为当前vip用户
					currentCustomer.setIsCurrentvip(true);
					stagecontrol.setStage(s3, s1);
				} else {
					stage_lf.show();
					bt_lf.setOnMouseClicked(elf -> {
						stage_lf.close();
					});
				}
			}
		});
		p1.add(new Label("I AM NOT VIP"), 0, 4);
		Button btcreataccount = new Button("Create Account");
		p1.add(btcreataccount, 0, 5);
		btcreataccount.setOnMouseClicked(e -> {
			stagecontrol.setStage(s2, s1);
		});
		Button btordernow = new Button("Order now");
		p1.add(btordernow, 2, 5);
		btordernow.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				NOTVIP temp = new NOTVIP();
				currentCustomer.setNotvip_current(temp); // 当前非vip用户
				nonviplist.add(temp);
				currentCustomer.setIsCurrentvip(false);
				stagecontrol.setStage(s3, s1);
			}
		});

		p1.add(new Label("直接点餐，\n不注册会员。"), 2, 6);
		p1.add(new Label("VIP benefits: \n    消费满500可享8折优惠。\n    免费停车三小时。"), 0, 6);
		Button btmanager = new Button("Manager");
		p1.add(btmanager, 2, 8);
		btmanager.setOnMouseClicked(e -> {
			stagecontrol.setStage(s5, s1);
		});
		GridPane.setHalignment(btordernow, HPos.RIGHT); // 设置水平右对齐
		p1.setStyle("-fx-border-color:null;-fx-background-color:gold;");
		Scene scene1 = new Scene(p1, width, height);
		s1.setTitle("恭候圣驾");
		s1.setScene(scene1);
		// s1 end

		// s12 select food page dessert and drink START
		Button btnext12 = new Button("NEXT");
		btnext12.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				getDessertOrder();
				getDrinkOrder();
				ta15.setText(orderPrintOut());
				stagecontrol.setStage(s15, s12);
			}
		});
		VBox vb121 = new VBox(5); // dessert
		vb121.setAlignment(Pos.CENTER);
		vb121.setPadding(new Insets(5));
		vb121.setStyle("-fx-border-color:grey;-fx-background-color:gold;");
		ScrollPane sp121 = new ScrollPane(vb121);
		sp121.setStyle("-fx-border-color:grey;-fx-background-color:gold;");
		sp121.setMinWidth(145);
		for (int i = 0; i < desserts.size(); i++) {
			Text t = new Text(desserts.get(i).getFoodName() + " " + desserts.get(i).getFoodPrice());
			vb121.getChildren().addAll(t, tfdessert.get(i));
		}
		VBox vb122 = new VBox(5); // drink
		vb122.setAlignment(Pos.CENTER);
		vb122.setPadding(new Insets(5));
		vb122.setStyle("-fx-border-color:grey;-fx-background-color:gold;");
		ScrollPane sp122 = new ScrollPane(vb122);
		sp122.setStyle("-fx-border-color:grey;-fx-background-color:gold;");

		for (int i = 0; i < drinks.size(); i++) {
			Text t = new Text(drinks.get(i).getFoodName() + " " + drinks.get(i).getFoodPrice());
			vb122.getChildren().addAll(t, tfdrink.get(i));
		}

		GridPane pane12 = new GridPane();// 主面板
		pane12.setAlignment(Pos.CENTER);
		pane12.setPadding(new Insets(10));
		pane12.setHgap(10);
		pane12.setVgap(10);
		pane12.setStyle("-fx-border-color:null;-fx-background-color:gold;");
		pane12.add(new Text("甜点（元）"), 0, 0);
		pane12.add(sp121, 0, 1);
		pane12.add(new Text("饮料/酒水（元）"), 0, 2);
		pane12.add(sp122, 0, 3);
		pane12.add(btnext12, 2, 4);
		Scene scene12 = new Scene(pane12, width, height);
		s12.setTitle("Food Selection");
		s12.setScene(scene12);
		// s12 select food page dessert and drink END

		// s4 select food page dishes and dianxin START
		Button btnext4 = new Button("NEXT");
		btnext4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				getDishesOrder();
				getDianxinOrder();
				stagecontrol.setStage(s12, s4);
			}
		});

		VBox vb41 = new VBox(5); // dishes
		vb41.setAlignment(Pos.CENTER);
		vb41.setPadding(new Insets(5));
		vb41.setStyle("-fx-border-color:grey;-fx-background-color:gold;");
		ScrollPane sp41 = new ScrollPane(vb41);
		sp41.setStyle("-fx-border-color:grey;-fx-background-color:gold;");
		vb41.setMinWidth(180);

		for (int i = 0; i < dishes.size(); i++) {
			Text t = new Text(dishes.get(i).getFoodName() + " " + dishes.get(i).getFoodPrice());
			vb41.getChildren().addAll(t, tfdish.get(i));
		}

		VBox vb42 = new VBox(5); // dianxin
		vb42.setAlignment(Pos.CENTER);
		vb42.setPadding(new Insets(5));
		vb42.setStyle("-fx-border-color:grey;-fx-background-color:gold;");
		ScrollPane sp42 = new ScrollPane(vb42);
		sp42.setStyle("-fx-border-color:grey;-fx-background-color:gold;");

		for (int i = 0; i < dianxins.size(); i++) {
			Text t = new Text(dianxins.get(i).getFoodName() + " " + dianxins.get(i).getFoodPrice());
			vb42.getChildren().addAll(t, tfdianxin.get(i));
		}

		GridPane pane4 = new GridPane();// 主面板
		pane4.setAlignment(Pos.CENTER);
		pane4.setPadding(new Insets(10));
		pane4.setHgap(10);
		pane4.setVgap(10);
		pane4.setStyle("-fx-border-color:null;-fx-background-color:gold;");
		pane4.add(new Text("若您需要点餐，\n请在下方输入所需数量。\n\n菜品（元）"), 0, 0);
		pane4.add(sp41, 0, 1);
		pane4.add(new Text("点心（元）"), 0, 2);
		pane4.add(sp42, 0, 3);
		pane4.add(btnext4, 2, 4);
		Scene scene4 = new Scene(pane4, width, height);
		s4.setTitle("Food Selection");
		s4.setScene(scene4);
		// s4 select food page dishes and dianxin END

		// s10 add food page included name,price and type START
		/////////////////////// successfully add pop up////////////////////////////
		StackPane s10_popup = new StackPane();
		s10_popup.setStyle("-fx-border-color:null;-fx-background-color:pink;");
		s10_popup.getChildren().add(new Text("添加成功"));
		s10_popup.setAlignment(Pos.CENTER);
		Stage stage10_popup = new Stage();
		Scene scene10_popup = new Scene(s10_popup, 70, 50);
		stage10_popup.setScene(scene10_popup);
		stage10_popup.setTitle("Add Food");
		/////////////////////// successfully add pop up////////////////////////////
		VBox p10 = new VBox(15);
		p10.setAlignment(Pos.CENTER_LEFT);
		p10.setPadding(new Insets(10));
		p10.setStyle("-fx-border-color:null;-fx-background-color:gold;");
		TextField tfname = new TextField();
		TextField tfprice = new TextField();
		tfname.setPrefColumnCount(5);
		tfprice.setPrefColumnCount(5);
		p10.getChildren().addAll(new Text("Name :"), tfname, new Text("Price :"), tfprice, new Text("Type :"));

		HBox p10_1 = new HBox(7);
		p10_1.setAlignment(Pos.CENTER);
		p10_1.setStyle("-fx-border-color:null;-fx-background-color:gold;");
		ToggleGroup tg10 = new ToggleGroup();
		RadioButton rb10a = new RadioButton("菜类");
		RadioButton rb10b = new RadioButton("点心");
		RadioButton rb10c = new RadioButton("甜品");
		RadioButton rb10d = new RadioButton("酒水/饮料");
		rb10a.setContentDisplay(ContentDisplay.RIGHT);
		rb10b.setContentDisplay(ContentDisplay.RIGHT);
		rb10c.setContentDisplay(ContentDisplay.RIGHT);
		rb10d.setContentDisplay(ContentDisplay.RIGHT);
		rb10a.setToggleGroup(tg10);
		rb10b.setToggleGroup(tg10);
		rb10c.setToggleGroup(tg10);
		rb10d.setToggleGroup(tg10);
		p10_1.getChildren().addAll(rb10a, rb10b, rb10c, rb10d);
		p10.getChildren().add(p10_1);
		Button bt10 = new Button("确认添加");
		p10.getChildren().add(bt10);

		bt10.setOnMouseClicked(e10 -> {
			String name10 = tfname.getText();
			double price10 = Integer.parseInt(tfprice.getText());
			if (rb10a.isSelected()) { // 更新菜单
				tfdish.add(new TextField());
				dishes.add(new Dish(name10, price10));
				int n = tfdish.size();
				vb41.getChildren().addAll(new Text(name10 + " " + price10), tfdish.get(n - 1));
			} else if (rb10b.isSelected()) {
				tfdianxin.add(new TextField());
				dianxins.add(new Dianxin(name10, price10));
				int n = tfdianxin.size();
				vb42.getChildren().addAll(new Text(name10 + " " + price10), tfdianxin.get(n - 1));
			} else if (rb10c.isSelected()) {
				tfdessert.add(new TextField());
				desserts.add(new Dessert(name10, price10));
				int n = tfdessert.size();
				vb121.getChildren().addAll(new Text(name10 + " " + price10), tfdessert.get(n - 1));
			} else {
				tfdrink.add(new TextField());
				drinks.add(new Drink(name10, price10));
				int n = tfdrink.size();
				vb122.getChildren().addAll(new Text(name10 + " " + price10), tfdrink.get(n - 1));
			}
			stage10_popup.show();
		});
		Scene scene10 = new Scene(p10, 250, 300);
		s10.setTitle("Add Food");
		s10.setScene(scene10);
		// s10 add food page included name,price and type END

		// s7 management page choose manage or add new food to the menu START
		VBox p7 = new VBox(15);
		p7.setAlignment(Pos.CENTER);
		p7.setStyle("-fx-border-color:null;-fx-background-color:gold;");
		Button btmanage = new Button("管理菜单");
		btmanage.setOnMouseClicked(e71 -> {
			stagecontrol.setStage(s11, s7);
		});
		Button btadd = new Button("新增菜品");
		p7.getChildren().addAll(new Text("管理菜单：可修改现有菜品价格，\n或删除现有菜品。"), btmanage, new Text("增加新菜"), btadd);
		btadd.setOnMouseClicked(e7 -> {
			stagecontrol.setStage(s10);
		});
		Button btreturn7 = new Button("BACK");
		btreturn7.setOnMouseClicked(e72 -> {
			stagecontrol.setStage(s6, s7);
		});
		p7.getChildren().add(btreturn7);
		btreturn7.setAlignment(Pos.CENTER_RIGHT);
		Scene scene7 = new Scene(p7, width, height);
		s7.setTitle("Menu Management");
		s7.setScene(scene7);
		// s7 management page choose manage or add new food to the menu END

		// s3 get the number of guest,if the guest already book seats, choose tea type
		// START
		VBox p3_1 = new VBox(5); // tea choose pane
		p3_1.setAlignment(Pos.CENTER);
		p3_1.setPadding(new Insets(5));
		p3_1.setStyle("-fx-border-color:grey;-fx-background-color:pink;");
		RadioButton[] rb3 = new RadioButton[8];
		ToggleGroup tg3 = new ToggleGroup(); // 保证只选了一种茶
		for (int i = 0; i < tealist.size(); i++) {
			rb3[i] = new RadioButton(tealist.get(i).getTeaName() + " " + tealist.get(i).getTeaPrice());
			rb3[i].setContentDisplay(ContentDisplay.RIGHT);
			rb3[i].setToggleGroup(tg3);
			p3_1.getChildren().add(rb3[i]);
		}
		GridPane p3 = new GridPane();
		p3.setAlignment(Pos.CENTER);
		p3.setPadding(new Insets(10));
		p3.setHgap(10);
		p3.setVgap(10);
		p3.setStyle("-fx-border-color:null;-fx-background-color:gold;");
		p3.add(new Text("就餐人数"), 0, 0);
		p3.add(new Text("是否有预约"), 0, 1);
		p3.add(new Text("茶水选择/每位价格"), 0, 2);
		p3.add(p3_1, 0, 3);
		CheckBox cb3 = new CheckBox(); // 是否有预约
		cb3.setSelected(false);
		p3.add(cb3, 1, 1);
		TextField tf3 = new TextField(); // 就餐人数
		tf3.setPrefColumnCount(2);
		p3.add(tf3, 1, 0);

		Button bt3 = new Button("NEXT");
		p3.add(bt3, 2, 4);
		bt3.setOnMouseClicked(ea -> {
			if (currentCustomer.isIsCurrentvip()) {
				currentCustomer.getVip_current().setNumberOfpeople(Integer.parseInt(tf3.getText()));
				for (int i = 0; i < tealist.size(); i++) {
					if (rb3[i].isSelected()) {
						currentCustomer.getVip_current().setTeatype(tealist.get(i));
					}
				}
			} else {
				currentCustomer.getNotvip_current().setNumberOfpeople(Integer.parseInt(tf3.getText()));
				for (int i = 0; i < tealist.size(); i++) {
					if (rb3[i].isSelected()) {
						currentCustomer.getNotvip_current().setTeatype(tealist.get(i));
					}
				}
			}
			stagecontrol.setStage(s4, s3);
		});

		Scene scene3 = new Scene(p3, width, height);
		s3.setTitle("Start to order");
		s3.setScene(scene3);
		// s3 get the number of guest,if the guest already book seats, choose tea type
		// END

		// s13 choose how to pay,with total fee START
		GridPane p13 = new GridPane();
		p13.setAlignment(Pos.CENTER); // 对齐方式
		p13.setPadding(new Insets(5));
		p13.setHgap(5);
		p13.setVgap(30);
		p13.setStyle("-fx-border-color:null;-fx-background-color:gold;");
		Text t13 = new Text("请选择支付方式");
		t13.setFont(new Font("幼圆", 25));
		Text t131 = new Text("共消费 ");
		t131.setFont(new Font("幼圆", 25));
		Text t132 = new Text(" 元");
		t132.setFont(new Font("幼圆", 25));

		t_sum.setFont(new Font("幼圆", 25));
		Button btcash = new Button("现金支付");
		Button btcard = new Button("银联卡/信用卡支付");
		btcash.setOnMouseClicked(e -> {
			stagecontrol.setStage(s14, s13);
		});
		btcard.setOnMouseClicked(e -> {
			stagecontrol.setStage(s14, s13);
		});
		p13.add(t13, 1, 1);
		p13.add(t131, 1, 2);
		p13.add(t_sum, 2, 2);
		p13.add(t132, 3, 2);
		p13.add(btcash, 1, 3);
		p13.add(btcard, 1, 4);
		Scene scene13 = new Scene(p13, width, height);
		s13.setTitle("Please choose a way to pay.");
		s13.setScene(scene13);
		// s13 choose how to pay,with total fee END

		// s14 successfully pay page start
		Text t14 = new Text("支付成功！\n");
		t14.setFont(new Font("幼圆", 35));
		Button return_mainmenu = new Button("返回首页");
		return_mainmenu.setOnMouseClicked(e -> {
			zeroFoodNumber();
			stagecontrol.setStage(primaryStage, s14);
		});
		VBox p14 = new VBox(30);
		p14.setPadding(new Insets(5));
		p14.setAlignment(Pos.CENTER);
		p14.getChildren().addAll(t14, return_mainmenu);
		p14.setStyle("-fx-background-color:gold;");
		Scene scene14 = new Scene(p14, width, height);
		s14.setTitle("You have successfully paid.");
		s14.setScene(scene14);
		// s14 successfully pay page end

		// s6 management page start
		Text t6 = new Text("管理菜单：增加/删减菜品，修改菜品价格。\n" + "管理会员：查看会员名单，修改会员资格。\n" + "查看营业额：查看总营业额。");
		t6.setFont(new Font("幼圆", 15));
		Button menu_manage = new Button("管理菜单");
		Button vip_manage = new Button("管理会员");
		Button revenue_check = new Button("查看营业额");

		menu_manage.setOnMouseClicked(e6 -> {
			stagecontrol.setStage(s7, s6);
		});
		vip_manage.setOnMouseClicked(e61 -> {
			Set<String> settemp = viplist.keySet();
			ta91.setText(settemp.toString());
			stagecontrol.setStage(s9, s6);
		});
		revenue_check.setOnMouseClicked(e62 -> {
			t8.setText("总营业额 ：" + String.valueOf(totalRevenue) + "元");
			stagecontrol.setStage(s8);
		});

		VBox p6 = new VBox(30);
		p6.setPadding(new Insets(5));
		p6.setAlignment(Pos.CENTER);
		Button btreturn6 = new Button("BACK");
		btreturn6.setOnMouseClicked(e63 -> {
			stagecontrol.setStage(s1, s6);
		});
		p6.getChildren().addAll(t6, menu_manage, vip_manage, revenue_check, btreturn6);
		p6.setStyle("-fx-background-color:gold;");
		Scene scene6 = new Scene(p6, width, height);
		s6.setTitle("Restaurant Management");
		s6.setScene(scene6);
		// s6 management page end

		// s5 manager login start
		/////////////// manager login fail/////////////////
		VBox vb_mlf = new VBox();
		Button bt_mlf = new Button("重新输入");
		vb_mlf.getChildren().addAll(new Text("管理人员账号或密码输入错误"), bt_mlf);
		vb_mlf.setAlignment(Pos.CENTER);
		Stage stage_mlf = new Stage();
		Scene scene_mlf = new Scene(vb_mlf, 350, 50);
		stage_mlf.setScene(scene_mlf);
		stage_mlf.setTitle("管理人员登陆失败");
		/////////////// manager login fail/////////////////
		GridPane p5 = new GridPane();
		p5.setAlignment(Pos.CENTER); // 对齐方式
		p5.setPadding(new Insets(5));
		p5.setHgap(5.5);
		p5.setVgap(5.5);
		p5.setStyle("-fx-border-color:null;-fx-background-color:gold;");
		p5.add(new Label("Manager Account: "), 0, 1);
		TextField tfaccount5 = new TextField();
		p5.add(tfaccount5, 1, 1);
		p5.add(new Label("Keyword:"), 0, 2);
		PasswordField tfkeyword5 = new PasswordField();
		p5.add(tfkeyword5, 1, 2);
		Button btloginAccount5 = new Button("Manager Login");
		p5.add(btloginAccount5, 1, 3);
		btloginAccount5.setOnMouseClicked(emanager -> {
			if (managerLogin(tfaccount5.getText(), tfkeyword5.getText())) {
				stagecontrol.setStage(s6, s5);
			} else {
				stage_mlf.show();
				bt_mlf.setOnMouseClicked(eman -> {
					stage_mlf.close();
				});
			}
		});
		Button btreturn5 = new Button("返回主菜单");
		btreturn5.setOnMouseClicked(e -> {
			stagecontrol.setStage(s1, s5);
		});
		p5.add(btreturn5, 0, 4);
		Scene scene5 = new Scene(p5, width, height);
		s5.setTitle("Manager Login");
		s5.setScene(scene5);
		// s5 manager login end

	}

	public static void main(String[] args) {
		initVipList();
		initalOriginalMenu();
		stagecontrol = new StageController();
		createTextFieldForOriginalMenu();
		launch(args);
	}

	public static void initalOriginalMenu() {
		tealist.add(new Tea("祁门红茶", 25));
		tealist.add(new Tea("蒙顶甘露", 27));
		tealist.add(new Tea("西湖龙井", 35));
		tealist.add(new Tea("庐山云雾", 20));
		tealist.add(new Tea("君山银针", 26));
		tealist.add(new Tea("古树普洱", 30));
		tealist.add(new Tea("茉莉毛尖", 35));
		tealist.add(new Tea("恩施玉露", 25));

		dishes.add(new Dish("黑松露野菌素卷", 398));
		dishes.add(new Dish("香煎银鳕鱼配香芒", 198));
		dishes.add(new Dish("小米炖辽参", 298));
		dishes.add(new Dish("陈酒醉鸡", 155));
		dishes.add(new Dish("卤水狮头鹅粉肝", 398));
		dishes.add(new Dish("黑蒜雪花牛肉粒", 198));

		dianxins.add(new Dianxin("花旗参汤四色虾饺", 45));
		dianxins.add(new Dianxin("明瑕蟹子烧卖", 35));
		dianxins.add(new Dianxin("上海小笼包", 35));
		dianxins.add(new Dianxin("瑶柱糯米鸡", 40));
		dianxins.add(new Dianxin("百合酱蒸凤爪", 30));
		dianxins.add(new Dianxin("泰式冰榴莲", 45));

		desserts.add(new Dessert("杏仁茶", 78));
		desserts.add(new Dessert("糖蒸酥酪", 68));
		desserts.add(new Dessert("枣泥山药糕", 43));
		desserts.add(new Dessert("桂花糖蒸栗粉糕", 45));
		desserts.add(new Dessert("芝麻糖脆", 35));
		desserts.add(new Dessert("玫瑰卤子", 58));

		drinks.add(new Drink("82年的拉菲", 99999));
		drinks.add(new Drink("干邑白兰地", 588));
		drinks.add(new Drink("苏格兰威士忌", 1288));
		drinks.add(new Drink("鲜榨苹果汁", 66));
		drinks.add(new Drink("天山牛初乳", 53));
		drinks.add(new Drink("巴黎气泡水", 18));
	}

	public static boolean viplogin(String account, String key) // 判断用户登录是否成功
	{
		VIP v;
		if (viplist.containsKey(account)) {
			v = viplist.get(account); // 获取key对应的value
			if (key.equals(v.getKeyword())) {
				return true;
			} else
				return false;
		} else
			return false;
	}

	public static boolean managerLogin(String account, String key) {
		if (account.compareTo(manager_account) == 0) {
			if (key.compareTo(manager_key) == 0) {
				return true;
			} else
				return false;
		} else
			return false;
	}

	public static void createTextFieldForOriginalMenu() {
		TextField[] tfA = new TextField[6];
		for (int i = 0; i < 6; i++) {
			tfA[i] = new TextField();
			tfA[i].setPrefColumnCount(3);
			tfdish.add(tfA[i]);
		}
		TextField[] tfB = new TextField[6];
		for (int i = 0; i < 6; i++) {
			tfB[i] = new TextField();
			tfB[i].setPrefColumnCount(3);
			tfdianxin.add(tfB[i]);
		}
		TextField[] tfC = new TextField[6];
		for (int i = 0; i < 6; i++) {
			tfC[i] = new TextField();
			tfC[i].setPrefColumnCount(3);
			tfdessert.add(tfC[i]);
		}
		TextField[] tfD = new TextField[6];
		for (int i = 0; i < 6; i++) {
			tfD[i] = new TextField();
			tfD[i].setPrefColumnCount(3);
			tfdrink.add(tfD[i]);
		}

	}

	public static void getDessertOrder() {
		for (int i = 0; i < desserts.size(); i++) {
			if ((tfdessert.get(i).getText()).compareTo("") != 0) {
				int n = Integer.parseInt(tfdessert.get(i).getText());
				if (currentCustomer.isIsCurrentvip()) {
					desserts.get(i).setNumberOfFood(n);
					currentCustomer.getVip_current().AddFood(desserts.get(i));

				} else {
					desserts.get(i).setNumberOfFood(n);
					currentCustomer.getNotvip_current().AddFood(desserts.get(i));
				}
			}

		}
	}

	public static void getDishesOrder() {
		for (int i = 0; i < dishes.size(); i++) {
			if ((tfdish.get(i).getText()).compareTo("") != 0) {
				int n = Integer.parseInt(tfdish.get(i).getText());
				if (currentCustomer.isIsCurrentvip()) {
					dishes.get(i).setNumberOfFood(n);
					currentCustomer.getVip_current().AddFood(dishes.get(i));
				} else {
					dishes.get(i).setNumberOfFood(n);
					currentCustomer.getNotvip_current().AddFood(dishes.get(i));
				}
			}

		}
	}

	public static void getDianxinOrder() {
		for (int i = 0; i < dianxins.size(); i++) {
			if ((tfdianxin.get(i).getText()).compareTo("") != 0) {
				int n = Integer.parseInt(tfdianxin.get(i).getText());
				if (currentCustomer.isIsCurrentvip()) {
					dianxins.get(i).setNumberOfFood(n);
					currentCustomer.getVip_current().AddFood(dianxins.get(i));
				} else {
					dianxins.get(i).setNumberOfFood(n);
					currentCustomer.getNotvip_current().AddFood(dianxins.get(i));
				}
			}

		}
	}

	public static void getDrinkOrder() {
		for (int i = 0; i < drinks.size(); i++) {
			if ((tfdrink.get(i).getText()).compareTo("") != 0) {
				int n = Integer.parseInt(tfdrink.get(i).getText());
				if (currentCustomer.isIsCurrentvip()) {
					drinks.get(i).setNumberOfFood(n);
					currentCustomer.getVip_current().AddFood(drinks.get(i));
				} else {
					drinks.get(i).setNumberOfFood(n);
					currentCustomer.getNotvip_current().AddFood(drinks.get(i));
				}
			}

		}
	}

	public static String orderPrintOut() {
		ArrayList<Food> f;
		StringBuffer s = new StringBuffer();
		if (currentCustomer.isIsCurrentvip()) {
			f = currentCustomer.getVip_current().getFoodlist();
			for (int i = 0; i < f.size(); i++) {
				s.append(f.get(i).getFoodName().toString());
				s.append("/");
				s.append(String.valueOf(f.get(i).getNumberOfFood()));
				s.append("份\n");
			}
		} else {
			f = currentCustomer.getNotvip_current().getFoodlist();
			for (int i = 0; i < f.size(); i++) {
				s.append(f.get(i).getFoodName().toString());
				s.append("/");
				s.append(String.valueOf(f.get(i).getNumberOfFood()));
				s.append("份\n");
			}
		}
		return s.toString();
	}

	public static double getTotalPrice() {
		double price = 0;
		if (currentCustomer.isCurrentvip) {
			for (int i = 0; i < currentCustomer.vip_current.getFoodlist().size(); i++) {
				double t = currentCustomer.vip_current.getFoodlist().get(i).getNumberOfFood()
						* currentCustomer.vip_current.getFoodlist().get(i).getFoodPrice();
				price += t;
			}
			if (price >= 500) {
				price *= 0.8;
			}
			price += 300;
		} else {
			for (int i = 0; i < currentCustomer.notvip_current.getFoodlist().size(); i++) {
				double t = currentCustomer.notvip_current.getFoodlist().get(i).getNumberOfFood()
						* currentCustomer.notvip_current.getFoodlist().get(i).getFoodPrice();
				price += t;
			}
			price += currentCustomer.getNotvip_current().getTeatype().getTeaPrice()
					* currentCustomer.getNotvip_current().getNumberOfpeople();
		}
		totalRevenue += price;
		return price;
	}

	public static void orderStartOver() {
		if (currentCustomer.isCurrentvip) {
			currentCustomer.vip_current.foodlist.clear();
		} else {
			currentCustomer.notvip_current.foodlist.clear();
		}
	}

	public static void initVipList() {
		viplist.put("Peter", new VIP("Peter", "111111"));
		viplist.put("Sarah", new VIP("Sarah", "111111"));
		viplist.put("Billy", new VIP("Billy", "111111"));
		viplist.put("Avery", new VIP("Avery", "111111"));
		viplist.put("Dora", new VIP("Dora", "111111"));
		viplist.put("Willian", new VIP("Willian", "111111"));
		viplist.put("Simon", new VIP("Simon", "111111"));
	}

	public static void zeroFoodNumber() {
		for (int i = 0; i < dishes.size(); i++) {
			dishes.get(i).clearFood();
		}
		for (int i = 0; i < desserts.size(); i++) {
			desserts.get(i).clearFood();
		}
		for (int i = 0; i < dianxins.size(); i++) {
			dianxins.get(i).clearFood();
		}
		for (int i = 0; i < drinks.size(); i++) {
			drinks.get(i).clearFood();
		}

	}

	public static void AdjustMenuInitial(VBox vb) { // repaint adjust menu
		allfood.clear();
		for (int i = 0; i < dishes.size(); i++) {
			if (dishes.get(i).isAvailable()) {
				allfood.add(dishes.get(i));
			}
		}
		for (int i = 0; i < dianxins.size(); i++) {
			if (dianxins.get(i).isAvailable()) {
				allfood.add(dianxins.get(i));
			}
		}
		for (int i = 0; i < desserts.size(); i++) {
			if (desserts.get(i).isAvailable()) {
				allfood.add(desserts.get(i));
			}
		}
		for (int i = 0; i < drinks.size(); i++) {
			if (drinks.get(i).isAvailable()) {
				allfood.add(drinks.get(i));
			}
		}
		for (int i = 0; i < allfood.size(); i++) {
			CheckBox cb = new CheckBox();
			cb.setGraphic(new Text(allfood.get(i).getFoodName() + " / " + allfood.get(i).getFoodPrice()));
			cb.setContentDisplay(ContentDisplay.LEFT);
			TextField tf = new TextField();
			cb11.add(cb);
			tf11.add(tf);
			vb.getChildren().addAll(cb, tf);
		}
	}

	public static void AdjustMenuDeuxFois(VBox vb) {

		for (int i = 0; i < allfood.size(); i++) {
			if (cb11.get(i).isSelected()) {
				allfood.get(i).setAvailable(false);
			}
		}
		for (int i = 0; i < allfood.size(); i++) {
			if ((tf11.get(i).getText()).compareTo("") != 0) {
				allfood.get(i).setFoodPrice(Double.parseDouble(tf11.get(i).getText()));
				Food f = allfood.get(i);
				vb.getChildren().removeAll(tf11.get(i), cb11.get(i));
				tf11.remove(i);
				cb11.remove(i);
				allfood.remove(i);
				CheckBox cb = new CheckBox();
				allfood.add(f);
				cb.setGraphic(new Text(f.getFoodName() + " / " + f.getFoodPrice()));
				cb.setContentDisplay(ContentDisplay.LEFT);
				TextField tf = new TextField();
				cb11.add(cb);
				tf11.add(tf);
				vb.getChildren().addAll(cb, tf);
			}
		}
		for (int i = 0; i < allfood.size(); i++) {
			if (allfood.get(i).isAvailable() == false) {
				vb.getChildren().removeAll(tf11.get(i), cb11.get(i));
				allfood.remove(i);
				tf11.remove(i);
				cb11.remove(i);
			}
		}

	}
}
