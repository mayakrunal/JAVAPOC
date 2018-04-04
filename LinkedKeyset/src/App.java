
/*
 *
 */
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class App
{

	public static void main(String[] args)
	{
		final LinkedHashMap<String, Integer> fileFields = new LinkedHashMap<>();

		fileFields.put("asdfasdf23", 1);
		fileFields.put("asdfadsfa456", 2);
		fileFields.put("asdfasdfa3245", 3);
		fileFields.put("asdfadfa367", 4);

		System.out.print(new LinkedList<>(fileFields.keySet()));
	}

}
