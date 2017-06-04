package com.render.Thread;

/**
 * Created by yg on 2017/3/7.
 */
public class PrintThread extends Thread {

    @Override
    public void run(){
        System.out.println(getName() + " is running.");
        try{
            Thread.sleep(5000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(getName() + " is running again.");
    }

}
