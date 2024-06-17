package main.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileRead {
    public static HashMap<String, ArrayList<String>> read(String path){
        HashMap<String, ArrayList<String>> dataMap = new HashMap<>();
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
//                System.out.println(data);
                String[] dataArr = data.split(",");
                String key = dataArr[0];
                ArrayList<String> value = new ArrayList<>();
                for (int i = 1; i < dataArr.length; i++) {
                    value.add(dataArr[i]);
                }
                dataMap.put(key, value);

            }

            myReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataMap;
    }

    public static Integer[] uniqueMap(String filePath){
        Set<Integer> uniqueValues = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); // Split by whitespace
                for (String value : values) {
                    try {
                        if (value.equals("-1")){
                            continue;
                        }
                        uniqueValues.add(Integer.parseInt(value));
                    } catch (NumberFormatException e) {
                        // Handle the case where the value is not an integer
                        System.out.println("Invalid number format: " + value);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convert the set to an array
        Integer[] uniqueArray = uniqueValues.toArray(new Integer[0]);

        return uniqueArray;
    }

    public static Integer[][] getMap(String filePath) {
        ArrayList<Integer[]> map = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); // Split by whitespace
                Integer[] row = new Integer[values.length];
                for (int i = 0; i < values.length; i++) {
                    try {
                        row[i] = Integer.parseInt(values[i]);
                    } catch (NumberFormatException e) {
                        // Handle the case where the value is not an integer
                        System.out.println("Invalid number format: " + values[i]);
                    }
                }
                map.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map.toArray(new Integer[0][0]);
    }

}
