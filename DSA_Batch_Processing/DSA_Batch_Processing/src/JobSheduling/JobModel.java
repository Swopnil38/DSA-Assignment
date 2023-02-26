package JobSheduling;

public class JobModel {
    private int profit;
    private int deadline;
    private int jobId;

    public JobModel(int jobId, int profit, int deadline){
        this.jobId=jobId;
        this.profit=profit;
        this.deadline=deadline;
    }


    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }
}
