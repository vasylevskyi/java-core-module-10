package ua.goit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        getValidPhoneNumbers("phoneNumbers.txt");
        getUsersInJSON("users.txt");

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


}