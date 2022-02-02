/**
 * 
 */
package mmb.WORLD.blocks.machine.line;

import net.miginfocom.swing.MigLayout;
import mmb.WORLD.crafting.ElectroItemProcessHelper.Refreshable;
import mmb.WORLD.crafting.recipes.SimpleProcessingRecipeGroup.SimpleProcessingRecipe;
import mmb.WORLD.gui.inv.InventoryController;
import mmb.WORLD.gui.inv.InventoryOrchestrator;

import javax.swing.JLabel;
import mmb.WORLD.gui.inv.MoveItems;
import mmb.WORLD.gui.window.GUITab;
import mmb.WORLD.gui.window.WorldWindow;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JProgressBar;
import javax.swing.Box;

/**
 * @author oskar
 *
 */
public class FurnaceGUI extends GUITab implements Refreshable{
	private static final long serialVersionUID = 82163446136100004L;
	
	@Nonnull private InventoryController invPlayer;
	@Nonnull private InventoryOrchestrator inventoryOrchestrator;
	@Nonnull private MoveItems moveItemsInput;
	@Nonnull public final InventoryController invInput;
	@Nonnull private MoveItems moveItemsOutput;
	@Nonnull public final InventoryController invOutput;
	@Nonnull private JButton exit;
	@Nonnull private JLabel lblSmelt;
	@Nonnull private JProgressBar smelt;

	private Furnace furnace;
	private JLabel lblFuelWarn;
	private JLabel lblRecipeWarn;
	private Box verticalBox;
	private JLabel lblFuel;
	private JProgressBar fuel;
	/**
	 * Create the panel.
	 * @param furnace furnace connected to this GUI
	 * @param window world window, in which the furnace GUI is located
	 */
	public FurnaceGUI(Furnace furnace, WorldWindow window) {
		this.furnace = furnace;
		setLayout(new MigLayout("", "[grow][grow][grow]", "[][grow][grow,center][20px,center][grow][29.00]"));
		
		verticalBox = Box.createVerticalBox();
			lblFuelWarn = new JLabel("Note: any fuel items in this place will be consumed for energy.");
			lblFuelWarn.setBackground(Color.YELLOW);
			lblFuelWarn.setOpaque(true);
			verticalBox.add(lblFuelWarn);
			
			lblRecipeWarn = new JLabel("This furnace processes only ULV recipes");
			lblRecipeWarn.setBackground(Color.ORANGE);
			lblRecipeWarn.setOpaque(true);
			verticalBox.add(lblRecipeWarn);
		add(verticalBox, "flowx,cell 2 0,growx");
		
		
		
		invPlayer = new InventoryController(window.getPlayer().inv);
		invPlayer.setTitle("  Player inventory");
		add(invPlayer, "flowx,cell 0 0 1 5,grow");
		
		invInput = new InventoryController(furnace.incoming);
		invInput.setTitle("  Incoming items and fuel");
		add(invInput, "cell 2 1,grow");
		
		moveItemsInput = new MoveItems(invPlayer, invInput);
		add(moveItemsInput, "flowy,cell 1 0 1 2,grow");
		
		exit = new JButton("Exit");
		exit.addActionListener(e -> {
			window.closeWindow(this);
			
		});
		exit.setBackground(Color.RED);
		add(exit, "cell 1 2 1 2,grow");
		
		lblSmelt = new JLabel("Currently smelted:");
		add(lblSmelt, "flowx,cell 2 3");
		
		invOutput = new InventoryController(furnace.output);
		invOutput.setTitle("  Output ");
		add(invOutput, "cell 2 4,grow");
		
		moveItemsOutput = new MoveItems(invPlayer, invOutput, MoveItems.LEFT);
		add(moveItemsOutput, "cell 1 4,grow");
		
		
		inventoryOrchestrator = new InventoryOrchestrator();
		add(inventoryOrchestrator, "cell 0 5 3 1,grow");
		
		smelt = new JProgressBar();
		smelt.setStringPainted(true);
		add(smelt, "cell 2 3,grow");
		
		lblFuel = new JLabel("Fuel level:");
		add(lblFuel, "flowx,cell 2 2,alignx left");
		
		fuel = new JProgressBar();
		fuel.setMaximum(12_000_000);
		fuel.setStringPainted(true);
		fuel.setForeground(Color.ORANGE);
		add(fuel, "cell 2 2,growx");
	}
	
	@Override
	public void refreshProgress(double progress, @Nullable SimpleProcessingRecipe underway) {
		smelt.setValue((int)progress);
		if(underway == null) {
			lblSmelt.setText("Not smelting");
		}else {
			smelt.setMaximum((int)underway.energy);
			lblSmelt.setText("Currently smelted: "+underway.input.type().title());
		}
		double f = furnace.getFuelLevel();
		int f2 = (int) f;
		fuel.setValue(f2);
		fuel.setString(f+"/12'000'000");
	}

	@Override
	public void refreshInputs() {
		invInput.refresh();
	}

	@Override
	public void refreshOutputs() {
		invOutput.refresh();
	}

	@Override
	public void createTab(WorldWindow window) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroyTab(WorldWindow window) {
		furnace.closeWindow();
	}


}
