import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by midhapranav on 11/24/15.
 */
public class AISMentorship {

    public static int MAX_MENTEE_PER_MENTOR = 10;

    static class Mentors {
        String name;
        String email;
        String departement;
        String specialization;
        int menteeCount;
        List<Mentees>menteeNames;
    }
    static class Mentees {
        String name;
        String email;
        String department;
        String specialization;
    }

    public static List<Mentors> mentorList = new ArrayList<Mentors>();
    public static List<Mentees> unMappedmenteeList = new ArrayList<Mentees>();
    public static HashMap<Mentees, Mentors> mappedMenteesMap = new HashMap<Mentees, Mentors>();

    public static void main(String args[]) {
        mentorList = new ArrayList<Mentors>();
        unMappedmenteeList = new ArrayList<Mentees>();
        mappedMenteesMap = new HashMap<Mentees, Mentors>();
        //TODO populate mentorList and menteeList from dummy data

        /*
         * The algorithm below is a brute algorithm, better implementation will be a bucket sort.
         */
        for(Mentors mentor : mentorList) {
            if(mentor.menteeCount < MAX_MENTEE_PER_MENTOR) {
                Mentees newMentee = findSuitableMentee(mentor);
                mentor.menteeNames.add(newMentee);
                mappedMenteesMap.put(newMentee, mentor);
                //unMappedmenteeList.remove(mentee);
                //the above line may throw Concurrent Array Modification exception, if not remove the checks on the hashMap
                //and uncomment this line
            }
        }

        //TODO sort mentorList based on number of mentees assigned

        for(Mentees mentee : unMappedmenteeList) {
            if (!mappedMenteesMap.containsKey(mentee)) {
                Mentors newMentor = findSuitableMentor(mentee);
                newMentor.menteeNames.add(mentee);
                mappedMenteesMap.put(mentee, newMentor);
                //unMappedmenteeList.remove(mentee);
                //the above line may throw Concurrent Array Modification exception, if not remove the checks on the hashMap
                //and uncomment this line
            } else {
                //do-nothing
            }
        }

        //create a email thread for all mentors and cc all the mentees in it.
    }

    private static Mentees findSuitableMentee(Mentors mentor) {
        Mentees newMentee = new Mentees();
        for(Mentees mentee : unMappedmenteeList) {
            if(!mappedMenteesMap.containsKey(mentee)) {
                if (mentee.department.equals(mentor.departement)) {
                    newMentee = mentee;
                    break;
                }
            } else {
                //do nothing
            }
        }
        return newMentee;
    }

    private static Mentors findSuitableMentor(Mentees mentee) {
        Mentors newMentor = new Mentors();
        for(Mentors mentor : mentorList) {
            if(mentee.department.equals(mentor.departement)) {
                newMentor = mentor;
                break;
            }
        }
        return newMentor;
    }
}
