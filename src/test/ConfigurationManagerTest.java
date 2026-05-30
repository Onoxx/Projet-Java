package test;

import BusinessLogic.ConfigurationManager;
import MVC.Model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ConfigurationManagerTest {

    private ConfigurationManager manager;

    @BeforeEach
    void setUp() {
        manager = new ConfigurationManager();
    }

    @Test
    void computeTotalPrice_emptyConfig() {
        // => Arrange
        Configuration configuration = new Configuration(
                null, null, null, null, null, null,
                new HashMap<>(), new HashMap<>(), new Date()
        );
        // => Act
        double result = manager.computeTotalPrice(configuration);
        // => Assert
        assertEquals(0.0, result, 0.01);
    }
    @Test
    void computeTotalPrice_withProcessor() {
        // Arrange
        Processor processor = new Processor("Intel Core i5", "LGA1700", "Intel",
                6, 12, 65, 3.5, 4.9, 300.0, false, new Date());
        Configuration configuration = new Configuration(
                null, processor, null, null, null, null,
                new HashMap<>(), new HashMap<>(), new Date()
        );
        // Act
        double result = manager.computeTotalPrice(configuration);
        // Assert
        assertEquals(300.0, result, 0.01);
    }

    @Test
    void computeTotalPrice_withStorage() {
        // Arrange
        Storage storage = new Storage( "Samsung 870 EVO", "SSD", "SATA", "Samsung",
                1000, 530, 560, 100.0);
        HashMap<Storage, Integer> storages = new HashMap<>();
        storages.put(storage, 2); // 2x le prix du storage
        Configuration configuration = new Configuration(
                null, null, null, null, null, null,
                storages, new HashMap<>(), new Date()
        );
        // Act
        double result = manager.computeTotalPrice(configuration);
        // Assert
        assertEquals(200.0, result, 0.01);
    }

    @Test
    void computeTotalPrice_avecCooling() {
        // Arrange
        Cooling cooling = new Cooling("Noctua NH-D15", "Air", "LGA1700", "Noctua",
                150, 165, 1320, 135, 250, 50.0);
        HashMap<Cooling, Integer> coolings = new HashMap<>();
        coolings.put(cooling, 3); // 3x le prix du cooling
        Configuration configuration = new Configuration(
                null, null, null, null, null, null,
                new HashMap<>(), coolings, new Date()
        );
        // Act
        double result = manager.computeTotalPrice(configuration);
        // Assert
        assertEquals(150.0, result, 0.01);
    }
    @Test
    void computeTotalPrice_configComplete() {
        // Arrange
        Processor processor = new Processor(
                "Intel Core i5", "LGA1700", "Intel",
                6, 12, 65, 3.5, 4.9, 300.0, false, new Date()
        );
        Storage storage = new Storage(
                "Samsung 870 EVO", "SSD", "SATA", "Samsung",
                1000, 530, 560, 100.0
        );
        Cooling cooling = new Cooling(
                "Noctua NH-D15", "Air", "LGA1700", "Noctua",
                150, 165, 1320, 135, 250, 50.0
        );
        HashMap<Storage, Integer> storages = new HashMap<>();
        storages.put(storage, 2);

        HashMap<Cooling, Integer> coolings = new HashMap<>();
        coolings.put(cooling, 1);

        Configuration configuration = new Configuration(
                null, processor, null, null, null, null,
                storages, coolings, new Date()
        );
        // Act
        double result = manager.computeTotalPrice(configuration);
        // Assert
        assertEquals(550.0, result, 0.01);
    }
    @Test
    void computeTotalPrice_withMotherBoard() {
        // Arrange
        MotherBoard motherBoard = new MotherBoard(
                "ASUS ROG", "Z790", "LGA1700", "ATX", "DDR5", "ASUS", 4, 250.0
        );
        Configuration configuration = new Configuration(
                null, null, null, null, motherBoard, null,
                new HashMap<>(), new HashMap<>(), new Date()
        );
        // Act
        double result = manager.computeTotalPrice(configuration);
        // Assert
        assertEquals(250.0, result, 0.01);
    }

    @Test
    void computeTotalPrice_withRam() {
        // Arrange
        Ram ram = new Ram("Corsair Vengeance", "DDR5", "Corsair", 32, 2, 6000, 120.0);
        Configuration configuration = new Configuration(
                null, null, null, null, null, ram,
                new HashMap<>(), new HashMap<>(), new Date()
        );
        // Act
        double result = manager.computeTotalPrice(configuration);
        // Assert
        assertEquals(120.0, result, 0.01);
    }

    @Test
    void computeTotalPrice_withGraphicCard() {
        // Arrange
        GraphicCard graphicCard = new GraphicCard(
                "RTX 4070", "AD104", 12, "GDDR6X", 336, 140, 60, 200, 600.0, "Nvidia"
        );
        Configuration configuration = new Configuration(
                null, null, graphicCard, null, null, null,
                new HashMap<>(), new HashMap<>(), new Date()
        );
        // Act
        double result = manager.computeTotalPrice(configuration);
        // Assert
        assertEquals(600.0, result, 0.01);
    }

    @Test
    void computeTotalPrice_withCase() {
        // Arrange
        Case computerCase = new Case(
                "NZXT H510", "ATX", "NZXT", 90.0, 381, 165, 2, false
        );
        Configuration configuration = new Configuration(
                null, null, null, computerCase, null, null,
                new HashMap<>(), new HashMap<>(), new Date()
        );
        // Act
        double result = manager.computeTotalPrice(configuration);
        // Assert
        assertEquals(90.0, result, 0.01);
    }

    @Test
    void computeTotalPrice_withProcessorAndCase() {
        // Arrange
        Processor processor = new Processor(
                "Intel Core i5", "LGA1700", "Intel",
                6, 12, 65, 3.5, 4.9, 300.0, false, new Date()
        );
        Case computerCase = new Case(
                "NZXT H510", "ATX", "NZXT", 90.0, 381, 165, 2, false
        );
        Configuration configuration = new Configuration(
                null, processor, null, computerCase, null, null,
                new HashMap<>(), new HashMap<>(), new Date()
        );
        // Act
        double result = manager.computeTotalPrice(configuration);
        // Assert
        assertEquals(390.0, result, 0.01);
    }

    @Test
    void computeTotalPrice_withMotherBoardAndRam() {
        // Arrange
        MotherBoard motherBoard = new MotherBoard(
                "ASUS ROG", "Z790", "LGA1700", "ATX", "DDR5", "ASUS", 4, 250.0
        );
        Ram ram = new Ram("Corsair Vengeance", "DDR5", "Corsair", 32, 2, 6000, 120.0);
        Configuration configuration = new Configuration(
                null, null, null, null, motherBoard, ram,
                new HashMap<>(), new HashMap<>(), new Date()
        );
        // Act
        double result = manager.computeTotalPrice(configuration);
        // Assert
        assertEquals(370.0, result, 0.01);
    }

    @Test
    void computeTotalPrice_withGraphicCardAndStorage() {
        // Arrange
        GraphicCard graphicCard = new GraphicCard(
                "RTX 4070", "AD104", 12, "GDDR6X", 336, 140, 60, 200, 600.0, "Nvidia"
        );
        Storage storage = new Storage(
                "Samsung 870 EVO", "SSD", "SATA", "Samsung", 1000, 530, 560, 100.0
        );
        HashMap<Storage, Integer> storages = new HashMap<>();
        storages.put(storage, 2); // 200€
        Configuration configuration = new Configuration(
                null, null, graphicCard, null, null, null,
                storages, new HashMap<>(), new Date()
        );
        // Act
        double result = manager.computeTotalPrice(configuration);
        // Assert
        assertEquals(800.0, result, 0.01);
    }
}