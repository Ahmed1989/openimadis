/**
 * openImaDis - Open Image Discovery: Image Life Cycle Management Software
 * Copyright (C) 2011-2016  Strand Life Sciences
 *   
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.strandgenomics.imaging.iacquisition.memory;

import java.net.URL;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

/**
 * This class represents a button factory which builds
 * buttons from the content of a resource bundle. <br>
 *
 * The resource entries format is (for a button named 'Button'):<br>
 * <pre>
 *   Button.text      = text
 *   Button.icon      = icon_name 
 *   Button.mnemonic  = mnemonic 
 *   Button.action    = action_name
 *   Button.selected  = true | false
 *   Button.tooltip   = tool tip text
 * where
 *   text, icon_name and action_name are strings
 *   mnemonic is a character
 * </pre>
 *
 * @author <a href="mailto:stephane@hillion.org">Stephane Hillion</a>
 * @version $Id: ButtonFactory.java 6418 2005-05-11 15:03:27Z anand $
 */
public class ButtonFactory extends ResourceManager {
    // Constants
    //
    private final static String ICON_SUFFIX        = ".icon";
    private final static String TEXT_SUFFIX        = ".text";
    private final static String MNEMONIC_SUFFIX    = ".mnemonic";
    private final static String ACTION_SUFFIX      = ".action";
    private final static String SELECTED_SUFFIX    = ".selected";
    private final static String TOOLTIP_SUFFIX     = ".tooltip";

    /** The table which contains the actions */
    private ActionMap actions;

    /**
     * Creates a new button factory
     * @param rb the resource bundle that contains the buttons
     *           description.
     * @param am the actions to bind to the button
     */
    public ButtonFactory(ResourceBundle rb, ActionMap am) {
        super(rb);
        actions = am;
    }

    private Object bundle = new Object();

    /**
     * Creates and returns a new swing button
     * @param name the name of the button in the resource bundle
     * @throws MissingResourceException if key is not the name of a button.
     *         It is not thrown if the mnemonic and the action keys are missing
     * @throws ResourceFormatException if the mnemonic is not a single
     *         character
     * @throws MissingListenerException if the button action is not found in
     *         the action map
     */
    public JButton createJButton(String name)
	throws MissingResourceException,
	       ResourceFormatException,
	       MissingListenerException {
        JButton result;
	try {
	    result = new JButton(getString(name+TEXT_SUFFIX));
	} catch (MissingResourceException e) {
	    result = new JButton();
	}
	initializeButton(result, name);
        return result;
    }

    /**
     * Creates and returns a new swing button initialised
     * to be used as a toolbar button
     * @param name the name of the button in the resource bundle
     * @throws MissingResourceException if key is not the name of a button.
     *         It is not thrown if the mnemonic and the action keys are missing
     * @throws ResourceFormatException if the mnemonic is not a single
     *         character
     * @throws MissingListenerException if the button action is not found in
     *         the action map
     */
    public JButton createJToolbarButton(String name)
	throws MissingResourceException,
	       ResourceFormatException,
	       MissingListenerException {
        JButton result;
	try {
	    result = new JToolbarButton(getString(name+TEXT_SUFFIX));
	} catch (MissingResourceException e) {
	    result = new JToolbarButton();
	}
	initializeButton(result, name);
        return result;
    }

    /**
     * Creates and returns a new swing radio button
     * @param name the name of the button in the resource bundle
     * @throws MissingResourceException if key is not the name of a button.
     *         It is not thrown if the mnemonic and the action keys are
     *         missing.
     * @throws ResourceFormatException if the mnemonic is not a single
     *         character.
     * @throws MissingListenerException if the button action is not found in
     *         the action map.
     */
    public JRadioButton createJRadioButton(String name)
	throws MissingResourceException,
	       ResourceFormatException,
	       MissingListenerException {
        JRadioButton result = new JRadioButton(getString(name+TEXT_SUFFIX));
	initializeButton(result, name);

        // is the button selected?
	try {
	    result.setSelected(getBoolean(name+SELECTED_SUFFIX));
	} catch (MissingResourceException e) {
	}
	
        return result;
    }

    /**
     * Creates and returns a new swing check box
     * @param name the name of the button in the resource bundle
     * @throws MissingResourceException if key is not the name of a button.
     *         It is not thrown if the mnemonic and the action keys are missing
     * @throws ResourceFormatException if the mnemonic is not a single
     *         character.
     * @throws MissingListenerException if the button action is not found in
     *         the action map.
     */
    public JCheckBox createJCheckBox(String name)
	throws MissingResourceException,
	       ResourceFormatException,
	       MissingListenerException {
        JCheckBox result = new JCheckBox(getString(name+TEXT_SUFFIX));
	initializeButton(result, name);

        // is the button selected?
	try {
	    result.setSelected(getBoolean(name+SELECTED_SUFFIX));
	} catch (MissingResourceException e) {
	}
	
        return result;
    }

    /**
     * Initializes a button
     * @param b    the button to initialize
     * @param name the button's name
     * @throws ResourceFormatException if the mnemonic is not a single
     *         character.
     * @throws MissingListenerException if the button action is not found
     *         in the action map.
     */
    private void initializeButton(AbstractButton b, String name)
	throws ResourceFormatException, MissingListenerException {
        // Action
	try {
	    Action a = actions.getAction(getString(name+ACTION_SUFFIX));
	    if (a == null) {
		throw new MissingListenerException("", "Action",
                                                   name+ACTION_SUFFIX);
	    }
	    b.setAction(a);
            try {
                b.setText(getString(name+TEXT_SUFFIX));
            } catch (MissingResourceException mre) {
                // not all buttons have text defined so just
                // ignore this exception.
            }
	    /*
	    if (a instanceof JComponentModifier) {
		((JComponentModifier)a).addJComponent(b);
	    }
	    */
	} catch (MissingResourceException e) {
	}

	// Icon
	try {
	    String s = getString(name+ICON_SUFFIX);
	    //URL url  = actions.getClass().getResource(s);
	    URL url = null; //XXX: Anand
	    if (url != null) {
		b.setIcon(new ImageIcon(url));
	    }
	} catch (MissingResourceException e) {
	}

        // Mnemonic
	try {
	    String str = getString(name+MNEMONIC_SUFFIX);
	    if (str.length() == 1) {
		b.setMnemonic(str.charAt(0));
	    } else {
		throw new ResourceFormatException("Malformed mnemonic",
						  bundle.getClass().getName(),
						  name+MNEMONIC_SUFFIX);
	    }
	} catch (MissingResourceException e) {
	}

	// ToolTip
	try {
	    String s = getString(name+TOOLTIP_SUFFIX);
	    if (s != null) {
		b.setToolTipText(s);
	    }
	} catch (MissingResourceException e) {
	}
    }

    private static class MissingListenerException extends RuntimeException {
	public MissingListenerException(String a, String b, String c) {
	    super(a + b + c);
	}
    }

    private static class ResourceFormatException extends RuntimeException {
	public ResourceFormatException(String a, String b, String c) {
	    super(a + b + c);
	}
    }

    private static class JToolbarButton extends JButton {
	public JToolbarButton() {
	    super();
	}

	public JToolbarButton(String title) {
	    super(title);
	}
    }
}
