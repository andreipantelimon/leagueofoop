package greatmagician;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GreatMagician implements PropertyChangeListener {
    private String status;

    public void propertyChange(PropertyChangeEvent evt) {
        //this.setStatus((String) evt.getNewValue());
    }
}
