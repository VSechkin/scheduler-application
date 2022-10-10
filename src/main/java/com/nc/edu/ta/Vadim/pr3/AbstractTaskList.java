package com.nc.edu.ta.Vadim.pr3;

public abstract class AbstractTaskList {
    protected int countTasks = 0;
    protected static int countArrays = 0;

    public abstract void add(Task task);

    public abstract void remove(Task task);

    public abstract Task getTask(int index);

    public int size() {
        return countTasks;
    }

    public abstract Task[] incoming(int from, int to);
}
