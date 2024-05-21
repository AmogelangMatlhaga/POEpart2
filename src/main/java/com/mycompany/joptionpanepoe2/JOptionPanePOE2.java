/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.joptionpanepoe2;

/**
 *
 * @author Lethabo Molate
 */
import javax.swing.JOptionPane;

public class JOptionPanePOE2 {

    public static void main(String[] args) {
        boolean loggedIn = false;
        
        while (!loggedIn) {
            String username = JOptionPane.showInputDialog("Enter username:");
            String password = JOptionPane.showInputDialog("Enter password:");
            String firstName = JOptionPane.showInputDialog("Enter first name:");
            String lastName = JOptionPane.showInputDialog("Enter last name:");

            // Create the account
            Account account = new Account(username, password, firstName, lastName);

            // Validate username and password
            if (account.isUsernameValid()) {
                JOptionPane.showMessageDialog(null, "Username successfully captured");
            } else {
                JOptionPane.showMessageDialog(null, account.getUsernameErrorMessage());
                continue; // Restart the loop if username is invalid
            }

            if (account.isPasswordValid()) {
                JOptionPane.showMessageDialog(null, "Password successfully captured");
            } else {
                JOptionPane.showMessageDialog(null, account.getPasswordErrorMessage());
                continue; // Restart the loop if password is invalid
            }

            // Create the login section
            Login login = new Login(account);

            // Login process
            String enteredUsername = JOptionPane.showInputDialog("Enter your credentials to login:\nEnter username:");
            String enteredPassword = JOptionPane.showInputDialog("Enter password:");

            loggedIn = login.loginUser(enteredUsername, enteredPassword);
            JOptionPane.showMessageDialog(null, login.returnLoginStatus(loggedIn));
        }

        // User logged in successfully
        JOptionPane.showMessageDialog(null, "Welcome to EasyKanban");

        Task[] tasks = new Task[0];
        boolean running = true;

        while (running) {
            String[] options = {"Add tasks", "Show report", "Quit"};
            int option = JOptionPane.showOptionDialog(null, "Menu:", "Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            if (option != 0) if (option == 1) {
                JOptionPane.showMessageDialog(null, "Coming Soon");
            } else if (option == 2) {
                running = false;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid option");
            } else {
                int numTasks = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of tasks to add:"));
                Task[] newTasks = new Task[tasks.length + numTasks];
                System.arraycopy(tasks, 0, newTasks, 0, tasks.length);

                for (int i = tasks.length; i < newTasks.length; i++) {
                    newTasks[i] = addTask(i);
                }

                tasks = newTasks;
                int totalHours = getTotalHours(tasks);
                JOptionPane.showMessageDialog(null, "Total hours: " + totalHours);
            }
        }
    }

    private static Task addTask(int taskNumber) {
        String taskName = JOptionPane.showInputDialog("Enter task name:");
        String taskDescription = JOptionPane.showInputDialog("Enter task description:");

        while (taskDescription.length() > 50) {
            taskDescription = JOptionPane.showInputDialog("Please enter a task description of less than 50 characters:\nEnter task description:");
        }

        String developerDetails = JOptionPane.showInputDialog("Enter developer details:");
        int taskDuration = Integer.parseInt(JOptionPane.showInputDialog("Enter task duration (hours):"));

        String[] statusOptions = {"To Do", "Done", "Doing"};
        String taskStatus = (String) JOptionPane.showInputDialog(null, "Select task status:",
                "Task Status", JOptionPane.QUESTION_MESSAGE, null, statusOptions, statusOptions[0]);

        Task task = new Task(taskNumber, taskName, taskDescription, developerDetails, taskDuration, taskStatus);
        JOptionPane.showMessageDialog(null, "Task successfully captured\n" + task.printTaskDetails());

        return task;
    }

    private static int getTotalHours(Task[] tasks) {
        int totalHours = 0;
        for (Task task : tasks) {
            totalHours += task.getDuration();
        }
        return totalHours;
    }
}

