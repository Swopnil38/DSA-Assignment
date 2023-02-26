package JobSheduling;

import java.util.*;

public class SequencingJob {

    public List<Integer> scheduling(ArrayList<JobModel> js){


        Compare cs = new Compare();
        Collections.sort(js,cs);
        // Create list of scheduled jobs and set of used deadlines
        List<Integer> scheduledJobs = new ArrayList<Integer>();
        Set<Integer> usedDeadlines = new HashSet<>();

        // Iterate over jobs and select jobs with available deadlines
        for (JobModel job : js) {
            for (int i = job.getDeadline(); i >= 1; i--) {
                if (!usedDeadlines.contains(i)) {
                    scheduledJobs.add(job.getJobId());
                    usedDeadlines.add(i);
                    break;
                }
            }
        }

        // Print scheduled jobs
        System.out.println("Scheduled jobs: " + scheduledJobs);
        return scheduledJobs;


    }

    public static void main(String[] args) {
        JobModel p1 = new JobModel(11,70,2);
        JobModel p2 = new JobModel(12,100,1);
        JobModel p3 = new JobModel(13,20,3);
        JobModel p4 = new JobModel(14,40,2);
        JobModel p5 = new JobModel(15,20,1);

        ArrayList<JobModel> input = new ArrayList<JobModel>();
        input.add(p1);
        input.add(p2);
        input.add(p3);
        input.add(p4);
        input.add(p5);
        SequencingJob s = new SequencingJob();
        s.scheduling(input);
    }
}
