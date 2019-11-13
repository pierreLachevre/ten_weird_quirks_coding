import static java.lang.Character.getNumericValue;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Slang {
    public static void main(String... a){
        //rightMethod();
        dohteMthgir();
    }

    private static void rightMethod(){
        List<Integer> myInts= IntStream.range(0, 10000).boxed()
        .collect(Collectors.toList());

        for(int i : myInts){
            if (Math.sqrt(i) > 99.95){
                System.out.println(i+":"+ Math.sqrt(i));
            }
        }
    }

    private static void dohteMthgir(){
        List<Integer> myInts‮=IntStream.range(0, 10000)
        .boxed().collect(Collectors.toList());

        for(int i‮ : myInts‮ ){
            if (Math.sqrt(i‮) > 99.95 ){
                System.out.println(i‮ + ": " + Math.sqrt(i‮));
            }
        }
    }
}
