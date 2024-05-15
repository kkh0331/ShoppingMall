package exercise.etc;

public class EnumBeforeDemo {

    private final int menu;

    private static final int americano = 0;
    private static final int latte = 1;
    private static final int strawberryade = 2;
    private static final int espresso = 3;

    public EnumBeforeDemo(int menu) {
        this.menu = menu;
    }

    public void selectMenu() {
        switch (menu) {
            case americano: //0
                System.out.println("아메리카노");
                break;
            case latte: // 1
                System.out.println("라떼");
                break;
            case strawberryade: // 2
                System.out.println("딸기에이드");
                break;
            case espresso: // 3
                System.out.println("에스프레소");
                break;
        }
    }

}
