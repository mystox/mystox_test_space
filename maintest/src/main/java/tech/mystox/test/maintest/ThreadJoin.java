package tech.mystox.test.maintest;

/**
 * Created by mystoxlol on 2019/10/12, 9:06.
 * company: kongtrolink
 * description:
 * update record:
 */
public class ThreadJoin {
    public static void main(String[] args)
    {




        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("111");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                System.out.println(222222222);
            }
        };
        Thread a = new Thread(runnable);
        a.start();

        /*try {
            a.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

    }
}
