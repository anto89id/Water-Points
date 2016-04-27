/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anto89.utils;

import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Hendranto Nugroho
 */
public class JsonFormatter {

    public static String format(final JSONObject object) {
        final JsonVisitor visitor = new JsonVisitor(4, ' ');
        visitor.visit(object, 0);
        return visitor.toString();
    }

    private static class JsonVisitor {

        private final StringBuilder builder = new StringBuilder();
        private final int indentationSize;
        private final char indentationChar;

        public JsonVisitor(final int indentationSize, final char indentationChar) {
            this.indentationSize = indentationSize;
            this.indentationChar = indentationChar;
        }

        private void visit(final JSONArray array, final int indent) {
            final int length = array.size();
            if (length == 0) {
                writeln("[]", indent);
            } else {
                writeln(" ", indent);
                writeln("[", indent);
                for (int i = 0; i < length; i++) {
                    visit(array.get(i), indent + 1);
                }
                write("]", indent);
            }

        }

        private void visit(final JSONObject obj, final int indent) {
            final int length = obj.size();
            if (length == 0) {
                writeln("{}", indent);
            } else {
                if (indent == 0) {
                    writeln("{", indent);
                } else {
                    write("{", indent);
                }
                final Iterator<String> keys = obj.keySet().iterator();
                while (keys.hasNext()) {
                    final String key = keys.next();
                    write(key + ": ", 0);
                    visit(obj.get(key), indent + 1);
                    if (keys.hasNext()) {
                        write(", ", 0);
                        if (indent == 0) {
                            writeln("", 0);

                        }
                    }
                }
                writeln("}", 0);
            }

        }

        private void visit(final Object object, final int indent) {
            if (object instanceof JSONArray) {
                visit((JSONArray) object, indent);
            } else if (object instanceof JSONObject) {
                visit((JSONObject) object, indent);
            } else {
                if (object instanceof String) {
                    write("\"" + (String) object + "\"", 0);
                } else {
                    write(String.valueOf(object), 0);
                }
            }

        }

        private void write(final String data, final int indent) {
            for (int i = 0; i < (indent * indentationSize); i++) {
                builder.append(indentationChar);
            }
            builder.append(data);
        }

        private void writeln(final String data, final int indent) {
            for (int i = 0; i < (indent * indentationSize); i++) {
                builder.append(indentationChar);
            }
            builder.append(data).append('\n');
        }

        @Override
        public String toString() {
            return builder.toString();
        }

    }

}
