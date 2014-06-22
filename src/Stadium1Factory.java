import java.awt.Color;

public class Stadium1Factory extends AbstractStadiumFactory
{
	@Override
	public FieldLines createFieldLines() 
	{
		ColoredFieldLines fieldLines = new ColoredFieldLines();
		fieldLines.setColor(Color.ORANGE);
		return fieldLines;
	}

	@Override
	public Field createField() 
	{
		return new DefaultField();
	}

	@Override
	public Goal createGoal() 
	{
		return new DefaultGoal();
	}
}