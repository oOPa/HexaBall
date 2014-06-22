import java.awt.Color;

public class Stadium2Factory extends AbstractStadiumFactory 
{
	@Override
	public FieldLines createFieldLines() 
	{
		return new DashedFieldLines();
	}

	@Override
	public Field createField() 
	{
		ColoredField coloredField = new ColoredField();
		coloredField.setColor(Color.BLUE);
		return coloredField;
	}

	@Override
	public Goal createGoal() 
	{
		return new TriangleGoal();
	}
}
