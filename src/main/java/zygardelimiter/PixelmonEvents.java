package zygardelimiter;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.events.legendary.ZygardeEvent;

import config.FileHandler;
import data.IZygardeTime;
import data.ZygardeTimeProvider;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PixelmonEvents {

	public final int COOLDOWN = FileHandler.config.getDelay();
	public final String MESSAGE = FileHandler.config.getMessage();

	public PixelmonEvents() {
		Pixelmon.EVENT_BUS.register(this);
	}

	private void setCooldownToPlayer(EntityPlayerMP player) {
		IZygardeTime zygardeTime = player.getCapability(ZygardeTimeProvider.ZYGARDTIME_CAP, null);
		long currentTime = new Timestamp(System.currentTimeMillis()).getTime();
		long delay = COOLDOWN * 60 * 1000;
		zygardeTime.setTime(currentTime + delay);
	}

	@SubscribeEvent
	public void onZygardeConstructMerge(ZygardeEvent.Assemble.Merge.End event) {
		EntityPlayerMP player = event.player;
		setCooldownToPlayer(player);
	}

	@SubscribeEvent
	public void onZygardeConstructNew(ZygardeEvent.Assemble.New.End event) {
		EntityPlayerMP player = event.player;
		setCooldownToPlayer(player);
	}

	@SubscribeEvent
	public void onZygardeConstructNew(ZygardeEvent.Select event) {
		EntityPlayerMP player = event.player;
		if (event.mode.toString() == "ASSEMBLING") {

			long currentTime = new Timestamp(System.currentTimeMillis()).getTime();
			long delayTime = player.getCapability(ZygardeTimeProvider.ZYGARDTIME_CAP, null).getTime();
			long diff = TimeUnit.MILLISECONDS.toMinutes(delayTime - currentTime);
			if(diff>0) {
				player.sendMessage(new TextComponentString(TextFormatting.RED + MESSAGE.replace("%DELAY%", String.valueOf(diff))));
				event.setCanceled(true);
			}

		    

		}

	}
}
