package ua.goit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        getValidPhoneNumbers("phoneNumbers.txt");
        getUsersInJSON("users.txt");
        getWordsSortedByCount("words.txt");

    }

    public static void getValidPhoneNumbers(String fileName) {
        try (FileReader fileReader = new FileReader(fileName)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            StringBuilder resultSet = new StringBuilder();
            while (line != null) {
                if (line.matches("(\\d{3} )(\\d{3} )(\\d{4})")) {
                    line = bufferedReader.readLine();
                    continue;
                }
                resultSet.append(line + "\n");
                line = bufferedReader.readLine();
            }
            System.out.println(resultSet);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getUsersInJSON(String fileName) {
        try (FileReader fileReader = new FileReader(fileName)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            ArrayList<User> usersArrayList = new ArrayList<>();
            String[] userData = new String[2];
            while (line != null) {
                if (line.equals("name age")) {
                    line = bufferedReader.readLine();
                    continue;
                }
                userData = line.split(" ");
                User oneUser = new User(userData[0], Integer.valueOf(userData[1]));
                usersArrayList.add(oneUser);
                line = bufferedReader.readLine();
            }
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(usersArrayList);
            System.out.println(json);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getWordsSortedByCount(String fileName) {
        try (FileReader fileReader = new FileReader(fileName)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            StringBuilder allLines = new StringBuilder();
            String[] allWords;
            int counter = 0;
            Map<String, Integer> wordsWithCount = new TreeMap<>();

/*          Map<Integer, String> countWithWords = new TreeMap<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    if (o1 < o2) {
                        return 1;
                    } else if (o1 > o2) {
                        return -1;
                    }
                    return 0;
                }

            });*/

            while (line != null) {
                allLines.append(line + "\n");
                line = bufferedReader.readLine();
            }
            allWords = allLines.toString().split("\\s+");

            for (int i = 0; i < allWords.length; i++) {
                for (int j = 0; j < allWords.length; j++) {
                    if (allWords[i].equals(allWords[j])) {
                        counter++;
                    }
                }
                wordsWithCount.put(allWords[i], (Integer) counter);
                counter = 0;

            }
            MyComparator comp=new MyComparator(wordsWithCount);
            Map<String,Integer> newSortedMap = new TreeMap(comp);
            newSortedMap.putAll(wordsWithCount);

            for (Map.Entry<String, Integer> item: newSortedMap.entrySet()) {
                System.out.println(item.getKey() + " " + item.getValue());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}