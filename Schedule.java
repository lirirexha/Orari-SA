import java.util.ArrayList;

public class Schedule {
    private ArrayList<Ora> classes;
    private int classNumb = 0;
    private boolean isFitnessChanged = true;
    private double fitness = -1;
    private int numbOfConflicts = 0;
    private double softConstraints = 0;
    private Data data;
    public Instructor instructor;
    public Ora ora;
    //private Course course;
    public Data getData(){ return data; }

    public Schedule(Data data){
        this.data = data;
        classes = new ArrayList<Ora>(192); //32*6
    }
    

    
    // public Schedule initializeDays(){
    //     new ArrayList<Days>(data.getDays()).forEach(days -> {
    //         days.getMeetingTimes().forEach(meetingTime -> {
    //             Ora newClass = new Ora(classNumb++, meetingTime);
    //             newClass.setInstructor(data.getInstructors().get((int)(data.getInstructors().size()*Math.random())));
    //             classes.add(newClass);
    //         });
    //     });
    //     return this;
    // }

    // public Schedule initialize(){
    //     new ArrayList<Course>(data.getCourses()).forEach( course -> {
    //         Ora newClass = new Ora(classNumb++, course);
    //         newClass.setMeetingTime(data.getMeetingTimes().get((int)(data.getMeetingTimes().size()*Math.random())));
    //         newClass.setRoom(data.getRooms().get((int)(data.getRooms().size()*Math.random())));
    //         newClass.setInstructor(course.getInstructor());
    //         classes.add(newClass);
    //     });
    // return this;
    // }

    // public Schedule initializeDays(){
    //     new ArrayList<Days>(data.getDays().forEach( days -> {});)
    // }

    // public Schedule initializeRooms(){
    //     new ArrayList<Room>(data.getRooms()).forEach(room -> {
    //         new ArrayList<Days>(data.getDays()).forEach(days -> {
    //             days.getMeetingTimes().forEach(meetingTime -> {
    //                 Ora newClass = new Ora(classNumb++, meetingTime);
    //                 newClass.setDay(days);
    //                 newClass.setRoom(room);
    //                 newClass.setInstructor(data.getInstructors().get((int)(data.getInstructors().size()*Math.random())));
    //                 classes.add(newClass);
    //             });
    //         });
    //     });
    //     return this;
    // }
    public Schedule initializeRooms(){
        new ArrayList<Room>(data.getRooms()).forEach(room -> { 
            Instructo
            new ArrayList<Days>(data.getDays()).forEach(days -> {
                days.getMeetingTimes().forEach(meetingTime -> {
                    Ora newClass = new Ora(classNumb++, meetingTime);
                    newClass.setDay(days);
                    newClass.setRoom(room);
                    Course crs = data.getCourses().get((int)(data.getCourses().size()*Math.random()));
                    Instructor inst = crs.getInstructor();
                    while(!(room.getCourses().contains(crs)) || !(inst.getDays().contains(days))){ 
                        crs = data.getCourses().get((int)(data.getCourses().size()*Math.random()));
                        inst = crs.getInstructor();
                    }
                    newClass.setCourse(crs);
                    classes.add(newClass);
                });
            });
        });
        return this;
    }

    public int getNumbOfConflicts(){ return numbOfConflicts; }

    //public double getSoftConstraints() { return softConstraints; }

    public ArrayList<Ora> getClasses(){
        isFitnessChanged = true;
        return classes;
    }

    public double getFitness(){
        if(isFitnessChanged==true){
            fitness=calculateFitness();
            isFitnessChanged = false;
        }
        return fitness;
    }

    private double calculateFitness(){
        numbOfConflicts = 0;
        classes.forEach(x -> { 
        classes.stream().filter( y -> classes.indexOf(y) >= classes.indexOf(x)).forEach(y -> { //dy prof ne te njejten klase ose dy klase-1prof
            if(x.getMeetingTime()==y.getMeetingTime() && x.getId() != y.getId() && x.getDays()== y.getDays()){
                if(x.getRoom() == y.getRoom()) numbOfConflicts++;
                if(x.getInstructor() == y.getInstructor()) numbOfConflicts++;
            }
        });
    });

        



        // classes.forEach(x -> {
        //     Course crs = x.getCourse();
        //     Instructor inst = crs.getInstructor();
        //     if(!(inst.getMeetingTimes().contains(x.getMeetingTime()))){ numbOfConflicts++; }
        // });

        // classes.forEach(x -> {
        //     Room r = x.getRoom();
        //     new ArrayList<Course>(r.getCourses()).forEach(course ->{
        //         course.getInstructor();
        //         int n = course.getInstructor().getNumPerClass();
        //     });
            
        // });

        
         //classes.forEach(x -> {
           // if(!(x.getCourse().getInstructor()== null)) numbOfConflicts++;
         //});

        

        return 1/(double)(numbOfConflicts + 1);
    }

    public String toString(){
        String returnValue = new String();
        for (int x = 0; x < classes.size()-1; x++) returnValue += classes.get(x)+",";
        returnValue += classes.get(classes.size()-1);
        return returnValue;       
    }
}
