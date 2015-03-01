package web.wc

class Document {

    static final CharSequence DELIMITERS = " \t\n\r\f,.;:`~!@#%^&*(){}[]<>/?=+|-_–'\"‘’"
    static final Set<String> BORING_WORDS = [
            "the", "with", "and", "this", "more", "for", "from",
            "are", "has", "have", "you", "that", "our", "its"
    ]
    String url
    Map<String, Integer> words;

    static hasMany = [words: Integer]

    public Document(String contents) {
        this(null, contents)
    }

    public Document(String url, String contents)  {
        this.url = url
        this.words = countWords(contents)
    }

    private static Map<String, Integer> countWords(String string) {
        Map<String, Integer> words = new HashMap<>()
        parseWords(string).collectEntries(words, {
            new MapEntry(it, 1 + (words.get(it) ?: 0))
        })
    }

    private static List<String> parseWords(String string) {
        return string.toLowerCase().tokenize(DELIMITERS)
    }

    public String getUrl() {
        return this.url
    }

    /***
     * @return the portion after 'http://'
     */
    public String getShortUrl() {
        return url.substring(7)
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

    String getTopWord(boolean filterBoringWords = false) {
        return getTopTenWords(filterBoringWords).first()
    }

    List<String> getTopTenWords(boolean filterBoringWords = false) {
        return getTop(10, filterBoringWords).collect{it.key}
    }

    List<Map.Entry<String, Integer>> getTop(int num, boolean filterBoringWords = false) {
        getWords(filterBoringWords).entrySet().sort{-it.value}.take(num) // negate count so that list is descending
    }

    Map<String, Integer> getWords(boolean filterBoringWords = false) {
        if (filterBoringWords)
            return words.findAll{it.key.length() > 2 && !BORING_WORDS.contains(it.key)}
        else
            return words
    }

    void setWords(Map<String, Integer> words) {
        this.words = words
    }

}
