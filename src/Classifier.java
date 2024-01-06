import java.util.Random;

public class Classifier {
    private final int length;
    private int[] input;
    private int[] output;

    private final Random random = new Random();

    public Classifier(int length, int[] output) {
        this.length = length;
        this.input = generateInput();
        this.output = output;
    }

    private int[] generateInput() {
        int[] array = new int[length];

        for (int i = 0; i < length; i++) {
            array[i] = random.nextInt(3) - 1;
        }

        return array;
    }

    public boolean countCompliance(int[] output) {
        if (output.length != length) {
            System.out.println(output.length + " " + length);
        }

        for (int i = 0; i < length; i++) {
            if(output[i] != input[i] && input[i] != -1) {
                return false;
            }
        }

        return true;
    }

    public int[] getOutput() {
        return output;
    }

    public void setOutput(int[] output) {
        this.output = output;
    }

    public void setInput(int[] input) {
        this.input = input;
    }
}
