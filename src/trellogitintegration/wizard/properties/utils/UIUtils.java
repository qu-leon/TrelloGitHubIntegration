package trellogitintegration.wizard.properties.utils;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class UIUtils {

  public static void addLabel(Composite parent, String text) {
    Label label = new Label(parent, SWT.NONE);
    label.setText(text);
  }
  
  public static Text addInputTextBox(Composite parent, GridData gridData, String defaultText) {
    Text textbox = new Text(parent, SWT.SEARCH | SWT.SINGLE | SWT.BORDER);
    textbox.setLayoutData(gridData);
    textbox.setText(defaultText);
    return textbox;
  }
  
  public static void addSeparator(Composite parent) {
    Label separator = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL);
    GridData gridData = new GridData();
    gridData.horizontalAlignment = GridData.FILL;
    gridData.grabExcessHorizontalSpace = true;
    separator.setLayoutData(gridData);
  }  
  
  public static void uniformButtonSize(Composite parent, Button... buttons) {
    parent.layout();
    int maxHeight = 0;
    int maxWidth = 0;
    for (int i = 0; i < buttons.length; i++) {
      Point size = buttons[i].getSize();
      if (size.x > maxWidth) {
        maxWidth = size.x;
      }
      if (size.y > maxHeight) {
        maxHeight = size.y;
      }
    }
    
    GridData gridData = new GridData();
    gridData.widthHint = maxWidth;
    gridData.heightHint = maxHeight;
    
    for (int i = 0; i < buttons.length; i++) {
      buttons[i].setLayoutData(gridData);
    }
  }
  
  public static void centerShell(Composite parent, Composite child) {
    Monitor primary = parent.getDisplay().getPrimaryMonitor();
    Rectangle bounds = primary.getBounds();
    Rectangle rect = child.getBounds();
    
    int x = bounds.x + (bounds.width - rect.width) / 2;
    int y = bounds.y + (bounds.height - rect.height) / 2;
    
    child.setLocation(x, y);
  }
}