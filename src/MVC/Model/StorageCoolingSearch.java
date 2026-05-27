package MVC.Model;

import java.util.Date;

public class StorageCoolingSearch {
    private int configurationId;
    private int userId;
    private Date creationDate;
    private int storageCapacity;
    private int readingSpeed;
    private int writingSpeed;
    private String coolingType;
    private int coolingLength;
    private int coolingHeight;

    public StorageCoolingSearch(int configurationId, int userId, Date creationDate,
                                int storageCapacity, int readingSpeed, int writingSpeed, String coolingType,
                                int coolingLength, int coolingHeight) {
        this.configurationId = configurationId;
        this.userId = userId;
        this.creationDate = creationDate;
        this.storageCapacity = storageCapacity;
        this.readingSpeed = readingSpeed;
        this.writingSpeed = writingSpeed;
        this.coolingType = coolingType;
        this.coolingLength = coolingLength;
        this.coolingHeight = coolingHeight;
    }

    public int getConfigurationId() {
        return configurationId;
    }

    public int getUserId() {
        return userId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public int getStorageCapacity() {
        return storageCapacity;
    }

    public int getReadingSpeed() {
        return readingSpeed;
    }

    public int getWritingSpeed() {
        return writingSpeed;
    }

    public String getCoolingType() {
        return coolingType;
    }

    public int getCoolingLength() {
        return coolingLength;
    }

    public int getCoolingHeight() {
        return coolingHeight;
    }
}
