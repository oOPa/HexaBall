import java.util.LinkedList;
import java.util.ArrayList;

public class SetupModel {

	public enum SetupModelItem 
	{
		SetupModelItemInvalid, 
		SetupModelItemGoalKeeper, 
		SetupModelItemTime, 
		SetupModelPlayerTypeLeft, 
		SetupModelPlayerTypeRight, 
		SetupModelPlayerUniformTypeLeft, 
		SetupModelPlayerUniformTypeRight, 
		SetupModelPlayerUniformNumberLeft, 
		SetupModelPlayerUniformNumberRight
	}

	ArrayList<LinkedList<String>> items = new ArrayList<LinkedList<String>>();
	ArrayList<String> currentItems = new ArrayList<String>();

	SetupModel(int itemCount)
	{
		for (int i = 0; i < itemCount; ++i)
		{
			items.add(new LinkedList<String>());
			currentItems.add("");
		}

		items.get(1).add("Stadium - 1");
		items.get(1).add("Stadium - 2");
		items.get(1).add("Stadium - 3");
		currentItems.set(1, "Stadium - 1");

		items.get(2).add("Goal Keeper ON");
		items.get(2).add("Goal Keeper OFF");
		currentItems.set(2, "Goal Keeper ON");

		items.get(3).add("01:00");
		items.get(3).add("02:00");
		items.get(3).add("05:00");
		items.get(3).add("10:00");
		items.get(3).add("15:00");
		items.get(3).add("30:00");
		items.get(3).add("45:00");
		items.get(3).add("90:00");
		currentItems.set(3, "01:00");

		items.get(4).add("Defender");
		items.get(4).add("Midfielder");
		items.get(4).add("Forward");
		currentItems.set(4, "Defender");

		items.get(5).add("Blue");
		items.get(5).add("Black");
		items.get(5).add("Green");
		currentItems.set(5, "Blue");

		items.get(6).add("2");
		items.get(6).add("3");
		items.get(6).add("4");
		items.get(6).add("5");
		items.get(6).add("6");
		items.get(6).add("7");
		items.get(6).add("8");
		items.get(6).add("9");
		items.get(6).add("10");
		items.get(6).add("11");
		currentItems.set(6, "2");

		items.get(7).add("Defender");
		items.get(7).add("Midfielder");
		items.get(7).add("Forward");
		currentItems.set(7, "Midfielder");

		items.get(8).add("Red");
		items.get(8).add("Yellow");
		items.get(8).add("Purple");
		currentItems.set(8, "Red");

		items.get(9).add("2");
		items.get(9).add("3");
		items.get(9).add("4");
		items.get(9).add("5");
		items.get(9).add("6");
		items.get(9).add("7");
		items.get(9).add("8");
		items.get(9).add("9");
		items.get(9).add("10");
		items.get(9).add("11");

		currentItems.set(9, "3");
	}

	void decreaseValue(Integer itemIndex, String text)
	{
		Integer subItemIndex = items.get(itemIndex).indexOf(text);

		if (subItemIndex >= 0) {
			if (--subItemIndex < 0) {
				subItemIndex = items.get(itemIndex).size() - 1;
			}
			currentItems.set(itemIndex, items.get(itemIndex).get(subItemIndex));
		}
	}

	void increaseValue(Integer itemIndex, String text) 
	{
		Integer subItemIndex = items.get(itemIndex).indexOf(text);

		++subItemIndex;
		subItemIndex %= items.get(itemIndex).size();

		//System.out.println("Item: " + items.get(itemIndex).get(subItemIndex));
		currentItems.set(itemIndex, items.get(itemIndex).get(subItemIndex));
	}

	String getItemValue(Integer itemIndex) 
	{
		return currentItems.get(itemIndex);
	}
}