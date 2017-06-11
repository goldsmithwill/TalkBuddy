package application;

import buddies.ClientBuddy;
import buddies.ServerBuddy;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Main extends Application {
	private TextArea convoBox;
	private TextField answerTextField = new TextField();
	public static ClientBuddy client;
	public static ProgressIndicator progressIndicator = new ProgressIndicator();
	public static String username;
	private TextField usernameTextField = new TextField();
	private TextField clientPortField = new TextField();
	private TextField serverPortField = new TextField();
	private TextField ipTextField = new TextField();
	private String serverPort;
	private String clientPort;
	private String hostName;
	private ServerBuddy server;

	@Override
	public void start(Stage primaryStage) {
		setConvoBox(new TextArea());

		Stage loginStage = new Stage();
		loginStage.setTitle("Login");
		GridPane loginGrid = new GridPane();
		loginGrid.setAlignment(Pos.CENTER);
		loginGrid.setHgap(10);
		loginGrid.setVgap(10);
		loginGrid.setPadding(new Insets(25, 25, 25, 25));
		loginGrid.setStyle("-fx-base: blue;");
		Button loginButton = new Button("Login");

		TextField[] textFieldArray = { usernameTextField, clientPortField,
				serverPortField, ipTextField };
		for (int j = 0; j < textFieldArray.length; j++) {
			textFieldArray[j].setStyle("-fx-base: green;");
			loginGrid.add(textFieldArray[j], 1, j + 2);
		}
		Label usernameLabel = new Label("Username: ");
		Label clientPortLabel = new Label("Client Port: ");
		Label serverPortLabel = new Label("Server Port: ");
		Label ipLabel = new Label("IP Address: ");
		Label[] labelArray = { usernameLabel, clientPortLabel, serverPortLabel,
				ipLabel };
		for (Label label : labelArray) {
			label.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
		}
		for (int i = 0; i < labelArray.length; i++) {
			loginGrid.add(labelArray[i], 0, i + 2);
		}

		loginButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, 21));

		loginButton.setStyle("-fx-base: green;");
		loginButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				boolean shouldBreak = false;
				for (TextField textField : textFieldArray) {
					if (textField.getText().equals("")) {
						shouldBreak = true;
						break;
					}
				}
				if (shouldBreak) {
					return;
				}
				username = usernameTextField.getText();
				clientPort = clientPortField.getText();
				serverPort = serverPortField.getText();
				hostName = ipTextField.getText();
				server = new ServerBuddy(serverPort);
				client = new ClientBuddy(hostName, clientPort, getConvoBox(),
						getAnswerTextField());
				primaryStage.show();
				new Thread(server).start();
				new Thread(client).start();
			}
		});
		loginGrid.add(loginButton, 1, 1);
		Scene loginScene = new Scene(loginGrid, 400, 300);
		loginStage.setScene(loginScene);

		primaryStage.setTitle("TalkBuddy");
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
grid.setStyle("-fx-base: blue;");
		
		Text botName = new Text("TalkBuddy");
		botName.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(botName, 0, 0, 2, 1);

		Label answer = new Label("Answer:");
		grid.add(answer, 0, 1);

		grid.add(answerTextField, 1, 1);
		convoBox.setEditable(false);
		convoBox.setPrefSize(450, 1000);
		grid.add(convoBox, 1, 2);
		progressIndicator.setPrefSize(50, 50);
		progressIndicator.setVisible(true);
		grid.add(progressIndicator, 1, 2);

		Scene scene = new Scene(grid, 600, 450);
		primaryStage.setScene(scene);
		// primaryStage.show();
		loginStage.show();
		boolean listening = true;

	

	}

	public static void main(String[] args) {
		launch(args);
	}

	public TextArea getConvoBox() {
		return convoBox;
	}

	private void setConvoBox(TextArea convoBox) {
		this.convoBox = convoBox;
	}

	public TextField getAnswerTextField() {
		return answerTextField;
	}

	public void setAnswerTextField(TextField answerTextField) {
		this.answerTextField = answerTextField;
	}
}
