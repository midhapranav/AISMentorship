import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;

/**
 * Created by midhapranav on 11/24/15.
 */
public class AISMentorship {

    /**
     * Structure to hold information about a mentor
     */
    static class Mentor {
        String name;
        String email;
        String departement;
        String specialization;
        int menteeCount = 0;
        List<Mentee> menteeList;
        //Add more fields if required
    }

    /**
     * Structure to hold information about a mentee
     */
    static class Mentee {
        String name;
        String email;
        String department;
        String specialization;
        Mentor mentor;
        //add more fields if required
    }

    public static List<Mentor> mentorList = new ArrayList<Mentor>();
    public static List<Mentee> unMappedmenteeList = new ArrayList<Mentee>();
    public static HashMap<Mentee, Mentor> mappedMenteesMap = new HashMap<Mentee, Mentor>();

    public static void main(String args[]) {
        mentorList = new ArrayList<Mentor>();
        unMappedmenteeList = new ArrayList<Mentee>();
        mappedMenteesMap = new HashMap<Mentee, Mentor>();

        //TODO : Populate the mentorList and unMappedMenteeList, the data should be accessed through google forms

        /**
         * The algorithm below is a brute algorithm, keeping it simple as it will be easier to accommodate additional fields
         */
        while(mappedMenteesMap.entrySet().size() != unMappedmenteeList.size()) {
            for (final Mentee mentee : unMappedmenteeList) {
                final Mentor newMentor = assignSameSpecializationMentor(mentee);
                mentee.mentor = newMentor;
                newMentor.menteeList.add(mentee);
                newMentor.menteeCount++;
                mappedMenteesMap.put(mentee, newMentor);
            }

            updateUnMappedMenteeList();

            //if still mentees have not been assigned a mentor, assign from the same departement
            for(final Mentee mentee : unMappedmenteeList) {
                Mentor newMentor = assignSameDepartementMentor(mentee);
                mentee.mentor = newMentor;
                newMentor.menteeList.add(mentee);
                newMentor.menteeCount++;
                mappedMenteesMap.put(mentee, newMentor);
            }

            updateUnMappedMenteeList();

            //finally, if some useless fellow is not mapped assign them to a random mentor
            for(final Mentee mentee : unMappedmenteeList) {
                Mentor newMentor = assignMentorWithLowestMentees();
                mentee.mentor = newMentor;
                newMentor.menteeList.add(mentee);
                newMentor.menteeCount++;
                mappedMenteesMap.put(mentee, newMentor);
            }
            updateUnMappedMenteeList();
        }

        //TODO : Once testing is done update write a class to email the mentors and mentees
    }

    private static void updateUnMappedMenteeList() {
        final Iterator iterator = mappedMenteesMap.entrySet().iterator();
        while(iterator.hasNext()) {
            HashMap.Entry<Mentee, Mentor> pair = (HashMap.Entry<Mentee, Mentor>) iterator.next();
            unMappedmenteeList.remove(pair.getKey());
        }
    }

    private static Mentor assignSameSpecializationMentor(final Mentee mentee) {
        Mentor newMentor = new Mentor();
        Collections.shuffle(mentorList); //shuffling the list everytime so that a mentor doesnt get overloaded
        for(final Mentor mentor : mentorList) {
            if((mentee.department.equals(mentor.departement)) &&
                    (mentee.specialization.equals(mentor.specialization))) {
                newMentor = mentor;
                break;
            }
        }
        return newMentor;
    }

    private static Mentor assignSameDepartementMentor(final Mentee mentee) {
        Mentor newMentor = new Mentor();
        Collections.shuffle(mentorList); //shuffling the list everytime so that a mentor doesnt get overloaded
        for(Mentor mentor : mentorList) {
            if(mentee.department.equals(mentor.departement)) {
                newMentor = mentor;
                break;
            }
        }
        return newMentor;
    }

    private static Mentor assignMentorWithLowestMentees() {
        Collections.sort(mentorList, (o1, o2) -> o2.menteeCount - o1.menteeCount); //ascending order
        final Mentor newMentor = mentorList.get(0);
        return newMentor;
    }
}
