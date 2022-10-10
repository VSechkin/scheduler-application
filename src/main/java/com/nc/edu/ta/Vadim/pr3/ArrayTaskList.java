package com.nc.edu.ta.Vadim.pr3;

public class ArrayTaskList extends AbstractTaskList {
    public static final String NAME_TASK = "[EDUCTR][TA].";
    private static final int ARRAY_SIZE = 7;
    Task[] tasks;

    public ArrayTaskList() {
        tasks = new Task[ARRAY_SIZE];
        AbstractTaskList.countArrays++;
    }

    @Override
    public void add(Task task) {
        if (task != null) {
            for (int i = 0; i < tasks.length; i++)
                if (tasks[i] == null) {
                    tasks[i] = task;
                    countTasks++;
                    break;
                } else if (i + 1 == tasks.length && tasks[i] != null) {
                    Task[] tempTasks = new Task[i + 2];
                    for (int j = 0; j < tasks.length; j++) {
                        tempTasks[j] = tasks[j];
                    }
                    tempTasks[i + 1] = task;
                    countTasks++;
                    this.tasks = new Task[i + 1 + ARRAY_SIZE];
                    for (int j = 0; j < tempTasks.length; j++) {
                        tasks[j] = tempTasks[j];
                    }
                    break;
                }
        }
    }

    @Override
    public void remove(Task task) {
        if (task != null) {
            for (int i = 0; i < tasks.length; i++) {
                if (tasks[i].equals(task)) {
                    tasks[i] = null;
                    for (int j = i; j < tasks.length; j++) {
                        if(j + 1 == countTasks){
                            break;
                        }
                        tasks[j] = tasks[j + 1];
                        if (tasks[j + 1] == null) {
                            break;
                        }
                    }
                    countTasks--;
                    Task[] tempTasks = new Task[countTasks];
                    for (int j = 0; j < countTasks; j++) {
                        tempTasks[j] = tasks[j];
                    }
                    this.tasks = new Task[countTasks];
                    for (int j = 0; j < tempTasks.length; j++) {
                        tasks[j] = tempTasks[j];

                    }
                    i--;
                }
            }
        } else {
            System.out.println("Task is inactive");
        }
    }

    @Override
    public Task getTask(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("The index must be greater than zero");
        }
        for (int i = 0; i < tasks.length; i++) {
            if (i == index) {
                return tasks[i];
            }
        }
        return tasks[0];
    }

    @Override
    public Task[] incoming(int from, int to) {
        int count = 0;
        Task[] tempTasks = new Task[tasks.length];
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] != null && tasks[i].isActive()) {
                if ((tasks[i].getStartTime() > from && tasks[i].getEndTime() <= to) || (tasks[i].isRepeated()
                        && (tasks[i].getStartTime() + tasks[i].getRepeatInterval()) <= to)) {
                    count++;
                    tempTasks[i] = tasks[i];
                }
            }
        }
        Task[] currentTasks = new Task[count];
        int j = 0;
        for(int i = 0; i < tempTasks.length; i++)
        {
            if(tempTasks[i] != null){
                currentTasks[i - j] = tempTasks[i];
            } else j++;
        }
        return currentTasks;
    }
}
