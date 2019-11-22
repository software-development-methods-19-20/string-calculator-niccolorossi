package dssc.calculator;

import java.util.List;
import java.util.Arrays;


public class StringCalculator {

    public static int add(String numbers) {

        List<String> separators = Arrays.asList("\\n", ",");

        if (numbers.isEmpty()) {
            return 0;
        } else if (separators.stream().anyMatch(x -> numbers.contains(x))) {
            String joinedSeparators = separators.stream().reduce("", (x,y)->x+y);
            String separatorsRegex = "[" + joinedSeparators + "]";
            List<String> tokensList = Arrays.asList(numbers.split(separatorsRegex));
            return tokensList.stream().map(x -> Integer.parseInt(x)).reduce(0, (x,y)->x+y);
        } else {
            return Integer.parseInt(numbers);
        }

    }
}
