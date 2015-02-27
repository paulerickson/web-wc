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
        this.words = new HashBag(contents.toLowerCase().split(/\W/).findAll{!it.isEmpty()})
        print(words)
    }

    int getUniqueWordCount() {
        return words.unique().size()
    }

    int getWordCount() {
        return words.size()
    }

    int getCount(String word) {
        return words.getCount(word)
    }
}
