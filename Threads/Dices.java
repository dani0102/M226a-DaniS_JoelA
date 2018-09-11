package Threads;

import java.util.Random;

public class Dices implements Runnable {
  //runnable contains run() only

  String name;
  int time;
  int dicenumb;
  Random rtime = new Random();
  Random rdicenumb = new Random();

  public Dices(String x){
    name = x;
    time = rtime.nextInt(4000);
    dicenumb = rdicenumb.nextInt(6);
  }

  public void run(){
    try{

      System.out.printf("%s is being rolled. [time needed: %d ms]\n", name, time);
      Thread.sleep(time);
      System.out.printf(" %s done with result: %s\n", name, dicenumb + 1);

    } catch (Exception e){

    }
  }

}

