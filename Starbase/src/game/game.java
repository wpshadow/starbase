package game;

import org.lwjgl.Sys;
import XE_Core.XE_Display;
import XE_Core.XE_FPS;

public class game {
	
	
	public static final String TITLE = "Starbase";
	public static int WIDTH 	= 800;
	public static int HEIGHT 	= 600;
	public static int MAXFRAMES = 0;
	
	public static loader load = new loader();
	
	
	public static void main(String[] args){
		
		try {
			// init the game
			 init();
			// run the mainloop
			run();
		} catch (Exception e) {
			e.printStackTrace();
			Sys.alert(TITLE, "An error occured and the game will exit.");
		} finally {
			// clear all up on error
			clear();
		}
		
	}
	
	
	
	
	
	public static void init() throws Exception{
		XE_Display.init(WIDTH, HEIGHT);
		XE_Display.setTitle(TITLE);
		XE_Display.enableVSYNC(false);
		XE_Display.setIcon("res/icons/icon16.png", "res/icons/icon32.png");
		
		load.loadGround0();
		
		
	}
	
	public static void run(){
		while(!XE_Display.isCloseRequested()){
			
			
			
			input();
			render();
			update();
		}
		clear();
	}
	
	public static void input(){
		// input and logic here!
	}
	
	public static void render(){
		// draw stuff here!
		load.drawGround0();
	}
	
	public static void update(){	
		XE_FPS.updateFPS();
		XE_Display.setTitle(TITLE+"           ---          FPS: "+XE_FPS.getFPS());
		XE_Display.update();
		XE_Display.clear();
		//XE_Display.limitFPS(MAXFRAMES);
	}
	
	public static void clear(){
		XE_Display.destroy();
	}
	
	
	
}
