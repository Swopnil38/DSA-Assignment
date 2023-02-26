package model;

public class TaskModel {

    String task;
    int taskId;

    public TaskModel(){

    }

   public TaskModel(String task, int taskId){
        this.task=task;
        this.taskId=taskId;
    }


   public void setTask(String task){
        this.task=task;
    }

    public void setTaskId(int taskId){
        this.taskId=taskId;
    }

    public String getTask(){
        return this.task;
    }

   public int getTaskId(){
        return this.taskId;
    }

}
