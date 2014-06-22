public class ForwardPlayerModel extends PlayerModel 
{
	@Override
	int acceleration() 
	{
		return 5; /* 0 - 10 */
	}

	@Override
	int stamina() 
	{
		return 5; /* 0 - 10 */
	}

	@Override
	int shootPower() 
	{
		return 9; /* 0 - 10 */
	}
}
