package esprit.tn.Service;

import esprit.tn.Entites.Meeting;

import java.util.List;

public interface IMeetingService {
    List<Meeting> retrieveAllMeetings();

    Meeting addMeeting(Meeting m);

    void deleteMeeting(int id);

    Meeting updateMeeting(Meeting m);

    Meeting retrieveMeeting(int id);
}
