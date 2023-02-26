
import controller.TaskController;
import model.JobModel;
import model.TaskModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.Format;


public class CreateJob extends JFrame {


    JLabel jobLabel;
    JLabel id;
    JPanel frame;
    JLabel numTask;
    JLabel profit;

    JLabel time;


    JTextField txtjob;
    JTextField txtId;
    JTextField txtNumTask;
    JTextField txtprofit;
    JTextField txtTime;

    JButton CreateJob;
    JButton addTak;
    JButton back;





    public CreateJob() {
        initialize();
        uIInitialize();


    }



    public void initialize () {
        setTitle("Add Your Task");
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

    }
    public void uIInitialize(){


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

        id = new JLabel("Job Id");
        id.setFont(new Font("San Serif", Font.PLAIN, 20));
        id.setBounds(350, 100, 100, 25);
        id.setForeground(Color.black);
        add(id);
        add(frame);

        txtId = new JTextField();
        txtId.setBounds(420, 100, 50, 30);
        add(txtId);
        add(frame);

        time = new JLabel("End Time");
        time.setFont(new Font("San Serif", Font.PLAIN, 20));
        time.setBounds(680, 100, 100, 25);
        time.setForeground(Color.black);
        add(time);
        add(frame);

        txtTime = new JTextField();
        txtTime.setBounds(780, 100, 50, 30);
        add(txtTime);
        add(frame);


        profit=new JLabel("Profit");
        profit.setFont(new Font("San Serif", Font.PLAIN, 20));
        profit.setBounds(600, 200, 100, 25);
        profit.setForeground(Color.black);
        add(profit);
        add(frame);

        txtprofit = new JTextField();
        txtprofit.setBounds(700, 200, 50, 30);
        add(txtprofit);
        add(frame);

        numTask = new JLabel("Num of task");
        numTask.setFont(new Font("San Serif", Font.PLAIN, 20));
        numTask.setBounds(380, 200, 200, 30);
        numTask.setForeground(Color.black);
        add(numTask);
        add(frame);

        txtNumTask = new JTextField();
        txtNumTask.setBounds(500, 200, 50, 30);
        add(txtNumTask);
        add(frame);

        jobLabel = new JLabel("Job name");
        jobLabel.setFont(new Font("San Serif", Font.PLAIN, 30));
        jobLabel.setBounds(450, 280, 400, 40);
        jobLabel.setForeground(Color.black);
        add(jobLabel);
        add(frame);




        txtjob = new JTextField();
        txtjob.setBounds(350, 320, 450, 45);
        add(txtjob);
        add(frame);


        CreateJob = new JButton("Create Job");
        CreateJob.setBounds(450,400,300,45);
        CreateJob.setForeground(Color.white);
        CreateJob.setFocusPainted(false);
        CreateJob.setBackground(new Color(0x9494AE));
        CreateJob.setFont(new Font("San Serif", Font.PLAIN, 25));
        add(CreateJob);
        add(frame);

        addTak = new JButton("Add Task");
        addTak.setBounds(450,500,300,45);
        addTak.setForeground(Color.white);
        addTak.setFocusPainted(false);
        addTak.setBackground(new Color(0x9494AE));
        addTak.setFont(new Font("San Serif", Font.PLAIN, 25));
        add(addTak);
        add(frame);

        CreateJob.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addJob();
            }
        });

        addTak.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddJobTask addJobTask = new AddJobTask();
                addJobTask.show();
                dispose();
            }
        });



    }

    private void addJob(){
        int id=Integer.parseInt(txtId.getText());
        String jobName = txtjob.getText();
        int numOfTask = Integer.parseInt(txtNumTask.getText());
        int dead=Integer.parseInt(txtTime.getText());
        int profit = Integer.parseInt(txtprofit.getText());

        if(id==0||jobName.isEmpty()){
            JOptionPane.showMessageDialog(this,"Please Enter All Field","Try again",JOptionPane.ERROR_MESSAGE);
        }else{
            JobModel jobModel = new JobModel(id, jobName,numOfTask,dead,profit);
            TaskController taskController = new TaskController();

            int insert = taskController.addJob(jobModel);


            if(insert>0){

                JOptionPane.showMessageDialog(null, "Job Created Successfully");

            }

            else{
                JOptionPane.showMessageDialog(null, "Failed to create job");
            }
        }

    }








    public static void main(String[] args) {
        new CreateJob().setVisible(true);
    }
}