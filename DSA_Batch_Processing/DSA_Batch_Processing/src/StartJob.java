import JobSheduling.SequencingJob;
import controller.TaskController;
import model.JobModel;
import model.JobTaskModel;
import model.TaskModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StartJob  extends JFrame {

    int count = 0;
    TaskController taskController = new TaskController();
    JButton back;
    JPanel frame;
    JLabel jobs;
    JLabel selectJob;
    JButton jButton;
    JScrollPane jtf;
    String text="";
    JButton stop;
    ScrollableLabel t;
    StartJob(){
        setTitle("Start Job");
        setSize(1280, 720 );
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setBackground(new Color(0x9F7298));
        getContentPane();

        frame =new JPanel();
        frame.setBounds(300,50,550,550);
        frame.setBackground(Color.white);
        add(frame);

        initilize();
    }
    void initilize(){


        back = new JButton("Back");
        back.setBounds(0, 10, 70, 20);
        back.setFont(new Font("Roboto", Font.BOLD, 10));
        back.setFocusPainted(false);
        add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddTaskPage addTaskPage = new AddTaskPage();
                addTaskPage.show();
                dispose();
            }
        });



        selectJob = new JLabel("Select Job");
        selectJob.setFont(new Font("San Serif", Font.PLAIN, 20));
        selectJob.setBounds(350, 200, 200, 20);
        selectJob.setForeground(Color.black);
        add(selectJob);
        add(frame);

        jobs = new JLabel("Job Here");
        jobs.setFont(new Font("Roboto", Font.PLAIN, 20));
        jobs.setBounds(520,200,200,30);
        add(jobs);
        add(frame);
      t=new ScrollableLabel(text);
      t.setFont(new Font("San Serif", Font.PLAIN, 15));
       jtf=new JScrollPane(t);
        jtf.setBounds(400,300,400,55);
        jtf.setVisible(true);
        add(jtf);

        jButton = new JButton("Start Job");
        jButton.setBounds(450,400,300,45);
        jButton.setForeground(Color.white);
        jButton.setFocusPainted(false);
        jButton.setBackground(new Color(0x9494AE));
        jButton.setFont(new Font("San Serif", Font.PLAIN, 25));
        add(jButton);
        add(frame);

        stop = new JButton("Job Completed");
        stop.setBounds(450,500,300,45);
        stop.setForeground(Color.white);
        stop.setFocusPainted(false);
        stop.setBackground(new Color(0x9494AE));
        stop.setFont(new Font("San Serif", Font.PLAIN, 25));
        add(stop);
        add(frame);

       jButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               JOptionPane.showMessageDialog(null, "job started");
               try {
                   jobShedule();
               } catch (InterruptedException ex) {
                   ex.printStackTrace();
               }
           }
       });



    }
    public void topoSort(int jobId){
//        int jobId = Integer.parseInt(String.valueOf(jobs.getSelectedItem()));

        JobModel jobModel = taskController.fetchJobBYId(jobId);
        int vertixes = jobModel.getNumOfTask();
        System.out.println("i am vertix: "+vertixes);
        Graph graph = new Graph(vertixes);

        ArrayList<JobTaskModel> jtm = taskController.fetchTaskModels(jobId);
        System.out.println("I am arrat list of task");
        for(int i=0; i<jtm.size(); i++){
            graph.addEdge(jtm.get(i).getSource(),jtm.get(i).getDestination());
        }

        int[] tasks=graph.topologicalSort();

        for (int i=0; i<tasks.length; i++){
            TaskModel tm =new TaskModel();
            tm=taskController.getTaskById(tasks[i]);
            System.out.println(tm.getTask());
             text+= tm.getTask()+"->";
             t.setText(text);

        }



    }

    public void jobShedule() throws InterruptedException {

        ArrayList<JobModel> jobModels = taskController.fetchJob();
        SequencingJob sequencingJob = new SequencingJob();

        ArrayList<JobSheduling.JobModel> jminsc = new ArrayList<>();

        //creating job model from the job model retreved from database
        for(int i=0; i<jobModels.size(); i++){
            JobSheduling.JobModel jobm = new JobSheduling.JobModel(jobModels.get(i).getJobId(),jobModels.get(i).getProfit(),jobModels.get(i).getTime());
            jminsc.add(jobm);
        }

        List<Integer> scheduledJobs = new ArrayList<Integer>();
        scheduledJobs=sequencingJob.scheduling(jminsc);
        int[] allTask =  new int[scheduledJobs.size()];
        int[] allTime = new int[scheduledJobs.size()];

        for(int i=0; i<scheduledJobs.size(); i++){
            JobModel JM = taskController.fetchJobBYId(scheduledJobs.get(i));
             allTime[i]=JM.getTime();
             allTask[i]=JM.getJobId();

        }
        //created list of executables tasks
        System.out.println(Arrays.toString(allTask));
        Runnable[] TASKS = new Runnable[allTask.length];
        for(int tim = 0; tim<allTime.length;tim++) {

            int finalTim = tim;
            TimerTask task = new TimerTask() {
            @Override
            public void run() {

                    text="";
                    t.setText(text);

                    System.out.println("run task");
                    JobModel job = taskController.fetchJobBYId(allTask[finalTim]);

                    String jobName = job.getJobName();
                    System.out.println(jobName);
                    jobs.setText(jobName);
                    topoSort(allTask[finalTim]);


                }
            };
            TASKS[tim]=task;
        }
        for(int i=0; i<allTime.length; i++){

        }

        //list of date object of when task will execute
        ArrayList<Calendar> TIMES = new ArrayList<>();
       for(int timeTo=0; timeTo<allTime.length;timeTo++){
           Calendar date = Calendar.getInstance();
           date.set(Calendar.HOUR_OF_DAY,4);
           date.set(Calendar.MINUTE,allTime[timeTo]);
           date.set(Calendar.SECOND,0);
           TIMES.add(date);
           System.out.println(date.getTime());
       }


       //executing tasks in the given time
        Timer timer = new Timer();
        for(int i=0; i<TASKS.length; i++){
            final int index=i;
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    TASKS[index].run();;
                }
            };
            timer.schedule(task,TIMES.get(i).getTime());
        }







    }

    public static void main(String[] args) {
        new StartJob().setVisible(true);
    }


}
