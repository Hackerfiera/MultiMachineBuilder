/**
 * 
 */
package mmb.content.imachine.filter;

import javax.annotation.Nullable;

import mmb.engine.inv.storage.SingleItemInventory;
import mmbbase.cgui.DestroyTab;

/**
 * @author oskar
 * Provides filtering settings for pipes and other machines
 */
public interface ControllableFilter extends DestroyTab{
	/**
	 * 
	 * @return a list of filters
	 */
	public SingleItemInventory[] getFilters();
	/**
	 * Gets the list of titles.
	 * Returns null if filters are not named
	 * If result is non-null, the number of titles must match number of filters,
	 * otherwise the {@link FilterGUI} will fail with {@link IllegalStateException};
	 * @return a list of filter titles
	 */
	@Nullable public String[] getTitles();
	
}
