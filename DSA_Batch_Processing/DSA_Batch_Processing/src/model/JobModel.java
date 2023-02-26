package model;

public class JobModel {
    private int jobId;
    private String jobName;
    private int numOfTask;
    private int time;
    private int profit;

    public JobModel(){

    }
    public JobModel(int jobId, String jobName, int numOfTask, int time, int profit){
        this.jobId=jobId;
        this.jobName=jobName;
        this.numOfTask=numOfTask;
        this.time=time;
        this.profit=profit;
    }

    public void setJobId(int jobId){
        this.jobId=jobId;
    }

    public void setJobName(String jobName){
        this.jobName=jobName;
    }

    public void setNumOfTask(int numOfTask) {
        this.numOfTask = numOfTask;
    }

    public int getJobId(){
        return this.jobId;
    }

    public String getJobName(){
        return this.jobName;
    }

    public int getNumOfTask() {
        return numOfTask;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }
}
