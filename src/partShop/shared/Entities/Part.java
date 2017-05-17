package partShop.shared.Entities;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;

/**
 * Created by Александр on 09.05.2017.
 */
public class Part implements IsSerializable {

    int ID;
    String partNumber;
    String producer;
    String partName12345;
    String description;
    public Part(){}

    public Part(String partNumber, String producer,
                String partName12345, String description) {
        this.partNumber = partNumber;
        this.producer = producer;
        this.partName12345 = partName12345;
        this.description = description;
    }

    public Part(int ID, String partNumber, String producer,
                String partName12345, String description) {
        this.ID = ID;
        this.partNumber = partNumber;
        this.producer = producer;
        this.partName12345 = partName12345;
        this.description = description;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getPartName12345() {
        return partName12345;
    }

    public void setPartName12345(String partName12345) {
        this.partName12345 = partName12345;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {

        return getPartName12345();
    }
}

