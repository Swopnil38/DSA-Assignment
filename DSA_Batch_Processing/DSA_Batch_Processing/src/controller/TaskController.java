package controller;

import Database.DatabaseConnection;
import model.JobModel;
import model.JobTaskModel;
import model.TaskModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskController {
    DatabaseConnection db = new DatabaseConnection();

    public int addTask(TaskModel taskModel){
        try{
            String query ="insert into Jobtask(task,taskId) values(?,?)";

            PreparedStatement st = db.connection.prepareStatement(query);
            st.setString(1,taskModel.getTask());
            st.setInt(2,taskModel.getTaskId());

            return db.manipulate(st);

        }catch (SQLException e){
            e.printStackTrace();
            return 0;
        }
    }

    public int addJob(JobModel jobModel){
        try{
            String query="insert into Jobtable(jobId, jobName,jobProfit, deadline, numOfTask) values(?,?,?,?,?)";

            PreparedStatement st= db.connection.prepareStatement(query);
            st.setInt(1, jobModel.getJobId());
            st.setString(2,jobModel.getJobName());
            st.setInt(3,jobModel.getProfit());
            st.setInt(4,jobModel.getTime());
            st.setInt(5,jobModel.getNumOfTask());

            return db.manipulate(st);
        }catch(SQLException e){
            e.printStackTrace();;
            return 0;
        }
    }

    public ArrayList<Integer> fetchTask() {
        TaskModel taskModel = null;
        ArrayList<Integer> idList = new ArrayList<>();

        try {
            String query = "select * from Jobtask";
            PreparedStatement ps = db.connection.prepareStatement(query);
            ResultSet resultSet = db.retrieve(ps);

            while (resultSet.next()) {
                idList.add(resultSet.getInt("taskId"));
                taskModel = new TaskModel();
                taskModel.setTaskId(resultSet.getInt("taskId"));
                taskModel.setTask(resultSet.getString("task"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idList;
    }

    public int addJobTask(JobTaskModel jobTaskModel){
        try{
            String query="insert into JobTasktable(jobId, sourcetask, destinationtask) values(?,?,?)";
            PreparedStatement st= db.connection.prepareStatement(query);
            st.setInt(1,jobTaskModel.getJobId());
            st.setInt(2,jobTaskModel.getSource());
            st.setInt(3,jobTaskModel.getDestination());
            return db.manipulate(st);
        }catch(SQLException e){
            e.printStackTrace();;
            return 0;
        }
    }

    public JobModel fetchJobBYId(Integer id){
        JobModel jobModel = null;

        try {
            String query = "select * from Jobtable where jobId=?";
            PreparedStatement ps = db.connection.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet resultSet = db.retrieve(ps);

            while (resultSet.next()) {

                jobModel = new JobModel();
                jobModel.setJobId(resultSet.getInt("jobId"));
                jobModel.setJobName(resultSet.getString("jobName"));
                jobModel.setNumOfTask(resultSet.getInt("numOfTask"));
                jobModel.setTime(resultSet.getInt("deadline"));
                jobModel.setProfit(resultSet.getInt("jobProfit"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobModel;
    }
    public ArrayList<JobModel> fetchJob() {
        JobModel jobModel = null;
        ArrayList<Integer> idList = new ArrayList<>();
        ArrayList<JobModel> job = new ArrayList<>();

        try {
            String query = "select * from Jobtable";
            PreparedStatement ps = db.connection.prepareStatement(query);
            ResultSet resultSet = db.retrieve(ps);

            while (resultSet.next()) {
                idList.add(resultSet.getInt("jobId"));
                jobModel = new JobModel();
                jobModel.setJobId(resultSet.getInt("jobId"));
                jobModel.setJobName(resultSet.getString("jobName"));
                jobModel.setNumOfTask(resultSet.getInt("numOfTask"));
                jobModel.setProfit(resultSet.getInt("jobProfit"));
                jobModel.setTime(resultSet.getInt("deadline"));

                job.add(jobModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return job;
    }

    public ArrayList<JobTaskModel> fetchTaskModels(int jobId){
        ArrayList<JobTaskModel> list = new ArrayList<>();
        try{
            String query = "select * from Jobtasktable where jobId=?";
            PreparedStatement ps = db.connection.prepareStatement(query);
            ps.setInt(1,jobId);
            ResultSet resultSet = db.retrieve(ps);

            while (resultSet.next()){
                JobTaskModel jtm = new JobTaskModel();
                jtm.setJobId(resultSet.getInt("jobId"));
                jtm.setSource(resultSet.getInt("sourcetask"));
                jtm.setDestination(resultSet.getInt("destinationtask"));
                list.add(jtm);

            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public TaskModel getTaskById(Integer id){
        TaskModel taskModel = new TaskModel();
        try{
            String query = "select * from Jobtask where taskId=?";
            PreparedStatement ps= db.connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet resultSet = db.retrieve(ps);
            while(resultSet.next()){
                taskModel.setTaskId(resultSet.getInt("taskId"));
                taskModel.setTask(resultSet.getString("task"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return taskModel;
    }
    public List<JobModel> getAllJob(){
        ArrayList<JobModel> jobs= new ArrayList<>();

        try{
            String query="select * from Jobtable";
            PreparedStatement ps = db.connection.prepareStatement(query);
            ResultSet resultSet = db.retrieve(ps);
            while (resultSet.next()){
                JobModel j = new JobModel();
                j.setJobId(resultSet.getInt("jobId"));
                j.setJobName(resultSet.getString("jobName"));
                j.setNumOfTask(resultSet.getInt("numOfTask"));
                j.setProfit(resultSet.getInt("jobProfit"));
                j.setTime(resultSet.getInt("deadline"));
                jobs.add(j);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return jobs;
    }



}
