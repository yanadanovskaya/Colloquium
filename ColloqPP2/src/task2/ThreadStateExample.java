package task2;
public class ThreadStateExample {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            synchronized (ThreadStateExample.class) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println("Состояние потока перед запуском: " + thread.getState());

        thread.start();
        Thread.sleep(500);

        System.out.println("Состояние потока после запуска: " + thread.getState());

        Thread.sleep(3000);

        System.out.println("Состояние потока во время выполнения: " + thread.getState());
    }
}
