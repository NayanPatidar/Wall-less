import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ServerClient {

	public static void main(String[] args) {
		// Given string
		String inputString = "Nayan--R-Kinal  Kinjal--L-Nayan R-Pahal  Pahal--L-Kinjal";

		// Break the string into segments
		String[] segments = inputString.split("  ");
		for(String segment : segments){
			System.out.println(segment);
		}

		// Create a map to store the segments
		Map<String, String[]> resultMap = new HashMap<>();

		// Process each segment and add it to the map
		for (String segment : segments) {
			String[] parts = segment.split("--");

			String[] clientSides = parts[1].split(" ");
			resultMap.put(parts[0], clientSides);
			System.out.println();
		}

		// Print the result map
		for (Map.Entry<String, String[]> entry : resultMap.entrySet()) {
			String key = entry.getKey();
			String[] value = entry.getValue();
			System.out.println(key + ": " + Arrays.toString(value));
		}
	}
}
