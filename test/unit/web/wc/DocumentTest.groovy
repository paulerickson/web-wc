package web.wc

class DocumentTest extends GroovyTestCase {

    void "test counts total words"() {
        Document document = new Document("Egg sausage and bacon")
        assertEquals(4, document.getWordCount())
    }

    void "test words are unique"() {
        Document document = new Document("spam bacon sausage and spam");
        assertEquals(5, document.getWordCount())
        assertEquals(4, document.getUniqueWordCount())
    }

    void "test words are normalized"() {
        Document document = new Document("Spam, bacon, sausage, and spam...");
        assertEquals(5, document.getWordCount())
        assertEquals(4, document.getUniqueWordCount())
    }

    void "test counts each word"() {
        Document document = new Document("Spam, eggs, spam, spam, bacon, and spam")
        assertEquals(4, document.getCount("spam"))
        assertEquals(1, document.getCount("eggs"))
        assertEquals(1, document.getCount("bacon"))
        assertEquals(0, document.getCount("sausage"))
    }

}
