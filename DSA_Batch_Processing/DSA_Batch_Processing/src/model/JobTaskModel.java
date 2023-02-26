package model;

public class JobTaskModel {

    private int jobId;
    private int source;
    private int destination;

    public JobTaskModel(){

    }

    public JobTaskModel(int jobId, int source, int destination){
        this.jobId=jobId;
        this.source=source;
        this.destination=destination;
    }

    public void setJobId(int jobId){
        this.jobId=jobId;
    }

    public void setSource(int source){
        this.source=source;
    }

    public void setDestination(int destination){
        this.destination=destination;
    }

    public int getJobId(){
        return this.jobId;
    }

    public int getSource(){
        return this.source;
    }

    public int getDestination(){
        return this.destination;
    }
}
