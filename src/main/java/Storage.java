import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

class Storage {
    private String filepath;

    Storage(String filepath) {
        this.filepath = filepath;
    }

    ArrayList<Task> load() throws FileNotFoundException, ParseException, DukeException {
        // pass the path to the file as a parameter
        File file = new File(filepath);
        Scanner s = new Scanner(file);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        ArrayList<Task> tasks = new ArrayList<>();

        while (s.hasNextLine()) {
            String[] task = s.nextLine().split(" \\| ");
            Task taskToAdd;

            switch (task[0]) {
            case "D":
                taskToAdd = new Deadline(task[2], task[1].equals("+"), formatter.parse(task[3]));
                break;
            case "E":
                taskToAdd = new Event(task[2], task[1].equals("+"), formatter.parse(task[3]));
                break;
            case "T":
                taskToAdd = new Todo(task[2], task[1].equals("+"));
                break;
            default:
                throw new DukeException();
            }
            tasks.add(taskToAdd);
        }
        return tasks;
    }

    void save(ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter("data/duke.txt");
        for(Task task: tasks) {
            writer.write(task.toFile() + System.lineSeparator());
        }
        writer.close();
    }
}
