import java.util.ArrayList;

public class Instructor {
    private String id;
    private String name;
    private boolean blockH;
    private int totalNum;
    private ArrayList<Room> rooms;
    private ArrayList<Days> days;
    private ArrayList<MeetingTime> meetingTimes;
    private int numPerClass; 

    public Instructor(String id, String name, int totalNum, int numPerClass, boolean blockH, 
                        ArrayList<Room> rooms, ArrayList<Days> days, ArrayList<MeetingTime> meetingTimes){
        this.id = id;
        this.name = name;
        this.totalNum = totalNum;
        this.numPerClass = numPerClass;
        this.blockH = blockH;
        this.rooms = rooms;
        this.days = days;
        this.meetingTimes = meetingTimes;
    }

    public String getId(){ return id; }

    public String getName() { return name; }
    public int getTotalNum(){ return totalNum; }
    public int getNumPerClass(){ return numPerClass;}

    public boolean getBlockH(){ return blockH; }

    public ArrayList<Room> getRooms(){ return rooms; }
    public ArrayList<Days> getDays(){ return days; }
    public ArrayList<MeetingTime> getMeetingTimes(){ return meetingTimes; }

    public String toString() { return name; }
    
}
