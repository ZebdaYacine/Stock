/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import javafx.scene.control.TextField;

/**
 *
 * @author kadri
 */
public class Functions {

    public static boolean testdouble(TextField t) {
        boolean var = false;
        if (t.getText().matches("\\d+\\.\\d+")
                || t.getText().matches("\\d+")
                || t.getText().matches("-\\d+\\.\\d+")
                || t.getText().matches("-\\d+")
                || t.getText().matches("\\d+\\/\\d+")
                || t.getText().matches("\\d+\\.\\d+\\/\\d+\\.\\d+")
                || t.getText().matches("\\d+\\/\\d+\\.\\d+")
                || t.getText().matches("\\d+\\.\\d+\\/\\d+")
                
                || t.getText().matches("\\d+\\*\\d+")
                || t.getText().matches("\\d+\\.\\d+\\*\\d+\\.\\d+")
                || t.getText().matches("\\d+\\*\\d+\\.\\d+")
                || t.getText().matches("\\d+\\.\\d+\\*\\d+")) {
            var = true;
        }
        return var;
    }

    public static boolean testint(TextField t) {
        boolean var = false;
        if (t.getText().matches("\\d+") || t.getText().matches("\\d+\\*\\d+")) {
            var = true;
        }
        return var;
    }

    public static double calculateQ(String st) {
        double q;
        boolean a = true;
        String q1 = "", q2 = "";
        if (st.matches("\\d+\\*\\d+") || st.matches("\\d+\\*")) {
            int i = 0;
            while (i < st.length()) {
                if (st.charAt(i) != '*') {
                    if (a) {
                        q1 = q1 + st.charAt(i);
                    } else {
                        q2 = q2 + st.charAt(i);
                    }
                } else {
                    a = false;
                }
                i++;
            }
            if ("".equals(q2)) {
                q = Double.parseDouble(q1);
            } else {
                q = Double.parseDouble(q1) * Double.parseDouble(q2);
            }
        } else {
            q = Double.parseDouble(st);
        }
        return q;
    }

    public static double calculatePP(String st) {
        double p;
        boolean a = true;
        String p1 = "", p2 = "";
        if (!st.matches("\\d+") && !st.matches("\\d+\\.\\d+")) {
            int i = 0;
            while (i < st.length()) {
                if (st.charAt(i) != '/') {
                    if (a) {
                        p1 = p1 + st.charAt(i);
                    } else {
                        p2 = p2 + st.charAt(i);
                    }
                } else {
                    a = false;
                }
                i++;
            }
            if ("".equals(p2)) {
                p = Double.parseDouble(p1);
            } else {
                p = Double.parseDouble(p1) / Double.parseDouble(p2);
            }
        } else {
            p = Double.parseDouble(st);
        }
        return p;
    }
}
