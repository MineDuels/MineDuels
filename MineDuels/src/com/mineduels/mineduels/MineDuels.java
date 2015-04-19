/*

A lot of testing going on here. Don't remind me how broken everything would be right now.

*/

package com.mineduels.mineduels;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MineDuels extends JavaPlugin {

	private static MineDuels instance;
	
	private static Logger logger =
			Logger.getLogger("MineDuels");
	private static PluginManager pm =
			Bukkit.getPluginManager();
	
	private static final Runnable shutdownTask = new Runnable()
	{
		
		@Override
		public void run()
		{
			Bukkit.getServer().shutdown();
		}
		
	}, closeDatabases = new Runnable()
	{
		
		@Override
		public void run()
		{
			// SQL Closure
		}
		
	}, openDatabases = new Runnable()
	{
		
		@Override
		public void run()
		{
			// SQL Open
		}
		
	}, closeArenas = new Runnable()
	{
		
		@Override
		public void run()
		{
			// Arena.close
				// This means kicking out all the players!
				// Making sure no-one else can join
		}
		
	}, openArenas = new Runnable()
	{
		
		@Override
		public void run()
		{
			// Arena.open
				// Allow people to join the arenas!
		}
		
	};
	
	@Override
	public void onLoad()
	{
		println("The plugin has now been loaded.");
		runTask(openDatabases);
	}
	
	@Override
	public void onEnable()
	{
		instance = this;
		runTask(openArenas);
		println("The plugin has now been enabled.");
		scheduleShutdown(5);
	}
	
	@Override
	public void onDisable()
	{
		runTask(closeDatabases);
		runTask(closeArenas);
		
		// Make sure it prints this last.
		// Just to make sure it's logical.
		println("The plugin has now been disabled.");
	}
	
	// Print to console
	public static void println(String output)
	{
		logger.info(output);
	}
	
	// Schedule Restart
	/**
	 * This is used to schedule a shutdown. It will not restart automatically.
	 * @param seconds The seconds until a shutdown occurs.
	 */
	public static void scheduleShutdown(int seconds)
	{
		Bukkit.getServer().broadcastMessage("The server will shutdown in " + seconds + " seconds.");
		schedule(shutdownTask, seconds);
	}
	
	/**
	 * This is used to schedule a shutdown. It will not restart automatically.
	 * @param message The message to be broadcasted to the server.
	 * @param seconds The seconds until a shutdown occurs.
	 */
	public static void scheduleShutdown(String message, int seconds)
	{
		Bukkit.getServer().broadcastMessage(message);
		schedule(shutdownTask, seconds);
	}
	
	// Schedule any task
	public static void schedule(Runnable runnable, int seconds)
	{
		Bukkit.getScheduler().scheduleSyncDelayedTask(instance, runnable, seconds * 20);
	}
	
	// Quick run any task
	public static void runTask(Runnable task)
	{
		task.run();
	}
	
}
