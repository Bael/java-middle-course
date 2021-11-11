package io.github.bael.part1.inner;

import java.util.Iterator;
import java.util.Locale;

// nested
public class Journal implements Iterable<String> {
    String[] data;

    private static class Filter {
        String a;
    }

    @Override
    public Iterator<String> iterator() {
        return new JournalIterator();
    }


    private class JournalIterator implements Iterator<String> {
        int i = 0;
        @Override
        public boolean hasNext() {
            return i < data.length;
        }

        @Override
        public String next() {
//            interface formatString { // NOT allowed
//
//            }
            // local classes
            class Formatter {
                String format(String s) {
                    return i + " -> " + s.toUpperCase(Locale.ROOT);
                }
            }


            return new MakeEvenSymbolsLowerCase() {
                @Override
                public String format(String format) {
                    char[] chars = format.toCharArray();
                    for (int i = 0; i <chars.length; i++) {
                        if (i % 2 == 0) {
                            chars[i] = Character.toLowerCase(chars[i]);
                        }
                    }
                    return new String(chars);
                }
            }.format(
                    new Formatter().format(data[i++])
            );
        }
    }

    public static void main(String[] args) {
        Journal journal = new Journal();
        journal.data = new String[] {"one", "two", "three"};
        for(String s : journal) {
            System.out.println(s);
        }
    }

    interface MakeEvenSymbolsLowerCase {
        String format(String format);
    }
}
