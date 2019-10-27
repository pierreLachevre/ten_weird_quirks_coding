import static java.lang.Character.getNumericValue;

public class Slang {
    public static void main(String... a){
        rightMethod()
        dohteMthgir();
    }

    private static void rightMethod(){
        for(char c = 1; c > 0; c++){
            if(getNumericValue(c) > 1000) {
                System.out.println(c+ ": "+ getNumericValue(c));
            }
        }
    }

    private static void dohteMthgir(){
        for (char c‮ = 1; c‮ > 0; c‮++) {
            if (getNumericValue(c‮) > 1000) {
                System.out.println(c‮ + ": " + getNumericValue(c‮));
            }
        }
    }
}
