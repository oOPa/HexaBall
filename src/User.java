import java.lang.Object;
import java.util.Hashtable;

import javax.activity.InvalidActivityException;

public class User {
	
	public enum UserKeyType 
	{
		UserKeyInvalid,
		UserKeyLeft,
		UserKeyRight,
		UserKeyUp,
		UserKeyDown,
		UserKeyHit
	};
	
	Hashtable<Integer, UserKeyType> keys = new Hashtable<Integer, UserKeyType>();
	String theName;
	
	User(String Name)
	{
		theName = Name;
	}
	
	void setKey(UserKeyType keyType, int ch) throws InvalidActivityException
	{
		if (null != keys.put(ch, keyType)) 
		{
			throw new InvalidActivityException("The key has already been registered!");
		}
	}
	
	UserKeyType getKey(int ch) 
	{
		UserKeyType n = keys.get((Object)ch);
		if (null != n) {
			return n;
		}
		
		return UserKeyType.UserKeyInvalid;
	}
}
