/**
 * 
 */
package mmb.world.items.data;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.fasterxml.jackson.databind.JsonNode;

import mmb.world.crafting.ProcessIngredients;
import mmb.world.item.ItemEntity;
import mmb.world.item.ItemType;
import mmb.world.items.ContentsItems;
import mmb.world.items.ItemEntry;

/**
 * @author oskar
 * An item for Bill Of Materials, which contains a list of items.
 */
public final class ItemPIngredients extends ItemEntity {
	public ItemPIngredients() {
		//empty
	}
	public ItemPIngredients(ProcessIngredients items) {
		this.items = items;
	}
	
	@Override
	public ItemEntry itemClone() {
		return this;
	}

	@Nonnull private ProcessIngredients items = ProcessIngredients.EMPTY;
	
	/**
	 * @return the item list for this Bill Of Materials. The returned item list is immutable
	 */
	@Nonnull public ProcessIngredients contents() {
		return items;
	}
	
	@Override
	public void load(@Nullable JsonNode data) {
		if(data == null) return;
		ProcessIngredients list0 = ProcessIngredients.read(data);
		if(list0 == null) list0 = ProcessIngredients.EMPTY;
		items = list0;	
	}
	@Override
	public JsonNode save() {
		return ProcessIngredients.save(items);
	}

	@Override
	protected int hash0() {
		return items.hashCode();
	}

	@Override
	protected boolean equal0(ItemEntity other) {
		if(other instanceof ItemPIngredients)
			return ((ItemPIngredients) other).contents().equals(items);
		return false;
	}
	@Override
	public ItemType type() {
		return ContentsItems.BOM;
	}

	

}
