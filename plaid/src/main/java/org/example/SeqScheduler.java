package org.example;

public class SeqScheduler extends Scheduler{
    @Override
    public int calculateJobsWithWorkers(int[][] pipeline, int timeLimit) {
        int totalJobs = 0;
        int limit = timeLimit;
        for (int[] stage : pipeline) {
            int numOfJobs = stage[0];
            int durationEachJob = stage[1];
            int jobsInThisStage = ((numOfJobs * durationEachJob) > limit) ? (limit / durationEachJob) : numOfJobs;
            limit -= numOfJobs * durationEachJob;
            totalJobs += (jobsInThisStage);
            if (limit < 0) {
                return totalJobs;
            }
        }
        return totalJobs;
    }
}
