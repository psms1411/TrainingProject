import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by pooja.sharma on 01/11/17.
 */
public class BlockingQueueExample {


    public static void main(String[] args) {

        BlockingQueue<DataType> bQ = new ArrayBlockingQueue<DataType>(5);
        int maxLimit = 20;

        Thread th1 = new Thread(new Producer(bQ, maxLimit));
        Thread th2 = new Thread(new Consumer(bQ, maxLimit, th1));


        th1.start();
        th2.start();


    }

}

//Reentrant lock
class DataType {
    int x;
    int y;

    DataType(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

 class Consumer implements Runnable{

     private BlockingQueue queue;
     private int maxLimit;
     Thread th;

     Consumer(BlockingQueue queue, int maxLimit, Thread th) {
         this.queue = queue;
         this.maxLimit = maxLimit;
         this.th = th;
     }

     @Override
     public void run() {

         try {
             int i=0;
             while(i<maxLimit) {
                DataType d1 = (DataType) queue.take();
                 Thread.sleep(1000);
                System.out.println("Consumer consuming: "+d1.x+" "+d1.y) ;
                 i++;
             }

         } catch (InterruptedException e) {
             e.printStackTrace();
         }
     }
 }

class Producer implements Runnable{

    private BlockingQueue queue;
    private int maxLimit;

    Producer(BlockingQueue queue, int maxLimit) {
        this.queue = queue;
        this.maxLimit = maxLimit;
    }

    @Override
    public void run() {
        try {
            for(int i=0; i<maxLimit; i++) {
                DataType d1= new DataType(i, i+1);
                System.out.println("Producer inseting: "+d1.x+" "+d1.y) ;
                queue.put(d1);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
