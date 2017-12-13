package com.dphan.dirtycow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by dphan on 12/12/17.
 */

public class ShellExecuter {

    public ShellExecuter() {
    }

    public String execute(String command) {
        StringBuffer output = new StringBuffer();

        Process process;

        try {
            process = Runtime.getRuntime().exec(command);
            process.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line = "";

            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String response = output.toString();
        return response;
    }
}
