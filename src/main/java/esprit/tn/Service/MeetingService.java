package esprit.tn.Service;

import esprit.tn.Entites.Meeting;
import esprit.tn.Repository.MeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeetingService implements IMeetingService {

    private  final MeetingRepository meetingRepository;

    @Override
    public List<Meeting> retrieveAllMeetings() {
        return (List<Meeting>) meetingRepository.findAll();
    }

    @Override
    public Meeting addMeeting(Meeting u) {
        meetingRepository.save(u);
        return u;
    }

    @Override
    public void deleteMeeting (int id) {
        meetingRepository.deleteById(id);
    }

    @Override
    public Meeting updateMeeting(Meeting u) {
        meetingRepository.save(u);
        return u;
    }

    @Override
    public Meeting retrieveMeeting(int id) {
        Meeting u = meetingRepository.findById(id).get();
        return u;
    }

}
