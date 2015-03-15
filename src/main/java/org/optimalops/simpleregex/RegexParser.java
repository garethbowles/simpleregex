/*
  Parse a string with a simple regex where:
    * denotes 0 or more of any character  
    + denotes 1 or more of any character
    ? denotes a single arbitrary character  
*/

/*
  TODO

  1. Preprocess regex to replace multiple * with single *
  2. Cache each partial-regex/string pair with its result to avoid reevaluation  
  
*/
package org.optimalops.simpleregex;

public class RegexParser {
    public static boolean match(String regex, String candidate) {
        // '+' is equivalent to "?*", so preprocess the regex
        String processedRegex = regex.replaceAll("\\+", "?*");
        return recursiveMatch(processedRegex, candidate);
    }

    private static boolean recursiveMatch(String regex, String candidate) {
        // Empty regex will always return false
        if (regex.isEmpty()) {
            return false;
        } else {
            if (regex.charAt(0) == '*') {
                // Last * matches everything
                if (regex.length() == 1) {
                    return true;
                } else {
                    return matchStar(regex.substring(1), candidate);
                }
            } else if (candidate.isEmpty()) {
                // Return false if text is empty but pattern is not *
                return false;
            } else if (regex.charAt(0) == '?' || regex.charAt(0) == candidate.charAt(0)) {
                // If the last regex matches the last text
                if (regex.length() == 1 && candidate.length() == 1) {
                    return true;
                // If not at end, try to recursiveMatch the rest of the string
                } else {                  
                    return recursiveMatch(regex.substring(1), candidate.substring(1));
                }
            } else {
                return false; 
            }
        }
    }

    // Otherwise skip as many chars as required
    private static boolean matchStar(String regex, String candidate) {
        for (int i = 0; i < candidate.length(); i++) {
            if (recursiveMatch(regex, candidate.substring(i))) {
                return true;
            } else {
                continue;
            }
        }
        return false;
    }
}
