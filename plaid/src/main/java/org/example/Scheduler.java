package org.example;

import java.util.PriorityQueue;

public abstract class Scheduler {

    abstract public int calculateJobsWithWorkers(int[][] pipeline, int timeLimit);

    public int calculateStageDuration(int[] jobDuration, int numWorkers) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int time = -1;
        for (int i = 0; i < jobDuration.length; i++) {
            if (numWorkers > 0) {
                pq.offer(jobDuration[i]);
                numWorkers--;
            } else {
                time = pq.poll();
                pq.offer(time + jobDuration[i]);
            }
        }
        while (!pq.isEmpty()) {
            time = pq.poll();
        }
        return time;
    }

}
