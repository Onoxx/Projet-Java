package BusinessLogic;

import DAO.GraphicCard.GraphicCardDAO;
import DAO.GraphicCard.GraphicCardDAOImpl;
import MVC.Model.GraphicCard;

import java.util.ArrayList;

public class GraphicCardManager {
    private GraphicCardDAO graphicCardDAO;

    public GraphicCardManager() {
        this.graphicCardDAO = new GraphicCardDAOImpl();
    }
    public ArrayList<GraphicCard> getAllGraphicCards() {
        return graphicCardDAO.getAllGraphicCards();
    }
}
