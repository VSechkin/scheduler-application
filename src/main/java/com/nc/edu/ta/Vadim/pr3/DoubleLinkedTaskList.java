package com.nc.edu.ta.Vadim.pr3;

public class DoubleLinkedTaskList extends AbstractTaskList {

    Node head;
    Node end;

    public DoubleLinkedTaskList() {
        head = null;
        end = null;
        AbstractTaskList.countArrays++;
    }

    public class Node {
        private Task task;
        private Node next;
        private Node prev;

        public Node(Task task) {
            this.task = task;
        }

        public Task getTask() {
            return task;
        }
    }

    @Override
    public void add(Task task) {
        if (task != null) {
            Node tempNode = new Node(task);
            Node currentNode = head;
            if (head == null) {
                head = tempNode;
                countTasks++;
            } else if (head.next == null) {
                end = tempNode;
                head.next = end;
                end.prev = head;
                countTasks++;
            } else {
                end.next = tempNode;
                tempNode.prev = end;
                end = tempNode;
                countTasks++;
            }
        }
    }


    @Override
    public void remove(Task task) {
        if (task != null) {
            Node tempNode = new Node(task);
            Node currentNode = head;
            while (currentNode != null) {
                if (currentNode.getTask().equals(tempNode.getTask())) {
                    if (currentNode == head) {
                        head = currentNode.next;
                        head.prev = null;
                        countTasks--;

                    } else if (currentNode == end) {
                        end = currentNode.prev;
                        end.next = null;
                        countTasks--;


                    } else {
                        currentNode.prev.next = currentNode.next;
                        currentNode.next.prev = currentNode.prev;
                        countTasks--;

                    }
                }
                currentNode = currentNode.next;
            }
        }
    }

    @Override
    public Task getTask(int index) {
        Node currentNode = head;
        int count = 0;
        if (index < 0) {
            throw new IllegalArgumentException("The index must be greater than zero");
        }
        while (currentNode != null) {
            if (count == index) {
                return currentNode.getTask();
            }
            currentNode = currentNode.next;
            count++;
        }
        return null;
    }

    @Override
    public Task[] incoming(int from, int to) {
        Node currentNode = head;
        Task[] currentTasks = new Task[countTasks];
        int count = 0;
        while (currentNode != null) {
            if (currentNode.task != null && currentNode.task.isActive()) {
                if ((currentNode.task.getStartTime() > from && currentNode.task.getEndTime() <= to) || (currentNode.task.isRepeated()
                        && (currentNode.task.getStartTime() + currentNode.task.getRepeatInterval()) <= to)) {
                    count++;
                    currentTasks[count - 1] = currentNode.task;

                }
            }
            currentNode = currentNode.next;
        }
        Task[] tempTasks = new Task[count];
        for (int i = 0; i < tempTasks.length; i++) {
            tempTasks[i] = currentTasks[i];
        }
        return tempTasks;
    }
}
