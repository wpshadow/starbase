package XE_Core;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class XE_Mouse {
	
	public static boolean isButtonDown(int buttonnumber){
		if(Mouse.isButtonDown(buttonnumber)){
			return true;
		}else{
			return false;
		}
	}

	public static int getX() {
		return Mouse.getX();
	}

	public static int getY() {
		return Display.getHeight()-1-Mouse.getY();
	}
	
	public static int getDX(){
		return Mouse.getDX();
	}
	
	public static int getDY(){
		return Mouse.getDY();
	}
	
	public static void setGrabbed(boolean grab){
		Mouse.setGrabbed(grab);
	}
	
}
