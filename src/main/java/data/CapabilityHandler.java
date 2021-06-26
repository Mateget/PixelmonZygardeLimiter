package data;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import zygardelimiter.ZygardeLimiter;

/**
 * Capability handler
 * 
 * This class is responsible for attaching our capabilities
 */

public class CapabilityHandler {
	 public static final ResourceLocation ZYGARDE_CAP = new ResourceLocation(ZygardeLimiter.MODID, "zygardetime");
	 
	 @SubscribeEvent
	 public void attachCapability(AttachCapabilitiesEvent<Entity> event) {
		 if (event.getObject() instanceof EntityPlayer)
			 event.addCapability(ZYGARDE_CAP, new ZygardeTimeProvider());
	 }
}

