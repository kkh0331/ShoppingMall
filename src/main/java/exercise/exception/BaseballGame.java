package exercise.exception;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BaseballGame {
    public static void main(String[] args) throws IOException {
        Game game = new Game();
        game.run();
    }

}

class Game{

    private List<Integer> baseNumbers;
    private final int LENGTH = 3;

    public Game(){
        baseNumbers = new ArrayList<>();
        setBaseNumbers();
    }

    private void setBaseNumbers(){
        Random random = new Random();
        while(baseNumbers.size() != 3){
            int number = random.nextInt(10);
            if(!baseNumbers.contains(number)){
                baseNumbers.add(number);
            }
        }
    }

    public void run() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=10; i>=1; i--){
            System.out.printf("기회가 %d번 남았습니다.\n", i);
            //사용자 입력
            InputDTO inputDTO = inputNumbers(br);
            if(inputDTO.isDouble()) continue;
            List<Integer> inputs = inputDTO.getResInputs();
            String res = result(inputs);
            System.out.println(res);
            if(res.trim().equals("3 Strike")){
                System.out.println("정답!! 당신이 승리하였습니다.");
                return;
            }
        }
        System.out.println("횟수가 초과되었습니다.");
        System.out.println("기본 숫자들 : "+baseNumbers);
    }

    private String result(List<Integer> inputs){
        int strike = 0;
        int ball = 0;
        int out = 0;
        for(int i=0; i<inputs.size(); i++){
            int number = inputs.get(i);
            if(baseNumbers.contains(number)){
                if(baseNumbers.get(i) == number){
                    strike++;
                } else {
                    ball++;
                }
            } else {
                out++;
            }
        }
        StringBuilder sb = new StringBuilder();
        if(strike != 0) sb.append(strike+" Strike  ");
        if(ball != 0) sb.append(ball+" Ball  ");
        if(out != 0 ) sb.append(out+" Out  ");
        return sb.toString();
    }


    private InputDTO inputNumbers(BufferedReader br) throws IOException {
        List<Integer> resInputs = new ArrayList<>();
        boolean isDouble = false;
        while(resInputs.size() != LENGTH){
            resInputs.clear();
            System.out.print("3개의 숫자를 입력해 주세요. ex. 5 8 2 : ");
            String[] inputs = br.readLine().split(" ");
            if(inputs.length != LENGTH){
                System.out.printf("숫자는 %d개만 가능합니다.\n", LENGTH);
                continue;
            }
            for(int i=0; i<LENGTH; i++){
                int input;
                try{
                    input = Integer.parseInt(inputs[i]);
                } catch(NumberFormatException e){
                    System.out.println("숫자만 입력 가능합니다.");
                    break;
                }
                if(0 > input || input > 9){
                    System.out.println("각 숫자의 범위는 0부터 9까지 입니다.");
                    break;
                }
                if(resInputs.contains(input)){
                    System.out.println("중복 숫자는 불가능합니다.");
                    isDouble = true;
                    break;
                }
                resInputs.add(input);
            }
            if(isDouble) break;
        }
        return new InputDTO(isDouble, resInputs);
    }
}

class InputDTO{
    private boolean isDouble;
    private List<Integer> resInputs;

    public InputDTO(boolean isDouble, List<Integer> resInputs) {
        this.isDouble = isDouble;
        this.resInputs = resInputs;
    }

    public boolean isDouble() {
        return isDouble;
    }

    public List<Integer> getResInputs() {
        return resInputs;
    }
}
