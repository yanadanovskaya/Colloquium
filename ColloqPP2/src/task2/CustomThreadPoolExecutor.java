package task2;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Repeat {
    int value() default 1;
}

class CustomThreadPoolExecutor extends ThreadPoolExecutor {
    public CustomThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    public void execute(Runnable command) {
        if (command instanceof RepeatableRunnable) {
            RepeatableRunnable repeatableRunnable = (RepeatableRunnable) command;
            int repeatCount = repeatableRunnable.getRepeatCount();
            for (int i = 0; i < repeatCount; i++) {
                super.execute(repeatableRunnable);
            }
        } else {
            super.execute(command);
        }
    }
}

interface RepeatableRunnable extends Runnable {
    default int getRepeatCount() {
        Repeat repeatAnnotation = this.getClass().getAnnotation(Repeat.class);
        return repeatAnnotation != null ? repeatAnnotation.value() : 1;
    }
}

class MyRunnable implements RepeatableRunnable {
    @Override
    @Repeat(3)
    public void run() {
        System.out.println("Hello, world!");
    }
}

