package stlstudentsregistry;

public class LockerInformation {
    
    private int lockerNum;
    private static int[] totalLockers = new int [100];

    public LockerInformation() {

        for (int i = 0; i < totalLockers.length; i++) {
            if (totalLockers[i] == 0) {
                lockerNum = (i + 1); 
                totalLockers[i] = 1;
                break;
            }
        }

    }
    
    public int getLockerNum() {
        return lockerNum;
    }
}
