/**
 * 
 */
package mmb.engine.craft;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import mmb.engine.json.JsonTool;

/**
 * Combination of inputs and outputs
 * @author oskar
 */
public class ProcessIngredients{
	/** An empty list of processing ingredients*/
	@Nonnull public static final ProcessIngredients EMPTY = new ProcessIngredients(RecipeOutput.NONE, RecipeOutput.NONE);
	/** Input items */
	@Nonnull public final RecipeOutput input;
	/** Output items */
	@Nonnull public final RecipeOutput output;
	/**
	 * Creates a list of processing ingredients
	 * @param input
	 * @param output
	 */
	public ProcessIngredients(RecipeOutput input, RecipeOutput output) {
		this.input = input;
		this.output = output;
	}
	/**
	 * Saves this list of processing ingredients
	 * @return a JSON array with representation of this list of processing ingredients
	 */
	@Nonnull public ArrayNode save() {
		ArrayNode node = JsonTool.newArrayNode();
		node.add(ItemLists.save(input));
		node.add(ItemLists.save(output));
		return node;
	}
	/**
	 * Loads a list of processing ingredients from JSON
	 * @param data a JSON array with representation of a list of processing ingredients
	 * @return a list of processing ingredients
	 */
	@Nullable public static ProcessIngredients read(@Nullable JsonNode data) {
		if(data == null) return null;
		RecipeOutput a = ItemLists.read(data.get(0));
		if(a == null) return null;
		RecipeOutput b = ItemLists.read(data.get(1));
		if(b == null) return null;
		return new ProcessIngredients(a, b);
	}
	/**
	 * Saves a list of processing ingredients
	 * @param x a list of processing ingredients
	 * @return a JSON array with representation of this list of processing ingredients
	 */
	@Nullable public static ArrayNode save(@Nullable ProcessIngredients x) {
		if(x == null) return null;
		return x.save();
	}
}
