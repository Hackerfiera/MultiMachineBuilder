/**
 * 
 */
package mmb.menu.wtool;

import java.awt.Graphics;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.swing.Icon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import mmb.debug.Debugger;
import mmb.menu.world.window.WorldWindow;
import mmb.world.worlds.world.Player;

/**
 * @author oskar
 *
 */
public class ToolSelectionModel {
	private WindowTool toolIL;
	private WindowTool toolTL;
	@Nullable public final WorldWindow window;
	private static final Debugger debug = new Debugger("TOOL SELECTOR");
	public ToolSelectionModel(@Nullable WorldWindow window) {
		this.window = window;
	}

	public void toolSelectedItemList(@Nullable WindowTool tool) {
		debug.printl("IL tool: "+tool);
		toolIL = tool;
		resetTools();
	}
	public void toolSelectedToolList(@Nullable WindowTool tool) {
		debug.printl("TL tool: "+tool);
		toolTL = tool;
		resetTools();
	}
	
	private void resetTools() {
		debug.printl("Reset tools");
		if(toolTL == null) {
			setTool(toolIL);
		} else if(window != null) {
			Player p = window.getPlayer();
			if(!(p == null || p.isCreative())) { //true
				if(toolIL == null) {
					setTool(window.std);
				}else {
					setTool(toolIL);
				}
			}else {
				setTool(toolTL);
			}
		}else {
			setTool(toolTL);
		}
	}

	private WindowTool tool;
	public void setTool(@Nullable WindowTool newTool) {
		if(tool == newTool) return;
		if(tool != null) {
			tool.deselected();
			debug.printl("Old tool: " + tool.id());
		}else {
			debug.printl("Old tool: null");
		}
		if(newTool != null) {
			newTool.selected();
			if(window != null) newTool.setWindow(window);
			debug.printl("New tool: " + newTool.id());
		}else {
			debug.printl("New tool: null");
			return;
		}
		tool = newTool;
	}
	public WindowTool getTool() {
		return tool;
	}

	//TEST
	private static class DummyTool extends WindowTool{
		protected DummyTool(String s) {
			super(s);
		}

		@Override
		public String title() {
			return "Dummy tool";
		}

		@Override
		public Icon getIcon() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void preview(int startX, int startY, double scale, Graphics g) {
			// unused
		}
		
	}
	@Test static void test() {
		//WorldWindow dummyWindow = new WorldWindow();
		ToolSelectionModel test = new ToolSelectionModel(null);
		WindowTool tool1 = new DummyTool("Tool1");
		WindowTool tool2 = new DummyTool("Tool2");
		Assertions.assertNull(test.getTool(), "Tool was not yet selected");
		test.toolSelectedToolList(tool1);
		Assertions.assertSame(tool1, test.getTool(), "Tool1 not properly selected");
		test.toolSelectedToolList(tool2);
		Assertions.assertSame(tool2, test.getTool(), "Tool2 not properly selected");
		test.toolSelectedToolList(tool1);
		Assertions.assertSame(tool1, test.getTool(), "Tool1 not properly selected");
	}
}
