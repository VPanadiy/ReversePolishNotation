package dream.development.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.*;

/**
 * Class that parse infix form to RPN
 */
public class Parser implements Initializable {

    private ResourceBundle resourceBundle;

    @FXML
    private TextField txtNumExpressions;

    @FXML
    private TextArea txtAreaInput;

    @FXML
    private Button btnLaunch;

    @FXML
    private TextArea txtAreaResult;

    /**
     * Initialize parameters after stage creating
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resourceBundle = resources;
    }

    /**
     * Main method that launch RPN parser
     */
    public void mainController() {
        txtAreaResult.clear();

        if (checkValidCharacter()) {

            List<String> expressionsList = new ArrayList<>();
            Collections.addAll(expressionsList, txtAreaInput.getText().split("\n"));

            int loopLength = Integer.parseInt(txtNumExpressions.getText());
            if (expressionsList.size() < Integer.parseInt(txtNumExpressions.getText())) {
                loopLength = expressionsList.size();
            }

            for (int i = 0; i < loopLength; i++) {
                txtAreaResult.appendText(infixToRPN(expressionsList.get(i)) + "\n");
            }

        }

    }

    /**
     * Check valid input parameters
     *
     * @return Boolean. If input values are valid return true, else return false;
     */
    private boolean checkValidCharacter() {

        if (txtNumExpressions.getText().equals("")) {
            txtAreaResult.setText(resourceBundle.getString("main.numExpressionsError"));
            return false;
        }

        for (int i = 0; i < txtNumExpressions.getText().length(); i++) {
            if (!Character.isDigit(txtNumExpressions.getText().charAt(i))) {
                txtAreaResult.setText(resourceBundle.getString("main.numExpressionsInvalid"));
                return false;
            }
        }

        if (Integer.parseInt(txtNumExpressions.getText()) == 0 || Integer.parseInt(txtNumExpressions.getText()) > 100) {
            txtAreaResult.setText(resourceBundle.getString("main.numExpressionsError"));
            return false;
        }

        if (txtAreaInput.getText().length() == 0) {
            txtAreaResult.setText(resourceBundle.getString("main.inputError"));
            return false;
        }

        return true;
    }

    /**
     * Parse infix form to RPN
     *
     * @param input String
     * @return RPN String
     */
    public String infixToRPN(String input) {
        char[] charArray = input.toCharArray();
        Stack<Character> stack = new Stack<Character>();
        StringBuilder out = new StringBuilder();

        for (char inputChar : charArray) {

            switch (inputChar) {
                case '+':
                case '-': {
                    while (!stack.empty() && (stack.peek() == '*' || stack.peek() == '/' || stack.peek() == '^')) {
                        out.append(stack.pop());
                    }
                    stack.push(inputChar);
                    break;
                }
                case '*':
                case '/':
                case '^': {
                    stack.push(inputChar);
                    break;
                }
                case '(': {
                    stack.push(inputChar);
                    break;
                }
                case ')': {
                    while (!stack.empty() && stack.peek() != '(') {
                        out.append(stack.pop());
                    }
                    stack.pop();
                    break;
                }
                default: {
                    out.append(inputChar);
                    break;
                }
            }

        }

        return out.toString();
    }

}
