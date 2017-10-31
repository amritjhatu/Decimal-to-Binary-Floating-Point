import java.util.Scanner;
import java.text.DecimalFormat;

public class test {

    public static void main(String[] args) {
        DecimalFormat formatExponent = new DecimalFormat("0000");

        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print("Enter the decimal number: ");
            String number = "" + (input.nextDouble());
            String sign = Double.parseDouble(number) > 0 ? "0" : "1";
            number = sign.equals("1") ? number.replace("-", "") : number;
            String firstBinaryPart = Integer.toBinaryString((int) Double.parseDouble(number));
            String secondPart = "0" + number.substring(number.indexOf("."));
            String secondBinaryPart = "";
            double secondDoublePart = Double.parseDouble(secondPart);
            for (int i = 0; i < 10; i++) {

                secondDoublePart *= 2;
                if (secondDoublePart >= 1) {
                    secondBinaryPart += "1";
                    secondDoublePart -= 1;
                } else {
                    secondBinaryPart += "0";
                }
            }
            String binaryExpression = firstBinaryPart + "." + secondBinaryPart;
            System.out.println("Binary expression: " + binaryExpression);
            int movingRadixPointPostion = binaryExpression.indexOf(".") - binaryExpression.indexOf("1");
            int exponentDecimal = movingRadixPointPostion > 0 ? movingRadixPointPostion - 1 : movingRadixPointPostion;
            String exponentBias7;
            if (exponentDecimal < -7) {
                exponentBias7 = "0000";
            } else if (exponentDecimal > 8) {
                exponentBias7 = "1111";
            } else {
                exponentBias7 = formatExponent.format(Integer.parseInt(Integer.toBinaryString(exponentDecimal + 7)));
            }
            binaryExpression = binaryExpression.replace(".", "");
            String mantissa;
            if (binaryExpression.indexOf("1") + 6 > binaryExpression.length()) {
                mantissa = formatMantissa(
                        Integer.parseInt(binaryExpression.substring(binaryExpression.indexOf("1") + 1)));
            } else {
                mantissa = binaryExpression.substring(binaryExpression.indexOf("1") + 1,
                        binaryExpression.indexOf("1") + 6);
            }
            System.out.println("BCIT Floating Point: " + sign + "|" + exponentBias7 + "|" + mantissa);
            System.out.println("Continue? (y/n)");
            String decision = input.next();
            if (decision.equals("n")) {
                break;
            }
        }
        input.close();
    }

    private static String formatMantissa(int number) {
        String numberToString = "" + number;
        while (numberToString.length() < 5) {
            numberToString += "0";
        }
        return numberToString;
    }

}
