import static java.lang.Character.getNumericValue;

public class BigCharacters {
    public static void main(String... a‮) {
        dohteMthgir();
    }

    private static void dohteMthgir(){
        for (char c‮ = 1; c‮ > 0; c‮++) {
            if (getNumericValue(c‮) > 1000) {
                System.out.println(c‮ + ": " + getNumericValue(c‮));
            }
        }
    }
}
