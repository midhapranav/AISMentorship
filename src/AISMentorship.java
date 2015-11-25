import java.util.ArrayList;
import java.util.List;

/**
 * Created by midhapranav on 11/24/15.
 */
public class AISMentorship {

    public static int MAX_MENTEE_PER_MENTOR = 10;
    static class mentors {
        String name;
        String email;
        String departement;
        String specialization;
        List<mentees>menteeNames;
    }
    static class mentees {
        String name;
        String email;
        String department;
        String specialization;
    }

    public static void main(String args[]) {
        List<mentors> mentorList = new ArrayList<mentors>();
        List<mentees> menteeList = new ArrayList<mentees>();
        List<mentees> mappedMenteeList = new ArrayList<mentees>();
        //TODO populate mentorList and menteeList from dummy data

        /*
         * The algorithm below is a brute algorithm, better implementation will be a bucket sort.
         */
        for(int mentorNumber = 0; mentorNumber < MAX_MENTEE_PER_MENTOR; mentorNumber++) {
            int menteeCount = 0;
            mentorList.get(mentorNumber).menteeNames = new ArrayList<mentees>();
            while(menteeCount < MAX_MENTEE_PER_MENTOR) {
                mentorList.get(mentorNumber).menteeNames
                // TODO put mentees into mentorList.get(mentorNumber).menteeNames
            }
        }
    }
}
