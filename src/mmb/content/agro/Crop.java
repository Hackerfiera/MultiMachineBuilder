/**
 * 
 */
package mmb.content.agro;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import mmb.engine.block.BlockEntityData;
import mmb.engine.block.BlockEntry;
import mmb.engine.block.BlockType;
import mmb.engine.chance.Chance;
import mmb.engine.worlds.MapProxy;

/**
 * @author oskar
 * A crop is growable block, which after some time it drops specified items
 */
public class Crop extends BlockEntityData {

	@Override
	public BlockType type() {
		return type;
	}

	@Nonnull private BlockType type;
	@Nonnull private Chance drop;
	private int progress;
	private int time;
	public Crop(BlockType type, int time, Chance drop) {
		super();
		this.type = type;
		this.time = time;
		this.drop = drop;
	}

	@Override
	public void load(@Nullable JsonNode data) {
		if(data == null) return;
		JsonNode progNode = data.get("progress");
		if(progNode != null) progress = progNode.asInt();
	}

	@Override
	protected void save0(ObjectNode node) {
		node.put("progress", progress);
	}

	/**
	 * @return time elapsed since last drop
	 */
	public int getProgress() {
		return progress;
	}

	/**
	 * @param progress new time to count as time elapsed since last drop
	 */
	public void setProgress(int progress) {
		this.progress = progress;
	}

	/**
	 * @return time between successive drops
	 */
	public int getTime() {
		return time;
	}

	/**
	 * @param time new time between successive drops
	 */
	public void setTime(int time) {
		this.time = time;
	}

	@Override
	public void onTick(MapProxy map) {
		progress++;
		if(progress >= time) {
			drop.drop(null, owner(), posX(), posY());
			progress -= time;
		}
	}

	
	@Override
	public BlockEntry blockCopy() {
		Crop copy = new Crop(type, time, drop);
		copy.progress = progress;
		return copy;
	}

}
