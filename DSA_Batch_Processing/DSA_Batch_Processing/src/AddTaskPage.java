import controller.TaskController;
import model.TaskModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddTaskPage extends JFrame {


    JLabel taskLabel;
    JLabel id;
    JPanel frame;


    JTextField txtTask;
    JTextField txtId;
    JButton addTask;
    JButton sheduleJob;





    public AddTaskPage() {
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

        id = new JLabel("Task Id");
        id.setFont(new Font("San Serif", Font.PLAIN, 30));
        id.setBounds(420, 120, 100, 25);
        id.setForeground(Color.black);
        add(id);
        add(frame);

        txtId = new JTextField();
        txtId.setBounds(550, 120, 120, 30);
        add(txtId);
        add(frame);

        taskLabel = new JLabel("Add your Task");
        taskLabel.setFont(new Font("San Serif", Font.PLAIN, 30));
        taskLabel.setBounds(450, 200, 400, 40);
        taskLabel.setForeground(Color.black);
        add(taskLabel);
        add(frame);


        txtTask = new JTextField();
        txtTask.setBounds(350, 280, 450, 45);
        add(txtTask);
        add(frame);


        addTask = new JButton("Add Task");
        addTask.setBounds(450,400,300,45);
        addTask.setForeground(Color.white);
        addTask.setFocusPainted(false);
        addTask.setBackground(new Color(0x9494AE));
        addTask.setFont(new Font("San Serif", Font.PLAIN, 25));
        add(addTask);
        add(frame);

        sheduleJob = new JButton("Create Job");
        sheduleJob.setBounds(450,500,300,45);
        sheduleJob.setForeground(Color.white);
        sheduleJob.setFocusPainted(false);
        sheduleJob.setBackground(new Color(0x9494AE));
        sheduleJob.setFont(new Font("San Serif", Font.PLAIN, 25));
        add(sheduleJob);
        add(frame);

      addTask.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
             addTask();
          }
      });

        sheduleJob.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateJob createJob = new CreateJob();
                createJob.show();
                dispose();
            }
        });
    }


    private void addTask(){
        String task=txtTask.getText();
        int taskId = Integer.parseInt(txtId.getText());

        if(task.isEmpty()||txtId.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,"Please enter all the fields");

        }

        else{
            TaskModel taskModel = new TaskModel(task,taskId);

            TaskController taskController = new TaskController();
            int insert =taskController.addTask(taskModel);

            if (insert > 0) {
                JOptionPane.showMessageDialog(null, "Task Added Successfully");


            } else {
                JOptionPane.showMessageDialog(null, "Failed to Add Task");
            }
        }
    }





    public static void main(String[] args) {
        new AddTaskPage().setVisible(true);
    }
}