// Anagrams.java
// CSC 211 - Practical 5 - Anagram Dictionary
// Assisted by Claude in exporting to a LaTeX file and minor compilation issues (claude.ai, free version)

import java.io.*;
import java.util.*;

public class Anagrams {

    public static String signature(String word) {
        char[] charArr = word.toCharArray();
        Arrays.sort(charArr);
        return new String(charArr);
    }

    public static void main(String[] args) throws IOException {

        if (args.length != 1) {
            System.out.println("Usage: ./anagrams inputfile.\n You gave:\n " + Arrays.toString(args));
            System.exit(1);
        }

        BufferedReader bf = new BufferedReader(
            new InputStreamReader(new FileInputStream(args[0]), "ISO-8859-1")
        );
        System.out.println("Data file: " + args[0]);

        HashMap<String, Integer> hashD = new HashMap<>();

        String line = bf.readLine();
        while (line != null) {
            for (String w : line.split("\\s+")) {
                w = w.replaceAll("^[\\[\\]0-9(,.:;_.!?\\-]+|[\\[\\]0-9(,.:;_.!?\\-]+$", "");
                if (w.isEmpty()) continue;
                hashD.put(w, hashD.getOrDefault(w, 0) + 1);
            }
            line = bf.readLine();
        }
        bf.close();

        HashMap<String, ArrayList<String>> A = new HashMap<>();
        for (String w : hashD.keySet()) {
            String a = signature(w);
            if (!A.containsKey(a)) {
                ArrayList<String> list = new ArrayList<>();
                list.add(w);
                A.put(a, list);
            } else {
                A.get(a).add(w);
            }
        }

        PrintWriter anagramsFile = new PrintWriter(new FileWriter("anagrams"));
        for (String key : A.keySet()) {
            ArrayList<String> group = A.get(key);
            if (group.size() > 1) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < group.size(); i++) {
                    if (i == 0) sb.append(group.get(i));
                    else sb.append(" ").append(group.get(i));
                }
                String anagramlist = sb.toString();
                anagramsFile.print(anagramlist + "\\\\\n");
                for (int repeat = 0; repeat < group.size() - 1; repeat++) {
                    int space = anagramlist.indexOf(' ');
                    anagramlist = anagramlist.substring(space + 1) + ' ' + anagramlist.substring(0, space);
                    anagramsFile.print(anagramlist + "\\\\\n");
                }
            }
        }
        anagramsFile.close();

        BufferedReader asf = new BufferedReader(new FileReader("anagrams"));
        List<String> arrList = new ArrayList<>();
        String ln;
        while ((ln = asf.readLine()) != null) {
            arrList.add(ln);
        }
        asf.close();
        Collections.sort(arrList);

        new File("Latex").mkdirs();
        PrintWriter asftex = new PrintWriter(new FileWriter("Latex/SortedAnagrams.tex"));
        char letter = 'X';
        for (String lemma : arrList) {
            if (lemma.isEmpty()) continue;
            char initial = lemma.charAt(0);
            if (Character.toLowerCase(initial) != Character.toLowerCase(letter)) {
                letter = initial;
                asftex.println("\n\\vspace{14pt}\n\\noindent\\textbf{\\Large " + Character.toUpperCase(initial) + "}\\\\*[+12pt]");
            }
            asftex.print(lemma + "\n"); 
        }
        asftex.close();

        try {
            new File("anagrams").delete();
        } catch (Exception e) {}
    }
}