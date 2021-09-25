/**
 * 
 */
package mmb.WORLD.blocks.gates;

import mmb.DATA.contents.texture.Textures;
import mmb.WORLD.block.BlockType;
import mmb.WORLD.blocks.ContentsBlocks;
import mmb.WORLD.rotate.RotatedImageGroup;

/**
 * @author oskar
 *
 */
public class FlipGate extends AbstractStateGate {

	@Override
	public BlockType type() {
		return ContentsBlocks.TOGGLE;
	}

	private RotatedImageGroup ON = RotatedImageGroup.create(Textures.get("logic/toggle on.png"));
	private RotatedImageGroup OFF = RotatedImageGroup.create(Textures.get("logic/toggle off.png"));
	
	@Override
	protected RotatedImageGroup getOnImage() {
		return ON;
	}

	@Override
	protected RotatedImageGroup getOffImage() {
		return OFF;
	}

	@Override
	protected boolean run(boolean a) {
		if(a) state = !state;
		return state;
	}

}
