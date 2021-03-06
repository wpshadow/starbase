package XE_2D;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.Rectangle;

public class XE_CollisionRect {
	private Rectangle rect = new Rectangle();
	private static boolean isDebug = false;
	
	public void setBounds(int x, int y, int width, int height){
		rect.setBounds(x, y, width, height);
	}
	
	public void setPosition(int x, int y){
		rect.setX(x);
		rect.setY(y);
	}
	
	public boolean isCollision(XE_Object2D obj){
		if(isDebug){
			DrawDebug(rect);
			DrawDebug(obj.getRect().rect);;
		}
		
		if(rect.intersects( obj.getRect().rect ) )
			return true;
		
		return false;		
	}
	
	public boolean isCollision(XE_CollisionRect obj){
		if(isDebug){				
			DrawDebug(rect);
			DrawDebug(obj.rect);
		}		
		
		if(rect.intersects( obj.rect ) )
			return true;
		
		return false;		
	}

	public boolean isCollision(int x, int y, int width, int height){
		Rectangle obj = new Rectangle(x, y, width, height);
		
		if(rect.intersects( obj ) )
			return true;
		
		return false;		
	}
	
	public static boolean isCollision(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2){
		Rectangle obj1 = new Rectangle(x1, y1, w1, h1);
		Rectangle obj2 = new Rectangle(x2, y2, w2, h2);
		
		if(isDebug){
			DrawDebugS(obj1);
			DrawDebugS(obj2);
		}
		
		if(obj1.intersects( obj2 ) )
			return true;
		
		return false;		
	}
	
	public static void showDebug(){
		isDebug = true;
	}
	
	private void DrawDebug(Rectangle rect){
		
		int height = rect.getHeight();
		int width = rect.getWidth();
		
		glPushMatrix();
		
		glTranslatef(rect.getX(), rect.getY(), 0);
		glLineWidth(1f);
		glBegin(GL_LINES);
				glColor3f(1, 0, 0);
				glVertex2i(0, 0);
				glVertex2i(width, 0);
				
				glVertex2i(width, 0);
				glVertex2i(width, height);
				
				glVertex2i(width, height);
				glVertex2i(0, height);
				
				glVertex2i(0, height);
				glVertex2i(0, 0);
		glEnd();
	glPopMatrix();
	}	
	
	private static void DrawDebugS(Rectangle rect){
		
		int height = rect.getHeight();
		int width = rect.getWidth();
		
		glPushMatrix();
		
		glTranslatef(rect.getX(), rect.getY(), 0);
		glLineWidth(1f);
		glBegin(GL_LINES);
				glColor3f(1, 0, 0);
				glVertex2i(0, 0);
				glVertex2i(width, 0);
				
				glVertex2i(width, 0);
				glVertex2i(width, height);
				
				glVertex2i(width, height);
				glVertex2i(0, height);
				
				glVertex2i(0, height);
				glVertex2i(0, 0);
		glEnd();
	glPopMatrix();
	}
}
