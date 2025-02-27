import java.util.Arrays;
import java.util.Scanner;

class Job {
    int id, deadline, profit;
    public Job(int id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class GreedyJobScheduling {

    public static void greedyJobScheduling(Job jobs[], int n) {
        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);
        int maxDeadline = 0;
        for (int i = 0; i < n; i++) {
            if (jobs[i].deadline > maxDeadline) {
                maxDeadline = jobs[i].deadline;
            }
        }

        int[] schedule = new int[maxDeadline];
        Arrays.fill(schedule, -1);

        for (int i = 0; i < n; i++) {
            for (int j = Math.min(jobs[i].deadline - 1, maxDeadline - 1); j >= 0; j--) {
                if (schedule[j] == -1) {
                    schedule[j] = jobs[i].id;
                    break;
                }
            }
        }
        System.out.println("Selected jobs for maximum profit:");
        for (int i = 0; i < maxDeadline; i++) {
            if (schedule[i] != -1) {
                System.out.println("Job " + schedule[i] + " ");
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of jobs: ");
        int n = sc.nextInt();
        Job[] jobs = new Job[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Enter details for Job " + (i + 1) + " (Deadline Profit): ");
            int deadline = sc.nextInt();
            int profit = sc.nextInt();
            jobs[i] = new Job(i + 1, deadline, profit); 
        }

        greedyJobScheduling(jobs, n);
        sc.close();
    }
}
