import java.util.*;

/**
 * Created by midhapranav on 11/24/15.
 */
public class AISMentorship {

    static class Mentors {
        String name;
        String email;
        String departement;
        String specialization;
        int menteeCount = 0;
        List<Mentees>menteeNames;
    }
    static class Mentees {
        String name;
        String email;
        String department;
        String specialization;
        Mentors mentor;
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
        while(mappedMenteesMap.entrySet().size() != unMappedmenteeList.size()) {
            for (Mentees mentee : unMappedmenteeList) {
                Mentors newMentor = assignSameSpecializationMentor(mentee);
                mentee.mentor = newMentor;
                newMentor.menteeNames.add(mentee);
                newMentor.menteeCount++;
                mappedMenteesMap.put(mentee, newMentor);
                Collections.sort(mentorList, (o1, o2) -> o1.menteeCount - o2.menteeCount);
            }
            //update unMappedMenteeList

            Iterator iterator = mappedMenteesMap.entrySet().iterator();
            while(iterator.hasNext()) {
                HashMap.Entry<Mentees, Mentors> pair = (HashMap.Entry<Mentees, Mentors>) iterator.next();
                unMappedmenteeList.remove(pair.getKey());
            }
            //if still mentees have not been assigned a mentor, assign from the same departement

            for(Mentees mentee : unMappedmenteeList) {
                Mentors newMentor = assignSameDepartementMentor(mentee);
                mentee.mentor = newMentor;
                newMentor.menteeNames.add(mentee);
                newMentor.menteeCount++;
                mappedMenteesMap.put(mentee, newMentor);
                Collections.sort(mentorList, (o1, o2) -> o1.menteeCount - o2.menteeCount);
            }
        }

        //create a email thread for all mentors and cc all the mentees in it.
    }


    private static Mentors assignSameSpecializationMentor(Mentees mentee) {
        Mentors newMentor = new Mentors();
        for(Mentors mentor : mentorList) {
            if((mentee.department.equals(mentor.departement)) &&
                    (mentee.specialization.equals(mentor.specialization))) {
                newMentor = mentor;
                break;
            }
        }
        return newMentor;
    }

    private static Mentors assignSameDepartementMentor(Mentees mentee) {
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
