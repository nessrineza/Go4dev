package esprit.tn.controllers;

import esprit.tn.Entites.Command;
import esprit.tn.services.CommandService;
import esprit.tn.stat.CAEvolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommandController {
    @Autowired
    private CommandService commandService;

    @PostMapping("/command")
    public void addCommand(@RequestBody Command command)
    {
        commandService.addCommand(command);
    }

    @GetMapping("/commands")
    public List<Command> readCommands()
    {
        return commandService.readCommands();
    }

    @GetMapping("/command/{id}")
    public Command findCommandById(@PathVariable("id") String id)
    {
        return commandService.findCommandById(Integer.parseInt(id));
    }

    @PutMapping("/command/{id}")
    public void updateCommand(@PathVariable("id") String id)
    {
        commandService.updateCommand(Integer.parseInt(id));
    }

    @DeleteMapping("/command/{id}")
    public void deleteCommand (@PathVariable("id") String id)
    {
        commandService.deleteCommand(Integer.parseInt(id));
    }

    @GetMapping("/command/stat")
    public List<CAEvolution> getCAEvolution()
    {
        return commandService.getCAEvolution();
    }
}
