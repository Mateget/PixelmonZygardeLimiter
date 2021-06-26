package zygardelimiter;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pixelmonmod.pixelmon.Pixelmon;

import config.FileHandler;
import data.CapabilityHandler;
import data.IZygardeTime;
import data.ZygardeTime;
import data.ZygardeTimeProvider;
import data.ZygardeTimeStorage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = ZygardeLimiter.MODID,
        name = ZygardeLimiter.MODNAME,
        version = ZygardeLimiter.VERSION,
        acceptableRemoteVersions = "*",
        dependencies = "after:" + Pixelmon.MODID,
        acceptedMinecraftVersions = "[1.12.2]",
        useMetadata = true
    )

public class ZygardeLimiter {

    public static final String MODID = "zygardelimiter";
    public static final String MODNAME = "Zygarde Limiter";
    public static final String VERSION = "1.0";
    public static Logger logger = LogManager.getLogger(MODNAME);
    public static PixelmonEvents events;
    
    public static File configDir;
    public static File configFile;

    @SuppressWarnings("deprecation")
	@Mod.EventHandler 
    public void onPreInit(FMLPreInitializationEvent e) {
    	
    	
    	logger = e.getModLog();
    	logger.info("Booting up " + MODNAME + " - " + VERSION);
    	
    	configDir = new File(e.getModConfigurationDirectory() + "/" + MODID);
        configDir.mkdir();
        configFile = new File(configDir, "config.json");
    	
    	FileHandler.readConfig();
        FileHandler.creationCheck();
        FileHandler.writeConfig();
    	
        events = new PixelmonEvents();
        CapabilityManager.INSTANCE.register(IZygardeTime.class, new ZygardeTimeStorage(), ZygardeTime.class);
        MinecraftForge.EVENT_BUS.register(new ZygardeTimeProvider());
        MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
        logger.info("Finished the booting process");
    }

}
