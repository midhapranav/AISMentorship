import java.util.ArrayList;
import java.util.HashMap;
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
        int menteeCount;
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
        List<mentees> unMappedmenteeList = new ArrayList<mentees>();
        HashMap<mentees, mentors> mappedMenteesMap = new HashMap<mentees, mentors>();
        //TODO populate mentorList and menteeList from dummy data

        /*
         * The algorithm below is a brute algorithm, better implementation will be a bucket sort.
         */
        for(mentors mentor : mentorList) {
            if(mentor.menteeCount < MAX_MENTEE_PER_MENTOR) {
                //call a function which adds a mentee from the same field
                //add mentee to the mentor's mentee list
                //remove mentee from unMappedMenteeList
                // add mentee to mapped mentee map
            }
        }

        for(mentees mentee : unMappedmenteeList) {
            //find a mentor with same field as mentee
            //add mentee to the mentor's mentee list
            //remove mentee from unmapped mentee list
            // add mentee to mapped mentee map
        }

        //create a email thread for all mentors and cc all the mentees in it.
    }
}
