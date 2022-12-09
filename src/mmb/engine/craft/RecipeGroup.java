/**
 * 
 */
package mmb.engine.craft;

import java.util.Set;

import javax.annotation.Nonnull;
import javax.swing.ListCellRenderer;

import mmb.engine.item.ItemEntry;
import mmbbase.beans.Titled;
import mmbbase.menu.world.craft.RecipeView;
import monniasza.collects.Identifiable;

/**
 * Stores recipes.
 * @author oskar
 * @param <T> type of recipes
 * 
 */
public interface RecipeGroup<T extends Recipe<?>> extends Identifiable<String>, Titled{
	/**@return a set with recipes*/
	public Set<@Nonnull T> recipes();
	
	/**@return a set with all supported items*/
	public Set<@Nonnull ? extends ItemEntry> supportedItems();
	
	public @Nonnull RecipeView<T> createView();
	
	/**
	 * @return a cell renderer for compatible recipes
	 */
	public @Nonnull ListCellRenderer<? super T> cellRenderer();
	
	/**
	 * @return does the recipe group support catalysts?
	 */
	public boolean isCatalyzed();
}
