package command;

import task.Task;
import task.TaskList;
import duke.UserInterface;
import duke.Storage;

/**
 * Specifies the 'add' action to add user-specified tasks.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructs the Add Command object with specified task.
     * @param task Specified task.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * @return Specified task.
     */
    public Task getTask() {
        return task;
    }

    /**
     * Executes the command and runs the related logic flow.
     * @param tasks List of tasks.
     * @param ui User interface.
     * @param storage Tasks storage: load & save.
     */
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        //add task into the task list
        tasks.add(task);

        //display successful message and task count
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t " + task.toString());
        System.out.println("\tNow you have " + tasks.getSize() + " tasks in the list.");
    }
}
