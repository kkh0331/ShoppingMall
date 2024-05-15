package exercise.etc;

public enum EnumAfterDemo {

    AMERICANO(0, "아메리카노"),
    LATTE(1, "라떼"),
    STRAWBERRY_ADE(2, "딸기에이드"),
    ESPRESSO(3, "에스프레소");

    private final int menuNum;
    private final String menuName;

    EnumAfterDemo(int menuNum, String menuName) {
        this.menuNum = menuNum;
        this.menuName = menuName;
    }

    void selectMenu(){
        System.out.println(menuName);
    }

}
