package Data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	public List<HashMap<String,String>> getDataJsonToMap(String Filepath) throws IOException {
		//read json to string 
		String JsonsContents = FileUtils.readFileToString(new File(Filepath),StandardCharsets.UTF_8);
		
		//String toHashMAp JacksonDataBind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(JsonsContents, new TypeReference<List<HashMap<String, String>>>()
				{});
		return data;
		
	}
}
