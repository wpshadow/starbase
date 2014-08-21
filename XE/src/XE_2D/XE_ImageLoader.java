package XE_2D;

import java.io.IOException;
import java.util.regex.Pattern;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.Log;
import org.newdawn.slick.util.ResourceLoader;

public class XE_ImageLoader {
	
	public Texture texture;
	private String filetype; 	// PNG , BMP, JPG ....
	
	public void loadTexture(String file){
		
		String[] split = file.split(Pattern.quote( "." )); // ex:  "res/ground/ground_0.png"
	
		this.filetype = split[1].toUpperCase();
		
		try {
			// try to load the texture with the given format
			setTexture(TextureLoader.getTexture(filetype, ResourceLoader.getResourceAsStream(file)));
		} catch (IOException e) {
			Log.debug("Can't load image :"+file );
			e.printStackTrace();
		}
	}

	private void setTexture(Texture texture) {
		this.texture = texture;
	}
	
	public byte[] getImageData(){
		return texture.getTextureData();
	}
}
