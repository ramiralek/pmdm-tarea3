package condeceron.beatriz.pmdmtarea3;

import java.util.List;

public class Pokemon {

    private String image;
    private String name;
    private String index;
    private String firstType;
    private String secondType;
    private String height;
    private String weight;

    public Pokemon(String image, String name, String index, String firstType, String secondType, String height, String weight) {
        this.image = image;
        this.name = name;
        this.index = index;
        this.firstType = firstType;
        this.secondType = secondType;
        this.height = height;
        this.weight = weight;
    }

    public Pokemon(String image, String name, String index, String firstType, String height, String weight) {
        this.image = image;
        this.name = name;
        this.index = index;
        this.firstType = firstType;
        this.height = height;
        this.weight = weight;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getIndex() {
        return index;
    }

    public String getFirstType() {
        return firstType;
    }

    public String getSecondType() {
        return secondType;
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public void setFirstType(String firstType) {
        this.firstType = firstType;
    }

    public void setSecondType(String secondType) {
        this.secondType = secondType;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

}
