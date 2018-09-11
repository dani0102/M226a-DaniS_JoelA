package Threads;

//import java.util.Scanner;

public class Main {

public static void executeThreads(){

  Thread thread1 = new Thread(new Dices("Dice one"));
  Thread thread2 = new Thread(new Dices("Dice two"));
  Thread thread3 = new Thread(new Dices("Dice three"));
  Thread thread4 = new Thread(new Dices("Dice four"));

  thread1.start();
  thread2.start();
  thread3.start();
  thread4.start();

}

public static void main(String[] args){

  /*boolean loop = true;

  while(loop == true){

    System.out.println("Start? [y/n]");
    Scanner answer = new Scanner(System.in);
    String inputAnswer = answer.nextLine();

    System.out.println(inputAnswer);

    if(inputAnswer.equals("y") || inputAnswer.equals("Y") || inputAnswer.equals("yes") || inputAnswer.equals("Yes") || inputAnswer.equals("YES")) {
      loop = false;*/
      executeThreads();
    /*}

  }*/

}

}

