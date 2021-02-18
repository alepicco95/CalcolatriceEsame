package com.pinno99.calc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class Controller {
	
	@FXML
	private Text output;
	
	private long number1 = 0;
	private String operator = "";
	private boolean start = true;
	
	private double number2 =0;
	
	private Model model = new Model();
	
	@FXML
	private void processNumpad(ActionEvent event) {
		if (start) {
			output.setText("");
			start = false;
		}
		
		String value = ((Button)event.getSource()).getText();
		output.setText(output.getText() + value);
	}
	
	@FXML
	private void processOperator(ActionEvent event) {
		String value = ((Button)event.getSource()).getText();
		
		if(!"=".equals(value)) {
			if(!operator.isEmpty())
				return;
			
			operator = value;
			number1 = Long.parseLong(output.getText());
			output.setText("");
			
		}
		else {
			if(operator.isEmpty())
				return;
			
			output.setText(String.valueOf(model.calculate(number1, Long.parseLong(output.getText()), operator)));
			operator = "";
			start = true;
		}
	}
	
	@FXML
	private void processLn(ActionEvent event) {
		number2 = Double.valueOf(output.getText());
		
		if (number2 != 0) {
			Double res =  model.ln(number2);
			output.setText(String.valueOf(res));
			start = true;
		}else {
			System.out.println("Error, the number must be not equal to zero.");
			output.setText("");
		}
		
	}
	
	@FXML
	private void processSqrt(ActionEvent event) {
		number2 = Double.valueOf(output.getText());
		
		if (number2 >= 0) {
			Double res =  model.sqrt(number2);
			output.setText(String.valueOf(res));
			start = true;
		}else {
			System.out.println("Error, the number must be greater than zero.");
			output.setText("");
		}
		
	}
}
