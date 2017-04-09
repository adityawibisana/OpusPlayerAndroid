package aw.com.events;

/**
 * Created by Adit on 4/9/2017.
 */

public class PlaylistClickedEvent {
    private String selectedFilePath;

    public String getSelectedFilePath() {
        return selectedFilePath;
    }

    public void setSelectedFilePath(String selectedFilePath) {
        this.selectedFilePath = selectedFilePath;
    }
}
