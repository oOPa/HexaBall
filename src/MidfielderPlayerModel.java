public class MidfielderPlayerModel extends PlayerModel 
{
	@Override
	int acceleration() 
	{
		return 7; /* 0 -- 10 */
	}

	@Override
	int stamina() 
	{
		return 7; /* 0 -- 10 */
	}


	@Override
	int shootPower() 
	{
		return 5; /* 0 -- 10 */
	}
}
