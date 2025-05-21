package elocindev.eldritch_end;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public interface WorldSchedulerEldritch {
    long getSchedulerTimeEldritch();

    Map<Long, List<Runnable>> getScheduledTasksEldritch();

    default void scheduleEldritch(int ticks, Runnable task) {
        if (ticks <= 0) {
            task.run();
        } else {
            long executionTime = this.getSchedulerTimeEldritch() + (long)ticks;
            List<Runnable> list = (List)this.getScheduledTasksEldritch().getOrDefault(executionTime, new ArrayList());
            list.add(task);
            this.getScheduledTasksEldritch().put(executionTime, list);
        }
    }

    default void updateScheduledTasksEldritch() {
        Map<Long, List<Runnable>> taskQueue = this.getScheduledTasksEldritch();
        if (!taskQueue.isEmpty()) {
            long currentTime = this.getSchedulerTimeEldritch();
            List<Runnable> currentTasks = (List)taskQueue.get(currentTime);
            if (currentTasks != null) {
                Iterator var5 = currentTasks.iterator();

                while(var5.hasNext()) {
                    Runnable task = (Runnable)var5.next();
                    task.run();
                }

                taskQueue.remove(currentTime);
            }

        }
    }
}
