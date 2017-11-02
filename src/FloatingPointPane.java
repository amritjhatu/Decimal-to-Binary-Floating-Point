import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import java.text.DecimalFormat;

/**
 * Create the object of type FloatingPointPane.
 * 
 * @author Hoang, Minh
 * @version 31/10/2017
 *
 */
public class FloatingPointPane extends GridPane {
    /**
     * Stores the TextField for the decimal.
     */
    private TextField decimal;
    /**
     * Stores the TextField for the binary.
     */
    private TextField binary;
    /**
     * Stores the TextField for the sign.
     */
    private TextField sign;
    /**
     * Stores the TextField for the exponent.
     */
    private TextField exponent;
    /**
     * Stores the TextField for the mantissa.
     */
    private TextField mantissa;
    /**
     * Stores the Label for the decimal.
     */
    private Label decimalLabel;
    /**
     * Stores the Label for the binaryLabel.
     */
    private Label binaryLabel;
    /**
     * Stores the Button for the convert.
     */
    private Button convert;

    /**
     * Constructor of the class.
     */
    public FloatingPointPane() {
        createNode();
        addNode();
        setMarginAll();

    }

    /**
     * Creates all the nodes in the grid.
     */
    public void createNode() {
        decimalLabel = new Label("Decimal:");
        binaryLabel = new Label("Binary:");
        decimal = new TextField();
        binary = new TextField();
        sign = new TextField();
        exponent = new TextField();
        mantissa = new TextField();
        convert = new Button("Convert");
        convert.setDefaultButton(true);
        convert.setOnAction(this::convert);

    }

    /**
     * Adds all the nodes to the grid.
     */
    public void addNode() {
        final int i3 = 3;
        add(decimalLabel, 0, 0);
        add(decimal, 1, 0);
        add(binaryLabel, 0, 1);
        add(binary, 1, 1);
        add(sign, 0, 2);
        add(exponent, 1, 2);
        add(mantissa, 2, 2);
        add(convert, 0, i3);
    }

    /**
     * Sets the margin for all the nodes.
     */
    public void setMarginAll() {
        final int i30 = 30;
        final int i5 = 5;
        Insets firstRow = new Insets(i30, 0, 0, i30);
        Insets secondRow = new Insets(i5, 0, 0, i30);
        Insets thirdRow = new Insets(i5, 0, 0, i30);
        Insets forthRow = new Insets(i30, 0, 0, i30);

        setMargin(decimalLabel, firstRow);
        setMargin(decimal, firstRow);
        setMargin(binaryLabel, secondRow);
        setMargin(binary, secondRow);

        setMargin(sign, thirdRow);
        setMargin(exponent, thirdRow);
        setMargin(mantissa, thirdRow);
        setMargin(convert, forthRow);

    }

    /**
     * Create a convert event for the button.
     * 
     * @param event
     *            unused.
     */
    public void convert(ActionEvent event) {
        String binaryExpression = "";
        String sign;
        String exponentBias7;
        String mantissa;

        String number = "" + Double.parseDouble(decimal.getText());
        if (Double.parseDouble(number) > 252) {
            sign = "0";
            exponentBias7 = "1111";
            mantissa = "00000";
        } else {
            number += number.contains(".") ? "" : "."; // Add the , to the end of the number
            sign = setSign(Double.parseDouble(number));
            number = sign.equals("1") ? number.replace("-", "") : number;
            String firstBinaryPart = Integer.toBinaryString((int) Double.parseDouble(number));
            String secondPart = "0" + number.substring(number.indexOf("."));
            String secondBinaryPart = getSecondDoublePart(Double.parseDouble(secondPart));

            binaryExpression = firstBinaryPart + "." + secondBinaryPart;
            this.binary.setText(binaryExpression);
            int movingRadixPointPostion = binaryExpression.indexOf(".") - binaryExpression.indexOf("1");
            int exponentDecimal = movingRadixPointPostion > 0 ? movingRadixPointPostion - 1 : movingRadixPointPostion;

            if (exponentDecimal < -7 || binaryExpression.indexOf("1") == -1) {
                exponentBias7 = "0000";
            } else {
                exponentBias7 = formatExponent(Integer.parseInt(Integer.toBinaryString(exponentDecimal + 7)));
            }
            binaryExpression = binaryExpression.replace(".", "");

            if (binaryExpression.indexOf("1") + 6 > binaryExpression.length()) {
                mantissa = formatMantissa(
                        Integer.parseInt(binaryExpression.substring(binaryExpression.indexOf("1") + 1)));
            } else {
                mantissa = binaryExpression.substring(binaryExpression.indexOf("1") + 1,
                        binaryExpression.indexOf("1") + 6);
            }

        }

        this.sign.setText(sign);
        this.exponent.setText(exponentBias7);
        this.mantissa.setText(mantissa);
    }

    /**
     * Returns the secondPart
     * 
     * @param secondPart
     *            a double
     * @return a string.
     */
    public String getSecondDoublePart(double secondPart) {
        String secondBinaryPart = "";

        for (int i = 0; i < 10; i++) {
            secondPart *= 2;
            if (secondPart >= 1) {
                secondBinaryPart += "1";
                secondPart -= 1;
            } else {
                secondBinaryPart += "0";
            }
        }
        return secondBinaryPart;
    }

    /**
     * Gets the sign of the number.
     * 
     * @param number
     *            a double
     * @return a String
     */
    public String setSign(double number) {
        return number > 0 ? "0" : "1";
    }

    /**
     * Formats the exponent.
     * 
     * @param exponent
     *            as integer
     * @return a string.
     */
    public String formatExponent(int exponent) {
        DecimalFormat formatExponent = new DecimalFormat("0000");
        return formatExponent.format(exponent);
    }

    /**
     * Format the mantissa to match the 5-digit binary format.
     * 
     * @param number
     *            the mantissa
     * @return the number as a String
     */
    public static String formatMantissa(int number) {
        final int i5 = 5;
        String numberToString = "" + number;
        while (numberToString.length() < i5) {
            numberToString += "0";
        }
        return numberToString;
    }

}
