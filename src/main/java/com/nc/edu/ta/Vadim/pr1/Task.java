package com.nc.edu.ta.Vadim.pr1;

import java.io.IOException;

public class Task {

    private String title;
    private boolean active;
    private int time;
    private int start;
    private int end;
    private int repeat;

    public Task(String title, int time) {

        active = false;
        this.title = title;
        this.time = time;
        this.start = time;
        this.end = time;

    }

    public Task(String title, int start, int end, int repeat) {

        active = false;
        this.title = title;
        this.start = start;
        this.end = end;
        this.repeat = repeat;
        this.time = start;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @param time must be greater than zero
     * @throws IOException When an input error occurs
     */


    public void setTime(int time) {
        if (time < 0) {
            throw new IllegalArgumentException("The time must be greater than zero");
        }
        this.time = time;
        this.start = time;
        this.end = time;
        this.repeat = 0;

    }

    /**
     * @param start, repeat must be greater than zero. start must be greater than end start plus repeat must be greater than end
     * @throws IOException When an input error occurs
     */


    public void setTime(int start, int end, int repeat) {
        if (start > 0 && start < end && repeat > 0 && (start + repeat) < end) {
            this.start = start;
            this.end = end;
            this.repeat = repeat;
            this.time = start;
        } else {
            throw new IllegalArgumentException
                    ("The start and repeat must be greater than zero or end must be be greater than start and start plus repeat must be greater than end");
        }

    }

    public int getTime() {
        return time;
    }

    public int getStartTime() {
        return start;
    }

    public int getEndTime() {
        return end;
    }

    public int getRepeatInterval() {
        return repeat;
    }

    public boolean isRepeated() {
        if (repeat > 0) {
            return true;
        }
        return false;
    }

    /**
     * This method returns a description of the task
     *
     * @return sentence
     */

    public String toString() {
        String sentence;
        if (!active) {
            sentence = "Task \"" + title + "\" is inactive";
        } else if (repeat == 0) {
            sentence = "Task \"" + title + "\" at " + time;
        } else {
            sentence = "Task \"" + title + "\" from " + start + " to " + end + " every " + repeat + " seconds";
        }
        return sentence;

    }

    /**
     * This method returns the time of the next notification
     *
     * @param time must be greater than zero
     * @throws IOException When an input error occurs
     */


    public int nextTimeAfter(int time) {
        if (time < 0) {
            throw new IllegalArgumentException("The time must be greater than zero");
        }
        int tempStart = this.start;
        if (active) {
            if (repeat == 0 && time < this.time) {
                return this.time;
            }
            if (time < tempStart) {
                return tempStart;
            }
            while (tempStart < end) {
                int segment;
                segment = tempStart + repeat;
                if (time < segment && segment < end) {
                    return segment;
                }
                tempStart = segment;
            }
        }
        return -1;
    }
}
