package esprit.tn.Controller;

import esprit.tn.Entites.Etat;
import esprit.tn.Entites.Meeting;
import esprit.tn.Entites.Partner;
import esprit.tn.Repository.PartnerRepository;
import esprit.tn.Service.IMeetingService;
import esprit.tn.Service.IPartnerService;
import esprit.tn.Service.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Meeting")
public class MeetingController {

   private final IMeetingService meetingService;


   private final IPartnerService partnerservice;


    @GetMapping("/retrieve-all-meetings")
    @ResponseBody
    public List<Meeting> getMeetings() {
        List<Meeting> listMeetings = meetingService.retrieveAllMeetings();
        return listMeetings;
    }

    // http://localhost:8083/SpringMVC/Meeting/retrieve-meeting/8
    @GetMapping("/retrieve-meeting/{meeting-id}")
    @ResponseBody
    public Meeting retrieveMeeting(@PathVariable("meeting-id") Integer meetingId)
    {
        return meetingService.retrieveMeeting(meetingId);
    }


   /* @PostMapping("/add-meeting")
    @ResponseBody
    public ResponseEntity<?> addmeeting(@RequestBody Meeting c, @RequestHeader (name="Authorization") String token)
    {
        User user=userrepository.findByEmail(email).get();



        c.setValidity(Etat.pending);
        c.setUser(user);
        Meeting meet = meetingService.addMeeting(c);

        return ResponseEntity.ok(meet);
    }*/
    @DeleteMapping("/remove-meeting/{meeting-id}")
    @ResponseBody
    public void removeMeeting(@PathVariable("meeting-id") int meetingId)
    {
        meetingService.deleteMeeting(meetingId);
    }
    /*

    @GetMapping("/getmeetingbypartner/{id}")
    public Set<Meeting> getallmeetingsbypartner(@PathVariable(value = "id") int idpartner) {
        Optional<Partner> partner = partnerrepository.findById(idpartner);
        return partner.get().getMeetings();

    }

    @PutMapping("/affectmeetingtopartner/{id}/{idm}")
    public Meeting affectmeetingtopartner(@PathVariable(value = "id") int idpartner ,@PathVariable(value = "idm") int idmeeting) {
        Partner p= partnerservice.retrievePartner(idpartner);
        Meeting m=meetingService.retrieveMeeting(idmeeting);
        m.setPartner(p);

        return meetingService.updateMeeting(m);

    }


    @GetMapping("/getdisponiblepartnersbydate/{date}")
    public List<Partner> getdisponiblepartnersbydate(@PathVariable(value = "date") String date) {
        Date datemeeting=new Date();

        try {
            datemeeting = new SimpleDateFormat("yyyy-MM-dd").parse(date);


        } catch (ParseException e) {

        }

        List<Partner>partners=partnerservice.retrieveAllPartners();

        List<Meeting>meetings=meetingService.retrieveAllMeetings();
        List<Partner>notavailablepartners=new ArrayList<Partner>();

        for(Meeting m :meetings){
            if(m.getDate().getDay()==datemeeting.getDay() && m.getDate().getMonth()==datemeeting.getMonth() && m.getDate().getYear()==datemeeting.getYear())
            {

                notavailablepartners.add(m.getPartner());
            }
        }

        partners.removeAll(notavailablepartners);
        return partners;
    }


    @PutMapping("/validatemeeting/{id}/{decision}")
    @ResponseBody
    public Meeting validatemeeting(@PathVariable(value = "id")  int id,@PathVariable(value = "decision") int decision)
    {   Meeting m= meetingService.retrieveMeeting(id);
        if(decision==1){
            m.setValidity(Etat.valid);
        }else
            m.setValidity(Etat.refused);

        return meetingService.updateMeeting(m);
    }

*/

}



