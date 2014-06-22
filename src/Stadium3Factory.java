import java.awt.Color;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public class Stadium3Factory extends AbstractStadiumFactory 
{
	@Override
	public FieldLines createFieldLines() {
		ColoredFieldLines coloredFieldLines = new ColoredFieldLines();
		coloredFieldLines.setColor(Color.ORANGE);
		return coloredFieldLines;
	}

	@Override
	public Field createField() 
	{
		TexturedField texturedField = new TexturedField();
		File imagePath = new File("C:\\Users\\meerd\\Documents\\eclipse\\Hexaball\\img\\texture.jpg");
		try 
		{
	        Image image = ImageIO.read(imagePath );
			texturedField.setTexture(image);
		} catch (Exception e)
		{
			;/*do nothing*/
		}
		
		return texturedField;
	}

	@Override
	public Goal createGoal() 
	{
		return new CircularGoal();
	}
}
