package tech.mystox.test.zookeeper;

/**
 * Created by mystox on 2022/8/8, 17:55.
 * company:
 * description:
 * update record:
 */
public class ExecutorTest {
    public static void main(String[] args) {
        //        if (args.length < 4) {
        //            System.err
        //                    .println("USAGE: Executor hostPort znode filename program [args ...]");
        //            System.exit(2);
        //        }
        //        String hostPort = args[0];
        //        String znode = args[1];
        //        String filename = args[2];
        //        String exec[] = new String[args.length - 3];
        //        System.arraycopy(args, 3, exec, 0, exec.length);


        String hostPort = "192.168.0.201:2181";
        String znode = "/test";
        String filename = "a";
        String exec[] = {"set","/javatest"};
        //        System.arraycopy(args, 3, exec, 0, exec.length);
        try {
//            Executor b = new Executor(hostPort, znode, "b", exec);
//            b.run();
            Executor a = new Executor(hostPort, znode, "a", exec);
            a.run();
            Thread.sleep(10000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
