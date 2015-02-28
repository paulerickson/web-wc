package web.wc

import org.apache.commons.collections4.Bag
import org.apache.commons.collections4.bag.HashBag

class Document {

    String contents
    Bag words

    static constraints = {
    }

    public Document(String contents) {
        this.contents = contents
        this.words = new HashBag(contents.toLowerCase().split(/\W/).findAll {!it.isEmpty()}) // FIXME: make this more efficient
        println(words)
    }

    int getUniqueWordCount() {
        return words.uniqueSet().size()
    }

    int getWordCount() {
        return words.size()
    }

    int getCount(String word) {
        return words.getCount(word)
    }

    String getTopWord() {
        return getTopTenWords().first()
    }

    List<String> getTopTenWords() {
        List<String> topWords = words.uniqueSet().sort({-words.getCount(it)}) // negate count so that it's descending
        if (topWords.size() < 10)
            return topWords
        else
            return topWords.subList(0, 10)
    }
}
