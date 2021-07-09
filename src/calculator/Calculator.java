package calculator;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculator extends Application {

    String display = "";
    String operator = "";
    BigDecimal temp = new BigDecimal(0.0);

    public void start(Stage stage) {
        VBox vBox = new VBox();

        javafx.scene.control.TextField displayArea = new TextField("0");
        displayArea.setFont(new Font(65));
        displayArea.setAlignment(Pos.CENTER_RIGHT);
        displayArea.setPrefSize(400, 120);
        displayArea.setEditable(false);

        TilePane buttons = new TilePane();
        buttons.setPrefColumns(4);
        String s = "789/456x123-0.=+";

        for (int i = 0; i < s.length(); i++) {
            Button button = new Button(String.valueOf(s.charAt(i)));
            button.setPrefSize(100, 100);
            button.setFont(new Font(45));
            button.setOnAction((event) -> {

                String input = button.getText();

                if (input.matches("0|1|2|3|4|5|6|7|8|9") || input.equals(".")) {
                    display = display + input;
                }

                if (input.equals("+") || input.matches("-|/|x")) {

                    if (!display.isEmpty() && temp.compareTo(BigDecimal.ZERO)==0) {
                        temp = new BigDecimal(display);
                    }


                    if (!operator.isEmpty() && !display.isEmpty()) {
                        if (operator.equals("+")) {
                            temp = temp.add(new BigDecimal(display));
                        }
                        if (operator.equals("-")) {
                            temp = temp.subtract(new BigDecimal(display));
                        }
                        if (operator.equals("x")) {
                            temp = temp.multiply(new BigDecimal(display));
                        }
                        if (operator.equals("/")) {
                            if (!(Double.valueOf(display) == 0)) {
                                temp = temp.divide(new BigDecimal(display), RoundingMode.HALF_UP);
                            }
                        }
                    }
                    operator = input;
                    display = "";
                }

                if (input.equals("=") && !operator.isEmpty()) {
                    if (operator.equals("+")) {
                        temp = temp.add(new BigDecimal(display));
                    }
                    if (operator.equals("-")) {
                        temp = temp.subtract(new BigDecimal(display));
                    }
                    if (operator.equals("x")) {
                        temp = temp.multiply(new BigDecimal(display));
                    }
                    if (operator.equals("/")) {
                        if (!(Double.valueOf(display) == 0)) {
                            temp = temp.divide(new BigDecimal(display),6, RoundingMode.HALF_DOWN);
                        }
                    }
                    operator = "";
                    temp = temp.stripTrailingZeros();
                    display = String.valueOf(temp);
                }
                displayArea.setText(display);
                System.out.println("temp: " + temp);
                System.out.println("operator: " + operator);
                System.out.println("display: " + display);


            });
            buttons.getChildren().add(button);
        }

        vBox.getChildren().addAll(displayArea, buttons);
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Calculator");
        stage.show();
    }
}
