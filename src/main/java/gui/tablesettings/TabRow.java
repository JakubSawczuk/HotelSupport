package gui.tablesettings;

/**
 * Created by Kuba on 2018-01-18.
 */
public class TabRow {

    private String first;
    private String second;

    public TabRow(String first, String second) {
        this.first = first;
        this.second = second;
    }

    public String getFirst() {
        return first;
    }

    public String getSecond() {
        return second;
    }


    public void setFirst(String first) {
        this.first = first;
    }

    public void setSecond(String second) {
        this.second = second;
    }
}

