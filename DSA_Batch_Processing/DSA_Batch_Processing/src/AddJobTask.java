import controller.TaskController;
import model.JobTaskModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class AddJobTask extends JFrame{
  JPanel frame;
  JButton back;
  JLabel id;
  JTextField txtId;
  JComboBox task;
  JComboBox dependent;
  JButton addTak;
  JLabel currTask;
  JLabel depTask;

    AddJobTask(){
        setTitle("Select Task For Job");
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

        TaskController taskController = new TaskController();
        ArrayList<Integer> idtaskList = taskController.fetchTask();
        String[] idOfTaskList=new String[idtaskList.size()+1];
        System.out.println(idtaskList);


        for(int i=0; i<idtaskList.size(); i++){

               System.out.println(idtaskList.get(i));
               idOfTaskList[i]=idtaskList.get(i).toString();


        }

        System.out.println(Arrays.toString(idOfTaskList));

        id = new JLabel("Job Id");
        id.setFont(new Font("San Serif", Font.PLAIN, 30));
        id.setBounds(420, 120, 100, 25);
        id.setForeground(Color.black);
        add(id);
        add(frame);

        txtId = new JTextField();
        txtId.setBounds(550, 120, 120, 30);
        add(txtId);
        add(frame);

        currTask = new JLabel("Current Task");
        currTask.setFont(new Font("San Serif", Font.PLAIN, 20));
        currTask.setBounds(350, 200, 200, 20);
        currTask.setForeground(Color.black);
        add(currTask);
        add(frame);


        task = new JComboBox(idOfTaskList);
        task.setFont(new Font("Roboto", Font.PLAIN, 20));
        task.setBounds(520,200,200,30);
        add(task);

        depTask = new JLabel("Dependent Task");
        depTask.setFont(new Font("San Serif", Font.PLAIN, 20));
        depTask.setBounds(350, 300, 200, 25);
        depTask.setForeground(Color.black);
        add(depTask);
        add(frame);


        dependent = new JComboBox(idOfTaskList);
        dependent.setFont(new Font("Roboto", Font.PLAIN, 20));
        dependent.setBounds(520,300,200,30);
        add(dependent);

        addTak = new JButton("Add Task");
        addTak.setBounds(450,400,300,45);
        addTak.setForeground(Color.white);
        addTak.setFocusPainted(false);
        addTak.setBackground(new Color(0x9494AE));
        addTak.setFont(new Font("San Serif", Font.PLAIN, 25));
        add(addTak);
        add(frame);

        addTak.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int jobId;
                int source;
                int destination;

                if(txtId.getText().isEmpty()||String.valueOf(task.getSelectedItem()).isEmpty()){
                    JOptionPane.showMessageDialog(null, "blank fields", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    jobId=Integer.parseInt(txtId.getText());
                    source=Integer.parseInt(String.valueOf(dependent.getSelectedItem()));
                    destination=Integer.parseInt(String.valueOf(task.getSelectedItem()));

                    JobTaskModel jobTaskModel = new JobTaskModel(jobId,source,destination);
                    TaskController taskController1 = new TaskController();
                    int insert = taskController.addJobTask(jobTaskModel);

                    if(insert>0){
                        JOptionPane.showMessageDialog(null, "Task added Successfully");
                    }else{
                        JOptionPane.showMessageDialog(null, "Filed to add Task");
                    }


                }
            }
        });

    }





    public static void main(String[] args) {
        new AddJobTask().setVisible(true);
    }





}
