package MVC.Model;

import java.util.Date;
import java.util.HashMap;

public class Configuration {

    private User user;
    private Processor processor;
    private GraphicCard graphicCard;
    private Case computerCase;
    private MotherBoard motherBoard;
    private Ram ram;

    private HashMap<Storage, Integer> storages;
    private HashMap<Cooling, Integer> coolings;

    private Date date;

    public Configuration(
            User user,
            Processor processor,
            GraphicCard graphicCard,
            Case computerCase,
            MotherBoard motherBoard,
            Ram ram,
            HashMap<Storage, Integer> storages,
            HashMap<Cooling, Integer> coolings,
            Date date
    ) {
        this.user = user;
        this.processor = processor;
        this.graphicCard = graphicCard;
        this.computerCase = computerCase;
        this.motherBoard = motherBoard;
        this.ram = ram;
        this.storages = storages;
        this.coolings = coolings;
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public Processor getProcessor() {
        return processor;
    }

    public GraphicCard getGraphicCard() {
        return graphicCard;
    }

    public Case getComputerCase() {
        return computerCase;
    }

    public MotherBoard getMotherBoard() {
        return motherBoard;
    }

    public Ram getRam() {
        return ram;
    }

    public HashMap<Storage, Integer> getStorages() {
        return storages;
    }

    public HashMap<Cooling, Integer> getCoolings() {
        return coolings;
    }

    public Date getDate() {
        return date;
    }
    @Override
    public String toString() {
        return user.getName() + "-" + new java.text.SimpleDateFormat("dd/MM/yyyy").format(date);
    }
}