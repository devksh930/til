package daterbindingpropertyeditor.demo;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;

public class EventEditor extends PropertyEditorSupport {
    @Override
    public String getAsText() {
//        getValue(); propertyEditor가 받은 객체를 getValue로 가져올수 있다
        Event event = (Event) getValue();
        return event.getId().toString();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(new Event(Integer.parseInt(text)));
    }
}
