package wrappy;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.clubobsidian.wrappy.Configuration;
import com.clubobsidian.wrappy.ConfigurationSection;

public class TestConfigurationYaml {

	private static File testFile = new File("test.yml");
	private static Configuration config = Configuration.load(testFile);
	
	@Test
	public void testGet()
	{
		assertTrue("Config get is null", config.get("key") != null);
		assertTrue("Config get is not null", config.get("non-existent-key") == null);
	}
	
	@Test
	public void testGetString()
	{
		assertTrue("Config getString is null", config.getString("key") != null);
		assertTrue("Config getString is not null", config.getString("non-existent-key") == null);
	}

	@Test
	public void testGetInteger()
	{
		assertTrue("Config getInteger is 0", config.getInteger("integer") == 5);
		assertTrue("Config getInteger is not 0", config.getInteger("non-existent-integer") == 0);
	}
	
	@Test
	public void testGetLong()
	{
		assertTrue("Config getLong is not 6", config.getLong("long") == 6);
		assertTrue("Config getLong is not 0", config.getLong("non-existent-long") == 0);
	}
	
	@Test
	public void testGetFloat()
	{
		float fl = config.getFloat("float");
		assertTrue("Config getFloat is not between 0.7 and 0.9", fl < 0.9 && fl > 0.7);
		assertTrue("Config getFloat is not 0", config.getFloat("non-existent-float") == 0);
	}
	
	@Test
	public void testGetBoolean()
	{
		assertTrue("Config getBoolean is not true", config.getBoolean("boolean"));
		assertTrue("Config getBoolean is not false", config.getBoolean("non-existent-boolean") == false);
	}
	
	@Test
	public void testGetDouble()
	{
		double dub = config.getDouble("double");
		assertTrue("Config getDouble is not between 0.6 and 0.8", dub < 0.8 && dub > 0.6);
		assertTrue("Config getDouble is not 0", config.getFloat("non-existent-double") == 0);
	}
	
	@Test
	public void testGetStringList()
	{
		List<String> list = config.getStringList("string-list");
		assertTrue("Config getStringList 0 index is not asdf", list.get(0).equals("asdf"));
		assertTrue("Config getStringList size is not 1", list.size() == 1);
	}
	
	@Test
	public void testGetIntegerList()
	{
		List<Integer> list = config.getIntegerList("integer-list");
		assertTrue("Config getIntegerList 1 index is not 7", list.get(1) == 7);
		assertTrue("Config getIntegerList size is not 2", list.size() == 2);
	}
	
	@Test
	public void testGetLongList()
	{
		List<Long> list = config.getLongList("long-list");
		assertTrue("Config getIntegerList 1 index is not 5", list.get(0) == 5);
		assertTrue("Config getIntegerList size is not 1", list.size() == 1);
	}
	
	@Test
	public void testGetFloatList()
	{
		List<Float> list = config.getFloatList("float-list");
		assertTrue("Config getFloatList 1 index is not greater than 0", list.get(0) > 0);
		assertTrue("Config getFloatList size is not 1", list.size() == 1);
	}
	
	@Test
	public void testGetBooleanList()
	{
		List<Boolean> list = config.getBooleanList("boolean-list");
		assertTrue("Config getBooleanList 1 index is not false", list.get(1) == false);
		assertTrue("Config getBooleanList size is not 2", list.size() == 2);
	}
	
	@Test
	public void testGetDoubleList()
	{
		List<Double> list = config.getDoubleList("double-list");
		assertTrue("Config getDoubleList 1 index is not > 1 && < 2", list.get(0) > 1 && list.get(0) < 2);
		assertTrue("Config getDoubleList size is not 1", list.size() == 1);
	}
	
	@Test
	public void testGetKeys()
	{
		List<String> keys = config.getKeys();
		assertTrue("Config getKeys is not size 13", keys.size() == 13);
	}
	
	@Test
	public void testIsEmpty()
	{
		assertTrue("Config is empty", !config.isEmpty());
	}
	
	@Test
	public void testHasKey()
	{
		assertTrue("Config does not have the key \"key\"", config.hasKey("key"));
	}
	
	@Test
	public void testGetConfigurationSection()
	{
		ConfigurationSection section = config.getConfigurationSection("section");
		assertTrue("Section is null", section != null);
		assertTrue("Section key size is not 1", section.getKeys().size() == 1);
	}
	
	@Test
	public void testSet()
	{
		config.set("key", "newvalue");
		assertTrue("Set was not able to set key to newvalue", config.getString("key").equals("newvalue"));
		config.set("key", "value");
	}
	
	@Test
	public void testSave()
	{
		config.set("key", "newvalue");
		config.save();
		config = Configuration.load(testFile);
		assertTrue("Save did not save value to key", config.getString("key").equals("newvalue"));
		config.set("key", "value");
		config.save();
	}
}