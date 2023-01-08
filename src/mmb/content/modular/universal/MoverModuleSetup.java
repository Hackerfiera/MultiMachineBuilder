/**
 * 
 */
package mmb.content.modular.universal;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

import mmb.NN;
import mmb.content.modular.gui.SafeCloseable;
import mmb.data.variables.ListenableValue;
import mmb.engine.settings.GlobalSettings;
import mmb.menu.components.ItemSelectionSlot;
import mmb.menu.world.inv.InventoryController;

import java.awt.Color;
import javax.swing.JSpinner;

/**
 *
 * @author oskar
 *
 */
public class MoverModuleSetup extends JPanel implements SafeCloseable{
	private static final long serialVersionUID = -6701441866106804925L;
	
	/** The item selection slot */
	@NN public final ItemSelectionSlot settings;
	/** Configures stack count */
	@NN public final JSpinner stacker;

	/**
	 * Create the panel.
	 * @param invctrl 
	 */
	public MoverModuleSetup(InventoryController invctrl) {
		setLayout(new MigLayout("", "[grow][]", "[][][]"));
		
		JLabel lblSettings = new JLabel(GlobalSettings.$res("modblocks-settings"));
		add(lblSettings, "cell 0 0,growx");
		
		settings = new ItemSelectionSlot();
		settings.setSelector(invctrl::getSelectedItem);
		settings.setTarget(new ListenableValue<>(null));
		settings.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, Color.CYAN));
		add(settings, "cell 1 0");
		
		JLabel lblStacker = new JLabel(GlobalSettings.$res("modblocks-stacker"));
		add(lblStacker, "cell 0 1,growx");
		
		stacker = new JSpinner();
		add(stacker, "cell 1 1,growx");
	}

	@Override
	public void close() {
		settings.close();
	}
}
