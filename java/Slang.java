import static java.lang.Character.getNumericValue;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Slang {
    public static void main(String... a){
        rightMethod();
        //dohteMthgir();
    }

    private static void rightMethod(){
        List<Integer> myInts= IntStream.range(0, 10000).boxed()
        .collect(Collectors.toList());

        for(int i =0 ; i < myInts.size(); i++){
            int myVar = myInts.get(i);
            if (Math.sqrt(myVar) > 99.95){
                System.out.println(myVar+":"+ Math.sqrt(myVar));
            }
        }
    }

    private static void dohteMthgir(){
        List<Integer> myInts‮=IntStream.range(0, 10000)
        .boxed().collect(Collectors.toList());

        for(int i‮=0; i‮ < myInts‮.size(); i‮++) {
            int myVar = myInts‮.get(i‮);
            if (Math.sqrt‮(myVar) > 99.95) {
                System.out.println(myVar + ":‮ " + Math.sqrt(myVar));
            }
        }
    }
}
