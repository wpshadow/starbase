package XE_Core;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.ImageIOImageData;
import org.newdawn.slick.util.Log;
import XE_3D.XE_Camera;

public class XE_Display {
	
	// Core Variables	
	private static int WIDTH;					// Screensize X


	private static int HEIGHT;					// Screensize Y

	private static int INTERVAL = 60;			// screen refresh rate
	private static boolean is3D = true;		// is Display 3D ?!
	public static String TITLE = "X-Engine"; 	// window-caption 
	
	// 2D Variables
	
	// 3D Variables
	private static int FOV;
	private static float ZNEAR;
	private static float ZFAR;
	
	
	public static int getWIDTH() {
		return WIDTH;
	}

	public static int getHEIGHT() {
		return HEIGHT;
	}
	
	public static void setIcon(String file16x16, String file32x32){
		try {
			Display.setIcon(new ByteBuffer[] {
			        new ImageIOImageData().imageToByteBuffer(ImageIO.read(new File(file16x16)), false, false, null),
			        new ImageIOImageData().imageToByteBuffer(ImageIO.read(new File(file32x32)), false, false, null)
			        });
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void init(int width, int height){
		
		WIDTH = width;
		HEIGHT = height;
			
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setVSyncEnabled(true);
			setTitle(TITLE);
			Display.create();
			XE_Display.Make2D();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
	}
	
	public static void Make3D(int fov, float zNear, float zFar){
		
		if(!is3D){
			setFOV(fov);
			setZNEAR(zNear);
			setZFAR(zFar);			
			
			glEnable(GL_DEPTH_TEST);
			glEnable(GL_ALPHA_TEST);
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			gluPerspective(FOV, (float)Display.getWidth()/(float)Display.getHeight(), ZNEAR, ZFAR);
			glMatrixMode(GL_MODELVIEW);

			XE_Camera.needUpdate = true;
			is3D = true;
		}
		
		if(XE_Camera.getActiveCamera() == -1){
			Log.error("WARNING, NO CAMERA WAS CREATED!");
		}else{
			if(XE_Camera.needUpdate){
				glLoadIdentity();
				float camX = -XE_Camera.cameras.get(XE_Camera.getActiveCamera()).getCamX();
				float camY = -XE_Camera.cameras.get(XE_Camera.getActiveCamera()).getCamY();
				float camZ = -XE_Camera.cameras.get(XE_Camera.getActiveCamera()).getCamZ();
					
				float rotX = XE_Camera.cameras.get(XE_Camera.getActiveCamera()).getRotX();
				float rotY = XE_Camera.cameras.get(XE_Camera.getActiveCamera()).getRotY();
				float rotZ = XE_Camera.cameras.get(XE_Camera.getActiveCamera()).getRotZ();				
							
				glRotatef(rotX, 1, 0, 0);
				glRotatef(rotY, 0, 1, 0);
				glRotatef(rotZ, 0, 0, 1);
				glTranslatef(camX, camY, camZ);
				
				XE_Camera.needUpdate = false;
			}
		}		
		
	}
	
	public static void Make2D(){
		if(is3D){
			glEnable(GL_TEXTURE_2D);
			glShadeModel(GL_SMOOTH);        
			glDisable(GL_DEPTH_TEST);
			glDisable(GL_LIGHTING);                                   
	        glClearDepth(1);                                       
	 
	        glEnable(GL_BLEND);
	        glEnable(GL_ALPHA_TEST);
	        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	 
	        glViewport(0,0,WIDTH,HEIGHT);
			glMatrixMode(GL_MODELVIEW);
	 
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
			glMatrixMode(GL_MODELVIEW);
			glLoadIdentity();
			is3D = false;

		}
	}
	
	public static void clear(){
		// clear the Screen
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glClearColor(0f, 0f, 0f, 1f);
		
	}
	public static void clear(XE_Color col){
		// clear the Screen
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glClearColor((float)col.getR()/255f, (float)col.getG()/255f, (float)col.getB()/255f, 1f);
		
	}
	public static void clear(int R, int G, int B){
		// clear the Screen
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glClearColor((float)R/255f, (float)G/255f, (float)B/255f, 1f);
		
	}	
	
	public static boolean isCloseRequested(){
		return Display.isCloseRequested();	
	}
	
	public static void setTitle(String title){
		TITLE = title;
		Display.setTitle(TITLE);
	}

	public static void setInterval(int interval){
		INTERVAL = interval;
		Display.setSwapInterval(interval);
	}	
	
	public static int getInterval() {
		return INTERVAL;
	}
	
	public static void enableVSYNC(boolean state){
		Display.setVSyncEnabled(state);	
	}
	
	public static void limitFPS(int frames){
		Display.sync(frames);
	}
	
	public static void update(){
		Display.sync(INTERVAL);
		Display.update();
	}
	
	public static void destroy(){
		Display.destroy();
	}

	public static int getFOV() {
		return FOV;
	}

	public static void setFOV(int fOV) {
		FOV = fOV;
	}

	public static float getZNEAR() {
		return ZNEAR;
	}

	public static void setZNEAR(float zNEAR) {
		ZNEAR = zNEAR;
	}

	public static float getZFAR() {
		return ZFAR;
	}

	public static void setZFAR(float zFAR) {
		ZFAR = zFAR;
	}
		
}
