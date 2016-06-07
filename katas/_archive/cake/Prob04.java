import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;

public class Prob04 {
  private static class Meeting {
    int startTime;
    int endTime;
    Meeting(int startTime, int endTime) {
      this.startTime = startTime; this.endTime = endTime;
    }
    public String toString() {
      return "(" + startTime + ", " + endTime + ")";
    }
  }

  private static class MeetingComparator implements Comparator<Meeting> {
    @Override
    public int compare(Meeting m1, Meeting m2) {
      if (m1.startTime < m2.startTime) return -1;
      else if (m1.startTime > m2.startTime) return 1;
      else return 0;
    }
  }

  public static List<Meeting> condenseMeetingTimes(Meeting[] meetings) {
    LinkedList<Meeting> list = new LinkedList<Meeting>();
    int s = meetings[0].startTime;
    int e = meetings[0].endTime;

    for (int i = 1; i < meetings.length; i++) {
      Meeting curr = meetings[i];

      if (curr.startTime > e) {
        list.add(new Meeting(s, e));
        s = curr.startTime;
        e = curr.endTime;
      } else if (curr.endTime > e) {
        e = curr.endTime;
      }
    }
    list.add(new Meeting(s, e));

    return list;
  }

  public static void main(String[] args) {
    Meeting[] input = new Meeting[]{
        new Meeting(1, 10), new Meeting(2, 6), new Meeting(3, 5), new Meeting(7, 9)};
        //new Meeting(0, 5), new Meeting(2, 3)};
        //new Meeting(0, 1), new Meeting(3, 5), new Meeting(4, 8), new Meeting(10, 12), new Meeting(9, 10)};
    Arrays.sort(input, new MeetingComparator());
    System.out.println(Arrays.toString(input));
    System.out.println(Arrays.toString(condenseMeetingTimes(input).toArray()));
  }
}
