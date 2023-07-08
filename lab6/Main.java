import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int numQueries = scn.nextInt();
        Roster roster = new Roster("Roster");
        for (int i = 0; i < numQueries; i++) {
            String name = scn.next();
            String module = scn.next();
            String assessment = scn.next();
            String grade = scn.next();
            roster = roster.add(name, module, assessment, grade);
        }
        while (scn.hasNext()) {
            String name = scn.next();
            if (name.equals("^D")) {
                break;
            }
            String module = scn.next();
            String assessment = scn.next();
            System.out.println(roster.getGrade(name, module, assessment));
        }
        scn.close();
    }
}
