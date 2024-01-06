import java.util.ArrayList;
import java.util.List;

public class Simulation extends SimulationUtil {
    protected static int length;
    protected static int nrOfClassifiers;
    protected static int iterationBoundary;
    protected static double theoreticalValue;

    protected static void initSimulation(int length, int nrOfClassifiers) {
        Simulation.length = length;
        Simulation.nrOfClassifiers = nrOfClassifiers;
        iterationBoundary = nrOfClassifiers;
        theoreticalValue = calculateTheory();
        initClassifiers(length, nrOfClassifiers);
    }

    protected static double calculateTheory() {
        double x = (Math.pow(3, length) - Math.pow(2, length))/Math.pow(3, length);
        double a = 1 - Math.pow(x, nrOfClassifiers);
        double b = 1 - Math.pow(x, nrOfClassifiers - 1);

        double sum = 0;
        for (int i = 1; i < nrOfClassifiers; i++) {
            sum += Math.pow(b, i + 1) * getProduct(i) * i/(nrOfClassifiers - 1);
        }

        return a*sum;
    }

    private static double getProduct(int i) {
        double product = 1;
        for (int j = 1;j < i + 1; j++) {
            product *= (double) (nrOfClassifiers - j)/(nrOfClassifiers - 1);
        }
        return product;
    }

    protected static int simulate() {
        int[] message = getRandomOutput(length);

        List<Classifier> winners = new ArrayList<>();

        for (Classifier classifier : classifiers) {
            if (classifier.countCompliance(message)) {
                winners.add(classifier);
                break;
            }
        }

        if (winners.isEmpty()) {
            return 0;
        }

        for (int i = 0; i < iterationBoundary + 1; i++) {
            message = winners.get(winners.size() - 1).getOutput();
            shuffle(nrOfClassifiers);

            for (Classifier classifier : classifiers) {
                classifier.setInput(getRandomInput(length));
            }

            int winnersSize = winners.size();
            for (Classifier classifier : classifiers) {
                if (!classifier.equals(winners.get(winners.size() - 1)) && classifier.countCompliance(message)) {
                    if (winners.contains(classifier)) {
                        return 1;
                    }

                    winners.add(classifier);
                    break;
                }
            }

            if (winnersSize == winners.size()) {
                return 0;
            }
        }

        return 0;
    }

    protected static void updateClassifiers() {
        for (Classifier classifier : classifiers) {
            classifier.setInput(getRandomInput(length));
            classifier.setOutput(getRandomOutput(length));
        }
    }
}
