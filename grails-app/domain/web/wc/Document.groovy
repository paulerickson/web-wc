package web.wc

class Document {

    static final CharSequence DELIMITERS = " \t\n\r\f,.;:`~!@#%^&*(){}[]<>/?=+|-_"
    final String contents
    final Map<String, Integer> words;

    public Document(String contents) {
        this.contents = contents
        this.words = countWords(contents)
        println(words)
    }

    private static Map<String, Integer> countWords(String string) {
        Map<String, Integer> words = new HashMap<>()
        parseWords(string).collectEntries(words, {
            new MapEntry(it, 1 + (words.get(it) ?: 0))
        })
    }

    private static List<String> parseWords(String string) {
        string.toLowerCase().tokenize(DELIMITERS)
    }

    int getUniqueWordCount() {
        return words.size()
    }

    int getWordCount() {
        return words.values().sum()
    }

    int getCount(String word) {
        return words.get(word) ?: 0
    }

    String getTopWord() {
        return getTopTenWords().first()
    }

    List<String> getTopTenWords() {
        return getTop(10).collect{it.key}
    }

    List<Map.Entry<String, Integer>> getTop(int num) {
        words.entrySet().sort{-it.value}.take(num) // negate count so that list is descending
    }

}
