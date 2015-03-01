package web.wc

class DocumentServiceTest extends GroovyTestCase {

    DocumentService svc

    void setUp() {
        svc = new DocumentService()
    }

    void "test lookup bogus url"() {
        shouldFail{svc.retrieveFromWeb("some://bogus.url")}
    }

    void "test lookup actual url"() {
        Document document = svc.retrieveFromWeb("http://catb.org/jargon/html/S/spam.html")
        print document.getTop(10)
    }

}
