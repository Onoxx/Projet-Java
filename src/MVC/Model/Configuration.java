package MVC.Model;

import java.util.Date;

public class Configuration {
    private User user;
    private Processor processor;
    private GraphicCard graphicCard;
    private Case computerCase;
    private MotherBoard motherBoard;
    private Ram ram;
    private Date date;

    public Configuration(User user, Processor processor, GraphicCard graphicCard, Case computerCase, MotherBoard motherBoard, Ram ram, Date date) {
        this.user = user;
        this.processor = processor;
        this.graphicCard = graphicCard;
        this.computerCase = computerCase;
        this.motherBoard = motherBoard;
        this.ram = ram;
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
    public Date getDate() {
        return date;
    }
}
