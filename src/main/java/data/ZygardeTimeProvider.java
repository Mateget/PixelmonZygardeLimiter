package data;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class ZygardeTimeProvider implements ICapabilitySerializable<NBTBase>{

	 @CapabilityInject(IZygardeTime.class)
	 public static final Capability<IZygardeTime> ZYGARDTIME_CAP = null;

	 private IZygardeTime instance = ZYGARDTIME_CAP.getDefaultInstance();

	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == ZYGARDTIME_CAP;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == ZYGARDTIME_CAP ? ZYGARDTIME_CAP.<T> cast(this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() {
		return ZYGARDTIME_CAP.getStorage().writeNBT(ZYGARDTIME_CAP, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		ZYGARDTIME_CAP.getStorage().readNBT(ZYGARDTIME_CAP, this.instance, null, nbt);
	}
}