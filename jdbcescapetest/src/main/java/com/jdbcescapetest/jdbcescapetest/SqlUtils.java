/*
 *
 */
package com.jdbcescapetest.jdbcescapetest;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class SqlUtils
{

	private static final Map<String, String> SPECIAL_CHARACTER_ESCAPE_MAP = new HashMap<>();

	static
	{
		SPECIAL_CHARACTER_ESCAPE_MAP.put("'", "''");
		SPECIAL_CHARACTER_ESCAPE_MAP.put("%", "\\%");
	}

	public static String escapeStringValue(String value)
	{
		for (Entry<String, String> escapeMap : SPECIAL_CHARACTER_ESCAPE_MAP.entrySet())
		{
			value = value.replaceAll(escapeMap.getKey(), escapeMap.getValue());
		}
		return value;
	}

}
