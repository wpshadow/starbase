package game;

import XE_2D.XE_Image;

public class loader {

	public XE_Image ground_0;
	public XE_Image ground[] = new XE_Image[100];
	
	public void loadGround0() {
		ground_0 = new XE_Image("res/gfx/ground_0.png");
		ground[0] = new XE_Image("res/gfx/ground_0.png");
		ground[1] = new XE_Image("res/gfx/ground_7.png");
		
		
	}
	
	public XE_Image getGround_0() {
		return ground_0;
	}

	public XE_Image[] getGround() {
		return ground;
	}

	public void drawGround0() {
		for(int x = 0; x<25; x++){
			for(int y = 0; y<19; y++){
				
				getGround()[1].draw(x*32,y*32);
				
				//getGround_0().draw(x*32,y*32);
			}
		}
	}
	
	
	
}


// DONE