package Fields;

public abstract class Field {
    private int sortingWeight;
    protected String showName;

    // constructor

    public Field(String showName){
        sortingWeight = 0;
        this.showName = showName;
    }

    public Field(String showName, int sortingWeight){
        this.sortingWeight = sortingWeight;
        this.showName = showName;
    }

    // methods

    // setter

    public void setSortingWeight(int sortingWeight) {
        this.sortingWeight = sortingWeight;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    // getter

    public int getSortingWeight() {
        return sortingWeight;
    }

    public String getShowName() {
        return showName;
    }
}
