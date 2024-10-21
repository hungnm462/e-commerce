package vn.gs.notify.scheduler;

import org.springframework.stereotype.Component;

@Component
public class ExampleSchedule {
    // @Scheduled(fixedDelay = 1000, initialDelay = 1000)
    public void scheduleFixedRateWithInitialDelayTask() {

        long now = System.currentTimeMillis() / 1000;
        System.out.println(
                "Fixed rate task with one second initial delay - " + now);
    }
}
