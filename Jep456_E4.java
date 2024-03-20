public class Jep456_E4 {
    static sealed abstract class Color permits White, Black {
    }

    static final class White extends Color {
    }

    static final class Black extends Color {
    }


    public static void main(String[] args) {
        oldSwitchCode();//White
        newSwitchCode();//White
    }

    private static void oldSwitchCode() {
        Color color = new White();
        switch (color) {
            case White unusedWhite -> System.out.println("White");
            case Black unusedBlack -> System.out.println("Black");
        }
    }

    private static void newSwitchCode() {
        Color color = new White();
        switch (color) {
            case White _ -> System.out.println("White");
            case Black _ -> System.out.println("Black");
        }
    }
}
