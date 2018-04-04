/*
 *
 */
package JoinTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App
{

	private static List<Map<String, Object>> doFullOuterJoin(List<Map<String, Object>> list1,
			List<Map<String, Object>> list2)
	{

		Map<String, Map<String, Object>> result = Stream.concat(list1.stream(), list2.stream())
				.collect(Collectors.toMap(m -> (String) m.get("Employee_ID"), m -> m, (m1, m2) ->
				{
					m1.putAll(m2);
					return m1;
				}));
		System.out.println("mapresults = " + result);

		List<Map<String, Object>> outerjoinresult = new ArrayList<>(result.values());

		return outerjoinresult;
	}

	private static Map<String, String> getMapping()
	{
		Map<String, String> map = new HashMap<>();
		map.put("emp_id", "Employee_ID");
		map.put("employeeid", "Employee_ID");
		map.put("location", "Location");
		map.put("Org", "Organization");
		map.put("Cost", "Loaded_Cost");
		return map;
	}

	public static void main(String[] args)
	{
		List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> list2 = new ArrayList<>();

		Map<String, String> l1 = new HashMap<String, String>();
		Map<String, String> l2 = new HashMap<>();

		l1.put("A", "1");
		l1.put("B", "2");
		l1.put("C", "3");

		l2.put("A", "1");
		l2.put("B", "2");
		l2.put("C", "3");

		List<Map<String, String>> lh = new ArrayList<Map<String, String>>();

		lh.add(l1);
		lh.add(l2);

		System.out.println("l1 = l2 :" + l1.equals(l2));
		System.out.println("distinct lj = l2 :" + lh.stream().distinct().collect(Collectors.toList()));

		Map<String, String> columnmappings = getMapping();

		// add data from first file as column <-> value pair
		list1.add(map("emp_id", "1", "location", "US"));
		list1.add(map("emp_id", "2", "Cost", "23"));

		// add data from second file as column <-> value pair
		list2.add(map("employeeid", "2", "location", "China"));
		list2.add(map("emp_id", "3", "Org", "PWC"));

		System.out.println("originallist1 = " + list1);

		System.out.println("originallist2 = " + list2);

		list1 = replaceSourceColumnNameWithTemplateName(list1, columnmappings);
		list2 = replaceSourceColumnNameWithTemplateName(list2, columnmappings);

		System.out.println("list1 = " + list1);

		System.out.println("list2 = " + list2);

		// lets do the outer join
		List<Map<String, Object>> outerjoinresult = doFullOuterJoin(list1, list2);
		System.out.println("outerjoinresult = " + outerjoinresult);

	}

	private static Map<String, Object> map(Object... kvs)
	{
		Map<String, Object> map = new HashMap<>();
		for (int i = 0; i < kvs.length; i += 2)
		{
			map.put((String) kvs[i], kvs[i + 1]);
		}
		return map;
	}

	private static List<Map<String, Object>> replaceSourceColumnNameWithTemplateName(
			List<Map<String, Object>> sourcerowcollection, Map<String, String> mappings)
	{
		// we are going to save template row collection with changed column
		// names
		List<Map<String, Object>> templaterowcollection = new ArrayList<>();

		// iterate over the source row collection
		for (Map<String, Object> sourcerow : sourcerowcollection)
		{

			// create the template row
			Map<String, Object> templaterow = new HashMap<>();

			// iterate over the mappings to fill our template row with data
			for (Map.Entry<String, String> onemap : mappings.entrySet())
			{
				String sourceColumnName = onemap.getKey();
				String templateColumnName = onemap.getValue();

				if (sourcerow.containsKey(sourceColumnName))
				{
					templaterow.put(templateColumnName, sourcerow.get(sourceColumnName));
				}
			}
			// add the template row to our template row collection
			templaterowcollection.add(templaterow);
		}
		return templaterowcollection;
	}

}
