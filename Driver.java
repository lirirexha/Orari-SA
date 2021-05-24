import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Driver extends JFrame {
    private Data data;
    int scheduleNumb = 0;

    public static double acceptanceProbability(int currentConflict, int newConflict, double temp) {
        if (currentConflict <= newConflict) {
            return 1.0;
        } else {
            return Math.exp((currentConflict - newConflict) / temp);
        }
    }

    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.data = new Data();
        int iteration = 0;
        System.out.println("Iteration: " + iteration);
        System.out.println("Classes  |  Conflicts");
        System.out.print("----------------------------------------------------------------------------------------------------");
        System.out.println("--------------------------------------------------------------");
        Solutions currentSolution = new Solutions(8, driver.data).sortByFitness();
        // Schedule schedule1 = currentSolution.getSchedules().get(0);
        System.out.println(currentSolution.getSchedules().get(0));
        int currentConflict = currentSolution.getSchedules().get(0).getNumbOfConflicts();
        System.out.println(currentConflict);
        // solutions.getSchedules().forEach(schedule -> System.out.println("
        // "+driver.scheduleNumb++ +
        // " | " + schedule + " | " + schedule.getFitness() +" | "+
        // schedule.getNumbOfConflicts()));

        // SIMULATED ANNEALING

        double temp = 100;
        double coolingRate = 0.003;
        int newConflict = 0;

        while (temp > 1) {

            iteration = iteration + 1;

            Solutions newSolution = new Solutions(8, driver.data).sortByFitness();
            newConflict = newSolution.getSchedules().get(0).getNumbOfConflicts();

            if (acceptanceProbability(currentConflict, newConflict, temp) > 1) {
                currentConflict = newSolution.getSchedules().get(0).getNumbOfConflicts();
                currentSolution = newSolution;
            }
            temp *= 1 - coolingRate;

            if (newConflict == 0) {
                break;
            }
        }

        System.out.println("Iteration: " + iteration);
        System.out.println(currentSolution.getSchedules().get(0));
        System.out.println(currentConflict);

        // String[] columnHeader = { "Nr", "Professor", "Class", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" };
        String[][] rowData = { { " ", " ", " ", " ", " ", " ", " ", " " }, { " ", " ", " ", " ", " ", " ", " ", " " } };
        for (int i = 0; i < rowData.length; i++) {
            rowData[i][0] = "" + i;
        }
        JFrame frame = new JFrame("Orari");

        JTable table = new JTable(40, 8);
        table.setBounds(30, 40, 1000, 300);
        table.getColumnModel().getColumn(0).setPreferredWidth(5);
        ;
        for (int i = 0; i < 40; i++) {
            String a = "" + (i + 1);
            table.setValueAt(a, i, 0);
        }
        // table.getColumn(1).setHeaderValue("Profesorat");
        table.getColumnModel().getColumn(1).setWidth(30);
        for (int i = 0; i < 40; i++) {
            String b = driver.data.getInstructors().get(i).getName();
            table.setValueAt(b, i, 1);
        }
        JScrollPane sp = new JScrollPane(table);
        frame.add(sp);
        frame.setSize(1000, 700);
        frame.setResizable(false);
        frame.setVisible(true);

    }

}
