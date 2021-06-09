package sample.utilities;

public enum Categories {
    SCIENCE("Science"), ART("Art"), RELIGION("Religion"), HISTORY("History"), GEOGRAPHY("Geography");
    private Categories(String categoryName) {
        this.categoryName = categoryName;
    }

    private String categoryName;

    @Override
    public String toString() {
        return this.categoryName;
    }
}
