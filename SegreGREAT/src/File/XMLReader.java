package File;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import view.GameView;
import android.content.res.AssetManager;

public class XMLReader {
	
	private GameView gameView;
	private AssetManager assetManager;
	
	public XMLReader(GameView gw){
		this.gameView = gw;
	}
	
	public XMLReader(AssetManager am){
		this.assetManager = am;
	}
	
	public Element readXML(String location){
		if(assetManager==null){
			assetManager = gameView.getContext().getAssets();
		}
		Element rootElement = null;
		try {
			InputStream is = assetManager.open(location);
			SAXBuilder builder = new SAXBuilder();
				Document document = (Document) builder.build(is);
				rootElement = document.getRootElement();
				/*for(Element tokenElement: tokenElements){
					String name = tokenElement.getAttributeValue("name");
					String translation = tokenElement.getAttributeValue("translation");
					String type = tokenElement.getAttributeValue("type");
				}*/
			is.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch(Exception e){e.printStackTrace();}
		
		
		return rootElement;
	}
	
}
