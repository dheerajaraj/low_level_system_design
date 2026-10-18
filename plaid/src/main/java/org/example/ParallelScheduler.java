package org.example;

public class ParallelScheduler extends Scheduler {
    @Override
    public int calculateJobsWithWorkers(int[][] pipeline, int timeLimit) {
        int totalJobs = 0;
        int limit = timeLimit;
        for (int[] stage : pipeline) {
            int numOfJobs = stage[0];
            int durationEachJob = stage[1];
            int workers = stage[2];

            int jobsPerWorker = numOfJobs / workers;
            int remainingJobs = numOfJobs % workers;
            int totalTimeRequired = jobsPerWorker * durationEachJob + ((remainingJobs > 0) ? durationEachJob : 0);

            int jobsInThisStage = (totalTimeRequired > limit) ? (limit / durationEachJob) * workers : numOfJobs;
            limit -= totalTimeRequired;
            totalJobs += (jobsInThisStage);
            if (limit < 0) {
                return totalJobs;
            }
        }
        return totalJobs;
    }
}
