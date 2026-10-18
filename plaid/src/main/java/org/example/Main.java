package org.example;


/**
 * The interview consists of two rounds: the first round is coding, and the second round involves preparing a project deep dive PowerPoint presentation.
 *
 * Plaid Coding Interview Experience - Problem: automation pipeline, with 3 milestones progressively adding complexity.
 *
 * Milestone 1: Single worker, calculate how many jobs can be completed within a time limit.
 *
 * The pipeline consists of multiple stages executed sequentially. Each stage format:
 *
 * [number_of_jobs, job_time]
 *
 * Example:
 *
 * pipeline = [[4, 3], [10, 1]]
 * time_limit = 14
 *
 * In the first stage, 4 jobs each take 3 minutes, totaling 12 minutes; with 2 minutes remaining, 2 jobs in the second stage can be completed, so the return value is 6.
 *
 * Interface:
 *
 * def calculate_jobs_completed(pipeline, time_limit):
 *     ...
 *
 * Note that only fully completed jobs count; partially completed jobs do not.
 *
 * Milestone 2: Multiple workers per stage added.
 *
 * Each stage format becomes:
 *
 * [number_of_jobs, job_time, number_of_workers]
 *
 * Workers in the same stage can process jobs in parallel, but:
 *
 * - One job can only be handled by one worker and cannot be split.
 * - One worker can only process one job at a time.
 * - Stages remain sequential.
 * - Only fully completed jobs count.
 *
 * Example:
 *
 * pipeline = [[4, 3, 2], [10, 1, 1]]
 * time_limit = 14
 *
 * In the first stage, with 2 workers and 4 jobs each taking 3 minutes, it takes 6 minutes total. The remaining 8 minutes allow the single worker in the second stage to complete 8 jobs, totaling 12 jobs returned.
 *
 * Interface:
 *
 * def calculate_jobs_with_workers(pipeline, time_limit):
 *     ...
 *
 * Milestone 3: Job durations vary within the same stage.
 *
 * Input:
 *
 * job_durations: list[int]
 * num_workers: int
 *
 * Jobs are assigned in order:
 *
 * - Initially assign jobs to free workers.
 * - When a worker finishes, immediately assign the next unassigned job.
 * - Jobs cannot be split across workers.
 * - Return total time to complete all jobs in the stage (makespan).
 * - Return 0 if there are no jobs.
 *
 * Interface:
 *
 * def calculate_stage_duration(job_durations, num_workers):
 *     ...
 *
 * Example:
 *
 * job_durations = [5, 2, 10, 4, 8]
 * num_workers = 2
 *
 * Execution process:
 *
 * T=0: worker1 does 5, worker2 does 2
 * T=2: worker2 does 10
 * T=5: worker1 does 4
 * T=9: worker1 does 8
 * T=12: worker2 free
 * T=17: worker1 finishes
 *
 * Return 17.
 *
 * A natural solution uses a min heap to track each worker's current finishing time, always assigning the next job to the earliest available worker.
 *
 * Project Deep Dive:
 * Use Plaid's PowerPoint template to create slides introducing a project.
 *
 * Problem: Completed Jobs in a Sequential Automation Pipeline
 * Implement:
 *
 * def calculate_jobs_with_workers(pipeline, time_limit):
 *     pass
 * A pipeline consists of stages that must execute in order. Each stage is represented as:
 *
 * [number_of_jobs, job_time, number_of_workers]
 * Rules:
 *
 * Stages are strictly sequential: the next stage may start only after the current stage has fully completed.
 * Within a stage, workers may process jobs in parallel.
 * A job must be processed by exactly one worker and cannot be split.
 * Each worker can process at most one job at a time.
 * Only jobs fully completed by time_limit count.
 * Return the total number of completed jobs across the pipeline.
 * Example 1
 * pipeline = [[4, 3, 2], [10, 1, 1]]
 * time_limit = 14
 *
 * Output: 12
 * Stage 1 finishes all 4 jobs in 6 minutes. The remaining 8 minutes allow Stage 2 to complete 8 jobs, for a total of 12.
 *
 * Constraints
 * 0 <= len(pipeline) <= 10^5
 * 0 <= number_of_jobs <= 10^9
 * 1 <= job_time <= 10^9
 * 1 <= number_of_workers <= 10^9
 * 0 <= time_limit <= 10^18
 * Target complexity: O(number of stages).
 *
 *
 * Question 2
 * Practice this problem
 * EN
 * AI Insights
 * Min Heap
 * Priority Queue
 * Job Scheduling
 * Simulation
 *
 * Problem: Stage Makespan with Ordered Job Assignment
 * Implement:
 *
 * def calculate_stage_duration(job_durations, num_workers):
 *     pass
 * You are given job durations for one pipeline stage and a number of workers. All workers are idle at time 0. Jobs must be assigned in the order they appear in job_durations.
 *
 * Rules:
 *
 * Initially, assign jobs to available workers until either no worker or no job remains.
 * Whenever a worker finishes, immediately assign that worker the next unassigned job.
 * If multiple workers finish at the same time, they may all receive subsequent jobs at that time; jobs are still assigned in array order.
 * A job cannot be split across workers.
 * A worker can process at most one job at a time.
 * Return the time when all jobs are complete (the makespan).
 * Return 0 if there are no jobs.
 * Example
 * job_durations = [5, 2, 10, 4, 8]
 * num_workers = 2
 *
 * Output: 17
 * Constraints
 * 0 <= len(job_durations) <= 10^5
 * 1 <= job_durations[i] <= 10^9
 * 1 <= num_workers <= 10^5
 *
 */
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scheduler sch = new SeqScheduler();
        int[][] pipeline = {{4, 3}, {10, 1}};
        int result = sch.calculateJobsWithWorkers(pipeline, 14);

         System.out.println(String.format("Total number of jobs: %d", result));
         result = sch.calculateJobsWithWorkers(pipeline, 11);
         System.out.println(String.format("Total number of jobs: %d", result));
         result = sch.calculateJobsWithWorkers(pipeline, 12);
         System.out.println(String.format("Total number of jobs: %d", result));
         result = sch.calculateJobsWithWorkers(pipeline, 22);
         System.out.println(String.format("Total number of jobs: %d", result));
         result = sch.calculateJobsWithWorkers(pipeline, 0);
         System.out.println(String.format("Total number of jobs: %d", result));

        sch = new ParallelScheduler();

        pipeline = new int[][]{{4, 3, 2}, {10, 1, 1}};
        result = sch.calculateJobsWithWorkers(pipeline, 14);
        System.out.println(String.format("Total number of jobs: %d", result));
        pipeline = new int[][]{{4, 3, 3}, {10, 1, 1}};
        result = sch.calculateJobsWithWorkers(pipeline, 14);
        System.out.println(String.format("Total number of jobs: %d", result));
        pipeline = new int[][]{{4, 3, 3}, {10, 1, 1}};
        result = sch.calculateJobsWithWorkers(pipeline, 5);
        System.out.println(String.format("Total number of jobs: %d", result));
        pipeline = new int[][]{{4, 3, 3}, {10, 1, 1}};
        result = sch.calculateJobsWithWorkers(pipeline, 6);
        System.out.println(String.format("Total number of jobs: %d", result));
        pipeline = new int[][]{{4, 3, 2}, {10, 1, 1}};
        result = sch.calculateJobsWithWorkers(pipeline, 5);
        System.out.println(String.format("Total number of jobs: %d", result));
        pipeline = new int[][]{{4, 3, 2}, {10, 1, 1}};
        result = sch.calculateJobsWithWorkers(pipeline, 7);
        System.out.println(String.format("Total number of jobs: %d", result));

        int[] duration = {5, 2, 10, 4, 8};
        result = sch.calculateStageDuration(duration, 2);
        System.out.println(String.format("Total time taken: %d", result));
        duration = new int[]{5, 2, 10, 4, 8};
        result = sch.calculateStageDuration(duration, 3);
        System.out.println(String.format("Total time taken: %d", result));
        duration = new int[]{5, 2, 10, 4, 8};
        result = sch.calculateStageDuration(duration, 4);
        System.out.println(String.format("Total time taken: %d", result));
    }
}