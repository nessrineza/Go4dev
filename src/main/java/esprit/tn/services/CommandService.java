package esprit.tn.services;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.sms.MessageStatus;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.messages.TextMessage;
import esprit.tn.Entites.Command;
import esprit.tn.Entites.Etat;
import esprit.tn.repository.CommandRepository;
import esprit.tn.stat.CAEvolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommandService
{
    @Autowired
    private CommandRepository commandRepository;

    @Autowired
    private EmailService emailService;
    public void addCommand(Command command)
    {
        command.setEtat(Etat.nonpaye);
        command.setDate(new Date());
        commandRepository.save(command);
        emailService.sendMailCommand("aminerahmouni500@gmail.com","Confirmation de commande");
        NexmoClient client = NexmoClient.builder().apiKey("d7f68de2").apiSecret("keJ6biP1jgjzyAw8").build();
        TextMessage message = new TextMessage("Command" ,
                "+21656781597",
                "We received your command!"
        );

        try {
            SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);
            if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
                System.out.println("Message sent successfully.");
            } else {
                System.out.println("Message failed with error: " + response.getMessages().get(0).getErrorText());
            }
        }
        catch(Exception e) {}
    }
    public void updateCommand(int id)
    {
        Command command=commandRepository.findById(id).orElse(null);
        command.setEtat(Etat.paye);
        commandRepository.save(command);
    }
    public List<Command> readCommands()
    {
        return (List<Command>) commandRepository.findAll();
    }
    public Command findCommandById(int id)
    {
        return commandRepository.findById(id).orElse(null);
    }
    public void deleteCommand (int id)
    {
        Command command=commandRepository.findById(id).orElse(null);
        commandRepository.delete(command);
    }
    public List<CAEvolution> getCAEvolution()
    {
        return commandRepository.getCAEvolution();
    }
}
