package data;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class ZygardeTimeStorage implements IStorage<IZygardeTime>{

	/**
	 * This class is responsible for saving and reading zygarde time data from or to server
	 */
	
	@Override
	public NBTBase writeNBT(Capability<IZygardeTime> capability, IZygardeTime instance, EnumFacing side) {
		NBTTagCompound zygardeTime = new NBTTagCompound();
		zygardeTime.setLong("zygardetime", instance.getTime());
		return zygardeTime;
	}

	@Override
	public void readNBT(Capability<IZygardeTime> capability, IZygardeTime instance, EnumFacing side, NBTBase nbt) {
		long time = ((NBTTagCompound) nbt).getLong("zygardetime");
		instance.setTime(time);
    }
	
}