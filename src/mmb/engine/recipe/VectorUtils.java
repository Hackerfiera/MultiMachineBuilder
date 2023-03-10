/**
 * 
 */
package mmb.engine.recipe;

import java.util.Vector;
import java.util.stream.Collectors;

import mmb.NN;

/**
 * @author oskar
 *
 */
public class VectorUtils {
	private VectorUtils() {}
	/**
	 * Converts an item list into an array
	 * @param output recipe output
	 * @return a vector with given contents
	 */
	@NN public static Vector<ItemStack> list2vector(RecipeOutput output){
		return output
				.getContents()
				.object2IntEntrySet()
				.stream()
				.map(ent -> new ItemStack(ent.getKey(), ent.getIntValue()))
				.collect(Collectors.toCollection(Vector::new));
	}
	/** 
	 * Converts an item list into an array 
	 * @param output recipe output
	 * @return an array with given contents
	 */
	@NN public static ItemStack[] list2arr(RecipeOutput output){
		return output
				.getContents()
				.object2IntEntrySet()
				.stream()
				.map(entry -> new ItemStack(entry.getKey(), entry.getIntValue()))
				.toArray(n -> new ItemStack[n]);
	}

}
