package dssc.calculator;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.stream.Collectors;


public class StringCalculator {

    public static int add(String numbers) throws NegativeNumberException {

        if (numbers.isEmpty()) {
            return 0;
        } else if (numbers.length() == 1) {
            return Integer.parseInt(numbers);
        } else {
            List<String> separators = new ArrayList<>();
            separators.add("\n");
            separators.add(",");

            if (needsPreprocessing(numbers)) {
                separators.add(newSeparator(numbers));
                numbers = preprocessedString(numbers);
            }

            String separatorsRegex = separators.stream().reduce("", (x,y)->x+y+"|");
            separatorsRegex = separatorsRegex.substring(0, separatorsRegex.length()-1);

            List<String> tokensList = Arrays.asList(numbers.split(separatorsRegex));
            List<Integer> integersList = tokensList.stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());

            List<Integer> negativeNums = integersList.stream().filter(x -> x <0).collect(Collectors.toList());
            if (!negativeNums.isEmpty()) throw new NegativeNumberException("Negatives not allowed: " + negativeNums.toString());

            return integersList.stream().filter(x -> x < 1001).reduce(0, (x,y)->x+y);

        }
    }

    private static boolean needsPreprocessing(String numbers) {
        String begin = numbers.substring(0, 2);
        if (begin.equals("//")) {
            return true;
        } else return false;
    }

    private static String preprocessedString(String numbers) {
        int index = numbers.indexOf("\n") + 1;
        return numbers.substring(index);
    }

    private static String newSeparator(String numbers) {
        int beginIndex = numbers.indexOf("//")+2;
        String beginChar = String.valueOf(numbers.charAt(beginIndex));
        if (beginChar.equals("[")) {
            int endIndex = numbers.indexOf("]");
            return numbers.substring(beginIndex+1, endIndex);
        } else return String.valueOf(numbers.charAt(beginIndex));
    }

}
