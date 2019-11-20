
public class IntegerEquality{
    public static void main(String[] args) {
        firstEquality();
        secondEquality();
    }

    private static void firstEquality(){
        Integer a = 42;
        Integer b = 42;
        System.out.println(a == b);
    }

    private static void secondEquality(){
        Integer c = 404;
        Integer d = 404;
        System.out.println(c == d);
    }
}


