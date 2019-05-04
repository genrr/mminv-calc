package impl;


import java.math.BigInteger;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Gui extends Application {
	
	private static boolean checkBoxState = false;

	@Override
	public void start(Stage arg0) {
		arg0.setTitle("Modular multiplicative inverse calculator");
		FlowPane flp = new FlowPane(Orientation.VERTICAL);
		Scene mainScene = new Scene(flp,650,550);
		arg0.setScene(mainScene);
		arg0.show();
		
		Button calculate = new Button("calculate");
		Button reset = new Button("reset");
		CheckBox apCalc = new CheckBox();
		TextField inputFieldB = new TextField("");
		TextField inputFieldM = new TextField("");
		TextArea output = new TextArea();
		output.setEditable(false);
		StringBuilder consoleText = new StringBuilder("initialized");
		output.setText(consoleText.toString());
		output.setPrefSize(400, 350);apCalc.setText("use BigInteger");
		FlowPane.setMargin(calculate, new Insets(30,0,10,20));
		FlowPane.setMargin(reset, new Insets(10,0,0,20));
		FlowPane.setMargin(output, new Insets(15,0,0,30));
		FlowPane.setMargin(apCalc, new Insets(20,0,10,20));
		flp.setPadding(new Insets(60));
		
		flp.getChildren().addAll(new Label("calculating inverse of"),inputFieldB,new Label("modulo"),inputFieldM,calculate,reset,apCalc,output);
		
		apCalc.setOnAction(e -> checkBoxState=!checkBoxState);
		calculate.setOnAction(e -> initCalc(inputFieldB,inputFieldM,output));
		reset.setOnAction(e -> resetFields(inputFieldB,inputFieldM,output));

	}
	
	private static void initCalc(TextField t1, TextField t2,TextArea console) {
		BigInteger b;
		BigInteger mod;
		BigInteger temp;
		
		if(t1.getText().length() == 0 || t2.getText().length() == 0) {
			console.appendText("\nnull arguments!");
		}
		else {
			try {
				b = new BigInteger(t1.getText());
				mod = new BigInteger(t2.getText());
				
				if(checkBoxState) {
					temp = APCalc.calculate_mminv(b, mod);
				}
				else {
					temp = new BigInteger(String.valueOf(Calc.calculate_mminv(b.longValue(),mod.longValue())));
				}
				console.appendText("\nInverse: " + temp + " | " + b + " * " + temp + " = "+ b.multiply(temp).mod(mod) + " (mod " + mod + ")");
				
				
			}
			catch(NumberFormatException e1) {
				console.appendText("\ninvalid arguments!");
			}
			catch(IllegalArgumentException e2) {
				if(e2.getMessage().equals("gcd(b,m)!=1")) {
					console.appendText("\ngreatest common divisor of "+Long.parseLong(t1.getText())+", "+Long.parseLong(t2.getText())+" is not 1");
				}
				else if(e2.getMessage().equals("mod=1")){
					console.appendText("\nmodulus is 1!");
				}
			}
			catch(ArithmeticException e3) {
				e3.printStackTrace();
				console.appendText("\nb * max(b,m) overflows a long!");
			}
		}	
	}
	
	private static void resetFields(TextField t1, TextField t2, TextArea console) {
		t1.clear();
		t2.clear();
		console.clear();
	}
	
	public static void main(String[] s) {
		launch(s);
	}

}
