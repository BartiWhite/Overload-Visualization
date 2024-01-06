import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulationUtil {

    protected static List<Classifier> classifiers;

    protected static final Random random = new Random();

    protected static int[] getRandomOutput(int ruleLength) {
        int[] newOutput = new int[ruleLength];
        fillOutputWithRandoms(newOutput);
        return newOutput;
    }

    protected static void fillOutputWithRandoms(int[] output) {
        for (int i = 0; i < output.length; i++) {
            output[i] = random.nextInt(2);
        }
    }

    protected static int[] getRandomInput(int ruleLength) {
        int[] newOutput = new int[ruleLength];
        fillInputWithRandoms(newOutput);
        return newOutput;
    }

    protected static void fillInputWithRandoms(int[] input) {
        for (int i = 0; i < input.length; i++) {
            input[i] = random.nextInt(3) - 1;
        }
    }

    protected static void shuffle(int nrOfClassifiers) {
        List<Integer> indexes = new ArrayList<>(nrOfClassifiers);

        for (int i = 0; i < nrOfClassifiers; i++) {
            int randomInt = random.nextInt(nrOfClassifiers);
            while (indexes.contains(randomInt)) {
                randomInt = (randomInt + 1)%(nrOfClassifiers);
            }

            indexes.add(randomInt);
        }

        List<Classifier> newList = new ArrayList<>(nrOfClassifiers);
        for (int index : indexes) {
            newList.add(classifiers.get(index));
        }

        classifiers = newList;
    }

    protected static void initClassifiers(int ruleLength, int nrOfClassifiers) {
        classifiers = new ArrayList<>();
        int drawingRange = (int) Math.pow(2, ruleLength);
        List<Integer> outputs = new ArrayList<>(drawingRange);

        for (int i = 0; i < nrOfClassifiers; i++) {
            outputs.add(random.nextInt(drawingRange));
        }

        for (int i = 0; i < nrOfClassifiers; i++) {
            classifiers.add(new Classifier(ruleLength, getInBinary(outputs.get(i), ruleLength)));
        }
    }

    protected static int[] getInBinary(int number, int inputLength) {
        StringBuilder builder = new StringBuilder();

        String binaryAsString = Integer.toBinaryString(number);
        builder.append("0".repeat(inputLength - binaryAsString.length()));
        builder.append(binaryAsString);

        return builder.chars().map(ch -> ch - 48).toArray();
    }
}
